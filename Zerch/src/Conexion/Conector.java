package Conexion;

import InterfazServidor.IServidor;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 * @author zerch
 */
public class Conector
{
	private AdministradorEventos administrador;
	private Logger logger;
	private Link link;
	
	public Conector()
	{
		logger = Logger.getLogger(Conector.class.getName());
	}

	public void conectar()
	{
		String direccionServidor = JOptionPane.showInputDialog("ip Servidor:");
		logger.log(Level.INFO, "Tratando de conectar al servidor...");
		try
		{
			String nombreProyecto = JOptionPane
					.showInputDialog("Nombre Proyecto:");
			Registry reg = LocateRegistry.getRegistry(direccionServidor, 1234);
			IServidor servidor = (IServidor) reg.lookup(nombreProyecto);
			registrar(servidor);
			logger.log(Level.INFO, "Conexion establecida");
		} catch (RemoteException | NotBoundException e)
		{
			logger.log(Level.SEVERE, "Error al momento de intentar conectar.");
			conectar();
		}
	}

	private void registrar(IServidor servidor) throws NotBoundException
	{
		String nombreUsuario = JOptionPane.showInputDialog("Nick:");
		String portSt = null;
		int portStr = 0;
		do
		{
			try
			{
				portSt = JOptionPane.showInputDialog("Puerto(4 digitos):");
				portStr = Integer.parseInt(portSt);
			} catch (NumberFormatException e)
			{
				JFrame f = new JFrame();
				JOptionPane.showMessageDialog(f,
						"El Puerto debe tener 4 caracteres numericos");
				f.dispose();
			}
		} while (portStr == 0);

		if (nombreUsuario != null && portSt != null)
		{
			if (controlPuerto(portStr))
			{
				try
				{
					Usuario usuario = new Usuario(nombreUsuario, portStr, null);
					Registry reg = LocateRegistry.createRegistry(portStr);
					reg.rebind(usuario.getName(), usuario);
					boolean resultadoConexion = servidor.registrarUsuario(
							usuario.getName(), usuario.getIp(),
							usuario.getPort());
					if (resultadoConexion == true)
					{
						logger.log(Level.INFO,
								"Usuario registrado correctamente");
						administrador = new AdministradorEventos(usuario,
								servidor);
						link = new Link(administrador);
						usuario.addLink(link);
						servidor.getUpdates(usuario);
					} else
					{
						logger.log(Level.INFO,
								"Usuario no fue registrado, El Nick ya existe");
						JFrame f = new JFrame();
						JOptionPane.showMessageDialog(f,
								"Usuario no fue registrado, El id ya existe");
						f.dispose();
						reg.unbind(usuario.getName());
						UnicastRemoteObject.unexportObject(reg, true);
						registrar(servidor);
					}
				} catch (RemoteException ex)
				{
					logger.log(Level.SEVERE,
							"Ocurrio un problema al registrar el usuario", ex);
				}
			} else
			{
				JFrame f = new JFrame();
				JOptionPane.showMessageDialog(f,
						"El Puerto debe tener 4 caracteres numericos");
				f.dispose();
				registrar(servidor);
			}
		}
	}
	private boolean controlPuerto(int puerto)
	{
		return puerto > 1000 && puerto < 10000;
	}

}
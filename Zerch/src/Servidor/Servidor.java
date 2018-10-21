package Servidor;

import ElementosServidor.ComponenteServer;
import ElementosServidor.ElementoInterface;
import ElementosServidor.EntornoServer;
import InterfazServidor.IServidor;
import InterfazServidor.IUsuario;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
//import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author zerch
 */
@SuppressWarnings("serial")
public class Servidor extends UnicastRemoteObject implements IServidor
{

	private ArrayList<IUsuario> usuarios;
	// pensado para persistencia de los usuarios en el proyecto
	// private List<UsuarioServer> usuariosServer;
	private DatosConexion datos;
	private ProyectoServidor proyecto;
	private Logger logger;

	/**
	 * constructor de la clase Servidor;
	 */
	public Servidor() throws RemoteException
	{
		usuarios = new ArrayList<>();
		datos = new DatosConexion();
		logger = Logger.getLogger("ChatServer");
		proyecto = new ProyectoServidor();
	}

	/**
	 * Inicia el servicio en la red.
	 */
	public void iniciarServidor()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		try
		{
			Registry reg = LocateRegistry.createRegistry(datos.getPort());
			reg.rebind(proyecto.getNombreProyecto(), this);
			logger.log(Level.INFO, "Server up!");
			logger.log(Level.INFO, "IP: {0} Puerto: {1}", new Object[]
			{ datos.getIp(), datos.getPort() });
		} catch (RemoteException e)
		{
			logger.log(Level.SEVERE, "Server down.");
		}
	}

	/**
	 * registra un usuario en el servidor.
	 * 
	 * @param user
	 *            usuario a registrar.
	 */
	@Override
	public boolean registrarUsuario(String userName, String nombreHost,
			int port) throws RemoteException
	{
		boolean respuesta = false;
		try
		{
			Registry reg = LocateRegistry.getRegistry(nombreHost, port);
			IUsuario observer = (IUsuario) reg.lookup(userName);
			if (!existeUsuario(userName))
			{
				this.usuarios.add(observer);
				logger.log(Level.INFO, "Cliente registrado correctamente");
				respuesta = true;
			} else
			{
				logger.log(Level.SEVERE,
						"Cliente no registrado, el id ya esta siendo usado");
			}
		} catch (RemoteException | NotBoundException e)
		{
			logger.log(Level.SEVERE, "Cliente no registrado correctamente" + e);
		}
		return respuesta;
	}

	public void getUpdates(IUsuario observer) throws RemoteException
	{
		observer.updateChat(proyecto.obtenerMensajes());
		ArrayList<ElementoInterface> elementos = proyecto.getElementos();
		for (int i = 0; i < elementos.size(); i++)
		{
			if (elementos.get(i) instanceof EntornoServer)
			{
				EntornoServer entorno = (EntornoServer) elementos.get(i);
				observer.updateNuevoEntorno(entorno.getNombre());
			} else
			{
				ComponenteServer componente = (ComponenteServer) elementos
						.get(i);
				if (componente.getClase().equals("Label"))// agregar los
															// componentes al
															// cliente :D
				{
					observer.updateNuevoLabel(componente.getX(),
							componente.getY(), componente.getWidth(),
							componente.getHeight(), componente.getPosParent(),
							componente.getText());
				}
				if (componente.getClase().equals("Panel"))// agregar los
															// componentes al
															// cliente :D
				{
					observer.updateNuevoPanel(componente.getX(),
							componente.getY(), componente.getWidth(),
							componente.getHeight(), componente.getPosParent());
				}
				if (componente.getClase().equals("Button"))// agregar los
															// componentes al
															// cliente :D
				{
					observer.updateNuevoButton(componente.getX(),
							componente.getY(), componente.getWidth(),
							componente.getHeight(), componente.getPosParent(),
							componente.getText());
				}
				if (componente.getClase().equals("RadioButton"))// agregar los
																// componentes
																// al cliente :D
				{
					observer.updateNuevoRadioButton(componente.getX(),
							componente.getY(), componente.getWidth(),
							componente.getHeight(), componente.getPosParent(),
							componente.getText());
				}
				if (componente.getClase().equals("CheckBox"))// agregar los
																// componentes
																// al cliente :D
				{
					observer.updateNuevoCheckBox(componente.getX(),
							componente.getY(), componente.getWidth(),
							componente.getHeight(), componente.getPosParent(),
							componente.getText());
				}
				if (componente.getClase().equals("TextField"))// agregar los
																// componentes
																// al cliente :D
				{
					observer.updateNuevoTextField(componente.getX(),
							componente.getY(), componente.getWidth(),
							componente.getHeight(), componente.getPosParent(),
							componente.getText());
				}
				if (componente.getClase().equals("PasswordField"))// agregar los
																	// componentes
																	// al
																	// cliente
																	// :D
				{
					observer.updateNuevoPasswordField(componente.getX(),
							componente.getY(), componente.getWidth(),
							componente.getHeight(), componente.getPosParent(),
							componente.getText());
				}
				if (componente.getClase().equals("TextArea"))// agregar los
																// componentes
																// al cliente :D
				{
					observer.updateNuevoTextArea(componente.getX(),
							componente.getY(), componente.getWidth(),
							componente.getHeight(), componente.getPosParent(),
							componente.getText());
				}
				if (componente.getClase().equals("List"))// agregar los
															// componentes al
															// cliente :D
				{
					observer.updateNuevoList(componente.getX(),
							componente.getY(), componente.getWidth(),
							componente.getHeight(), componente.getPosParent(),
							componente.getText());
				}
				if (componente.getClase().equals("ComboBox"))// agregar los
																// componentes
																// al cliente :D
				{
					observer.updateNuevoComboBox(componente.getX(),
							componente.getY(), componente.getWidth(),
							componente.getHeight(), componente.getPosParent(),
							componente.getText());
				}
				if (componente.getClase().equals("Slider"))// agregar los
															// componentes al
															// cliente :D
				{
					observer.updateNuevoSlider(componente.getX(),
							componente.getY(), componente.getWidth(),
							componente.getHeight(), componente.getPosParent(),
							componente.getText());
				}
				if (componente.getClase().equals("ProgresBar"))// agregar los
																// componentes
																// al cliente :D
				{
					observer.updateNuevoProgresBar(componente.getX(),
							componente.getY(), componente.getWidth(),
							componente.getHeight(), componente.getPosParent(),
							componente.getText());
				}
			}

			updateColor(i);
		}
	}

	public void desconectarUsuario(String nombre) throws RemoteException
	{
		for (int i = 0; i < usuarios.size(); i++)
		{
			if (usuarios.get(i).getName().equals(nombre))
			{
				usuarios.remove(i);
			}
		}
	}

	// metodos colaborativos...
	@Override
	public void agregarMensaje(String mensaje) throws RemoteException
	{
		proyecto.agregarMensaje(mensaje);
		this.notificarChat();
	}

	public void nuevoEntorno(String nombre) throws RemoteException
	{
		boolean posible = proyecto.nuevoEntorno(nombre);
		if (posible)
			notificarNuevoEntorno(nombre);
	}

	@Override
	public void agregarLabel(int x, int y, int w, int h, int r, int e,
			String texto) throws RemoteException
	{
		proyecto.agregarComponente(x, y, w, h, r, e, "Label", texto);
		notificarNuevoLabel(x, y, w, h, r, texto);
	}

	@Override
	public void agregarPanel(int x, int y, int w, int h, int r, int e)
			throws RemoteException
	{
		proyecto.agregarComponente(x, y, w, h, r, e, "Panel");
		notificarNuevoPanel(x, y, w, h, r);
	}

	@Override
	public void agregarButton(int x, int y, int w, int h, int r, int e,
			String t) throws RemoteException
	{
		proyecto.agregarComponente(x, y, w, h, r, e, "Button", t);
		notificarNuevoButton(x, y, w, h, r, t);
	}

	@Override
	public void agregarRadioButton(int x, int y, int w, int h, int r, int e,
			String t) throws RemoteException
	{
		proyecto.agregarComponente(x, y, w, h, r, e, "RadioButton", t);
		notificarNuevoRadioButton(x, y, w, h, r, t);
	}

	@Override
	public void agregarCheckBox(int x, int y, int w, int h, int r, int e,
			String t) throws RemoteException
	{
		proyecto.agregarComponente(x, y, w, h, r, e, "CheckBox", t);
		notificarNuevoCheckBox(x, y, w, h, r, t);
	}

	@Override
	public void agregarTextField(int x, int y, int w, int h, int r, int e,
			String t) throws RemoteException
	{
		proyecto.agregarComponente(x, y, w, h, r, e, "TextField", t);
		notificarNuevoTextField(x, y, w, h, r, t);
	}

	@Override
	public void agregarPasswordField(int x, int y, int w, int h, int r, int e,
			String t) throws RemoteException
	{
		proyecto.agregarComponente(x, y, w, h, r, e, "PasswordField", t);
		notificarNuevoPasswordField(x, y, w, h, r, t);
	}

	@Override
	public void agregarTextArea(int x, int y, int w, int h, int r, int e,
			String t) throws RemoteException
	{
		proyecto.agregarComponente(x, y, w, h, r, e, "TextArea", t);
		notificarNuevoTextArea(x, y, w, h, r, t);
	}

	@Override
	public void agregarList(int x, int y, int w, int h, int r, int e, String t)
			throws RemoteException
	{
		proyecto.agregarComponente(x, y, w, h, r, e, "List", t);
		notificarNuevoList(x, y, w, h, r, t);
	}

	@Override
	public void agregarComboBox(int x, int y, int w, int h, int r, int e,
			String t) throws RemoteException
	{
		proyecto.agregarComponente(x, y, w, h, r, e, "ComboBox", t);
		notificarNuevoComboBox(x, y, w, h, r, t);
	}

	@Override
	public void agregarSlider(int x, int y, int w, int h, int r, int e,
			String t) throws RemoteException
	{
		proyecto.agregarComponente(x, y, w, h, r, e, "Slider", t);
		notificarNuevoSlider(x, y, w, h, r, t);
	}

	@Override
	public void agregarProgresBar(int x, int y, int w, int h, int r, int e,
			String t) throws RemoteException
	{
		proyecto.agregarComponente(x, y, w, h, r, e, "ProgresBar", t);
		notificarNuevoProgresBar(x, y, w, h, r, t);
	}

	@Override
	public void bloquear(int posicionWidget, boolean estado, String usrName)
			throws RemoteException
	{
		for (IUsuario usuario : usuarios)
		{
			if (!usuario.getName().equals(usrName))
			{
				usuario.bloquear(posicionWidget, estado);
			}
		}
	}

	@Override
	public void moverWidget(int posicion, int x, int y, int w, int h)
	{
		ElementoInterface elemento = proyecto.getElementos().get(posicion);
		elemento.setX(x);
		elemento.setY(y);
		elemento.setWidth(w);
		elemento.setHeight(h);
		for (IUsuario usuario : usuarios)
		{
			try
			{
				usuario.moverWidget(posicion, x, y, w, h);
			} catch (RemoteException e)
			{
				e.printStackTrace();
			}
		}
	}

	@Override
	public void agregarValor(int posicion, int value) throws RemoteException
	{
		ElementoInterface elemento = proyecto.getElementos().get(posicion);
		elemento.setValue(value);
		for (IUsuario usuario : usuarios)
		{
			try
			{
				usuario.actualizarWidget(posicion, value);
			} catch (RemoteException e)
			{
				e.printStackTrace();
			}
		}
	}

	@Override
	public void agregarTexto(int posicion, String text) throws RemoteException
	{
		ElementoInterface elemento = proyecto.getElementos().get(posicion);
		elemento.setText(text);
		for (IUsuario usuario : usuarios)
		{
			try
			{
				usuario.actualizarWidget(posicion, text);
			} catch (RemoteException e)
			{
				e.printStackTrace();
			}
		}
	}

	@Override
	public void solicitarRemover(int posicion, String nombreUsuario)
	{
		try
		{
			bloquear(posicion, true, "");
			ComponenteServer elemento = (ComponenteServer)proyecto.getElementos().get(posicion);
			int result =1;
			for (IUsuario usuario : usuarios)
			{
				String usuarioActual = usuario.getName();
				if(!usuarioActual.equals(nombreUsuario))
				{
					result = result + usuario.votarEliminar(elemento.getClase());
				}
			}
			if(result>0)
			{
				for (IUsuario usuario : usuarios)
				{
					usuario.eliminarElemento( posicion);
				}
				proyecto.eliminarElemento(posicion);
			}
			else
			{
				bloquear(posicion, false, "");
			}
			
		} catch (Exception e)
		{
			
		}
	}

	private synchronized void notificarNuevoEntorno(String nombre)
			throws RemoteException
	{
		for (int i = 0; i < usuarios.size(); i++)
		{
			usuarios.get(i).updateNuevoEntorno(nombre);
		}
	}

	private synchronized void notificarNuevoLabel(int x, int y, int w, int h,
			int r, String texto) throws RemoteException
	{
		for (int i = 0; i < usuarios.size(); i++)
		{
			usuarios.get(i).updateNuevoLabel(x, y, w, h, r, texto);
		}
	}

	private synchronized void notificarChat() throws RemoteException
	{
		for (int i = 0; i < usuarios.size(); i++)
		{
			usuarios.get(i).updateChat(proyecto.obtenerMensajes());
		}
	}

	private synchronized void notificarNuevoPanel(int x, int y, int w, int h,
			int r) throws RemoteException
	{
		for (int i = 0; i < usuarios.size(); i++)
		{
			usuarios.get(i).updateNuevoPanel(x, y, w, h, r);
		}
	}

	private synchronized void notificarNuevoButton(int x, int y, int w, int h,
			int r, String t) throws RemoteException
	{
		for (int i = 0; i < usuarios.size(); i++)
		{
			usuarios.get(i).updateNuevoButton(x, y, w, h, r, t);
		}
	}

	private synchronized void notificarNuevoRadioButton(int x, int y, int w,
			int h, int r, String t) throws RemoteException
	{
		for (int i = 0; i < usuarios.size(); i++)
		{
			usuarios.get(i).updateNuevoRadioButton(x, y, w, h, r, t);
		}
	}

	private synchronized void notificarNuevoCheckBox(int x, int y, int w, int h,
			int r, String t) throws RemoteException
	{
		for (int i = 0; i < usuarios.size(); i++)
		{
			usuarios.get(i).updateNuevoCheckBox(x, y, w, h, r, t);
		}
	}

	private synchronized void notificarNuevoTextField(int x, int y, int w,
			int h, int r, String t) throws RemoteException
	{
		for (int i = 0; i < usuarios.size(); i++)
		{
			usuarios.get(i).updateNuevoTextField(x, y, w, h, r, t);
		}
	}

	private synchronized void notificarNuevoPasswordField(int x, int y, int w,
			int h, int r, String t) throws RemoteException
	{
		for (int i = 0; i < usuarios.size(); i++)
		{
			usuarios.get(i).updateNuevoPasswordField(x, y, w, h, r, t);
		}
	}

	private synchronized void notificarNuevoTextArea(int x, int y, int w, int h,
			int r, String t) throws RemoteException
	{
		for (int i = 0; i < usuarios.size(); i++)
		{
			usuarios.get(i).updateNuevoTextArea(x, y, w, h, r, t);
		}
	}

	private synchronized void notificarNuevoList(int x, int y, int w, int h,
			int r, String t) throws RemoteException
	{
		for (int i = 0; i < usuarios.size(); i++)
		{
			usuarios.get(i).updateNuevoList(x, y, w, h, r, t);
		}
	}

	private synchronized void notificarNuevoComboBox(int x, int y, int w, int h,
			int r, String t) throws RemoteException
	{
		for (int i = 0; i < usuarios.size(); i++)
		{
			usuarios.get(i).updateNuevoComboBox(x, y, w, h, r, t);
		}
	}

	private synchronized void notificarNuevoSlider(int x, int y, int w, int h,
			int r, String t) throws RemoteException
	{
		for (int i = 0; i < usuarios.size(); i++)
		{
			usuarios.get(i).updateNuevoSlider(x, y, w, h, r, t);
		}
	}

	private synchronized void notificarNuevoProgresBar(int x, int y, int w,
			int h, int r, String t) throws RemoteException
	{
		for (int i = 0; i < usuarios.size(); i++)
		{
			usuarios.get(i).updateNuevoProgresBar(x, y, w, h, r, t);
		}
	}

	private boolean existeUsuario(String nombre) throws RemoteException
	{
		boolean res = false;
		for (IUsuario usuario : usuarios)
		{
			if (usuario.getName().equals(nombre))
			{
				res = true;
			}
		}
		return res;
	}

	@Override
	public void solicitarCambioDeColor(int posicion, int r, int g, int b, String tipo)
			throws RemoteException
	{
		proyecto.cambiarDeColor(posicion, r, g, b,tipo);
		for (IUsuario usuario : usuarios)
		{
			try
			{
				ElementoInterface elemento = proyecto.getElementos().get(posicion);
				ArrayList<Integer> colors = elemento.getColors();
				usuario.cambiarColor(posicion, colors.get(0), colors.get(1),
						colors.get(2),"fondo");
				usuario.cambiarColor(posicion, colors.get(3), colors.get(4),
						colors.get(5),"texto");
			} catch (RemoteException e)
			{
				e.printStackTrace();
			}
		}
	}

	private void updateColor(int pos)
	{
		for (IUsuario usuario : usuarios)
		{
			try
			{
				ElementoInterface elemento = proyecto.getElementos().get(pos);
				ArrayList<Integer> colors = elemento.getColors();
				usuario.cambiarColor(pos, colors.get(0), colors.get(1),
						colors.get(2),"fondo");
				usuario.cambiarColor(pos, colors.get(3), colors.get(4),
						colors.get(5),"texto");
			} catch (RemoteException e)
			{
				e.printStackTrace();
			}
		}
	}

}
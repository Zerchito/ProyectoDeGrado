package Conexion;

import InterfazServidor.IUsuario;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JOptionPane;

/**
 * Informacion necesaria del usuario en el Chat
 * 
 * @author zerch
 */
@SuppressWarnings("serial")
public class Usuario extends UnicastRemoteObject implements IUsuario
{
	private String nombre;
	private boolean estado;
	private int puerto;
	private String ip;
	private Link conection;

	public Usuario(String nombre, int puerto, Link conector)
			throws RemoteException
	{
		this.nombre = nombre;
		this.puerto = puerto;
		ip = obtenerIp();
		this.conection = conector;
		estado = true;
	}

	public void addLink( Link newLink){
		this.conection = newLink;
	}
	
	private String obtenerIp() throws RemoteException
	{
		String respuesta;
		try
		{
			InetAddress host = InetAddress.getLocalHost();
			respuesta = host.getHostAddress();
		} catch (Exception e)
		{
			throw new RemoteException("No se puede obtener la direccion IP.");
		}
		return respuesta;
	}

	@Override
	public String getIp()
	{
		return ip;
	}
	
	@Override
	public int getPort()
	{
		return puerto;
	}
	
	@Override
	public String getName()
	{
		return nombre;
	}

	@Override
	public boolean getEstado() throws RemoteException
	{
		return estado;
	}

	@Override
	public void updateChat(String mensajes) throws RemoteException
	{
		conection.update(mensajes);
	}

	@Override
	public void updateNuevoEntorno(String nombre) throws RemoteException
	{
		conection.updateNuevoEntorno(nombre);
	}

	@Override
	public void updateNuevoLabel(int x, int y, int w, int h, int r,
			String texto) throws RemoteException
	{
		conection.updateNuevoLabel(x, y, w, h, r, texto);
	}

	@Override
	public void updateNuevoPanel(int x, int y, int w, int h, int r)
			throws RemoteException
	{
		conection.updateNuevoPanel(x, y, w, h, r);
	}

	@Override
	public void updateNuevoButton(int x, int y, int w, int h, int r, String t)
			throws RemoteException
	{
		conection.updateNuevoButton(x, y, w, h, r, t);
	}

	@Override
	public void updateNuevoRadioButton(int x, int y, int w, int h, int r,
			String t) throws RemoteException
	{
		conection.updateNuevoRadioButton(x, y, w, h, r, t);
	}

	@Override
	public void updateNuevoCheckBox(int x, int y, int w, int h, int r, String t)
			throws RemoteException
	{
		conection.updateNuevoCheckBox(x, y, w, h, r, t);
	}

	@Override
	public void updateNuevoTextField(int x, int y, int w, int h, int r,
			String t) throws RemoteException
	{
		conection.updateNuevoTextField(x, y, w, h, r, t);
	}

	@Override
	public void updateNuevoPasswordField(int x, int y, int w, int h, int r,
			String t) throws RemoteException
	{
		conection.updateNuevoPasswordField(x, y, w, h, r, t);
	}

	@Override
	public void updateNuevoTextArea(int x, int y, int w, int h, int r, String t)
			throws RemoteException
	{
		conection.updateNuevoTextArea(x, y, w, h, r, t);
	}

	@Override
	public void updateNuevoList(int x, int y, int w, int h, int r, String t)
			throws RemoteException
	{
		conection.updateNuevoList(x, y, w, h, r, t);
	}

	@Override
	public void updateNuevoComboBox(int x, int y, int w, int h, int r, String t)
			throws RemoteException
	{
		conection.updateNuevoComboBox(x, y, w, h, r, t);
	}

	@Override
	public void updateNuevoSlider(int x, int y, int w, int h, int r, String t)
			throws RemoteException
	{
		conection.updateNuevoSlider(x, y, w, h, r, t);
	}

	@Override
	public void updateNuevoProgresBar(int x, int y, int w, int h, int r,
			String t) throws RemoteException
	{
		conection.updateNuevoProgresBar(x, y, w, h, r, t);
	}

	@Override
	public void bloquear(int posicionWidget, boolean estado)
	{
		conection.bloquearWidget(posicionWidget, estado);
	}

	@Override
	public void moverWidget(int posicionWidget, int x, int y, int w, int h)
	{
		conection.moverWidget(posicionWidget, x, y, w, h);
	}

	@Override
	public void actualizarWidget(int posicion, int value)
	{
		conection.actualizarWidget(posicion, value);
	}

	@Override
	public void actualizarWidget(int posicion, String text)
			throws RemoteException
	{
		conection.actualizarWidget(posicion, text);
	}

	@Override
	public void cambiarColor(int posicion, int r, int g, int b, String tipo)
			throws RemoteException
	{
		conection.cambiarColor(posicion, r, g, b, tipo);
	}

	@Override
	public int votarEliminar(String elemento)
	{
		int respuesta = 1;
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog(null, "Desea eliminar el elemento :"+elemento, "Eliminar", dialogButton);
		
		if(dialogResult != 0) {
			respuesta = -1;
		} 
		return respuesta;
	}

	@Override
	public void eliminarElemento(int posicion) throws RemoteException
	{
		conection.eliminarElemento(posicion);
	}
}
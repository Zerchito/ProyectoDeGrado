package InterfazServidor;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author zerch
 */
public interface IUsuario extends Remote
{
	public String getName() throws RemoteException;

	public int getPort() throws RemoteException;

	public String getIp() throws RemoteException;

	public boolean getEstado() throws RemoteException;

	public void updateChat(String mensajes) throws RemoteException;

	public void updateNuevoEntorno(String nombre) throws RemoteException;

	public void updateNuevoLabel(int x, int y, int w, int h, int r,
			String texto) throws RemoteException;

	public void updateNuevoPanel(int x, int y, int w, int h, int r)
			throws RemoteException;

	public void updateNuevoButton(int x, int y, int w, int h, int r, String t)
			throws RemoteException;

	public void updateNuevoRadioButton(int x, int y, int w, int h, int r,
			String t) throws RemoteException;

	public void updateNuevoCheckBox(int x, int y, int w, int h, int r, String t)
			throws RemoteException;

	public void updateNuevoTextField(int x, int y, int w, int h, int r,
			String t) throws RemoteException;

	public void updateNuevoPasswordField(int x, int y, int w, int h, int r,
			String t) throws RemoteException;

	public void updateNuevoTextArea(int x, int y, int w, int h, int r, String t)
			throws RemoteException;

	public void updateNuevoList(int x, int y, int w, int h, int r, String t)
			throws RemoteException;

	public void updateNuevoComboBox(int x, int y, int w, int h, int r, String t)
			throws RemoteException;

	public void updateNuevoSlider(int x, int y, int w, int h, int r, String t)
			throws RemoteException;

	public void updateNuevoProgresBar(int x, int y, int w, int h, int r,
			String t) throws RemoteException;

	public void bloquear(int posicionWidget, boolean estado)
			throws RemoteException;

	public void moverWidget(int posicionWidget, int x, int y, int w, int h)
			throws RemoteException;

	public void actualizarWidget(int posicion, String text)
			throws RemoteException;

	public void actualizarWidget(int posicion, int value)
			throws RemoteException;

	public void cambiarColor(int posicion, int r, int g, int b, String tipo)
			throws RemoteException;
	
	public int votarEliminar(String elemento)
			throws RemoteException;
	
	public void eliminarElemento(int posicion)
			throws RemoteException;
}

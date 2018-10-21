package InterfazServidor;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author zerch
 */
public interface IServidor extends Remote
{
	public boolean registrarUsuario(String name, String nombreHost, int port)
			throws RemoteException;

	public void getUpdates(IUsuario observer) throws RemoteException;

	public void agregarMensaje(String mensaje) throws RemoteException;

	public void nuevoEntorno(String nombre) throws RemoteException;

	public void desconectarUsuario(String nombre) throws RemoteException;

	public void agregarLabel(int x, int y, int w, int h, int r, int e,
			String texto) throws RemoteException;

	public void agregarPanel(int x, int y, int w, int h, int r, int e)
			throws RemoteException;

	public void agregarButton(int x, int y, int w, int h, int r, int p,
			String t) throws RemoteException;

	public void agregarRadioButton(int x, int y, int w, int h, int r, int p,
			String t) throws RemoteException;

	public void agregarCheckBox(int x, int y, int w, int h, int r, int p,
			String t) throws RemoteException;

	public void agregarTextField(int x, int y, int w, int h, int r, int p,
			String t) throws RemoteException;

	public void agregarPasswordField(int x, int y, int w, int h, int r, int p,
			String t) throws RemoteException;

	public void agregarTextArea(int x, int y, int w, int h, int r, int p,
			String t) throws RemoteException;

	public void agregarList(int x, int y, int w, int h, int r, int p, String t)
			throws RemoteException;

	public void agregarComboBox(int x, int y, int w, int h, int r, int p,
			String t) throws RemoteException;

	public void agregarSlider(int x, int y, int w, int h, int r, int p,
			String t) throws RemoteException;

	public void agregarProgresBar(int x, int y, int w, int h, int r, int p,
			String t) throws RemoteException;

	public void bloquear(int posicionWidget, boolean estado, String usrName)
			throws RemoteException;

	public void moverWidget(int posicionWidget, int x, int y, int w, int h)
			throws RemoteException;

	public void agregarValor(int posicion, int value) throws RemoteException;

	public void agregarTexto(int posicion, String text) throws RemoteException;

	public void solicitarRemover(int posicion,String nombreUsuario) throws RemoteException;

	public void solicitarCambioDeColor(int posicion, int r, int g, int b, String tipo)
			throws RemoteException;
	
}
package Conexion;

import java.rmi.RemoteException;

public class Link
{
	private AdministradorEventos administrador;
	
	public Link(AdministradorEventos admin)
	{
		administrador = admin;
	}

	public void update(String mensajes)
	{
		administrador.update(mensajes);
	}

	public void updateNuevoEntorno(String nombre) throws RemoteException
	{
		administrador.updateNuevoEntorno(nombre);
	}

	public void updateNuevoLabel(int x, int y, int w, int h, int r,
			String texto)
	{
		administrador.nuevoLabel(x, y, w, h, r, texto);
	}

	public void updateNuevoPanel(int x, int y, int w, int h, int r)
	{
		administrador.nuevoPanel(x, y, w, h, r);
	}

	public void updateNuevoButton(int x, int y, int w, int h, int r, String t)
	{
		administrador.nuevoButton(x, y, w, h, r, t);
	}

	public void updateNuevoRadioButton(int x, int y, int w, int h, int r,
			String t)
	{
		administrador.nuevoRadioButton(x, y, w, h, r, t);
	}

	public void updateNuevoCheckBox(int x, int y, int w, int h, int r, String t)
	{
		administrador.nuevoCheckBox(x, y, w, h, r, t);
	}

	public void updateNuevoTextField(int x, int y, int w, int h, int r,
			String t)
	{
		administrador.nuevoTextField(x, y, w, h, r, t);
	}

	public void updateNuevoPasswordField(int x, int y, int w, int h, int r,
			String t)
	{
		administrador.nuevoPasswordField(x, y, w, h, r, t);
	}

	public void updateNuevoTextArea(int x, int y, int w, int h, int r, String t)
	{
		administrador.nuevoTextArea(x, y, w, h, r, t);
	}

	public void updateNuevoList(int x, int y, int w, int h, int r, String t)
	{
		administrador.nuevoList(x, y, w, h, r, t);
	}

	public void updateNuevoComboBox(int x, int y, int w, int h, int r, String t)
	{
		administrador.nuevoComboBox(x, y, w, h, r, t);
	}

	public void updateNuevoSlider(int x, int y, int w, int h, int r, String t)
	{
		administrador.nuevoSlider(x, y, w, h, r, t);
	}

	public void updateNuevoProgresBar(int x, int y, int w, int h, int r,
			String t)
	{
		administrador.nuevoProgresBar(x, y, w, h, r, t);
	}

	public void bloquearWidget(int posicionWidget, boolean estado)
	{
		administrador.bloquearWidget(posicionWidget, estado);
	}

	public void moverWidget(int posicionWidget, int x, int y, int w, int h)
	{
		administrador.moveWidget(posicionWidget, x, y, w, h);
	}

	public void actualizarWidget(int posicion, int value)
	{
		administrador.actualizarWidget(posicion, value);
	}

	public void actualizarWidget(int posicion, String text)
	{
		administrador.actualizarWidget(posicion, text);
	}

	public void cambiarColor(int posicion, int r, int g, int b, String tipo)
	{
		administrador.cambiarColor(posicion, r, g, b, tipo);
	}
	public void eliminarElemento(int posicion)
	{
		administrador.eliminarElemento(posicion);
	}
}

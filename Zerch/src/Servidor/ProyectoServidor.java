package Servidor;

import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import ElementosServidor.ComponenteServer;
import ElementosServidor.ElementoInterface;
import ElementosServidor.EntornoServer;

public class ProyectoServidor
{
	private String nombreProyecto;
	private ChatServidor chat;
	private ArrayList<EntornoServer> entornos;
	private ArrayList<ElementoInterface> elementos;

	public ProyectoServidor()
	{
		nombreProyecto = JOptionPane.showInputDialog("Nombre del Proyecto:");
		chat = new ChatServidor(nombreProyecto);
		entornos = new ArrayList<EntornoServer>();
		elementos = new ArrayList<ElementoInterface>();
	}

	public void agregarMensaje(String mensaje) throws RemoteException
	{
		chat.agregarMensaje(mensaje);
	}

	public String obtenerMensajes()
	{
		return chat.obtenerMensajes();
	}

	public String getNombreProyecto()
	{
		return nombreProyecto;
	}

	public boolean nuevoEntorno(String nombreEntorno)
	{
		boolean res = false;
		EntornoServer nuevoEntorno = new EntornoServer(nombreEntorno);
		if (!entornos.contains(nuevoEntorno))
		{
			entornos.add(nuevoEntorno);
			elementos.add(nuevoEntorno);
			res = true;
		}
		return res;
	}

	public ArrayList<EntornoServer> getEntornos()
	{
		return entornos;
	}

	public ArrayList<ElementoInterface> getElementos()
	{
		return elementos;
	}

	public void agregarComponente(int x, int y, int w, int h, int r, int e,
			String type)
	{
		ComponenteServer nuevo = new ComponenteServer(x, y, w, h, r, type);
		nuevo.setColorBackground(255, 255, 255);
		nuevo.setColorText(51,51,51);
		entornos.get(e).agregarComponente(nuevo);
		elementos.add(nuevo);
	}

	public void agregarComponente(int x, int y, int w, int h, int r, int e,
			String type, String text)
	{
		ComponenteServer nuevo = new ComponenteServer(x, y, w, h, r, type);
		nuevo.setText(text);
		entornos.get(e).agregarComponente(nuevo);
		elementos.add(nuevo);
	}

	public void cambiarDeColor(int posicion, int r, int g, int b, String tipo)
	{
		ElementoInterface actual = elementos.get(posicion);
		if(tipo.equals("fondo"))
		{
			actual.setColorBackground(r, g, b);
		}
		else
		{
			actual.setColorText(r, g, b);
		}
	}
	
	public void eliminarElemento(int posicion)
	{
		elementos.remove(posicion);
	}

}
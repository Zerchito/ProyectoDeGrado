package Servidor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author zerch
 */
public class ChatServidor
{
	private File proyecto;
	private StringBuilder mensajes;

	public ChatServidor(String proyecto)
	{
		mensajes = new StringBuilder();
		try
		{
			crearLog(proyecto);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void crearLog(String nombre) throws IOException
	{
		proyecto = new File("Proyectos/" + nombre + "_Chat");
		System.out.println(proyecto.getAbsolutePath());
		if (!proyecto.exists())
		{
			proyecto.createNewFile();
		}
		FileReader fr = new FileReader(proyecto);
		BufferedReader br = new BufferedReader(fr);
		// lectura del archivo
		String linea = "";
		while ((linea = br.readLine()) != null)
		{
			mensajes.append(linea);
			mensajes.append("\n");
		}
		fr.close();
	}

	public void agregarMensaje(String mensaje)
	{
		mensajes.append(mensaje);
		mensajes.append("\n");
		PrintWriter pw = null;
		try
		{
			pw = new PrintWriter(proyecto);
			pw.print(mensajes.toString());
			pw.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public String obtenerMensajes()
	{
		return mensajes.toString();
	}
}
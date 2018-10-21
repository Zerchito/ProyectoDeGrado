package Servidor;

import java.net.InetAddress;
import java.rmi.RemoteException;

/**
 *
 * @author zerch
 */
public class DatosConexion
{
	private int port;
	private String ip;

	/**
	 * constructor de la Clase;
	 */
	public DatosConexion() throws RemoteException
	{
		port = 1234;
		try
		{
			InetAddress host = InetAddress.getLocalHost();
			ip = host.getHostAddress();
		} catch (Exception e)
		{
			throw new RemoteException("No se puede obtener la direccion IP.");
		}
	}

	/**
	 * 
	 * @return puerto del servidor
	 */
	public int getPort()
	{
		return port;
	}

	/**
	 * 
	 * @return ip del servidor
	 */
	public String getIp()
	{
		return ip;
	}
}
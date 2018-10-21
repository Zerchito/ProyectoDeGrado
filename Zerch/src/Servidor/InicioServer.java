package Servidor;

import java.rmi.RemoteException;

/**
 *
 * @author zerch
 */
public class InicioServer
{
	public static void main(String[] args) throws RemoteException
	{
		Servidor server = new Servidor();
		server.iniciarServidor();
	}
}
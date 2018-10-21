import java.rmi.RemoteException;
import Conexion.Conector;

public class Inicio
{
	public static void main(String[] args) throws RemoteException
	{
		Conector conector = new Conector();
		conector.conectar();
	}
}
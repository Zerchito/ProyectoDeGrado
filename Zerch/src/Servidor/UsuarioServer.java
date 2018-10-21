package Servidor;

public class UsuarioServer
{
	private String nick;
	private boolean estado;

	public UsuarioServer(String nickName)
	{
		nick = nickName;
		estado = true;
	}

	public void cambiarEstado()
	{
		estado = !estado;
	}

	public String getNick()
	{
		return nick;
	}

	public boolean getStatus()
	{
		return estado;
	}
}

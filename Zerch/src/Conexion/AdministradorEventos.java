package Conexion;

import Interfaz.VentanaPrincipal;
import InterfazServidor.IServidor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author zerch
 */
public class AdministradorEventos implements ActionListener, KeyListener
{
	private Usuario usuario;
	private IServidor servidor;
	private VentanaPrincipal ventanaPrincipal;

	public AdministradorEventos(Usuario usuario, IServidor servidor)
	{
		this.usuario = usuario;
		this.servidor = servidor;
		ventanaPrincipal = new VentanaPrincipal(usuario.getName());
		ventanaPrincipal.agregarManejadorDeEventos(this);
	}

	public int desconectarUsuario() throws RemoteException
	{
		servidor.desconectarUsuario(usuario.getName());
		return 3;
	}

	private void enviarMensaje() throws RemoteException
	{
		String mensaje = ventanaPrincipal.getMensaje();
		mensaje = usuario.getName() + ": " + mensaje;
		ventanaPrincipal.limpiarCampoTexto();
		servidor.agregarMensaje(mensaje);
	}

	public void update(String mensajes)
	{
		ventanaPrincipal.updateAreaTexto(mensajes);
	}

	public void updateNuevoEntorno(String nombre)
	{
		ventanaPrincipal.updateNuevoEntorno(nombre);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource().getClass() == JButton.class)
		{
			JButton buton = (JButton) e.getSource();
			eventsButtons(buton);
		} else
		{
			if (e.getSource().getClass() == JMenuItem.class)
			{
				try
				{
					String nombre_ranura;
					do
					{
						nombre_ranura = JOptionPane.showInputDialog(
								"Ingrese el nombre de la ventana:");
					} while (nombre_ranura == null || nombre_ranura.equals(""));
					servidor.nuevoEntorno(nombre_ranura);
				} catch (RemoteException e1)
				{
					e1.printStackTrace();
				}
			}

		}
	}

	private void eventsButtons(JButton buton)
	{
		String name = buton.getName();
		if (name.equals("enviar"))
		{
			try
			{
				enviarMensaje();
			} catch (RemoteException exception)
			{
				System.out.println("Error en la comunicaccion con el servidor");
			}
		} else
		{
			if (name.equals("participantes"))
			{

			}
		}

	}

	@Override
	public void keyTyped(KeyEvent e)
	{

	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			try
			{
				enviarMensaje();
			} catch (RemoteException exception)
			{
				System.out.println("Error en la comunicaccion con el servidor");
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{

	}

	// <para el servidor (componentes)>
	public void notificarLabel(int x, int y, int w, int h, int r, int p,
			String texto)
	{
		try
		{
			servidor.agregarLabel(x, y, w, h, r, p, texto);
		} catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}

	public void notificarPanel(int x, int y, int w, int h, int r, int p)
	{
		try
		{
			servidor.agregarPanel(x, y, w, h, r, p);
		} catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}

	public void notificarButton(int x, int y, int w, int h, int r, int p,
			String t)
	{
		try
		{
			servidor.agregarButton(x, y, w, h, r, p, t);
		} catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}

	public void notificarRadioButton(int x, int y, int w, int h, int r, int p,
			String t)
	{
		try
		{
			servidor.agregarRadioButton(x, y, w, h, r, p, t);
		} catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}

	public void notificarCheckBox(int x, int y, int w, int h, int r, int p,
			String t)
	{
		try
		{
			servidor.agregarCheckBox(x, y, w, h, r, p, t);
		} catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}

	public void notificarTextField(int x, int y, int w, int h, int r, int p,
			String t)
	{
		try
		{
			servidor.agregarTextField(x, y, w, h, r, p, t);
		} catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}

	public void notificarPasswordField(int x, int y, int w, int h, int r, int p,
			String t)
	{
		try
		{
			servidor.agregarPasswordField(x, y, w, h, r, p, t);
		} catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}

	public void notificarTextArea(int x, int y, int w, int h, int r, int p,
			String t)
	{
		try
		{
			servidor.agregarTextArea(x, y, w, h, r, p, t);
		} catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}

	public void notificarList(int x, int y, int w, int h, int r, int p,
			String t)
	{
		try
		{
			servidor.agregarList(x, y, w, h, r, p, t);
		} catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}

	public void notificarComboBox(int x, int y, int w, int h, int r, int p,
			String t)
	{
		try
		{
			servidor.agregarComboBox(x, y, w, h, r, p, t);
		} catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}

	public void notificarSlider(int x, int y, int w, int h, int r, int p,
			String t)
	{
		try
		{
			servidor.agregarSlider(x, y, w, h, r, p, t);
		} catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}

	public void notificarProgresBar(int x, int y, int w, int h, int r, int p,
			String t)
	{
		try
		{
			servidor.agregarProgresBar(x, y, w, h, r, p, t);
		} catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}

	public void nuevoLabel(int x, int y, int w, int h, int r, String texto)
	{
		ventanaPrincipal.nuevoLabel(x, y, w, h, r, texto);
	}

	public void nuevoPanel(int x, int y, int w, int h, int r)
	{
		ventanaPrincipal.nuevoPanel(x, y, w, h, r);
	}

	public void nuevoButton(int x, int y, int w, int h, int r, String t)
	{
		ventanaPrincipal.nuevoButton(x, y, w, h, r, t);
	}

	public void nuevoRadioButton(int x, int y, int w, int h, int r, String t)
	{
		ventanaPrincipal.nuevoRadioButton(x, y, w, h, r, t);
	}

	public void nuevoCheckBox(int x, int y, int w, int h, int r, String t)
	{
		ventanaPrincipal.nuevoCheckBox(x, y, w, h, r, t);
	}

	public void nuevoTextField(int x, int y, int w, int h, int r, String t)
	{
		ventanaPrincipal.nuevoTextField(x, y, w, h, r, t);
	}

	public void nuevoPasswordField(int x, int y, int w, int h, int r, String t)
	{
		ventanaPrincipal.nuevoPasswordField(x, y, w, h, r, t);
	}

	public void nuevoTextArea(int x, int y, int w, int h, int r, String t)
	{
		ventanaPrincipal.nuevoTextArea(x, y, w, h, r, t);
	}

	public void nuevoList(int x, int y, int w, int h, int r, String t)
	{
		ventanaPrincipal.nuevoList(x, y, w, h, r, t);
	}

	public void nuevoComboBox(int x, int y, int w, int h, int r, String t)
	{
		ventanaPrincipal.nuevoComboBox(x, y, w, h, r, t);
	}

	public void nuevoSlider(int x, int y, int w, int h, int r, String t)
	{
		ventanaPrincipal.nuevoSlider(x, y, w, h, r, t);
	}

	public void nuevoProgresBar(int x, int y, int w, int h, int r, String t)
	{
		ventanaPrincipal.nuevoProgresBar(x, y, w, h, r, t);
	}

	public void bloquear(int posicionWidget, boolean estado)
	{
		try
		{
			servidor.bloquear(posicionWidget, estado, usuario.getName());
		} catch (RemoteException e)
		{
			System.out.println("Error en la comunicacion con el servidor," + e);
		}
	}

	public void bloquearWidget(int posicionWidget, boolean estado)
	{
		ventanaPrincipal.bloquearWidget(posicionWidget, estado);
	}

	public void moverWidget(int posicionWidget, int x, int y, int w, int h)
	{
		try
		{
			servidor.moverWidget(posicionWidget, x, y, w, h);
		} catch (RemoteException e)
		{
			System.out.println("Error en la comunicacion con el servidor," + e);
		}
	}

	public void moveWidget(int posicionWidget, int x, int y, int w, int h)
	{
		ventanaPrincipal.moverWidget(posicionWidget, x, y, w, h);
	}

	public void sendValueWidget(int posicionWidget, int value)
	{
		try
		{
			servidor.agregarValor(posicionWidget, value);
		} catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}

	public void actualizarWidget(int posicion, int value)
	{
		ventanaPrincipal.actualizarWidget(posicion, value);
	}

	public void sendValueWidget(int posicion, String text)
	{
		try
		{
			servidor.agregarTexto(posicion, text);
		} catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}

	public void actualizarWidget(int posicion, String text)
	{
		ventanaPrincipal.actualizarWidget(posicion, text);
	}

	public void solicitarRemover(int indice)
	{
		try
		{
			servidor.solicitarRemover(indice,usuario.getName());
		} catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}

	public void solicitarCambioDeColor(int posicion, int r, int g, int b, String tipo)
	{
		try
		{
			servidor.solicitarCambioDeColor(posicion, r, g, b, tipo);
		} catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}

	public void cambiarColor(int posicion, int r, int g, int b, String tipo)
	{
		ventanaPrincipal.cambiarColor(posicion, r, g, b, tipo);
	}
	
	public void eliminarElemento(int posicion)
	{
		ventanaPrincipal.eliminarElemento(posicion);
	}
}
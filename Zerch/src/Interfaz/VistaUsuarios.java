package Interfaz;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import Conexion.Usuario;
import InterfazServidor.IUsuario;

@SuppressWarnings("serial")
public class VistaUsuarios extends JScrollPane
{
	public static String CONECTADOS = " usuarios conectados";
	public static int ATRIBUTOS_A_MOSTRAR = 2;

	private List<IUsuario> usuarios;
	private JPanel panel;

	public VistaUsuarios(List<IUsuario> usuarios)
	{
		super(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.usuarios = usuarios;
		panel = new JPanel();
		actualizar();
		add(panel);
	}

	private void actualizar()
	{
		TitledBorder title;
		title = BorderFactory.createTitledBorder(usuarios.size() + CONECTADOS);
		panel.setBorder(title);
		GridLayout layout = new GridLayout(usuarios.size(), ATRIBUTOS_A_MOSTRAR,
				15, 15);
		panel.setLayout(layout);
		for (IUsuario actual : usuarios)
		{
			try
			{
				panel.add(new Label(actual.getName()));
				JCheckBox estado = new JCheckBox();
				estado.setEnabled(false);
				if (actual.getEstado())
				{
					estado.setSelected(true);
				} else
				{
					estado.setSelected(false);
				}
				panel.add(estado);
			} catch (HeadlessException | RemoteException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void actualizarUsuarios(List<IUsuario> usuarios)
	{
		this.usuarios = usuarios;
		actualizar();
	}

	public static void main(String[] args) throws RemoteException
	{
		JFrame f = new JFrame();
		List<IUsuario> us = new ArrayList<>();
		IUsuario u = new Usuario("ro", 123, null);
		IUsuario u1 = new Usuario("so", 123, null);
		IUsuario u2 = new Usuario("rr", 123, null);
		IUsuario u3 = new Usuario("asdf", 123, null);
		us.add(u);
		us.add(u1);
		us.add(u2);
		us.add(u3);
		VistaUsuarios v = new VistaUsuarios(us);
		f.add(v);
		f.setSize(100, 400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}

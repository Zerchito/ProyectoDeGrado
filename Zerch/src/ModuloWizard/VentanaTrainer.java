package ModuloWizard;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.*;
import javax.swing.event.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.*;

import ModuloBase.*;

public class VentanaTrainer extends JFrame
		implements ActionListener, java.io.Serializable
{

	// componentes de interfaz grafica
	JPanel panel_oeste;
	JPanel panel_centro;
	JPanel panel_sud;

	JLabel label_saludo;
	JTextArea textarea_informacion;
	StringBuffer sinformacion;

	JRadioButton radiobutton_reproducir;
	JRadioButton radiobutton_editar;

	JButton boton_siguiente;
	JButton boton_cancelar;
	JButton boton_actualizar;
	JButton boton_cargarcamino;

	// componente personalizado = panel del arbol
	ArbolTrainer arbol_trainer;

	// variables para las funciones
	int valor_reproducir = 0;
	int valor_editar = 0;
	String s = "";
	int porcentaje_progreso = 0;
	ImageIcon imagen_trainer;
	JLabel label_trainer;
	StringBuffer sb;
	Vector vseparador = new Vector();

	// constructor
	public VentanaTrainer()
	{
		setTitle("Trainer GUI");
		setLayout(new BorderLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int) screenSize.getWidth() * 2 / 6,
				(int) screenSize.getHeight() * 1 / 5);
		setSize(700, 400);
		setVisible(true);
		setMinimumSize(new Dimension(600, 350));

		panel_oeste = new JPanel();
		panel_oeste.setLayout(null);
		panel_oeste.setPreferredSize(new Dimension(200, 500));
		panel_oeste.setBorder(new EtchedBorder());
		add("West", panel_oeste);

		panel_centro = new JPanel();
		panel_centro.setLayout(new BorderLayout());
		panel_centro.setBorder(new EtchedBorder());
		panel_centro.setPreferredSize(new Dimension(350, 350));
		add("Center", panel_centro);

		imagen_trainer = new ImageIcon("Soporte/Imagenes/trainer.png");

		label_trainer = new JLabel(imagen_trainer);
		label_trainer.setBounds(5, 5, 105, 105);
		label_trainer.setBorder(new EtchedBorder());
		panel_oeste.add(label_trainer);

		JPanel panel_inicio = new JPanel();
		panel_inicio.setLayout(null);
		panel_inicio.setLocation(5, 5);
		panel_inicio.setSize(300, 300);
		panel_centro.add(panel_inicio);

		label_saludo = new JLabel("Bienvenido al Trainer Gui");
		label_saludo.setBounds(10, 10, 250, 30);
		label_saludo.setFont(new Font("Dialog", Font.BOLD, 17));
		panel_inicio.add(label_saludo);

		textarea_informacion = new JTextArea();
		textarea_informacion.setBounds(10, 50, 300, 40);
		textarea_informacion.setFont(new Font("Dialog", Font.PLAIN, 11));
		textarea_informacion.setEditable(false);
		sinformacion = new StringBuffer();
		sinformacion.append(
				"Porfavor seleccione un elemento especifico, luego marque \n");
		sinformacion.append("la opcion requerida... \n");
		textarea_informacion.setText(sinformacion.toString());
		textarea_informacion.setBackground(new Color(238, 238, 238));
		panel_inicio.add(textarea_informacion);

		arbol_trainer = new ArbolTrainer(this);
		arbol_trainer.setLocation(10, 90);
		panel_inicio.add(arbol_trainer);

		radiobutton_reproducir = new JRadioButton("Reproducir Selecto");
		radiobutton_reproducir.setBounds(10, 260, 250, 20);
		radiobutton_reproducir.setFont(new Font("Dialog", Font.PLAIN, 11));
		radiobutton_reproducir.setEnabled(false);
		panel_inicio.add(radiobutton_reproducir);

		radiobutton_editar = new JRadioButton("Editar Selecto");
		radiobutton_editar.setBounds(10, 280, 250, 30);
		radiobutton_editar.setFont(new Font("Dialog", Font.PLAIN, 11));
		radiobutton_editar.setEnabled(false);
		panel_inicio.add(radiobutton_editar);

		panel_sud = new JPanel();
		panel_sud.setPreferredSize(new Dimension(400, 40));
		panel_sud.setLayout(new BorderLayout());
		panel_sud.setBorder(new BevelBorder(BevelBorder.RAISED));
		add("South", panel_sud);

		JPanel panel_opciones = new JPanel();
		panel_opciones.setBounds(5, 5, 360, 30);
		panel_opciones.setPreferredSize(new Dimension(360, 30));
		panel_opciones.setBorder(new EtchedBorder());
		panel_opciones.setLayout(null);
		panel_sud.add("East", panel_opciones);

		boton_siguiente = new JButton("Siguiente >");
		boton_siguiente.setBounds(110, 5, 100, 20);
		panel_opciones.add(boton_siguiente);
		boton_siguiente.setEnabled(false);

		boton_actualizar = new JButton("Actualizar");
		boton_actualizar.setBounds(5, 5, 100, 20);
		panel_opciones.add(boton_actualizar);

		boton_cancelar = new JButton("Cancelar X");
		boton_cancelar.setBounds(215, 5, 100, 20);
		panel_opciones.add(boton_cancelar);

		radiobutton_reproducir.addActionListener(this);
		radiobutton_editar.addActionListener(this);

		boton_siguiente.addActionListener(this);
		boton_cancelar.addActionListener(this);
		boton_actualizar.addActionListener(this);

		setSize(this.getWidth() + 1, this.getHeight());
	}

	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == radiobutton_reproducir)
		{
			if (radiobutton_reproducir.isSelected())
			{
				valor_reproducir = 1;
			} else if (!radiobutton_reproducir.isSelected())
			{
				valor_reproducir = 0;
			}
			radiobutton_editar.setSelected(false);
			valor_editar = 0;
		}

		if (ae.getSource() == radiobutton_editar)
		{
			if (radiobutton_editar.isSelected())
			{
				valor_editar = 1;
			} else if (!radiobutton_editar.isSelected())
			{
				valor_editar = 0;
			}
			radiobutton_reproducir.setSelected(false);
			valor_reproducir = 0;
		}

		if (ae.getSource() == boton_actualizar)
		{
			actualizarTrainer();
		}

		if (ae.getSource() == boton_siguiente)
		{
			s = "" + arbol_trainer.arbol_total.getLastSelectedPathComponent();
			sb = new StringBuffer();
			String sp = arbol_trainer.getArbolpath();
			StringBuffer sbpath = new StringBuffer();

			int i = 0;
			while (i < sp.length())
			{
				if (sp.charAt(i) == (','))
				{
					sbpath.append('/');
					i++;
				} else if (sp.charAt(i) == ('['))
				{
					sbpath.append('/');
				} else if (sp.charAt(i) == (']'))
				{
					sbpath.append('.');
				} else
				{
					sbpath.append(sp.charAt(i));
				}
				i++;
			}

			int profundidad = arbol_trainer.arbol_total.getSelectionPath()
					.getPathCount();

			FileReader entrada = null;

			if ((valor_reproducir == 1) && (!s.equals("null"))
					&& (profundidad > 2))
			{

				try
				{

					entrada = new FileReader(
							"Soporte/Referencias" + sbpath + "txt");

					int c;
					while ((c = entrada.read()) != -1)
					{
						sb.append((char) c);
					}

					entrada.close();
				}

				catch (IOException ex)
				{

				}

				for (int a = 0; a < sb.length(); a++)
				{
					if (sb.charAt(a) == ('#'))
					{
						llenarBuffer(a);
					}
				}

				HciReproductor hci_reproductor = new HciReproductor();
				Point ploc = this.getLocation();
				int plocx = (int) ploc.getX();
				int plocy = (int) ploc.getY();

				hci_reproductor.setLocation(plocx, plocy);

				hci_reproductor.cargarReferencias(vseparador);
				hci_reproductor.setVentanatrainer(this);

			}

			if ((valor_editar == 1) && (!s.equals("null")) && (profundidad > 2))
			{
				HciEditor hci_editor = new HciEditor();
				Point ploc = this.getLocation();
				int plocx = (int) ploc.getX();
				int plocy = (int) ploc.getY();

				hci_editor.setLocation(plocx, plocy);
				hci_editor.setVentanatrainer(this);

				try
				{
					entrada = new FileReader(
							"Soporte/Referencias" + sbpath + "txt");
					hci_editor.setPath("Soporte/Referencias" + sbpath);

					int c;
					while ((c = entrada.read()) != -1)
					{

						sb.append((char) c);
					}

					entrada.close();
				}

				catch (IOException ex)
				{

				}

				hci_editor.cargarReferencias(sb);

			}

			else if (((valor_reproducir == 0) && (valor_editar == 0))
					|| ((profundidad <= 2)))
			{
				JOptionPane joptema = new JOptionPane();
				StringBuffer buffer_contenido = new StringBuffer();
				buffer_contenido.append("No se ha seleccionado una opcion \n");
				buffer_contenido.append("Seleccione una opcion especifica \n");
				buffer_contenido.append("y vuelva a intentar \n");
				joptema.showMessageDialog(null, buffer_contenido,
						"ADVERTENCIA: Opcion invalida",
						javax.swing.JOptionPane.WARNING_MESSAGE);
			}
		}

		if (ae.getSource() == boton_cancelar)
		{
			this.dispose();
		}
	}

	public void llenarBuffer(int pos)
	{
		StringBuffer s = new StringBuffer();
		for (int a = pos; a < sb.length(); a++)
		{
			if (sb.charAt(a) != (';'))
			{
				s.append(sb.charAt(a));
			} else
			{
				vseparador.add(s);
				break;
			}
		}
	}

	public void actualizarTrainer()
	{
		VentanaTrainer t2 = new VentanaTrainer();
		Point ploc = this.getLocation();
		int plocx = (int) ploc.getX();
		int plocy = (int) ploc.getY();
		t2.setLocation(plocx, plocy);
		this.dispose();
	}
}

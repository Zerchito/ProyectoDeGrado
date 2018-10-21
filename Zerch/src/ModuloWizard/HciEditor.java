package ModuloWizard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.*;
import java.io.*;
import java.io.FileWriter;

public class HciEditor extends JFrame
		implements ActionListener, java.io.Serializable
{

	JPanel panel_oeste;
	JPanel panel_centro;
	JPanel panel_centro_norte;
	JPanel panel_sud;

	JLabel label_titulo_area;
	JLabel label_titulo_subarea;

	JLabel label_saludo;
	JTextArea textarea_informacion;
	StringBuffer sinformacion;

	JButton boton_descartar;
	JButton boton_guardar;
	JButton boton_exportar;

	Vector vector_referencias;

	int id_area;
	String titulo_area;
	int id_subarea;
	String titulo_subarea;

	ImageIcon imagen_reproductor;
	JLabel label_reproductor;

	JPanel panel_referencias;
	JScrollPane scroll_referencias;

	JLabel label_selecto;
	JTextField textfield_selecto;

	JPanel panel_agregar;
	JLabel label_rotulo_agregar;

	JLabel label_agregar_titulo;
	JTextField textfield_agregar_titulo;

	JLabel label_agregar_descripcion;
	JTextArea textarea_agregar_descripcion;
	JScrollPane scroll_agregar_descripcion;

	JLabel label_agregar_enlace;
	JTextField textfield_agregar_enlace;
	JButton boton_buscar_enlace;

	JButton boton_cancelar_agregar;
	JButton boton_aceptar_agregar;

	JTextArea textarea_referencia;

	int referencia_actual;

	JTextArea textarea_lineas;

	JFileChooser file1;

	FileWriter salida = null;

	StringBuffer buffer_contenido = new StringBuffer();

	String path = "";

	VentanaTrainer ventana_trainer = null;

	Stylepad stylepad;

	JLabel label_tituloeditor;

	public HciEditor()
	{

		setTitle("Editor HCI");
		setVisible(true);
		setLayout(new BorderLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int) screenSize.getWidth() * 2 / 6,
				(int) screenSize.getHeight() * 1 / 5);
		setSize(750, 480);
		setMinimumSize(new Dimension(600, 480));
		setDefaultCloseOperation(0);

		panel_oeste = new JPanel();
		panel_oeste.setLayout(null);
		panel_oeste.setPreferredSize(new Dimension(200, 400));
		panel_oeste.setBorder(new EtchedBorder());
		add("West", panel_oeste);

		imagen_reproductor = new ImageIcon("Soporte/Imagenes/editor.png");
		label_reproductor = new JLabel(imagen_reproductor);
		label_reproductor.setBorder(new EtchedBorder());
		label_reproductor.setBounds(5, 5, 105, 105);
		panel_oeste.add(label_reproductor);

		panel_centro = new JPanel();
		panel_centro.setLayout(new BorderLayout());
		panel_centro.setPreferredSize(new Dimension(350, 350));
		add("Center", panel_centro);

		panel_centro_norte = new JPanel();
		panel_centro_norte.setLayout(null);
		panel_centro_norte.setPreferredSize(new Dimension(300, 80));
		panel_centro_norte.setBorder(new EtchedBorder());
		panel_centro.add("North", panel_centro_norte);

		label_tituloeditor = new JLabel();
		label_tituloeditor.setSize(450, 40);
		label_tituloeditor.setLocation(5, 5);
		label_tituloeditor.setFont(new Font("Arial", 1, 17));
		label_tituloeditor.setText("Editor de referencias HCI.");
		label_tituloeditor.setLayout(null);
		panel_centro_norte.add(label_tituloeditor);

		JPanel panel_ver = new JPanel();
		panel_ver.setLayout(new BorderLayout());
		panel_ver.setBackground(Color.red);

		stylepad = new Stylepad();
		panel_centro.add(stylepad);

		panel_sud = new JPanel();
		panel_sud.setPreferredSize(new Dimension(400, 40));
		panel_sud.setLayout(new BorderLayout());
		panel_sud.setBorder(new BevelBorder(BevelBorder.RAISED));
		add("South", panel_sud);

		JPanel panel_opciones = new JPanel();
		panel_opciones.setBounds(5, 5, 450, 30);
		panel_opciones.setPreferredSize(new Dimension(450, 30));
		panel_opciones.setBorder(new EtchedBorder());
		panel_opciones.setLayout(null);
		panel_sud.add("East", panel_opciones);

		boton_descartar = new JButton("Cancelar");
		boton_descartar.setBounds(315, 10, 120, 20);
		panel_opciones.add(boton_descartar);

		boton_guardar = new JButton("Guardar");
		boton_guardar.setBounds(160, 10, 150, 20);
		boton_guardar.setEnabled(false);
		panel_opciones.add(boton_guardar);

		boton_exportar = new JButton("Exportar");
		boton_exportar.setBounds(5, 10, 150, 20);
		boton_exportar.setEnabled(false);
		panel_opciones.add(boton_exportar);

		boton_descartar.addActionListener(this);
		boton_guardar.addActionListener(this);
		boton_exportar.addActionListener(this);

		setSize(this.getWidth() + 1, this.getHeight());

		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				cerrarEsto();
			}
		});

	}

	public void actionPerformed(ActionEvent ae)
	{

		if (ae.getSource() == boton_descartar)
		{
			cerrarEsto();

		}

		if (ae.getSource() == boton_guardar)
		{
			guardarCambios();
		}

		if (ae.getSource() == boton_exportar)
		{
			exportarReferencia();
		}

	}

	public void cargarReferencias(StringBuffer sb)
	{
		boton_guardar.setEnabled(true);
		boton_exportar.setEnabled(true);

		stylepad.referencia.loadDocument();
		stylepad.editor.setText(sb.toString() + "\n");

	}

	public void guardarCambios()
	{
		JOptionPane jopcontinuar = new JOptionPane();
		int confirmcontinuar = jopcontinuar.showConfirmDialog(null,
				"Esta seguro que desea modificar la referencia: \n" + path
						+ "?");

		if (confirmcontinuar == 0)
		{
			reemplazarReferencia();

		}
	}

	public void reemplazarReferencia()
	{

		try
		{

			salida = new FileWriter(path + "txt");

			llenarBuffer();

			for (int a = 0; a < buffer_contenido.length(); a++)
			{

				salida.write(buffer_contenido.charAt(a));

			}
			salida.close();

		} catch (IOException ex)
		{

		}

		mostrarMensaje("exito");

	}

	public void exportarReferencia()
	{

		file1 = new JFileChooser();

		FiltroEditor txt = new FiltroEditor(new String("txt"),
				"Documento de texto");
		file1.addChoosableFileFilter(txt);

		int seleccion = file1.showSaveDialog(null);

		if (seleccion == JFileChooser.APPROVE_OPTION)
		{
			String path = file1.getSelectedFile().toString();

			File factual = new File(path);
			if (factual.exists())
			{
				JOptionPane jopexiste = new JOptionPane();
				int confirmexiste = jopexiste.showConfirmDialog(null,
						"El archivo ya existe..Desea Reemplazarlo?");
				if (confirmexiste == 0)
				{
					factual.delete();

					String stb = path;
					StringBuffer sb = new StringBuffer();
					char auxb;
					for (int b = 0; b < stb.length(); b++)
					{

						auxb = stb.charAt(b);
						if (auxb == '.')
						{
							break;
						} else
						{
							sb.append(auxb);

						}
					}

					Guardar("" + sb);
				}

			} else
			{
				Guardar(path);
			}

		}

		else if (seleccion == JFileChooser.CANCEL_OPTION)
		{

		}
	}

	void Guardar(String p)
	{

		try
		{

			String descripcion = "" + file1.getFileFilter().getDescription();
			String extension = "";

			if (descripcion.endsWith("(.txt)"))
			{
				extension = ".txt";
			}

			salida = new FileWriter(p + extension);

			llenarBuffer();

			for (int a = 0; a < buffer_contenido.length(); a++)
			{
				salida.write(buffer_contenido.charAt(a));

			}

			salida.close();

		} catch (IOException ex)
		{

		}

		mostrarMensaje("exito");

	}

	public void llenarBuffer()
	{
		String scontenido = stylepad.editor.getText();
		for (int a = 0; a < scontenido.length(); a++)
		{
			buffer_contenido.append(scontenido.charAt(a));
		}
	}

	public void setPath(String p)
	{
		path = p;

	}

	public void setVentanatrainer(VentanaTrainer vt)
	{
		ventana_trainer = vt;
	}

	public void mostrarMensaje(String s)
	{
		if (s.equals("exito"))
		{
			StringBuffer buffer_contenido = new StringBuffer();
			buffer_contenido.append("La serializacion de la referencia \n");
			buffer_contenido.append("a finalizado satisfactoriamente \n");
			JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null, buffer_contenido);
		}
	}

	public void cerrarEsto()
	{
		if (ventana_trainer != null)
		{
			ventana_trainer.actualizarTrainer();
		}
		this.dispose();
	}

}
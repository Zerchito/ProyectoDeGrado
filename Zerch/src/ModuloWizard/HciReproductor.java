package ModuloWizard;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.*;
import javax.swing.event.*;
import javax.swing.tree.DefaultMutableTreeNode;

import ModuloBase.*;

public class HciReproductor extends JFrame
		implements ActionListener, java.io.Serializable
{

	CardLayout cardmanejador;
	JPanel panel_display;

	JPanel panel_oeste;
	JPanel panel_centro;
	JPanel panel_centro_norte;
	JPanel panel_sud;

	JLabel label_titulo_area;
	JLabel label_titulo_subarea;

	JLabel label_saludo;
	JTextArea textarea_informacion;
	StringBuffer sinformacion;

	JRadioButton radiobutton_reproducir;
	JRadioButton radiobutton_editar;

	JButton boton_siguiente;
	JButton boton_cancelar;
	JButton boton_anterior;

	int id_area;
	String titulo_area;
	int id_subarea;
	String titulo_subarea;

	ImageIcon imagen_reproductor;
	JLabel label_reproductor;

	JTextArea textarea_referencia;
	StringBuffer snew = new StringBuffer();
	Vector vector_buffers = new Vector();

	JTextArea textarea_lineas;

	int cont = 0;

	Stylepad stylepad;

	Vector vector_referencias = new Vector();

	VentanaTrainer ventana_trainer = null;

	JLabel label_tituloreproductor;

	public HciReproductor()
	{

		setTitle("Reproductor HCI");
		setLayout(new BorderLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int) screenSize.getWidth() * 2 / 6,
				(int) screenSize.getHeight() * 1 / 5);
		setSize(750, 480);
		// setResizable(false);
		setVisible(true);
		setMinimumSize(new Dimension(600, 480));
		setDefaultCloseOperation(0);
		// setAlwaysOnTop(true);

		panel_oeste = new JPanel();
		panel_oeste.setLayout(null);
		panel_oeste.setPreferredSize(new Dimension(200, 400));
		panel_oeste.setBorder(new EtchedBorder());
		add("West", panel_oeste);

		imagen_reproductor = new ImageIcon("Soporte/Imagenes/reproductor.png");
		label_reproductor = new JLabel(imagen_reproductor);
		label_reproductor.setBounds(5, 5, 105, 105);
		label_reproductor.setBorder(new EtchedBorder());
		panel_oeste.add(label_reproductor);

		panel_centro = new JPanel();
		panel_centro.setLayout(new BorderLayout());
		panel_centro.setPreferredSize(new Dimension(350, 350));
		add("Center", panel_centro);

		panel_centro_norte = new JPanel();
		// panel_centro_norte.setBackground(Color.white);
		panel_centro_norte.setLayout(null);
		panel_centro_norte.setPreferredSize(new Dimension(300, 80));
		panel_centro_norte.setBorder(new EtchedBorder());
		panel_centro.add("North", panel_centro_norte);

		label_tituloreproductor = new JLabel();
		label_tituloreproductor.setSize(450, 40);
		label_tituloreproductor.setLocation(5, 5);
		label_tituloreproductor.setFont(new Font("Arial", 1, 17));
		label_tituloreproductor.setText("Reproductor de referencias HCI.");
		label_tituloreproductor.setLayout(null);
		panel_centro_norte.add(label_tituloreproductor);

		label_titulo_area = new JLabel();
		label_titulo_area.setBounds(10, 10, 300, 30);
		label_titulo_area.setFont(new Font("Consolas", Font.BOLD, 17));
		label_titulo_area.setForeground(new Color(0, 56, 199));
		panel_centro_norte.add(label_titulo_area);

		label_titulo_subarea = new JLabel();
		label_titulo_subarea.setBounds(10, 40, 300, 30);
		label_titulo_subarea.setFont(new Font("Consolas", Font.BOLD, 17));
		label_titulo_subarea.setForeground(new Color(0, 56, 199));
		panel_centro_norte.add(label_titulo_subarea);

		panel_sud = new JPanel();
		panel_sud.setPreferredSize(new Dimension(400, 40));
		panel_sud.setLayout(new BorderLayout());
		panel_sud.setBorder(new BevelBorder(BevelBorder.RAISED));
		add("South", panel_sud);

		stylepad = new Stylepad();
		panel_centro.add("Center", stylepad);
		// JScrollPane scrol_referencia=new JScrollPane(panel_ver);
		// panel_centro.add("Center",scrol_referencia);

		JTextField label_propiedades = new JTextField("Propiedades");
		label_propiedades.setBounds(0, 120, 200, 20);
		label_propiedades.setFont(new Font("System", Font.BOLD, 12));
		label_propiedades.setEditable(false);
		label_propiedades.setBorder(new BevelBorder(BevelBorder.RAISED));
		panel_oeste.add(label_propiedades);

		JPanel panel_opciones = new JPanel();
		panel_opciones.setBounds(5, 5, 360, 30);
		panel_opciones.setPreferredSize(new Dimension(360, 30));
		panel_opciones.setBorder(new EtchedBorder());
		panel_opciones.setLayout(null);
		panel_sud.add("East", panel_opciones);

		boton_anterior = new JButton("< Anterior");
		boton_anterior.setBounds(5, 5, 100, 20);
		panel_opciones.add(boton_anterior);

		boton_siguiente = new JButton("Siguiente >");
		boton_siguiente.setBounds(110, 5, 100, 20);
		panel_opciones.add(boton_siguiente);

		boton_cancelar = new JButton("Cancelar X");
		boton_cancelar.setBounds(215, 5, 100, 20);
		panel_opciones.add(boton_cancelar);

		boton_anterior.addActionListener(this);
		boton_siguiente.addActionListener(this);
		boton_cancelar.addActionListener(this);

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
		if (ae.getSource() == boton_siguiente)
		{
			boton_anterior.setEnabled(true);
			if (cont < vector_referencias.size() - 1)
			{
				cont++;
				reproducirReferencia(cont);
			} else
			{
				boton_siguiente.setEnabled(false);
			}
		}
		if (ae.getSource() == boton_anterior)
		{
			boton_siguiente.setEnabled(true);
			if (cont > 0)
			{
				cont--;
				reproducirReferencia(cont);
			} else
			{
				boton_anterior.setEnabled(false);
			}
		}
		if (ae.getSource() == boton_cancelar)
		{
			cerrarEsto();
		}
	}

	public void cargarReferencias(Vector v)
	{
		vector_referencias = v;
		reproducirReferencia(cont);

	}

	public void reproducirReferencia(int pos)
	{
		stylepad.referencia.loadDocument();
		stylepad.editor
				.setText(vector_referencias.elementAt(pos).toString() + "\n");
	}

	public void setVentanatrainer(VentanaTrainer vt)
	{
		ventana_trainer = vt;
	}

	public void cerrarEsto()
	{
		if (ventana_trainer == null)
		{
		} else
		{
			ventana_trainer.actualizarTrainer();
		}
		this.dispose();
	}

}

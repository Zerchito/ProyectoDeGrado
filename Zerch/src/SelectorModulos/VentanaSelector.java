package SelectorModulos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.applet.AudioClip;
import java.applet.Applet;
import java.net.URL;
import java.net.MalformedURLException;

public class VentanaSelector extends JFrame implements ActionListener
{

	JScrollPane scrol_modulos;

	JPanel panel_modulos;
	JLabel label_titulo;
	JLabel label_modulo;
	JLabel label_descripcion;
	JTextArea textarea_descripcion_base;
	JTextArea textarea_descripcion_trainer;
	JTextArea textarea_descripcion_wizard;
	JTextArea textarea_descripcion_help;

	JButton boton_base;
	JButton boton_trainer;
	JButton boton_wizard;
	JButton boton_help;

	private static AudioClip modulo1, modulo2, modulo3;
	private static String path = "File:" + System.getProperty("user.dir")
			+ "/Soporte/Sonidos/";

	FuncionSelector funcion_selector;

	public VentanaSelector()
	{
		funcion_selector = new FuncionSelector(this);

		super.setTitle("SELECTOR DE M�DULOS");
		setVisible(true);
		setLayout(null);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int) screenSize.getWidth() * 2 / 6,
				(int) screenSize.getHeight() * 1 / 5);
		setSize(500, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);

		panel_modulos = new JPanel();
		panel_modulos.setLayout(null);
		panel_modulos.setBounds(0, 0, 500, 400);
		panel_modulos.setPreferredSize(new Dimension(500, 400));

		scrol_modulos = new JScrollPane(panel_modulos);
		scrol_modulos.setBounds(5, 45, 480, 320);
		add(scrol_modulos);

		label_titulo = new JLabel("CAPACIDADES HERRAMIENTA");
		label_titulo.setBounds(5, 5, 200, 20);
		label_titulo.setBorder(new EtchedBorder());
		add(label_titulo);

		label_modulo = new JLabel("- M�DULO:");
		label_modulo.setBounds(5, 25, 85, 20);
		label_modulo.setBorder(new EtchedBorder());
		add(label_modulo);

		label_descripcion = new JLabel("- DESCRIPICI�N:");
		label_descripcion.setBounds(95, 25, 390, 20);
		label_descripcion.setBorder(new EtchedBorder());
		add(label_descripcion);

		Icon icono_base = new ImageIcon("Soporte/Imagenes/swing2.png");
		boton_base = new JButton("", icono_base);
		boton_base.setBounds(5, 5, 75, 85);
		panel_modulos.add(boton_base);

		Icon icono_wizard = new ImageIcon("Soporte/Imagenes/wizard2.png");
		boton_wizard = new JButton("", icono_wizard);
		boton_wizard.setBounds(5, 95, 75, 85);
		panel_modulos.add(boton_wizard);

		Icon icono_guitr = new ImageIcon("Soporte/Imagenes/trainer2.png");
		boton_trainer = new JButton("", icono_guitr);
		boton_trainer.setBounds(5, 185, 75, 85);
		panel_modulos.add(boton_trainer);

		Icon icono_help = new ImageIcon("Soporte/Imagenes/help2.png");
		boton_help = new JButton("", icono_help);
		boton_help.setBounds(5, 275, 75, 85);
		panel_modulos.add(boton_help);

		textarea_descripcion_base = new JTextArea();
		textarea_descripcion_base.setEditable(false);
		textarea_descripcion_base.setBounds(90, 5, 370, 85);
		textarea_descripcion_base.setFont(new Font("Dialog", Font.BOLD, 11));
		textarea_descripcion_base.setBackground(new Color(238, 238, 238));
		textarea_descripcion_base.setForeground(new Color(0, 0, 0));
		textarea_descripcion_base.setBorder(new EtchedBorder());
		panel_modulos.add(textarea_descripcion_base);

		StringBuffer sbase = new StringBuffer();
		sbase.append(
				"- M�dulo BASE.- Este m�dulo otorga al usuario la capacidad \n");
		sbase.append(
				"  de editar Interfaces Gr�ficas de Usuario (GUI), mediante el \n");
		sbase.append("  uso del Toolkit Swing de Java, con un look mucho \n");
		sbase.append("  m�s satisfactorio \n");
		textarea_descripcion_base.setText(sbase.toString());

		textarea_descripcion_wizard = new JTextArea();
		textarea_descripcion_wizard.setEditable(false);
		textarea_descripcion_wizard.setBounds(90, 95, 370, 85);
		textarea_descripcion_wizard.setFont(new Font("Dialog", Font.BOLD, 11));
		textarea_descripcion_wizard.setBackground(new Color(238, 238, 238));
		textarea_descripcion_wizard.setForeground(new Color(0, 0, 0));
		textarea_descripcion_wizard.setBorder(new EtchedBorder());
		panel_modulos.add(textarea_descripcion_wizard);

		StringBuffer swizard = new StringBuffer();
		swizard.append(
				"- M�dulo WIZARD.- Este m�dulo otorga al usuario la capacidad \n");
		swizard.append(
				"  de ser guiado durante el proceso de edicion de las \n");
		swizard.append("  Interfaces Gr�ficas de Usuario (GUI).");
		textarea_descripcion_wizard.setText(swizard.toString());

		textarea_descripcion_trainer = new JTextArea();
		textarea_descripcion_trainer.setEditable(false);
		textarea_descripcion_trainer.setBounds(90, 185, 370, 85);
		textarea_descripcion_trainer.setFont(new Font("Dialog", Font.BOLD, 11));
		textarea_descripcion_trainer.setBackground(new Color(238, 238, 238));
		textarea_descripcion_trainer.setForeground(new Color(0, 0, 0));
		textarea_descripcion_trainer.setBorder(new EtchedBorder());
		panel_modulos.add(textarea_descripcion_trainer);

		StringBuffer strainer = new StringBuffer();
		strainer.append(
				"- M�dulo TRAINER.- Este m�dulo otorga al usuario la capacidad \n");
		strainer.append(
				"  de acceder directamente a los principios HCI, permitiendo su\n");
		strainer.append("  reproducci�n o edicion de manera comoda. \n");
		textarea_descripcion_trainer.setText(strainer.toString());

		textarea_descripcion_help = new JTextArea();
		textarea_descripcion_help.setEditable(false);
		textarea_descripcion_help.setBounds(90, 275, 370, 85);
		textarea_descripcion_help.setFont(new Font("Dialog", Font.BOLD, 11));
		textarea_descripcion_help.setBackground(new Color(238, 238, 238));
		textarea_descripcion_help.setForeground(new Color(0, 0, 0));
		textarea_descripcion_help.setBorder(new EtchedBorder());
		panel_modulos.add(textarea_descripcion_help);

		StringBuffer shelp = new StringBuffer();
		shelp.append("- HELP.- Provee al usuario todo el soporte \n");
		shelp.append(
				"  y guia necesario respecto al manejo de la herramienta \n");
		textarea_descripcion_help.setText(shelp.toString());

		boton_base.addActionListener(this);
		boton_trainer.addActionListener(this);
		boton_wizard.addActionListener(this);
		boton_help.addActionListener(this);

		setSize(this.getWidth() + 1, this.getHeight());

		loading();

	}

	public static void loading()
	{
		try
		{
			URL modulo1URL = new URL(path + "Hint.wav");
			modulo1 = Applet.newAudioClip(modulo1URL);

			URL modulo2URL = new URL(path + "SecretFound.wav");
			modulo2 = Applet.newAudioClip(modulo2URL);

			URL modulo3URL = new URL(path + "GoodJob.wav");
			modulo3 = Applet.newAudioClip(modulo3URL);
		} catch (MalformedURLException e)
		{

		}
	}

	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == boton_base)
		{
			modulo2.play();
			funcion_selector.nuevoModuloBase();
		}
		if (ae.getSource() == boton_trainer)
		{
			modulo3.play();
			funcion_selector.nuevoModuloTrainer();

		}
		if (ae.getSource() == boton_wizard)
		{
			modulo1.play();
			funcion_selector.nuevoModuloWizard();
		}

		if (ae.getSource() == boton_help)
		{
			funcion_selector.nuevoSoporteAyuda();
		}

	}

}

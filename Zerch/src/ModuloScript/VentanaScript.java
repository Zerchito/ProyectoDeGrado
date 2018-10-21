package ModuloScript;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.swing.border.*;

public class VentanaScript extends JFrame
		implements ActionListener, java.io.Serializable
{

	JPanel panel_oeste;
	JPanel panel_script;
	JPanel panel_sud;
	JPanel panel_opciones;

	JTextArea textarea_lineas;
	JTextArea textarea_previo;
	JScrollPane scrol_previo;

	StringBuffer string_buffer;

	JTextField label_propiedades;

	PanelScriptFondo panel_script_fondo;
	PanelScriptTexto panel_script_texto;
	PanelScriptFuente panel_script_fuente;

	JLabel label_guardar;
	JButton boton_java;
	JButton boton_txt;

	JButton boton_volver;
	JButton boton_guardar;

	FuncionScript funcion_script;

	public VentanaScript()
	{
		super.setTitle("SCRIPTER GUI");
		ImageIcon icono = new ImageIcon("Soporte/Imagenes/icono.png");
		this.setIconImage(icono.getImage());

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int) screenSize.getWidth() * 2 / 6,
				(int) screenSize.getHeight() * 1 / 5);
		setSize(750, 500);
		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(600, 500));
		setVisible(true);

		panel_script = new JPanel();
		panel_script.setLayout(new BorderLayout());
		panel_script.setBackground(Color.red);

		panel_oeste = new JPanel();
		panel_oeste.setPreferredSize(new Dimension(200, 200));
		panel_oeste.setBorder(new EtchedBorder());
		panel_oeste.setLayout(null);
		add("West", panel_oeste);

		panel_sud = new JPanel();
		panel_sud.setPreferredSize(new Dimension(400, 40));
		panel_sud.setLayout(new BorderLayout());
		panel_sud.setBorder(new BevelBorder(BevelBorder.RAISED));
		add("South", panel_sud);

		ImageIcon imagen_trainer = new ImageIcon("Soporte/Imagenes/script.png");
		JLabel label_trainer = new JLabel(imagen_trainer);
		label_trainer.setBounds(5, 5, 105, 105);
		label_trainer.setBorder(new EtchedBorder());
		panel_oeste.add(label_trainer);

		label_propiedades = new JTextField("Propiedades");
		label_propiedades.setBounds(0, 120, 200, 20);
		label_propiedades.setFont(new Font("System", Font.BOLD, 12));
		label_propiedades.setEditable(false);
		label_propiedades.setBorder(new BevelBorder(BevelBorder.RAISED));
		panel_oeste.add(label_propiedades);

		panel_script_fondo = new PanelScriptFondo(this);
		panel_script_fondo.label_muestra.setText("Fondo:");
		panel_script_fondo.setLocation(0, 140);
		panel_oeste.add(panel_script_fondo);

		panel_script_texto = new PanelScriptTexto(this);
		panel_script_texto.label_muestra.setText("Texto:");
		panel_script_texto.setLocation(0, 235);
		panel_oeste.add(panel_script_texto);

		panel_script_fuente = new PanelScriptFuente(this);
		panel_script_fuente.label_muestra.setText("Fuente:");
		panel_script_fuente.setLocation(0, 330);
		panel_oeste.add(panel_script_fuente);

		textarea_lineas = new JTextArea();
		textarea_lineas.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		textarea_lineas.setFont(new Font("Consolas", Font.PLAIN, 12));
		textarea_lineas.setEditable(false);
		panel_script.add("West", textarea_lineas);

		textarea_previo = new JTextArea();
		textarea_previo.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		textarea_previo.setFont(new Font("Consolas", Font.PLAIN, 12));
		textarea_previo.setEditable(false);
		panel_script.add("Center", textarea_previo);

		scrol_previo = new JScrollPane(panel_script);
		add("Center", scrol_previo);

		panel_opciones = new JPanel();
		panel_opciones.setBounds(5, 5, 260, 30);
		panel_opciones.setPreferredSize(new Dimension(260, 30));
		panel_opciones.setBorder(new EtchedBorder());
		panel_opciones.setLayout(null);
		panel_sud.add("East", panel_opciones);

		boton_volver = new JButton("<< Volver");
		boton_volver.setBounds(5, 10, 120, 20);
		panel_opciones.add(boton_volver);

		boton_guardar = new JButton("Guardar >>");
		boton_guardar.setBounds(130, 10, 120, 20);
		panel_opciones.add(boton_guardar);
		boton_guardar.setEnabled(false);

		boton_guardar.addActionListener(this);
		boton_volver.addActionListener(this);

		setSize(this.getWidth() + 1, this.getHeight());
	}

	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == boton_guardar)
		{
			funcion_script.guardarScript();
		} else if (ae.getSource() == boton_volver)
		{
			funcion_script.volver();
		}
	}

	public void setBuffer(StringBuffer s)
	{
		string_buffer = s;
		llenarAreabuffer();
		boton_guardar.setEnabled(true);
	}

	public void llenarAreabuffer()
	{
		textarea_previo.setText("" + string_buffer);
		for (int a = 1; a < textarea_previo.getLineCount(); a++)
		{
			textarea_lineas.append("" + a + "\n");
		}
	}

	public void setFuncionscript(FuncionScript fs)
	{
		funcion_script = fs;
	}
}

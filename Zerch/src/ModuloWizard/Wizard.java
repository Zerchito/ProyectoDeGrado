package ModuloWizard;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;

import ModuloBase.*;
import Interfaz.VentanaPrincipal;
import InterfazBase.*;
import SwingWidget.*;

public class Wizard extends JFrame
		implements ActionListener, java.io.Serializable
{

	CardLayout cardmanejador;

	JPanel panel_centro;
	JPanel panel_centro_norte;
	JPanel panel_centro_sud;
	JPanel panel_sud;
	JPanel panel_display;

	JScrollPane scrol_modulos;

	JPanel panel_modulos;
	JLabel label_titulo;
	JLabel label_modulo;
	JLabel label_descripcion;
	JTextArea textarea_descripcion_awt;
	JTextArea textarea_descripcion_swing;
	JTextArea textarea_descripcion_wizard;

	JButton boton_guiaw;
	JButton boton_guisw;
	JButton boton_wizard;

	JPanel panel_inicio;
	JPanel panel_uno;
	JPanel panel_dos;
	JPanel panel_tres;
	JPanel panel_cuatro;
	JPanel panel_fin;

	JButton boton_inicio;
	JButton boton_anterior;
	JButton boton_siguiente;
	JButton boton_fin;

	JProgressBar progreso_wizard;

	PanelDimension panel_dimension;
	PanelVentanas panel_ventanas;
	PanelFinalizador panel_finalizador;
	PanelHabilitables panel_habilitables;

	int porcentaje_progreso = 0;

	JRadioButton radio_defecto;
	JComboBox combo_defecto;

	int contador_pasos = 0;

	ValidadorWizard validador_wizard;

	Component[] paneles;
	int total_paneles = 0;
	int contador = 0;

	boolean panel_validado = false;

	JLabel label_titulowizard;

	JTextArea textarea_descripcionwizard;
	int ancho;
	int alto;
	int cantidad;

	String sancho;
	String salto;
	String scantidad;

	public Wizard()
	{

		setTitle("Wizard GUI");
		setLayout(new BorderLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int) screenSize.getWidth() * 2 / 6,
				(int) screenSize.getHeight() * 1 / 5);
		setSize(700, 400);
		setResizable(false);
		setVisible(true);
		super.setTitle("WIZARD GUI");
		setMinimumSize(new Dimension(600, 350));

		panel_centro = new JPanel();
		panel_centro.setLayout(new BorderLayout());
		add("Center", panel_centro);

		panel_centro_norte = new JPanel();
		panel_centro_norte.setBorder(new EtchedBorder());
		panel_centro_norte.setLayout(null);
		panel_centro_norte.setPreferredSize(new Dimension(400, 70));
		panel_centro.add("North", panel_centro_norte);

		label_titulowizard = new JLabel();
		label_titulowizard.setSize(450, 40);
		label_titulowizard.setLocation(5, 5);
		label_titulowizard.setFont(new Font("Arial", 1, 17));
		label_titulowizard.setText(
				"WIZARD: Asistente para la configuracion del Entorno.");
		label_titulowizard.setLayout(null);
		panel_centro_norte.add(label_titulowizard);

		JPanel panel_oeste = new JPanel();
		panel_oeste.setLayout(null);
		panel_oeste.setPreferredSize(new Dimension(200, 500));
		panel_oeste.setSize(200, 500);
		panel_oeste.setBorder(new EtchedBorder());
		add("West", panel_oeste);

		ImageIcon imagen_wizard = new ImageIcon(
				"Soporte/Imagenes/wizard_rep.png");

		JLabel label_wizard = new JLabel(imagen_wizard);
		label_wizard.setBounds(5, 5, 105, 105);
		label_wizard.setBorder(new EtchedBorder());
		panel_oeste.add(label_wizard);

		panel_display = new JPanel();
		cardmanejador = new CardLayout();
		panel_display.setLayout(cardmanejador);
		panel_display.setBorder(new EtchedBorder());
		panel_centro.add("Center", panel_display);

		panel_inicio = new JPanel();
		panel_inicio.setBackground(Color.white);
		panel_inicio.setLayout(new BorderLayout());
		panel_display.add("Inicio", panel_inicio);

		textarea_descripcionwizard = new JTextArea();
		textarea_descripcionwizard.setSize(373, 116);
		textarea_descripcionwizard.setLocation(39, 57);
		textarea_descripcionwizard.setBackground(new Color(255, 255, 255));
		textarea_descripcionwizard.setForeground(new Color(51, 51, 51));
		textarea_descripcionwizard.setBorder(null);
		textarea_descripcionwizard.setFont(new Font("Arial", 1, 13));
		StringBuffer buffer_texto = new StringBuffer();
		buffer_texto.append(
				"Este asistente le ayudara a configurar las principales\n");
		buffer_texto.append("caracteristicas del entorno de edicion.\n");
		buffer_texto.append("\n");
		buffer_texto.append("Puede configurar las opciones de:\n");
		buffer_texto.append("- Dimension inicial para las ventanas.\n");
		buffer_texto.append("- Numero de ventanas en el entorno.\n");
		textarea_descripcionwizard.setText(buffer_texto.toString());
		textarea_descripcionwizard.setLayout(null);
		textarea_descripcionwizard.setEditable(false);
		panel_inicio.add("Center", textarea_descripcionwizard);

		panel_dimension = new PanelDimension();
		panel_display.add("Uno", panel_dimension);

		panel_ventanas = new PanelVentanas();
		panel_display.add("Dos", panel_ventanas);

		panel_habilitables = new PanelHabilitables();
		panel_display.add("Tres", panel_habilitables);

		panel_finalizador = new PanelFinalizador();
		panel_display.add("Fin", panel_finalizador);

		panel_centro_sud = new JPanel();
		panel_centro_sud.setLayout(new GridLayout(1, 4, 10, 10));
		panel_centro_sud
				.setBorder(new MatteBorder(10, 10, 10, 10, Color.white));
		panel_centro.add("South", panel_centro_sud);

		boton_anterior = new JButton("<< Anterior");
		boton_anterior.setEnabled(false);
		panel_centro_sud.add(boton_anterior);

		boton_siguiente = new JButton("Siguiente >>");
		panel_centro_sud.add(boton_siguiente);

		boton_fin = new JButton("Finalizar");
		panel_centro_sud.add(boton_fin);

		progreso_wizard = new JProgressBar(0, 100);
		progreso_wizard.setStringPainted(true);
		progreso_wizard.setValue(porcentaje_progreso);
		add("South", progreso_wizard);

		boton_fin.setEnabled(false);

		boton_anterior.addActionListener(this);
		boton_siguiente.addActionListener(this);
		boton_fin.addActionListener(this);

		validador_wizard = new ValidadorWizard();

		setSize(this.getWidth() + 1, this.getHeight());

	}

	public void actionPerformed(ActionEvent ae)
	{

		paneles = panel_display.getComponents();
		total_paneles = paneles.length;
		if (contador == 0)
		{
			panel_validado = true;
		}
		if (contador == 1)
		{
			PanelDimension panel_actual = (PanelDimension) paneles[1];
			panel_actual.actualizar();
			panel_validado = validador_wizard
					.validarDimension(panel_actual.ancho, panel_actual.alto);
			sancho = panel_actual.ancho;
			salto = panel_actual.alto;

		}
		if (contador == 2)
		{
			PanelVentanas panel_actual = (PanelVentanas) paneles[2];
			panel_actual.actualizar();
			panel_validado = validador_wizard
					.validarVentanas(panel_actual.cantidad);
			scantidad = panel_actual.cantidad;

		}
		if (contador == 3)
		{
			panel_validado = true;
		}
		if (contador == 4)
		{
			panel_validado = true;
		}

		int incremento_progreso = 100 / (total_paneles - 1);

		if (ae.getSource() == boton_siguiente)
		{

			if (contador < total_paneles - 1)
			{
				if (panel_validado == true)
				{
					contador++;
					porcentaje_progreso = porcentaje_progreso
							+ incremento_progreso;
					progreso_wizard.setValue(porcentaje_progreso);
					cardmanejador.next(panel_display);
				} else
				{

				}
			}

		}

		if (ae.getSource() == boton_anterior)
		{
			if (contador > 0)
			{
				contador--;
				porcentaje_progreso = porcentaje_progreso - incremento_progreso;
				progreso_wizard.setValue(porcentaje_progreso);

				cardmanejador.previous(panel_display);
			}
		}

		if (ae.getSource() == boton_fin)
		{

			VentanaPrincipal v = new VentanaPrincipal("hola");
			v.setLocation(20, 20);

			PanelDimension panel_dimension = (PanelDimension) paneles[1];
			ancho = Integer.parseInt(panel_dimension.ancho);
			alto = Integer.parseInt(panel_dimension.alto);

			PanelVentanas panel_ventanas = (PanelVentanas) paneles[2];
			cantidad = Integer.parseInt(panel_ventanas.cantidad);

			for (int a = 0; a < cantidad; a++)
			{
				v.funcion_base.nuevoEntorno("chau");
				Ranura ranura_actual = (Ranura) v.funcion_base.vector_ranuras
						.elementAt(a);

				VentanaInterna ventana_actual = (VentanaInterna) ranura_actual.vector_widgets
						.elementAt(0);
				ventana_actual.setSize(ancho, alto);
			}

			this.dispose();
		}

		if (contador > 0)
		{
			boton_anterior.setEnabled(true);
			boton_siguiente.setEnabled(true);
			boton_fin.setEnabled(false);
		}
		if (contador == total_paneles - 1)
		{
			boton_anterior.setEnabled(true);
			boton_siguiente.setEnabled(false);
			boton_fin.setEnabled(true);

			PanelFinalizador panel_finalizador = (PanelFinalizador) paneles[4];
			panel_finalizador.label_01
					.setText("Dimension = (" + sancho + "," + salto + ");");
			panel_finalizador.label_02
					.setText("Cantidad de ventanas = (" + scantidad + ");");

		}
		if (contador == 0)
		{
			boton_anterior.setEnabled(false);
			boton_siguiente.setEnabled(true);
			boton_fin.setEnabled(false);
		}

	}

}

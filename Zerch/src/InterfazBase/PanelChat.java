package InterfazBase;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.DefaultCaret;

import Conexion.AdministradorEventos;
import ModuloBase.*;
import ModuloWizard.*;

@SuppressWarnings("serial")
public class PanelChat extends JPanel
{
	public JPanel panel_norte;
	public JPanel panel_norte_centro;
	public JPanel panel_norte_este;
	public JPanel panel_sur;
	public JPanel panel_centro;

	public BotonOcultar boton_ocultar;

	public JButton boton_participantes;
	public JTextArea area;
	public Stylepad stylepad;
	public JTextField entrada_chat;
	public JButton boton_enviar;

	public FuncionBase funcion_base;

	public PanelChat(FuncionBase fb)
	{
		funcion_base = fb;
		setSize(200, 100);
		setVisible(true);
		setLayout(new BorderLayout());
		setBorder(new BevelBorder(BevelBorder.RAISED));

		panel_norte = new JPanel();
		panel_norte.setLayout(new BorderLayout());
		panel_norte.setBorder(new EtchedBorder());
		this.add("North", panel_norte);

		panel_norte_centro = new JPanel();
		panel_norte_centro.setLayout(null);
		panel_norte_centro.setPreferredSize(new Dimension(300, 22));
		panel_norte.add("Center", panel_norte_centro);

		panel_norte_este = new JPanel();
		panel_norte_este.setPreferredSize(new Dimension(40, 20));
		panel_norte_este.setLayout(null);
		panel_norte.add("East", panel_norte_este);

		boton_participantes = new BotonParticipantes(this);
		boton_participantes.setBounds(0, 0, 90, 20);
		boton_participantes.setName("Participantes");
		boton_participantes
				.setToolTipText("Mostrar Participantes del proyecto");
		boton_participantes.setText("Participantes");
		panel_norte_centro.add("West", boton_participantes);

		boton_ocultar = new BotonOcultar(this);
		boton_ocultar.setBounds(20, 0, 20, 20);
		boton_ocultar.setName("Ocultar");
		boton_ocultar.setToolTipText("Ocultar");
		Icon icono_ocultar = new ImageIcon("Soporte/Imagenes/ocultar.png");
		boton_ocultar.fijarIcono(icono_ocultar);
		panel_norte_este.add(boton_ocultar);

		panel_centro = new JPanel();
		panel_centro.setLayout(new BorderLayout());
		panel_centro.setPreferredSize(new Dimension(100, 100));
		panel_centro.setBorder(new EtchedBorder());
		add("Center", panel_centro);
		// this.addTab("Mensajes", null , panel_centro, "Chat del Proyecto");

		area = new JTextArea(3, 40);
		area.setEditable(false);
		panel_centro.add("Center", area);
		panel_centro.add(new JScrollPane(area));
		area.setVisible(true);

		panel_sur = new JPanel();
		panel_sur.setPreferredSize(new Dimension(100, 25));
		panel_sur.setLayout(new BorderLayout());
		panel_sur.setBorder(new EtchedBorder());
		add("South", panel_sur);

		entrada_chat = new JTextField(65);
		entrada_chat.setDragEnabled(true);
		entrada_chat.setVisible(true);
		panel_sur.add("West", entrada_chat);

		boton_enviar = new JButton("Enviar");
		boton_enviar.setName("enviar");
		panel_sur.add("East", boton_enviar);

	}

	public void agregarManejadorDeEventos(AdministradorEventos oyente)
	{
		boton_enviar.addActionListener(oyente);
		entrada_chat.addKeyListener(oyente);
		boton_participantes.addActionListener(oyente);
	}

	public String getMensaje()
	{
		return entrada_chat.getText();
	}

	public void limpiarCampoTexto()
	{
		entrada_chat.setText("");
	}

	public void updateAreaTexto(String mensajes)
	{
		area.setText(mensajes);
		area.append("");
		DefaultCaret caret = (DefaultCaret) area.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	}
}
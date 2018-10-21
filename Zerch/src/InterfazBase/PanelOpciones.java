package InterfazBase;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.*;
import ModuloBase.*;

@SuppressWarnings(
{ "serial", "rawtypes" })
public class PanelOpciones extends JPanel implements ActionListener
{
	public JLabel label_opciones;
	public JButton boton_agregar_widget;
	public JButton boton_remover_selecto;
	public JButton boton_cerrar;
	JTextField label_background;
	JButton boton_background;
	FuncionBase funcion_base;
	JPanel panel_oeste2;
	Ranura ranura_selecta;
	Vector vector_posiciones = new Vector();
	Vector vector_contenedores = new Vector();
	Vector vector_borrar = new Vector();

	Component[] array_contenido = new Component[0];
	StringBuffer buffer_contenido = new StringBuffer();

	public PanelOpciones(FuncionBase v)
	{
		funcion_base = v;

		setPreferredSize(new java.awt.Dimension(200, 25));
		setVisible(true);
		setLayout(null);
		setBorder(new BevelBorder(BevelBorder.RAISED));

		label_opciones = new JLabel("Opciones:");
		label_opciones.setFont(new Font("ArialBlack", Font.BOLD, 12));
		label_opciones.setBorder(new EtchedBorder());
		label_opciones.setBounds(2, 2, 65, 20);
		add(label_opciones);

		boton_agregar_widget = new JButton("Agregar widget");
		boton_agregar_widget.setBounds(70, 2, 125, 20);
		boton_agregar_widget.setEnabled(false);
		add(boton_agregar_widget);

		boton_remover_selecto = new JButton("Remover widget");
		boton_remover_selecto.setBounds(200, 2, 130, 20);
		boton_remover_selecto.setEnabled(false);
		add(boton_remover_selecto);

		boton_agregar_widget.addActionListener(this);
		boton_remover_selecto.addActionListener(this);

	}

	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == boton_agregar_widget)
		{
			funcion_base.agregarWidget();
		}
		if (ae.getSource() == boton_remover_selecto)
		{
			funcion_base.removerSelecto();
		}
	}
}
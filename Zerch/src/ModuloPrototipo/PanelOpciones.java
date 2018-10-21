package ModuloPrototipo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

import java.util.*;

public class PanelOpciones extends JPanel
		implements ActionListener, java.io.Serializable
{

	public JLabel label_componentes;
	public JLabel label_opciones;

	JLabel label_nodo;
	JLabel label_enlace;

	PanelInteligente paneli_nodo;
	PanelInteligente paneli_enlace;

	VentanaPrototipo ventana_prototipo = null;

	JButton boton_movernodo;

	JButton boton_cerrar;

	Vector vector_panelesi = new Vector();

	public PanelOpciones(VentanaPrototipo i)
	{

		ventana_prototipo = i;

		setLayout(null);
		setSize(190, 400);
		setVisible(true);
		setBorder(new BevelBorder(BevelBorder.RAISED));

		label_componentes = new JLabel("Componentes:");
		label_componentes.setFont(new Font("ArialBlack", Font.BOLD, 12));
		label_componentes.setBorder(new EtchedBorder());
		label_componentes.setBounds(5, 5, 100, 20);
		add(label_componentes);

		label_nodo = new JLabel("Nodo:");
		label_nodo.setFont(new Font("ArialBlack", Font.BOLD, 12));
		label_nodo.setBorder(new EtchedBorder());
		label_nodo.setBounds(5, 30, 50, 20);
		add(label_nodo);

		paneli_nodo = new PanelInteligente(this);
		paneli_nodo.setName("Nodo");
		paneli_nodo.setToolTipText("Nodo");
		Icon icono_nodo = new ImageIcon(
				"Soporte/Imagenes/imagenesPrototipo/nodo.png");
		paneli_nodo.fijarIcono(icono_nodo);
		paneli_nodo.setBackground(Color.white);
		paneli_nodo.setLocation(5, 50);
		paneli_nodo.setSize(45, 45);
		add(paneli_nodo);
		vector_panelesi.add(paneli_nodo);

		label_enlace = new JLabel("Enlace:");
		label_enlace.setFont(new Font("ArialBlack", Font.BOLD, 12));
		label_enlace.setBorder(new EtchedBorder());
		label_enlace.setBounds(55, 30, 50, 20);
		add(label_enlace);

		paneli_enlace = new PanelInteligente(this);
		paneli_enlace.setName("Enlace");
		paneli_enlace.setToolTipText("Enlace");
		Icon icono_enlace = new ImageIcon(
				"Soporte/Imagenes/imagenesPrototipo/enlace.png");
		paneli_enlace.fijarIcono(icono_enlace);
		paneli_enlace.setLocation(55, 50);
		paneli_enlace.setSize(45, 45);
		add(paneli_enlace);
		vector_panelesi.add(paneli_enlace);

		label_opciones = new JLabel("Opciones:");
		label_opciones.setFont(new Font("ArialBlack", Font.BOLD, 12));
		label_opciones.setBorder(new EtchedBorder());
		label_opciones.setBounds(5, 100, 100, 20);
		add(label_opciones);

		boton_movernodo = new JButton("MoverNodo");
		boton_movernodo.setBounds(5, 125, 100, 20);
		add(boton_movernodo);

		boton_cerrar = new JButton("Cerrar");
		boton_cerrar.setBounds(5, 185, 100, 20);
		add(boton_cerrar);

		boton_movernodo.addActionListener(this);
		boton_cerrar.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == boton_movernodo)
		{
			for (int a = 0; a < vector_panelesi.size(); a++)
			{
				PanelInteligente paneli_actual = (PanelInteligente) vector_panelesi
						.elementAt(a);
				paneli_actual.setBorder(null);
				ventana_prototipo.funcion_prototipo.figura = null;
			}

			ventana_prototipo.funcion_prototipo.modo = 2;

		}

		if (ae.getSource() == boton_cerrar)
		{
			ventana_prototipo.cerrarEsto();
		}

	}

	public void actualizarClickeado(String nombre)
	{

		for (int a = 0; a < vector_panelesi.size(); a++)
		{
			PanelInteligente paneli_actual = (PanelInteligente) vector_panelesi
					.elementAt(a);

			if (paneli_actual.getName().equals(nombre))
			{
				paneli_actual.setClickeado(true);
				paneli_actual
						.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
				ventana_prototipo.funcion_prototipo.figura = nombre;
			} else
			{
				paneli_actual.setClickeado(false);
				paneli_actual.setBorder(new BevelBorder(BevelBorder.RAISED));
			}

			ventana_prototipo.funcion_prototipo.modo = 1;
		}
	}

}

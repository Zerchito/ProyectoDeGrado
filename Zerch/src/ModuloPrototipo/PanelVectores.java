package ModuloPrototipo;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.border.*;

import java.util.*;

import ModuloBase.*;

public class PanelVectores extends JPanel
		implements ActionListener, java.io.Serializable
{

	public JLabel label_vectornodos;
	public JLabel label_vectorenlaces;

	JComboBox combo_nodos;
	JComboBox combo_enlaces;

	JButton boton_nodook;
	JButton boton_enlaceok;

	VentanaPrototipo ventana_prototipo;

	public PanelVectores(VentanaPrototipo i)
	{

		ventana_prototipo = i;

		setLayout(null);
		setSize(350, 50);
		setVisible(true);
		setBorder(new BevelBorder(BevelBorder.RAISED));

		label_vectornodos = new JLabel("Vector Nodos:");
		label_vectornodos.setFont(new Font("ArialBlack", Font.BOLD, 12));
		label_vectornodos.setBorder(new EtchedBorder());
		label_vectornodos.setBounds(5, 5, 100, 20);
		add(label_vectornodos);

		label_vectorenlaces = new JLabel("Vector Enlaces:");
		label_vectorenlaces.setFont(new Font("ArialBlack", Font.BOLD, 12));
		label_vectorenlaces.setBorder(new EtchedBorder());
		label_vectorenlaces.setBounds(5, 25, 100, 20);
		add(label_vectorenlaces);

		combo_nodos = new JComboBox();
		combo_nodos.setBackground(Color.white);
		combo_nodos.setBounds(105, 5, 150, 20);
		add(combo_nodos);

		boton_nodook = new JButton("Eliminar");
		boton_nodook.setBounds(255, 5, 90, 20);
		add(boton_nodook);

		combo_enlaces = new JComboBox();
		combo_enlaces.setBackground(Color.white);
		combo_enlaces.setBounds(105, 25, 150, 20);
		add(combo_enlaces);

		boton_enlaceok = new JButton("Eliminar");
		boton_enlaceok.setBounds(255, 25, 90, 20);
		add(boton_enlaceok);

		boton_nodook.addActionListener(this);
		boton_enlaceok.addActionListener(this);

	}

	public void actionPerformed(ActionEvent ae)
	{

		if (ae.getSource() == boton_nodook)
		{
			int posicion = combo_nodos.getSelectedIndex();
			Ranura ranura_selecta = (Ranura) ventana_prototipo.vector_nonull
					.elementAt(posicion);

			JOptionPane jopcontinuar = new JOptionPane();
			int confirmcontinuar = jopcontinuar.showConfirmDialog(null,
					"La Ranura: " + ranura_selecta.getPosicionranura()
							+ ". aun contiene elementos... Desea Continuar?");
			if (confirmcontinuar == 0)
			{
				ventana_prototipo.funcion_base.ventana_base.carga_ranuras
						.removeTabAt(ranura_selecta.getPosicionranura());
				ventana_prototipo.funcion_base.ventana_base.carga_ranuras
						.insertTab("null", null, null, null,
								ranura_selecta.getPosicionranura());
				ventana_prototipo.funcion_base.vector_ranuras
						.removeElementAt(ranura_selecta.getPosicionranura());
				ventana_prototipo.funcion_base.vector_ranuras.insertElementAt(
						null, ranura_selecta.getPosicionranura());

				// ventana_prototipo.funcion_base.ventana_base.carga_ranuras.remove(posicion);

				combo_nodos.removeItemAt(posicion);
				ventana_prototipo.funcion_prototipo.vector_nodos
						.removeElementAt(posicion);
				// combo_nodos.insertItemAt(""+posicion+": null",posicion);
				// ventana_prototipo.funcion_prototipo.vector_nodos.insertElementAt(null,posicion);
				ventana_prototipo.funcion_prototipo.repaint();
			}

			// int posicion=combo_nodos.getSelectedIndex();

			// JOptionPane jopcontinuar=new JOptionPane();
			// int confirmcontinuar= jopcontinuar.showConfirmDialog(null, "La
			// Ranura aun contiene elementos... Desea Continuar?");
			// if(confirmcontinuar==0)
			// {
			// combo_nodos.removeItemAt(posicion);
			// ventana_prototipo.funcion_prototipo.vector_nodos.removeElementAt(posicion);
			// combo_nodos.insertItemAt(""+posicion+": null",posicion);
			// ventana_prototipo.funcion_prototipo.vector_nodos.insertElementAt(null,posicion);
			// ventana_prototipo.funcion_prototipo.repaint();
			// }

		}
		if (ae.getSource() == boton_enlaceok)
		{
			int posicion = combo_enlaces.getSelectedIndex();

			combo_enlaces.removeItemAt(posicion);
			ventana_prototipo.funcion_prototipo.vector_enlaces
					.removeElementAt(posicion);
			combo_enlaces.insertItemAt("" + posicion + ": null", posicion);
			ventana_prototipo.funcion_prototipo.vector_enlaces
					.insertElementAt(null, posicion);
			ventana_prototipo.funcion_prototipo.repaint();
		}
	}

}

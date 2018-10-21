package ModuloScript;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

import ModuloBase.*;

public class PanelScriptFuente extends JPanel implements ActionListener
{
	// Elementos Frame
	public JLabel label_muestra;
	public JComboBox choice_muestra;

	public JLabel label_puntos;
	public JTextField textfield_puntos;

	public JLabel label_estilo;
	public JTextField textfield_estilo;

	public JSlider slider_puntos;

	public JCheckBox check_negrita;
	public JCheckBox check_cursiva;

	// Variables
	// FuncionPrincipal funcion_principal;
	Font fuente_total;
	String fuente_nombre = "Arial";
	int fuente_puntos = 12;
	int valor_negrita = Font.PLAIN;
	int valor_cursiva = Font.PLAIN;

	VentanaScript ventana_actual;

	public PanelScriptFuente(VentanaScript v)
	{
		// funcion_principal=v;

		ventana_actual = v;

		setBounds(0, 0, 200, 70);
		setLayout(null);
		setVisible(true);
		setBorder(new BevelBorder(BevelBorder.RAISED));

		label_muestra = new JLabel("Muestra");
		label_muestra.setFont(new Font("ArialBlack", Font.BOLD, 12));
		label_muestra.setBorder(new EtchedBorder());
		label_muestra.setBounds(5, 5, 50, 20);
		add(label_muestra);

		choice_muestra = new JComboBox();
		choice_muestra.setBackground(Color.white);
		choice_muestra.setFont(new Font("Dialog", Font.PLAIN, 12));
		choice_muestra.setBounds(55, 5, 140, 20);
		add(choice_muestra);

		choice_muestra.addItem("Arial");
		choice_muestra.addItem("Calibri");
		choice_muestra.addItem("Cambria");
		choice_muestra.addItem("Consolas");
		choice_muestra.addItem("Constantia");
		choice_muestra.addItem("Corbel");
		choice_muestra.addItem("Courier");
		choice_muestra.addItem("Dialog");
		choice_muestra.addItem("Fixedsys");
		choice_muestra.addItem("Garamond");
		choice_muestra.addItem("Georgia");
		choice_muestra.addItem("Impact");
		choice_muestra.addItem("Monotype Corsiva");
		choice_muestra.addItem("System");
		choice_muestra.addItem("Verdana");

		label_puntos = new JLabel("Puntos:");
		label_puntos.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_puntos.setBorder(new EtchedBorder());
		label_puntos.setBounds(5, 25, 50, 20);
		add(label_puntos);

		textfield_puntos = new JTextField("...");
		textfield_puntos.setBorder(new EtchedBorder());
		textfield_puntos.setBackground(Color.white);
		textfield_puntos.setBounds(55, 25, 35, 20);
		add(textfield_puntos);

		slider_puntos = new JSlider();
		slider_puntos.setBounds(90, 25, 105, 20);
		slider_puntos.setMinimum(8);
		slider_puntos.setMaximum(100);
		slider_puntos.setValue(12);
		add(slider_puntos);

		label_estilo = new JLabel("Estilo:");
		label_estilo.setBorder(new EtchedBorder());
		label_estilo.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_estilo.setBounds(5, 45, 50, 20);
		add(label_estilo);

		check_negrita = new JCheckBox("Negrita");
		check_negrita.setBounds(55, 45, 70, 20);
		check_negrita.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(check_negrita);

		check_cursiva = new JCheckBox("Cursiva");
		check_cursiva.setBounds(125, 45, 70, 20);
		check_cursiva.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(check_cursiva);

		fuente_total = new Font("Arial", Font.PLAIN, 12);

		choice_muestra.addActionListener(this);
		check_negrita.addActionListener(this);
		check_cursiva.addActionListener(this);

		slider_puntos.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent event)
			{
				JSlider source = (JSlider) event.getSource();
				textfield_puntos.setText("" + source.getValue());
				fuente_puntos = source.getValue();

				fuente_total = new Font(fuente_nombre,
						valor_negrita + valor_cursiva, fuente_puntos);
				ventana_actual.textarea_lineas.setFont(fuente_total);
				ventana_actual.textarea_previo.setFont(fuente_total);

			}
		});

	}

	public void actionPerformed(ActionEvent ae)
	{
		// if(funcion_principal.widget_actual!=null)
		{

			if (ae.getSource() == choice_muestra)
			{
				fuente_nombre = "" + choice_muestra.getSelectedItem();
				fuente_total = new Font(fuente_nombre,
						valor_negrita + valor_cursiva, fuente_puntos);
				ventana_actual.textarea_lineas.setFont(fuente_total);
				ventana_actual.textarea_previo.setFont(fuente_total);
			}

			if (ae.getSource() == check_negrita)
			{
				if (check_negrita.isSelected())
				{
					valor_negrita = Font.BOLD;
					fuente_total = new Font(fuente_nombre,
							valor_negrita + valor_cursiva, fuente_puntos);
					ventana_actual.textarea_lineas.setFont(fuente_total);
					ventana_actual.textarea_previo.setFont(fuente_total);
				} else
				{
					valor_negrita = Font.PLAIN;
					fuente_total = new Font(fuente_nombre,
							valor_negrita + valor_cursiva, fuente_puntos);
					ventana_actual.textarea_lineas.setFont(fuente_total);
					ventana_actual.textarea_previo.setFont(fuente_total);
				}
			}

			if (ae.getSource() == check_cursiva)
			{
				if (check_cursiva.isSelected())
				{
					valor_cursiva = Font.ITALIC;
					fuente_total = new Font(fuente_nombre,
							valor_negrita + valor_cursiva, fuente_puntos);
					ventana_actual.textarea_lineas.setFont(fuente_total);
					ventana_actual.textarea_previo.setFont(fuente_total);
				} else
				{
					valor_cursiva = Font.PLAIN;
					fuente_total = new Font(fuente_nombre,
							valor_negrita + valor_cursiva, fuente_puntos);
					ventana_actual.textarea_lineas.setFont(fuente_total);
					ventana_actual.textarea_previo.setFont(fuente_total);
				}
			}
		}
	}

}

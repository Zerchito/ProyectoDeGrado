package InterfazBase;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.border.*;

import ModuloBase.*;

@SuppressWarnings("serial")
public class PanelFuenteTexto extends JPanel implements ActionListener
{
	// Elementos Frame
	public JLabel label_muestra;
	@SuppressWarnings("rawtypes")
	public JComboBox combo_muestra;

	public JLabel label_puntos;
	public JTextField textfield_puntos;

	public JLabel label_estilo;
	public JTextField textfield_estilo;

	public JSlider slider_puntos;

	public JCheckBox check_negrita;
	public JCheckBox check_cursiva;

	// Variables
	FuncionBase funcion_base;
	Font fuente_total;
	String fuente_nombre = "Arial";
	public int fuente_puntos = 12;
	public int valor_negrita = 0;
	public int valor_cursiva = 0;

	@SuppressWarnings(
	{ "rawtypes", "unchecked" })
	public PanelFuenteTexto(FuncionBase v)
	{
		funcion_base = v;

		setBounds(0, 0, 200, 70);
		setLayout(null);
		setVisible(true);
		setBorder(new BevelBorder(BevelBorder.RAISED));

		label_muestra = new JLabel("Muestra");
		label_muestra.setFont(new Font("ArialBlack", Font.BOLD, 12));
		label_muestra.setBorder(new EtchedBorder());
		label_muestra.setBounds(5, 5, 50, 20);
		add(label_muestra);

		combo_muestra = new JComboBox();
		combo_muestra.setBackground(Color.white);
		combo_muestra.setFont(new Font("Dialog", Font.PLAIN, 12));
		combo_muestra.setBounds(55, 5, 140, 20);
		add(combo_muestra);

		combo_muestra.addItem("Arial");
		combo_muestra.addItem("Calibri");
		combo_muestra.addItem("Cambria");
		combo_muestra.addItem("Consolas");
		combo_muestra.addItem("Constantia");
		combo_muestra.addItem("Corbel");
		combo_muestra.addItem("Courier");
		combo_muestra.addItem("Dialog");
		combo_muestra.addItem("Fixedsys");
		combo_muestra.addItem("Garamond");
		combo_muestra.addItem("Georgia");
		combo_muestra.addItem("Impact");
		combo_muestra.addItem("Monotype Corsiva");
		combo_muestra.addItem("System");
		combo_muestra.addItem("Verdana");

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

		combo_muestra.addActionListener(this);
		check_negrita.addActionListener(this);
		check_cursiva.addActionListener(this);

		slider_puntos.setEnabled(false);

		slider_puntos.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent event)
			{
				JSlider source = (JSlider) event.getSource();
				textfield_puntos.setText("" + source.getValue());
				fuente_puntos = source.getValue();

				fuente_total = new Font(fuente_nombre,
						valor_negrita + valor_cursiva, fuente_puntos);
				funcion_base.widget_actual.setFont(fuente_total);

			}
		});

	}

	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == combo_muestra)
		{
			fuente_nombre = "" + combo_muestra.getSelectedItem();
			fuente_total = new Font(fuente_nombre,
					valor_negrita + valor_cursiva, fuente_puntos);
			funcion_base.widget_actual.setFont(fuente_total);
		}

		if (ae.getSource() == check_negrita)
		{
			if (check_negrita.isSelected())
			{
				valor_negrita = Font.BOLD;
				fuente_total = new Font(fuente_nombre,
						valor_negrita + valor_cursiva, fuente_puntos);
				funcion_base.widget_actual.setFont(fuente_total);
			} else
			{
				valor_negrita = Font.PLAIN;
				fuente_total = new Font(fuente_nombre,
						valor_negrita + valor_cursiva, fuente_puntos);
				funcion_base.widget_actual.setFont(fuente_total);
			}
		}

		if (ae.getSource() == check_cursiva)
		{
			if (check_cursiva.isSelected())
			{
				valor_cursiva = Font.ITALIC;
				fuente_total = new Font(fuente_nombre,
						valor_negrita + valor_cursiva, fuente_puntos);
				funcion_base.widget_actual.setFont(fuente_total);
			} else
			{
				valor_cursiva = Font.PLAIN;
				fuente_total = new Font(fuente_nombre,
						valor_negrita + valor_cursiva, fuente_puntos);
				funcion_base.widget_actual.setFont(fuente_total);
			}
		}
	}

}

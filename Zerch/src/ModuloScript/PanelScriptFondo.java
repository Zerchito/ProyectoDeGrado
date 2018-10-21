package ModuloScript;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

import ModuloBase.*;

public class PanelScriptFondo extends JPanel
{
	// Elementos Frame
	public JLabel label_muestra;
	public JPanel textfield_muestra;

	public JLabel label_red;
	public JTextField textfield_red;

	public JLabel label_green;
	public JTextField textfield_green;

	public JLabel label_blue;
	public JTextField textfield_blue;

	public JSlider slider_r;
	public JSlider slider_g;
	public JSlider slider_b;

	// Varialbes
	VentanaScript ventana_actual;

	public PanelScriptFondo(VentanaScript v)
	{
		// funcion_principal=v;
		ventana_actual = v;

		setBounds(0, 0, 200, 90);
		setLayout(null);
		setVisible(true);
		setBorder(new BevelBorder(BevelBorder.RAISED));

		label_muestra = new JLabel("Muestra");
		label_muestra.setFont(new Font("ArialBlack", Font.BOLD, 12));
		label_muestra.setBorder(new EtchedBorder());
		label_muestra.setBounds(5, 5, 85, 20);
		add(label_muestra);

		textfield_muestra = new JPanel();
		textfield_muestra.setBorder(new BevelBorder(BevelBorder.RAISED));
		textfield_muestra.setBackground(Color.white);
		textfield_muestra.setBounds(90, 5, 105, 20);
		add(textfield_muestra);

		label_red = new JLabel("Rojo:");
		label_red.setBorder(new EtchedBorder());
		label_red.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_red.setBounds(5, 25, 45, 20);
		add(label_red);

		textfield_red = new JTextField("...");
		textfield_red.setEditable(false);
		textfield_red.setBackground(Color.white);
		textfield_red.setBounds(50, 25, 40, 20);
		textfield_red.setBorder(new EtchedBorder());
		add(textfield_red);

		label_green = new JLabel("Verde:");
		label_green.setBorder(new EtchedBorder());
		label_green.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_green.setBounds(5, 45, 45, 20);
		add(label_green);

		textfield_green = new JTextField("...");
		textfield_green.setEditable(false);
		textfield_green.setBackground(Color.white);
		textfield_green.setBounds(50, 45, 40, 20);
		textfield_green.setBorder(new EtchedBorder());
		add(textfield_green);

		label_blue = new JLabel("Azul:");
		label_blue.setBorder(new EtchedBorder());
		label_blue.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_blue.setBounds(5, 65, 45, 20);
		add(label_blue);

		textfield_blue = new JTextField("...");
		textfield_blue.setEditable(false);
		textfield_blue.setBackground(Color.white);
		textfield_blue.setBounds(50, 65, 40, 20);
		textfield_blue.setBorder(new EtchedBorder());
		add(textfield_blue);

		slider_r = new JSlider();
		slider_r.setBounds(90, 25, 105, 20);
		slider_r.setMaximum(255);
		slider_r.setValue(255);
		add(slider_r);

		slider_g = new JSlider();
		slider_g.setBounds(90, 45, 105, 20);
		slider_g.setMaximum(255);
		slider_g.setValue(255);
		add(slider_g);

		slider_b = new JSlider();
		slider_b.setBounds(90, 65, 105, 20);
		slider_b.setMaximum(255);
		slider_b.setValue(255);
		add(slider_b);

		slider_r.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent event)
			{
				JSlider source = (JSlider) event.getSource();
				textfield_red.setText("" + source.getValue());
				ventana_actual.textarea_lineas
						.setBackground(new Color(slider_r.getValue(),
								slider_g.getValue(), slider_b.getValue()));
				ventana_actual.textarea_previo
						.setBackground(new Color(slider_r.getValue(),
								slider_g.getValue(), slider_b.getValue()));
				textfield_muestra.setBackground(new Color(slider_r.getValue(),
						slider_g.getValue(), slider_b.getValue()));

			}
		});

		slider_g.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent event)
			{
				JSlider source = (JSlider) event.getSource();
				textfield_green.setText("" + source.getValue());
				ventana_actual.textarea_lineas
						.setBackground(new Color(slider_r.getValue(),
								slider_g.getValue(), slider_b.getValue()));
				ventana_actual.textarea_previo
						.setBackground(new Color(slider_r.getValue(),
								slider_g.getValue(), slider_b.getValue()));
				textfield_muestra.setBackground(new Color(slider_r.getValue(),
						slider_g.getValue(), slider_b.getValue()));

			}
		});

		slider_b.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent event)
			{
				JSlider source = (JSlider) event.getSource();
				textfield_blue.setText("" + source.getValue());
				ventana_actual.textarea_lineas
						.setBackground(new Color(slider_r.getValue(),
								slider_g.getValue(), slider_b.getValue()));
				ventana_actual.textarea_previo
						.setBackground(new Color(slider_r.getValue(),
								slider_g.getValue(), slider_b.getValue()));
				textfield_muestra.setBackground(new Color(slider_r.getValue(),
						slider_g.getValue(), slider_b.getValue()));

			}
		});

	}

}

package ModuloWizard;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class PanelDimension extends JPanel implements ActionListener
{
	JLabel label_propiedades_ventana;
	JRadioButton radio_pordefecto;
	JLabel label_ancho;
	JLabel label_alto;
	JTextField textfield_ancho;
	JTextField textfield_alto;
	JLabel label_personalizado;
	JRadioButton radio_personalizado;
	JLabel label_defecto;
	JComboBox combo_defecto;

	String ancho = "100";
	String alto = "100";

	public PanelDimension()
	{
		setLayout(null);

		label_propiedades_ventana = new JLabel("Propiedades Ventana");
		label_propiedades_ventana.setBounds(5, 5, 150, 20);
		label_propiedades_ventana.setBorder(new EtchedBorder());
		add(label_propiedades_ventana);

		radio_pordefecto = new JRadioButton("Por Defecto");
		radio_pordefecto.setBounds(5, 25, 120, 20);
		add(radio_pordefecto);

		label_defecto = new JLabel("Dimension:");
		label_defecto.setBounds(5, 45, 80, 20);
		label_defecto.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_defecto.setBorder(new EtchedBorder());
		add(label_defecto);

		combo_defecto = new JComboBox();
		combo_defecto.setFont(new Font("Dialog", Font.PLAIN, 12));
		combo_defecto.setBounds(85, 45, 100, 20);
		combo_defecto.setBackground(Color.white);
		add(combo_defecto);

		combo_defecto.addItem("400x200");
		combo_defecto.addItem("600x400");
		combo_defecto.addItem("800x600");
		combo_defecto.addItem("1000x800");
		combo_defecto.addItem("1200x1000");
		combo_defecto.setEnabled(false);

		radio_personalizado = new JRadioButton("Personalizado");
		radio_personalizado.setBounds(5, 85, 120, 20);
		add(radio_personalizado);

		label_ancho = new JLabel("Ancho:");
		label_ancho.setBounds(5, 105, 50, 20);
		label_ancho.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_ancho.setBorder(new EtchedBorder());
		add(label_ancho);

		textfield_ancho = new JTextField(10);
		textfield_ancho.setText("100");
		textfield_ancho.setBounds(55, 105, 50, 20);
		textfield_ancho.setEnabled(false);
		textfield_ancho.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		add(textfield_ancho);

		label_alto = new JLabel("Alto:");
		label_alto.setBounds(5, 125, 50, 20);
		label_alto.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_alto.setBorder(new EtchedBorder());
		add(label_alto);

		textfield_alto = new JTextField(10);
		textfield_alto.setText("100");
		textfield_alto.setBounds(55, 125, 50, 20);
		textfield_alto.setEnabled(false);
		textfield_alto.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		add(textfield_alto);

		radio_pordefecto.addActionListener(this);
		radio_personalizado.addActionListener(this);
		combo_defecto.addActionListener(this);

		ancho = textfield_ancho.getText().toString();
		alto = textfield_alto.getText().toString();

	}

	public void actionPerformed(ActionEvent ae)
	{

		if (ae.getSource() == combo_defecto)
		{

		}

		if (ae.getSource() == radio_pordefecto)
		{
			if (radio_pordefecto.isSelected())
			{
				combo_defecto.setEnabled(true);

			} else if (!radio_pordefecto.isSelected())
			{
				combo_defecto.setEnabled(false);
			}
			radio_personalizado.setSelected(false);
			textfield_ancho.setEnabled(false);
			textfield_alto.setEnabled(false);
		}

		if (ae.getSource() == radio_personalizado)
		{
			if (radio_personalizado.isSelected())
			{
				textfield_ancho.setEnabled(true);
				textfield_alto.setEnabled(true);
			} else if (!radio_personalizado.isSelected())
			{
				textfield_ancho.setEnabled(false);
				textfield_alto.setEnabled(false);
			}
			radio_pordefecto.setSelected(false);
			combo_defecto.setEnabled(false);
		}

	}

	public String traducirAncho(String s)
	{
		String retorno = "";
		StringBuffer ancho = new StringBuffer();
		for (int a = 0; a < s.length(); a++)
		{
			if ((s.charAt(a) == 'x') || (s.charAt(a) == 'X'))
			{
				break;
			} else
			{
				ancho.append(s.charAt(a));
			}
		}

		return ancho.toString();
	}

	public String traducirAlto(String s)
	{
		String retorno = "";
		StringBuffer alto = new StringBuffer();
		for (int a = 0; a < s.length(); a++)
		{
			if ((s.charAt(a) == 'x') || (s.charAt(a) == 'X'))
			{
				retorno = altoPartedos(s, a).toString();
			}

		}
		return retorno;
	}

	public StringBuffer altoPartedos(String s, int v)
	{
		StringBuffer retorno = new StringBuffer();
		for (int b = v + 1; b < s.length(); b++)
		{
			retorno.append(s.charAt(b));
		}
		return retorno;
	}

	public void actualizar()
	{
		if (radio_pordefecto.isSelected() == false)
		{
			ancho = textfield_ancho.getText().toString();
			alto = textfield_alto.getText().toString();
		} else
		{
			ancho = traducirAncho(combo_defecto.getSelectedItem().toString());
			alto = traducirAlto(combo_defecto.getSelectedItem().toString());
		}

	}

}

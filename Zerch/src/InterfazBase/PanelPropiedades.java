package InterfazBase;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.*;
import ModuloBase.*;
import SwingWidget.*;

@SuppressWarnings(
{ "serial", "rawtypes", "unchecked" })
public class PanelPropiedades extends JPanel implements ActionListener
{
	// Elementos del Frame
	public JLabel label_propiedades;

	public JLabel label_nombre;
	public JLabel label_nombre_objeto;
	public JTextField textfield_nombre;

	public JLabel label_size;
	public JLabel label_size_w;
	public JTextField textfield_size_w;
	public JLabel label_size_h;
	public JTextField textfield_size_h;

	public JLabel label_location;
	public JLabel label_location_x;
	public JLabel label_location_y;

	public JTextField textfield_posx;
	public JTextField textfield_posy;

	public JLabel label_borde;
	public JComboBox combo_borde;

	public JLabel label_argumento;
	public JTextField textfield_argumento;

	// Variables
	FuncionBase funcion_base;

	JComponent c_actual;

	public Vector bordes;

	public PanelPropiedades(FuncionBase v)
	{
		funcion_base = v;

		setBounds(0, 0, 200, 130);
		setLayout(null);
		setBorder(new BevelBorder(BevelBorder.RAISED));

		label_propiedades = new JLabel("Propiedades:");
		label_propiedades.setFont(new Font("ArialBlack", Font.BOLD, 12));
		label_propiedades.setBorder(new EtchedBorder());
		label_propiedades.setBounds(5, 5, 95, 20);
		add(label_propiedades);

		label_nombre = new JLabel("Texto:");
		label_nombre.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_nombre.setBounds(5, 25, 75, 20);
		label_nombre.setBorder(new EtchedBorder());
		add(label_nombre);

		label_nombre_objeto = new JLabel();
		label_nombre_objeto.setText("T");
		label_nombre_objeto.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_nombre_objeto.setBounds(80, 25, 20, 20);
		label_nombre_objeto.setBorder(new EtchedBorder());
		add(label_nombre_objeto);

		textfield_nombre = new JTextField();
		textfield_nombre.setText("...");
		textfield_nombre.setEditable(true);
		textfield_nombre.setBackground(Color.white);
		textfield_nombre.setBounds(100, 25, 90, 20);
		textfield_nombre.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		add(textfield_nombre);

		label_size = new JLabel("Dimension:");
		label_size.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_size.setBounds(5, 45, 75, 20);
		label_size.setBorder(new EtchedBorder());
		add(label_size);

		label_size_w = new JLabel("W");
		label_size_w.setBorder(new EtchedBorder());
		label_size_w.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_size_w.setBounds(80, 45, 20, 20);
		add(label_size_w);

		textfield_size_w = new JTextField("...");
		textfield_size_w.setEditable(true);
		textfield_size_w.setBackground(Color.white);
		textfield_size_w.setBounds(100, 45, 35, 20);
		textfield_size_w.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		add(textfield_size_w);

		label_size_h = new JLabel("H");
		label_size_h.setBounds(135, 45, 20, 20);
		label_size_h.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_size_h.setBorder(new EtchedBorder());
		add(label_size_h);

		textfield_size_h = new JTextField("...");
		textfield_size_h.setEditable(true);
		textfield_size_h.setBackground(Color.white);
		textfield_size_h.setBounds(155, 45, 35, 20);
		textfield_size_h.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		add(textfield_size_h);

		label_location = new JLabel("Posicion:");
		label_location.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_location.setBounds(5, 65, 75, 20);
		label_location.setBorder(new EtchedBorder());
		add(label_location);

		label_location_x = new JLabel("X");
		label_location_x.setBounds(80, 65, 20, 20);
		label_location_x.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_location_x.setBorder(new EtchedBorder());
		add(label_location_x);

		textfield_posx = new JTextField("...");
		textfield_posx.setEditable(true);
		textfield_posx.setBackground(Color.white);
		textfield_posx.setBounds(100, 65, 35, 20);
		textfield_posx.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		add(textfield_posx);

		label_location_y = new JLabel("Y");
		label_location_y.setBounds(135, 65, 20, 20);
		label_location_y.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_location_y.setBorder(new EtchedBorder());
		add(label_location_y);

		textfield_posy = new JTextField("...");
		textfield_posy.setEditable(true);
		textfield_posy.setBackground(Color.white);
		textfield_posy.setBounds(155, 65, 35, 20);
		textfield_posy.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		add(textfield_posy);

		label_borde = new JLabel("Borde Tipo:");
		label_borde.setBounds(5, 85, 75, 20);
		label_borde.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_borde.setBorder(new EtchedBorder());
		add(label_borde);

		combo_borde = new JComboBox();
		combo_borde.addItem("Null");
		combo_borde.addItem("Etched");
		combo_borde.addItem("Raised");
		combo_borde.addItem("Lowered");

		bordes = new Vector();
		Border bnulo = null;
		bordes.add(bnulo);
		Border betched = new EtchedBorder();
		bordes.add(betched);
		Border braised = new BevelBorder(BevelBorder.RAISED);
		bordes.add(braised);
		Border blowered = new SoftBevelBorder(BevelBorder.LOWERED);
		bordes.add(blowered);

		combo_borde.setBounds(80, 85, 110, 20);
		combo_borde.setBackground(Color.white);
		combo_borde.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(combo_borde);

		label_argumento = new JLabel("Argumentos:");
		label_argumento.setBounds(5, 105, 75, 20);
		label_argumento.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_argumento.setBorder(new EtchedBorder());
		add(label_argumento);

		textfield_argumento = new JTextField("...");
		textfield_argumento.setBounds(80, 105, 110, 20);
		textfield_argumento.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		add(textfield_argumento);

		textfield_nombre.addActionListener(this);
		textfield_size_w.addActionListener(this);
		textfield_size_h.addActionListener(this);
		textfield_posx.addActionListener(this);
		textfield_posy.addActionListener(this);
		combo_borde.addActionListener(this);
		textfield_argumento.addActionListener(this);

	}

	public void actionPerformed(ActionEvent ae)
	{

		if (funcion_base.widget_actual != null && !funcion_base.widget_actual
				.getClass().getName().endsWith("VentanaInterna"))
		{

			if (ae.getSource() == textfield_nombre
					|| ae.getSource() == textfield_size_w
					|| ae.getSource() == textfield_size_h
					|| ae.getSource() == textfield_posx
					|| ae.getSource() == textfield_posy
					|| ae.getSource() == combo_borde
					|| ae.getSource() == textfield_argumento)
			{

				c_actual = funcion_base.widget_actual;
				c_actual.setLayout(null);

				if (funcion_base.widget_actual.getClass().getName()
						.endsWith("Plantilla"))
				{
					Plantilla b = (Plantilla) c_actual;
					Border bor = (Border) bordes
							.elementAt(combo_borde.getSelectedIndex());
					b.setBorder(bor);

				}

				if (funcion_base.widget_actual.getClass().getName()
						.endsWith("SPanel"))
				{
					SPanel b = (SPanel) c_actual;
					Border bor = (Border) bordes
							.elementAt(combo_borde.getSelectedIndex());
					b.setBorder(bor);

				}

				else if (funcion_base.widget_actual.getClass().getName()
						.endsWith("SButton"))
				{
					SButton b = (SButton) c_actual;
					b.setText(textfield_nombre.getText());

				}

				else if (funcion_base.widget_actual.getClass().getName()
						.endsWith("SRadioButton"))
				{
					SRadioButton b = (SRadioButton) c_actual;
					b.setText(textfield_nombre.getText());

				}

				else if (funcion_base.widget_actual.getClass().getName()
						.endsWith("SCheckBox"))
				{
					SCheckBox b = (SCheckBox) c_actual;
					b.setText(textfield_nombre.getText());

				}

				else if (funcion_base.widget_actual.getClass().getName()
						.endsWith("STextField"))
				{
					STextField b = (STextField) c_actual;
					b.setText(textfield_nombre.getText());
					Border bor = (Border) bordes
							.elementAt(combo_borde.getSelectedIndex());
					b.setBorder(bor);

				}

				else if (funcion_base.widget_actual.getClass().getName()
						.endsWith("SPasswordField"))
				{
					SPasswordField b = (SPasswordField) c_actual;
					b.setText(textfield_nombre.getText());
					Border bor = (Border) bordes
							.elementAt(combo_borde.getSelectedIndex());
					b.setBorder(bor);

				}

				else if (funcion_base.widget_actual.getClass().getName()
						.endsWith("STextArea"))
				{
					STextArea b = (STextArea) c_actual;
					b.setText(textfield_nombre.getText());
					Border bor = (Border) bordes
							.elementAt(combo_borde.getSelectedIndex());
					b.setBorder(bor);

				}

				else if (funcion_base.widget_actual.getClass().getName()
						.endsWith("SList"))
				{
					SList b = (SList) c_actual;
					b.setVector(funcion_base.ventana_base.separa_argumento
							.separarArgumento(textfield_argumento.getText()));
					funcion_base.ventana_base.separa_argumento.v_buffer = new Vector();
					Border bor = (Border) bordes
							.elementAt(combo_borde.getSelectedIndex());
					b.setBorder(bor);

				}

				else if (funcion_base.widget_actual.getClass().getName()
						.endsWith("SComboBox"))
				{
					SComboBox b = (SComboBox) c_actual;
					b.setVector(funcion_base.ventana_base.separa_argumento
							.separarArgumento(textfield_argumento.getText()));
					funcion_base.ventana_base.separa_argumento.v_buffer = new Vector();
					Border bor = (Border) bordes
							.elementAt(combo_borde.getSelectedIndex());
					b.setBorder(bor);

				}

				if (funcion_base.widget_actual.getClass().getName()
						.endsWith("SLabel"))
				{
					SLabel b = (SLabel) c_actual;
					b.setText(textfield_nombre.getText());
					Border bor = (Border) bordes
							.elementAt(combo_borde.getSelectedIndex());
					b.setBorder(bor);

				}

				else if (funcion_base.widget_actual.getClass().getName()
						.endsWith("SSlider"))
				{
					SSlider b = (SSlider) c_actual;
					Border bor = (Border) bordes
							.elementAt(combo_borde.getSelectedIndex());
					b.setBorder(bor);

				}

				else if (funcion_base.widget_actual.getClass().getName()
						.endsWith("SProgressBar"))
				{
					SProgressBar b = (SProgressBar) c_actual;
					Border bor = (Border) bordes
							.elementAt(combo_borde.getSelectedIndex());
					b.setBorder(bor);

				}

				Point ploc_previo = c_actual.getLocation();
				int locx_previo = (int) ploc_previo.getX();
				int locy_previo = (int) ploc_previo.getY();

				String string_locx = textfield_posx.getText();
				String string_locy = textfield_posy.getText();
				String string_sizew = textfield_size_w.getText();
				String string_sizeh = textfield_size_h.getText();

				int location_x = locx_previo;
				int location_y = locy_previo;
				int size_w = c_actual.getWidth();
				int size_h = c_actual.getHeight();

				if (digitoPositivo(string_locx) == true)
				{
					location_x = Integer
							.parseInt(textfield_posx.getText().toString());
				} else
				{
					mostrarError("caracter_x", string_locx);
					location_x = locx_previo;
				}

				if (digitoPositivo(string_locy) == true)
				{
					location_y = Integer
							.parseInt(textfield_posy.getText().toString());
				} else
				{
					mostrarError("caracter_y", string_locy);
					location_y = locy_previo;
				}

				if (digitoPositivo(string_sizew) == true)
				{
					size_w = Integer
							.parseInt(textfield_size_w.getText().toString());
					if (size_w < 5)
					{
						mostrarError("valor_w", string_sizew);
						size_w = c_actual.getWidth();

					}
				} else
				{
					mostrarError("caracter_w", string_sizew);
				}

				if (digitoPositivo(string_sizeh) == true)
				{
					size_h = Integer
							.parseInt(textfield_size_h.getText().toString());
					if (size_h < 5)
					{
						mostrarError("valor_h", string_sizew);
						size_h = c_actual.getHeight();

					}
				} else
				{
					mostrarError("caracter_h", string_sizeh);
				}

				c_actual.setLocation(location_x, location_y);
				c_actual.setSize(size_w, size_h);
				int color_r = funcion_base.ventana_base.color_selector.slider_r
						.getValue();
				int color_g = funcion_base.ventana_base.color_selector.slider_g
						.getValue();
				int color_b = funcion_base.ventana_base.color_selector.slider_b
						.getValue();
				c_actual.setBackground(new Color(color_r, color_g, color_b));

				c_actual.setVisible(true);

			}
		}
	}

	public void mostrarError(String s, String arg)
	{
		if (s.endsWith("caracter_x"))
		{
			JOptionPane.showMessageDialog(null,
					"Error de caracter en location X='" + arg
							+ "'.\nPor favor ingrese digitos mayores a '0' ",
					"ERROR: Caracter no valido",
					javax.swing.JOptionPane.ERROR_MESSAGE);
		}
		if (s.endsWith("caracter_y"))
		{
			JOptionPane.showMessageDialog(null,
					"Error de caracter en location Y='" + arg
							+ "'.\nPor favor ingrese digitos mayores a '0' ",
					"ERROR: Caracter no valido",
					javax.swing.JOptionPane.ERROR_MESSAGE);
		}
		if (s.endsWith("caracter_w"))
		{
			JOptionPane.showMessageDialog(null,
					"Error de caracter en size W='" + arg
							+ "'.\nPor favor ingrese digitos mayores a '4' ",
					"ERROR: Caracter no valido",
					javax.swing.JOptionPane.ERROR_MESSAGE);
		}
		if (s.endsWith("valor_w"))
		{
			JOptionPane.showMessageDialog(null,
					"Error de valor en size W='" + arg
							+ "'.\nPor favor ingrese digitos mayores a '4' ",
					"ERROR: Valor fuera de rango",
					javax.swing.JOptionPane.ERROR_MESSAGE);
		}
		if (s.endsWith("caracter_h"))
		{
			JOptionPane.showMessageDialog(null,
					"Error de caracter en size H='" + arg
							+ "'.\nPor favor ingrese digitos mayores a '4' ",
					"ERROR: Caracter no valido",
					javax.swing.JOptionPane.ERROR_MESSAGE);
		}
		if (s.endsWith("valor_h"))
		{
			JOptionPane.showMessageDialog(null,
					"Error de valor en size H='" + arg
							+ "'.\nPor favor ingrese digitos mayores a '4' ",
					"ERROR: Valor fuera de rango",
					javax.swing.JOptionPane.ERROR_MESSAGE);
		}
	}

	public boolean digitoPositivo(String s)
	{
		char c;
		boolean retorno = false;

		for (int a = 0; a < s.length(); a++)
		{
			c = s.charAt(a);
			if (Character.isDigit(c))
			{
				retorno = true;
				continue;
			} else
			{
				retorno = false;
				break;
			}
		}

		return retorno;
	}
}

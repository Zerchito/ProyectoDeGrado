package InterfazBase;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.*;
import ModuloBase.*;
import SwingWidget.*;

@SuppressWarnings("serial")
public class PanelEventos extends JPanel implements ActionListener
{
	// Elementos del Frame
	public JLabel label_eventos;
	public JButton boton_estado;
	public JLabel label_selecto;
	public JLabel label_objeto;
	public JLabel label_origen;
	public JLabel label_textfield_origen;
	public JLabel label_destino;
	public JTextField textfield_destino;

	// Variables
	FuncionBase funcion_base;

	JComponent c_actual;

	public Vector<Object> bordes;

	public PanelEventos(FuncionBase v)
	{
		funcion_base = v;

		setBounds(0, 0, 200, 80);
		setLayout(null);
		setBorder(new BevelBorder(BevelBorder.RAISED));

		label_eventos = new JLabel("Eventos:");
		label_eventos.setFont(new Font("ArialBlack", Font.BOLD, 12));
		label_eventos.setBorder(new EtchedBorder());
		label_eventos.setBounds(5, 5, 55, 20);
		add(label_eventos);

		boton_estado = new JButton("Inactivo");
		boton_estado.setFont(new Font("ArialBlack", Font.BOLD, 12));
		boton_estado.setBackground(Color.black);
		boton_estado.setForeground(Color.red);
		boton_estado.setBounds(60, 5, 125, 20);
		add(boton_estado);

		label_selecto = new JLabel("Selecto:");
		label_selecto.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_selecto.setBounds(5, 25, 55, 20);
		label_selecto.setBorder(new EtchedBorder());
		add(label_selecto);

		label_objeto = new JLabel("...");
		label_objeto.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_objeto.setBounds(60, 25, 125, 20);
		label_objeto.setBorder(new EtchedBorder());
		add(label_objeto);

		label_origen = new JLabel("Origen:");
		label_origen.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_origen.setBounds(5, 45, 55, 20);
		label_origen.setBorder(new EtchedBorder());
		add(label_origen);

		label_textfield_origen = new JLabel("...");
		label_textfield_origen.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_textfield_origen.setBounds(60, 45, 35, 20);
		label_textfield_origen.setBorder(new EtchedBorder());
		// label_textfield_origen.setEditable(false);
		add(label_textfield_origen);

		label_destino = new JLabel("Destino:");
		label_destino.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_destino.setBounds(95, 45, 55, 20);
		label_destino.setBorder(new EtchedBorder());
		add(label_destino);

		textfield_destino = new JTextField("...");
		textfield_destino.setFont(new Font("Dialog", Font.PLAIN, 12));
		textfield_destino.setBounds(150, 45, 35, 20);
		textfield_destino.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		textfield_destino.setEnabled(false);
		add(textfield_destino);

		textfield_destino.addActionListener(this);
		boton_estado.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae)
	{
		String texto_destino = "";
		int valor_destino = 0;
		if (funcion_base.widget_actual != null && !funcion_base.widget_actual
				.getClass().getName().endsWith("VentanaInterna"))
		{

			if (ae.getSource() == textfield_destino)
			{
				c_actual = funcion_base.widget_actual;
				c_actual.setLayout(null);
				texto_destino = textfield_destino.getText().toString();

				if (funcion_base.widget_actual.getClass().getName()
						.endsWith("SButton"))
				{
					SButton b = (SButton) c_actual;
					if (digitoPositivo(texto_destino) == true)
					{
						valor_destino = Integer.parseInt(
								textfield_destino.getText().toString());
						if (valor_destino > funcion_base.vector_ranuras.size()
								- 1)
						{
							b.setDestino(b.getDestino());
							mostrarError("valor", texto_destino);
						} else if (valor_destino == b.getOrigen())
						{
							mostrarError("autoreferido", texto_destino);
						} else
						{
							b.setDestino(Integer.parseInt(
									textfield_destino.getText().toString()));
						}

					} else
					{
						mostrarError("caracter", texto_destino);
						b.setDestino(b.getDestino());
					}

				}
				if (funcion_base.widget_actual.getClass().getName()
						.endsWith("SRadioButton"))
				{
					SRadioButton b = (SRadioButton) c_actual;
					if (digitoPositivo(texto_destino) == true)
					{
						valor_destino = Integer.parseInt(
								textfield_destino.getText().toString());
						if (valor_destino > funcion_base.vector_ranuras.size()
								- 1)
						{
							b.setDestino(b.getDestino());
							mostrarError("valor", texto_destino);
						} else
						{
							b.setDestino(Integer.parseInt(
									textfield_destino.getText().toString()));
						}

					} else
					{
						mostrarError("caracter", texto_destino);
						b.setDestino(b.getDestino());
					}
				}
				if (funcion_base.widget_actual.getClass().getName()
						.endsWith("SCheckBox"))
				{
					SCheckBox b = (SCheckBox) c_actual;
					if (digitoPositivo(texto_destino) == true)
					{
						valor_destino = Integer.parseInt(
								textfield_destino.getText().toString());
						if (valor_destino > funcion_base.vector_ranuras.size()
								- 1)
						{
							b.setDestino(b.getDestino());
							mostrarError("valor", texto_destino);
						} else
						{
							b.setDestino(Integer.parseInt(
									textfield_destino.getText().toString()));
						}

					} else
					{
						mostrarError("caracter", texto_destino);
						b.setDestino(b.getDestino());
					}
				}

			}

		}

		if (ae.getSource() == boton_estado)
		{
			if (funcion_base.modalidad == 0)
			{
				funcion_base.iniciarEventos();
			} else if (funcion_base.modalidad == 1)
			{
				funcion_base.detenerEventos();
			}
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

	public void mostrarError(String s, String arg)
	{
		if (s.endsWith("caracter"))
		{
			JOptionPane.showMessageDialog(null,
					"Error de caracter en Destino='" + arg
							+ "'.\nPor favor ingrese digitos mayores a '0' ",
					"ERROR: Caracter no valido",
					javax.swing.JOptionPane.ERROR_MESSAGE);
		}
		if (s.endsWith("valor"))
		{
			JOptionPane.showMessageDialog(null,
					"Error de valor en Destino='" + arg
							+ "'.\nPor favor ingrese digitos entre el rango de ranuras creadas ",
					"ERROR: Valor fuera de rango",
					javax.swing.JOptionPane.ERROR_MESSAGE);
		}
		if (s.endsWith("autoreferido"))
		{
			JOptionPane.showMessageDialog(null,
					"Error de valor en Destino='" + arg
							+ "'.\nNo puede ser autoreferido \nPor favor ingrese digitos entre el rango de ranuras creadas ",
					"ERROR: Valor fuera de rango",
					javax.swing.JOptionPane.ERROR_MESSAGE);
		}
	}
}

package ModuloWizard;

import java.awt.*;
import javax.swing.*;
import java.lang.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.event.*;
import javax.swing.border.*;

public class ValidadorWizard
{

	public ValidadorWizard()
	{

	}

	public boolean validarDimension(String an, String al)
	{
		boolean retorno = false;

		if (digitoPositivo(an))
		{
			if (digitoPositivo(al))
			{

				if (Integer.parseInt(an) < 100)
				{
					mostrarError("valor ancho", "" + an);
					retorno = false;
				} else if (Integer.parseInt(al) < 100)
				{
					mostrarError("valor alto", "" + al);
					retorno = false;
				} else
				{
					retorno = true;
				}
			} else
			{
				mostrarError("caracter alto", "" + al);
				retorno = false;
			}

		} else
		{
			mostrarError("caracter ancho", "" + an);
			retorno = false;
		}
		return retorno;
	}

	public boolean validarVentanas(String s)
	{
		boolean retorno = false;
		if (digitoPositivo(s))
		{
			if (Integer.parseInt(s) < 1)
			{
				mostrarError("valor cantidad", "" + s);
				retorno = false;
			} else
			{
				retorno = true;
			}
		} else
		{
			mostrarError("caracter cantidad", "" + s);
			retorno = false;
		}
		return retorno;
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
		JOptionPane jovalidar = new JOptionPane();

		if (s.equals("caracter ancho"))
		{
			jovalidar.showMessageDialog(null,
					"Error de caracter en Ancho='" + arg
							+ "'.\nPor favor ingrese digitos mayores a '100' ",
					"ERROR: Caracter no valido",
					javax.swing.JOptionPane.ERROR_MESSAGE);
		}
		if (s.equals("valor ancho"))
		{
			jovalidar.showMessageDialog(null,
					"Error de valor en Ancho='" + arg
							+ "'.\nPor favor ingrese digitos mayores a '100' ",
					"ERROR: Valor fuera de rango",
					javax.swing.JOptionPane.ERROR_MESSAGE);
		}
		if (s.equals("caracter alto"))
		{
			jovalidar.showMessageDialog(null,
					"Error de caracter en Alto='" + arg
							+ "'.\nPor favor ingrese digitos mayores a '100' ",
					"ERROR: Caracter no valido",
					javax.swing.JOptionPane.ERROR_MESSAGE);
		}
		if (s.equals("valor alto"))
		{
			jovalidar.showMessageDialog(null,
					"Error de valor en Alto='" + arg
							+ "'.\nPor favor ingrese digitos mayores a '100' ",
					"ERROR: Valor fuera de rango",
					javax.swing.JOptionPane.ERROR_MESSAGE);
		}
		if (s.equals("caracter cantidad"))
		{
			jovalidar.showMessageDialog(null,
					"Error de caracter en Cantidad='" + arg
							+ "'.\nPor favor ingrese digitos mayores a '1' ",
					"ERROR: Caracter no valido",
					javax.swing.JOptionPane.ERROR_MESSAGE);
		}
		if (s.equals("valor cantidad"))
		{
			jovalidar.showMessageDialog(null,
					"Error de valor en Cantidad='" + arg
							+ "'.\nPor favor ingrese digitos mayores a '1' ",
					"ERROR: Valor fuera de rango",
					javax.swing.JOptionPane.ERROR_MESSAGE);
		}
	}
}

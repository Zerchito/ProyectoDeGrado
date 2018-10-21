package InterfazBase;

import java.util.*;

import Interfaz.VentanaPrincipal;
import SwingWidget.*;

@SuppressWarnings(
{ "serial", "rawtypes", "unchecked" })
public class SeparaArgumento implements java.io.Serializable
{
	Vector v_buffer = new Vector();
	StringBuffer sb = new StringBuffer();
	String string;
	VentanaPrincipal ventana_base;

	public SeparaArgumento(VentanaPrincipal ventanaPrincipal)
	{
		ventana_base = ventanaPrincipal;
	}

	public void aVector(StringBuffer s)
	{
		v_buffer.add(s);
		sb = new StringBuffer();
	}

	public String unirArgumento(SList sl)
	{
		String s = "";

		for (int a = 0; a < sl.getModel().getSize(); a++)
		{
			if (a < sl.getModel().getSize() - 1)
			{
				s = s + sl.getModel().getElementAt(a) + ",";
			} else
			{
				s = s + sl.getModel().getElementAt(a);
			}
		}
		return s;
	}

	public String unirArgumento(SComboBox sc)
	{
		String s = "";

		for (int a = 0; a < sc.getModel().getSize(); a++)
		{
			if (a < sc.getModel().getSize() - 1)
			{
				s = s + sc.getModel().getElementAt(a) + ",";
			} else
			{
				s = s + sc.getModel().getElementAt(a);
			}
		}
		return s;
	}

	public Vector separarArgumento(String s)
	{
		string = s;
		for (int a = 0; a < s.length(); a++)
		{
			sb.append(s.charAt(a));
			if (s.charAt(a) == ',')
			{
				sb.deleteCharAt(sb.length() - 1);
				aVector(sb);
				continue;
			} else if (a == s.length() - 1)
			{
				aVector(sb);
				continue;

			}
		}
		return v_buffer;
	}
}

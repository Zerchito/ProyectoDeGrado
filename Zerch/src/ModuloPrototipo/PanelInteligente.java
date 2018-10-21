package ModuloPrototipo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.border.*;

public class PanelInteligente extends JLabel implements java.io.Serializable
{

	boolean clickeado = false;

	PanelOpciones opciones_actual;

	public PanelInteligente(PanelOpciones p)
	{

		setBorder(new BevelBorder(BevelBorder.RAISED));

		opciones_actual = p;

		addMouseListener(new java.awt.event.MouseAdapter()
		{
			public void mouseClicked(java.awt.event.MouseEvent evt)
			{
				formMouseClicked(evt);
			}
		});

		addMouseListener(new java.awt.event.MouseAdapter()
		{
			public void mouseEntered(java.awt.event.MouseEvent evt)
			{
				formMouseEntered(evt);
			}
		});

		addMouseListener(new java.awt.event.MouseAdapter()
		{
			public void mouseExited(java.awt.event.MouseEvent evt)
			{
				formMouseExited(evt);
			}
		});

	}

	private void formMouseClicked(java.awt.event.MouseEvent evt)
	{
		setClickeado(true);
		opciones_actual.actualizarClickeado(this.getName());
	}

	private void formMouseEntered(java.awt.event.MouseEvent evt)
	{
		if (clickeado == false)
		{
			this.setBorder(new EtchedBorder());
		}
	}

	private void formMouseExited(java.awt.event.MouseEvent evt)
	{
		if (clickeado == false)
		{
			this.setBorder(null);
		}
	}

	public void setClickeado(boolean v)
	{
		clickeado = v;
	}

	public void fijarIcono(Icon icono)
	{

		this.setIcon(icono);

	}
}

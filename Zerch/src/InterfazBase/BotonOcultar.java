package InterfazBase;

import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class BotonOcultar extends JLabel implements java.io.Serializable
{
	boolean chat_visible = true;
	PanelChat panel_chat;

	public BotonOcultar(PanelChat p)
	{
		setBorder(new BevelBorder(BevelBorder.RAISED));
		panel_chat = p;
		addMouseListener(new java.awt.event.MouseAdapter()
		{
			public void mouseClicked(java.awt.event.MouseEvent evt)
			{
				formMouseClicked(evt);
			}
		});
	}

	private void formMouseClicked(java.awt.event.MouseEvent evt)
	{
		if (chat_visible)
		{
			Icon icono_nodo = new ImageIcon("Soporte/Imagenes/ocultar2.png");
			fijarIcono(icono_nodo);
			setBorder(new BevelBorder(BevelBorder.LOWERED));
		} else
		{
			Icon icono_nodo = new ImageIcon("Soporte/Imagenes/ocultar.png");
			fijarIcono(icono_nodo);
			setBorder(new BevelBorder(BevelBorder.RAISED));
		}
		setClickeado(true);
	}

	public void setClickeado(boolean v)
	{
		if (chat_visible == true)
		{
			panel_chat.panel_centro.setVisible(false);
			panel_chat.panel_sur.setVisible(false);
			chat_visible = false;
		} else if (chat_visible == false)
		{
			panel_chat.panel_centro.setVisible(true);
			panel_chat.panel_sur.setVisible(true);
			chat_visible = true;
		}
	}

	public void fijarIcono(Icon icono)
	{
		this.setIcon(icono);
	}
}
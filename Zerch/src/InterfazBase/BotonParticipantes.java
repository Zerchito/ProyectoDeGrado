package InterfazBase;

import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class BotonParticipantes extends JButton
{

	boolean clickeado = false;
	boolean consejero_visible = true;

	PanelChat panel_chat;

	public BotonParticipantes(PanelChat p)
	{
		setBorder(new BevelBorder(BevelBorder.RAISED));
		panel_chat = p;
		this.setName("participantes");
	}

	public void setClickeado(boolean v)
	{
		clickeado = v;
		if (consejero_visible == true)
		{
			panel_chat.panel_centro.setVisible(false);
			panel_chat.panel_sur.setVisible(false);
			consejero_visible = false;
		} else if (consejero_visible == false)
		{
			panel_chat.panel_centro.setVisible(true);
			panel_chat.panel_sur.setVisible(true);
			consejero_visible = true;
		}
	}

	public void fijarIcono(Icon icono)
	{
		this.setIcon(icono);
	}
}

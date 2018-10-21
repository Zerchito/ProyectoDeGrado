package InterfazBase;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class PanelPuntero extends JPanel
{

	public JLabel label_coordenadas;
	public JTextField textfield_coordenadas_x;
	public JTextField textfield_coordenadas_y;

	public PanelPuntero()
	{

		setVisible(true);
		setSize(145, 25);
		setLayout(null);
		setBorder(new BevelBorder(BevelBorder.RAISED));

		label_coordenadas = new JLabel("Puntero:");
		label_coordenadas.setFont(new Font("ArialBlack", Font.BOLD, 12));
		label_coordenadas.setBorder(new EtchedBorder());
		label_coordenadas.setBounds(2, 2, 55, 20);
		add(label_coordenadas);

		textfield_coordenadas_x = new JTextField("...");
		textfield_coordenadas_x.setEditable(false);
		textfield_coordenadas_x.setBackground(Color.white);
		textfield_coordenadas_x.setBounds(57, 2, 40, 20);
		add(textfield_coordenadas_x);

		textfield_coordenadas_y = new JTextField("...");
		textfield_coordenadas_y.setEditable(false);
		textfield_coordenadas_y.setBackground(Color.white);
		textfield_coordenadas_y.setBounds(97, 2, 40, 20);
		add(textfield_coordenadas_y);

	}

}

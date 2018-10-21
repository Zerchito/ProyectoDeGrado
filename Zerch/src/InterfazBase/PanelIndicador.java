package InterfazBase;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class PanelIndicador extends JPanel
{

	public JLabel label_ranura;
	public JTextField textfield_ranura;

	public PanelIndicador()
	{

		setVisible(true);
		setSize(85, 25);
		setLayout(null);
		setBorder(new BevelBorder(BevelBorder.RAISED));

		label_ranura = new JLabel("Ranura:");
		label_ranura.setFont(new Font("ArialBlack", Font.BOLD, 12));
		label_ranura.setBorder(new EtchedBorder());
		label_ranura.setBounds(2, 2, 55, 20);
		add(label_ranura);

		textfield_ranura = new JTextField("...");
		textfield_ranura.setEditable(false);
		textfield_ranura.setBackground(Color.white);
		textfield_ranura.setBounds(57, 2, 20, 20);
		add(textfield_ranura);

	}

}

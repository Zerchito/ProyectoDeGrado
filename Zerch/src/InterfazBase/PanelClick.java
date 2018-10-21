package InterfazBase;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class PanelClick extends JPanel
{

	public JLabel label_click;
	public JTextField textfield_click_x;
	public JTextField textfield_click_y;

	public PanelClick()
	{
		setSize(130, 25);
		setVisible(true);
		setLayout(null);
		setBorder(new BevelBorder(BevelBorder.RAISED));

		label_click = new JLabel("Click:");
		label_click.setFont(new Font("ArialBlack", Font.BOLD, 12));
		label_click.setBorder(new EtchedBorder());
		label_click.setBounds(2, 2, 40, 20);

		add(label_click);

		textfield_click_x = new JTextField("...");
		textfield_click_x.setEditable(false);
		textfield_click_x.setBackground(Color.white);
		textfield_click_x.setBounds(42, 2, 40, 20);
		add(textfield_click_x);

		textfield_click_y = new JTextField("...");
		textfield_click_y.setEditable(false);
		textfield_click_y.setBackground(Color.white);
		textfield_click_y.setBounds(82, 2, 40, 20);
		add(textfield_click_y);
	}
}
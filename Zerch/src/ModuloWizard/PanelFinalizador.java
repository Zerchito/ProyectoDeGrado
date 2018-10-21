package ModuloWizard;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class PanelFinalizador extends JPanel
{

	JTextField textfield_ancho;
	JTextField textfield_alto;
	JRadioButton radio_defecto;
	JComboBox combo_defecto;

	JSpinner spinner_ranuras;

	JLabel label_00;
	JLabel label_01;
	JLabel label_02;
	JTextArea textarea_03;

	String cantidad = "";

	public PanelFinalizador()
	{
		setLayout(null);

		label_00 = new JLabel();
		label_00.setSize(300, 20);
		label_00.setLocation(20, 40);
		label_00.setBackground(new Color(255, 255, 255));
		label_00.setForeground(new Color(51, 51, 51));
		label_00.setFont(new Font("Dialog", 1, 12));
		label_00.setText("La configuracion actual es la siguiente:");
		label_00.setLayout(null);
		this.add(label_00);

		label_01 = new JLabel();
		label_01.setSize(140, 20);
		label_01.setLocation(20, 70);
		label_01.setBackground(new Color(255, 255, 255));
		label_01.setForeground(new Color(51, 51, 51));
		label_01.setFont(new Font("Dialog", 0, 12));
		label_01.setText("Dimension = (XXX,XXX);");
		label_01.setLayout(null);
		this.add(label_01);

		label_02 = new JLabel();
		label_02.setSize(170, 20);
		label_02.setLocation(20, 90);
		label_02.setBackground(new Color(255, 255, 255));
		label_02.setForeground(new Color(51, 51, 51));
		label_02.setFont(new Font("Dialog", 0, 12));
		label_02.setText("Cantidad de ventanas = (XX);");
		label_02.setLayout(null);
		this.add(label_02);

		textarea_03 = new JTextArea();
		textarea_03.setSize(300, 55);
		textarea_03.setLocation(20, 130);
		textarea_03.setBackground(new Color(255, 255, 255));
		textarea_03.setForeground(new Color(51, 51, 51));
		textarea_03.setBorder(new EtchedBorder());
		textarea_03.setFont(new Font("Dialog", 0, 12));
		textarea_03.setText(
				"Verifique que los valores fijados esten correctos \n antes de finalizar el asistente");
		textarea_03.setLayout(null);
		textarea_03.setEditable(false);
		this.add(textarea_03);

	}

	public void actualizar()
	{
		cantidad = spinner_ranuras.getValue().toString();
	}

}

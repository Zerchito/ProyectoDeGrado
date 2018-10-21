package ModuloWizard;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class PanelVentanas extends JPanel
{

	JTextField textfield_ancho;
	JTextField textfield_alto;
	JRadioButton radio_defecto;
	JComboBox combo_defecto;

	JSpinner spinner_ranuras;

	String cantidad = "";

	public PanelVentanas()
	{
		setLayout(null);

		JLabel label_ventanas = new JLabel("Numero de Ventanas");
		label_ventanas.setBounds(5, 5, 150, 20);
		label_ventanas.setBorder(new EtchedBorder());
		add(label_ventanas);

		JLabel cantidad_ventanas = new JLabel("Cantidad:");
		cantidad_ventanas.setBounds(5, 25, 80, 20);
		cantidad_ventanas.setBorder(new EtchedBorder());
		cantidad_ventanas.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(cantidad_ventanas);

		spinner_ranuras = new JSpinner();
		spinner_ranuras.setValue(1);
		spinner_ranuras.setBounds(85, 25, 50, 20);
		spinner_ranuras.setFont(new Font("Dialog", Font.PLAIN, 12));
		spinner_ranuras.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		add(spinner_ranuras);

		JLabel label_ranura = new JLabel("Ranura");
		label_ranura.setBounds(5, 65, 100, 20);
		label_ranura.setBorder(new EtchedBorder());
		add(label_ranura);

		JLabel label_orientacion = new JLabel("Orientacion:");
		label_orientacion.setBounds(5, 85, 80, 20);
		label_orientacion.setBorder(new EtchedBorder());
		label_orientacion.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(label_orientacion);

		JComboBox combo_orientacion = new JComboBox();
		combo_orientacion.setBounds(85, 85, 80, 20);
		combo_orientacion.setFont(new Font("Dialog", Font.PLAIN, 12));
		combo_orientacion.setBackground(Color.white);
		add(combo_orientacion);

		combo_orientacion.addItem("Norte");
		combo_orientacion.addItem("Sud");
		combo_orientacion.addItem("Este");
		combo_orientacion.addItem("Oeste");

	}

	public void actualizar()
	{
		cantidad = spinner_ranuras.getValue().toString();
	}

}

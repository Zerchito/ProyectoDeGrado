package ModuloWizard;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class PanelHabilitables extends JPanel
{

	JPanel panel_00;
	JLabel label_000;
	JLabel label_001;
	JRadioButton radiobutton_002;
	JRadioButton radiobutton_003;
	JLabel label_004;
	JTextField textfield_005;
	JLabel label_006;
	JPanel panel_01;
	JLabel label_010;
	JRadioButton radiobutton_011;
	JLabel label_012;
	JRadioButton radiobutton_013;
	JLabel label_02;

	public PanelHabilitables()
	{

		this.setVisible(true);
		this.setLayout(null);
		this.setLocation(0, 0);
		this.setSize(440, 233);

		panel_00 = new JPanel();
		panel_00.setSize(200, 150);
		panel_00.setLocation(10, 35);
		panel_00.setBackground(new Color(238, 238, 238));
		panel_00.setForeground(new Color(51, 51, 51));
		panel_00.setBorder(new EtchedBorder());
		panel_00.setLayout(null);
		this.add(panel_00);

		label_000 = new JLabel();
		label_000.setSize(130, 20);
		label_000.setLocation(10, 10);
		label_000.setBackground(new Color(255, 255, 255));
		label_000.setForeground(new Color(51, 51, 51));
		label_000.setBorder(new EtchedBorder());
		label_000.setFont(new Font("Dialog", 0, 12));
		label_000.setText("Modalidad Consejero:");
		label_000.setLayout(null);
		panel_00.add(label_000);

		label_001 = new JLabel();
		label_001.setSize(60, 20);
		label_001.setLocation(10, 40);
		label_001.setBackground(new Color(255, 255, 255));
		label_001.setForeground(new Color(51, 51, 51));
		label_001.setBorder(new EtchedBorder());
		label_001.setFont(new Font("Dialog", 0, 12));
		label_001.setText("Estado:");
		label_001.setLayout(null);
		panel_00.add(label_001);

		radiobutton_002 = new JRadioButton();
		radiobutton_002.setSize(90, 20);
		radiobutton_002.setLocation(75, 40);
		radiobutton_002.setFont(new Font("Dialog", 0, 12));
		radiobutton_002.setText("Inactivo");
		radiobutton_002.setLayout(null);
		panel_00.add(radiobutton_002);

		radiobutton_003 = new JRadioButton();
		radiobutton_003.setSize(90, 20);
		radiobutton_003.setLocation(75, 65);
		radiobutton_003.setFont(new Font("Dialog", 0, 12));
		radiobutton_003.setText("Activo");
		radiobutton_003.setLayout(null);
		panel_00.add(radiobutton_003);

		label_004 = new JLabel();
		label_004.setSize(60, 20);
		label_004.setLocation(10, 100);
		label_004.setBackground(new Color(255, 255, 255));
		label_004.setForeground(new Color(51, 51, 51));
		label_004.setBorder(new EtchedBorder());
		label_004.setFont(new Font("Dialog", 0, 12));
		label_004.setText("Intervalo:");
		label_004.setLayout(null);
		panel_00.add(label_004);

		textfield_005 = new JTextField();
		textfield_005.setSize(50, 20);
		textfield_005.setLocation(75, 100);
		textfield_005.setBackground(new Color(255, 255, 255));
		textfield_005.setForeground(new Color(51, 51, 51));
		textfield_005.setBorder(new EtchedBorder());
		textfield_005.setFont(new Font("Dialog", 0, 12));
		textfield_005.setText("XX");
		textfield_005.setLayout(null);
		panel_00.add(textfield_005);

		label_006 = new JLabel();
		label_006.setSize(40, 20);
		label_006.setLocation(130, 100);
		label_006.setBackground(new Color(255, 255, 255));
		label_006.setForeground(new Color(51, 51, 51));
		// label_006.setBorder(new EtchedBorder());
		label_006.setFont(new Font("Dialog", 0, 12));
		label_006.setText("Seg.");
		label_006.setLayout(null);
		panel_00.add(label_006);

		panel_01 = new JPanel();
		panel_01.setSize(200, 150);
		panel_01.setLocation(220, 35);
		panel_01.setBackground(new Color(238, 238, 238));
		panel_01.setForeground(new Color(51, 51, 51));
		panel_01.setBorder(new EtchedBorder());
		panel_01.setLayout(null);
		this.add(panel_01);

		label_010 = new JLabel();
		label_010.setSize(130, 20);
		label_010.setLocation(10, 10);
		label_010.setBackground(new Color(255, 255, 255));
		label_010.setForeground(new Color(51, 51, 51));
		label_010.setBorder(new EtchedBorder());
		label_010.setFont(new Font("Dialog", 0, 12));
		label_010.setText("Modalidad Eventos:");
		label_010.setLayout(null);
		panel_01.add(label_010);

		radiobutton_011 = new JRadioButton();
		radiobutton_011.setSize(90, 20);
		radiobutton_011.setLocation(75, 40);
		radiobutton_011.setFont(new Font("Dialog", 0, 12));
		radiobutton_011.setText("Inactivo");
		radiobutton_011.setLayout(null);
		panel_01.add(radiobutton_011);

		label_012 = new JLabel();
		label_012.setSize(60, 20);
		label_012.setLocation(10, 40);
		label_012.setBackground(new Color(255, 255, 255));
		label_012.setForeground(new Color(51, 51, 51));
		label_012.setBorder(new EtchedBorder());
		label_012.setFont(new Font("Dialog", 0, 12));
		label_012.setText("Estado:");
		label_012.setLayout(null);
		panel_01.add(label_012);

		radiobutton_013 = new JRadioButton();
		radiobutton_013.setSize(90, 20);
		radiobutton_013.setLocation(75, 65);
		radiobutton_013.setFont(new Font("Dialog", 0, 12));
		radiobutton_013.setText("Activo");
		radiobutton_013.setLayout(null);
		panel_01.add(radiobutton_013);

		label_02 = new JLabel();
		label_02.setSize(150, 20);
		label_02.setLocation(10, 10);
		label_02.setBackground(new Color(255, 255, 255));
		label_02.setForeground(new Color(51, 51, 51));
		label_02.setBorder(new EtchedBorder());
		label_02.setFont(new Font("Dialog", 0, 12));
		label_02.setText("Modalidades Habilitables:");
		label_02.setLayout(null);
		this.add(label_02);
	}
}

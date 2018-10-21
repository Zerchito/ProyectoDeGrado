package SoporteAyuda;

import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Toolkit.*;
import java.awt.Image.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.colorchooser.*;
import java.io.*;
import java.awt.image.*;
import java.awt.print.*;
import java.awt.datatransfer.Clipboard.*;
import java.awt.datatransfer.*;
import com.sun.image.codec.jpeg.*;
import java.applet.*;
import javax.swing.text.EditorKit;
import javax.swing.border.*;

public class VentanaAyuda extends JFrame
{
	File file;
	File file2;
	VentanaHTML fileshown;

	JPanel panel_oeste;

	public VentanaAyuda()
	{
		setTitle("AYUDA");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int) screenSize.getWidth() * 2 / 6,
				(int) screenSize.getHeight() * 1 / 5);
		setSize(new Dimension(760, 500));
		setLayout(new BorderLayout());

		panel_oeste = new JPanel();
		panel_oeste.setLayout(null);
		panel_oeste.setPreferredSize(new Dimension(300, 300));
		panel_oeste.setBorder(new EtchedBorder());
		add("West", panel_oeste);

		ArbolTemas arbol_temas = new ArbolTemas(this);
		arbol_temas.setLocation(5, 5);
		panel_oeste.add(arbol_temas);

		file2 = new File("windowRegistry.txt");
		file = new File(file2.getAbsoluteFile().getParent());

		String filename = new String(
				"file://" + file.separator + file.getAbsolutePath()
						+ file.separator + "Soporte" + file.separator + "Ayuda"
						+ file.separator + "0.- Temas de ayuda.html");

		fileshown = new VentanaHTML(filename, "0.- Temas de ayuda");
		try
		{
			JScrollPane scrol_html = fileshown.getScrollPane();
			scrol_html.setPreferredSize(new Dimension(400, 400));
			add("Center", scrol_html);
		} catch (Exception e)
		{
		}
		setVisible(true);
	};

	public void HelpMenuActionPerformed(String filen)
	{
		String filename = new String("file://" + file.separator
				+ file.getAbsolutePath() + file.separator + "Soporte"
				+ file.separator + "Ayuda" + file.separator + filen);
		try
		{
			remove(fileshown.getScrollPane());
			fileshown = new VentanaHTML(filename, "0.- Temas de ayuda");
			add("Center", fileshown.getScrollPane());
			setVisible(true);
		} catch (Exception e)
		{
		}
	}
}

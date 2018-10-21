package ModuloWizard;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.io.*;
import java.net.URL;
import java.util.*;

import javax.swing.text.*;
import javax.swing.undo.*;
import javax.swing.event.*;
import javax.swing.*;
import javax.swing.border.*;

class Notepad extends JPanel implements java.io.Serializable
{

	public JTextComponent editor;
	public JTextComponent c;

	public void paintChildren(Graphics g)
	{
		super.paintChildren(g);
	}

	Notepad()
	{
		super(true);

		setBorder(new EtchedBorder());
		setLayout(new BorderLayout());

		editor = createEditor();

		JScrollPane scroller = new JScrollPane();
		JViewport port = scroller.getViewport();
		port.add(editor);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add("Center", scroller);
		add("Center", panel);
	}

	protected JTextComponent createEditor()
	{
		c = new JTextArea();
		c.setDragEnabled(true);
		c.setFont(new Font("monospaced", Font.PLAIN, 12));
		return c;
	}

	protected Frame getFrame()
	{
		for (Container p = getParent(); p != null; p = p.getParent())
		{
			if (p instanceof Frame)
			{
				return (Frame) p;
			}
		}
		return null;
	}
}

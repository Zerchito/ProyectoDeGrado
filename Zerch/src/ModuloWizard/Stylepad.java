package ModuloWizard;

import javax.swing.text.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Stylepad extends Notepad implements java.io.Serializable
{
	public Referencia referencia;
	public StyleContext sc;
	public DefaultStyledDocument doc;

	public Stylepad()
	{
		super();
	}

	protected JTextComponent createEditor()
	{
		sc = new StyleContext();
		doc = new DefaultStyledDocument(sc);
		initDocument(doc, sc);
		JTextPane p = new JTextPane(doc);
		p.setDragEnabled(true);
		return p;
	}

	void initDocument(DefaultStyledDocument doc, StyleContext sc)
	{
		referencia = new Referencia(doc, sc); // referencia.loadDocument();
	}
}
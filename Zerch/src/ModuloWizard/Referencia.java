package ModuloWizard;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import java.net.URL;
import java.util.Hashtable;
import java.awt.Color;
import javax.swing.*;
import javax.swing.text.*;
import java.util.*;

public class Referencia implements java.io.Serializable
{
	DefaultStyledDocument doc;
	StyleContext styles;
	Hashtable runAttr;

	Vector vector_runs;
	Run run;
	Paragrafo Paragrafo;
	Vector vector_Paragrafos = new Vector();

	Referencia(DefaultStyledDocument doc, StyleContext styles)
	{
		this.doc = doc;
		this.styles = styles;
		runAttr = new Hashtable();
		llenarParagrafo();

	}

	void loadDocument()
	{
		createStyles();

		for (int j = 0; j < vector_Paragrafos.size(); j++)
		{
			Paragrafo p2 = (Paragrafo) vector_Paragrafos.elementAt(j);
			addParagrafo(p2);
		}

	}

	void addParagrafo(Paragrafo p)
	{
		try
		{
			Style s = null;

			for (int i = 0; i < p.data.size(); i++)
			{
				Run run = (Run) p.data.elementAt(i);
				s = (Style) runAttr.get(run.attr);
				doc.insertString(doc.getLength(), run.content, s);
			}

			Style ls = styles.getStyle(p.logical);
			doc.setLogicalStyle(doc.getLength() - 1, ls);
			doc.insertString(doc.getLength(), "\n", null);
		} catch (BadLocationException e)
		{
			System.err.println("Internal error: " + e);
		}
	}

	void createStyles()
	{
		// no attributes defined
		Style s = styles.addStyle(null, null);
		s = styles.addStyle(null, null);
		StyleConstants.setItalic(s, true);
		StyleConstants.setForeground(s, new Color(50, 102, 153));
		runAttr.put("aquote", s); // alice quote

		try
		{
			Icon alice = new ImageIcon("Soporte/Referencias/alice.gif");
			StyleConstants.setIcon(s, alice);
			runAttr.put("alice", s); // alice
		} catch (MissingResourceException mre)
		{
			// can't display image
		}

		Style def = styles.getStyle(StyleContext.DEFAULT_STYLE);

		// normal
		Style sty = styles.addStyle("normal", def);
		StyleConstants.setLeftIndent(sty, 10);
		StyleConstants.setRightIndent(sty, 10);
		StyleConstants.setFontFamily(sty, "SansSerif");
		StyleConstants.setFontSize(sty, 13);
		StyleConstants.setSpaceAbove(sty, 3);
		StyleConstants.setSpaceBelow(sty, 3);
		StyleConstants.setBold(sty, true);

	}

	public void llenarParagrafo()
	{
		llenarTitulo();
		llenarContenido();
		llenarGrafico();

	}

	public void llenarTitulo()
	{
		vector_runs = new Vector();
		run = new Run("none", "PRINCIPIO ");
		vector_runs.add(run);
		Paragrafo = new Paragrafo("normal", vector_runs);
		vector_Paragrafos.add(Paragrafo);
	}

	public void llenarContenido()
	{

		vector_runs = new Vector();
		run = new Run("none", "sdasdd");
		vector_runs.add(run);
		Paragrafo = new Paragrafo("normal", vector_runs);
		vector_Paragrafos.add(Paragrafo);

	}

	public void llenarGrafico()
	{
		vector_runs = new Vector();
		run = new Run("alice", " ");
		vector_runs.add(run);
		Paragrafo = new Paragrafo("title", vector_runs);
		vector_Paragrafos.add(Paragrafo);
	}
}

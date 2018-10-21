package SoporteAyuda;

import javax.swing.text.EditorKit;
import java.net.URL;
import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.*;
import javax.swing.text.html.*;
import java.awt.event.ActionEvent;
import java.awt.event.*;

public class VentanaHTML extends JFrame
{

	JScrollPane scrollPane;

	VentanaHTML(String s, String t)
	{
		JEditorPane editor_html = new JEditorPane();
		editor_html.setEditable(false);

		EditorKit kit_html = editor_html
				.getEditorKitForContentType("text/html");
		HTMLDocument documento_html = (HTMLDocument) kit_html
				.createDefaultDocument();
		editor_html.setEditorKit(kit_html);

		try
		{
			URL url_html = new URL(s);
			editor_html.setPage(url_html);
		} catch (Exception e)
		{

		}

		scrollPane = new JScrollPane(editor_html);

	}

	public JScrollPane getScrollPane()
	{

		return scrollPane;
	}

}

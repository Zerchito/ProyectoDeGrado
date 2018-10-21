package ModuloScript;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.swing.border.*;

import ModuloBase.*;

import java.util.*;

import SwingWidget.*;

public class FuncionScript implements java.io.Serializable
{

	// enlace a su interfaz grafica
	VentanaScript ventana_script;

	// lectura y escritura del fichero persistente
	FileWriter salida = null;

	// variables para las funciones
	Ranura ranura_actual;
	StringBuffer buffer_script;
	JFileChooser file1;

	// constructor
	public FuncionScript()
	{
	}

	// establecer la ranura selecta
	public void setRanura(Ranura r)
	{
		ranura_actual = (Ranura) r;
		Exportar();
	}

	// generar el cuerpo de las lineas de codigo y exhibir
	public void Exportar()
	{
		buffer_script = new StringBuffer();

		String string_contenedor = new String("");

		Vector vector_script = new Vector();

		vector_script.add("import java.awt.*;");
		vector_script.add("import javax.swing.*;");
		vector_script.add("import javax.swing.border.*;");
		vector_script.add("import java.io.*;");
		vector_script.add("import java.awt.event.*;");
		vector_script.add("");
		vector_script
				.add("public class Sintitulo_" + ranura_actual.posicion_ranura
						+ " extends JFrame implements ActionListener");
		vector_script.add("{");
		vector_script.add("");

		for (int a = 0; a < ranura_actual.vector_widgets.size(); a++)
		{
			JComponent widget_recorrido = (JComponent) ranura_actual.vector_widgets
					.elementAt(a);

			if (widget_recorrido.getClass().getName()
					.endsWith("VentanaInterna"))
			{
				VentanaInterna widget_actual = (VentanaInterna) widget_recorrido;
				vector_script
						.add("" + widget_actual.declaracionesScript("" + a));
			}

		}

		vector_script.add(
				"public Sintitulo_" + ranura_actual.posicion_ranura + "()");
		vector_script.add("{");

		JComponent widget_recorrido2 = (JComponent) ranura_actual.vector_widgets
				.elementAt(0);

		VentanaInterna widget_actual = (VentanaInterna) widget_recorrido2;
		vector_script.add("this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);");
		vector_script.add("this.setTitle(\"Sintitulo_"
				+ ranura_actual.posicion_ranura + "\");");
		vector_script.add("this.setVisible(true);");
		vector_script.add("this.setLayout(null);");
		Point ploc = widget_actual.getLocation();
		int plocx = (int) ploc.getX();
		int plocy = (int) ploc.getY();
		vector_script.add("this.setLocation(" + plocx + "," + plocy + ");");
		vector_script.add("this.setSize(" + widget_actual.getWidth() + ","
				+ widget_actual.getHeight() + ");");
		vector_script.add("\n");

		for (int b = 0; b < ranura_actual.vector_widgets.size(); b++)
		{
			JComponent widget_recorrido3 = (JComponent) ranura_actual.vector_widgets
					.elementAt(b);

			if (widget_recorrido3.getClass().getName()
					.endsWith("VentanaInterna"))
			{
				VentanaInterna widget_actual3 = (VentanaInterna) widget_recorrido3;
				vector_script.add("" + widget_actual3
						.especificacionesScript("this", "" + b));
			}
		}

		for (int c = 0; c < ranura_actual.vector_widgets.size(); c++)
		{
			JComponent widget_recorrido4 = (JComponent) ranura_actual.vector_widgets
					.elementAt(c);

			if (widget_recorrido4.getClass().getName()
					.endsWith("VentanaInterna"))
			{
				VentanaInterna widget_actual4 = (VentanaInterna) widget_recorrido4;
				vector_script.add(
						"" + widget_actual4.agregarListener("this", "" + c));
			}
		}

		vector_script.add("this.setSize(" + widget_actual.getWidth() + "+1,"
				+ widget_actual.getHeight() + ");");

		vector_script.add("}");
		vector_script.add("\n public void actionPerformed(ActionEvent ae)");
		vector_script.add("{");

		for (int d = 0; d < ranura_actual.vector_widgets.size(); d++)
		{
			JComponent widget_recorrido5 = (JComponent) ranura_actual.vector_widgets
					.elementAt(d);

			if (widget_recorrido5.getClass().getName()
					.endsWith("VentanaInterna"))
			{
				VentanaInterna widget_actual5 = (VentanaInterna) widget_recorrido5;
				vector_script.add(""
						+ widget_actual5.metodosEnlaceScript("this", "" + d));
			}
		}

		vector_script.add("}");

		vector_script.add("public static void main (String[]arg)");
		vector_script.add("{");
		vector_script.add("Sintitulo_" + ranura_actual.posicion_ranura
				+ " sn = new Sintitulo_" + ranura_actual.posicion_ranura
				+ "();");
		vector_script.add("}");
		vector_script.add("}");

		for (int b = 0; b < vector_script.size(); b++)
		{
			buffer_script
					.append(" " + (String) vector_script.elementAt(b) + "\n");

		}

		ventana_script = new VentanaScript();
		ventana_script.setFuncionscript(this);
		ventana_script.setBuffer(buffer_script);
		Point ploc2 = ranura_actual.funcion_base.ventana_base.getLocation();
		int plocx2 = (int) ploc2.getX();
		int plocy2 = (int) ploc2.getY();
		ventana_script.setLocation(plocx2 + 50, plocy2 + 50);
		ventana_script
				.setTitle("Script Ranura_" + ranura_actual.posicion_ranura);

	}

	// cerrar el scripter
	public void volver()
	{
		ventana_script.dispose();
	}

	// guardar el script
	public void guardarScript()
	{
		file1 = new JFileChooser();

		FiltroScript scr = new FiltroScript(new String("gsc"), "Script de gui");
		FiltroScript java = new FiltroScript(new String("java"),
				"Documento de Java file");
		FiltroScript txt = new FiltroScript(new String("txt"),
				"Documento de texto");
		file1.addChoosableFileFilter(scr);
		file1.addChoosableFileFilter(java);
		file1.addChoosableFileFilter(txt);

		int seleccion = file1.showSaveDialog(null);

		if (seleccion == JFileChooser.APPROVE_OPTION)
		{
			String path = file1.getSelectedFile().toString();

			File factual = new File(path);
			if (factual.exists())
			{
				JOptionPane jopexiste = new JOptionPane();
				int confirmexiste = jopexiste.showConfirmDialog(null,
						"El archivo ya existe..Desea Reemplazarlo?");
				if (confirmexiste == 0)
				{
					factual.delete();

					String stb = path;
					StringBuffer sb = new StringBuffer();
					char auxb;
					for (int b = 0; b < stb.length(); b++)
					{

						auxb = stb.charAt(b);
						if (auxb == '.')
						{
							break;
						} else
						{
							sb.append(auxb);

						}
					}

					Guardar("" + sb);
				}

			} else
			{
				Guardar(path);
			}

			JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null, "Archivo Guardado con exito", "Aviso",
					jop.WARNING_MESSAGE);
		}

		else if (seleccion == JFileChooser.CANCEL_OPTION)
		{

		}
	}

	void Guardar(String p)
	{

		try
		{

			String descripcion = "" + file1.getFileFilter().getDescription();

			String extension = "";

			if (descripcion.endsWith("(.txt)"))
			{
				extension = ".txt";
			} else if (descripcion.endsWith("(.java)"))
			{
				extension = ".java";
			} else if (descripcion.endsWith("(.gsc)"))
			{
				extension = ".gsc";
			}

			salida = new FileWriter(p + extension);

			for (int a = 0; a < buffer_script.length(); a++)
			{

				salida.write(buffer_script.charAt(a));

			}

			salida.close();
		} catch (IOException ex)
		{

		}
	}
}

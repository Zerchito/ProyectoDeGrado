package SoporteAyuda;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileFilter;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultTreeCellRenderer;

import InterfazBase.*;

public class ArbolTemas extends JPanel
		implements ActionListener, TreeSelectionListener, TreeExpansionListener
{
	JTree arbol_total;
	DefaultTreeModel arbol_modelo;

	DefaultMutableTreeNode nodo_raiz;

	DefaultMutableTreeNode nodo_1;
	DefaultMutableTreeNode nodo_11;
	DefaultMutableTreeNode nodo_12;
	DefaultMutableTreeNode nodo_13;
	DefaultMutableTreeNode nodo_14;
	DefaultMutableTreeNode nodo_15;
	DefaultMutableTreeNode nodo_16;
	DefaultMutableTreeNode nodo_17;

	DefaultMutableTreeNode nodo_2;
	DefaultMutableTreeNode nodo_21;
	DefaultMutableTreeNode nodo_22;

	DefaultMutableTreeNode nodo_3;
	DefaultMutableTreeNode nodo_31;
	DefaultMutableTreeNode nodo_32;
	DefaultMutableTreeNode nodo_33;
	DefaultMutableTreeNode nodo_34;
	DefaultMutableTreeNode nodo_35;
	DefaultMutableTreeNode nodo_36;

	DefaultMutableTreeNode nodo_4;
	DefaultMutableTreeNode nodo_41;
	DefaultMutableTreeNode nodo_42;

	DefaultMutableTreeNode nodo_5;
	DefaultMutableTreeNode nodo_51;
	DefaultMutableTreeNode nodo_52;
	DefaultMutableTreeNode nodo_53;
	DefaultMutableTreeNode nodo_54;
	DefaultMutableTreeNode nodo_55;

	JButton b_aceptar;

	VentanaAyuda ventana_ayuda;

	String s;

	public ArbolTemas(VentanaAyuda a)
	{

		ventana_ayuda = a;

		setSize(290, 340);
		setVisible(true);
		setLayout(null);

		// Construccion del arbol
		nodo_raiz = new DefaultMutableTreeNode("0.- Temas de ayuda");

		arbol_modelo = new DefaultTreeModel(nodo_raiz);

		arbol_total = new JTree(arbol_modelo);

		// Construccion de los datos del arbol
		nodo_1 = new DefaultMutableTreeNode("1.- Modulo Base funcional");
		arbol_modelo.insertNodeInto(nodo_1, nodo_raiz, 0);

		nodo_11 = new DefaultMutableTreeNode(
				"1.1.- Crear nueva instancia del editor");
		arbol_modelo.insertNodeInto(nodo_11, nodo_1, 0);

		nodo_12 = new DefaultMutableTreeNode(
				"1.2.- Agregar nueva ranura de edicion");
		arbol_modelo.insertNodeInto(nodo_12, nodo_1, 1);

		nodo_13 = new DefaultMutableTreeNode(
				"1.3.- Agregar un widget a la ventana de edicion");
		arbol_modelo.insertNodeInto(nodo_13, nodo_1, 2);

		nodo_14 = new DefaultMutableTreeNode(
				"1.4.- Configurar propiedades de un widget");
		arbol_modelo.insertNodeInto(nodo_14, nodo_1, 3);

		nodo_15 = new DefaultMutableTreeNode(
				"1.5.- Remover un widget especifico");
		arbol_modelo.insertNodeInto(nodo_15, nodo_1, 4);

		nodo_16 = new DefaultMutableTreeNode("1.6.- Guardar un entorno");
		arbol_modelo.insertNodeInto(nodo_16, nodo_1, 5);

		nodo_17 = new DefaultMutableTreeNode("1.7.- Recuperar un entorno");
		arbol_modelo.insertNodeInto(nodo_17, nodo_1, 6);

		nodo_2 = new DefaultMutableTreeNode("2.- Modulo Script");
		arbol_modelo.insertNodeInto(nodo_2, nodo_raiz, 1);

		nodo_21 = new DefaultMutableTreeNode("2.1.- Generar un script");
		arbol_modelo.insertNodeInto(nodo_21, nodo_2, 0);

		nodo_22 = new DefaultMutableTreeNode("2.2.- Guardar un script");
		arbol_modelo.insertNodeInto(nodo_22, nodo_2, 1);

		nodo_3 = new DefaultMutableTreeNode("3.- Modulo Wizard");
		arbol_modelo.insertNodeInto(nodo_3, nodo_raiz, 2);

		nodo_31 = new DefaultMutableTreeNode(
				"3.1.- Configurar un nuevo entorno");
		arbol_modelo.insertNodeInto(nodo_31, nodo_3, 0);

		nodo_32 = new DefaultMutableTreeNode("3.2.- Acceder a referencias HCI");
		arbol_modelo.insertNodeInto(nodo_32, nodo_3, 1);

		nodo_33 = new DefaultMutableTreeNode(
				"3.3.- Reproducir una referencia HCI");
		arbol_modelo.insertNodeInto(nodo_33, nodo_3, 2);

		nodo_34 = new DefaultMutableTreeNode("3.4.- Editar una referencia HCI");
		arbol_modelo.insertNodeInto(nodo_34, nodo_3, 3);

		nodo_35 = new DefaultMutableTreeNode(
				"3.5.- Exportar una referencia HCI");
		arbol_modelo.insertNodeInto(nodo_35, nodo_3, 4);

		nodo_36 = new DefaultMutableTreeNode(
				"3.6.- Habilitar un consejero HCI");
		arbol_modelo.insertNodeInto(nodo_36, nodo_3, 5);

		nodo_4 = new DefaultMutableTreeNode("4.- Modulo Prototipo");
		arbol_modelo.insertNodeInto(nodo_4, nodo_raiz, 3);

		nodo_41 = new DefaultMutableTreeNode(
				"4.1.- Editar una red de navegacion");
		arbol_modelo.insertNodeInto(nodo_41, nodo_4, 0);

		nodo_42 = new DefaultMutableTreeNode(
				"4.2.- Asignar enlace a un widget");
		arbol_modelo.insertNodeInto(nodo_42, nodo_4, 1);

		nodo_5 = new DefaultMutableTreeNode("5.- Modulo Plantillas");
		arbol_modelo.insertNodeInto(nodo_5, nodo_raiz, 4);

		nodo_51 = new DefaultMutableTreeNode("5.1.- Crear una plantilla");
		arbol_modelo.insertNodeInto(nodo_51, nodo_5, 0);

		nodo_52 = new DefaultMutableTreeNode("5.2.- Guardar una plantilla");
		arbol_modelo.insertNodeInto(nodo_52, nodo_5, 1);

		nodo_53 = new DefaultMutableTreeNode("5.3.- Recuperar una plantilla");
		arbol_modelo.insertNodeInto(nodo_53, nodo_5, 2);

		nodo_54 = new DefaultMutableTreeNode(
				"5.4.- Personalizar una plantilla");
		arbol_modelo.insertNodeInto(nodo_54, nodo_5, 3);

		nodo_55 = new DefaultMutableTreeNode("5.5.- Insertar una plantilla");
		arbol_modelo.insertNodeInto(nodo_55, nodo_5, 4);

		arbol_total.setEnabled(true);

		// Construccion y visualizacion de la ventana

		JScrollPane scroll = new JScrollPane(arbol_total);
		scroll.setLocation(5, 5);
		scroll.setSize(270, 320);
		scroll.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		add(scroll);

		DefaultTreeCellRenderer render = (DefaultTreeCellRenderer) arbol_total
				.getCellRenderer();
		render.setLeafIcon(
				new ImageIcon("Soporte/Imagenes/imagenesArbolayuda/hoja.png"));
		render.setOpenIcon(new ImageIcon(
				"Soporte/Imagenes/imagenesArbolayuda/abierto.png"));
		render.setClosedIcon(new ImageIcon(
				"Soporte/Imagenes/imagenesArbolayuda/cerrado.png"));
		render.setTextNonSelectionColor(Color.BLACK);
		render.setTextSelectionColor(Color.WHITE);
		render.setBackgroundNonSelectionColor(Color.WHITE);
		render.setBackgroundSelectionColor(Color.BLACK);
		render.setFont(new Font("ArialBlack", Font.BOLD, 11));

		arbol_total.expandPath(arbol_total.getPathForRow(0));

		arbol_total.getSelectionModel().addTreeSelectionListener(this);
		arbol_total.addTreeExpansionListener(this);

	}

	public void actionPerformed(ActionEvent ae)
	{

	}

	public void valueChanged(TreeSelectionEvent e)
	{

		s = "" + arbol_total.getLastSelectedPathComponent();

		arbol_total.expandPath(arbol_total.getSelectionPath());

		// int nivel=arbol_total.getSelectionPath().getPathCount();

		if (s != null)
		{
			ventana_ayuda.HelpMenuActionPerformed(s + ".html");
		}

	}

	public void treeExpanded(TreeExpansionEvent event)
	{

	}

	public void treeCollapsed(TreeExpansionEvent event)
	{
	}

}
package ModuloWizard;

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

import java.util.*;

public class ArbolTrainer extends JPanel
		implements TreeSelectionListener, TreeExpansionListener
{
	JTree arbol_total;
	DefaultTreeModel arbol_modelo;

	DefaultMutableTreeNode nodo_raiz;

	DefaultMutableTreeNode nodo_reglasdeoro;
	DefaultMutableTreeNode nodo_regla_darcontrol;
	DefaultMutableTreeNode nodo_regla_reducirmemoria;
	DefaultMutableTreeNode nodo_regla_consistencia;

	DefaultMutableTreeNode nodo_principios;
	DefaultMutableTreeNode nodo_principio_familiaridad;
	DefaultMutableTreeNode nodo_principio_uniformidad;
	DefaultMutableTreeNode nodo_principio_minimasorpresa;
	DefaultMutableTreeNode nodo_principio_recuperacion;
	DefaultMutableTreeNode nodo_principio_asistencia;
	DefaultMutableTreeNode nodo_principio_diversidad;

	DefaultMutableTreeNode nodo_caracterestetico;
	DefaultMutableTreeNode nodo_carac_fondo;
	DefaultMutableTreeNode nodo_carac_texto;
	DefaultMutableTreeNode nodo_carac_imagenes;
	DefaultMutableTreeNode nodo_carac_iconografia;

	DefaultMutableTreeNode nodo_patrones;
	DefaultMutableTreeNode nodo_patrones_comportamiento;
	DefaultMutableTreeNode nodo_patrones_estructura;

	DefaultMutableTreeNode nodo_plantillas;
	DefaultMutableTreeNode nodo_plantillas_aplicacionales;
	DefaultMutableTreeNode nodo_plantillas_metaforicas;

	DefaultMutableTreeNode nodo_otros;
	DefaultMutableTreeNode nodo_otros1;
	DefaultMutableTreeNode nodo_otros2;

	JButton b_aceptar;

	String arbol_path = null;

	VentanaTrainer trainer_actual;

	public ArbolTrainer(VentanaTrainer t)
	{
		trainer_actual = t;

		setSize(300, 150);
		setVisible(true);
		setLayout(null);

		// Construccion del arbol
		nodo_raiz = new DefaultMutableTreeNode("Areas Disciplina HCI");

		arbol_modelo = new DefaultTreeModel(nodo_raiz);

		arbol_total = new JTree(arbol_modelo);

		// Construccion de los datos del arbol
		nodo_reglasdeoro = new DefaultMutableTreeNode("Reglas de Oro");
		arbol_modelo.insertNodeInto(nodo_reglasdeoro, nodo_raiz, 0);

		nodo_regla_darcontrol = new DefaultMutableTreeNode(
				"Dar control al usuario");
		arbol_modelo.insertNodeInto(nodo_regla_darcontrol, nodo_reglasdeoro, 0);

		nodo_regla_reducirmemoria = new DefaultMutableTreeNode(
				"Reducir la carga memoria");
		arbol_modelo.insertNodeInto(nodo_regla_reducirmemoria, nodo_reglasdeoro,
				1);

		nodo_regla_consistencia = new DefaultMutableTreeNode(
				"Consistencia de la aplicacion");
		arbol_modelo.insertNodeInto(nodo_regla_consistencia, nodo_reglasdeoro,
				2);

		nodo_principios = new DefaultMutableTreeNode("Principios");
		arbol_modelo.insertNodeInto(nodo_principios, nodo_raiz, 1);

		nodo_principio_familiaridad = new DefaultMutableTreeNode(
				"Familiaridad del usuario");
		arbol_modelo.insertNodeInto(nodo_principio_familiaridad,
				nodo_principios, 0);

		nodo_principio_uniformidad = new DefaultMutableTreeNode(
				"Uniformidad en la interfaz");
		arbol_modelo.insertNodeInto(nodo_principio_uniformidad, nodo_principios,
				1);

		nodo_principio_minimasorpresa = new DefaultMutableTreeNode(
				"Minima sorpresa");
		arbol_modelo.insertNodeInto(nodo_principio_minimasorpresa,
				nodo_principios, 2);

		nodo_principio_recuperacion = new DefaultMutableTreeNode(
				"Recuperacion");
		arbol_modelo.insertNodeInto(nodo_principio_recuperacion,
				nodo_principios, 3);

		nodo_principio_asistencia = new DefaultMutableTreeNode(
				"Asistencia al usuario");
		arbol_modelo.insertNodeInto(nodo_principio_asistencia, nodo_principios,
				4);

		nodo_principio_diversidad = new DefaultMutableTreeNode(
				"Diversidad de usuarios");
		arbol_modelo.insertNodeInto(nodo_principio_diversidad, nodo_principios,
				5);

		nodo_caracterestetico = new DefaultMutableTreeNode("Caracter Estetico");
		arbol_modelo.insertNodeInto(nodo_caracterestetico, nodo_raiz, 2);

		nodo_carac_fondo = new DefaultMutableTreeNode("Fondo Background");
		arbol_modelo.insertNodeInto(nodo_carac_fondo, nodo_caracterestetico, 0);

		nodo_carac_texto = new DefaultMutableTreeNode("Espacio Texto");
		arbol_modelo.insertNodeInto(nodo_carac_texto, nodo_caracterestetico, 1);

		nodo_carac_imagenes = new DefaultMutableTreeNode("Espacio Imagenes");
		arbol_modelo.insertNodeInto(nodo_carac_imagenes, nodo_caracterestetico,
				2);

		nodo_carac_iconografia = new DefaultMutableTreeNode(
				"Botones e Iconografia");
		arbol_modelo.insertNodeInto(nodo_carac_iconografia,
				nodo_caracterestetico, 3);

		nodo_patrones = new DefaultMutableTreeNode("Patrones");
		arbol_modelo.insertNodeInto(nodo_patrones, nodo_raiz, 3);

		nodo_patrones_comportamiento = new DefaultMutableTreeNode(
				"Comportamiento");
		arbol_modelo.insertNodeInto(nodo_patrones_comportamiento, nodo_patrones,
				0);

		nodo_patrones_estructura = new DefaultMutableTreeNode("Estructura");
		arbol_modelo.insertNodeInto(nodo_patrones_estructura, nodo_patrones, 1);

		nodo_plantillas = new DefaultMutableTreeNode("Plantillas");
		arbol_modelo.insertNodeInto(nodo_plantillas, nodo_raiz, 4);

		nodo_plantillas_aplicacionales = new DefaultMutableTreeNode(
				"Aplicacionales");
		arbol_modelo.insertNodeInto(nodo_plantillas_aplicacionales,
				nodo_plantillas, 0);

		nodo_plantillas_metaforicas = new DefaultMutableTreeNode("Metaforicas");
		arbol_modelo.insertNodeInto(nodo_plantillas_metaforicas,
				nodo_plantillas, 1);

		nodo_otros = new DefaultMutableTreeNode("Otros");
		arbol_modelo.insertNodeInto(nodo_otros, nodo_raiz, 5);

		nodo_otros1 = new DefaultMutableTreeNode("Otros1");
		arbol_modelo.insertNodeInto(nodo_otros1, nodo_otros, 0);

		nodo_otros2 = new DefaultMutableTreeNode("Otros2");
		arbol_modelo.insertNodeInto(nodo_otros2, nodo_otros, 1);

		arbol_total.setEnabled(true);

		// Construccion y visualizacion de la ventana
		JScrollPane scroll = new JScrollPane(arbol_total);
		scroll.setLocation(5, 5);
		scroll.setSize(290, 140);
		scroll.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		add(scroll);

		DefaultTreeCellRenderer render = (DefaultTreeCellRenderer) arbol_total
				.getCellRenderer();
		render.setLeafIcon(new ImageIcon(
				"Soporte/Imagenes/imagenesArboltrainer/hoja.png"));
		render.setOpenIcon(new ImageIcon(
				"Soporte/Imagenes/imagenesArboltrainer/abierto.png"));
		render.setClosedIcon(new ImageIcon(
				"Soporte/Imagenes/imagenesArboltrainer/cerrado.png"));
		render.setTextNonSelectionColor(Color.BLACK);
		render.setTextSelectionColor(Color.WHITE);
		render.setBackgroundNonSelectionColor(Color.WHITE);
		render.setBackgroundSelectionColor(Color.BLACK);
		render.setFont(new Font("ArialBlack", Font.BOLD, 11));

		arbol_total.expandPath(arbol_total.getPathForRow(0));

		arbol_total.getSelectionModel().addTreeSelectionListener(this);
		arbol_total.addTreeExpansionListener(this);

	}

	public void valueChanged(TreeSelectionEvent e)
	{
		arbol_total.expandPath(arbol_total.getSelectionPath());
		arbol_path = "" + arbol_total.getSelectionPath();
		trainer_actual.boton_siguiente.setEnabled(true);

	}

	public void treeExpanded(TreeExpansionEvent event)
	{
		trainer_actual.radiobutton_reproducir.setEnabled(true);
		trainer_actual.radiobutton_editar.setEnabled(true);

	}

	public void treeCollapsed(TreeExpansionEvent event)
	{
	}

	public String getArbolpath()
	{
		return arbol_path;
	}

}
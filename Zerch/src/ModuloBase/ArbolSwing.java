package ModuloBase;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.tree.*;

@SuppressWarnings("serial")
public class ArbolSwing extends JPanel
		implements TreeSelectionListener, TreeExpansionListener
{
	JTree arbol_total;
	DefaultTreeModel arbol_modelo;

	DefaultMutableTreeNode nodo_raiz;

	DefaultMutableTreeNode nodo_scontenedores;
	DefaultMutableTreeNode nodo_spanel;

	DefaultMutableTreeNode nodo_sbotones;
	DefaultMutableTreeNode nodo_sbutton;
	DefaultMutableTreeNode nodo_scheckbox;
	DefaultMutableTreeNode nodo_sradiobutton;

	DefaultMutableTreeNode nodo_sselecciones;
	DefaultMutableTreeNode nodo_scombobox;
	DefaultMutableTreeNode nodo_slist;

	DefaultMutableTreeNode nodo_stextcampos;
	DefaultMutableTreeNode nodo_stextfield;
	DefaultMutableTreeNode nodo_spasswordfield;
	DefaultMutableTreeNode nodo_stextarea;

	DefaultMutableTreeNode nodo_svaloradores;
	DefaultMutableTreeNode nodo_sslider;

	DefaultMutableTreeNode nodo_sotros;
	DefaultMutableTreeNode nodo_slabel;
	DefaultMutableTreeNode nodo_sprogressbar;

	JButton b_aceptar;

	AgregarWidget agregar_actual;

	// constructor
	public ArbolSwing(AgregarWidget a)
	{
		agregar_actual = a;

		setSize(240, 200);
		setVisible(true);
		setLayout(null);

		// Construccion del arbol
		nodo_raiz = new DefaultMutableTreeNode("SComponents");

		arbol_modelo = new DefaultTreeModel(nodo_raiz);

		arbol_total = new JTree(arbol_modelo);

		// Construccion de los datos del arbol
		nodo_scontenedores = new DefaultMutableTreeNode("SContainers");
		arbol_modelo.insertNodeInto(nodo_scontenedores, nodo_raiz, 0);

		nodo_spanel = new DefaultMutableTreeNode("SPanel");
		arbol_modelo.insertNodeInto(nodo_spanel, nodo_scontenedores, 0);

		nodo_sbotones = new DefaultMutableTreeNode("SButtons");
		arbol_modelo.insertNodeInto(nodo_sbotones, nodo_raiz, 1);

		nodo_sbutton = new DefaultMutableTreeNode("SButton");
		arbol_modelo.insertNodeInto(nodo_sbutton, nodo_sbotones, 0);

		nodo_scheckbox = new DefaultMutableTreeNode("SCheckBox");
		arbol_modelo.insertNodeInto(nodo_scheckbox, nodo_sbotones, 1);

		nodo_sradiobutton = new DefaultMutableTreeNode("SRadioButton");
		arbol_modelo.insertNodeInto(nodo_sradiobutton, nodo_sbotones, 2);

		nodo_sselecciones = new DefaultMutableTreeNode("SSelects");
		arbol_modelo.insertNodeInto(nodo_sselecciones, nodo_raiz, 2);

		nodo_scombobox = new DefaultMutableTreeNode("SComboBox");
		arbol_modelo.insertNodeInto(nodo_scombobox, nodo_sselecciones, 0);

		nodo_slist = new DefaultMutableTreeNode("SList");
		arbol_modelo.insertNodeInto(nodo_slist, nodo_sselecciones, 1);

		nodo_stextcampos = new DefaultMutableTreeNode("STextfields");
		arbol_modelo.insertNodeInto(nodo_stextcampos, nodo_raiz, 3);

		nodo_stextfield = new DefaultMutableTreeNode("STextField");
		arbol_modelo.insertNodeInto(nodo_stextfield, nodo_stextcampos, 0);

		nodo_spasswordfield = new DefaultMutableTreeNode("SPasswordField");
		arbol_modelo.insertNodeInto(nodo_spasswordfield, nodo_stextcampos, 1);

		nodo_stextarea = new DefaultMutableTreeNode("STextArea");
		arbol_modelo.insertNodeInto(nodo_stextarea, nodo_stextcampos, 2);

		nodo_svaloradores = new DefaultMutableTreeNode("SValues");
		arbol_modelo.insertNodeInto(nodo_svaloradores, nodo_raiz, 4);

		nodo_sslider = new DefaultMutableTreeNode("SSlider");
		arbol_modelo.insertNodeInto(nodo_sslider, nodo_svaloradores, 0);

		nodo_sotros = new DefaultMutableTreeNode("SOthers");
		arbol_modelo.insertNodeInto(nodo_sotros, nodo_raiz, 5);

		nodo_sslider = new DefaultMutableTreeNode("SLabel");
		arbol_modelo.insertNodeInto(nodo_sslider, nodo_sotros, 0);

		nodo_sprogressbar = new DefaultMutableTreeNode("SProgressBar");
		arbol_modelo.insertNodeInto(nodo_sprogressbar, nodo_sotros, 1);

		arbol_total.setEnabled(true);

		// Construccion y visualizacion de la ventana
		JScrollPane scroll = new JScrollPane(arbol_total);
		scroll.setLocation(5, 5);
		scroll.setSize(230, 190);
		scroll.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		add(scroll);

		DefaultTreeCellRenderer render = (DefaultTreeCellRenderer) arbol_total
				.getCellRenderer();
		render.setLeafIcon(
				new ImageIcon("Soporte/Imagenes/imagenesArbolswing/hoja.png"));
		render.setOpenIcon(new ImageIcon(
				"Soporte/Imagenes/imagenesArbolswing/abierto.png"));
		render.setClosedIcon(new ImageIcon(
				"Soporte/Imagenes/imagenesArbolswing/cerrado.png"));
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

		String string_hoja = "" + arbol_total.getLastSelectedPathComponent();

		arbol_total.expandPath(arbol_total.getSelectionPath());

		if (string_hoja.equals("SPanel"))
		{
			agregar_actual.boton_aceptar.setEnabled(true);

			for (int a = 0; a < agregar_actual.vector_previos.size(); a++)
			{
				agregar_actual.widget_selecto = (Component) agregar_actual.vector_previos
						.elementAt(a);
				if (a != 0)
				{
					agregar_actual.widget_selecto.setVisible(false);
				} else
				{
					agregar_actual.widget_selecto.setVisible(true);
					agregar_actual.panel_propiedad.textfield_nombre
							.setText("SPanel...");
				}
			}
		}

		else if (string_hoja.equals("SButton"))
		{
			agregar_actual.boton_aceptar.setEnabled(true);

			for (int a = 0; a < agregar_actual.vector_previos.size(); a++)
			{
				agregar_actual.widget_selecto = (Component) agregar_actual.vector_previos
						.elementAt(a);
				if (a != 1)
				{
					agregar_actual.widget_selecto.setVisible(false);
				} else
				{
					agregar_actual.widget_selecto.setVisible(true);
					agregar_actual.panel_propiedad.textfield_nombre
							.setText("SButton...");
				}
			}
		}

		else if (string_hoja.equals("SCheckBox"))
		{
			agregar_actual.boton_aceptar.setEnabled(true);

			for (int a = 0; a < agregar_actual.vector_previos.size(); a++)
			{
				agregar_actual.widget_selecto = (Component) agregar_actual.vector_previos
						.elementAt(a);
				if (a != 2)
				{
					agregar_actual.widget_selecto.setVisible(false);
				} else
				{
					agregar_actual.widget_selecto.setVisible(true);
					agregar_actual.panel_propiedad.textfield_nombre
							.setText("SCheckBox...");
				}
			}
		}

		else if (string_hoja.equals("SRadioButton"))
		{
			agregar_actual.boton_aceptar.setEnabled(true);

			for (int a = 0; a < agregar_actual.vector_previos.size(); a++)
			{
				agregar_actual.widget_selecto = (Component) agregar_actual.vector_previos
						.elementAt(a);
				if (a != 3)
				{
					agregar_actual.widget_selecto.setVisible(false);
				} else
				{
					agregar_actual.widget_selecto.setVisible(true);
					agregar_actual.panel_propiedad.textfield_nombre
							.setText("SRadioButton...");
				}
			}
		}

		else if (string_hoja.equals("SComboBox"))
		{
			agregar_actual.boton_aceptar.setEnabled(true);

			for (int a = 0; a < agregar_actual.vector_previos.size(); a++)
			{
				agregar_actual.widget_selecto = (Component) agregar_actual.vector_previos
						.elementAt(a);
				if (a != 4)
				{
					agregar_actual.widget_selecto.setVisible(false);
				} else
				{
					agregar_actual.widget_selecto.setVisible(true);
					agregar_actual.panel_propiedad.textfield_nombre
							.setText("SComboBox...");
				}
			}
		}

		else if (string_hoja.equals("SList"))
		{
			agregar_actual.boton_aceptar.setEnabled(true);

			for (int a = 0; a < agregar_actual.vector_previos.size(); a++)
			{
				agregar_actual.widget_selecto = (Component) agregar_actual.vector_previos
						.elementAt(a);
				if (a != 5)
				{
					agregar_actual.widget_selecto.setVisible(false);
				} else
				{
					agregar_actual.widget_selecto.setVisible(true);
					agregar_actual.panel_propiedad.textfield_nombre
							.setText("SList...");
				}
			}
		}

		else if (string_hoja.equals("STextField"))
		{

			agregar_actual.boton_aceptar.setEnabled(true);

			for (int a = 0; a < agregar_actual.vector_previos.size(); a++)
			{
				agregar_actual.widget_selecto = (Component) agregar_actual.vector_previos
						.elementAt(a);
				if (a != 6)
				{
					agregar_actual.widget_selecto.setVisible(false);
				} else
				{
					agregar_actual.widget_selecto.setVisible(true);
					agregar_actual.panel_propiedad.textfield_nombre
							.setText("STextField...");
				}
			}
		}

		else if (string_hoja.equals("SPasswordField"))
		{

			agregar_actual.boton_aceptar.setEnabled(true);

			for (int a = 0; a < agregar_actual.vector_previos.size(); a++)
			{
				agregar_actual.widget_selecto = (Component) agregar_actual.vector_previos
						.elementAt(a);
				if (a != 7)
				{
					agregar_actual.widget_selecto.setVisible(false);
				} else
				{
					agregar_actual.widget_selecto.setVisible(true);
					agregar_actual.panel_propiedad.textfield_nombre
							.setText("SPasswordField...");
				}
			}
		}

		else if (string_hoja.equals("STextArea"))
		{

			agregar_actual.boton_aceptar.setEnabled(true);

			for (int a = 0; a < agregar_actual.vector_previos.size(); a++)
			{
				agregar_actual.widget_selecto = (Component) agregar_actual.vector_previos
						.elementAt(a);
				if (a != 8)
				{
					agregar_actual.widget_selecto.setVisible(false);
				} else
				{
					agregar_actual.widget_selecto.setVisible(true);
					agregar_actual.panel_propiedad.textfield_nombre
							.setText("STextArea...");
				}
			}
		}

		else if (string_hoja.equals("SSlider"))
		{

			agregar_actual.boton_aceptar.setEnabled(true);

			for (int a = 0; a < agregar_actual.vector_previos.size(); a++)
			{
				agregar_actual.widget_selecto = (Component) agregar_actual.vector_previos
						.elementAt(a);
				if (a != 9)
				{
					agregar_actual.widget_selecto.setVisible(false);
				} else
				{
					agregar_actual.widget_selecto.setVisible(true);
					agregar_actual.panel_propiedad.textfield_nombre
							.setText("SSlider...");
				}
			}
		}

		else if (string_hoja.equals("SLabel"))
		{
			agregar_actual.boton_aceptar.setEnabled(true);

			for (int a = 0; a < agregar_actual.vector_previos.size(); a++)
			{
				agregar_actual.widget_selecto = (Component) agregar_actual.vector_previos
						.elementAt(a);
				if (a != 10)
				{
					agregar_actual.widget_selecto.setVisible(false);
				} else
				{
					agregar_actual.widget_selecto.setVisible(true);
					agregar_actual.panel_propiedad.textfield_nombre
							.setText("SLabel...");
				}
			}
		}

		else if (string_hoja.equals("SProgressBar"))
		{
			agregar_actual.boton_aceptar.setEnabled(true);

			for (int a = 0; a < agregar_actual.vector_previos.size(); a++)
			{
				agregar_actual.widget_selecto = (Component) agregar_actual.vector_previos
						.elementAt(a);
				if (a != 11)
				{
					agregar_actual.widget_selecto.setVisible(false);
				} else
				{
					agregar_actual.widget_selecto.setVisible(true);
					agregar_actual.panel_propiedad.textfield_nombre
							.setText("SProgressBar");

				}
			}
		}

		else
		{
			for (int z = 0; z < agregar_actual.vector_previos.size(); z++)
			{
				agregar_actual.widget_selecto = (Component) agregar_actual.vector_previos
						.elementAt(z);
				agregar_actual.widget_selecto.setVisible(false);
			}

			agregar_actual.boton_aceptar.setEnabled(false);
		}

		agregar_actual.panel_propiedad.textfield_size_w
				.setText("" + agregar_actual.widget_selecto.getWidth());
		agregar_actual.panel_propiedad.textfield_size_h
				.setText("" + agregar_actual.widget_selecto.getHeight());
		agregar_actual.ploc = agregar_actual.widget_selecto.getLocation();
		agregar_actual.plocx = (int) agregar_actual.ploc.getX();
		agregar_actual.plocy = (int) agregar_actual.ploc.getY();
		agregar_actual.panel_propiedad.textfield_posx
				.setText("" + agregar_actual.funcion_base.getClickx());
		agregar_actual.panel_propiedad.textfield_posy
				.setText("" + agregar_actual.funcion_base.getClicky());

	}

	public void treeExpanded(TreeExpansionEvent event)
	{
	}

	public void treeCollapsed(TreeExpansionEvent event)
	{
	}

}
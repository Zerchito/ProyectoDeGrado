package ModuloPrototipo;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.*;

import ModuloBase.*;
import SwingWidget.*;

public class VentanaPrototipo extends JFrame implements java.io.Serializable
{
	JPanel panel_oeste;
	JPanel panel_sud;
	JPanel panel_centro;

	public FuncionPrototipo funcion_prototipo;

	PanelOpciones panel_opciones;
	PanelVectores panel_vectores;

	int cantidad = 0;

	FuncionBase funcion_base = null;

	Vector vector_nonull = new Vector();

	public VentanaPrototipo()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int) screenSize.getWidth() * 2 / 6 - 50,
				(int) screenSize.getHeight() * 1 / 5 - 50);
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(0);
		setLayout(new BorderLayout());
		setMinimumSize(new Dimension(370, 400));

		panel_oeste = new JPanel();
		panel_oeste.setPreferredSize(new Dimension(200, 200));
		panel_oeste.setLayout(null);
		panel_oeste.setBackground(new Color(140, 180, 225));
		panel_oeste.setBorder(new BevelBorder(BevelBorder.RAISED));
		add("West", panel_oeste);

		panel_sud = new JPanel();
		panel_sud.setPreferredSize(new Dimension(400, 60));
		panel_sud.setLayout(null);
		panel_sud.setBorder(new BevelBorder(BevelBorder.RAISED));
		add("South", panel_sud);

		panel_centro = new JPanel();
		panel_centro.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		panel_centro.setLayout(new BorderLayout());
		panel_centro.setBackground(Color.blue);
		add("Center", panel_centro);

		funcion_prototipo = new FuncionPrototipo(this);
		panel_centro.add("Center", funcion_prototipo);

		panel_opciones = new PanelOpciones(this);
		panel_opciones.setLocation(5, 5);
		panel_oeste.add(panel_opciones);

		panel_vectores = new PanelVectores(this);
		panel_vectores.setLocation(5, 5);
		panel_sud.add(panel_vectores);

		JButton b = new JButton("botonazo");

		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				cerrarEsto();
			}
		});

		this.setSize(this.getWidth(), this.getHeight() + 1);

	}

	public void fijarCantidad()
	{

		for (int a = 0; a < funcion_base.vector_ranuras.size(); a++)
		{
			if (funcion_base.vector_ranuras.elementAt(a) != null)
			{
				vector_nonull.add(funcion_base.vector_ranuras.elementAt(a));
			}
		}
		funcion_prototipo.ajustarNodos(vector_nonull);
	}

	public void fijarEnlaces(Vector v)
	{
		funcion_prototipo.ajustarEnlaces(v);
	}

	public void cerrarEsto()
	{

		funcion_prototipo.n = null;
		funcion_prototipo.e = null;

		funcion_prototipo.vector_nodos = null;
		funcion_prototipo.vector_enlaces = null;
		dispose();

	}

	public void setFuncionBase(FuncionBase fb)
	{
		funcion_base = fb;
		fijarCantidad();

		if (vector_nonull.size() >= 2)
		{
			buscarEnlaces();
		}

	}

	public void buscarEnlaces()
	{
		Vector vector_puntos = new Vector();

		for (int a = 0; a < vector_nonull.size(); a++)
		{

			Ranura ranura_actual = (Ranura) vector_nonull.elementAt(a);
			VentanaInterna ventana_actual = (VentanaInterna) ranura_actual.vector_widgets
					.elementAt(0);

			for (int b = 0; b < ventana_actual.vector_widgets.size(); b++)
			{

				JComponent componente_actual = (JComponent) ventana_actual.vector_widgets
						.elementAt(b);
				if (componente_actual.getClass().getName().endsWith("SButton"))
				{
					SButton widget_recorrido = (SButton) componente_actual;
					if ((widget_recorrido.getDestino() != -1)
							&& (widget_recorrido
									.getDestino() != widget_recorrido
											.getOrigen()))
					{

						Point p = new Point(widget_recorrido.getOrigen(),
								widget_recorrido.getDestino());
						vector_puntos.add(p);
					}
				}
				if (componente_actual.getClass().getName().endsWith("SPanel"))
				{
					SPanel widget_recorrido = (SPanel) componente_actual;
					vector_puntos = widget_recorrido
							.obtenerEnlaces(vector_puntos);
				}
				if (componente_actual.getClass().getName()
						.endsWith("Plantilla"))
				{
					Plantilla widget_recorrido = (Plantilla) componente_actual;
					vector_puntos = widget_recorrido
							.obtenerEnlaces(vector_puntos);
				}
			}

		}
		fijarEnlaces(vector_puntos);
	}

}
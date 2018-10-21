package ModuloPrototipo;

import javax.swing.*;
import java.awt.*;
import java.util.*;

import ModuloBase.*;

public class FuncionPrototipo extends Canvas implements java.io.Serializable
{

	Nodo n;
	Enlace e;

	Vector vector_nodos = new Vector();
	Vector vector_enlaces = new Vector();

	VentanaPrototipo ventana_prototipo;

	String figura = null;

	Point punto_anterior = null;

	int contar_click = 0;

	int modo = 0;

	int activado = 0;

	Nodo nodo_revisado = null;
	Nodo nodo_selecto = null;

	Nodo nodo_uno;
	Nodo nodo_dos;

	public int trabajo_conjunto = 0;

	boolean esEsta = false;

	Color color_fondo = new Color(75, 125, 230);

	FuncionBase funcion_base;

	public FuncionPrototipo(VentanaPrototipo v)
	{

		Graphics gc = getGraphics();
		ventana_prototipo = v;

		setLocation(0, 0);
		setPreferredSize(new Dimension(1000, 1000));
		setBackground(Color.white);

		setVisible(true);

		addMouseListener(new java.awt.event.MouseAdapter()
		{
			public void mouseClicked(java.awt.event.MouseEvent evt)
			{
				formMouseClicked(evt);
			}
		});

		addMouseMotionListener(new java.awt.event.MouseMotionAdapter()
		{
			public void mouseDragged(java.awt.event.MouseEvent evt)
			{
				formMouseDragged(evt);
			}
		});

		addMouseListener(new java.awt.event.MouseAdapter()
		{
			public void mousePressed(java.awt.event.MouseEvent evt)
			{
				formMousePressed(evt);
			}
		});

		addMouseListener(new java.awt.event.MouseAdapter()
		{
			public void mouseReleased(java.awt.event.MouseEvent evt)
			{
				formMouseReleased(evt);
			}
		});

	}

	public void paint(Graphics g)
	{
		if (vector_nodos != null)
		{
			for (int a = 0; a < vector_nodos.size(); a++)
			{
				if (vector_nodos.elementAt(a) != null)
				{
					Nodo n1 = (Nodo) vector_nodos.elementAt(a);
					n1.dibujar(this.getGraphics());
				}

			}
		}
		if (vector_enlaces != null)
		{
			for (int a = 0; a < vector_enlaces.size(); a++)
			{
				if (vector_enlaces.elementAt(a) != null)
				{
					Enlace e1 = (Enlace) vector_enlaces.elementAt(a);
					e1.dibujar(this.getGraphics());
				}
			}
		}
	}

	private void formMouseClicked(java.awt.event.MouseEvent evt)
	{

		if (modo == 1)
		{
			if (figura.equals("Nodo"))
			{
				n = new Nodo(this);
				n.locX = evt.getX();
				n.locY = evt.getY();
				n.dimW = 50;
				n.dimH = 50;
				n.setPosicion(vector_nodos.size());
				n.dibujar(this.getGraphics());
				vector_nodos.add(n);

				ventana_prototipo.panel_vectores.combo_nodos.addItem(""
						+ n.posicion + ": Ranura-"
						+ ventana_prototipo.funcion_base.vector_ranuras.size());
				ventana_prototipo.funcion_base.nuevoEntorno("hola");

			}

			if (figura.equals("Enlace"))
			{

				if (contar_click == 0)
				{
					if (vector_nodos != null)
					{
						// if(mover){

						int tam = vector_nodos.size() - 1;
						while ((!esEsta) && (0 <= tam))
						{
							if (vector_nodos.elementAt(tam) != null)
							{
								Nodo nodo_temp = (Nodo) vector_nodos
										.elementAt(tam);
								if (nodo_temp.pertenece(evt.getX(), evt.getY()))
								{

									esEsta = true;

									nodo_selecto = nodo_temp;
									nodo_selecto.color_fondo = color_fondo;
									nodo_selecto.color_borde = Color.cyan;
									nodo_uno = nodo_temp;
									contar_click++;
									repaint();

									esEsta = false;

								}
							}
							--tam;
						}
						if (!esEsta)
						{

						}
					}
				} else
				{
					if (vector_nodos != null)
					{

						int tam = vector_nodos.size() - 1;
						while ((!esEsta) && (0 <= tam))
						{
							if (vector_nodos.elementAt(tam) != null)
							{
								Nodo nodo_temp = (Nodo) vector_nodos
										.elementAt(tam);
								if (nodo_temp.pertenece(evt.getX(), evt.getY()))
								{

									esEsta = true;

									nodo_selecto = nodo_temp;
									nodo_selecto.color_fondo = color_fondo;
									nodo_selecto.color_borde = Color.cyan;
									nodo_dos = nodo_temp;
									contar_click--;
									repaint();

								}
							}
							--tam;
						}
						if (!esEsta)
						{
						}
						esEsta = false;

					}
					dibujarEnlace(nodo_uno, nodo_dos);
				}
			}

		}

		if (modo == 0)
		{

		}

	}

	private void formMouseDragged(java.awt.event.MouseEvent evt)
	{
		if (esEsta)
		{
			nodo_selecto.locX = evt.getX() - nodo_selecto.dimW / 2;
			nodo_selecto.locY = evt.getY() - nodo_selecto.dimH / 2;
			repaint();
		}
	}

	private void formMouseReleased(java.awt.event.MouseEvent evt)
	{
		if (modo == 2)
		{
			modo = 2;
			esEsta = false;
			nodo_selecto.color_fondo = Color.cyan;
			nodo_selecto.color_borde = color_fondo;
			repaint();
		}
	}

	private void formMousePressed(java.awt.event.MouseEvent evt)
	{
		if (modo == 2)
		{
			if (vector_nodos != null)
			{

				int tam = vector_nodos.size() - 1;
				while ((!esEsta) && (0 <= tam))
				{
					if (vector_nodos.elementAt(tam) != null)
					{
						Nodo nodo_temp = (Nodo) vector_nodos.elementAt(tam);
						if (nodo_temp.pertenece(evt.getX(), evt.getY()))
						{

							esEsta = true;

							nodo_selecto = nodo_temp;
							nodo_selecto.color_fondo = color_fondo;
							nodo_selecto.color_borde = Color.cyan;
							repaint();
						}
					}
					--tam;
				}
				if (!esEsta)
				{
				}
			}

		}

	}

	public void ajustarNodos(Vector vnonull)
	{
		int cx = (int) this.getPreferredSize().getWidth() / 4;
		int cy = (int) this.getPreferredSize().getHeight() / 4;
		double tita = 2 * 3.141516;
		int xf, yf;
		int incremento = (360 / vnonull.size());
		int radio = (cx / 2);
		Ranura r = null;
		int pos = 0;

		for (int angulo = 0; angulo < 360; angulo = angulo + incremento)
		{
			tita = Math.toRadians(angulo);
			xf = cx + (int) (radio * Math.cos(tita));
			yf = cy + (int) (radio * Math.sin(tita));

			n = new Nodo(this);
			n.locX = xf;
			n.locY = yf;
			n.dimW = 50;
			n.dimH = 50;

			r = (Ranura) vnonull.elementAt(pos);
			n.setPosicion(r.getPosicionranura());

			pos++;

			n.dibujar(this.getGraphics());
			vector_nodos.add(n);
			ventana_prototipo.panel_vectores.combo_nodos
					.addItem("" + n.posicion + ": Ranura-" + n.posicion);

			repaint();

		}

	}

	public void ajustarEnlaces(Vector v)
	{
		for (int a = 0; a < v.size(); a++)
		{
			Point p = (Point) v.elementAt(a);
			int x = (int) p.getX();
			int y = (int) p.getY();
			Nodo ni = (Nodo) vector_nodos.elementAt(x);
			Nodo nf = (Nodo) vector_nodos.elementAt(y);
			dibujarEnlace(ni, nf);
		}
	}

	public void setFuncionBase(FuncionBase fp)
	{
		funcion_base = fp;
	}

	public void mostrarVectores()
	{
		for (int a = 0; a < vector_nodos.size(); a++)
		{

		}
		for (int b = 0; b < vector_enlaces.size(); b++)
		{

		}

	}

	public void dibujarEnlace(Nodo ni, Nodo nf)
	{
		e = new Enlace(this);
		e.nodo_ini = ni;

		e.nodo_fin = nf;

		e.dibujar(this.getGraphics());

		e.nodo_ini.color_fondo = Color.cyan;
		e.nodo_ini.color_borde = color_fondo;

		e.nodo_fin.color_fondo = Color.cyan;
		e.nodo_fin.color_borde = color_fondo;

		vector_enlaces.add(e);
		e.posicion = vector_enlaces.size();

		ventana_prototipo.panel_vectores.combo_enlaces
				.addItem("" + e.posicion + ": Enlace-" + e.nodo_ini.posicion
						+ " a " + e.nodo_fin.posicion);
	}

}

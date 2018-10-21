package ModuloPrototipo;

import java.awt.*;

public class Enlace implements java.io.Serializable
{
	int locXi;
	int locYi;
	int locXf;
	int locYf;

	public Nodo nodo_ini;
	public Nodo nodo_fin;

	FuncionPrototipo funcion_prototipo;
	Color color_enlace = new Color(75, 125, 230);

	int posicion = 0;

	public Enlace(FuncionPrototipo r)
	{

		funcion_prototipo = r;

	}

	public void dibujar(Graphics g)
	{
		Graphics graphic_actual;
		graphic_actual = g;
		g.setColor(color_enlace);

		Point pi, pf;
		pi = new Point(nodo_ini.locX + nodo_ini.dimW / 2,
				nodo_ini.locY + nodo_ini.dimH / 2);
		pf = new Point(nodo_fin.locX + nodo_fin.dimW / 2,
				nodo_fin.locY + nodo_fin.dimH / 2);

		g.drawLine((int) pi.getX(), (int) pi.getY(), (int) pf.getX(),
				(int) pf.getY());
		g.setColor(Color.black);
		g.drawString("" + nodo_ini.posicion, (int) pi.getX() - 5,
				(int) pi.getY() - 5);
		g.drawString("" + nodo_fin.posicion, (int) pf.getX() - 5,
				(int) pf.getY() - 5);

		int r = 7;
		int fx = (int) (pi.getX() + r * pf.getX()) / (r + 1);
		int fy = (int) (pi.getY() + r * pf.getY()) / (r + 1);

		g.setColor(color_enlace);
		g.fillOval(fx - 4, fy - 4, 8, 8);

	}
}

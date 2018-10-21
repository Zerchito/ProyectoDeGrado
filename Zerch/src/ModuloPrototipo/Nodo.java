package ModuloPrototipo;

import java.awt.*;

public class Nodo implements java.io.Serializable
{
	int locX;
	int locY;
	int dimW;
	int dimH;
	int posicion = 0;

	Color color_borde = new Color(75, 125, 230);
	Color color_fondo = Color.cyan;

	FuncionPrototipo funcion_prototipo;

	public Nodo(FuncionPrototipo r)
	{
		funcion_prototipo = r;

	}

	public void dibujar(Graphics g)
	{
		Graphics graphic_actual;
		graphic_actual = g;
		g.setColor(color_borde);
		g.drawRect(locX, locY, dimW, dimH);
		g.setColor(color_fondo);
		g.fillRect(locX + 1, locY + 1, dimW - 1, dimH - 1);
		g.setColor(color_borde);
		g.fillRect(locX + dimW / 2 - 2, locY + dimH / 2 - 2, 4, 4);
		g.setColor(Color.black);
		g.drawString("Ran-" + posicion, locX + 5, locY - 2);

	}

	public boolean pertenece(int px, int py)
	{
		boolean resultado;
		if ((px <= locX + dimW) && (px >= locX) && (py <= locY + dimH)
				&& (py >= locY))
		{
			resultado = true;
		} else
		{
			resultado = false;
		}
		return resultado;
	}

	public void setPosicion(int v)
	{
		posicion = v;
	}

}

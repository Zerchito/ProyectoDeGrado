package ElementosServidor;

import java.util.ArrayList;

public abstract class ElementoInterface
{
	private int x;
	private int y;
	private int width;
	private int height;
	private String text;
	private int value;
	private int r_background;
	private int g_background;
	private int b_background;
	private int r_text;
	private int g_text;
	private int b_text;

	public ElementoInterface(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		text = "";
		value = 0;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public String getText()
	{
		return text;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public void setValue(int x)
	{
		value = x;
	}

	public int getValue()
	{
		return value;
	}

	public void setColorBackground(int r, int g, int b)
	{
		this.r_background = r;
		this.g_background = g;
		this.b_background = b;
	}
	public void setColorText(int r, int g, int b)
	{
		this.r_text = r;
		this.g_text = g;
		this.b_text = b;
	}
	
	public ArrayList<Integer> getColors()
	{
		ArrayList<Integer> respuesta = new ArrayList<>();
		respuesta.add(r_background);
		respuesta.add(g_background);
		respuesta.add(b_background);
		respuesta.add(r_text);
		respuesta.add(g_text);
		respuesta.add(b_text);
		return respuesta;
	}
}

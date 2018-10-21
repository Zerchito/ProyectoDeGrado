package ElementosServidor;

public class ComponenteServer extends ElementoInterface
{
	private String clase;
	private int posParent;

	public ComponenteServer(int x, int y, int w, int h, int r, String clase)
	{
		super(x, y, w, h);
		setPosParent(r);
		this.setClase(clase);
		this.setColorBackground(255, 255, 255);
		this.setColorText(51, 51, 51);
	}

	public int getPosParent()
	{
		return posParent;
	}

	public void setPosParent(int posParent)
	{
		this.posParent = posParent;
	}

	public String getClase()
	{
		return clase;
	}

	public void setClase(String clase)
	{
		this.clase = clase;
	}
}
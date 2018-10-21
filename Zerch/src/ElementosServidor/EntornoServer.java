package ElementosServidor;

import java.util.ArrayList;
import java.util.List;

public class EntornoServer extends ElementoInterface
{
	private String nombre;
	private List<ComponenteServer> componentes;

	public EntornoServer(String nombre)
	{
		super(0, 0, 300, 300);
		this.nombre = nombre;
		componentes = new ArrayList<>();
		setColorBackground(238, 238, 238);
		setColorText(51, 51, 51);
	}

	@Override
	public boolean equals(Object o)
	{
		boolean res = false;
		if (o instanceof EntornoServer)
		{
			EntornoServer otro = (EntornoServer) o;
			if (otro.nombre.equals(nombre))
				res = true;
		}
		return res;
	}

	public String getNombre()
	{
		return nombre;
	}

	public void agregarComponente(ComponenteServer componente)
	{
		componentes.add(componente);
	}

	public void setText(String text)
	{
		nombre = text;
	}

	public String getText()
	{
		return nombre;
	}

}
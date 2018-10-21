package ModuloBase;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class RotuloRanura extends JPanel
{
	FuncionBase funcion_base;
	JLabel lab;
	BotonRanura boton_ranura;
	String nombre;
	int posicion_ranura;

	public RotuloRanura(FuncionBase f)
	{
		funcion_base = f;

		this.setLayout(null);
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(90, 24));
		this.setBorder(new EtchedBorder());

		lab = new JLabel("Ventana_" + 000);
		lab.setBounds(5, 2, 65, 20);
		add(lab);

		boton_ranura = new BotonRanura(this);
		boton_ranura.setName("Cerrar");
		boton_ranura.setToolTipText("Cerrar");
		Icon icono_nodo = new ImageIcon("Soporte/Imagenes/cerrar2.png");
		boton_ranura.fijarIcono(icono_nodo);
		boton_ranura.setBackground(Color.white);
		boton_ranura.setLocation(70, 2);
		boton_ranura.setSize(20, 20);
		add(boton_ranura);

		this.setVisible(true);
	}

	public void setNombre(String nuevoNombre)
	{
		nombre = nuevoNombre;
		this.lab.setText(nuevoNombre);
	}

	public void setposicionRanura(int nueva_posicion_ranura)
	{
		posicion_ranura = nueva_posicion_ranura;
	}

	public String getNombre()
	{
		return nombre;
	}

	public int getPosicionRanura()
	{
		return posicion_ranura;
	}
}

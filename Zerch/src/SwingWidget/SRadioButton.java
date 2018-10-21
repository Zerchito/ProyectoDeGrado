package SwingWidget;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import ModuloBase.*;

@SuppressWarnings("serial")
public class SRadioButton extends JRadioButton
		implements Serializable, Bloqueable
{

	String nombre_corto = "SRadioButton";
	FuncionBase funcion_base;
	Color fondo;
	Color fuente;
	int posicion_ranura;

	boolean dimensionar = false;
	boolean mover = false;
	private boolean bloqueado;
	private boolean selected;
	private Color colorDesbloqueo;
	int origen = 0;
	int destino = 0;

	Cursor puntero;

	public SRadioButton(FuncionBase v)
	{
		bloqueado = false;
		selected = false;
		funcion_base = v;

		addMouseListener(new java.awt.event.MouseAdapter()
		{
			public void mousePressed(java.awt.event.MouseEvent evt)
			{
				if (!bloqueado)
					formMousePressed(evt);
			}
		});

		addMouseMotionListener(new java.awt.event.MouseMotionAdapter()
		{
			public void mouseDragged(java.awt.event.MouseEvent evt)
			{
				if (!bloqueado)
					formMouseDragged(evt);
			}
		});

		addMouseListener(new java.awt.event.MouseAdapter()
		{
			public void mouseReleased(java.awt.event.MouseEvent evt)
			{
				if (!bloqueado)
					formMouseReleased(evt);
			}
		});

		addMouseListener(new java.awt.event.MouseAdapter()
		{
			public void mouseClicked(java.awt.event.MouseEvent evt)
			{
				if (!bloqueado)
					formMouseClicked(evt);
			}
		});
	}

	private void formMousePressed(java.awt.event.MouseEvent evt)
	{
		funcion_base.SPmenu.menuitem_agregarwidget.setEnabled(false);
		funcion_base.SPmenu.menuitem_removerwidget.setEnabled(true);
		funcion_base.SPmenu.menuitem_exportarscript.setEnabled(true);
		funcion_base.SPmenu.menuitem_generarprototipo.setEnabled(true);

		addMouseMotionListener(new java.awt.event.MouseMotionAdapter()
		{
			public void mouseMoved(java.awt.event.MouseEvent evt)
			{
				if (!bloqueado)
					formMouseMoved(evt);
			}
		});

		funcion_base.widget_actual = this;
		this.requestFocus();
		funcion_base.ventana_base.panel_propiedad.textfield_nombre
				.setText("" + this.getText());
		funcion_base.ventana_base.panel_propiedad.textfield_size_w
				.setText("" + this.getWidth());
		funcion_base.ventana_base.panel_propiedad.textfield_size_h
				.setText("" + this.getHeight());
		Point ploc = this.getLocation();
		funcion_base.ventana_base.panel_propiedad.textfield_posx
				.setText("" + (int) ploc.getX());
		funcion_base.ventana_base.panel_propiedad.textfield_posy
				.setText("" + (int) ploc.getY());
		Color col = this.getBackground();
		funcion_base.ventana_base.color_selector.textfield_muestra
				.setBackground(col);
		Color coltexto = this.getForeground();
		funcion_base.ventana_base.color_selector_texto.textfield_muestra
				.setBackground(coltexto);

		funcion_base.posicion_actual = posicion_ranura;

		funcion_base.ventana_base.panel_opciones.boton_agregar_widget
				.setEnabled(false);
		funcion_base.ventana_base.panel_opciones.boton_remover_selecto
				.setEnabled(true);

		fondo = this.getBackground();
		int vr = fondo.getRed();
		int vg = fondo.getGreen();
		int vb = fondo.getBlue();
		funcion_base.ventana_base.color_selector.slider_r.setValue(vr);
		funcion_base.ventana_base.color_selector.slider_g.setValue(vg);
		funcion_base.ventana_base.color_selector.slider_b.setValue(vb);

		fuente = this.getForeground();
		int fr = fuente.getRed();
		int fg = fuente.getGreen();
		int fb = fuente.getBlue();
		funcion_base.ventana_base.color_selector_texto.slider_r.setValue(fr);
		funcion_base.ventana_base.color_selector_texto.slider_g.setValue(fg);
		funcion_base.ventana_base.color_selector_texto.slider_b.setValue(fb);

		funcion_base.ventana_base.fuente_texto.combo_muestra
				.setSelectedItem(this.getFont().getName());
		funcion_base.ventana_base.fuente_texto.slider_puntos
				.setValue(this.getFont().getSize());

		funcion_base.setClickx(evt.getX());
		funcion_base.setClicky(evt.getY());

		funcion_base.ventana_base.panel_click.textfield_click_x
				.setText("X:" + funcion_base.getClickx() + "");
		funcion_base.ventana_base.panel_click.textfield_click_y
				.setText("Y:" + funcion_base.getClicky() + "");

		funcion_base.setPosicionranura(posicion_ranura);

		funcion_base.ventana_base.panel_indicador.textfield_ranura
				.setText("" + posicion_ranura);

		funcion_base.ventana_base.panel_propiedad.textfield_nombre
				.setEnabled(true);
		funcion_base.ventana_base.panel_propiedad.combo_borde.setEnabled(false);
		funcion_base.ventana_base.panel_propiedad.textfield_argumento
				.setEnabled(false);

		funcion_base.ventana_base.panel_eventos.label_objeto
				.setText("" + nombre_corto);
		funcion_base.ventana_base.panel_eventos.textfield_destino
				.setEnabled(true);
		funcion_base.ventana_base.panel_eventos.textfield_destino
				.setText("" + destino);

		int x = evt.getX();
		int y = evt.getY();
		if ((x >= this.getWidth() - 10) && (x <= this.getWidth())
				&& (y >= this.getHeight() - 10) && (y <= this.getHeight()))
		{
			dimensionar = true;
		} else if ((x >= 0) && (x <= 10) && (y >= 0) && (y <= 10))
		{
			mover = true;
		}
		funcion_base.solicitarBloqueo(this, true);
	}

	private void formMouseDragged(java.awt.event.MouseEvent evt)
	{

		if (dimensionar == true)
		{
			this.setSize(evt.getX(), evt.getY());
		} else if (mover == true)
		{
			setLocation(this.getX() + evt.getX() - 10,
					this.getY() + evt.getY() - 10);
		}
	}

	private void formMouseReleased(java.awt.event.MouseEvent evt)
	{
		dimensionar = false;
		mover = false;
		if (evt.isPopupTrigger())
		{
			funcion_base.SPmenu.show(evt.getComponent(), evt.getX(),
					evt.getY());
		}
		funcion_base.solicitarBloqueo(this, false);
		funcion_base.moverWidget(this, this.getX(), this.getY(),
				this.getWidth(), this.getHeight());

	}

	private void formMouseClicked(java.awt.event.MouseEvent evt)
	{
		if (funcion_base.modalidad == 1)
		{

			if (destino >= 0)
			{
				funcion_base.ventana_base.carga_ranuras
						.setSelectedIndex(destino);
			}

		} else
		{

		}
	}

	private void formMouseMoved(java.awt.event.MouseEvent evt)
	{
		if (funcion_base.widget_actual.getClass().getName()
				.equals("SwingWidget.SRadioButton"))
		{
			int x = evt.getX();
			int y = evt.getY();

			funcion_base.ventana_base.panel_puntero.textfield_coordenadas_x
					.setText("X:" + x);
			funcion_base.ventana_base.panel_puntero.textfield_coordenadas_y
					.setText("Y:" + y);

			if ((x >= this.getWidth() - 10) && (x <= this.getWidth())
					&& (y >= this.getHeight() - 10) && (y <= this.getHeight()))
			{
				puntero = new Cursor(Cursor.SE_RESIZE_CURSOR);
			} else if ((x >= 0) && (x <= 10) && (y >= 0) && (y <= 10))
			{
				puntero = new Cursor(Cursor.MOVE_CURSOR);
			} else
			{
				puntero = new Cursor(Cursor.CROSSHAIR_CURSOR);
			}
			setCursor(puntero);
		}

	}

	public void setPosicionranura(int v)
	{
		posicion_ranura = v;
	}

	public int getPosicionranura()
	{
		return posicion_ranura;
	}

	public StringBuffer armarNombre(int v, String s)
	{
		StringBuffer retorno = new StringBuffer();
		for (int a = v + 1; a < s.length(); a++)
		{
			retorno.append(s.charAt(a));
		}
		return retorno;
	}

	public void setOrigen(int v)
	{
		origen = v;
	}

	public void setDestino(int v)
	{
		destino = v;
	}

	public int getOrigen()
	{
		return origen;
	}

	public int getDestino()
	{
		return destino;
	}

	public void bloquear(boolean estado)
	{
		if (estado)
		{
			dimensionar = false;
			mover = false;
			bloqueado = true;
			colorDesbloqueo = this.getBackground();
			this.setBackground(Color.darkGray);
			repaint();
		} else
		{
			bloqueado = false;
			this.setBackground(colorDesbloqueo);
		}
	}

	public boolean getSelected()
	{
		return selected;
	}

	public void setSelected()
	{
		selected = !selected;
	}
}
package SwingWidget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.Serializable;
import ModuloBase.*;

@SuppressWarnings("serial")
public class SProgressBar extends JProgressBar
		implements Serializable, Bloqueable
{
	String nombre_corto = "SProgressBar";
	private boolean bloqueado;
	private Color colorDesbloqueo;
	private boolean dragged;
	FuncionBase funcion_base;
	Color fondo;
	Color fuente;
	int posicion_ranura;
	boolean dimensionar = false;
	boolean mover = false;
	Cursor puntero;

	public SProgressBar(FuncionBase v)
	{
		funcion_base = v;
		bloqueado = false;
		dragged = false;
		addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent evt)
			{
				if (!bloqueado)
					formMousePressed(evt);
			}
		});

		addMouseMotionListener(new MouseMotionAdapter()
		{
			public void mouseDragged(MouseEvent evt)
			{
				if (!bloqueado)
					formMouseDragged(evt);
			}
		});

		addMouseListener(new MouseAdapter()
		{
			public void mouseReleased(MouseEvent evt)
			{
				if (!bloqueado)
					formMouseReleased(evt);
			}
		});
	}

	private void formMousePressed(MouseEvent evt)
	{
		funcion_base.SPmenu.menuitem_agregarwidget.setEnabled(false);
		funcion_base.SPmenu.menuitem_removerwidget.setEnabled(true);
		funcion_base.SPmenu.menuitem_exportarscript.setEnabled(true);
		funcion_base.SPmenu.menuitem_generarprototipo.setEnabled(true);

		addMouseMotionListener(new MouseMotionAdapter()
		{
			public void mouseMoved(MouseEvent evt)
			{
				if (!bloqueado)
					formMouseMoved(evt);
			}
		});

		funcion_base.widget_actual = this;
		this.requestFocus();
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

		if (this.getFont().getStyle() == Font.BOLD)
			funcion_base.ventana_base.fuente_texto.check_negrita
					.setSelected(true);
		else
			funcion_base.ventana_base.fuente_texto.check_negrita
					.setSelected(false);

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
				.setEnabled(false);
		funcion_base.ventana_base.panel_propiedad.combo_borde.setEnabled(true);
		funcion_base.ventana_base.panel_propiedad.textfield_argumento
				.setEnabled(false);

		funcion_base.ventana_base.panel_eventos.label_objeto
				.setText("" + nombre_corto);
		funcion_base.ventana_base.panel_eventos.textfield_destino
				.setEnabled(false);

		// actualizarVector();
		funcion_base.solicitarBloqueo(this, true);
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
	}

	private void formMouseDragged(MouseEvent evt)
	{
		if (dimensionar == true)
		{
			this.setSize(evt.getX(), evt.getY());
		} else if (mover == true)
		{
			setLocation(this.getX() + evt.getX() - 10,
					this.getY() + evt.getY() - 10);
		}
		dragged = true;
	}

	private void formMouseReleased(MouseEvent evt)
	{
		dimensionar = false;
		mover = false;
		if (evt.isPopupTrigger())
			funcion_base.SPmenu.show(evt.getComponent(), evt.getX(),
					evt.getY());
		if (dragged)
		{
			dragged = false;
			funcion_base.moverWidget(this, this.getX(), this.getY(),
					this.getWidth(), this.getHeight());
		}
		funcion_base.solicitarBloqueo(this, false);
	}

	private void formMouseMoved(MouseEvent evt)
	{
		if (funcion_base.widget_actual.getClass().getName()
				.equals("SwingWidget.SSlider"))
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
}
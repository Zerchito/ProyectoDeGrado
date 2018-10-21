package SwingWidget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

import ModuloBase.*;
import java.util.*;
import javax.swing.border.*;

@SuppressWarnings(
{ "serial", "rawtypes", "unchecked", "deprecation" })
public class SPanel extends JPanel implements java.io.Serializable, Bloqueable
{
	String nombre_corto = "SPanel";
	FuncionBase funcion_base;
	Color fondo;
	Color colorDesbloqueo;
	Color fuente;
	public int posicion_ranura;
	boolean dimensionar = false;
	boolean mover = false;
	boolean bloqueado = false;
	boolean dragged = false;
	Cursor puntero;
	public Vector vector_temporal = new Vector();
	public Vector vector_widgets = new Vector();

	public SPanel(FuncionBase v)
	{
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
	}

	private void formMousePressed(MouseEvent evt)
	{
		funcion_base.SPmenu.menuitem_agregarwidget.setEnabled(true);
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
				.setEnabled(true);
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

		funcion_base.ventana_base.fuente_texto.slider_puntos
				.setValue(this.getFont().getSize());
		if (this.getFont().getStyle() == Font.BOLD)
		{
			funcion_base.ventana_base.fuente_texto.check_negrita
					.setSelected(true);
		} else
		{
			funcion_base.ventana_base.fuente_texto.check_negrita
					.setSelected(false);
		}
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
		ploc = this.getLocation();
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
		if (evt.isPopupTrigger())
		{
			funcion_base.SPmenu.show(evt.getComponent(), evt.getX(),
					evt.getY());
		}
		dimensionar = false;
		mover = false;
		funcion_base.solicitarBloqueo(this, false);
		if (dragged)
		{
			dragged = false;
			funcion_base.moverWidget(this, this.getX(), this.getY(),
					this.getWidth(), this.getHeight());
		}
	}

	private void formMouseMoved(java.awt.event.MouseEvent evt)
	{
		if (funcion_base.widget_actual.getClass().getName()
				.equals("SwingWidget.SPanel"))
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

	public void reconstruirEntorno()
	{
		for (int a = 0; a < vector_temporal.size(); a++)
		{
			JComponent widget_recorrido = (JComponent) vector_temporal
					.elementAt(a);

			if (widget_recorrido.getClass().getName().endsWith("Plantilla"))
			{
				Plantilla widget_previo = (Plantilla) widget_recorrido;
				Plantilla widget_actual = new Plantilla(funcion_base);
				widget_actual.setLocation(widget_previo.getLocation());
				widget_actual.setSize(widget_previo.getSize());
				widget_actual.setLayout(widget_previo.getLayout());
				// widget_actual.setText(widget_previo.getText());
				widget_actual.setFont(widget_previo.getFont());
				// widget_actual.setBorder(widget_previo.getBorder());
				widget_actual.setBackground(widget_previo.getBackground());
				widget_actual.setBorder(new EtchedBorder());
				widget_actual.setPosicionranura(posicion_ranura);

				widget_actual.setBackground(widget_previo.getBackground());
				widget_actual.setVisible(true);

				widget_actual.vector_temporal = widget_previo.vector_widgets;
				widget_actual.reconstruirEntorno();

				add(widget_actual);
				vector_widgets.add(widget_actual);
			}

			if (widget_recorrido.getClass().getName().endsWith("SPanel"))
			{
				SPanel widget_previo = (SPanel) widget_recorrido;
				SPanel widget_actual = new SPanel(funcion_base);
				widget_actual.setLocation(widget_previo.getLocation());
				widget_actual.setSize(widget_previo.getSize());
				widget_actual.setLayout(widget_previo.getLayout());
				// widget_actual.setText(widget_previo.getText());
				widget_actual.setFont(widget_previo.getFont());
				widget_actual.setBorder(widget_previo.getBorder());
				widget_actual.setBackground(widget_previo.getBackground());
				widget_actual.setPosicionranura(posicion_ranura);

				widget_actual.setBackground(widget_previo.getBackground());
				widget_actual.setVisible(true);

				widget_actual.vector_temporal = widget_previo.vector_widgets;
				widget_actual.reconstruirEntorno();

				add(widget_actual);
				vector_widgets.add(widget_actual);
			}
			if (widget_recorrido.getClass().getName().endsWith("SButton"))
			{
				SButton widget_previo = (SButton) widget_recorrido;
				SButton widget_actual = new SButton(funcion_base);
				widget_actual.setLocation(widget_previo.getLocation());
				widget_actual.setSize(widget_previo.getSize());
				widget_actual.setLayout(widget_previo.getLayout());
				widget_actual.setText(widget_previo.getText());
				widget_actual.setFont(widget_previo.getFont());
				widget_actual.setBorder(widget_previo.getBorder());
				widget_actual.setBackground(widget_previo.getBackground());
				widget_actual.setPosicionranura(posicion_ranura);

				widget_actual.setText(widget_previo.getText());
				widget_actual.setOrigen(this.getPosicionranura());
				// widget_actual.setDestino(widget_previo.getDestino());

				widget_actual.setForeground(widget_previo.getForeground());
				widget_actual.setFont(widget_previo.getFont());
				widget_actual.setVisible(true);

				add(widget_actual);
				vector_widgets.add(widget_actual);
			}
			if (widget_recorrido.getClass().getName().endsWith("SRadioButton"))
			{
				SRadioButton widget_previo = (SRadioButton) widget_recorrido;
				SRadioButton widget_actual = new SRadioButton(funcion_base);
				widget_actual.setLocation(widget_previo.getLocation());
				widget_actual.setSize(widget_previo.getSize());
				widget_actual.setLayout(widget_previo.getLayout());
				widget_actual.setText(widget_previo.getText());
				widget_actual.setFont(widget_previo.getFont());
				widget_actual.setBorder(widget_previo.getBorder());
				widget_actual.setBackground(widget_previo.getBackground());
				widget_actual.setPosicionranura(posicion_ranura);

				widget_actual.setText(widget_previo.getText());
				// widget_actual.setOrigen(widget_previo.getOrigen());
				// widget_actual.setDestino(widget_previo.getDestino());

				widget_actual.setForeground(widget_previo.getForeground());
				widget_actual.setFont(widget_previo.getFont());
				widget_actual.setVisible(true);

				add(widget_actual);
				vector_widgets.add(widget_actual);
			}
			if (widget_recorrido.getClass().getName().endsWith("SCheckBox"))
			{
				SCheckBox widget_previo = (SCheckBox) widget_recorrido;
				SCheckBox widget_actual = new SCheckBox(funcion_base);
				widget_actual.setLocation(widget_previo.getLocation());
				widget_actual.setSize(widget_previo.getSize());
				widget_actual.setLayout(widget_previo.getLayout());
				widget_actual.setText(widget_previo.getText());
				widget_actual.setFont(widget_previo.getFont());
				widget_actual.setBorder(widget_previo.getBorder());
				widget_actual.setBackground(widget_previo.getBackground());
				widget_actual.setPosicionranura(posicion_ranura);

				widget_actual.setText(widget_previo.getText());
				// widget_actual.setOrigen(widget_previo.getOrigen());
				// widget_actual.setDestino(widget_previo.getDestino());

				widget_actual.setForeground(widget_previo.getForeground());
				widget_actual.setFont(widget_previo.getFont());
				widget_actual.setVisible(true);

				add(widget_actual);
				vector_widgets.add(widget_actual);
			}
			if (widget_recorrido.getClass().getName().endsWith("STextField"))
			{
				STextField widget_previo = (STextField) widget_recorrido;
				STextField widget_actual = new STextField(funcion_base);
				widget_actual.setLocation(widget_previo.getLocation());
				widget_actual.setSize(widget_previo.getSize());
				widget_actual.setLayout(widget_previo.getLayout());
				widget_actual.setText(widget_previo.getText());
				widget_actual.setFont(widget_previo.getFont());
				widget_actual.setBorder(widget_previo.getBorder());
				widget_actual.setBackground(widget_previo.getBackground());
				widget_actual.setPosicionranura(posicion_ranura);

				widget_actual.setText(widget_previo.getText());

				widget_actual.setForeground(widget_previo.getForeground());
				widget_actual.setFont(widget_previo.getFont());
				widget_actual.setVisible(true);

				add(widget_actual);
				vector_widgets.add(widget_actual);
			}
			if (widget_recorrido.getClass().getName()
					.endsWith("SPasswordField"))
			{
				SPasswordField widget_previo = (SPasswordField) widget_recorrido;
				SPasswordField widget_actual = new SPasswordField(funcion_base);
				widget_actual.setLocation(widget_previo.getLocation());
				widget_actual.setSize(widget_previo.getSize());
				widget_actual.setLayout(widget_previo.getLayout());
				widget_actual.setText(widget_previo.getText());
				widget_actual.setFont(widget_previo.getFont());
				widget_actual.setBorder(widget_previo.getBorder());
				widget_actual.setBackground(widget_previo.getBackground());
				widget_actual.setPosicionranura(posicion_ranura);

				widget_actual.setText(widget_previo.getText());

				widget_actual.setForeground(widget_previo.getForeground());
				widget_actual.setFont(widget_previo.getFont());
				widget_actual.setVisible(true);

				add(widget_actual);
				vector_widgets.add(widget_actual);
			}
			if (widget_recorrido.getClass().getName().endsWith("STextArea"))
			{
				STextArea widget_previo = (STextArea) widget_recorrido;
				STextArea widget_actual = new STextArea(funcion_base);
				widget_actual.setLocation(widget_previo.getLocation());
				widget_actual.setSize(widget_previo.getSize());
				widget_actual.setLayout(widget_previo.getLayout());
				widget_actual.setText(widget_previo.getText());
				widget_actual.setFont(widget_previo.getFont());
				widget_actual.setBorder(widget_previo.getBorder());
				widget_actual.setBackground(widget_previo.getBackground());
				widget_actual.setPosicionranura(posicion_ranura);

				widget_actual.setText(widget_previo.getText());

				widget_actual.setForeground(widget_previo.getForeground());
				widget_actual.setFont(widget_previo.getFont());
				widget_actual.setVisible(true);

				add(widget_actual);
				vector_widgets.add(widget_actual);
			}
			if (widget_recorrido.getClass().getName().endsWith("SList"))
			{
				SList widget_previo = (SList) widget_recorrido;
				SList widget_actual = new SList(funcion_base);
				widget_actual.setLocation(widget_previo.getLocation());
				widget_actual.setSize(widget_previo.getSize());
				widget_actual.setLayout(widget_previo.getLayout());
				// widget_actual.setText(widget_previo.getText());
				widget_actual.setFont(widget_previo.getFont());
				widget_actual.setBorder(widget_previo.getBorder());
				widget_actual.setBackground(widget_previo.getBackground());
				widget_actual.setPosicionranura(posicion_ranura);

				widget_actual.vector_argumentos = widget_previo.vector_argumentos;

				widget_actual.setForeground(widget_previo.getForeground());
				widget_actual.setFont(widget_previo.getFont());
				widget_actual.setVisible(true);

				add(widget_actual);
				vector_widgets.add(widget_actual);
			}
			if (widget_recorrido.getClass().getName().endsWith("SComboBox"))
			{
				SComboBox widget_previo = (SComboBox) widget_recorrido;
				SComboBox widget_actual = new SComboBox(funcion_base);
				widget_actual.setLocation(widget_previo.getLocation());
				widget_actual.setSize(widget_previo.getSize());
				// widget_actual.setLayout(widget_previo.getLayout());
				// widget_actual.setText(widget_previo.getText());
				widget_actual.setFont(widget_previo.getFont());
				widget_actual.setBorder(widget_previo.getBorder());
				widget_actual.setBackground(widget_previo.getBackground());
				widget_actual.setPosicionranura(posicion_ranura);

				widget_actual.vector_argumentos = widget_previo.vector_argumentos;

				widget_actual.setForeground(widget_previo.getForeground());
				widget_actual.setFont(widget_previo.getFont());
				widget_actual.setVisible(true);

				add(widget_actual);
				vector_widgets.add(widget_actual);
			}
			if (widget_recorrido.getClass().getName().endsWith("SLabel"))
			{
				SLabel widget_previo = (SLabel) widget_recorrido;
				SLabel widget_actual = new SLabel(funcion_base);
				widget_actual.setLocation(widget_previo.getLocation());
				widget_actual.setSize(widget_previo.getSize());
				widget_actual.setLayout(widget_previo.getLayout());
				widget_actual.setText(widget_previo.getText());
				widget_actual.setFont(widget_previo.getFont());
				widget_actual.setBorder(widget_previo.getBorder());
				widget_actual.setBackground(widget_previo.getBackground());
				widget_actual.setPosicionranura(posicion_ranura);

				widget_actual.setText(widget_previo.getText());

				widget_actual.setForeground(widget_previo.getForeground());
				widget_actual.setFont(widget_previo.getFont());
				widget_actual.setVisible(true);

				add(widget_actual);
				vector_widgets.add(widget_actual);
			}
			if (widget_recorrido.getClass().getName().endsWith("SSlider"))
			{
				SSlider widget_previo = (SSlider) widget_recorrido;
				SSlider widget_actual = new SSlider(funcion_base);
				widget_actual.setLocation(widget_previo.getLocation());
				widget_actual.setSize(widget_previo.getSize());
				widget_actual.setLayout(widget_previo.getLayout());
				// widget_actual.setText(widget_previo.getText());
				widget_actual.setFont(widget_previo.getFont());
				widget_actual.setBorder(widget_previo.getBorder());
				widget_actual.setBackground(widget_previo.getBackground());
				widget_actual.setPosicionranura(posicion_ranura);

				widget_actual.setBackground(widget_previo.getBackground());
				widget_actual.setVisible(true);

				add(widget_actual);
				vector_widgets.add(widget_actual);
			}
			if (widget_recorrido.getClass().getName().endsWith("SProgressBar"))
			{
				SProgressBar widget_previo = (SProgressBar) widget_recorrido;
				SProgressBar widget_actual = new SProgressBar(funcion_base);
				widget_actual.setLocation(widget_previo.getLocation());
				widget_actual.setSize(widget_previo.getSize());
				widget_actual.setLayout(widget_previo.getLayout());
				// widget_actual.setText(widget_previo.getText());
				widget_actual.setFont(widget_previo.getFont());
				widget_actual.setBorder(widget_previo.getBorder());
				widget_actual.setBackground(widget_previo.getBackground());
				widget_actual.setPosicionranura(posicion_ranura);

				widget_actual.setBackground(widget_previo.getBackground());
				widget_actual.setVisible(true);

				add(widget_actual);
				vector_widgets.add(widget_actual);
			}
		}
		vector_temporal = new Vector();
	}

	public String declaracionesScript(String v)
	{
		StringBuffer sb = new StringBuffer();
		for (int a = 0; a < vector_widgets.size(); a++)
		{
			JComponent widget_recorrido = (JComponent) vector_widgets
					.elementAt(a);
			if (widget_recorrido.getClass().getName().endsWith("Plantilla"))
			{
				Plantilla widget_actual = (Plantilla) widget_recorrido;
				sb.append("JPanel panel_" + v + a + ";\n");
				sb.append(" " + widget_actual.declaracionesScript("" + v + a));
			}
			if (widget_recorrido.getClass().getName().endsWith("SPanel"))
			{
				SPanel widget_actual = (SPanel) widget_recorrido;
				sb.append("JPanel panel_" + v + a + ";\n");
				sb.append(" " + widget_actual.declaracionesScript("" + v + a));
			}
			if (widget_recorrido.getClass().getName().endsWith("SButton"))
			{
				sb.append(" JButton button_" + v + a + ";\n");
			}
			if (widget_recorrido.getClass().getName().endsWith("SRadioButton"))
			{
				sb.append(" JRadioButton radiobutton_" + v + a + ";\n");
			}
			if (widget_recorrido.getClass().getName().endsWith("SCheckBox"))
			{
				sb.append(" JCheckBox checkbox_" + v + a + ";\n");
			}
			if (widget_recorrido.getClass().getName().endsWith("STextField"))
			{
				sb.append(" JTextField textfield_" + v + a + ";\n");
			}
			if (widget_recorrido.getClass().getName()
					.endsWith("SPasswordField"))
			{
				sb.append(" JPasswordField passwordfield_" + v + a + ";\n");
			}
			if (widget_recorrido.getClass().getName().endsWith("STextArea"))
			{
				sb.append(" JTextArea textarea_" + v + a + ";\n");
			}
			if (widget_recorrido.getClass().getName().endsWith("SList"))
			{
				sb.append(" JList list_" + v + a + ";\n");
			}
			if (widget_recorrido.getClass().getName().endsWith("SComboBox"))
			{
				sb.append(" JComboBox combobox_" + v + a + ";\n");
			}
			if (widget_recorrido.getClass().getName().endsWith("SLabel"))
			{
				sb.append(" JLabel label_" + v + a + ";\n");
			}
			if (widget_recorrido.getClass().getName().endsWith("SSlider"))
			{
				sb.append(" JSlider slider_" + v + a + ";\n");
			}
			if (widget_recorrido.getClass().getName().endsWith("SProgressBar"))
			{
				sb.append(" JProgressBar progressbar_" + v + a + ";\n");
			}
		}
		return sb.toString();
	}

	public String especificacionesScript(String c, String v)
	{
		StringBuffer sb = new StringBuffer();
		for (int a = 0; a < vector_widgets.size(); a++)
		{
			JComponent widget_recorrido = (JComponent) vector_widgets
					.elementAt(a);

			if (widget_recorrido.getClass().getName().endsWith("Plantilla"))
			{
				Plantilla widget_actual = (Plantilla) widget_recorrido;
				sb.append(" panel_" + v + a + " = new JPanel();\n");
				sb.append(" panel_" + v + a + ".setSize("
						+ widget_actual.getWidth() + ","
						+ widget_actual.getHeight() + ");\n");
				Point ploc = widget_actual.getLocation();
				int plocx = (int) ploc.getX();
				int plocy = (int) ploc.getY();
				sb.append(" panel_" + v + a + ".setLocation(" + plocx + ","
						+ plocy + ");\n");
				Color color_fondo = widget_actual.getBackground();
				int colorf_r = color_fondo.getRed();
				int colorf_g = color_fondo.getGreen();
				int colorf_b = color_fondo.getBlue();
				sb.append(" panel_" + v + a + ".setBackground(new Color("
						+ colorf_r + "," + colorf_g + "," + colorf_b + "));\n");
				Color color_texto = widget_actual.getForeground();
				int colort_r = color_texto.getRed();
				int colort_g = color_texto.getGreen();
				int colort_b = color_texto.getBlue();
				sb.append(" panel_" + v + a + ".setForeground(new Color("
						+ colort_r + "," + colort_g + "," + colort_b + "));\n");
				String borde = verBorde(widget_actual.getBorder());
				sb.append(" panel_" + v + a + ".setBorder(" + borde + ");\n");
				sb.append(" panel_" + v + a + ".setLayout(null);\n");
				sb.append(" " + c + ".add(panel_" + v + a + ");\n");
				sb.append(" \n");
				sb.append(" " + widget_actual
						.especificacionesScript("panel_" + v + a, "" + v + a));
			}

			if (widget_recorrido.getClass().getName().endsWith("SPanel"))
			{
				SPanel widget_actual = (SPanel) widget_recorrido;
				sb.append(" panel_" + v + a + " = new JPanel();\n");
				sb.append(" panel_" + v + a + ".setSize("
						+ widget_actual.getWidth() + ","
						+ widget_actual.getHeight() + ");\n");
				Point ploc = widget_actual.getLocation();
				int plocx = (int) ploc.getX();
				int plocy = (int) ploc.getY();
				sb.append(" panel_" + v + a + ".setLocation(" + plocx + ","
						+ plocy + ");\n");
				Color color_fondo = widget_actual.getBackground();
				int colorf_r = color_fondo.getRed();
				int colorf_g = color_fondo.getGreen();
				int colorf_b = color_fondo.getBlue();
				sb.append(" panel_" + v + a + ".setBackground(new Color("
						+ colorf_r + "," + colorf_g + "," + colorf_b + "));\n");
				Color color_texto = widget_actual.getForeground();
				int colort_r = color_texto.getRed();
				int colort_g = color_texto.getGreen();
				int colort_b = color_texto.getBlue();
				sb.append(" panel_" + v + a + ".setForeground(new Color("
						+ colort_r + "," + colort_g + "," + colort_b + "));\n");
				String borde = verBorde(widget_actual.getBorder());
				sb.append(" panel_" + v + a + ".setBorder(" + borde + ");\n");
				sb.append(" panel_" + v + a + ".setLayout(null);\n");
				sb.append(" " + c + ".add(panel_" + v + a + ");\n");
				sb.append(" \n");
				sb.append(" " + widget_actual
						.especificacionesScript("panel_" + v + a, "" + v + a));

			}
			if (widget_recorrido.getClass().getName().endsWith("SButton"))
			{
				SButton widget_actual = (SButton) widget_recorrido;
				sb.append(" button_" + v + a + " = new JButton();\n");
				sb.append(" button_" + v + a + ".setSize("
						+ widget_actual.getWidth() + ","
						+ widget_actual.getHeight() + ");\n");
				Point ploc = widget_actual.getLocation();
				int plocx = (int) ploc.getX();
				int plocy = (int) ploc.getY();
				sb.append(" button_" + v + a + ".setLocation(" + plocx + ","
						+ plocy + ");\n");
				Color color_fondo = widget_actual.getBackground();
				int colorf_r = color_fondo.getRed();
				int colorf_g = color_fondo.getGreen();
				int colorf_b = color_fondo.getBlue();
				sb.append(" button_" + v + a + ".setBackground(new Color("
						+ colorf_r + "," + colorf_g + "," + colorf_b + "));\n");
				Color color_texto = widget_actual.getForeground();
				int colort_r = color_texto.getRed();
				int colort_g = color_texto.getGreen();
				int colort_b = color_texto.getBlue();
				sb.append(" button_" + v + a + ".setForeground(new Color("
						+ colort_r + "," + colort_g + "," + colort_b + "));\n");
				String fuente = verFuente(widget_actual.getFont());
				sb.append(" button_" + v + a + ".setFont(" + fuente + ");\n");
				sb.append(" button_" + v + a + ".setText(\""
						+ widget_actual.getText() + "\");\n");
				sb.append(" button_" + v + a + ".setLayout(null);\n");
				sb.append(" " + c + ".add(button_" + v + a + ");\n");
				sb.append(" \n");
			}
			if (widget_recorrido.getClass().getName().endsWith("SRadioButton"))
			{
				SRadioButton widget_actual = (SRadioButton) widget_recorrido;
				sb.append(" radiobutton_" + v + a + " = new JRadioButton();\n");
				sb.append(" radiobutton_" + v + a + ".setSize("
						+ widget_actual.getWidth() + ","
						+ widget_actual.getHeight() + ");\n");
				Point ploc = widget_actual.getLocation();
				int plocx = (int) ploc.getX();
				int plocy = (int) ploc.getY();
				sb.append(" radiobutton_" + v + a + ".setLocation(" + plocx
						+ "," + plocy + ");\n");
				Color color_fondo = widget_actual.getBackground();
				int colorf_r = color_fondo.getRed();
				int colorf_g = color_fondo.getGreen();
				int colorf_b = color_fondo.getBlue();
				sb.append(" radiobutton_" + v + a + ".setBackground(new Color("
						+ colorf_r + "," + colorf_g + "," + colorf_b + "));\n");
				Color color_texto = widget_actual.getForeground();
				int colort_r = color_texto.getRed();
				int colort_g = color_texto.getGreen();
				int colort_b = color_texto.getBlue();
				sb.append(" radiobutton_" + v + a + ".setForeground(new Color("
						+ colort_r + "," + colort_g + "," + colort_b + "));\n");
				String fuente = verFuente(widget_actual.getFont());
				sb.append(" radiobutton_" + v + a + ".setFont(" + fuente
						+ ");\n");
				sb.append(" radiobutton_" + v + a + ".setText(\""
						+ widget_actual.getText() + "\");\n");
				sb.append(" radiobutton_" + v + a + ".setLayout(null);\n");
				sb.append(" " + c + ".add(radiobutton_" + v + a + ");\n");
				sb.append(" \n");
			}
			if (widget_recorrido.getClass().getName().endsWith("SCheckBox"))
			{
				SCheckBox widget_actual = (SCheckBox) widget_recorrido;
				sb.append(" checkbox_" + v + a + " = new JCheckBox();\n");
				sb.append(" checkbox_" + v + a + ".setSize("
						+ widget_actual.getWidth() + ","
						+ widget_actual.getHeight() + ");\n");
				Point ploc = widget_actual.getLocation();
				int plocx = (int) ploc.getX();
				int plocy = (int) ploc.getY();
				sb.append(" checkbox_" + v + a + ".setLocation(" + plocx + ","
						+ plocy + ");\n");
				Color color_fondo = widget_actual.getBackground();
				int colorf_r = color_fondo.getRed();
				int colorf_g = color_fondo.getGreen();
				int colorf_b = color_fondo.getBlue();
				sb.append(" checkbox_" + v + a + ".setBackground(new Color("
						+ colorf_r + "," + colorf_g + "," + colorf_b + "));\n");
				Color color_texto = widget_actual.getForeground();
				int colort_r = color_texto.getRed();
				int colort_g = color_texto.getGreen();
				int colort_b = color_texto.getBlue();
				sb.append(" checkbox_" + v + a + ".setForeground(new Color("
						+ colort_r + "," + colort_g + "," + colort_b + "));\n");
				String fuente = verFuente(widget_actual.getFont());
				sb.append(" checkbox_" + v + a + ".setFont(" + fuente + ");\n");
				sb.append(" checkbox_" + v + a + ".setText(\""
						+ widget_actual.getText() + "\");\n");
				sb.append(" checkbox_" + v + a + ".setLayout(null);\n");
				sb.append(" " + c + ".add(checkbox_" + v + a + ");\n");
				sb.append(" \n");
			}
			if (widget_recorrido.getClass().getName().endsWith("STextField"))
			{
				STextField widget_actual = (STextField) widget_recorrido;
				sb.append(" textfield_" + v + a + " = new JTextField();\n");
				sb.append(" textfield_" + v + a + ".setSize("
						+ widget_actual.getWidth() + ","
						+ widget_actual.getHeight() + ");\n");
				Point ploc = widget_actual.getLocation();
				int plocx = (int) ploc.getX();
				int plocy = (int) ploc.getY();
				sb.append(" textfield_" + v + a + ".setLocation(" + plocx + ","
						+ plocy + ");\n");
				Color color_fondo = widget_actual.getBackground();
				int colorf_r = color_fondo.getRed();
				int colorf_g = color_fondo.getGreen();
				int colorf_b = color_fondo.getBlue();
				sb.append(" textfield_" + v + a + ".setBackground(new Color("
						+ colorf_r + "," + colorf_g + "," + colorf_b + "));\n");
				Color color_texto = widget_actual.getForeground();
				int colort_r = color_texto.getRed();
				int colort_g = color_texto.getGreen();
				int colort_b = color_texto.getBlue();
				sb.append(" textfield_" + v + a + ".setForeground(new Color("
						+ colort_r + "," + colort_g + "," + colort_b + "));\n");
				String borde = verBorde(widget_actual.getBorder());
				sb.append(
						" textfield_" + v + a + ".setBorder(" + borde + ");\n");
				String fuente = verFuente(widget_actual.getFont());
				sb.append(
						" textfield_" + v + a + ".setFont(" + fuente + ");\n");
				sb.append(" textfield_" + v + a + ".setText(\""
						+ widget_actual.getText() + "\");\n");
				sb.append(" textfield_" + v + a + ".setLayout(null);\n");
				sb.append(" " + c + ".add(textfield_" + v + a + ");\n");
				sb.append(" \n");
			}
			if (widget_recorrido.getClass().getName()
					.endsWith("SPasswordField"))
			{
				SPasswordField widget_actual = (SPasswordField) widget_recorrido;
				sb.append(" passwordfield_" + v + a
						+ " = new JPasswordField();\n");
				sb.append(" passwordfield_" + v + a + ".setSize("
						+ widget_actual.getWidth() + ","
						+ widget_actual.getHeight() + ");\n");
				Point ploc = widget_actual.getLocation();
				int plocx = (int) ploc.getX();
				int plocy = (int) ploc.getY();
				sb.append(" passwordfield_" + v + a + ".setLocation(" + plocx
						+ "," + plocy + ");\n");
				Color color_fondo = widget_actual.getBackground();
				int colorf_r = color_fondo.getRed();
				int colorf_g = color_fondo.getGreen();
				int colorf_b = color_fondo.getBlue();
				sb.append(" passwordfield_" + v + a
						+ ".setBackground(new Color(" + colorf_r + ","
						+ colorf_g + "," + colorf_b + "));\n");
				Color color_texto = widget_actual.getForeground();
				int colort_r = color_texto.getRed();
				int colort_g = color_texto.getGreen();
				int colort_b = color_texto.getBlue();
				sb.append(" passwordfield_" + v + a
						+ ".setForeground(new Color(" + colort_r + ","
						+ colort_g + "," + colort_b + "));\n");
				String borde = verBorde(widget_actual.getBorder());
				sb.append(" passwordfield_" + v + a + ".setBorder(" + borde
						+ ");\n");
				String fuente = verFuente(widget_actual.getFont());
				sb.append(" passwordfield_" + v + a + ".setFont(" + fuente
						+ ");\n");
				sb.append(" passwordfield_" + v + a + ".setText(\""
						+ widget_actual.getText() + "\");\n");
				sb.append(" passwordfield_" + v + a + ".setLayout(null);\n");
				sb.append(" " + c + ".add(passwordfield_" + v + a + ");\n");
				sb.append(" \n");
			}
			if (widget_recorrido.getClass().getName().endsWith("STextArea"))
			{
				STextArea widget_actual = (STextArea) widget_recorrido;
				sb.append(" textarea_" + v + a + " = new JTextArea();\n");
				sb.append(" textarea_" + v + a + ".setSize("
						+ widget_actual.getWidth() + ","
						+ widget_actual.getHeight() + ");\n");
				Point ploc = widget_actual.getLocation();
				int plocx = (int) ploc.getX();
				int plocy = (int) ploc.getY();
				sb.append(" textarea_" + v + a + ".setLocation(" + plocx + ","
						+ plocy + ");\n");
				Color color_fondo = widget_actual.getBackground();
				int colorf_r = color_fondo.getRed();
				int colorf_g = color_fondo.getGreen();
				int colorf_b = color_fondo.getBlue();
				sb.append(" textarea_" + v + a + ".setBackground(new Color("
						+ colorf_r + "," + colorf_g + "," + colorf_b + "));\n");
				Color color_texto = widget_actual.getForeground();
				int colort_r = color_texto.getRed();
				int colort_g = color_texto.getGreen();
				int colort_b = color_texto.getBlue();
				sb.append(" textarea_" + v + a + ".setForeground(new Color("
						+ colort_r + "," + colort_g + "," + colort_b + "));\n");
				String borde = verBorde(widget_actual.getBorder());
				sb.append(
						" textarea_" + v + a + ".setBorder(" + borde + ");\n");
				String fuente = verFuente(widget_actual.getFont());
				sb.append(" textarea_" + v + a + ".setFont(" + fuente + ");\n");
				String string_texto = widget_actual.getText();
				StringBuffer buffer_texto = new StringBuffer();
				for (int z = 0; z < string_texto.length(); z++)
				{
					if (string_texto.charAt(z) == ('\n'))
					{
						buffer_texto.append("\\n ");
					} else
					{
						buffer_texto.append(string_texto.charAt(z));
					}
				}
				// widget_actual.setText(buffer_texto.toString());
				sb.append(" textarea_" + v + a + ".setText(\"" + buffer_texto
						+ "\");\n");
				sb.append(" textarea_" + v + a + ".setLayout(null);\n");
				sb.append(" " + c + ".add(textarea_" + v + a + ");\n");
				sb.append(" \n");
			}
			if (widget_recorrido.getClass().getName().endsWith("SList"))
			{
				SList widget_actual = (SList) widget_recorrido;
				sb.append(" list_" + v + a + " = new JList();\n");
				sb.append(" list_" + v + a + ".setSize("
						+ widget_actual.getWidth() + ","
						+ widget_actual.getHeight() + ");\n");
				Point ploc = widget_actual.getLocation();
				int plocx = (int) ploc.getX();
				int plocy = (int) ploc.getY();
				sb.append(" list_" + v + a + ".setLocation(" + plocx + ","
						+ plocy + ");\n");
				Color color_fondo = widget_actual.getBackground();
				int colorf_r = color_fondo.getRed();
				int colorf_g = color_fondo.getGreen();
				int colorf_b = color_fondo.getBlue();
				sb.append(" list_" + v + a + ".setBackground(new Color("
						+ colorf_r + "," + colorf_g + "," + colorf_b + "));\n");
				Color color_texto = widget_actual.getForeground();
				int colort_r = color_texto.getRed();
				int colort_g = color_texto.getGreen();
				int colort_b = color_texto.getBlue();
				sb.append(" list_" + v + a + ".setForeground(new Color("
						+ colort_r + "," + colort_g + "," + colort_b + "));\n");
				String fuente = verFuente(widget_actual.getFont());
				sb.append(" list_" + v + a + ".setFont(" + fuente + ");\n");

				sb.append(" String [] arg_list_" + v + a + "="
						+ traducirLista("" + widget_actual.vector_argumentos)
						+ ";\n");
				sb.append(" list_" + v + a + ".setListData(arg_list_" + v + a
						+ ");\n");
				// for(int y=0;y<widget_actual.vector_argumentos.size();y++)
				// {
				// sb.append("
				// combobox_"+v+a+".addItem(\""+widget_actual.vector_argumentos.elementAt(y)+"\");\n");
				// }
				// sb.append(" list_"+v+a+".setLayout(null);\n");
				sb.append(" " + c + ".add(list_" + v + a + ");\n");
				sb.append(" \n");
			}
			if (widget_recorrido.getClass().getName().endsWith("SComboBox"))
			{
				SComboBox widget_actual = (SComboBox) widget_recorrido;
				sb.append(" combobox_" + v + a + " = new JComboBox();\n");
				sb.append(" combobox_" + v + a + ".setSize("
						+ widget_actual.getWidth() + ","
						+ widget_actual.getHeight() + ");\n");
				Point ploc = widget_actual.getLocation();
				int plocx = (int) ploc.getX();
				int plocy = (int) ploc.getY();
				sb.append(" combobox_" + v + a + ".setLocation(" + plocx + ","
						+ plocy + ");\n");
				Color color_fondo = widget_actual.getBackground();
				int colorf_r = color_fondo.getRed();
				int colorf_g = color_fondo.getGreen();
				int colorf_b = color_fondo.getBlue();
				sb.append(" combobox_" + v + a + ".setBackground(new Color("
						+ colorf_r + "," + colorf_g + "," + colorf_b + "));\n");
				Color color_texto = widget_actual.getForeground();
				int colort_r = color_texto.getRed();
				int colort_g = color_texto.getGreen();
				int colort_b = color_texto.getBlue();
				sb.append(" combobox_" + v + a + ".setForeground(new Color("
						+ colort_r + "," + colort_g + "," + colort_b + "));\n");
				String fuente = verFuente(widget_actual.getFont());
				sb.append(" combobox_" + v + a + ".setFont(" + fuente + ");\n");

				for (int y = 0; y < widget_actual.vector_argumentos.size(); y++)
				{
					sb.append(" combobox_" + v + a + ".addItem(\""
							+ widget_actual.vector_argumentos.elementAt(y)
							+ "\");\n");
				}
				// sb.append(" combobox_"+v+a+".setLayout(null);\n");
				sb.append(" " + c + ".add(combobox_" + v + a + ");\n");
				sb.append(" \n");
			}
			if (widget_recorrido.getClass().getName().endsWith("SLabel"))
			{
				SLabel widget_actual = (SLabel) widget_recorrido;
				sb.append(" label_" + v + a + " = new JLabel();\n");
				sb.append(" label_" + v + a + ".setSize("
						+ widget_actual.getWidth() + ","
						+ widget_actual.getHeight() + ");\n");
				Point ploc = widget_actual.getLocation();
				int plocx = (int) ploc.getX();
				int plocy = (int) ploc.getY();
				sb.append(" label_" + v + a + ".setLocation(" + plocx + ","
						+ plocy + ");\n");
				Color color_fondo = widget_actual.getBackground();
				int colorf_r = color_fondo.getRed();
				int colorf_g = color_fondo.getGreen();
				int colorf_b = color_fondo.getBlue();
				sb.append(" label_" + v + a + ".setBackground(new Color("
						+ colorf_r + "," + colorf_g + "," + colorf_b + "));\n");
				Color color_texto = widget_actual.getForeground();
				int colort_r = color_texto.getRed();
				int colort_g = color_texto.getGreen();
				int colort_b = color_texto.getBlue();
				sb.append(" label_" + v + a + ".setForeground(new Color("
						+ colort_r + "," + colort_g + "," + colort_b + "));\n");
				String borde = verBorde(widget_actual.getBorder());
				sb.append(" label_" + v + a + ".setBorder(" + borde + ");\n");
				String fuente = verFuente(widget_actual.getFont());
				sb.append(" label_" + v + a + ".setFont(" + fuente + ");\n");
				sb.append(" label_" + v + a + ".setText(\""
						+ widget_actual.getText() + "\");\n");
				sb.append(" label_" + v + a + ".setLayout(null);\n");
				sb.append(" " + c + ".add(label_" + v + a + ");\n");
				sb.append(" \n");
			}
			if (widget_recorrido.getClass().getName().endsWith("SSlider"))
			{
				SSlider widget_actual = (SSlider) widget_recorrido;
				sb.append(" slider_" + v + a + " = new JSlider();\n");
				sb.append(" slider_" + v + a + ".setSize("
						+ widget_actual.getWidth() + ","
						+ widget_actual.getHeight() + ");\n");
				Point ploc = widget_actual.getLocation();
				int plocx = (int) ploc.getX();
				int plocy = (int) ploc.getY();
				sb.append(" slider_" + v + a + ".setLocation(" + plocx + ","
						+ plocy + ");\n");
				Color color_fondo = widget_actual.getBackground();
				int colorf_r = color_fondo.getRed();
				int colorf_g = color_fondo.getGreen();
				int colorf_b = color_fondo.getBlue();
				sb.append(" slider_" + v + a + ".setBackground(new Color("
						+ colorf_r + "," + colorf_g + "," + colorf_b + "));\n");
				Color color_texto = widget_actual.getForeground();
				int colort_r = color_texto.getRed();
				int colort_g = color_texto.getGreen();
				int colort_b = color_texto.getBlue();
				sb.append(" slider_" + v + a + ".setForeground(new Color("
						+ colort_r + "," + colort_g + "," + colort_b + "));\n");
				sb.append(" slider_" + v + a + ".setLayout(null);\n");
				sb.append(" " + c + ".add(slider_" + v + a + ");\n");
				sb.append(" \n");
			}
			if (widget_recorrido.getClass().getName().endsWith("SProgressBar"))
			{
				SProgressBar widget_actual = (SProgressBar) widget_recorrido;
				sb.append(" progressbar_" + v + a + " = new JProgressBar();\n");
				sb.append(" progressbar_" + v + a + ".setSize("
						+ widget_actual.getWidth() + ","
						+ widget_actual.getHeight() + ");\n");
				Point ploc = widget_actual.getLocation();
				int plocx = (int) ploc.getX();
				int plocy = (int) ploc.getY();
				sb.append(" progressbar_" + v + a + ".setLocation(" + plocx
						+ "," + plocy + ");\n");
				Color color_fondo = widget_actual.getBackground();
				int colorf_r = color_fondo.getRed();
				int colorf_g = color_fondo.getGreen();
				int colorf_b = color_fondo.getBlue();
				sb.append(" progressbar_" + v + a + ".setBackground(new Color("
						+ colorf_r + "," + colorf_g + "," + colorf_b + "));\n");
				Color color_texto = widget_actual.getForeground();
				int colort_r = color_texto.getRed();
				int colort_g = color_texto.getGreen();
				int colort_b = color_texto.getBlue();
				sb.append(" progressbar_" + v + a + ".setForeground(new Color("
						+ colort_r + "," + colort_g + "," + colort_b + "));\n");
				String borde = verBorde(widget_actual.getBorder());
				sb.append(" progressbar_" + v + a + ".setBorder(" + borde
						+ ");\n");
				sb.append(" progressbar_" + v + a + ".setLayout(null);\n");
				sb.append(" " + c + ".add(progressbar_" + v + a + ");\n");
				sb.append(" \n");
			}

		}
		return sb.toString();
	}

	public String agregarListener(String c, String v)
	{
		StringBuffer sb = new StringBuffer();
		for (int a = 0; a < vector_widgets.size(); a++)
		{
			JComponent widget_recorrido = (JComponent) vector_widgets
					.elementAt(a);

			if (widget_recorrido.getClass().getName().endsWith("Plantilla"))
			{
				Plantilla widget_actual = (Plantilla) widget_recorrido;
				sb.append(" " + widget_actual
						.agregarListener("plantilla_" + v + a, "" + v + a));
			}

			if (widget_recorrido.getClass().getName().endsWith("SPanel"))
			{
				SPanel widget_actual = (SPanel) widget_recorrido;
				sb.append(" " + widget_actual.agregarListener("panel_" + v + a,
						"" + v + a));
			}

			if (widget_recorrido.getClass().getName().endsWith("SButton"))
			{
				SButton widget_actual = (SButton) widget_recorrido;
				if (widget_actual.getDestino() != -1)
				{
					sb.append(
							" button_" + v + a + ".addActionListener(this);\n");
				} else
				{
				}
			}
		}
		return sb.toString();
	}

	public String metodosEnlaceScript(String c, String v)
	{
		StringBuffer sb = new StringBuffer();
		for (int a = 0; a < vector_widgets.size(); a++)
		{
			JComponent widget_recorrido = (JComponent) vector_widgets
					.elementAt(a);

			if (widget_recorrido.getClass().getName().endsWith("Plantilla"))
			{
				Plantilla widget_actual = (Plantilla) widget_recorrido;
				sb.append(" " + widget_actual
						.metodosEnlaceScript("plantilla_" + v + a, "" + v + a));
			}

			if (widget_recorrido.getClass().getName().endsWith("SPanel"))
			{
				SPanel widget_actual = (SPanel) widget_recorrido;
				sb.append(" " + widget_actual
						.metodosEnlaceScript("panel_" + v + a, "" + v + a));
			}

			if (widget_recorrido.getClass().getName().endsWith("SButton"))
			{
				SButton widget_actual = (SButton) widget_recorrido;
				sb.append(" if(ae.getSource()==" + "button_" + v + a + ")\n");
				sb.append(" {\n");
				if (widget_actual.getDestino() != -1)
				{
					sb.append(" Sintitulo_" + widget_actual.getDestino() + " sn"
							+ widget_actual.getDestino() + "= new Sintitulo_"
							+ widget_actual.getDestino() + "();\n");
					sb.append("this.dispose();\n");
				} else
				{
				}
				sb.append(" }\n");
			}
		}
		return sb.toString();
	}

	public void buscarWidget(JComponent jc)
	{

		for (int a = 0; a < vector_widgets.size(); a++)
		{
			JComponent widget_recorrido = (JComponent) vector_widgets
					.elementAt(a);

			if (widget_recorrido.getClass().getName().endsWith("Plantilla"))
			{
				Plantilla widget_actual = (Plantilla) widget_recorrido;
				if (widget_recorrido == jc)
				{
					remove(widget_recorrido);
					vector_widgets.removeElementAt(
							vector_widgets.indexOf(widget_recorrido));
				} else
				{
					widget_actual.buscarWidget(jc);

				}
			}
			if (widget_recorrido.getClass().getName().endsWith("SPanel"))
			{
				SPanel widget_actual = (SPanel) widget_recorrido;
				if (widget_recorrido == jc)
				{
					remove(widget_recorrido);
					vector_widgets.removeElementAt(
							vector_widgets.indexOf(widget_recorrido));
				} else
				{
					widget_actual.buscarWidget(jc);

				}
			}
			if (widget_recorrido.getClass().getName().endsWith("SButton"))
			{
				if (widget_recorrido == jc)
				{
					remove(widget_recorrido);
					vector_widgets.removeElementAt(
							vector_widgets.indexOf(widget_recorrido));
				}
			}
			if (widget_recorrido.getClass().getName().endsWith("SRadioButton"))
			{
				if (widget_recorrido == jc)
				{
					remove(widget_recorrido);
					vector_widgets.removeElementAt(
							vector_widgets.indexOf(widget_recorrido));
				}
			}
			if (widget_recorrido.getClass().getName().endsWith("SCheckBox"))
			{
				if (widget_recorrido == jc)
				{
					remove(widget_recorrido);
					vector_widgets.removeElementAt(
							vector_widgets.indexOf(widget_recorrido));
				}
			}
			if (widget_recorrido.getClass().getName().endsWith("STextField"))
			{
				if (widget_recorrido == jc)
				{
					remove(widget_recorrido);
					vector_widgets.removeElementAt(
							vector_widgets.indexOf(widget_recorrido));
				}
			}
			if (widget_recorrido.getClass().getName()
					.endsWith("SPasswordField"))
			{
				if (widget_recorrido == jc)
				{
					remove(widget_recorrido);
					vector_widgets.removeElementAt(
							vector_widgets.indexOf(widget_recorrido));
				}
			}
			if (widget_recorrido.getClass().getName().endsWith("STextArea"))
			{
				if (widget_recorrido == jc)
				{
					remove(widget_recorrido);
					vector_widgets.removeElementAt(
							vector_widgets.indexOf(widget_recorrido));
				}
			}
			if (widget_recorrido.getClass().getName().endsWith("SList"))
			{
				if (widget_recorrido == jc)
				{
					remove(widget_recorrido);
					vector_widgets.removeElementAt(
							vector_widgets.indexOf(widget_recorrido));
				}
			}
			if (widget_recorrido.getClass().getName().endsWith("SComboBox"))
			{
				if (widget_recorrido == jc)
				{
					remove(widget_recorrido);
					vector_widgets.removeElementAt(
							vector_widgets.indexOf(widget_recorrido));
				}
			}
			if (widget_recorrido.getClass().getName().endsWith("SLabel"))
			{
				if (widget_recorrido == jc)
				{
					remove(widget_recorrido);
					vector_widgets.removeElementAt(
							vector_widgets.indexOf(widget_recorrido));
				}
			}
			if (widget_recorrido.getClass().getName().endsWith("SSlider"))
			{
				if (widget_recorrido == jc)
				{
					remove(widget_recorrido);
					vector_widgets.removeElementAt(
							vector_widgets.indexOf(widget_recorrido));
				}
			}
			if (widget_recorrido.getClass().getName().endsWith("SProgressBar"))
			{
				if (widget_recorrido == jc)
				{
					remove(widget_recorrido);
					vector_widgets.removeElementAt(
							vector_widgets.indexOf(widget_recorrido));
				}
			}

		}

		setSize(getWidth() + 1, getHeight());

	}

	public String verBorde(Border b)
	{
		String string_borde_final = "null";
		String string_borde = "";

		if (b != null)
		{
			string_borde = "" + b.getClass().getName();
			if (string_borde.endsWith("EtchedBorder"))
			{
				string_borde_final = "new EtchedBorder()";
			}
			if (string_borde.endsWith("BevelBorder"))
			{
				string_borde_final = "new BevelBorder(BevelBorder.RAISED)";
			}
			if (string_borde.endsWith("SoftBevelBorder"))
			{
				string_borde_final = "new SoftBevelBorder(BevelBorder.LOWERED)";
			}

		} else
		{
			string_borde_final = "null";
		}
		return string_borde_final;
	}

	public String verFuente(Font f)
	{
		String string_fuente_final = "";
		String string_fuente_nombre = "" + f.getName();
		String string_fuente_estilo = "" + f.getStyle();
		String string_fuente_dimension = "" + f.getSize();

		string_fuente_final = "new Font(\"" + string_fuente_nombre + "\","
				+ string_fuente_estilo + "," + string_fuente_dimension + ")";

		return string_fuente_final;
	}

	public String traducirLista(String s)
	{
		String retorno = "";
		StringBuffer buffer_argumentos = new StringBuffer();
		for (int a = 0; a < s.length(); a++)
		{
			if (s.charAt(a) == ('['))
			{
				buffer_argumentos.append("{\"");
			} else if (s.charAt(a) == (']'))
			{
				buffer_argumentos.append("\"}");
			} else if (s.charAt(a) == (','))
			{
				buffer_argumentos.append("\",\"");
			} else
			{
				buffer_argumentos.append(s.charAt(a));
			}
		}
		retorno = buffer_argumentos.toString();

		return retorno;
	}

	public Vector obtenerEnlaces(Vector v)
	{
		Vector vector_retorno = v;

		for (int b = 0; b < vector_widgets.size(); b++)
		{

			JComponent componente_actual = (JComponent) vector_widgets
					.elementAt(b);
			if (componente_actual.getClass().getName().endsWith("SButton"))
			{
				SButton widget_recorrido = (SButton) componente_actual;
				if ((widget_recorrido.getDestino() != -1) && (widget_recorrido
						.getDestino() != widget_recorrido.getOrigen()))
				{
					Point p = new Point(widget_recorrido.getOrigen(),
							widget_recorrido.getDestino());
					vector_retorno.add(p);
				}
			}
			if (componente_actual.getClass().getName().endsWith("SPanel"))
			{
				SPanel widget_recorrido = (SPanel) componente_actual;
				vector_retorno = widget_recorrido
						.obtenerEnlaces(vector_retorno);
			}
			if (componente_actual.getClass().getName().endsWith("Plantilla"))
			{
				Plantilla widget_recorrido = (Plantilla) componente_actual;
				vector_retorno = widget_recorrido
						.obtenerEnlaces(vector_retorno);
			}
		}
		return vector_retorno;
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

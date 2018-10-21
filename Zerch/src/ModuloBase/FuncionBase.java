package ModuloBase;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyVetoException;
import java.rmi.RemoteException;
import java.util.*;
import java.util.List;
import java.io.*;
import Conexion.AdministradorEventos;
import Interfaz.VentanaPrincipal;
import InterfazBase.PanelColorFondo;
import ModuloWizard.*;
import SwingWidget.*;
import ModuloScript.*;
import ModuloPrototipo.*;
import SoporteAyuda.*;

@SuppressWarnings(
{ "serial", "rawtypes", "unchecked", "unused", "static-access" })
public class FuncionBase implements java.io.Serializable
{
	// almacenadores de objetos
	public Vector vector_ranuras = new Vector();
	public Vector vector_entrada = new Vector();
	public Vector vector_salida = new Vector();

	// <para controlar Objetos>
	private List<JComponent> componentes;

	// seleccionado
	public JComponent widget_actual;
	public JComponent widget_capturado;
	public JComponent widget_clonado;

	public int posicion_actual = 0;

	int posicion_ranura;

	int posicion_contenedor;

	// =??
	Font fuente_general;
	Font fuente_especial;

	// donde se presiono por ultima ves el mouse
	int click_x;
	int click_y;

	// el enlace a su interfaz grafica
	public VentanaPrincipal ventana_base;

	// instanciadores de los modulos
	VentanaTrainer trainer_actual;
	VentanaPrototipo ventana_prototipo;

	// indicadores para las modalidades
	public int modalidad = 0;
	public int consejero = 0;

	public SPopupMenu SPmenu;

	private AdministradorEventos administradorE;

	// constructor de la base funcional
	public FuncionBase(VentanaPrincipal ventanaPrincipal)
	{
		SPmenu = new SPopupMenu(this);
		ventana_base = ventanaPrincipal;
		componentes = new ArrayList<>();
	}

	// nueva ranura para la edicion
	public void nuevoEntorno(String nombre)
	{
		Ranura ranura_centro = new Ranura(this);
		ranura_centro.setPosicionranura(vector_ranuras.size(), nombre);
		ventana_base.carga_ranuras.addTab(
				"Ranura_" + ventana_base.carga_ranuras.getTabCount(), null,
				ranura_centro.scrolgrilla,
				"Ranura_" + ventana_base.carga_ranuras.getTabCount());
		ventana_base.carga_ranuras.setTabComponentAt(
				ventana_base.carga_ranuras.getTabCount() - 1,
				ranura_centro.rotulo_ranura);
		VentanaInterna internal = new VentanaInterna(this);
		internal.init(nombre);
		componentes.add(internal);
		ranura_centro.panel_dibujo.add(internal, new Integer(1));
		internal.setPosicionranura(vector_ranuras.size());
		ranura_centro.vector_widgets.add(internal);
		vector_ranuras.add(ranura_centro);
	}

	// guardar entorno
	public void guardarEntorno()
	{
		JFileChooser file1 = new JFileChooser();
		FiltroBase ent = new FiltroBase(new String("ent"),
				"Entorno de Edicion");
		file1.addChoosableFileFilter(ent);
		int seleccion = file1.showSaveDialog(null);

		if (seleccion == JFileChooser.APPROVE_OPTION)
		{
			String path = file1.getSelectedFile().toString();

			File factual = new File(path);
			if (factual.exists())
			{
				int confirmexiste = JOptionPane.showConfirmDialog(null,
						"El archivo ya existe..Desea Reemplazarlo?");
				if (confirmexiste == 0)
				{
					factual.delete();

					String stb = path;
					StringBuffer sb = new StringBuffer();
					char auxb;
					for (int b = 0; b < stb.length(); b++)
					{
						auxb = stb.charAt(b);
						if (auxb == '.')
						{
							break;
						} else
						{
							sb.append(auxb);
						}
					}
					Guardar("" + sb);
				}
			} else
			{
				Guardar(path);
			}

		} else if (seleccion == JFileChooser.CANCEL_OPTION)
		{
			JOptionPane.showMessageDialog(null, "Se cancelo la operacion.");
		}
	}

	// serializar entorno
	void Guardar(String p)
	{
		try
		{
			ObjectOutputStream salida = new ObjectOutputStream(
					new FileOutputStream(p + ".ent"));
			vector_salida = vector_ranuras;
			salida.writeObject(vector_salida);

			salida.close();
			JOptionPane.showMessageDialog(null,
					"Entorno Serializado con exito");
		} catch (IOException ex)
		{
			JOptionPane.showMessageDialog(null,
					"La serializacion fallo por el siguiente detalle: \n" + ex,
					"ADVERTENCIA: Falla en la serializacion",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	// recuperar serializacion entorno
	public void recuperarEntorno()
	{
		JFileChooser file1 = new JFileChooser();
		FiltroBase pla = new FiltroBase(new String("pla"),
				"Plantilla Estandarizada");
		FiltroBase ent = new FiltroBase(new String("ent"),
				"Entorno de Edicion");
		file1.addChoosableFileFilter(pla);
		file1.addChoosableFileFilter(ent);
		int seleccion = file1.showOpenDialog(null);
		if (seleccion == JFileChooser.APPROVE_OPTION)
		{
			File chosen = file1.getSelectedFile();
			if (chosen.getName().endsWith(".ent"))
			{
				String nombre = file1.getSelectedFile().getPath();
				Abrir(nombre);
				JOptionPane.showMessageDialog(null,
						"Entorno recuperado con exito");
			}
		} else if (seleccion == JFileChooser.CANCEL_OPTION)
		{

		}

		ventana_base.color_selector.slider_r.setEnabled(true);
		ventana_base.color_selector.slider_g.setEnabled(true);
		ventana_base.color_selector.slider_b.setEnabled(true);

		ventana_base.color_selector_texto.slider_r.setEnabled(true);
		ventana_base.color_selector_texto.slider_g.setEnabled(true);
		ventana_base.color_selector_texto.slider_b.setEnabled(true);

		ventana_base.fuente_texto.slider_puntos.setEnabled(true);

	}

	// reconstruir entorno
	void cargarVector()
	{
		for (int a = 0; a < vector_entrada.size(); a++)
		{
			if (vector_entrada.elementAt(a) != null)
			{
				Ranura ranura_previa = (Ranura) vector_entrada.elementAt(a);
				Ranura ranura_centro = new Ranura(this);
				ranura_centro.vector_temporal = ranura_previa.vector_widgets;
				ranura_centro.setPosicionranura(vector_ranuras.size(),
						ranura_previa.getNombre());
				// ranura_centro.vector_temporal=ranura_previa.vector_widgets;
				ventana_base.carga_ranuras.addTab("Ranura", null,
						ranura_centro.scrolgrilla, "Ranura");
				ventana_base.carga_ranuras.setTabComponentAt(
						ventana_base.carga_ranuras.getTabCount() - 1,
						ranura_centro.rotulo_ranura);
				ranura_centro.reconstruirEntorno();
				vector_ranuras.add(ranura_centro);
			}
		}
	}

	// agregar widget
	public void agregarWidget()
	{
		AgregarWidget agregar_widget = new AgregarWidget(this);
	}

	// insertar serializacion plantilla
	public void Abrir(String dir)
	{
		try
		{
			ObjectInputStream entrada = new ObjectInputStream(
					new FileInputStream(dir));
			vector_entrada = (Vector) entrada.readObject();
			entrada.close();
			cargarVector();
		} catch (IOException ex)
		{
		} catch (ClassNotFoundException ex)
		{
		}
	}

	// enviar entorno a script
	public void exportarScript()
	{
		FuncionScript funcion_script = new FuncionScript();

		if (vector_ranuras.size() > 0)
		{
			Ranura ranura_selecta = (Ranura) vector_ranuras
					.elementAt(posicion_actual);

			funcion_script.setRanura(
					(Ranura) vector_ranuras.elementAt(posicion_ranura));
		} else
		{
			JOptionPane jopnoselecto = new JOptionPane();
			StringBuffer jop_buffer = new StringBuffer();
			jop_buffer.append("Se requiere la seleccion de una ranura \n");
			jop_buffer.append("para la generacion del script. \n");
			jop_buffer.append("Verifique la seleccion y vuelva a intentar. \n");
			jopnoselecto.showMessageDialog(null, jop_buffer,
					"ADVERTENCIA: Seleccion invalida",
					javax.swing.JOptionPane.WARNING_MESSAGE);
			jop_buffer = new StringBuffer();
		}
	}

	// iniciar modulo wizard
	public void iniciarWizard()
	{
		Wizard w = new Wizard();
		Point ploc = ventana_base.getLocation();
		int plocx = (int) ploc.getX();
		int plocy = (int) ploc.getY();
		w.setLocation(plocx + 50, plocy + 50);
	}

	// iniciar modulo trainer
	public void iniciarTrainer()
	{
		trainer_actual = new VentanaTrainer();
		Point ploc = ventana_base.getLocation();
		int plocx = (int) ploc.getX();
		int plocy = (int) ploc.getY();
		trainer_actual.setLocation(
				plocx + ventana_base.panel_propiedad.getWidth(), plocy + 50);
	}

	// iniciar modulo prototipo
	public void iniciarPrototipo()
	{
		ventana_prototipo = new VentanaPrototipo();
		ventana_prototipo.setFuncionBase(this);
	}

	// iniciar modalidad eventos
	public void iniciarEventos()
	{
		modalidad = 1;

		ventana_base.panel_eventos.textfield_destino.setEnabled(false);
		ventana_base.panel_eventos.boton_estado.setText("Activo");
		ventana_base.panel_eventos.boton_estado.setForeground(Color.green);

	}

	// detener modalidad eventos
	public void detenerEventos()
	{
		modalidad = 0;

		ventana_base.panel_eventos.textfield_destino.setEnabled(true);
		ventana_base.panel_eventos.boton_estado.setText("Inactivo");
		ventana_base.panel_eventos.boton_estado.setForeground(Color.red);
	}

	// iniciar ayuda
	public void iniciarAyuda()
	{
		VentanaAyuda ventana_ayuda = new VentanaAyuda();
		ventana_ayuda.setVisible(true);
	}

	// fijar coordenadas del click
	public void setClickx(int v)
	{
		click_x = v;
	}

	public void setClicky(int v)
	{
		click_y = v;
	}

	// obtener coordenadas del click
	public int getClickx()
	{
		return click_x;
	}

	public int getClicky()
	{
		return click_y;
	}

	// remover widget
	public void removerSelecto()
	{
		Ranura ranura_selecta = (Ranura) vector_ranuras
				.elementAt(posicion_actual);
		JComponent widget_clickeado = widget_actual;
		JOptionPane jopcontenido = new JOptionPane();
		String mensaje = "Esta seguro que desea eliminar el componente: "
				+ widget_clickeado.getClass().getName() + " seleccionado?";
		int respuesta = jopcontenido.showConfirmDialog(null, mensaje);
		int indice = buscarComponente(widget_clickeado);
		if (respuesta == 0)
		{
			jopcontenido.showMessageDialog(null,
					"Se solicito la eliminacion del componenete");
			administradorE.solicitarRemover(indice);
		}
	}

	// terminar y cerrar aplicacion
	public void salir() throws RemoteException
	{
		verificarCerrado();
	}

	// ///////////revisar
	public void setPosicionranura(int v)
	{
		posicion_ranura = v;
	}

	public void setPosicioncontenedor(int v)
	{
		posicion_contenedor = v;
	}

	public int getPosicionranura()
	{
		return posicion_ranura;
	}

	public int getPosicioncontenedor()
	{
		return posicion_contenedor;
	}

	// solicitar confirmacion de cerrado
	public void verificarCerrado() throws RemoteException
	{
		if (vector_ranuras.size() < 1)
		{
			administradorE.desconectarUsuario();
			System.exit(0);
		} else
		{
			int confirmexiste = JOptionPane.showConfirmDialog(null,
					"Guardar los cambios en el entorno actual antes de salir?");
			if (confirmexiste == 0)
			{
				guardarEntorno();
			}
			if (confirmexiste == 1)
			{
				administradorE.desconectarUsuario();
				System.exit(0);
			}
		}
	}

	public void addAdminsitadorE(AdministradorEventos e)
	{
		administradorE = e;
	}

	public void agregarComponente(JComponent componente)
	{
		componentes.add(componente);
	}

	public int buscarComponente(JComponent componente)
	{
		int res = -1;
		for (int i = 0; i < componentes.size(); i++)
		{
			if (componente.equals(componentes.get(i)))
			{
				res = i;
			}
		}
		return res;
	}

	// <para el servidor>
	public void notificarLabel(int x, int y, int w, int h, int r, String texto)
	{
		administradorE.notificarLabel(x, y, w, h, r, posicion_ranura, texto);
	}

	public void notificarPanel(int x, int y, int w, int h, int r)
	{
		administradorE.notificarPanel(x, y, w, h, r, posicion_ranura);
	}

	public void notificarButton(int x, int y, int w, int h, int r, String t)
	{
		administradorE.notificarButton(x, y, w, h, r, posicion_ranura, t);
	}

	public void notificarRadioButton(int x, int y, int w, int h, int r,
			String t)
	{
		administradorE.notificarRadioButton(x, y, w, h, r, posicion_ranura, t);
	}

	public void notificarCheckBox(int x, int y, int w, int h, int r, String t)
	{
		administradorE.notificarCheckBox(x, y, w, h, r, posicion_ranura, t);
	}

	public void notificarTextField(int x, int y, int w, int h, int r, String t)
	{
		administradorE.notificarTextField(x, y, w, h, r, posicion_ranura, t);
	}

	public void notificarPasswordField(int x, int y, int w, int h, int r,
			String t)
	{
		administradorE.notificarPasswordField(x, y, w, h, r, posicion_ranura,
				t);
	}

	public void notificarTextArea(int x, int y, int w, int h, int r, String t)
	{
		administradorE.notificarTextArea(x, y, w, h, r, posicion_ranura, t);
	}

	public void notificarList(int x, int y, int w, int h, int r, String t)
	{
		administradorE.notificarList(x, y, w, h, r, posicion_ranura, t);
	}

	public void notificarComboBox(int x, int y, int w, int h, int r, String t)
	{
		administradorE.notificarComboBox(x, y, w, h, r, posicion_ranura, t);
	}

	public void notificarSlider(int x, int y, int w, int h, int r, String t)
	{
		administradorE.notificarSlider(x, y, w, h, r, posicion_ranura, t);
	}

	public void notificarProgresBar(int x, int y, int w, int h, int r, String t)
	{
		administradorE.notificarProgresBar(x, y, w, h, r, posicion_ranura, t);
	}

	// <para agregar items>
	public void agregarLabel(int location_x, int location_y, int size_w,
			int size_h, int posicion, String texto)
	{
		SLabel widget_actual = new SLabel(this);
		widget_actual.setBounds(location_x, location_y, size_w, size_h);
		widget_actual.setText(texto);
		inicializarComponente(widget_actual, posicion);
		widget_actual.setPosicionranura(posicion_actual);
		componentes.add(widget_actual);
		ventana_base.repaint();
	}

	public void agregarPanel(int location_x, int location_y, int size_w,
			int size_h, int posicion)
	{
		SPanel widget_actual = new SPanel(this);
		widget_actual.setBounds(location_x, location_y, size_w, size_h);
		inicializarComponente(widget_actual, posicion);
		widget_actual.setPosicionranura(posicion_actual);
		componentes.add(widget_actual);
		ventana_base.repaint();
	}

	public void agregarButton(int location_x, int location_y, int size_w,
			int size_h, int posicion, String text)
	{
		SButton widget_actual = new SButton(this);
		widget_actual.setText(text);
		widget_actual.setBounds(location_x, location_y, size_w, size_h);
		widget_actual.setFont(new Font(
				"" + ventana_base.fuente_texto.combo_muestra.getSelectedItem(),
				ventana_base.fuente_texto.valor_negrita
						+ ventana_base.fuente_texto.valor_cursiva,
				ventana_base.fuente_texto.fuente_puntos));
		inicializarComponente(widget_actual, posicion);
		widget_actual.setPosicionranura(posicion_actual);
		widget_actual.setOrigen(widget_actual.getPosicionranura());
		componentes.add(widget_actual);
		ventana_base.repaint();
	}

	public void agregarRadioButton(int location_x, int location_y, int size_w,
			int size_h, int posicion, String text)
	{
		SRadioButton widget_actual = new SRadioButton(this);
		widget_actual.setText(text);
		widget_actual.setBounds(location_x, location_y, size_w, size_h);
		widget_actual.setFont(new Font(
				"" + ventana_base.fuente_texto.combo_muestra.getSelectedItem(),
				ventana_base.fuente_texto.valor_negrita
						+ ventana_base.fuente_texto.valor_cursiva,
				ventana_base.fuente_texto.fuente_puntos));
		inicializarComponente(widget_actual, posicion);
		widget_actual.setPosicionranura(posicion_actual);
		componentes.add(widget_actual);
		ventana_base.repaint();
	}

	public void agregarCheckBox(int location_x, int location_y, int size_w,
			int size_h, int posicion, String text)
	{
		SCheckBox widget_actual = new SCheckBox(this);
		widget_actual.setText(text);
		widget_actual.setBounds(location_x, location_y, size_w, size_h);
		widget_actual.setFont(new Font(
				"" + ventana_base.fuente_texto.combo_muestra.getSelectedItem(),
				ventana_base.fuente_texto.valor_negrita
						+ ventana_base.fuente_texto.valor_cursiva,
				ventana_base.fuente_texto.fuente_puntos));
		inicializarComponente(widget_actual, posicion);
		widget_actual.setPosicionranura(posicion_actual);
		componentes.add(widget_actual);
		ventana_base.repaint();
	}

	public void agregarTextField(int location_x, int location_y, int size_w,
			int size_h, int posicion, String text)
	{
		STextField widget_actual = new STextField(this);
		widget_actual.setText(text);
		widget_actual.setBounds(location_x, location_y, size_w, size_h);
		widget_actual.setFont(new Font(
				"" + ventana_base.fuente_texto.combo_muestra.getSelectedItem(),
				ventana_base.fuente_texto.valor_negrita
						+ ventana_base.fuente_texto.valor_cursiva,
				ventana_base.fuente_texto.fuente_puntos));
		inicializarComponente(widget_actual, posicion);
		widget_actual.setPosicionranura(posicion_actual);
		componentes.add(widget_actual);
		ventana_base.repaint();
	}

	public void agregarPasswordField(int location_x, int location_y, int size_w,
			int size_h, int posicion, String text)
	{
		SPasswordField widget_actual = new SPasswordField(this);
		widget_actual.setText(text);
		widget_actual.setBounds(location_x, location_y, size_w, size_h);
		widget_actual.setFont(new Font(
				"" + ventana_base.fuente_texto.combo_muestra.getSelectedItem(),
				ventana_base.fuente_texto.valor_negrita
						+ ventana_base.fuente_texto.valor_cursiva,
				ventana_base.fuente_texto.fuente_puntos));
		inicializarComponente(widget_actual, posicion);
		widget_actual.setPosicionranura(posicion_actual);
		componentes.add(widget_actual);
		ventana_base.repaint();
	}

	public void agregarTextArea(int location_x, int location_y, int size_w,
			int size_h, int posicion, String text)
	{
		STextArea widget_actual = new STextArea(this);
		widget_actual.setText(text);
		widget_actual.setBounds(location_x, location_y, size_w, size_h);
		widget_actual.setFont(new Font(
				"" + ventana_base.fuente_texto.combo_muestra.getSelectedItem(),
				ventana_base.fuente_texto.valor_negrita
						+ ventana_base.fuente_texto.valor_cursiva,
				ventana_base.fuente_texto.fuente_puntos));
		inicializarComponente(widget_actual, posicion);
		widget_actual.setPosicionranura(posicion_actual);
		componentes.add(widget_actual);
		ventana_base.repaint();
	}

	public void agregarList(int location_x, int location_y, int size_w,
			int size_h, int posicion, String text)
	{
		SList widget_actual = new SList(this);
		Vector v = new Vector();
		v.add("uno");
		v.add("dos");
		widget_actual.setVector(v);
		widget_actual.setBounds(location_x, location_y, size_w, size_h);
		widget_actual.setFont(new Font(
				"" + ventana_base.fuente_texto.combo_muestra.getSelectedItem(),
				ventana_base.fuente_texto.valor_negrita
						+ ventana_base.fuente_texto.valor_cursiva,
				ventana_base.fuente_texto.fuente_puntos));
		inicializarComponente(widget_actual, posicion);
		widget_actual.setPosicionranura(posicion_actual);
		widget_actual.repaint();
		componentes.add(widget_actual);
		ventana_base.repaint();
	}

	public void agregarComboBox(int location_x, int location_y, int size_w,
			int size_h, int posicion, String text)
	{
		SComboBox widget_actual = new SComboBox(this);
		Vector vcombo = new Vector();
		vcombo.add("uno");
		vcombo.add("dos");
		widget_actual.setVector(vcombo);
		widget_actual.setBounds(location_x, location_y, size_w, size_h);
		widget_actual.setFont(new Font(
				"" + ventana_base.fuente_texto.combo_muestra.getSelectedItem(),
				ventana_base.fuente_texto.valor_negrita
						+ ventana_base.fuente_texto.valor_cursiva,
				ventana_base.fuente_texto.fuente_puntos));
		inicializarComponente(widget_actual, posicion);
		widget_actual.setPosicionranura(posicion_actual);
		widget_actual.repaint();
		componentes.add(widget_actual);
		ventana_base.repaint();
	}

	public void agregarSlider(int location_x, int location_y, int size_w,
			int size_h, int posicion, String text)
	{
		SSlider widget_actual = new SSlider(this);
		widget_actual.setLayout(null);
		widget_actual.setVisible(true);
		widget_actual.setBounds(location_x, location_y, size_w, size_h);
		widget_actual.setBackground(new Color(255, 255, 255));
		inicializarComponente(widget_actual, posicion);
		widget_actual.setPosicionranura(posicion_actual);
		componentes.add(widget_actual);
		widget_actual.repaint();
		ventana_base.repaint();
	}

	public void agregarProgresBar(int location_x, int location_y, int size_w,
			int size_h, int posicion, String text)
	{
		SProgressBar widget_actual = new SProgressBar(this);
		widget_actual.setValue(50);
		widget_actual.setLayout(null);
		widget_actual.setVisible(true);
		widget_actual.setBounds(location_x, location_y, size_w, size_h);
		widget_actual.setBackground(new Color(255, 255, 255));
		inicializarComponente(widget_actual, posicion);
		widget_actual.setPosicionranura(posicion_actual);
		componentes.add(widget_actual);
		widget_actual.repaint();
		ventana_base.repaint();
	}

	private void inicializarComponente(JComponent widget_actual, int posicion)
	{
		widget_actual.setLayout(null);
		widget_actual.setVisible(true);
		widget_actual.setBackground(new Color(255, 255, 255));
		JComponent widget_clickeado = componentes.get(posicion);
		if (widget_clickeado.getClass().getName().endsWith("VentanaInterna"))
		{
			VentanaInterna contenedor_actual = (VentanaInterna) widget_clickeado;
			contenedor_actual.add(widget_actual);
			contenedor_actual.vector_widgets.add(widget_actual);
		}
		if (widget_clickeado.getClass().getName().endsWith("SPanel"))
		{
			SPanel contenedor_actual = (SPanel) widget_clickeado;
			contenedor_actual.add(widget_actual);
			contenedor_actual.vector_widgets.add(widget_actual);
		}
	}

	public int getCantComponentes()
	{
		return componentes.size();
	}

	public void solicitarBloqueo(JComponent widget, boolean estado)
	{
		int posicion = buscarComponente(widget);
		administradorE.bloquear(posicion, estado);
	}

	public void bloquearWidget(int posicionWidget, boolean estado)
	{
		if (componentes.get(posicionWidget) instanceof Bloqueable)
		{
			Bloqueable b = (Bloqueable) componentes.get(posicionWidget);
			b.bloquear(estado);
		}

	}

	public void moverWidget(JComponent widget, int x, int y, int w, int h)
	{
		int posicion = buscarComponente(widget);
		administradorE.moverWidget(posicion, x, y, w, h);
	}

	public void moverWidget(int posicionWidget, int x, int y, int w, int h)
	{
		JComponent componente = componentes.get(posicionWidget);
		if (componente instanceof SCheckBox)
		{
			SCheckBox actual = (SCheckBox) componente;
			actual.setSelected(actual.getSelected());
			actual.setSelected();
		}
		if (componente instanceof SRadioButton)
		{
			SRadioButton actual = (SRadioButton) componente;
			actual.setSelected(actual.getSelected());
			actual.setSelected();
		}
		componente.setBounds(x, y, w, h);
	}

	public void sendValueWidget(JComponent componente, int valor)
	{
		int posicion = buscarComponente(componente);
		administradorE.sendValueWidget(posicion, valor);
	}

	public void actualizarWidget(int posicion, int valor)
	{
		JComponent componente = componentes.get(posicion);
		if (componente instanceof SSlider)
		{
			SSlider auxiliar = (SSlider) componente;
			auxiliar.setValue(valor);
			auxiliar.repaint();
		}
		if (componente instanceof SComboBox)
		{
			SComboBox auxiliar = (SComboBox) componente;
			auxiliar.setSelectedIndex(valor);
			Object aux2 = auxiliar.getItemAt(valor);
			auxiliar.setSelectedItem(aux2);
			auxiliar.repaint();
		}
		if (componente instanceof SList)
		{
			SList lista = (SList) componente;
			lista.setSelectedIndex(valor);
			lista.repaint();
		}
	}

	public void sendValueWidget(JComponent componente, String text)
	{
		int posicion = buscarComponente(componente);
		administradorE.sendValueWidget(posicion, text);
	}

	public void actualizarWidget(int posicion, String text)
	{
		JComponent componente = componentes.get(posicion);
		if (componente instanceof STextField)
		{
			STextField textField = (STextField) componente;
			textField.setText(text);
			textField.repaint();
		}
		if (componente instanceof SPasswordField)
		{
			SPasswordField passField = (SPasswordField) componente;
			passField.setText(text);
			passField.repaint();
		}
		if (componente instanceof STextArea)
		{
			STextArea passField = (STextArea) componente;
			passField.setText(text);
			passField.repaint();
		}
	}

	public void solicitarCambioDeColor(JComponent componente, int r, int g,
			int b, String tipo)
	{
		int posicion = buscarComponente(componente);
		administradorE.solicitarCambioDeColor(posicion, r, g, b,tipo);
	}
	
	public void cambiarColor(int posicion, int r, int g, int b, String tipo)
	{
		JComponent componenteActual = componentes.get(posicion);
		if(tipo.equals("fondo"))
		{
			componenteActual.setBackground(new Color(r, g, b));
		}
		else
		{
			componenteActual.setForeground(new Color(r, g, b));
		}
	}

	public void eliminarElemento(int posicion)
	{
		componentes.get(posicion).setVisible(false);
		componentes.remove(posicion);
	}
}
package SwingWidget;

import javax.swing.*;
import java.awt.event.*;
import ModuloBase.*;

@SuppressWarnings("serial")
public class SPopupMenu extends JPopupMenu implements ActionListener

{
	// public JMenuItem menuitem_copiarwidget;
	// public JMenuItem menuitem_pegarwidget;

	public JMenuItem menuitem_agregarwidget;
	public JMenuItem menuitem_removerwidget;

	public JMenuItem menuitem_exportarscript;
	public JMenuItem menuitem_generarprototipo;

	FuncionBase funcion_base;

	public SPopupMenu(FuncionBase fb)
	{
		funcion_base = fb;

		/*
		 * menuitem_copiarwidget = new JMenuItem("Copiar widget");
		 * menuitem_copiarwidget.setEnabled(false); add(menuitem_copiarwidget);
		 * menuitem_pegarwidget = new JMenuItem("Pegar widget");
		 * menuitem_pegarwidget.setEnabled(false); add(menuitem_pegarwidget);
		 */
		menuitem_agregarwidget = new JMenuItem("Agregar widget");
		menuitem_agregarwidget.setEnabled(false);
		add(menuitem_agregarwidget);
		menuitem_removerwidget = new JMenuItem("RemoverWidget");
		menuitem_removerwidget.setEnabled(false);
		add(menuitem_removerwidget);
		addSeparator();
		menuitem_exportarscript = new JMenuItem("Exportar script");
		menuitem_exportarscript.setEnabled(false);
		add(menuitem_exportarscript);
		menuitem_generarprototipo = new JMenuItem("Generar prototipo");
		menuitem_generarprototipo.setEnabled(false);
		add(menuitem_generarprototipo);

		// menuitem_copiarwidget.addActionListener(this);
		// menuitem_pegarwidget.addActionListener(this);
		menuitem_agregarwidget.addActionListener(this);
		menuitem_removerwidget.addActionListener(this);
		menuitem_exportarscript.addActionListener(this);
		menuitem_generarprototipo.addActionListener(this);

	}

	public void actionPerformed(ActionEvent ae)
	{
		/*
		 * if (ae.getSource() == menuitem_copiarwidget) {
		 * funcion_base.copiarWidget(); } if (ae.getSource() ==
		 * menuitem_pegarwidget) { funcion_base.iniciarPegadoWidget(); }
		 */
		if (ae.getSource() == menuitem_agregarwidget)
		{
			funcion_base.agregarWidget();
		}
		if (ae.getSource() == menuitem_removerwidget)
		{
			funcion_base.removerSelecto();
		}
		if (ae.getSource() == menuitem_exportarscript)
		{
			funcion_base.exportarScript();
		}
		if (ae.getSource() == menuitem_generarprototipo)
		{
			funcion_base.iniciarPrototipo();
		}
	}
}

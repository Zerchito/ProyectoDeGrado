package SelectorModulos;

import java.awt.*;

import Interfaz.VentanaPrincipal;
import InterfazBase.*;
import ModuloWizard.*;
import SoporteAyuda.*;

public class FuncionSelector
{

	VentanaSelector ventana_selector;

	public FuncionSelector(VentanaSelector vs)
	{
		ventana_selector = vs;

	}

	public void nuevoModuloBase()
	{
		VentanaPrincipal v = new VentanaPrincipal("hola");
		ventana_selector.dispose();
	}

	public void nuevoModuloTrainer()
	{
		VentanaTrainer t = new VentanaTrainer();
		ventana_selector.dispose();
	}

	public void nuevoModuloWizard()
	{
		Wizard w = new Wizard();
		ventana_selector.dispose();
	}

	public void nuevoSoporteAyuda()
	{
		VentanaAyuda ventana_ayuda = new VentanaAyuda();
		ventana_ayuda.setVisible(true);
	}

}

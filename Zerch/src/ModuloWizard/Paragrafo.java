package ModuloWizard;

import java.util.*;

public class Paragrafo implements java.io.Serializable
{

	String logical;
	Vector data;

	Paragrafo(String logical, Vector data)
	{
		this.logical = logical;
		this.data = data;
	}

}
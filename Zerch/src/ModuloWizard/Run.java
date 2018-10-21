package ModuloWizard;

public class Run implements java.io.Serializable
{
	Run(String attr, String content)
	{
		this.attr = attr;
		this.content = content;
	}

	String attr;
	String content;
}
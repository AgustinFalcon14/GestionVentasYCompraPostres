package capaLogica;

public class PostreLight extends Postre 
{
	private static final long serialVersionUID = 1L;
	
	private String tipoEndulzante;
	private String descripcion;	

	public PostreLight (String cod, String nom, double pre, boolean tip, String tie, String des)
	{
		super(cod,nom,pre,tip);   //invoco al constructor de Postre
		setTipo(false);
		tipoEndulzante = tie;
		descripcion = des;
	}
	
	public String getTipoEndulzante()
	{ 
		return tipoEndulzante; 
	}
	
	public String getDescripcion()
	{ 
		return descripcion; 
	}
	
	public void setTipoEndulzante(String tie)
	{ 
		this.tipoEndulzante = tie; 
	}
	
	public void setDescripcion (String des)
	{ 
		this.descripcion = des; 
	}
	
	public String toString ()
	{ 
		//setTipo(false);		--> cuando lo creamos ya se hace false? entonces es inecesario
		return ( super.toString() + "\n Tipo Endulzante: " + tipoEndulzante + "\n Descripcion: " + descripcion );
	}
	
	
}
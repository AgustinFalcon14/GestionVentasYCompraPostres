package valueObjects;

public class VOPostreLight extends VOPostre 
{
	private static final long serialVersionUID = 1L;
    private String tipoEndulzante;
	private String descripcion;	

	public VOPostreLight (String cod, String nom, double pre, boolean tip, String tie, String des)
	{
		super(cod,nom,pre,tip);   //invoco al constructor de Postre
		tip = false;  //setTipo(false);
       	tipoEndulzante = tie;
		descripcion = des;
	}
	
	public String VOgetTipoEndulzanteVO()
	{ 
		return tipoEndulzante; 
	}
	
	public String VOgetDescripcionVO()
	{ 
		return descripcion; 
	}
	
	public String toString ()
	{ 
		//setTipo(false);		--> cuando lo creamos ya se hace false? entonces es inecesario
		return ( super.toString() + "\n Tipo Endulzante: " + tipoEndulzante + "\n Descripcion: " + descripcion );
	}

}

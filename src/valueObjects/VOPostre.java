package valueObjects;

import java.io.Serializable;

public class VOPostre implements Serializable
{
	private static final long serialVersionUID = 1L;

    // private static final long serialVersionUID = 1L;

    private String codigo;
    private String nombre;
    private double precio;
    private boolean tipo;

    public VOPostre(String cod, String nom, double pre, boolean tip)
    {
        this.codigo = cod;
        this.nombre = nom;
        this.precio = pre;
        this.tipo = tip;
    }

  	public String VOgetCodigo()
	{ 
		return codigo; 
	}
	
	public String VOgetNombre()
	{ 
		return nombre; 
	}
	
	public double VOgetPrecio()
	{ 
		return precio; 
	}
	
	public boolean VOgetTipo()
	{ 
		return tipo; 
	}   
	
	public String toString ()
	{ 
		String auxtipo;
		if(tipo)
			auxtipo = "Comun";
		else
			auxtipo = "Light";
		
		return ("\n Codigo:" + codigo + "\n Nombre:" + nombre + "\n Precio:" + precio + "\n Tipo:" + auxtipo);
	}
	
}

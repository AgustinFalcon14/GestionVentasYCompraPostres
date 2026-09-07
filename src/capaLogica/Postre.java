package capaLogica;

import java.io.Serializable;

public  class Postre implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String  codigo;
	private String nombre; 
	private double precio;
	private boolean tipo;
	
	public Postre (String cod, String nom, double pre, boolean tip) // utilizamos float para ser fieles al diagrama UML podriamos usar double
	{
		this.codigo = cod;
		this.nombre = nom;
		if (pre > 0)
			this.precio = pre;
		else
			this.precio = 0;
		this.tipo = tip;
	}
	
	public String getCodigo()
	{ 
		return codigo; 
	}
	
	public String getNombre()
	{ 
		return nombre; 
	}
	
	public double getPrecio()
	{ 
		return precio; 
	}
	
	public boolean getTipo()
	{ 
		return tipo; 
	}
	
	public void setCodigo (String codigo)
	{ 
		this.codigo = codigo; 
	}
	
	public void setNombre(String nombre)
	{ 
		this.nombre = nombre; 
	}
	
	public void setPrecio(double precio)
	{ 
		if (precio > 0)
			this.precio = precio;
	}
	
	public void setTipo(boolean tipo)
	{ 
		if (tipo)
			this.tipo = tipo;
		else
			this.tipo = false;
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

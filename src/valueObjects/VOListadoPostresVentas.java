package valueObjects;

import java.io.Serializable;

public class VOListadoPostresVentas implements Serializable
{
	private static final long serialVersionUID = 1L;

	private int numero;
	private String codigo;
	private String nombre;
	private double precio;
    private boolean tipoPostre;
	private int cantPostre;

    public VOListadoPostresVentas (int num, String cod, String nom, double pre, boolean tipoP, int cantP)
    {
        this.numero = num;
        this.codigo = cod;
        this.nombre = nom;
        this.precio= pre;
        this.tipoPostre = tipoP;
        this.cantPostre = cantP;
    }

    public int VOgetNumero()
	{
		return numero;
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

    public boolean VOgetTipoPostre()
	{ 
		return tipoPostre; 
	}

    public int VOgetCantP()
	{ 
		return cantPostre; 
	}
	 
	public String toString ()
	{ 
		return ("\n numero:" + numero + "\n codigo:" + codigo + "\n nombre:" + nombre + "\n precio:" + precio + "\n tipoPostre:" 
	                 + tipoPostre + "\ncant Postre:" + cantPostre);
	}


}

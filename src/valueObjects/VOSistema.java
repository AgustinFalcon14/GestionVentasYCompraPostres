package valueObjects;

import java.io.Serializable;

import capaLogica.Postre;
import capaLogica.Venta;

public class VOSistema implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Postre postres[];
	private Venta ventas[];
	
	public VOSistema (Postre[] postres, Venta[] ventas) 
	{
        this.postres = postres;
        this.ventas = ventas;
    }

	public Postre[] VOgetPostres()	
		{ 
		return this.postres;
	}
	public Venta[] VOgetVenta()
	{ 
		return this.ventas; 
	}
}



package valueObjects;

import capaLogica.Ventas;
import capaLogica.Postres;
import capaLogica.PostresAdquiridos;
import java.io.Serializable;

public class VOFachada implements Serializable
{ 
	private static final long serialVersionUID = 1L;

    private Postres postres;
    private Ventas ventas;
    private int contadorVentas;

    public VOFachada (Postres p, Ventas v, int contadorVentas)
    {
        this.postres = p;
        this.ventas = v;
        this.contadorVentas = contadorVentas;
    }

    public Postres VOgetPostres()
    {
        return postres;
    }

    public Ventas VOgetVentas()
    {
        return ventas;
    }

    public int VOgetContadorVentas()
    {
        return contadorVentas;
    }

//    public PostresAdquiridos VOgetPostresAdquiridos()
//    {
//        return postresAdquiridos;
//    }

}
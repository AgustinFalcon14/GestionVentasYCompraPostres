package capaLogica;

import java.io.Serializable;

public class PostreAdquirido implements Serializable
{
	private static final long serialVersionUID = 1L;

	private int cantPostres;
	private Postre postre;
	
	public PostreAdquirido(int cP, Postre p)
	{
		cantPostres = cP;
		postre = p;		
	}
	
	public int getCantPostres()
	{
		return cantPostres;		
	}
	
	public Postre getPostre()
	{
		return postre;
	}
	
	public void setCantPostres(int cant)
	{
		this.cantPostres = cant;		
	}

	public String toString ()
	{ 
		return ( "\n Cantidad de Postres: " + cantPostres + "\n Postre" + postre );
	}
	
}

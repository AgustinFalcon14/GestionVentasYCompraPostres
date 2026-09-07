package valueObjects;

import java.io.Serializable;

public class VOPostreAdquirido implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int cantPostres;
	
	public VOPostreAdquirido(int cP)
	{
		cantPostres = cP;	
	}
	
	public int VOgetCantPostres()
	{
		return cantPostres;		
	}
}

package capaLogica;

import java.io.Serializable;

public class Monitor implements Serializable
{
	private static final long serialVersionUID = 1L;

	private int cantLectores;
	private boolean escribiendo;

	public Monitor()
	{
		this.cantLectores = 0;
		this.escribiendo = false;
	}

	public synchronized void comienzoLectura()
	{
	    while (escribiendo)  // ← while en lugar de if
	    {
	        try { this.wait(); }
	        catch (InterruptedException e) { }
	    }
	    cantLectores++;
	}
	
	public synchronized void terminoLectura()
	{
		if ((cantLectores - 1) == 0)
		{
			this.notify();
		}
		cantLectores--;
	}
	public synchronized void comienzoEscritura()
	{
	    while (escribiendo || cantLectores > 0)  		// ← while en lugar de if, y || en lugar de |
	    {
	        try { this.wait(); }
	        catch (InterruptedException e) { }
	    }
	    escribiendo = true;
	}
	public synchronized void terminoEscritura()
	{
		escribiendo = false;
		this.notify();
	}
}
// EJEMPLO DE CLAVIJO
	//	public synchronized void comienzoLectura()
	//	{
	//		if (escribiendo)
	//		{
	//			try
	//			{
	//				this.wait();
	//			}
	//		
	//			catch (InterruptedException e)
	//			{
	//				
	//			}
	//		}cantLectores++;
	//	}
	
	

//	public synchronized void comienzoEscritura()
//	{
//		if (escribiendo | cantLectores > 0)
//		{
//			try
//			{
//				this.wait();
//			}
//			catch  (InterruptedException e)
//			{
//				
//			}
//		}
//		escribiendo = true;
//	}

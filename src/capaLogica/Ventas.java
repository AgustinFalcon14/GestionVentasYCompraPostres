package capaLogica;

import java.io.Serializable;
import java.util.LinkedList;

public class Ventas extends SecuenciaV <Venta> implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private LinkedList <Venta> secV;

	public Ventas() 
	{
		secV = new LinkedList<>();          //inicializa vacio?
	}
	@Override
	public void Insertar(Venta v)
	{
		secV.add(v);		
	}
	@Override
	public boolean EsVacia()				 
    {
    	return secV.isEmpty(); 	 // O size() == 0	
    }
	@Override	
	public int Largo() 						 
    {
        return secV.size();     	
    }
	@Override
	public Venta KEsimo(int kE) 	 
	{
	    return secV.get(kE); 		
	}
	@Override
	public void Resto(int pos) 				 
	{
		secV.remove(pos);			
	}
	
	@Override
	public String toString ()
	{
		return ( "\n Secuencia:" + secV.toString());
	}
	
	
	@Override
	public boolean Contiene(int cod)
	{
	    for (Venta v : secV)
	    {
	        if (v.getNumero() == cod) 
	        {
	            return true;
	        }
	    }
	    return false;
	}
	
	// Busca una venta por su NUMERO (no por posicion), devuelve null si no existe
	public Venta buscarPorNumero(int numero)
	{
	    for (Venta v : secV)
	    {
	        if (v.getNumero() == numero)
	            return v;
	    }
	    return null;
	}

	// Elimina la venta con ese NUMERO (no por posicion)
	public void RestoPorNumero(int numero)
	{
	    for (int i = 0; i < secV.size(); i++)
	    {
	        if (secV.get(i).getNumero() == numero)
	        {
	            secV.remove(i);
	            return;
	        }
	    }
	}

	@Override
	public  boolean modificarPorNumero(Venta nuevaVenta)
	{
		 if (secV == null)return false;
		    
		 	long NumeroBuscado = nuevaVenta.getNumero();
		    
		    for (int i = 0; i < secV.size(); i++) 
		    {
		        if (secV.get(i).getNumero() == NumeroBuscado)
		        {
		            secV.set(i, nuevaVenta);  				// Este método existe en LinkedList
		            return true;
		        }
		    }
		    return false;
	}
	
}

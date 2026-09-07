package capaLogica;

import java.io.Serializable;

public abstract class SecuenciaV <V> implements Serializable
{
	private static final long serialVersionUID = 1L;

	public abstract boolean EsVacia();				// O size() == 0
  
    public abstract int Largo();   	                // Usa el método size() 
   
    public abstract void Insertar(V ve); 			
    // Utiliza el método add() 
  
    public abstract Venta KEsimo(int kE); 	 		// Usa método get() 
   
    public abstract void Resto(int pos) ;			// Usa método remove() 
    
	public abstract boolean Contiene(int cod);
	
	public abstract boolean modificarPorNumero(Venta nuevaVenta);

}

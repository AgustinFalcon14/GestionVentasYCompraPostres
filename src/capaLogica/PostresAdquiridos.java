package capaLogica;
import java.io.Serializable;
import java.util.LinkedList;

public class PostresAdquiridos extends SecuenciaPA <PostreAdquirido> implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private LinkedList <PostreAdquirido> secu;

	public PostresAdquiridos() 
	{
		secu = new LinkedList<>();          //inicializa vacio?
	}
	@Override
	public void Insertar(PostreAdquirido pA)
	{
		secu.add(pA);		
	}
	@Override
	public boolean EsVacia()				 
    {
    	return secu.isEmpty(); 	 // O size() == 0	
    }
	@Override	
	public int Largo() 						 
    {
        return secu.size();     	
    }
	@Override
	public PostreAdquirido KEsimo(int kE) 	 
	{
	    return secu.get(kE); 		
	}
	@Override
	public void Resto(int pos) 				 
	{
		secu.remove(pos);			
	}
	
	@Override
	public  boolean Contiene(String cod)
	{ 
		for (PostreAdquirido  pA : secu)
		{
			if (pA.getPostre().getCodigo().equals(cod)) 
			{
				return true;
			}
		}
		return false;
	}
	
	
	
	@Override
	public String toString ()
	{
		if(secu.isEmpty())
			return ( "\n Secuencia de Postres Adquiridos vacia:" );
		else
			return ( "\n Secuencia:" + secu.toString());
	}	
	
	
	
	
	
	
	@Override
	//tremendo, para usar en req 5
	public boolean modificarPorCodigo(PostreAdquirido nuevoPA) 
	 {
	    if (secu == null) return false;
	    
	    String codigoBuscado = nuevoPA.getPostre().getCodigo();
	    
	    for (int i = 0; i < secu.size(); i++) 
	    {
	        if (secu.get(i).getPostre().getCodigo().equals(codigoBuscado)) {
	            secu.set(i, nuevoPA);  // Este método existe en LinkedList
	            return true;
	        }
	    }
	    return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
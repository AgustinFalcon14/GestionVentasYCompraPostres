package capaLogica;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import java.util.Collection; // para usar values()

public class Postres extends DiccionarioP<String, Postre> implements Serializable
{
	private static final long serialVersionUID = 1L;

    private TreeMap<String, Postre> mapa;

    public Postres()
    {
        mapa = new TreeMap<>();
    }

    @Override
    public void agregar(String codigo, Postre postre)
    {
        mapa.put(codigo, postre);
    }

    @Override
    public boolean member(String codigo)
    {
        return mapa.containsKey(codigo);
    }

    @Override
    public Postre obtener(String codigo) 
    {
        return mapa.get(codigo);
    }

//    @Override
//    public Postre obtenerPostre(Postre p) 
//    {
//        return mapa.get(codigo);
//    }
    
    @Override
    public void eliminar(String codigo)
    {
        mapa.remove(codigo);
    }

    // Método para mostrar todos los Postres
    public void mostrarTodos()
    {
        for (Map.Entry<String, Postre> entry : mapa.entrySet())
        {
            System.out.println(entry.getValue());
        }
    }

    @Override
    public int Largo()
    {
    	return mapa.size();
    }
   
    @Override
    public Collection<Postre> values() 
    {
    	return mapa.values();
    }

    
}
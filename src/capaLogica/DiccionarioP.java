package capaLogica;

import java.io.Serializable;
import java.util.Collection;

public abstract class DiccionarioP<K, V> implements Serializable
{
	private static final long serialVersionUID = 1L;

	// Método abstracto para agregar un elemento al diccionario
    public abstract void agregar(K clave, V valor);

    public abstract boolean member(K clave);

    // Método abstracto para obtener un valor del diccionario
    public abstract V obtener(K clave);

    // Método abstracto para eliminar un elemento del diccionario
    public abstract void eliminar(K clave);

    // Método abstracto para obtener largo del diccionario
    public abstract int Largo();

    //   public abstract Postre obtenerPostre(V valor); 
 
    public abstract Collection<Postre> values() ;

}




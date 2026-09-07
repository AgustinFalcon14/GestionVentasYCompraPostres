package excepciones;

import java.io.Serializable;

// cuando intentás eliminar un postre que no está en esa venta 
// se usara en el requerimiento 6

public class PostreNoEnVentaException extends Exception implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String mensaje;
	
	public PostreNoEnVentaException(String msg)
	{
		mensaje = msg;
	}

	public String getMensaje() 
	{
		return mensaje;
	}
}

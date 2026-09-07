package excepciones;

import java.io.Serializable;;

// cuando intentás agregar un código que ya existe
// se usara en el requerimiento 1

public class PostreYaExisteException extends Exception implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String mensaje;
	
	public PostreYaExisteException(String msg)
	{
		this.mensaje = msg;
	}

	public String getMensaje() 
	{
		return mensaje;
	}
}


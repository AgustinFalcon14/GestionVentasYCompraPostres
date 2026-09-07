package excepciones;

import java.io.Serializable;

// cuando el código no está en el diccionario
// se usara en los requerimiento  3, 5, 6, 10

public class PostreNoExisteException extends Exception implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	public PostreNoExisteException(String msg)
	{
		mensaje = msg;
	}

	public String getMensaje() 
	{
		return mensaje;
	}

}


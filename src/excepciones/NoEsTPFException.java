package excepciones;

import java.io.Serializable;
//  se utilizara para el requerimiento 8
//  cuando el caracter ingresado no era el deseado, es decir fuera de los parametros "TPF"


public class NoEsTPFException extends Exception implements Serializable
{ 
	private static final long serialVersionUID = 1L;
	private String mensaje;

	public NoEsTPFException (String msg)
	{
		mensaje = msg;
	}

	public String getMensaje()
	{
		return mensaje;
	}

}

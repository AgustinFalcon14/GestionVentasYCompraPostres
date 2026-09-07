package excepciones;

import java.io.Serializable;

// cuando la fecha de nueva venta es anterior a la última
// se usara en el requerimiento  4


public class FechaInvalidaException extends Exception implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	public FechaInvalidaException(String msg)
	{
		mensaje = msg;
	}

	public String getMensaje() 
	{
		return mensaje;
	}
}

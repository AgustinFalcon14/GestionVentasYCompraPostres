package excepciones;

import java.io.Serializable;

public class DiccioVacioException extends Exception implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	public DiccioVacioException(String msg)
	{
		mensaje = msg;
	}

	public String getMensaje() 
	{
		return mensaje;
	}
}

package excepciones;

import java.io.Serializable;

// cuando sumar las unidades supera 40 
// se usara en el requerimiento 5 

public class SumaSuperaLimiteException extends Exception implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String mensaje;
	
	public SumaSuperaLimiteException(String msg)
	{
		mensaje = msg;
	}

	public String getMensaje() 
	{
		return mensaje;
	}
}

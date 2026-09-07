package excepciones;

import java.io.Serializable;

// cuando las unidades alcanzan o superan 40 
// se usara en el requerimiento 5 


public class SeSupero40Exception extends Exception implements Serializable
{ 
	private static final long serialVersionUID = 1L;
	private String mensaje;

	public SeSupero40Exception (String msg)
	{
		mensaje = msg;
	}

	public String getMensaje()
	{
		return mensaje;
	}

}


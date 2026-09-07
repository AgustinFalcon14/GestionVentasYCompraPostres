package excepciones;

import java.io.Serializable;

// cuando intentás operar sobre una venta que no está en proceso
// se usara en el requerimiento 7

public class VentaYaFinalizadaException extends Exception implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String mensaje;
	
	public VentaYaFinalizadaException(String msg)
	{
		mensaje = msg;
	}

	public String getMensaje() 
	{
		return mensaje;
	}
}


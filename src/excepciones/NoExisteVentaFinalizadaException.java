package excepciones;

import java.io.Serializable;

// se usará en los requerimientos
// cuando no hay ninguna venta con estado "finalizada" en la secuencia
// cuando la secuencia es toda con ventas en proceso
// requerimiento 10

public class NoExisteVentaFinalizadaException extends Exception implements Serializable
{ 
	private static final long serialVersionUID = 1L;
	private String mensaje;

	public NoExisteVentaFinalizadaException (String msg)
	{
		mensaje = msg;
	}

	public String getMensaje()
	{
		return mensaje;
	}

}


package excepciones;
import java.io.Serializable;;

// cuando el número de venta no está en la secuencia
// se usara en los requerimientos 5, 6, 7, 9)

public class VentaNoExisteException extends Exception implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String mensaje;
	
	public VentaNoExisteException(String msg)
	{
		mensaje = msg;
	}

	public String getMensaje() 
	{
		return mensaje;
	}
}



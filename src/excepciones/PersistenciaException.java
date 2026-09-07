package excepciones;

public class PersistenciaException extends Exception 
{
    private static final long serialVersionUID = 1L;

    // Constructor vacío
    public PersistenciaException() 
    {
        super();
    }

    // Constructor con mensaje
    public PersistenciaException(String mensaje) 
    {
        super(mensaje);
    }

    // Constructor con mensaje y causa 
    public PersistenciaException(String mensaje, Throwable causa) 
    {
        super(mensaje, causa);
    }
}
 

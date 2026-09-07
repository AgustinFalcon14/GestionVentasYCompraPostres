package valueObjects;

import java.io.Serializable;

public class VOModificarPostreVenta implements Serializable
{
	private static final long serialVersionUID = 1L;

    private String codigo;
    private int cantPostre;
    private int numero;

    public VOModificarPostreVenta(String cod, int cantP, int num)
    {
        codigo = cod;
        cantPostre = cantP;
        numero = num;
    }

    public String VOgetCodigo()
	{ 
		return codigo; 
	}

    public int VOgetCantP()
    {
        return cantPostre;
    }

    public int VOgetNumero()
    {
        return numero;
    }




}

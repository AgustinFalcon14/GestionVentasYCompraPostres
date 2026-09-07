package valueObjects;

import java.io.Serializable;
import java.time.LocalDate;

public class VOMontoTotal implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private double montoTotal;
	private int cantPostres;
	private  LocalDate fecha;

    public VOMontoTotal(String cod, double mT, int cP, LocalDate fec)
    {
    	this.codigo = cod;
        this.montoTotal = mT;
        this.cantPostres = cP;
        
        this.fecha = fec;
    }

    public double VOgetMontoTotal()
    {
        return montoTotal;
    }

    public int VOgetCantPostres()
    {
        return cantPostres;
    }

    public String VOgetCodigo()
    {
        return codigo;
    }
    
    public LocalDate VOgetFecha()
    {
        return fecha;
    }
}

package valueObjects;

import java.io.Serializable;
import java.time.LocalDate;

public class VONuevaVenta implements Serializable
{
	private static final long serialVersionUID = 1L;

    private LocalDate fecha;
	private String direccionEntrega;
	private boolean tipoVenta;
	private double montoFinal;

    public VONuevaVenta(LocalDate fe, String de, boolean tV, double mF)
    {
        this.fecha = fe;
        this.direccionEntrega = de;
        this.tipoVenta = tV;
        this.montoFinal = mF;
    }

    public LocalDate VOgetFecha()
    {
        return this.fecha;
    }

	public String VOgetDireccionEntrega()
	{
		return direccionEntrega;
	}


	public boolean VOgetTipoVenta() 
	{
		return tipoVenta;
	}

	public double VOgetMontoFinal() 
	{
		return montoFinal;
	}


}

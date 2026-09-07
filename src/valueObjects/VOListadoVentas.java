package valueObjects;

import java.io.Serializable;
import java.time.LocalDate;

public class VOListadoVentas implements Serializable
{
	private static final long serialVersionUID = 1L;
	private char indicacion; 
	private int numero;
	private LocalDate fecha;
	private String direccionEntrega;
	private boolean tipoVenta;
	private double montoFinal;
	
	public VOListadoVentas (char indi, int num, LocalDate fec, String dir, boolean tipVen, double monFin )
	{
		indicacion = indi;
		numero = num;
		fecha = fec;
		direccionEntrega = dir;
		tipoVenta = tipVen;  // CORRECCION: usar el valor recibido, no hardcodear true
	
		if (monFin > 0)
			montoFinal = monFin;
		else
			montoFinal = 0;
	}
	
	public int VOgetNumero()
	{
		return numero;
	}

	public LocalDate VOgetFecha()
	{
		return fecha;
	}

	public String VOgetDireccionEntrega ()
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

	public char getIndicacion() 
	{
		return indicacion;
	}

	 
	public String toString ()
	{ 
		return ("\n Indicacion:" + indicacion + "\n Numero:" + numero + "\n Fecha:" + fecha + "\n Direccion Entrega:" + direccionEntrega + "\n TipoVenta:" 
	                 + tipoVenta + "\nMonto Final:" + montoFinal);
	}
	
}

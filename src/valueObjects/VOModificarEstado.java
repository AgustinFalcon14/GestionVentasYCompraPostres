package valueObjects;

import java.io.Serializable;

public class VOModificarEstado implements Serializable
{
	private static final long serialVersionUID = 1L;

	private int numero;
	private boolean tipoVenta;
	private double montoFinal;
	private boolean confirmacion;
	
	public VOModificarEstado (int num,  boolean tipVen, double monFin, boolean con)
	{
		this.numero = num;
		this.tipoVenta = true;  // es en proceso? true = si, false = finalizado
	
		if (monFin > 0)
			this.montoFinal = monFin;
		else
			this.montoFinal = 0;
		
		this.confirmacion = con; // confirmacion = true , false = cancelacion
	 }

	public int VOgetNumero()
	{
		return numero;
	}
	
	public boolean VOgetTipoVenta()
	{
		return tipoVenta;
	}

	public double VOgetMontoFinal()
	{
		return montoFinal;
	}
	
	public boolean VOgetConfirmacion()
	{
		return confirmacion;
	}
	
}

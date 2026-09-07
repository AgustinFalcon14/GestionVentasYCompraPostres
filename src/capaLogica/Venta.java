package capaLogica;

import java.io.Serializable;
import java.time.LocalDate;

public class Venta implements Serializable
{
	private static final long serialVersionUID = 1L;

	private int numero;
	private LocalDate fecha;
	private String direccionEntrega;
	private boolean tipoVenta;
	private double montoFinal;
	private PostresAdquiridos postresAdquiridos;


	public Venta (int num, LocalDate fec, String dir, boolean tipVen, double monFin, PostresAdquiridos pA)
	{
		this.numero = num;
		this.fecha = fec;
		this.direccionEntrega = dir;
	
		this.tipoVenta = true;
	
		if (monFin > 0)
			this.montoFinal = monFin;
		else
			this.montoFinal = 0;
		
		postresAdquiridos = pA;
	 }

	public int getNumero()
	{
		return numero;
	}

	public LocalDate getFecha()
	{
		return fecha;
	}

	public String getDireccionEntrega ()
	{
	return direccionEntrega;	
	}

	public boolean getTipoVenta()
	{
		return tipoVenta;
	}

	public double getMontoFinal()
	{
		return montoFinal;
	}
	
	public String toString ()
	{
		String auxtipo;
		if(tipoVenta)
			auxtipo = "En Proceso";
		else
			auxtipo = "Finalizado";
		
		return ("\n Numero: " + numero + "\n Fecha: " + fecha + "\n Direccion de entrega: " + direccionEntrega + "\n Tipo venta: " + auxtipo + 
				"\n Monto final: " + montoFinal + "\n Lista de Postres de la venta: " + postresAdquiridos.toString() );
	}
	
	public PostresAdquiridos getPostresAdquiridos()
	{
		return postresAdquiridos;
	}
	

	public void setNumero (int num)
	{ 
		this.numero = num; 
	}
	
	public void setFecha(LocalDate f)
	{ 
		this.fecha = f; 
	}
	
	public void setDireccionEntrega(String dirEntrega)
	{ 
		this.direccionEntrega = dirEntrega;
	}
	
	public void setTipoVenta(boolean tipoV)
	{ 
		this.tipoVenta = tipoV;
	}
	
	public void setMontoFinal(double mF)
	{
		this.montoFinal= mF;
	}
	

	public void setPostresAdquiridos(PostresAdquiridos pA)
	{
		this.postresAdquiridos = pA;
	}
	
}

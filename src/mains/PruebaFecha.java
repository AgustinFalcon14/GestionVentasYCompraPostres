package mains;

import java.time.LocalDate;

public class PruebaFecha 
{
	public static void main(String[] args) 
	{
			LocalDate fecha = LocalDate.of(1, 1, 1);
			System.out.println("Anio: " + fecha.getYear());
	        System.out.println("Mes: " + fecha.getMonthValue());
	        System.out.println("Dia: " + fecha.getDayOfMonth());
	        System.out.println("Hoy es: " + fecha);
 	}
}

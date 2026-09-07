package mains;

import java.time.LocalDate;
import capaLogica.Postre;
import capaLogica.PostreAdquirido;
import capaLogica.PostreLight;
import capaLogica.PostresAdquiridos;
import capaLogica.Venta;


public class PruebaVenta 
{
	public static void main(String[] args) 
	{
		//reciclamos codigo para crear los postres
		Postre p = new Postre( "11111", "lemonpie", 220.2, false);
		//Postre p2 = new Postre( "11111", "Manteca con AZUCAR", 220.2, true);
		PostreLight p2 = new PostreLight( "11111", "lemonpie", 220.2 , true, "stevia", "muy rico oe");
		
		//reciclamos codigo para crear los postres adquiridos

		PostreAdquirido pA = new PostreAdquirido(7,p);
		PostreAdquirido pA2 = new PostreAdquirido(19,p2);
		
		//creamos la secuencia 
		PostresAdquiridos secPA = new PostresAdquiridos();
		
		//Insertamos los postres
		secPA.Insertar(pA);
		secPA.Insertar(pA2);
	
		LocalDate f = LocalDate.of(2026, 2, 23);		//PRUEBAS DE CONSTRUCTORES COMUNES
		
		Venta v = new Venta(2, f, "micasa", true, 155, secPA );
		
		String datos = "Datos de la venta: ";
				System.out.println(datos + v.toString());
		
				
//		//PRUEBA DE GETTERS
//		Postre p3 = new Postre( 9292, "Brownie", 150, true);
//	
//		System.out.println("Codigo: " + p3.getCodigo());
//		System.out.println("Nombre: " + p3.getNombre());
//		System.out.println("Precio: " + p3.getPrecio());
//		System.out.println("Tipo: "   + p3.getTipo());
//		
//		//PRUEBA DE SETTERS
//		p.setCodigo(999);
//		p.setNombre("pepito");
//		p.setPrecio(78.4);
//		p.setTipo(false);
//		System.out.println(datos + p.toString());
	}
}

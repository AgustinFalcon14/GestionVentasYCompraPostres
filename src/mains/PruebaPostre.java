package mains;

import capaLogica.Postre;

public class PruebaPostre 
{
	public static void main(String[] args) 
	{		

	//PRUEBAS DE CONSTRUCTORES COMUNES
				Postre p = new Postre( "11111", "lemonpie", 220.2, false);
				String datos = "Datos del postre: ";
				System.out.println(datos + p.toString());
				
				Postre p2 = new Postre( "2222", "pie", 220, false);
				String datos2 = "Datos del postre: ";
				System.out.println(datos2 + p2.toString());
	//PRUEBA DE GETTERS
				Postre p3 = new Postre( "9292", "Brownie", 150, true);
				System.out.println("Codigo: " + p3.getCodigo());
				System.out.println("Nombre: " + p3.getNombre());
				System.out.println("Precio: " + p3.getPrecio());
				System.out.println("Tipo: " + p3.getTipo());
				
	//PRUEBA DE SETTERS
			p.setCodigo("999");
			p.setNombre("pepito");
			p.setPrecio(78.4);
			p.setTipo(false);
			System.out.println(datos + p.toString());

	}

}

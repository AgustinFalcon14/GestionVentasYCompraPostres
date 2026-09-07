package mains;

import capaLogica.PostreLight;

public class PruebaPostreLight 
{
	public static void main(String[] args) 
	{
		//PRUEBAS DE CONSTRUCTORES COMUNES
			PostreLight pl = new PostreLight( "11111", "lemonpie", 220.2 , true, "stevia", "muy rico oe");
			String datos = "Datos del postre light: ";
			System.out.println (datos + pl.toString());

			PostreLight pl2 = new PostreLight("12345", "pie", 5555,true , "splenda no es", "muy rico no es oa" );
			String datos2 = "Datos del postre light: ";
			System.out.println(datos2 + pl2.toString());
					
		//PRUEBA DE GETTERS
			PostreLight pl3 = new PostreLight( "9292", "Brownie", 150, false, "splenda no es", "El tecla" );
			System.out.println("Codigo: " + pl3.getCodigo());
			System.out.println("Nombre: " + pl3.getNombre());
			System.out.println("Precio: " + pl3.getPrecio());
			System.out.println("Tipo: " + pl3.getTipo());
			System.out.println("Endulzante: " + pl3.getTipoEndulzante());
			System.out.println("Descripcion: " + pl3.getDescripcion());
	
		//PRUEBA DE SETTERS
			pl.setCodigo("999");
			pl.setNombre("pepito");
			pl.setPrecio(78.4);
			pl.setTipo(false);
			pl.setTipoEndulzante("cacao");
			pl.setDescripcion("chicorita");
			System.out.println(datos + pl.toString());		
	
	}
}



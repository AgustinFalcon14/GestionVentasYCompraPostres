package mains;

import capaLogica.Postre;
import capaLogica.PostreAdquirido;

public class PruebaPostreAdquirido {

	public static void main(String[] args)
	{
		//PRUEBAS DE CONSTRUCTORES COMUNES
		
		
		Postre p = new Postre( "11111", "lemonpie", 220.2, false);
		
		PostreAdquirido pA = new PostreAdquirido(12,p);
		String datos = "Datos del postre adquirido: ";
		System.out.println(datos + pA.toString());
		
		//PRUEBA DE GETTERS
		System.out.println("------- AQUI INICIA LA PRUEBA DE GETTERS ---------");
		System.out.println("cantidad de postres: " + pA.getCantPostres());
		System.out.println("Postre: " + pA.getPostre());
		System.out.println("------- AQUI FINALIZA LA PRUEBA DE GETTERS ---------");

		//PRUEBA DE SETTERS
		System.out.println("\n------- AQUI INICIA LA PRUEBA DE SETTERS ---------");
		pA.setCantPostres(39);
		System.out.println("cantidad de postres modificada :" + pA.getCantPostres());
		System.out.println("------- AQUI FINALIZA LA PRUEBA DE SETTERS ---------");
	}

}

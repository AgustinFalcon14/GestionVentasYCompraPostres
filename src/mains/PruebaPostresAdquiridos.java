package mains;

import capaLogica.Postre;
import capaLogica.PostresAdquiridos;
import capaLogica.PostreAdquirido;

public class PruebaPostresAdquiridos 
{

	public static void main(String[] args) 
	{
		Postre p = new Postre( "11111", "lemonpie", 220.2, false);
		Postre p2 = new Postre( "11111", "Manteca con AZUCAR", 220.2, true);

		PostreAdquirido postreA = new PostreAdquirido(10,p) ;
		PostreAdquirido postreB = new PostreAdquirido(22,p2) ;
		
		PostresAdquiridos pA = new PostresAdquiridos();
		
	
		System.out.println("------- AQUI INICIA LA PRUEBA DE ES VACIA ---------");
		
		if(pA.EsVacia())
			System.out.println("EsVacia");
		else
			System.out.println("NOEsVacia");
		
		System.out.println("------- AQUI FINALIZA LA PRUEBA DE ES VACIA ---------");
		
		
		System.out.println("------- AQUI INICIA LA PRUEBA DE INSERTAR ---------");
		pA.Insertar(postreA);
		if(pA.EsVacia())
			System.out.println("EsVacia");
		else
			System.out.println("NOEsVacia");
		
		System.out.println("------- AQUI FINALIZA LA PRUEBA DE INSERTAR ---------");
	
		System.out.println("------- AQUI INICIA LA PRUEBA DE LARGO---------");
	
		System.out.println( "Largo:" + pA.Largo() );
		pA.Insertar(postreB);
		System.out.println( "Largo luego de insertar:" + pA.Largo() );
		
		System.out.println("------- AQUI FINALIZA LA PRUEBA DE LARGO---------");
	
		System.out.println("------- AQUI INICIA LA PRUEBA DE K-ESIMO ---------");
		
		System.out.println("K-esimo elemento: " + pA.KEsimo(1).toString());
		
		System.out.println("------- AQUI FINALIZA LA PRUEBA DE K-ESIMO ---------");
				
		System.out.println("------- AQUI INICIA LA PRUEBA DE RESTO---------");
		System.out.println( "Largo antes de eliminar:" + pA.Largo() );
		pA.Resto(0);
		System.out.println( "Largo luego de eliminar:" + pA.Largo() );
		pA.Resto(0);
		System.out.println("Elimino otro elemento par ver si es vacia");
		if(pA.EsVacia())
			System.out.println("EsVacia");
		else
			System.out.println("NOEsVacia");
		System.out.println("------- AQUI FINALIZA LA PRUEBA DE RESTO---------");

	}

}

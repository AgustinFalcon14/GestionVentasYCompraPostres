package mains;

import java.time.LocalDate;
import capaLogica.Postre;
import capaLogica.PostreAdquirido;
import capaLogica.PostreLight;
import capaLogica.PostresAdquiridos;
import capaLogica.Venta;
import capaLogica.Ventas;

public class PruebaVentas 
{
	public static void main(String[] args)
	{
		//CREO EN EL SIGUIENTE ORDEN: POSTRE, POSTRE-ADQUIRIDO, SecPA, VENTA
		Postre p = new Postre( "11111", "lemonpie", 220.2, false);
		Postre p2 = new Postre( "67575", "Manteca con AZUCAR", 220.2, true);
		
		PostreAdquirido postreA = new PostreAdquirido(10,p) ;
		PostreAdquirido postreB = new PostreAdquirido(22,p2) ;
		PostresAdquiridos secpA = new PostresAdquiridos();
		secpA.Insertar(postreA);
		secpA.Insertar(postreB);
		
		LocalDate f = LocalDate.of(2026, 1, 24);
		Venta v = new Venta(1, f, "General Flores", false, 200, secpA ); 
		
		//Repito	
	
		PostreLight pl = new PostreLight ("78875", "lemonpie", 220.2 , true, "stevia", "muy rico oe");
		PostreLight p21 = new PostreLight("12345", "pie", 5555,true , "splenda no es", "muy rico no es oa" );
				
		PostreAdquirido postreA1 = new PostreAdquirido(5,pl) ;
		PostreAdquirido postreB1 = new PostreAdquirido(2,p21) ;
		PostresAdquiridos secpB = new PostresAdquiridos();
		secpB.Insertar(postreA1);
		secpB.Insertar(postreB1);
		
		
		LocalDate f1 = LocalDate.of(2026, 1, 1);
		Venta v2 = new Venta(1, f1, "General Matungo", false, 200, secpB );
		
		//CREO LA SECUENCIA DE VENTAS
		Ventas secV = new Ventas();
		
		System.out.println("------- AQUI INICIA LA PRUEBA DE ES VACIA ---------");
		if(secV.EsVacia())
			System.out.println("EsVacia");
		else
			System.out.println("NOEsVacia");
		System.out.println("------- AQUI FINALIZA LA PRUEBA DE ES VACIA ---------");
		
		
		System.out.println("------- AQUI INICIA LA PRUEBA DE INSERTAR ---------");
		secV.Insertar(v);
		System.out.println("------- AQUI FINALIZA LA PRUEBA DE INSERTAR ---------");
			
		System.out.println("------- AQUI INICIA LA PRUEBA DE LARGO---------");
		System.out.println( "Largo:" + secV.Largo() );
		secV.Insertar(v2);
		System.out.println( "Largo luego de insertar:" + secV.Largo() );
	    System.out.println("------- AQUI FINALIZA LA PRUEBA DE LARGO---------");
	
	    System.out.println("------- AQUI INICIA LA PRUEBA DE K-ESIMO ---------");
		System.out.println("K-esimo elemento: " + secV.KEsimo(0).toString());
		System.out.println("------- AQUI FINALIZA LA PRUEBA DE K-ESIMO ---------");
		
		System.out.println("------- AQUI INICIA LA PRUEBA DE RESTO---------");
		System.out.println( "Largo antes de eliminar:" + secV.Largo() );
		secV.Resto(0);
		System.out.println( "Largo luego de eliminar:" + secV.Largo() );
		secV.Resto(0);
		System.out.println("Elimino otro elemento par ver si es vacia");
		if(secV.EsVacia())
			System.out.println("EsVacia");
		else
			System.out.println("NOEsVacia");
	
		System.out.println("Elimino otro elemento par ver si es vacia");
		System.out.println("------- AQUI FINALIZA LA PRUEBA DE RESTO---------");
	}

}

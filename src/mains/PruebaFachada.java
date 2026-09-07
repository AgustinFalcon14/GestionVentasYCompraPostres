package mains;

import java.rmi.RemoteException;
import java.time.LocalDate;

import valueObjects.*;
import capaLogica.Fachada;
import capaLogica.Postre;
import capaLogica.PostreAdquirido;
import capaLogica.PostreLight;
import capaLogica.PostresAdquiridos;
import capaLogica.Venta;
import capaLogica.Ventas;
import valueObjects.VOPostre;
import valueObjects.VONuevaVenta;
import valueObjects.VOListadoVentas;
import valueObjects.VOModificarEstado;
import valueObjects.VOModificarPostreVenta;
import valueObjects.VOPostreLight;

import excepciones.DiccioVacioException;
import excepciones.FechaInvalidaException;
import excepciones.NoEsTPFException;
import excepciones.NoExisteVentaFinalizadaException;
import excepciones.PersistenciaException;
import excepciones.PostreNoEnVentaException;
import excepciones.PostreNoExisteException;
import excepciones.PostreYaExisteException;
import excepciones.SeSupero40Exception;
import excepciones.SumaSuperaLimiteException;
import excepciones.VentaNoExisteException;
import excepciones.VentaYaFinalizadaException;

public class PruebaFachada 
{

	public static void main(String[] args) throws PersistenciaException, RemoteException
	{
		Postre p = new Postre( "11111", "lemonpie", 220.2, true);
//		Postre p1 = new Postre( "11112", "lemonpie", 220.2, false);
		PostreLight p1 = new PostreLight( "11112", "postre random", 220.2 , true, "stevia", "muy rico oe");

		PostreAdquirido pA = new PostreAdquirido(9,p);
	//PostreAdquirido pA2 = new PostreAdquirido(19,p1);
		
		//creamos la secuencia 
		PostresAdquiridos secPA = new PostresAdquiridos();
		
		//Insertamos los postres


	//	secPA.Insertar(pA2);
		
		LocalDate f1 = LocalDate.of(2028, 2, 23);		//PRUEBAS DE CONSTRUCTORES COMUNES
		LocalDate f2 = LocalDate.of(2026, 2, 23);
		
		
		Venta v = new Venta(0, f1, "micasa", true, 155, secPA);
		v.setPostresAdquiridos(secPA);
		
		Venta v2 = new Venta(1, f2, "micasa2", true, 122, secPA);
		v2.setPostresAdquiridos(secPA);
		
		
		VONuevaVenta voVenta = new VONuevaVenta(v.getFecha(), v.getDireccionEntrega(), v.getTipoVenta(), v.getMontoFinal());
		
		
		VOPostre voPostre = new VOPostre (p.getCodigo(), p.getNombre(), p.getPrecio(), p.getTipo());
		VOPostreLight voPostre1 = new VOPostreLight (p1.getCodigo(), p1.getNombre(), p1.getPrecio(),p1.getTipo(),   
													 p1.getTipoEndulzante(), p1.getDescripcion());
		
		Fachada f = Fachada.getInstancia();
	
		// inicio REQUERIMIENTO 1
		try
		{	
		
			f.RegistrarNuevoPostre(voPostre);
			System.out.println("primer insercion");
			f.RegistrarNuevoPostre(voPostre1);
			System.out.println("segunda insercion");			
		}
		catch(RemoteException ex)
		{
			System.out.println(ex.getMessage());
		}
		catch(PostreYaExisteException ex)
		{
			System.out.println(ex.getMensaje());
		}
	// fin prueba registrar nuevo postre
	

		//INICIO REQUERIMIENTO 2 LISTADO GENERAL DE POSTRES
		try 
		{
			System.out.println("Iniciando listado");
			f.ListadoGeneralDePostres();
			System.out.println("Listado finalizado con exito");

		}
		catch(RemoteException ex)
		{
			System.out.println(ex.getMessage());
		}
		catch(DiccioVacioException ex)
		{
			System.out.println(ex.getMensaje());
		}
		//FINN REQUERIMIENTO 2 LISTADO GENERAL DE POSTRES

		//inicio prueba requerimiento 3 listado detallado de un postre
		
		try 
		{
			System.out.println("Iniciando listado detallado de un postre");
			f.ListadoDetalladoPostre(voPostre);
			System.out.println("Listado finalizado con exito detallado de un postre");
		}
		catch(RemoteException ex)
		{
			System.out.println(ex.getMessage());
		}
		catch(DiccioVacioException ex)
		{
			System.out.println(ex.getMensaje());
		}
		catch(PostreNoExisteException ex)
		{
			System.out.println(ex.getMensaje());
		}
		//FIN REQUERIMIENTO 3 LISTADO DETALLADO DE UN POSTRE A PARTIR DE CODIGO
		
		//INICIO PRUEBA REQUERIMIENTO 4 COMIENZA NUEVA VENTA
		try 
		{
			System.out.println("Iniciando Comienzo de una nueva venta");
			f.RegistrarNuevaVenta(voVenta);
			System.out.println("Finalizando Nueva venta");
		}
		catch(RemoteException ex)
		{	
			System.out.println(ex.getMessage());
		}
		
		catch(FechaInvalidaException ex)
		{
			System.out.println(ex.getMensaje());
		}
		//FIN REQUERIMIENTO 4 COMIENZO DE UNA NUEVA VENTA				
		
		//INICIO PRUEBA REQUERIMIENTO 5 MODIFICAR VENTA
		try 
		{
			VOModificarPostreVenta vo = new VOModificarPostreVenta("11111", 8, 0);
			VOModificarPostreVenta vo2 = new VOModificarPostreVenta("11112", 8, 0);
			//insertamos una venta a la secuencia de ventas para probar
			
			System.out.println("Modificar Venta num 0, postre 1111 y sumarle 8 a cant\n");
			f.AgregarPostreVenta(vo);
			f.AgregarPostreVenta(vo2);
			
			System.out.println("Finalizado");
		}
		catch(RemoteException ex)
		
		{
			System.out.println(ex.getMessage());
		}
		
		catch(SumaSuperaLimiteException ex)
		{
			System.out.println(ex.getMensaje());
		}
		
		catch(SeSupero40Exception ex)
		{
			System.out.println(ex.getMensaje());
		}
		
		
		catch(VentaNoExisteException ex)
		{
			System.out.println(ex.getMensaje());
		}
		
		catch (PostreNoExisteException ex)
		{
			System.out.println(ex.getMensaje());
		}
		
		catch (VentaYaFinalizadaException ex)
		{
			System.out.println(ex.getMensaje());
		}
	
		
//FIN REQUERIMIENTO 5 MODIFICAR VENTA				
		
//INICIO PRUEBA REQUERIMIENTO 6 MODIFICAR VENTA
//		
//		try 
//		{
//			VOModificarPostreVenta vo = new VOModificarPostreVenta(11111, 8, 0);
//			//insertamos una venta a la secuencia de ventas para probar
//			
//			System.out.println("Modificar Venta num 0, postre 1111 y restarle 8 a cant");
//			f.EliminarPostreVenta(vo);
//			System.out.println("Finalizado");
//		}
//		
//		catch(RemoteException ex)
//		{
//			System.out.println(ex.getMessage());
//		}
//		
//		catch(PostreNoEnVentaException ex)
//		{
//			System.out.println(ex.getMensaje());
//		}
//
//		
//		catch(VentaNoExisteException ex)
//		{
//			System.out.println(ex.getMensaje());
//		}
//		
//		catch (PostreNoExisteException ex)
//		{
//			System.out.println(ex.getMensaje());
//		}
//		
		//FIN REQUERIMIENTO 6 MODIFICAR VENTA			
	
		//INICIO REQUERIMIENTO 7
		
//		try 
//		{
//			System.out.println("Iniciando requerimiento 7");
//			VOModificarEstado vo = new VOModificarEstado ( 0 ,  true, 500, true);
//			f.FinalizarVentas(vo);
//			System.out.println("Finalizando requerimiento 7");
//		}
//		
//		catch(RemoteException ex)
//		{
//			System.out.println(ex.getMessage());
//		}
//		
//		catch(VentaYaFinalizadaException ex)
//		{
//			System.out.println(ex.getMensaje());
//		}
//		
//		catch(VentaNoExisteException ex)
//		{
//			System.out.println(ex.getMensaje());
//		}
//	
	
		//FIN REQUERIMIENTO 7
		
		//INICIO REQUERIMIENTO 8
		
//		try 
//		{
//			System.out.println("Iniciando requerimiento 8");		
//			VOListadoVentas vo = new VOListadoVentas('P', 0, f1, "mi casa", false, 22.23 );
//			f.ListadoVentas(vo);
//			System.out.println("Finalizando requerimiento 8");
//		}
//		
//		catch(RemoteException ex)
//		{
//			System.out.println(ex.getMessage());
//		}
//		
//		catch(NoEsTPFException ex)
//		{
//			System.out.println(ex.getMensaje());
//		}
		
		//FIN REQUERIMIENTO 8
		
		//INICIO REQUERIMIENTO 9
		try
		{		
			//VOListadoPostresVentas vo = new VOListadoPostresVentas(0, 11111, "LemonPie", 225.55, false, 10); 
			VOListadoPostresVentas vo = new VOListadoPostresVentas(v.getNumero(), voPostre.VOgetCodigo(), voPostre.VOgetNombre(), voPostre.VOgetPrecio(), voPostre.VOgetTipo(), pA.getCantPostres()); 
			f.ListadoPostresVentas(vo);
			
		}
		catch(RemoteException ex)
		{
			System.out.println(ex.getMessage());
		}
		catch(VentaNoExisteException ex)
		{
			System.out.println(ex.getMensaje());
		}

		//FIN REQUERIMIENTO 9
		
//		//INICIO REQUERIMIENTO 10
//		try
//		{	
//			
//			LocalDate f11 = LocalDate.of(2028, 2, 22);
//			System.out.println("Iniciando requerimiento 10");		
////			VOMontoTotal vo = new VOMontoTotal (voPostre.VOgetCodigo(), v.getMontoFinal(), pA.getCantPostres(), v.getFecha()); 
//			VOMontoTotal vo = new VOMontoTotal (voPostre.VOgetCodigo(), v.getMontoFinal(), pA.getCantPostres(), f11); 
//			f.RecaudacionPostreFecha(vo);
//			
//			f.RecaudacionPostreFecha(vo);
//			
//			System.out.println("Finalizando requerimiento 10");
//		}
//	
//		catch(RemoteException ex)
//		{
//			System.out.println(ex.getMessage());
//		}
//
//		catch(NoExisteVentaFinalizadaException ex)
//		{
//			System.out.println(ex.getMensaje());
//		}
//		
//		catch(FechaInvalidaException ex)
//		{
//			System.out.println(ex.getMensaje());
//		}
//		
//		catch(PostreNoExisteException ex)
//		{
//			System.out.println(ex.getMensaje());
//		}
		//FIN REQUERIMIENTO 10		
		
		
		
		
	}
}
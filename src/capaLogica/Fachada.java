package capaLogica;

import java.io.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

import sistema.persistencia;
import valueObjects.*;
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
import capaLogica.PostreAdquirido;
import capaLogica.PostresAdquiridos;
import capaLogica.Postre;
import java.time.LocalDate;


public class Fachada extends UnicastRemoteObject implements IFachada
{
	private static final long serialVersionUID = 1L;

	private Postres postres;
    private Ventas ventas;
    private int contadorVentas;
    private static Monitor monitor;
    private static Fachada instancia;
    
    public Fachada() throws PersistenciaException, RemoteException
    {
    	monitor = new Monitor ();
        persistencia ds = new persistencia();
        Properties p = new Properties();
        try
        {
            p.load(new FileInputStream("config/archivo.properties"));
            String nomArch = p.getProperty("nombreArchivo");
            VOFachada aux = ds.recuperar(nomArch);
            if (aux != null)
            {
                setPostres(aux.VOgetPostres());
                setVentas(aux.VOgetVentas());
                contadorVentas = aux.VOgetContadorVentas();
            }
            else
            {
                setPostres(new Postres());
                setVentas(new Ventas());
            }
        }
        catch (IOException e) 
        {
            setPostres(new Postres());
            setVentas(new Ventas());
        }
       
    }
    
    public static Fachada getInstancia() throws PersistenciaException, RemoteException
    {
	    if (instancia == null)
	    	instancia = new Fachada();
	    return instancia;
    }

  
	public Postres getPostres() {
		return postres;
	}

	public void setPostres(Postres postres) {
		this.postres = postres;
	}

	public Ventas getVentas() {
		return ventas;
	}

	public void setVentas(Ventas ventas) {
		this.ventas = ventas;
	}

   
    public void RegistrarNuevoPostre(VOPostre voPostre)throws RemoteException, PostreYaExisteException 
    {
        monitor.comienzoEscritura();
        try
        {
            if(postres.member(voPostre.VOgetCodigo()))
            {
                throw new PostreYaExisteException("Error, ya existe previamente el codigo del postre ingresado ");
            }
            else
            {
                Postre p = null;
                if(voPostre.VOgetTipo()== false) // light
                {
                    p = new PostreLight(voPostre.VOgetCodigo(), voPostre.VOgetNombre(),
                                       voPostre.VOgetPrecio(), voPostre.VOgetTipo(),
                                       ((VOPostreLight) voPostre).VOgetTipoEndulzanteVO(),
                                       ((VOPostreLight) voPostre).VOgetDescripcionVO());
                    postres.agregar(p.getCodigo(), p);
                    System.out.println("postre light agregado exitosamente");
                }
                else
                {
                    p = new Postre(voPostre.VOgetCodigo(), voPostre.VOgetNombre(), voPostre.VOgetPrecio(), voPostre.VOgetTipo());
                    System.out.println("postre Comun agregado exitosamente");
                    postres.agregar(voPostre.VOgetCodigo(), p);
                }
            }
        }
        finally
        {
            monitor.terminoEscritura();
        }
    }
 
    public ArrayList<VOPostre>ListadoGeneralDePostres() throws RemoteException, DiccioVacioException
    {    	
    	monitor.comienzoLectura();
    	if(postres == null || postres.Largo() == 0) // si es vacío
    	{
            monitor.terminoLectura();
            throw new DiccioVacioException("Error, el diccionario de postres no tiene ningun postre (es vacio)");
    	}
    	
    	else
    	{
    		ArrayList<VOPostre> resultado = new ArrayList<>();
    		for (Postre p : postres.values()) 
    		{ 
    		   	if(p.getTipo())
    		   		resultado.add(new VOPostre(p.getCodigo(), p.getNombre(), p.getPrecio(), p.getTipo()));
    		    else
    		    {
    		    	resultado.add(new VOPostreLight(p.getCodigo(), p.getNombre(), p.getPrecio(), p.getTipo(),((PostreLight) p).getTipoEndulzante(),
    		    			((PostreLight) p).getDescripcion()));
    		    }
    		}
    		monitor.terminoLectura();
    		System.out.println("fin del listado\n" + "el largo del array de vo devuelto es de: "  +  resultado.size() + resultado.toString() );
    		
    		return resultado;
    	}
    }
    
    public VOPostre ListadoDetalladoPostre (VOPostre voPostre)throws RemoteException, PostreNoExisteException, DiccioVacioException
    {
    	monitor.comienzoLectura();
    	if(postres == null || postres.Largo() == 0) // si es vacío
    	{
            monitor.terminoLectura();
            throw new DiccioVacioException("Error, el diccionario de postres no tiene ningun postre (es vacio)");
    	}
    	else
    	{
    		if(postres.member(voPostre.VOgetCodigo())) 
    		{
    			Postre aux = postres.obtener(voPostre.VOgetCodigo());
    			if(aux.getTipo()== false)
    			{
    				VOPostreLight voPL = new VOPostreLight(aux.getCodigo(), aux.getNombre(), aux.getPrecio(), aux.getTipo(),
    						((PostreLight) aux).getTipoEndulzante(),
    		    			((PostreLight) aux).getDescripcion()); 
    				monitor.terminoLectura();  
    				return  voPL;
    			}
    			else
    			{
    				VOPostre voP = new VOPostre(aux.getCodigo(), aux.getNombre(), aux.getPrecio(), aux.getTipo());
    				monitor.terminoLectura();
    				return  voP;            
    			}
    		}
    		else
    		{
                throw new PostreNoExisteException ("Error, el codigo ingresado no pertenece a ningun postre");
    		}
    	}	
	}
    
    public void RegistrarNuevaVenta(VONuevaVenta voVenta) throws RemoteException, FechaInvalidaException
    {
        monitor.comienzoEscritura();
        if(ventas.EsVacia())
        {
            // Primera venta: no hay fecha anterior que comparar, se registra directamente
            PostresAdquiridos pA = new PostresAdquiridos();
            Venta nuevaVenta = new Venta(contadorVentas, voVenta.VOgetFecha(), voVenta.VOgetDireccionEntrega(), true, 0, pA);
            ventas.Insertar(nuevaVenta);
            contadorVentas++;
//            System.out.println("Venta ingresada con exito. Largo secuencia: " + ventas.Largo());
            monitor.terminoEscritura();
        }
        else
        {
            // Verificar que la fecha sea igual o posterior a la ultima venta
            Venta ultima = ventas.KEsimo(ventas.Largo() - 1);
            LocalDate fechaNueva  = voVenta.VOgetFecha();
            LocalDate fechaUltima = ultima.getFecha();
            if(fechaNueva.compareTo(fechaUltima) < 0)
            {	
                monitor.terminoEscritura();
                throw new FechaInvalidaException("Error, fecha de venta incorrecta");
            }
            else
            {
                PostresAdquiridos pA = new PostresAdquiridos();
                Venta nuevaVenta = new Venta(contadorVentas, voVenta.VOgetFecha(), voVenta.VOgetDireccionEntrega(), true, 0, pA);
                ventas.Insertar(nuevaVenta);
                contadorVentas++;
                //System.out.println("Venta ingresada con exito. Largo secuencia: " + ventas.Largo());
                monitor.terminoEscritura();
            }
        }
    }

    public void AgregarPostreVenta(VOModificarPostreVenta vo) throws RemoteException, SumaSuperaLimiteException, SeSupero40Exception, VentaNoExisteException, PostreNoExisteException, VentaYaFinalizadaException
    {
    	monitor.comienzoEscritura();
    	System.out.println("Inicio Requerimiento 5");
	 	if(postres.member(vo.VOgetCodigo()))
    	{
    		if( ventas.Contiene(vo.VOgetNumero()))
    		{
    			if(!ventas.buscarPorNumero(vo.VOgetNumero()).getTipoVenta())    // Verificar que la venta no este finalizada
    			{
    				monitor.terminoEscritura();
    				throw new VentaYaFinalizadaException("Error, no se pueden agregar postres a una venta ya finalizada");
    			}
    			if ( vo.VOgetCantP() > 40 )
    			{
    				  monitor.terminoEscritura();
    				  throw new SeSupero40Exception("Error, límite de postres por venta ");
    			}
    			else
    			{
					Venta v = ventas.buscarPorNumero(vo.VOgetNumero());
	    			PostresAdquiridos secPA = v.getPostresAdquiridos();    			
	    			int suma = 0;
	    			
	    			for(int i=0; i<secPA.Largo(); i++)
	    			{
	    				suma = suma + secPA.KEsimo(i).getCantPostres();
	    			}  
	    			
	    			int total = suma + vo.VOgetCantP();
	    			if(total >40)
	    			{
	    				monitor.terminoEscritura();
	    				throw new SumaSuperaLimiteException ("Error, la suma de unidades adquiridas excede MAX ");
	    			}
	    			else
	    			{	    	
	    				 Postre aBuscar = postres.obtener(vo.VOgetCodigo());   					 
    					 if(secPA.Contiene(aBuscar.getCodigo()))
	    				{
	    					int pos = 0;
	    					while(pos < secPA.Largo() && !secPA.KEsimo(pos).getPostre().getCodigo().equals(vo.VOgetCodigo()))
	    						{pos++;} // Buscar la posicion del postre en la secuencia
	    					PostreAdquirido pA = secPA.KEsimo(pos);
		    				int agregar = pA.getCantPostres() + vo.VOgetCantP();
		    				pA.setCantPostres(agregar);
							secPA.modificarPorCodigo(pA);
		    				v.setPostresAdquiridos(secPA);
		    				ventas.modificarPorNumero(v);
		    				System.out.println("Fin Requerimiento 5");
		        			monitor.terminoEscritura();
	    				}
		    			else
		    			{
	    					System.out.println("Postre nuevo en la venta, insertando... ");
		    				PostreAdquirido pA = new PostreAdquirido(vo.VOgetCantP(), postres.obtener(vo.VOgetCodigo()));   				
		    				int agregar = vo.VOgetCantP();
		    				secPA.Insertar(pA);
		    				v.setPostresAdquiridos(secPA);
		    				ventas.modificarPorNumero(v);
		    		    	System.out.println("Fin Requerimiento 5");
		        			monitor.terminoEscritura();
		    			}
	    			}
	    		}	
    		}
    		else
    		{
    			monitor.terminoEscritura();
    			throw new VentaNoExisteException ("Error, numero de venta inexistente ");
    		} 
    	}
    	else
    	{
    		monitor.terminoEscritura();
    		throw new PostreNoExisteException("Error, codigo de postre inexistente");
    	}
    }   
    
    
    public void EliminarPostreVenta(VOModificarPostreVenta vo) throws RemoteException, PostreNoEnVentaException, VentaNoExisteException, PostreNoExisteException, VentaYaFinalizadaException
    {
        monitor.comienzoEscritura();
        System.out.println("Inicio Requerimiento 6");
        try
        {
            if(postres.member(vo.VOgetCodigo()))
            {
                if(ventas.Contiene(vo.VOgetNumero()))
                {
                    Venta v = ventas.buscarPorNumero(vo.VOgetNumero());
                    if(!v.getTipoVenta())
                        throw new VentaYaFinalizadaException("Error, no se pueden eliminar postres de una venta ya finalizada");
                    PostresAdquiridos secPA = v.getPostresAdquiridos();
                    Postre aBuscar = postres.obtener(vo.VOgetCodigo());
                    if(secPA.Contiene(aBuscar.getCodigo()))
                    {
                        int pos = 0;
                        while(pos < secPA.Largo() && !secPA.KEsimo(pos).getPostre().getCodigo().equals(vo.VOgetCodigo()))
                            pos++;
                        if(pos >= secPA.Largo())
                            throw new PostreNoEnVentaException("Error, postre inexistente para la venta proporcionada");
                        int quitar = secPA.KEsimo(pos).getCantPostres() - vo.VOgetCantP();
                        if(quitar <= 0)
                        {
                            secPA.Resto(pos);
                            System.out.println("Fin Requerimiento 6");
                        }
                        else
                        {
                            PostreAdquirido pA = secPA.KEsimo(pos);
                            pA.setCantPostres(quitar);
                            secPA.modificarPorCodigo(pA);
                            v.setPostresAdquiridos(secPA);
                            ventas.modificarPorNumero(v);
                            System.out.println("Fin Requerimiento 6");
                        }
                    }
                    else
                        throw new PostreNoEnVentaException("Error, postre inexistente para la venta proporcionada"); // ahora dentro del try
                }
                else
                    throw new VentaNoExisteException("Error, numero de venta inexistente"); //ahora dentro del try
            }
            else
                throw new PostreNoExisteException("Error, codigo de postre inexistente"); // dentro del try
        }
        finally
        {
            monitor.terminoEscritura(); 	//se ejecuta SIEMPRE
        }
    }

    
    
    
    public double FinalizarVentas(VOModificarEstado vo) throws RemoteException, VentaYaFinalizadaException, VentaNoExisteException
    {
    	monitor.comienzoEscritura();
    	//System.out.println("El largo de la secuencia es de : "  +  ventas.Largo() + ventas.toString() );
    	try
    	{
	    	if (ventas.Contiene(vo.VOgetNumero()))
	    	{
	    		if(ventas.buscarPorNumero(vo.VOgetNumero()).getTipoVenta())
	    		{
	    			boolean sinPostres = ventas.buscarPorNumero(vo.VOgetNumero()).getPostresAdquiridos().EsVacia();
	    			if(sinPostres) 	    				// Sin postres: se elimina siempre, independientemente de confirmacion o cancelacion
	    			{
	    				ventas.RestoPorNumero(vo.VOgetNumero());
	    				System.out.println("Venta eliminada por no tener postres.");
	    				return 0;
	    			}
	    			else if(vo.VOgetConfirmacion())
	    			{
	    				// Con postres y confirma: calcular monto, marcar como finalizada
	    				Venta vAux = ventas.buscarPorNumero(vo.VOgetNumero());
	    				PostresAdquiridos secPA = vAux.getPostresAdquiridos();
	    				double monto = 0;
	    				for(int i = 0; i < secPA.Largo(); i++)
	    				{
	    					PostreAdquirido pA = secPA.KEsimo(i);
	    					monto += pA.getCantPostres() * pA.getPostre().getPrecio();
	    				}
	    				vAux.setTipoVenta(false);
	    				vAux.setMontoFinal(monto);
	    				ventas.modificarPorNumero(vAux);
	    				System.out.println("Monto total calculado: " + monto);
	    				return monto;
	    			}
	    			else
	    			{
	    				ventas.RestoPorNumero(vo.VOgetNumero()); 	// Con postres pero cancela: eliminar la venta del sistema
	    				System.out.println("Venta eliminada por cancelacion del usuario.");
	    				return 0;
	    			}
	    		}
	    		else
	    			throw new VentaYaFinalizadaException ("Error, la venta ya ha sido finalizada ");
	    	}
	    	else
	    		throw new VentaNoExisteException ("Error, numero de venta inexistente ");
    	}
    	finally
    	{
    		//System.out.println("El largo de la secuencia es de : "  +  ventas.Largo() + ventas.toString() );
    		monitor.terminoEscritura();
    	}
    }
   
    public ArrayList<VOListadoVentas>ListadoVentas(VOListadoVentas vo) throws RemoteException, NoEsTPFException
    {
    	monitor.comienzoLectura();
    	char opcion = vo.getIndicacion();
    	ArrayList<VOListadoVentas> resultado = new ArrayList<>();
    	switch (opcion) 
    	{
    	    case 'T' : case 't' : for(int i=0; i<ventas.Largo(); i++){resultado.add( new VOListadoVentas( opcion, ventas.KEsimo(i).getNumero(), ventas.KEsimo(i).getFecha(), ventas.KEsimo(i).getDireccionEntrega(), ventas.KEsimo(i).getTipoVenta(), ventas.KEsimo(i).getMontoFinal()));}break;
    	    case 'P' : case 'p' : for(int i=0; i<ventas.Largo(); i++){if(ventas.KEsimo(i).getTipoVenta() == true ){resultado.add( new VOListadoVentas( opcion, ventas.KEsimo(i).getNumero(), ventas.KEsimo(i).getFecha(), ventas.KEsimo(i).getDireccionEntrega(), ventas.KEsimo(i).getTipoVenta(), ventas.KEsimo(i).getMontoFinal()));}}break;   	    
    	    case 'F' : case 'f' : for(int i=0; i<ventas.Largo(); i++){if(ventas.KEsimo(i).getTipoVenta() == false ){resultado.add( new VOListadoVentas( opcion, ventas.KEsimo(i).getNumero(), ventas.KEsimo(i).getFecha(), ventas.KEsimo(i).getDireccionEntrega(), ventas.KEsimo(i).getTipoVenta(), ventas.KEsimo(i).getMontoFinal()));}}break;
    	    default  : monitor.terminoLectura(); throw new NoEsTPFException ("Error, el caracter ingresado no es T,P o F ");
    	}
    	monitor.terminoLectura();
    	return resultado ;
    }
    
        
    public ArrayList<VOListadoPostresVentas>ListadoPostresVentas(VOListadoPostresVentas vo) throws RemoteException, VentaNoExisteException
    {	
    	System.out.println("Inicio Requerimiento 9");
    	monitor.comienzoLectura();    	
    	ArrayList<VOListadoPostresVentas> resultado = new ArrayList<>();
    	if(ventas.Contiene(vo.VOgetNumero()))
    	{
    		Venta ventaBuscada = ventas.buscarPorNumero(vo.VOgetNumero());
    		Postre menosCodigo;PostreAdquirido menosCodigo2;
    		for (int i=0; i < ventaBuscada.getPostresAdquiridos().Largo(); i++)
    		{		
    			menosCodigo = ventaBuscada.getPostresAdquiridos().KEsimo(i).getPostre() ;
    			menosCodigo2 =  ventaBuscada.getPostresAdquiridos().KEsimo(i);
    			VOListadoPostresVentas vAux = new VOListadoPostresVentas (vo.VOgetNumero(), menosCodigo.getCodigo(), menosCodigo.getNombre(),
	    					                                                  menosCodigo.getPrecio(), menosCodigo.getTipo(),menosCodigo2.getCantPostres());
    			resultado.add(vAux)	;	                                                 
    		}
    	}
    	else
    	{
        	monitor.terminoLectura();
        	throw new VentaNoExisteException ("Error, numero de venta inexistente ");	
    	}
    	monitor.terminoLectura();
    	System.out.println("Fin requerimiento 9");
    	return resultado ;
    }
    
    public VOMontoTotal RecaudacionPostreFecha(VOMontoTotal vo) throws RemoteException, PostreNoExisteException
    {
        System.out.println("Inicio Requerimiento 10");
        monitor.comienzoLectura();
        if(!postres.member(vo.VOgetCodigo()))
        {
            monitor.terminoLectura();
            throw new PostreNoExisteException("Error, el codigo ingresado no pertenece a ningun postre");
        }

        double precio = postres.obtener(vo.VOgetCodigo()).getPrecio();
        double montoAcumulado = 0;
        int unidadesAcumuladas = 0;
        for(int i = 0; i < ventas.Largo(); i++)
        {
            Venta v = ventas.KEsimo(i);
            if(v.getFecha().equals(vo.VOgetFecha()))
            {
                if(!v.getTipoVenta()) // finalizada
                {
                    if(!v.getPostresAdquiridos().EsVacia())
                    {
                        for(int j = 0; j < v.getPostresAdquiridos().Largo(); j++)
                        {
                            PostreAdquirido pA = v.getPostresAdquiridos().KEsimo(j);
                            if(pA.getPostre().getCodigo().equals(vo.VOgetCodigo()))
                            {
                                montoAcumulado    += pA.getCantPostres() * precio;
                                unidadesAcumuladas += pA.getCantPostres();
                            }
                        }
                    }
                }
            }
        }  
        monitor.terminoLectura();
        System.out.println("Fin Requerimiento 10 ");
        return new VOMontoTotal(vo.VOgetCodigo(), montoAcumulado, unidadesAcumuladas, vo.VOgetFecha());
    }
 
  
	@Override
    public void Respaldar(VOSistema voSistema) throws RemoteException
    {
        Postre[] arrayPostres = this.postres.values().toArray(new Postre[0]);
        Venta[] arrayVentas = new Venta[this.ventas.Largo()];
        for (int i = 0; i < this.ventas.Largo(); i++)
        {
            arrayVentas[i] = this.ventas.KEsimo(i);
        }
        voSistema = new VOSistema(arrayPostres, arrayVentas);
        persistencia ds = new persistencia();
        Properties p = new Properties();
        try
        {
            p.load(new FileInputStream("config/archivo.properties"));
            String nomArch = p.getProperty("nombreArchivo");
            VOFachada voFachada = new VOFachada(this.postres, this.ventas, this.contadorVentas);
            ds.respaldar(nomArch, voFachada);
            System.out.println("Respaldo realizado exitosamente.");
        }
        catch (Exception e)
        {
            System.out.println("Error al respaldar: " + e.getMessage());
        }
    }

    @Override
    public VOSistema Recuperar() throws RemoteException
    {
        persistencia ds = new persistencia();
        Properties p = new Properties();
        monitor.comienzoEscritura();
        try
        {
            p.load(new FileInputStream("config/archivo.properties"));
            String nomArch = p.getProperty("nombreArchivo");
            VOFachada voFachada = ds.recuperar(nomArch);

            if (voFachada == null)
            {
                System.out.println("No hay respaldo disponible.");
                return null;
            }

            // Cargar datos en memoria de la Fachada (protegido por monitor)
            setPostres(voFachada.VOgetPostres());
            setVentas(voFachada.VOgetVentas());
            contadorVentas = voFachada.VOgetContadorVentas();

            // Construir VOSistema para devolver a la GUI
            Postre[] arrayPostres = this.postres.values().toArray(new Postre[0]);
            Venta[] arrayVentas = new Venta[this.ventas.Largo()];
            for (int i = 0; i < this.ventas.Largo(); i++)
                arrayVentas[i] = this.ventas.KEsimo(i);

            System.out.println("Recuperacion realizada exitosamente.");
            return new VOSistema(arrayPostres, arrayVentas);
        }
        catch (IOException | excepciones.PersistenciaException e)
        {
            System.out.println("Error al recuperar: " + e.getMessage());
            return null;
        }
        finally
        {
            monitor.terminoEscritura();
        }
    }
}
    
    
    //BORRADOR DE RESPALDAR
    
//  public void Respaldar (VOSistema voSistema) throws RemoteException, IOException 
//  {
//      // Gather all data into a VO (Value Object)
//      voSistema = new VOSistema(this.postres, this.ventas);
//
//      // Serialize to binary file
//      try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("backup.dat"))) 
//      {
//          oos.writeObject(voSistema);
//      }
//  }
    
    
    


// requerimientos--> RMI -->PROBAR RMI CON SERVIDOR (PUERTO,URL)-->CLIENTE CONECTA CON FACHADA
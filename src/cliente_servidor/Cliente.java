package cliente_servidor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

import capaLogica.IFachada;
import excepciones.DiccioVacioException;
import excepciones.FechaInvalidaException;
import excepciones.NoEsTPFException;
import excepciones.NoExisteVentaFinalizadaException;
import excepciones.PostreNoEnVentaException;
import excepciones.PostreNoExisteException;
import excepciones.PostreYaExisteException;
import excepciones.SeSupero40Exception;
import excepciones.SumaSuperaLimiteException;
import excepciones.VentaNoExisteException;
import excepciones.VentaYaFinalizadaException;
import valueObjects.VOListadoPostresVentas;
import valueObjects.VOListadoVentas;
import valueObjects.VOModificarEstado;
import valueObjects.VOModificarPostreVenta;
import valueObjects.VOMontoTotal;
import valueObjects.VONuevaVenta;
import valueObjects.VOPostre;
import valueObjects.VOSistema;

public class Cliente
{
    // La fachada es la conexion con el servidor
    // Se usa IFachada (la interfaz), NUNCA la clase Fachada directamente
    private IFachada fachada;

    // -------------------------------------------------------
    // CONSTRUCTOR: lee el properties y se conecta al servidor
    // -------------------------------------------------------
    public Cliente()
    {
        // Leer configuracion
        Properties p = new Properties();
        String nomArch = "config/config.properties";
        String ip     = null;
        String puerto = null;
        String nombre = null;

        try
        {
            p.load(new FileInputStream(nomArch));
            ip     = p.getProperty("ip");
            puerto = p.getProperty("puerto");
            nombre = p.getProperty("nombre");
        }
        catch (FileNotFoundException e) // si no encuentra el archivo de configuracion
        {
            System.out.println("Archivo de configuracion no encontrado: " + nomArch);
            e.printStackTrace();
            return;
        }
        catch (IOException e) // si ocurre cualquier otro error de E/S
        {
            System.out.println("Error al leer el archivo de configuracion: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        String url = "//" + ip + ":" + puerto + "/" + nombre;
        System.out.println("Conectando a: " + url);

        // Conectarse al servidor
        try
        {
            fachada = (IFachada) Naming.lookup(url);
            System.out.println("Conexion al servidor OK");
        }
        catch (MalformedURLException e) // si la ruta no esta bien formada
        {
            System.out.println("URL mal formada: " + e.getMessage());
            e.printStackTrace();
            fachada = null;
        }
        catch (RemoteException e) // si ocurre cualquier problema de red
        {
            System.out.println("Error de red: " + e.getMessage());
            e.printStackTrace();
            fachada = null;
        }
        catch (NotBoundException e) // si la ruta esta bien formada pero el servidor esta bajo
        {
            System.out.println("La Fachada no esta publicada en el servidor: " + e.getMessage());
            e.printStackTrace();
            fachada = null;
        }
    }

    public IFachada getFachada()
    {
        return fachada;
    }

    // -------------------------------------------------------
    // REQ 1: Registrar un nuevo postre
    // -------------------------------------------------------
    public void registrarNuevoPostre(VOPostre voPostre)
    {
        try
        {
            fachada.RegistrarNuevoPostre(voPostre);
            System.out.println("Postre registrado OK");
        }
        catch (RemoteException e)         { System.out.println("Error de red: " + e.getMessage()); }
        catch (PostreYaExisteException e)  { System.out.println("Error: " + e.getMessage()); }
    }

    // -------------------------------------------------------
    // REQ 2: Listado general de postres
    // -------------------------------------------------------
    public ArrayList<VOPostre> listadoGeneralDePostres()
    {
        try
        {
            ArrayList<VOPostre> lista = fachada.ListadoGeneralDePostres();
            System.out.println("Listado obtenido OK - " + lista.size() + " postres");
            return lista;
        }
        catch (RemoteException e)      { System.out.println("Error de red: " + e.getMessage()); }
        catch (DiccioVacioException e) { System.out.println("Error: " + e.getMessage()); }
        return null;
    }

    // -------------------------------------------------------
    // REQ 3: Listado detallado de un postre
    // -------------------------------------------------------
    public VOPostre listadoDetalladoPostre(VOPostre voPostre)
    {
        try
        {
            VOPostre resultado = fachada.ListadoDetalladoPostre(voPostre);
            System.out.println("Detalle del postre obtenido OK");
            return resultado;
        }
        catch (RemoteException e)          { System.out.println("Error de red: " + e.getMessage()); }
        catch (PostreNoExisteException e)   { System.out.println("Error: " + e.getMessage()); }
        catch (DiccioVacioException e)      { System.out.println("Error: " + e.getMessage()); }
        return null;
    }

    // -------------------------------------------------------
    // REQ 4: Registrar una nueva venta
    // -------------------------------------------------------
    public void registrarNuevaVenta(VONuevaVenta voVenta)
    {
        try
        {
            fachada.RegistrarNuevaVenta(voVenta);
            System.out.println("Venta registrada OK");
        }
        catch (RemoteException e)        { System.out.println("Error de red: " + e.getMessage()); }
        catch (FechaInvalidaException e) { System.out.println("Error: " + e.getMessage()); }
    }

    // -------------------------------------------------------
    // REQ 5: Agregar postre a una venta
    // -------------------------------------------------------
    public void agregarPostreVenta(VOModificarPostreVenta vo)
    {
        try
        {
            fachada.AgregarPostreVenta(vo);
            System.out.println("Postre agregado a la venta OK");
        }
        catch (RemoteException e)             { System.out.println("Error de red: " + e.getMessage()); }
        catch (SumaSuperaLimiteException e)    { System.out.println("Error: " + e.getMessage()); }
        catch (SeSupero40Exception e)          { System.out.println("Error: " + e.getMessage()); }
        catch (VentaNoExisteException e)       { System.out.println("Error: " + e.getMessage()); }
        catch (VentaYaFinalizadaException e)   { System.out.println("Error: " + e.getMessage()); }
        catch (PostreNoExisteException e)      { System.out.println("Error: " + e.getMessage()); }
    }

    // -------------------------------------------------------
    // REQ 6: Eliminar postre de una venta
    // -------------------------------------------------------
    public void eliminarPostreVenta(VOModificarPostreVenta vo)
    {
        try
        {
            fachada.EliminarPostreVenta(vo);
            System.out.println("Postre eliminado de la venta OK");
        }
        catch (RemoteException e)          { System.out.println("Error de red: " + e.getMessage()); }
        catch (PostreNoEnVentaException e)  { System.out.println("Error: " + e.getMessage()); }
        catch (VentaNoExisteException e)    { System.out.println("Error: " + e.getMessage()); }
        catch (VentaYaFinalizadaException e){ System.out.println("Error: " + e.getMessage()); }
        catch (PostreNoExisteException e)   { System.out.println("Error: " + e.getMessage()); }
    }

    // -------------------------------------------------------
    // REQ 7: Finalizar una venta
    // -------------------------------------------------------
    public void finalizarVenta(VOModificarEstado vo)
    {
        try
        {
            fachada.FinalizarVentas(vo);
            System.out.println("Venta finalizada OK");
        }
        catch (RemoteException e)              { System.out.println("Error de red: " + e.getMessage()); }
        catch (VentaYaFinalizadaException e)   { System.out.println("Error: " + e.getMessage()); }
        catch (VentaNoExisteException e)       { System.out.println("Error: " + e.getMessage()); }
    }

    // -------------------------------------------------------
    // REQ 8: Listado de ventas (T=todas, P=en proceso, F=finalizadas)
    // -------------------------------------------------------
    public ArrayList<VOListadoVentas> listadoVentas(char indicacion)
    {
        try
        {
            VOListadoVentas vo = new VOListadoVentas(indicacion, 0, null, null, false, 0);
            ArrayList<VOListadoVentas> lista = fachada.ListadoVentas(vo);
            System.out.println("Listado de ventas obtenido OK - " + lista.size() + " ventas");
            return lista;
        }
        catch (RemoteException e)   { System.out.println("Error de red: " + e.getMessage()); }
        catch (NoEsTPFException e)  { System.out.println("Error: " + e.getMessage()); }
        return null;
    }

    // -------------------------------------------------------
    // REQ 9: Listado de postres de una venta
    // -------------------------------------------------------
    public ArrayList<VOListadoPostresVentas> listadoPostresVentas(int numeroVenta)
    {
        try
        {
            VOListadoPostresVentas vo = new VOListadoPostresVentas(numeroVenta, "0", null, 0, false, 0);
            ArrayList<VOListadoPostresVentas> lista = fachada.ListadoPostresVentas(vo);
            System.out.println("Listado de postres de la venta obtenido OK - " + lista.size() + " postres");
            return lista;
        }
        catch (RemoteException e)        { System.out.println("Error de red: " + e.getMessage()); }
        catch (VentaNoExisteException e) { System.out.println("Error: " + e.getMessage()); }
        return null;
    }

    // -------------------------------------------------------
    // REQ 10: Recaudacion de un postre en una fecha
    // -------------------------------------------------------
    
    public VOMontoTotal recaudacionPostreFecha(String codigo, LocalDate fecha)
    {
        try
        {
            VOMontoTotal vo = new VOMontoTotal(codigo, 0, 0, fecha);
            VOMontoTotal resultado = fachada.RecaudacionPostreFecha(vo);
            System.out.println("Recaudacion calculada OK - Monto: " + resultado.VOgetMontoTotal() 
                               + " | Unidades: " + resultado.VOgetCantPostres());
            return resultado;
        }
        catch (RemoteException e)          { System.out.println("Error de red: " + e.getMessage()); }
        catch (PostreNoExisteException e)  { System.out.println("Error: " + e.getMessage()); }
        return null;
    }
    
    
    
    //    public void recaudacionPostreFecha(long codigo, LocalDate fecha)
//    {
//        try
//        {
//            VOMontoTotal vo = new VOMontoTotal(codigo, 0, 0, fecha);
//            fachada.RecaudacionPostreFecha(vo);
//            System.out.println("Recaudacion calculada OK");
//        }
//        catch (RemoteException e)                    { System.out.println("Error de red: " + e.getMessage()); }
//        catch (NoExisteVentaFinalizadaException e)   { System.out.println("Error: " + e.getMessage()); }
//        catch (FechaInvalidaException e)             { System.out.println("Error: " + e.getMessage()); }
//        catch (PostreNoExisteException e)            { System.out.println("Error: " + e.getMessage()); }
//    }

    // -------------------------------------------------------
    // REQ 11: Respaldar
    // -------------------------------------------------------
    public void respaldar()
    {
        try
        {
            fachada.Respaldar(null);
            System.out.println("Respaldo realizado OK");
        }
        catch (RemoteException e) { System.out.println("Error de red: " + e.getMessage()); }
    }

    // -------------------------------------------------------
    // REQ 12: Recuperar
    // -------------------------------------------------------
    public VOSistema recuperar()
    {
        try
        {
            VOSistema vo = fachada.Recuperar();
            System.out.println("Recuperacion realizada OK");
            return vo;
        }
        catch (RemoteException e) { System.out.println("Error de red: " + e.getMessage()); }
        return null;
    }

    // -------------------------------------------------------
    // MAIN de prueba rapida
    // IMPORTANTE: PruebaServidor.java debe estar corriendo primero
    // -------------------------------------------------------
    public static void main(String[] args)
    {
        System.out.println("==============================================");
        System.out.println("   CLIENTE RMI");
        System.out.println("==============================================\n");

        Cliente cliente = new Cliente();

        // Verificar que la conexion fue exitosa antes de operar
        if (cliente.getFachada() == null)
        {
            System.out.println("No se pudo conectar al servidor. Verificar:");
            System.out.println("  1) Que PruebaServidor.java este corriendo");
            System.out.println("  2) Que la IP en config/config.properties sea correcta");
            System.out.println("  3) Que ambas PCs esten en la misma red");
            return;
        }

        // Prueba REQ 1: registrar un postre
        VOPostre voPostre = new VOPostre("2001L", "Cheesecake", 420.0, true);
        cliente.registrarNuevoPostre(voPostre);

        // Prueba REQ 2: listar todos los postres
        ArrayList<VOPostre> postres = cliente.listadoGeneralDePostres();
        if (postres != null)
            postres.forEach(p -> System.out.println("  -> " + p.toString()));

        // Prueba REQ 4: registrar una venta
        VONuevaVenta voVenta = new VONuevaVenta(LocalDate.now(), "Rivera 1234", true, 0);
        cliente.registrarNuevaVenta(voVenta);

        // Prueba REQ 8: listar todas las ventas
        ArrayList<VOListadoVentas> ventas = cliente.listadoVentas('T');
        if (ventas != null)
            ventas.forEach(v -> System.out.println("  -> " + v.toString()));
     
        //Prueba REQ 9 : Listado postres de una venta
        // Verificar que la venta tiene 0 postres (PostresAdquiridos vacio)
        ArrayList<VOListadoPostresVentas> postresDeVenta = cliente.listadoPostresVentas(0); // numero de venta 0
        if (postresDeVenta != null)
        System.out.println("Postres en la venta: " + postresDeVenta.size()); // deberia dar 0
        
	     // --- PRUEBA REQ 10 ---
	     // Paso 1: registrar un postre
	     VOPostre voP = new VOPostre("3001L", "Tiramisu", 350.0, true);
	     cliente.registrarNuevoPostre(voP);
	
	     // Paso 2: registrar una venta con fecha de hoy
	     VONuevaVenta voV = new VONuevaVenta(LocalDate.now(), "Av. Italia 1234", true, 0);
	     cliente.registrarNuevaVenta(voV);
	
	     // Paso 3: agregar el postre a esa venta (necesitas saber el numero de venta)
	     // listá las ventas primero para ver el numero
	     ArrayList<VOListadoVentas> ventas2 = cliente.listadoVentas('T');
	     if(ventas2 != null)
	         ventas2.forEach(v -> System.out.println("  Venta numero: " + v.toString()));
	
	     // Paso 4: agregar postre a la venta numero 1
	     VOModificarPostreVenta voMod = new VOModificarPostreVenta("3001L", 5, 1);
	     cliente.agregarPostreVenta(voMod);

	     // Paso 5: finalizar la venta numero 1
	     VOModificarEstado voEst = new VOModificarEstado(1, true, 1750.0, true);
	     cliente.finalizarVenta(voEst);
	
	     // Paso 6: calcular recaudacion - deberia dar monto=1750.0, unidades=5
	     VOMontoTotal resultado = cliente.recaudacionPostreFecha("3001L", LocalDate.now());
	     if(resultado != null)
	         System.out.println("Monto: " + resultado.VOgetMontoTotal() + " | Unidades: " + resultado.VOgetCantPostres());
	        
        System.out.println("\n==============================================");
        System.out.println("   FIN DEL CLIENTE");
        System.out.println("==============================================");
    }
}
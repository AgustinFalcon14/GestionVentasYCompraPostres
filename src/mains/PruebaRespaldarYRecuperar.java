package mains;

import java.rmi.RemoteException;
import java.time.LocalDate;

import capaLogica.Fachada;
import capaLogica.Postre;
import capaLogica.Venta;
import excepciones.PersistenciaException;
import valueObjects.VOPostre;
import valueObjects.VOSistema;
import valueObjects.VONuevaVenta;

/**
 * PRUEBA DE RESPALDAR Y RECUPERAR
 *
 * Qué hace esta prueba:
 *   1) Instancia la Fachada
 *   2) Registra un postre y una venta para que haya datos
 *   3) Llama a Respaldar() - guarda el estado en backup.dat
 *   4) Llama a Recuperar() - lee el backup.dat
 *   5) Verifica que los datos recuperados coincidan con los guardados
 *
 * CÓMO EJECUTAR:
 *   Click derecho -> Run As -> Java Application
 */
public class PruebaRespaldarYRecuperar
{
    public static void main(String[] args)
    {
        System.out.println("==============================================");
        System.out.println("   PRUEBA DE RESPALDAR Y RECUPERAR");
        System.out.println("==============================================\n");

        Fachada fachada = null;

        // PASO 1: Instanciar la Fachada
        System.out.print("PASO 1 - Instanciando Fachada... ");
        try
        {
            fachada = new Fachada();
            System.out.println("OK");
        }
        catch (RemoteException e)
        {
            System.out.println("ERROR (RemoteException): " + e.getMessage());
            e.printStackTrace();
            return;
        }
        catch (PersistenciaException e)
        {
            System.out.println("ERROR (PersistenciaException): " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // PASO 2: Agregar un postre para tener datos
        System.out.print("PASO 2 - Registrando postre de prueba (cod=1001)... ");
        try
        {
            VOPostre voPostre = new VOPostre("1001L", "Tiramisu", 350.0, true);
            fachada.RegistrarNuevoPostre(voPostre);
          
            System.out.println("OK");
        }
        catch (Exception e)
        {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // PASO 3: Agregar una venta para tener datos
        System.out.print("PASO 3 - Registrando venta de prueba... ");
        try
        {
            VONuevaVenta voVenta = new VONuevaVenta(LocalDate.now(), "Av. 18 de Julio 1234", true, 0);
            fachada.RegistrarNuevaVenta(voVenta);
            System.out.println("OK");
        }
        catch (Exception e)
        {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // PASO 4: Respaldar
        System.out.print("PASO 4 - Llamando a Respaldar()... ");
        try
        {
            fachada.Respaldar(null);
            System.out.println("OK - backup.dat generado");
        }
        catch (RemoteException e)
        {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // PASO 5: Recuperar y verificar datos
        System.out.print("PASO 5 - Llamando a Recuperar()... ");
        try
        {
            VOSistema recuperado = fachada.Recuperar();

            if (recuperado == null)
            {
                System.out.println("ERROR - Recuperar() devolvio null");
                return;
            }

            System.out.println("OK");
            System.out.println("\n--- Verificacion de datos recuperados ---");

            Postre[] postresRecuperados = recuperado.VOgetPostres();
            if (postresRecuperados != null && postresRecuperados.length > 0)
            {
                System.out.println("Postres recuperados: " + postresRecuperados.length);
                for (Postre p : postresRecuperados)
                    System.out.println("  -> " + p.toString());
            }
            else
            {
                System.out.println("AVISO: No se recuperaron postres.");
            }

            Venta[] ventasRecuperadas = recuperado.VOgetVenta();
            if (ventasRecuperadas != null && ventasRecuperadas.length > 0)
            {
                System.out.println("Ventas recuperadas: " + ventasRecuperadas.length);
                for (Venta v : ventasRecuperadas)
                    System.out.println("  -> Venta #" + v.getNumero() + " | Fecha: " + v.getFecha() + " | Dir: " + v.getDireccionEntrega());
            }
            else
            {
                System.out.println("AVISO: No se recuperaron ventas.");
            }
        }
        catch (RemoteException e)
        {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        System.out.println("\n==============================================");
        System.out.println("   PRUEBA FINALIZADA");
        System.out.println("==============================================");
    }
}


/*
 * ANTERIOR FORMA (FUNCIONA DE MODO HARDCODE
 * 
 * 
public class PruebaRespaldarYRecuperar 
{

    public static void main(String[] args)
    {

        try
        {
            // ── 1. Crear la Fachada (ya intenta recuperar automáticamente) ──
            System.out.println("=== Iniciando sistema ===");
            Fachada fachada = new Fachada();

            // ── 2. Agregar algunos postres de prueba ──
            System.out.println("\n=== Registrando postres ===");
            VOPostre voPostre1 = new VOPostre(10021, "Tiramisú", 150.0, true);
            VOPostre voPostre2 = new VOPostre(10022, "Flan", 120.0, true);
            fachada.RegistrarNuevoPostre(voPostre1);
            fachada.RegistrarNuevoPostre(voPostre2);

            // ── 3. Agregar una venta de prueba ──
            System.out.println("\n=== Registrando venta ===");
            VONuevaVenta voVenta = new VONuevaVenta(
                LocalDate.of(2024, 1, 15),
                "Av. Siempreviva 742",
                true,
                0.0
            );
            fachada.RegistrarNuevaVenta(voVenta);

            // ── 4. RESPALDAR ──
            System.out.println("\n=== Respaldando datos ===");
            persistencia ds = new persistencia();
            VOFachada voFachada = new VOFachada(
                fachada.getPostres(),
                fachada.getVentas()
            );

        
            
            String nombreArchivo=  ""
            		+ "config/backup.dat";    // ajusta la ruta según tu proyecto
            ds.respaldar(nombreArchivo, voFachada);
            System.out.println("Respaldo exitoso en: " + nombreArchivo);

            // ── 5. RECUPERAR ──
            System.out.println("\n=== Recuperando datos ===");
            VOFachada voRecuperado = ds.recuperar(nombreArchivo);

            if (voRecuperado != null) {
                System.out.println("Recuperacion exitosa.");
                System.out.println("Postres recuperados: "
                    + voRecuperado.VOgetPostres().Largo());
                System.out.println("Ventas recuperadas:  "
                    + voRecuperado.VOgetVentas().Largo());
            } else {
                System.out.println("No se encontraron datos para recuperar.");
            }

            
        }
        catch (PersistenciaException e)
        {
            System.err.println("Error de persistencia: " + e.getMessage());
        }
        catch (PostreYaExisteException e)
        {
            System.err.println("Error: postre ya existe: (borrar backupdata) " + e.getMessage());
        } 
        catch (FechaInvalidaException e) 
        {
            System.err.println("Error de fecha: " + e.getMessage());
        } 
        catch (Exception e) 
        {
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
 
 * */




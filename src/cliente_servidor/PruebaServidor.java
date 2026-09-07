package cliente_servidor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Properties;
import capaLogica.Fachada;
import capaLogica.IFachada;
import excepciones.PersistenciaException;

public class PruebaServidor
{
    public static void main(String[] args)
    {
        System.out.println("==============================================");
        System.out.println("   PRUEBA DEL SERVIDOR RMI ");
        System.out.println("==============================================\n");

        // Leer configuración desde archivo
        Properties p = new Properties();
        String nomArch = "config/config.properties";
        String ip = null;
        String puerto = null;
        String nombre = null;
        int port = 0;

        try
        {
            p.load(new FileInputStream(nomArch));
            ip     = p.getProperty("ip");
            puerto = p.getProperty("puerto");
            nombre = p.getProperty("nombre");
            port   = Integer.parseInt(puerto);
            System.out.println("Configuracion cargada: IP=" + ip + " | Puerto=" + puerto + " | Nombre=" + nombre);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("ERROR - Archivo de configuracion no encontrado: " + nomArch);
            e.printStackTrace();
            return;
        }
        catch (IOException e)
        {
            System.out.println("ERROR - Fallo al leer el archivo de configuracion.");
            e.printStackTrace();
            return;
        }

        // PASO 1: Levantar el rmiregistry en el puerto configurado
        System.out.print("PASO 1 - Levantando rmiregistry en puerto " + port + "... ");
        try
        {
            LocateRegistry.createRegistry(port);
            System.out.println("OK");
        }
        catch (RemoteException e)
        {
            System.out.println("AVISO: " + e.getMessage() + " (puede que ya este activo)");
        }

        // PASO 2: Instanciar la Fachada
        System.out.print("PASO 2 - Instanciando Fachada... ");
        Fachada fachada = null;
        try
        {
            fachada = Fachada.getInstancia();
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

        // PASO 3: Publicar la Fachada con Naming.rebind
        String ruta = "//" + ip + ":" + puerto + "/" + nombre;
        System.out.print("PASO 3 - Publicando Fachada en " + ruta + "... ");
        try
        {
            Naming.rebind(ruta, fachada);
            System.out.println("OK");
        }
        catch (RemoteException e)
        {
            System.out.println("ERROR (RemoteException): " + e.getMessage());
            e.printStackTrace();
            return;
        }
        catch (MalformedURLException e)
        {
            System.out.println("ERROR (MalformedURLException): " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // PASO 4: Verificar con lookup
        System.out.print("PASO 4 - Verificando con Naming.lookup... ");
        try
        {
            IFachada fachadaRecuperada = (IFachada) Naming.lookup(ruta);
            if (fachadaRecuperada != null)
                System.out.println("OK - Objeto remoto recuperado correctamente");
            else
                System.out.println("ERROR - lookup devolvio null");
        }
        catch (Exception e)
        {
            System.out.println("ERROR en lookup: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        System.out.println("\n==============================================");
        System.out.println("   TODOS LOS PASOS PASARON CORRECTAMENTE");
        System.out.println("==============================================");
        System.out.println("\nPrueba finalizada. Fachada publicada en " + ruta);
    }
}


package cliente_servidor;

import capaGrafica.VentanaPrincipal;
import capaLogica.IFachada;

public class PruebaClienteServidorGUI
{
    public static void main(String[] args)
    {
        // Conectarse al servidor RMI (lee config/config.properties)
        Cliente cliente = new Cliente();  
        IFachada fachada = cliente.getFachada();

        // Verificar conexión antes de abrir la GUI
        if (fachada == null)
        {
            System.out.println("No se pudo conectar al servidor. Verificar:");
            System.out.println("  1) Que PruebaServidor se encuentre corriendo");
            System.out.println("  2) Que la IP en config/config.properties sea correcta");
            return;
        }

        // Abrir la GUI conectada al servidor
        VentanaPrincipal ventana = new VentanaPrincipal(fachada);
        ventana.setVisible(true);
    }
}
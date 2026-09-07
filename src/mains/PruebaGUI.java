package mains;

import capaGrafica.VentanaPrincipal;
import capaLogica.Fachada;
import capaLogica.IFachada;
import excepciones.PersistenciaException;

/**
 * Main de prueba local de la GUI.
 * Usa la Fachada directamente (sin RMI) para poder probar
 * la interfaz gráfica sin necesidad de un servidor RMI activo.
 *
 * Para usar con servidor RMI, ver PruebaVentanaPrincipal.java
 */
public class PruebaGUI {

    public static void main(String[] args)
    {
        try 
        {
            // Instanciamos la fachada directamente (sin RMI)
            IFachada fachada = new Fachada();

            // Creamos y mostramos la ventana principal
            VentanaPrincipal ventana = new VentanaPrincipal(fachada);
            ventana.setVisible(true);

        } catch (PersistenciaException e) {
            System.out.println("Error al inicializar la Fachada: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

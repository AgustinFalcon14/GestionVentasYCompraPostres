package capaGraficaControladores;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import capaLogica.IFachada;
import valueObjects.VOModificarPostreVenta;
import excepciones.*;

public class CtrlEliminarPostreVenta {

    private IFachada fachada;

    public CtrlEliminarPostreVenta(IFachada fachada) {
        this.fachada = fachada;
    }

    public void eliminar(JFrame frame, String strCodigo, String strCant, String strNumVenta) {
        try {
            if (strCodigo.isEmpty() || strCant.isEmpty() || strNumVenta.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Complete todos los campos.",
                    "Datos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String codigo = strCodigo;
            int cant = Integer.parseInt(strCant.trim());
            int numVenta = Integer.parseInt(strNumVenta.trim());

            VOModificarPostreVenta vo = new VOModificarPostreVenta(codigo, cant, numVenta);
            fachada.EliminarPostreVenta(vo);

            JOptionPane.showMessageDialog(frame, "Postre eliminado de la venta exitosamente.",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Código, cantidad y número de venta deben ser numéricos.",
                "Error de formato", JOptionPane.ERROR_MESSAGE);
        } catch (PostreNoEnVentaException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMensaje(), "No en venta", JOptionPane.ERROR_MESSAGE);
        } catch (VentaNoExisteException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMensaje(), "Venta no existe", JOptionPane.ERROR_MESSAGE);
        } catch (VentaYaFinalizadaException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMensaje(), "Venta ya finalizada", JOptionPane.ERROR_MESSAGE);
        } catch (PostreNoExisteException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMensaje(), "Postre no existe", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
package capaGraficaControladores;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import capaLogica.IFachada;
import valueObjects.VOModificarEstado;
import excepciones.*;

public class CtrlFinalizarVenta {

    private IFachada fachada;

    public CtrlFinalizarVenta(IFachada fachada) {
        this.fachada = fachada;
    }

    public void finalizar(JFrame frame, String strNumVenta, boolean confirmacion) {
        try {
            if (strNumVenta.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Ingrese el número de la venta.",
                    "Datos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int numVenta = Integer.parseInt(strNumVenta.trim());

            VOModificarEstado vo = new VOModificarEstado(numVenta, true, 0, confirmacion);
            double monto = fachada.FinalizarVentas(vo);

            if (confirmacion && monto > 0)
                JOptionPane.showMessageDialog(frame, "Venta finalizada exitosamente.\nMonto total a abonar: $" + String.format("%.2f", monto),
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(frame, "Venta eliminada del sistema.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Número de venta y monto deben ser numéricos.",
                "Error de formato", JOptionPane.ERROR_MESSAGE);
        } catch (VentaYaFinalizadaException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMensaje(), "Venta ya finalizada", JOptionPane.ERROR_MESSAGE);
        } catch (VentaNoExisteException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMensaje(), "Venta no existe", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
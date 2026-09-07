package capaGraficaControladores;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import capaLogica.IFachada;
import valueObjects.VONuevaVenta;
import excepciones.FechaInvalidaException;

public class CtrlNuevaVenta {

    private IFachada fachada;

    public CtrlNuevaVenta(IFachada fachada) {
        this.fachada = fachada;
    }

    public void registrar(JFrame frame, String strFecha, String direccion) {
        try {
            if (strFecha.isEmpty() || direccion.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Complete todos los campos.",
                    "Datos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            LocalDate fecha = LocalDate.parse(strFecha.trim());

            VONuevaVenta vo = new VONuevaVenta(fecha, direccion.trim(), true, 0);
            fachada.RegistrarNuevaVenta(vo);

            JOptionPane.showMessageDialog(frame, "Venta registrada exitosamente.",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (java.time.format.DateTimeParseException ex) {
            JOptionPane.showMessageDialog(frame, "Formato de fecha inválido. Use AAAA-MM-DD.",
                "Error de formato", JOptionPane.ERROR_MESSAGE);
        } catch (FechaInvalidaException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMensaje(),
                "Fecha inválida", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
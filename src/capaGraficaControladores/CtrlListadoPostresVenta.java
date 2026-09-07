package capaGraficaControladores;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import capaLogica.IFachada;
import valueObjects.VOListadoPostresVentas;
import excepciones.VentaNoExisteException;

public class CtrlListadoPostresVenta {

    private IFachada fachada;

    public CtrlListadoPostresVenta(IFachada fachada) {
        this.fachada = fachada;
    }

    public void cargar(JFrame frame, String strNumVenta, DefaultTableModel modelo) {
        try {
            if (strNumVenta.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Ingrese el número de la venta.",
                    "Datos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            modelo.setRowCount(0);
            int numVenta = Integer.parseInt(strNumVenta.trim());

            // VO de consulta con el número de venta
            VOListadoPostresVentas voConsulta = new VOListadoPostresVentas(numVenta, "0", "", 0, true, 0);
            ArrayList<VOListadoPostresVentas> lista = fachada.ListadoPostresVentas(voConsulta);

            for (VOListadoPostresVentas vo : lista) {
                String tipo = vo.VOgetTipoPostre() ? "Común" : "Light";
                modelo.addRow(new Object[]{
                    vo.VOgetCodigo(),
                    vo.VOgetNombre(),
                    String.format("%.2f", vo.VOgetPrecio()),
                    tipo,
                    vo.VOgetCantP()
                });
            }

            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "La venta no tiene postres registrados.",
                    "Sin postres", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "El número de venta debe ser numérico.",
                "Error de formato", JOptionPane.ERROR_MESSAGE);
        } catch (VentaNoExisteException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMensaje(), "Venta no existe", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

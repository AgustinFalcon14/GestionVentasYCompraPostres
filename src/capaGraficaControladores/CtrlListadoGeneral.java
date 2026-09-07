package capaGraficaControladores;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import capaLogica.IFachada;
import valueObjects.VOPostre;
import excepciones.DiccioVacioException;

public class CtrlListadoGeneral {

    private IFachada fachada;

    public CtrlListadoGeneral(IFachada fachada) {
        this.fachada = fachada;
    }

    public void cargar(JFrame frame, DefaultTableModel modelo) {
        try {
            // Limpiar tabla
            modelo.setRowCount(0);

            ArrayList<VOPostre> lista = fachada.ListadoGeneralDePostres();
            for (VOPostre vo : lista) {
                String tipo = vo.VOgetTipo() ? "Común" : "Light";
                modelo.addRow(new Object[]{
                    vo.VOgetCodigo(),
                    vo.VOgetNombre(),
                    String.format("%.2f", vo.VOgetPrecio()),
                    tipo
                });
            }

            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No hay postres registrados.",
                    "Listado vacío", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (DiccioVacioException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMensaje(),
                "Diccionario vacío", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

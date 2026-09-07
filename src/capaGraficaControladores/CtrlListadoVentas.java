package capaGraficaControladores;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import capaLogica.IFachada;
import valueObjects.VOListadoVentas;
import excepciones.NoEsTPFException;

public class CtrlListadoVentas {

    private IFachada fachada;

    public CtrlListadoVentas(IFachada fachada) {
        this.fachada = fachada;
    }

    public void cargar(JFrame frame, DefaultTableModel modelo, char indicacion) {
        try {
            modelo.setRowCount(0);

            // Creamos el VO con la indicación; los demás campos no importan para el listado
            VOListadoVentas voConsulta = new VOListadoVentas(indicacion, 0, null, "", true, 0);
            ArrayList<VOListadoVentas> lista = fachada.ListadoVentas(voConsulta);

            for (VOListadoVentas vo : lista) {
                String tipo = vo.VOgetTipoVenta() ? "En proceso" : "Finalizada";
                modelo.addRow(new Object[]{
                    vo.VOgetNumero(),
                    vo.VOgetFecha() != null ? vo.VOgetFecha().toString() : "",
                    vo.VOgetDireccionEntrega(),
                    tipo,
                    String.format("%.2f", vo.VOgetMontoFinal())
                });
            }

            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No se encontraron ventas.",
                    "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (NoEsTPFException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMensaje(),
                "Indicación inválida", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

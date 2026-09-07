package capaGraficaControladores;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import capaLogica.IFachada;
import valueObjects.VOPostre;
import valueObjects.VOPostreLight;
import excepciones.DiccioVacioException;
import excepciones.PostreNoExisteException;

public class CtrlListadoDetallado {

    private IFachada fachada;

    public CtrlListadoDetallado(IFachada fachada) {
        this.fachada = fachada;
    }

    public void buscar(JFrame frame, String strCodigo, JTextArea txtResultado) {
        try {
            if (strCodigo.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Ingrese el código del postre.",
                    "Datos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String codigo = strCodigo;
            // Creamos un VO con el código para consultar (nombre/precio/tipo son ignorados en la búsqueda)
            VOPostre voConsulta = new VOPostre(codigo, "", 0, true);
            VOPostre resultado = fachada.ListadoDetalladoPostre(voConsulta);

            StringBuilder sb = new StringBuilder();
            sb.append("Código:  ").append(resultado.VOgetCodigo()).append("\n");
            sb.append("Nombre:  ").append(resultado.VOgetNombre()).append("\n");
            sb.append("Precio:  ").append(String.format("%.2f", resultado.VOgetPrecio())).append("\n");
            sb.append("Tipo:    ").append(resultado.VOgetTipo() ? "Común" : "Light").append("\n");

            if (resultado instanceof VOPostreLight) {
                VOPostreLight vl = (VOPostreLight) resultado;
                sb.append("Endulzante: ").append(vl.VOgetTipoEndulzanteVO()).append("\n");
                sb.append("Descripción: ").append(vl.VOgetDescripcionVO()).append("\n");
            }

            txtResultado.setText(sb.toString());

        }/* catch (NumberFormatException ex) {
           JOptionPane.showMessageDialog(frame, "El código debe ser numérico.",
                "Error de formato", JOptionPane.ERROR_MESSAGE);

        } */catch (PostreNoExisteException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMensaje(),
                "No encontrado", JOptionPane.WARNING_MESSAGE);
        } catch (DiccioVacioException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMensaje(),
                "Diccionario vacío", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

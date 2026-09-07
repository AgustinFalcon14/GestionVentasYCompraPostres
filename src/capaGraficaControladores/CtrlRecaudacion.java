package capaGraficaControladores;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.time.LocalDate;
import capaLogica.IFachada;
import valueObjects.VOMontoTotal;
import excepciones.PostreNoExisteException;

public class CtrlRecaudacion {

    private IFachada fachada;

    public CtrlRecaudacion(IFachada fachada) {
        this.fachada = fachada;
    }

    public void consultar(JFrame frame, String strCodigo, String strFecha, JTextArea txtResultado) {
        try {
            if (strCodigo.isEmpty() || strFecha.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Complete todos los campos.",
                    "Datos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String codigo = strCodigo;
            LocalDate fecha = LocalDate.parse(strFecha.trim());

            VOMontoTotal voConsulta = new VOMontoTotal(codigo, 0, 0, fecha);
            VOMontoTotal resultado = fachada.RecaudacionPostreFecha(voConsulta);

            StringBuilder sb = new StringBuilder();
            sb.append("Código postre: ").append(resultado.VOgetCodigo()).append("\n");
            sb.append("Fecha:         ").append(resultado.VOgetFecha()).append("\n");
            sb.append("Cant. postres: ").append(resultado.VOgetCantPostres()).append("\n");
            sb.append("Monto total:   ").append(String.format("%.2f", resultado.VOgetMontoTotal())).append("\n");

            txtResultado.setText(sb.toString());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "El código debe ser numérico.",
                "Error de formato", JOptionPane.ERROR_MESSAGE);
        } catch (java.time.format.DateTimeParseException ex) {
            JOptionPane.showMessageDialog(frame, "Formato de fecha inválido. Use AAAA-MM-DD.",
                "Error de formato", JOptionPane.ERROR_MESSAGE);
        } catch (PostreNoExisteException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMensaje(), "Postre no existe", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

package capaGraficaControladores;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import capaLogica.IFachada;
import valueObjects.VOSistema;

public class CtrlRespaldar {

    private IFachada fachada;

    public CtrlRespaldar(IFachada fachada) {
        this.fachada = fachada;
    }

    public void respaldar(JFrame frame) {
        try {
            // CORRECCION: pasar null, la Fachada.Respaldar() usa su propio estado interno
            fachada.Respaldar(null);
            JOptionPane.showMessageDialog(frame, "Sistema respaldado exitosamente.",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error al respaldar: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

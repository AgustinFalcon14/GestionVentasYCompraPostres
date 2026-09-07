package capaGraficaControladores;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import capaLogica.IFachada;
import capaLogica.Fachada;
import capaLogica.Postre;
import capaLogica.Postres;
import capaLogica.Venta;
import capaLogica.Ventas;
import valueObjects.VOSistema;

public class CtrlRecuperar {

    private IFachada fachada;

    public CtrlRecuperar(IFachada fachada) {
        this.fachada = fachada;
    }

    public void recuperar(JFrame frame, JTextArea txtResultado) {
        try {
            VOSistema resultado = fachada.Recuperar();

            if (resultado == null) {
                JOptionPane.showMessageDialog(frame, "No hay respaldo disponible.",
                    "Sin respaldo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Mostrar datos en pantalla
            StringBuilder sb = new StringBuilder();
            sb.append("=== Estado recuperado ===\n\n");

            Postre[] postres = resultado.VOgetPostres();
            sb.append("Postres (").append(postres != null ? postres.length : 0).append("):\n");
            if (postres != null) {
                for (Postre p : postres) {
                    if (p != null)
                        sb.append("  - [").append(p.getCodigo()).append("] ").append(p.getNombre()).append("\n");
                }
            }

            Venta[] ventas = resultado.VOgetVenta();
            sb.append("\nVentas (").append(ventas != null ? ventas.length : 0).append("):\n");
            if (ventas != null) {
                for (Venta v : ventas) {
                    if (v != null)
                        sb.append("  - Venta #").append(v.getNumero()).append(" | ").append(v.getFecha()).append("\n");
                }
            }

            txtResultado.setText(sb.toString());

            // CORRECCION: cargar los datos recuperados en memoria de la Fachada
            // Solo aplica en modo local (sin RMI). Con RMI el servidor maneja esto solo.
            if (fachada instanceof Fachada) {
                Fachada fachadaLocal = (Fachada) fachada;

                if (resultado.VOgetPostres() != null) {
                    Postres nuevosPostres = new Postres();
                    for (Postre p : resultado.VOgetPostres()) {
                        if (p != null) nuevosPostres.agregar(p.getCodigo(), p);
                    }
                    fachadaLocal.setPostres(nuevosPostres);
                }

                if (resultado.VOgetVenta() != null) {
                    Ventas nuevasVentas = new Ventas();
                    for (Venta v : resultado.VOgetVenta()) {
                        if (v != null) nuevasVentas.Insertar(v);
                    }
                    fachadaLocal.setVentas(nuevasVentas);
                }
            }

            JOptionPane.showMessageDialog(frame, "Sistema recuperado y cargado exitosamente.",
                "Exito", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error al recuperar: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

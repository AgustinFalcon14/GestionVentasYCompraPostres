package capaGrafica;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import capaGraficaControladores.CtrlListadoVentas;
import capaLogica.IFachada;

public class VentanaListadoVentas {

    private JFrame frame;
    private JComboBox<String> cmbIndicacion;
    private JTable tabla;
    private DefaultTableModel modelo;
    private JButton btnCargar, btnCerrar;
    private IFachada fachada;

    public VentanaListadoVentas(IFachada fachada) {
        this.fachada = fachada;
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Listado de Ventas");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.setBounds(150, 100, 560, 370);

        Container panel = frame.getContentPane();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 255, 240));

        JLabel lblInd = new JLabel("Indicación:");
        lblInd.setBounds(10, 12, 90, 20);
        panel.add(lblInd);

        cmbIndicacion = new JComboBox<>(new String[]{"T - Todas", "P - En Proceso", "F - Finalizadas"});
        cmbIndicacion.setBounds(105, 10, 200, 24);
        panel.add(cmbIndicacion);

        btnCargar = new JButton("CARGAR");
        btnCargar.setBounds(315, 10, 90, 24);
        btnCargar.setBackground(new Color(60, 179, 113));
        btnCargar.setForeground(Color.WHITE);
        btnCargar.setFont(new Font("Arial", Font.BOLD, 11));
        panel.add(btnCargar);

        String[] cols = {"Nro.", "Fecha", "Dirección", "Tipo", "Monto"};
        modelo = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tabla = new JTable(modelo);
        tabla.setRowHeight(22);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(10, 42, 530, 270);
        panel.add(scroll);

        btnCerrar = new JButton("CERRAR");
        btnCerrar.setBounds(420, 10, 90, 24);
        btnCerrar.setBackground(new Color(220, 80, 80));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 11));
        panel.add(btnCerrar);

        btnCargar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sel = (String) cmbIndicacion.getSelectedItem();
                char indicacion = sel.charAt(0); // 'T', 'P' o 'F'
                CtrlListadoVentas ctrl = new CtrlListadoVentas(fachada);
                ctrl.cargar(frame, modelo, indicacion);
            }
        });

        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { frame.dispose(); }
        });
    }

    public void setVisible(boolean b) { frame.setVisible(b); }
}

package capaGrafica;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import capaGraficaControladores.CtrlListadoPostresVenta;
import capaLogica.IFachada;

public class VentanaListadoPostresVenta {

    private JFrame frame;
    private JTextField txtNumeroVenta;
    private JTable tabla;
    private DefaultTableModel modelo;
    private JButton btnCargar, btnCerrar;
    private IFachada fachada;

    public VentanaListadoPostresVenta(IFachada fachada) {
        this.fachada = fachada;
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Postres de una Venta");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.setBounds(150, 100, 520, 350);

        Container panel = frame.getContentPane();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 255, 240));

        JLabel lbl = new JLabel("Nro. de Venta:");
        lbl.setBounds(10, 12, 110, 20);
        panel.add(lbl);

        txtNumeroVenta = new JTextField();
        txtNumeroVenta.setBounds(125, 10, 100, 22);
        panel.add(txtNumeroVenta);

        btnCargar = new JButton("CARGAR");
        btnCargar.setBounds(235, 10, 90, 24);
        btnCargar.setBackground(new Color(60, 179, 113));
        btnCargar.setForeground(Color.WHITE);
        btnCargar.setFont(new Font("Arial", Font.BOLD, 11));
        panel.add(btnCargar);

        btnCerrar = new JButton("CERRAR");
        btnCerrar.setBounds(335, 10, 90, 24);
        btnCerrar.setBackground(new Color(220, 80, 80));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 11));
        panel.add(btnCerrar);

        String[] cols = {"Código", "Nombre", "Precio", "Tipo", "Cantidad"};
        modelo = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tabla = new JTable(modelo);
        tabla.setRowHeight(22);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(10, 42, 490, 260);
        panel.add(scroll);

        btnCargar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CtrlListadoPostresVenta ctrl = new CtrlListadoPostresVenta(fachada);
                ctrl.cargar(frame, txtNumeroVenta.getText(), modelo);
            }
        });

        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { frame.dispose(); }
        });
    }

    public void setVisible(boolean b) { frame.setVisible(b); }
}

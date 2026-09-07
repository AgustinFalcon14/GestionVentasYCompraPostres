package capaGrafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import capaGraficaControladores.CtrlNuevaVenta;
import capaLogica.IFachada;

public class VentanaNuevaVenta {

    private JFrame frame;
    private JLabel lblFecha, lblDireccion;
    private JTextField txtFecha, txtDireccion;
    private JButton btnRegistrar, btnCancelar;
    private IFachada fachada;

    public VentanaNuevaVenta(IFachada fachada) {
        this.fachada = fachada;
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Registrar Nueva Venta");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setBounds(150, 100, 370, 160);

        Container panel = frame.getContentPane();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 255, 240));

        JLabel lblInfo = new JLabel("Fecha (AAAA-MM-DD):");
        lblInfo.setBounds(15, 15, 145, 20);
        panel.add(lblInfo);

        txtFecha = new JTextField();
        txtFecha.setBounds(165, 12, 165, 22);
        panel.add(txtFecha);

        lblDireccion = new JLabel("Dirección entrega:");
        lblDireccion.setBounds(15, 45, 145, 20);
        panel.add(lblDireccion);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(165, 42, 165, 22);
        panel.add(txtDireccion);

        btnRegistrar = new JButton("REGISTRAR");
        btnRegistrar.setBounds(55, 80, 110, 28);
        btnRegistrar.setBackground(new Color(60, 179, 113));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 11));
        panel.add(btnRegistrar);

        btnCancelar = new JButton("CANCELAR");
        btnCancelar.setBounds(185, 80, 110, 28);
        btnCancelar.setBackground(new Color(220, 80, 80));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 11));
        panel.add(btnCancelar);

        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CtrlNuevaVenta ctrl = new CtrlNuevaVenta(fachada);
                ctrl.registrar(frame, txtFecha.getText(), txtDireccion.getText());
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}
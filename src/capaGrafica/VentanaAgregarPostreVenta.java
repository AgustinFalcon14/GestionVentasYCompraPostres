package capaGrafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import capaGraficaControladores.CtrlAgregarPostreVenta;
import capaLogica.IFachada;

public class VentanaAgregarPostreVenta {

    private JFrame frame;
    private JTextField txtNumeroVenta, txtCodigoPostre, txtCantidad;
    private JButton btnAgregar, btnCancelar;
    private IFachada fachada;

    public VentanaAgregarPostreVenta(IFachada fachada) {
        this.fachada = fachada;
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Agregar Postre a Venta");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setBounds(150, 100, 350, 185);

        Container panel = frame.getContentPane();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 255, 240));

        JLabel lbl1 = new JLabel("Nro. de Venta:");
        lbl1.setBounds(15, 15, 130, 20);
        panel.add(lbl1);
        txtNumeroVenta = new JTextField();
        txtNumeroVenta.setBounds(150, 12, 160, 22);
        panel.add(txtNumeroVenta);

        JLabel lbl2 = new JLabel("Código Postre:");
        lbl2.setBounds(15, 45, 130, 20);
        panel.add(lbl2);
        txtCodigoPostre = new JTextField();
        txtCodigoPostre.setBounds(150, 42, 160, 22);
        panel.add(txtCodigoPostre);

        JLabel lbl3 = new JLabel("Cantidad:");
        lbl3.setBounds(15, 75, 130, 20);
        panel.add(lbl3);
        txtCantidad = new JTextField();
        txtCantidad.setBounds(150, 72, 160, 22);
        panel.add(txtCantidad);

        btnAgregar = new JButton("AGREGAR");
        btnAgregar.setBounds(45, 112, 100, 28);
        btnAgregar.setBackground(new Color(60, 179, 113));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 11));
        panel.add(btnAgregar);

        btnCancelar = new JButton("CANCELAR");
        btnCancelar.setBounds(165, 112, 100, 28);
        btnCancelar.setBackground(new Color(220, 80, 80));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 11));
        panel.add(btnCancelar);

        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CtrlAgregarPostreVenta ctrl = new CtrlAgregarPostreVenta(fachada);
                ctrl.agregar(frame, txtCodigoPostre.getText(),
                    txtCantidad.getText(), txtNumeroVenta.getText());
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { frame.dispose(); }
        });
    }

    public void setVisible(boolean b) { frame.setVisible(b); }
}

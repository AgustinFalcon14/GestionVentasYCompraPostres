package capaGrafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import capaGraficaControladores.CtrlEliminarPostreVenta;
import capaLogica.IFachada;

public class VentanaEliminarPostreVenta {

    private JFrame frame;
    private JTextField txtNumeroVenta, txtCodigoPostre, txtCantidad;
    private JButton btnEliminar, btnCancelar;
    private IFachada fachada;

    public VentanaEliminarPostreVenta(IFachada fachada) {
        this.fachada = fachada;
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Eliminar Postre de Venta");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setBounds(150, 100, 350, 185);

        Container panel = frame.getContentPane();
        panel.setLayout(null);
        panel.setBackground(new Color(255, 240, 240));

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

        JLabel lbl3 = new JLabel("Cantidad a quitar:");
        lbl3.setBounds(15, 75, 130, 20);
        panel.add(lbl3);
        txtCantidad = new JTextField();
        txtCantidad.setBounds(150, 72, 160, 22);
        panel.add(txtCantidad);

        btnEliminar = new JButton("ELIMINAR");
        btnEliminar.setBounds(45, 112, 100, 28);
        btnEliminar.setBackground(new Color(220, 80, 80));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 11));
        panel.add(btnEliminar);

        btnCancelar = new JButton("CANCELAR");
        btnCancelar.setBounds(165, 112, 100, 28);
        btnCancelar.setBackground(new Color(150, 150, 150));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 11));
        panel.add(btnCancelar);

        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CtrlEliminarPostreVenta ctrl = new CtrlEliminarPostreVenta(fachada);
                ctrl.eliminar(frame, txtCodigoPostre.getText(),
                    txtCantidad.getText(), txtNumeroVenta.getText());
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { frame.dispose(); }
        });
    }

    public void setVisible(boolean b) { frame.setVisible(b); }
}

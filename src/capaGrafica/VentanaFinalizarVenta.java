package capaGrafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import capaGraficaControladores.CtrlFinalizarVenta;
import capaLogica.IFachada;

public class VentanaFinalizarVenta {

    private JFrame frame;
    private JTextField txtNumeroVenta;
    private JCheckBox chkConfirmacion;
    private JButton btnFinalizar, btnCancelar;
    private IFachada fachada;

    public VentanaFinalizarVenta(IFachada fachada) {
        this.fachada = fachada;
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Finalizar Venta");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setBounds(150, 100, 350, 155);

        Container panel = frame.getContentPane();
        panel.setLayout(null);
        panel.setBackground(new Color(255, 250, 230));

        JLabel lbl1 = new JLabel("Nro. de Venta:");
        lbl1.setBounds(15, 15, 130, 20);
        panel.add(lbl1);
        txtNumeroVenta = new JTextField();
        txtNumeroVenta.setBounds(150, 12, 160, 22);
        panel.add(txtNumeroVenta);

        JLabel lbl3 = new JLabel("¿Confirmar?");
        lbl3.setBounds(15, 45, 130, 20);
        panel.add(lbl3);
        chkConfirmacion = new JCheckBox();
        chkConfirmacion.setBounds(150, 43, 30, 22);
        chkConfirmacion.setBackground(new Color(255, 250, 230));
        chkConfirmacion.setSelected(true);
        panel.add(chkConfirmacion);

        btnFinalizar = new JButton("FINALIZAR");
        btnFinalizar.setBounds(45, 80, 100, 28);
        btnFinalizar.setBackground(new Color(210, 150, 50));
        btnFinalizar.setForeground(Color.WHITE);
        btnFinalizar.setFont(new Font("Arial", Font.BOLD, 11));
        panel.add(btnFinalizar);

        btnCancelar = new JButton("CANCELAR");
        btnCancelar.setBounds(165, 80, 100, 28);
        btnCancelar.setBackground(new Color(150, 150, 150));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 11));
        panel.add(btnCancelar);

        btnFinalizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CtrlFinalizarVenta ctrl = new CtrlFinalizarVenta(fachada);
                ctrl.finalizar(frame, txtNumeroVenta.getText(), chkConfirmacion.isSelected());
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { frame.dispose(); }
        });
    }

    public void setVisible(boolean b) { frame.setVisible(b); }
}
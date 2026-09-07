package capaGrafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import capaGraficaControladores.CtrlRespaldar;
import capaLogica.IFachada;

public class VentanaRespaldar {

    private JFrame frame;
    private JButton btnRespaldar, btnCerrar;
    private IFachada fachada;

    public VentanaRespaldar(IFachada fachada) {
        this.fachada = fachada;
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Respaldar Sistema");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setBounds(200, 150, 300, 130);

        Container panel = frame.getContentPane();
        panel.setLayout(null);
        panel.setBackground(new Color(255, 245, 220));

        JLabel lbl = new JLabel("¿Desea respaldar el sistema?");
        lbl.setBounds(30, 18, 220, 20);
        lbl.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(lbl);

        btnRespaldar = new JButton("RESPALDAR");
        btnRespaldar.setBounds(30, 55, 110, 28);
        btnRespaldar.setBackground(new Color(210, 150, 50));
        btnRespaldar.setForeground(Color.WHITE);
        btnRespaldar.setFont(new Font("Arial", Font.BOLD, 11));
        panel.add(btnRespaldar);

        btnCerrar = new JButton("CANCELAR");
        btnCerrar.setBounds(155, 55, 100, 28);
        btnCerrar.setBackground(new Color(150, 150, 150));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 11));
        panel.add(btnCerrar);

        btnRespaldar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CtrlRespaldar ctrl = new CtrlRespaldar(fachada);
                ctrl.respaldar(frame);
            }
        });

        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { frame.dispose(); }
        });
    }

    public void setVisible(boolean b) { frame.setVisible(b); }
}

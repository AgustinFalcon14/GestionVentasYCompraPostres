package capaGrafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import capaGraficaControladores.CtrlRecuperar;
import capaLogica.IFachada;

public class VentanaRecuperar {

    private JFrame frame;
    private JTextArea txtResultado;
    private JButton btnRecuperar, btnCerrar;
    private IFachada fachada;

    public VentanaRecuperar(IFachada fachada) {
        this.fachada = fachada;
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Recuperar Sistema");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setBounds(200, 150, 380, 250);

        Container panel = frame.getContentPane();
        panel.setLayout(null);
        panel.setBackground(new Color(255, 245, 220));

        JLabel lbl = new JLabel("Recuperar último respaldo del sistema:");
        lbl.setBounds(15, 12, 280, 20);
        lbl.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(lbl);

        btnRecuperar = new JButton("RECUPERAR");
        btnRecuperar.setBounds(40, 40, 120, 28);
        btnRecuperar.setBackground(new Color(210, 150, 50));
        btnRecuperar.setForeground(Color.WHITE);
        btnRecuperar.setFont(new Font("Arial", Font.BOLD, 11));
        panel.add(btnRecuperar);

        btnCerrar = new JButton("CERRAR");
        btnCerrar.setBounds(175, 40, 90, 28);
        btnCerrar.setBackground(new Color(150, 150, 150));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 11));
        panel.add(btnCerrar);

        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        txtResultado.setBackground(new Color(255, 255, 240));
        JScrollPane scroll = new JScrollPane(txtResultado);
        scroll.setBounds(10, 78, 345, 130);
        panel.add(scroll);

        btnRecuperar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CtrlRecuperar ctrl = new CtrlRecuperar(fachada);
                ctrl.recuperar(frame, txtResultado);
            }
        });

        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { frame.dispose(); }
        });
    }

    public void setVisible(boolean b) { frame.setVisible(b); }
}

package capaGrafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import capaGraficaControladores.CtrlRecaudacion;
import capaLogica.IFachada;

public class VentanaRecaudacion {

    private JFrame frame;
    private JTextField txtCodigo, txtFecha;
    private JTextArea txtResultado;
    private JButton btnConsultar, btnCerrar;
    private IFachada fachada;

    public VentanaRecaudacion(IFachada fachada) {
        this.fachada = fachada;
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Recaudación de Postre por Fecha");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setBounds(150, 100, 380, 260);

        Container panel = frame.getContentPane();
        panel.setLayout(null);
        panel.setBackground(new Color(255, 250, 230));

        JLabel lbl1 = new JLabel("Código Postre:");
        lbl1.setBounds(15, 15, 120, 20);
        panel.add(lbl1);
        txtCodigo = new JTextField();
        txtCodigo.setBounds(140, 12, 200, 22);
        panel.add(txtCodigo);

        JLabel lbl2 = new JLabel("Fecha (AAAA-MM-DD):");
        lbl2.setBounds(15, 45, 140, 20);
        panel.add(lbl2);
        txtFecha = new JTextField();
        txtFecha.setBounds(160, 42, 180, 22);
        panel.add(txtFecha);

        btnConsultar = new JButton("CONSULTAR");
        btnConsultar.setBounds(55, 75, 110, 28);
        btnConsultar.setBackground(new Color(210, 150, 50));
        btnConsultar.setForeground(Color.WHITE);
        btnConsultar.setFont(new Font("Arial", Font.BOLD, 11));
        panel.add(btnConsultar);

        btnCerrar = new JButton("CERRAR");
        btnCerrar.setBounds(185, 75, 110, 28);
        btnCerrar.setBackground(new Color(150, 150, 150));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 11));
        panel.add(btnCerrar);

        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        txtResultado.setBackground(new Color(255, 255, 240));
        JScrollPane scroll = new JScrollPane(txtResultado);
        scroll.setBounds(10, 112, 345, 105);
        panel.add(scroll);

        btnConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CtrlRecaudacion ctrl = new CtrlRecaudacion(fachada);
                ctrl.consultar(frame, txtCodigo.getText(), txtFecha.getText(), txtResultado);
            }
        });

        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { frame.dispose(); }
        });
    }

    public void setVisible(boolean b) { frame.setVisible(b); }
}

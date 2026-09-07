package capaGrafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import capaGraficaControladores.CtrlListadoDetallado;
import capaLogica.IFachada;

public class VentanaListadoDetallado {

    private JFrame frame;
    private JLabel lblCodigo;
    private JTextField txtCodigo;
    private JTextArea txtResultado;
    private JButton btnBuscar, btnCerrar;
    private IFachada fachada;

    public VentanaListadoDetallado(IFachada fachada) {
        this.fachada = fachada;
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Detalle de Postre");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setBounds(150, 100, 380, 280);

        Container panel = frame.getContentPane();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 248, 255));

        lblCodigo = new JLabel("Código del Postre:");
        lblCodigo.setBounds(15, 15, 130, 20);
        panel.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(150, 12, 190, 22);
        panel.add(txtCodigo);

        btnBuscar = new JButton("BUSCAR");
        btnBuscar.setBounds(100, 42, 90, 26);
        btnBuscar.setBackground(new Color(100, 149, 237));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 11));
        panel.add(btnBuscar);

        btnCerrar = new JButton("CERRAR");
        btnCerrar.setBounds(205, 42, 90, 26);
        btnCerrar.setBackground(new Color(220, 80, 80));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 11));
        panel.add(btnCerrar);

        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        txtResultado.setBackground(new Color(255, 255, 240));
        JScrollPane scroll = new JScrollPane(txtResultado);
        scroll.setBounds(10, 78, 345, 155);
        panel.add(scroll);

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CtrlListadoDetallado ctrl = new CtrlListadoDetallado(fachada);
                ctrl.buscar(frame, txtCodigo.getText(), txtResultado);
            }
        });

        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}

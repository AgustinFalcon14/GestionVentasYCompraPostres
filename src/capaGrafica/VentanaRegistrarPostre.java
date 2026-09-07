package capaGrafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import capaGraficaControladores.CtrlRegistrarPostre;
import capaLogica.IFachada;

public class VentanaRegistrarPostre {

    private JFrame frame;
    private JLabel lblCodigo, lblNombre, lblPrecio, lblTipo, lblEndulzante, lblDescripcion;
    private JTextField txtCodigo, txtNombre, txtPrecio, txtEndulzante, txtDescripcion;
    private JRadioButton rbComun, rbLight;
    private ButtonGroup bgTipo;
    private JButton btnRegistrar, btnCancelar;
    private JPanel panelLight;
    private IFachada fachada;

    public VentanaRegistrarPostre(IFachada fachada) {
        this.fachada = fachada;
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Registrar Nuevo Postre");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setBounds(150, 100, 380, 330);

        Container panel = frame.getContentPane();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 248, 255));

        lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(15, 15, 100, 20);
        panel.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(120, 12, 220, 22);
        panel.add(txtCodigo);

        lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(15, 45, 100, 20);
        panel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(120, 42, 220, 22);
        panel.add(txtNombre);

        lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(15, 75, 100, 20);
        panel.add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(120, 72, 220, 22);
        panel.add(txtPrecio);

        lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(15, 105, 100, 20);
        panel.add(lblTipo);

        rbComun = new JRadioButton("Común");
        rbComun.setBounds(120, 103, 80, 22);
        rbComun.setBackground(new Color(240, 248, 255));
        rbComun.setSelected(true);
        panel.add(rbComun);

        rbLight = new JRadioButton("Light");
        rbLight.setBounds(210, 103, 80, 22);
        rbLight.setBackground(new Color(240, 248, 255));
        panel.add(rbLight);

        bgTipo = new ButtonGroup();
        bgTipo.add(rbComun);
        bgTipo.add(rbLight);

        // Panel campos Light (oculto por defecto)
        panelLight = new JPanel();
        panelLight.setLayout(null);
        panelLight.setBounds(10, 128, 345, 65);
        panelLight.setBackground(new Color(220, 240, 220));
        panelLight.setBorder(BorderFactory.createTitledBorder("Datos Light"));
        panelLight.setVisible(false);
        panel.add(panelLight);

        lblEndulzante = new JLabel("Endulzante:");
        lblEndulzante.setBounds(10, 18, 90, 20);
        panelLight.add(lblEndulzante);

        txtEndulzante = new JTextField();
        txtEndulzante.setBounds(105, 15, 140, 22);
        panelLight.add(txtEndulzante);

        lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(10, 42, 90, 20);
        panelLight.add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(105, 39, 230, 22);
        panelLight.add(txtDescripcion);

        btnRegistrar = new JButton("REGISTRAR");
        btnRegistrar.setBounds(60, 210, 110, 28);
        btnRegistrar.setBackground(new Color(100, 149, 237));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 11));
        panel.add(btnRegistrar);

        btnCancelar = new JButton("CANCELAR");
        btnCancelar.setBounds(185, 210, 110, 28);
        btnCancelar.setBackground(new Color(220, 80, 80));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 11));
        panel.add(btnCancelar);

        // Mostrar/ocultar campos Light según selección
        rbLight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelLight.setVisible(true);
                frame.repaint();
            }
        });
        rbComun.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelLight.setVisible(false);
                frame.repaint();
            }
        });

        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CtrlRegistrarPostre ctrl = new CtrlRegistrarPostre(fachada);
                ctrl.registrar(
                    frame,
                    txtCodigo.getText(),
                    txtNombre.getText(),
                    txtPrecio.getText(),
                    rbComun.isSelected(),
                    txtEndulzante.getText(),
                    txtDescripcion.getText()
                );
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

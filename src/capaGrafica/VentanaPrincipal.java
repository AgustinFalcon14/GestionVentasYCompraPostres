package capaGrafica;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import capaLogica.IFachada;

public class VentanaPrincipal {

    private JFrame frame;
    private IFachada fachada;

    // Botones de postres
    private JButton btnRegistrarPostre;
    private JButton btnListadoGeneral;
    private JButton btnListadoDetallado;

    // Botones de ventas
    private JButton btnNuevaVenta;
    private JButton btnAgregarPostre;
    private JButton btnEliminarPostre;
    private JButton btnFinalizarVenta;
    private JButton btnListadoVentas;
    private JButton btnListadoPostresVenta;

    // Requerimiento 10
    private JButton btnRecaudacion;

    // Sistema
    private JButton btnRespaldar;
    private JButton btnRecuperar;

    public VentanaPrincipal(IFachada fachada) {
        this.fachada = fachada;
        this.initialize();
        this.setVisible(false);
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Sistema de Gestión de Postres");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setBounds(100, 50, 460, 480);

        Container panel = frame.getContentPane();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 245, 250));

        // Título
        JLabel lblTitulo = new JLabel("Sistema de Gestión de Postres");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setBounds(80, 10, 300, 25);
        panel.add(lblTitulo);

        // ---------- Panel Postres ----------
        JPanel panelPostres = new JPanel();
        panelPostres.setLayout(null);
        panelPostres.setBounds(20, 45, 200, 140);
        panelPostres.setBorder(new TitledBorder("Gestión de Postres"));
        panelPostres.setBackground(new Color(230, 240, 255));
        panel.add(panelPostres);

        btnRegistrarPostre = new JButton("Registrar Postre");
        btnRegistrarPostre.setBounds(15, 20, 165, 28);
        btnRegistrarPostre.setBackground(new Color(100, 149, 237));
        btnRegistrarPostre.setForeground(Color.WHITE);
        btnRegistrarPostre.setFont(new Font("Arial", Font.BOLD, 11));
        panelPostres.add(btnRegistrarPostre);

        btnListadoGeneral = new JButton("Listado General");
        btnListadoGeneral.setBounds(15, 55, 165, 28);
        btnListadoGeneral.setBackground(new Color(100, 149, 237));
        btnListadoGeneral.setForeground(Color.WHITE);
        btnListadoGeneral.setFont(new Font("Arial", Font.BOLD, 11));
        panelPostres.add(btnListadoGeneral);

        btnListadoDetallado = new JButton("Detalle de Postre");
        btnListadoDetallado.setBounds(15, 90, 165, 28);
        btnListadoDetallado.setBackground(new Color(100, 149, 237));
        btnListadoDetallado.setForeground(Color.WHITE);
        btnListadoDetallado.setFont(new Font("Arial", Font.BOLD, 11));
        panelPostres.add(btnListadoDetallado);

        // ---------- Panel Ventas ----------
        JPanel panelVentas = new JPanel();
        panelVentas.setLayout(null);
        panelVentas.setBounds(235, 45, 200, 280);
        panelVentas.setBorder(new TitledBorder("Gestión de Ventas"));
        panelVentas.setBackground(new Color(230, 255, 230));
        panel.add(panelVentas);

        btnNuevaVenta = new JButton("Nueva Venta");
        btnNuevaVenta.setBounds(15, 20, 165, 28);
        btnNuevaVenta.setBackground(new Color(60, 179, 113));
        btnNuevaVenta.setForeground(Color.WHITE);
        btnNuevaVenta.setFont(new Font("Arial", Font.BOLD, 11));
        panelVentas.add(btnNuevaVenta);

        btnAgregarPostre = new JButton("Agregar Postre");
        btnAgregarPostre.setBounds(15, 55, 165, 28);
        btnAgregarPostre.setBackground(new Color(60, 179, 113));
        btnAgregarPostre.setForeground(Color.WHITE);
        btnAgregarPostre.setFont(new Font("Arial", Font.BOLD, 11));
        panelVentas.add(btnAgregarPostre);

        btnEliminarPostre = new JButton("Eliminar Postre");
        btnEliminarPostre.setBounds(15, 90, 165, 28);
        btnEliminarPostre.setBackground(new Color(60, 179, 113));
        btnEliminarPostre.setForeground(Color.WHITE);
        btnEliminarPostre.setFont(new Font("Arial", Font.BOLD, 11));
        panelVentas.add(btnEliminarPostre);

        btnFinalizarVenta = new JButton("Finalizar Venta");
        btnFinalizarVenta.setBounds(15, 125, 165, 28);
        btnFinalizarVenta.setBackground(new Color(60, 179, 113));
        btnFinalizarVenta.setForeground(Color.WHITE);
        btnFinalizarVenta.setFont(new Font("Arial", Font.BOLD, 11));
        panelVentas.add(btnFinalizarVenta);

        btnListadoVentas = new JButton("Listado de Ventas");
        btnListadoVentas.setBounds(15, 160, 165, 28);
        btnListadoVentas.setBackground(new Color(60, 179, 113));
        btnListadoVentas.setForeground(Color.WHITE);
        btnListadoVentas.setFont(new Font("Arial", Font.BOLD, 11));
        panelVentas.add(btnListadoVentas);

        btnListadoPostresVenta = new JButton("Postres de Venta");
        btnListadoPostresVenta.setBounds(15, 195, 165, 28);
        btnListadoPostresVenta.setBackground(new Color(60, 179, 113));
        btnListadoPostresVenta.setForeground(Color.WHITE);
        btnListadoPostresVenta.setFont(new Font("Arial", Font.BOLD, 11));
        panelVentas.add(btnListadoPostresVenta);

        btnRecaudacion = new JButton("Recaudación Postre");
        btnRecaudacion.setBounds(15, 230, 165, 28);
        btnRecaudacion.setBackground(new Color(60, 179, 113));
        btnRecaudacion.setForeground(Color.WHITE);
        btnRecaudacion.setFont(new Font("Arial", Font.BOLD, 11));
        panelVentas.add(btnRecaudacion);

        // ---------- Panel postres extra (ocupa hueco) ----------
        JPanel panelPostres2 = new JPanel();
        panelPostres2.setLayout(null);
        panelPostres2.setBounds(20, 195, 200, 130);
        panelPostres2.setBorder(new TitledBorder("Sistema"));
        panelPostres2.setBackground(new Color(255, 245, 220));
        panel.add(panelPostres2);

        btnRespaldar = new JButton("Respaldar");
        btnRespaldar.setBounds(15, 20, 165, 28);
        btnRespaldar.setBackground(new Color(210, 150, 50));
        btnRespaldar.setForeground(Color.WHITE);
        btnRespaldar.setFont(new Font("Arial", Font.BOLD, 11));
        panelPostres2.add(btnRespaldar);

        btnRecuperar = new JButton("Recuperar");
        btnRecuperar.setBounds(15, 55, 165, 28);
        btnRecuperar.setBackground(new Color(210, 150, 50));
        btnRecuperar.setForeground(Color.WHITE);
        btnRecuperar.setFont(new Font("Arial", Font.BOLD, 11));
        panelPostres2.add(btnRecuperar);

        // ---------- Eventos ----------
        btnRegistrarPostre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VentanaRegistrarPostre(fachada).setVisible(true);
            }
        });

        btnListadoGeneral.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VentanaListadoGeneral(fachada).setVisible(true);
            }
        });

        btnListadoDetallado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VentanaListadoDetallado(fachada).setVisible(true);
            }
        });

        btnNuevaVenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VentanaNuevaVenta(fachada).setVisible(true);
            }
        });

        btnAgregarPostre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VentanaAgregarPostreVenta(fachada).setVisible(true);
            }
        });

        btnEliminarPostre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VentanaEliminarPostreVenta(fachada).setVisible(true);
            }
        });

        btnFinalizarVenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VentanaFinalizarVenta(fachada).setVisible(true);
            }
        });

        btnListadoVentas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VentanaListadoVentas(fachada).setVisible(true);
            }
        });

        btnListadoPostresVenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VentanaListadoPostresVenta(fachada).setVisible(true);
            }
        });

        btnRecaudacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VentanaRecaudacion(fachada).setVisible(true);
            }
        });

        btnRespaldar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VentanaRespaldar(fachada).setVisible(true);
            }
        });

        btnRecuperar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VentanaRecuperar(fachada).setVisible(true);
            }
        });
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}

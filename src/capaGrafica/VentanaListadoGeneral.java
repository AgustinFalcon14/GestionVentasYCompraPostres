package capaGrafica;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import capaGraficaControladores.CtrlListadoGeneral;
import capaLogica.IFachada;

public class VentanaListadoGeneral {

    private JFrame frame;
    private JTable tabla;
    private DefaultTableModel modelo;
    private JButton btnCargar, btnCerrar;
    private IFachada fachada;

    public VentanaListadoGeneral(IFachada fachada) {
        this.fachada = fachada;
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Listado General de Postres");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.setBounds(150, 100, 480, 340);

        Container panel = frame.getContentPane();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 248, 255));

        String[] columnas = {"Código", "Nombre", "Precio", "Tipo"};
        modelo = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        tabla = new JTable(modelo);
        tabla.setRowHeight(22);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(10, 10, 450, 240);
        panel.add(scroll);

        btnCargar = new JButton("CARGAR LISTADO");
        btnCargar.setBounds(80, 265, 140, 28);
        btnCargar.setBackground(new Color(100, 149, 237));
        btnCargar.setForeground(Color.WHITE);
        btnCargar.setFont(new Font("Arial", Font.BOLD, 11));
        panel.add(btnCargar);

        btnCerrar = new JButton("CERRAR");
        btnCerrar.setBounds(255, 265, 110, 28);
        btnCerrar.setBackground(new Color(220, 80, 80));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFont(new Font("Arial", Font.BOLD, 11));
        panel.add(btnCerrar);

        btnCargar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CtrlListadoGeneral ctrl = new CtrlListadoGeneral(fachada);
                ctrl.cargar(frame, modelo);
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

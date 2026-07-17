package org.unisiga.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import org.unisiga.controller.InscripcionController;
import org.unisiga.model.Seccion;

public class FrmInscripcion extends JFrame {
    private JTable tblSecciones;
    private DefaultTableModel modeloTabla;
    private JButton btnInscribir;
    private JButton btnVolver;
    private JLabel lblMensaje;
    private InscripcionController controller;
    private JFrame ventanaPadre;

    public FrmInscripcion(InscripcionController controller, JFrame ventanaPadre) {
        this.controller = controller;
        this.ventanaPadre = ventanaPadre;
        setTitle("UNISIGA - Inscripción de Secciones");
        setSize(700, 380);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(8, 8));

        JLabel titulo = new JLabel("INSCRIPCIÓN DE SECCIONES", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 15));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        add(titulo, BorderLayout.NORTH);

        String[] columnas = {"Código", "Asignatura", "Sección", "Docente", "Horario"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tblSecciones = new JTable(modeloTabla);
        tblSecciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblSecciones.getTableHeader().setReorderingAllowed(false);
        add(new JScrollPane(tblSecciones), BorderLayout.CENTER);

        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnInscribir = new JButton("Inscribir Sección");
        btnInscribir.setBackground(new Color(50, 150, 50));
        btnInscribir.setForeground(Color.WHITE);
        btnInscribir.setFocusPainted(false);
        btnVolver = new JButton("Volver");
        lblMensaje = new JLabel("");
        panelSur.add(btnInscribir);
        panelSur.add(btnVolver);
        panelSur.add(lblMensaje);
        add(panelSur, BorderLayout.SOUTH);

        cargarTabla();

        btnInscribir.addActionListener(e -> {
            int fila = tblSecciones.getSelectedRow();
            if (fila < 0) {
                lblMensaje.setForeground(Color.RED);
                lblMensaje.setText("Seleccione una sección de la tabla.");
                return;
            }
            Seccion seccion = controller.getSeccionByRow(fila);
            String resultado = controller.inscribirSeccionEstudiante(seccion);
            lblMensaje.setForeground(resultado.startsWith("Error") ? Color.RED : new Color(0, 130, 0));
            lblMensaje.setText(resultado);
            if (!resultado.startsWith("Error")) cargarTabla();
        });

        btnVolver.addActionListener(e -> {
            ventanaPadre.setVisible(true);
            dispose();
        });
    }

    private void cargarTabla() {
        modeloTabla.setRowCount(0);
        for (Object[] row : controller.getTablaSeccionesDisponibles()) {
            modeloTabla.addRow(row);
        }
    }
}

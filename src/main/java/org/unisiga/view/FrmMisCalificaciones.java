package org.unisiga.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import org.unisiga.controller.CalificacionController;
import org.unisiga.model.Inscripcion;

public class FrmMisCalificaciones extends JFrame {
    private JComboBox<String> cmbInscripcion;
    private JTable tblCalificaciones;
    private DefaultTableModel modeloTabla;
    private JLabel lblPromedio;
    private JButton btnVolver;
    private CalificacionController controller;
    private JFrame ventanaPadre;
    private List<Inscripcion> inscripciones;

    public FrmMisCalificaciones(CalificacionController controller, JFrame ventanaPadre) {
        this.controller = controller;
        this.ventanaPadre = ventanaPadre;
        setTitle("UNISIGA - Mis Calificaciones");
        setSize(520, 380);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(8, 8));

        JPanel panelNorte = new JPanel(new BorderLayout());
        JLabel titulo = new JLabel("MIS CALIFICACIONES", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 15));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        panelNorte.add(titulo, BorderLayout.NORTH);

        JPanel panelCombo = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        panelCombo.add(new JLabel("Asignatura inscrita:"));
        cmbInscripcion = new JComboBox<>();
        cmbInscripcion.setPreferredSize(new Dimension(280, 25));
        panelCombo.add(cmbInscripcion);
        panelNorte.add(panelCombo, BorderLayout.CENTER);
        add(panelNorte, BorderLayout.NORTH);

        String[] cols = {"Evaluación", "Ponderación", "Mi Nota"};
        modeloTabla = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tblCalificaciones = new JTable(modeloTabla);
        tblCalificaciones.getTableHeader().setReorderingAllowed(false);
        add(new JScrollPane(tblCalificaciones), BorderLayout.CENTER);

        JPanel panelSur = new JPanel(new BorderLayout());
        lblPromedio = new JLabel("Promedio Ponderado: --", SwingConstants.CENTER);
        lblPromedio.setFont(new Font("Arial", Font.BOLD, 13));
        lblPromedio.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        panelSur.add(lblPromedio, BorderLayout.CENTER);

        JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnVolver = new JButton("Volver");
        panelBtn.add(btnVolver);
        panelSur.add(panelBtn, BorderLayout.SOUTH);
        add(panelSur, BorderLayout.SOUTH);

        inscripciones = controller.getInscripcionesActivas();
        for (Inscripcion ins : inscripciones) {
            cmbInscripcion.addItem(ins.getSeccion().getAsignatura().getNombre()
                    + " [" + ins.getEstadoInscripcion() + "]");
        }

        cmbInscripcion.addActionListener(e -> cargarCalificaciones());
        cargarCalificaciones();

        btnVolver.addActionListener(e -> { ventanaPadre.setVisible(true); dispose(); });
    }

    private void cargarCalificaciones() {
        modeloTabla.setRowCount(0);
        int idx = cmbInscripcion.getSelectedIndex();
        if (idx < 0 || inscripciones.isEmpty()) {
            lblPromedio.setText("Promedio Ponderado: --");
            return;
        }
        Inscripcion ins = inscripciones.get(idx);
        for (Object[] row : controller.getTablaCalificaciones(ins)) modeloTabla.addRow(row);
        lblPromedio.setText("Promedio Ponderado: " + controller.calcularPromedio(ins));
    }
}

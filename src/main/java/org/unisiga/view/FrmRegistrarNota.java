package org.unisiga.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import org.unisiga.controller.NotaController;
import org.unisiga.model.*;

public class FrmRegistrarNota extends JFrame {
    private JComboBox<String> cmbSeccion;
    private JComboBox<String> cmbEvaluacion;
    private JTable tblEstudiantes;
    private DefaultTableModel modeloTabla;
    private JButton btnGuardarNota;
    private JButton btnVolver;
    private JLabel lblMensaje;
    private NotaController controller;
    private JFrame ventanaPadre;
    private List<Seccion> secciones;
    private List<Evaluacion> evaluaciones;

    public FrmRegistrarNota(NotaController controller, JFrame ventanaPadre) {
        this.controller = controller;
        this.ventanaPadre = ventanaPadre;
        this.evaluaciones = new ArrayList<>();
        setTitle("UNISIGA - Registro de Notas");
        setSize(600, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(8, 8));

        JPanel panelNorte = new JPanel(new BorderLayout());
        JLabel titulo = new JLabel("REGISTRO DE NOTAS", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 15));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        panelNorte.add(titulo, BorderLayout.NORTH);

        JPanel panelCombos = new JPanel(new GridLayout(2, 2, 8, 5));
        panelCombos.setBorder(BorderFactory.createEmptyBorder(5, 15, 8, 15));
        panelCombos.add(new JLabel("Sección:"));
        cmbSeccion = new JComboBox<>();
        panelCombos.add(cmbSeccion);
        panelCombos.add(new JLabel("Evaluación:"));
        cmbEvaluacion = new JComboBox<>();
        panelCombos.add(cmbEvaluacion);
        panelNorte.add(panelCombos, BorderLayout.CENTER);
        add(panelNorte, BorderLayout.NORTH);

        String[] cols = {"Estudiante", "Matrícula", "Nota Actual"};
        modeloTabla = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tblEstudiantes = new JTable(modeloTabla);
        tblEstudiantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblEstudiantes.getTableHeader().setReorderingAllowed(false);
        add(new JScrollPane(tblEstudiantes), BorderLayout.CENTER);

        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnGuardarNota = new JButton("Guardar Nota");
        btnGuardarNota.setBackground(new Color(50, 150, 50));
        btnGuardarNota.setForeground(Color.WHITE);
        btnGuardarNota.setFocusPainted(false);
        btnVolver = new JButton("Volver");
        lblMensaje = new JLabel("");
        panelSur.add(btnGuardarNota);
        panelSur.add(btnVolver);
        panelSur.add(lblMensaje);
        add(panelSur, BorderLayout.SOUTH);

        secciones = controller.getSeccionesDelDocente();
        for (Seccion s : secciones) {
            cmbSeccion.addItem(s.getAsignatura().getNombre() + " - Sección " + s.getIdGrupo());
        }
        actualizarEvaluaciones();
        cargarTablaEstudiantes();

        cmbSeccion.addActionListener(e -> { actualizarEvaluaciones(); cargarTablaEstudiantes(); });
        cmbEvaluacion.addActionListener(e -> cargarTablaEstudiantes());

        btnGuardarNota.addActionListener(e -> {
            int filaEst = tblEstudiantes.getSelectedRow();
            if (filaEst < 0) {
                lblMensaje.setForeground(Color.RED);
                lblMensaje.setText("Seleccione un estudiante.");
                return;
            }
            String notaStr = JOptionPane.showInputDialog(this, "Ingrese la nota (1.0 - 7.0):", "Registrar Nota", JOptionPane.QUESTION_MESSAGE);
            if (notaStr == null) return;
            try {
                float nota = Float.parseFloat(notaStr.replace(',', '.'));
                int idxS = cmbSeccion.getSelectedIndex();
                int idxE = cmbEvaluacion.getSelectedIndex();
                if (idxS < 0 || idxE < 0) return;
                String res = controller.guardarNota(secciones.get(idxS), evaluaciones.get(idxE), filaEst, nota);
                lblMensaje.setForeground(res.startsWith("Error") ? Color.RED : new Color(0, 130, 0));
                lblMensaje.setText(res);
                if (!res.startsWith("Error")) cargarTablaEstudiantes();
            } catch (NumberFormatException ex) {
                lblMensaje.setForeground(Color.RED);
                lblMensaje.setText("Formato de nota inválido.");
            }
        });

        btnVolver.addActionListener(e -> { ventanaPadre.setVisible(true); dispose(); });
    }

    private void actualizarEvaluaciones() {
        cmbEvaluacion.removeAllItems();
        evaluaciones.clear();
        int idx = cmbSeccion.getSelectedIndex();
        if (idx < 0 || secciones.isEmpty()) return;
        evaluaciones = controller.getEvaluacionesDe(secciones.get(idx));
        for (Evaluacion ev : evaluaciones) cmbEvaluacion.addItem(ev.getTitulo());
    }

    private void cargarTablaEstudiantes() {
        modeloTabla.setRowCount(0);
        int idxS = cmbSeccion.getSelectedIndex();
        int idxE = cmbEvaluacion.getSelectedIndex();
        if (idxS < 0 || idxE < 0 || secciones.isEmpty() || evaluaciones.isEmpty()) return;
        for (Object[] row : controller.getTablaEstudiantes(secciones.get(idxS), evaluaciones.get(idxE))) {
            modeloTabla.addRow(row);
        }
    }
}

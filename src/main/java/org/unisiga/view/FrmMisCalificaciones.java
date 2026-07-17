package org.unisiga.view;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.unisiga.controller.CalificacionController;
import org.unisiga.model.Inscripcion;

public class FrmMisCalificaciones extends javax.swing.JFrame {

    private CalificacionController controller;
    private javax.swing.JFrame ventanaPadre;
    private DefaultTableModel modeloTabla;
    private List<Inscripcion> inscripciones;

    public FrmMisCalificaciones(CalificacionController controller, javax.swing.JFrame ventanaPadre) {
        this.controller = controller;
        this.ventanaPadre = ventanaPadre;
        initComponents();
        setLocationRelativeTo(null);
        modeloTabla = (DefaultTableModel) tblCalificaciones.getModel();
        inscripciones = controller.getInscripcionesActivas();
        for (Inscripcion ins : inscripciones) {
            cmbInscripcion.addItem(ins.getSeccion().getAsignatura().getNombre()
                    + " [" + ins.getEstadoInscripcion() + "]");
        }
        cargarCalificaciones();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        lblTitulo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbInscripcion = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCalificaciones = new javax.swing.JTable();
        lblPromedio = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UNISIGA - Mis Calificaciones");
        getContentPane().setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("MIS CALIFICACIONES");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(0, 10, 520, 30);

        jLabel2.setText("Asignatura inscrita:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(15, 50, 120, 25);

        cmbInscripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbInscripcionActionPerformed(evt);
            }
        });
        getContentPane().add(cmbInscripcion);
        cmbInscripcion.setBounds(145, 50, 360, 25);

        tblCalificaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"Evaluación", "Ponderación", "Mi Nota"}
        ) {
            public boolean isCellEditable(int row, int col) { return false; }
        });
        tblCalificaciones.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblCalificaciones);
        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 85, 500, 230);

        lblPromedio.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 13));
        lblPromedio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPromedio.setText("Promedio Ponderado: --");
        getContentPane().add(lblPromedio);
        lblPromedio.setBounds(10, 325, 500, 25);

        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });
        getContentPane().add(btnVolver);
        btnVolver.setBounds(210, 360, 100, 30);

        setSize(520, 410);
    }// </editor-fold>

    private void cmbInscripcionActionPerformed(java.awt.event.ActionEvent evt) {
        cargarCalificaciones();
    }

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {
        ventanaPadre.setVisible(true);
        dispose();
    }

    private void cargarCalificaciones() {
        modeloTabla.setRowCount(0);
        int idx = cmbInscripcion.getSelectedIndex();
        if (idx < 0 || inscripciones == null || inscripciones.isEmpty()) {
            lblPromedio.setText("Promedio Ponderado: --");
            return;
        }
        Inscripcion ins = inscripciones.get(idx);
        for (Object[] row : controller.getTablaCalificaciones(ins)) modeloTabla.addRow(row);
        lblPromedio.setText("Promedio Ponderado: " + controller.calcularPromedio(ins));
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> cmbInscripcion;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPromedio;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblCalificaciones;
    // End of variables declaration
}

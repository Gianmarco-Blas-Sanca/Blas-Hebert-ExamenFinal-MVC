package org.unisiga.view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.unisiga.controller.NotaController;
import org.unisiga.model.*;

public class FrmRegistrarNota extends javax.swing.JFrame {

    private NotaController controller;
    private javax.swing.JFrame ventanaPadre;
    private DefaultTableModel modeloTabla;
    private List<Seccion> secciones;
    private List<Evaluacion> evaluaciones;

    public FrmRegistrarNota(NotaController controller, javax.swing.JFrame ventanaPadre) {
        this.controller = controller;
        this.ventanaPadre = ventanaPadre;
        this.evaluaciones = new ArrayList<>();
        initComponents();
        setLocationRelativeTo(null);
        modeloTabla = (DefaultTableModel) tblEstudiantes.getModel();
        secciones = controller.getSeccionesDelDocente();
        for (Seccion s : secciones) {
            cmbSeccion.addItem(s.getAsignatura().getNombre() + " - Sección " + s.getIdGrupo());
        }
        actualizarEvaluaciones();
        cargarTablaEstudiantes();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbSeccion = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cmbEvaluacion = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEstudiantes = new javax.swing.JTable();
        btnGuardarNota = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        lblMensaje = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UNISIGA - Registro de Notas");
        setMinimumSize(new java.awt.Dimension(610, 470));
        getContentPane().setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("REGISTRO DE NOTAS");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(0, 10, 600, 30);

        jLabel2.setText("Sección:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(15, 50, 80, 25);

        cmbSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSeccionActionPerformed(evt);
            }
        });
        getContentPane().add(cmbSeccion);
        cmbSeccion.setBounds(100, 50, 200, 25);

        jLabel3.setText("Evaluación:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(15, 85, 80, 25);

        cmbEvaluacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEvaluacionActionPerformed(evt);
            }
        });
        getContentPane().add(cmbEvaluacion);
        cmbEvaluacion.setBounds(100, 85, 200, 25);

        tblEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Estudiante", "Matrícula", "Nota Actual"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEstudiantes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tblEstudiantes);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 120, 580, 240);

        btnGuardarNota.setBackground(new java.awt.Color(50, 150, 50));
        btnGuardarNota.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarNota.setText("Guardar Nota");
        btnGuardarNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarNotaActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardarNota);
        btnGuardarNota.setBounds(130, 370, 140, 30);

        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });
        getContentPane().add(btnVolver);
        btnVolver.setBounds(290, 370, 100, 30);
        getContentPane().add(lblMensaje);
        lblMensaje.setBounds(400, 370, 185, 30);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmbSeccionActionPerformed(java.awt.event.ActionEvent evt) {
        actualizarEvaluaciones();
        cargarTablaEstudiantes();
    }

    private void cmbEvaluacionActionPerformed(java.awt.event.ActionEvent evt) {
        cargarTablaEstudiantes();
    }

    private void btnGuardarNotaActionPerformed(java.awt.event.ActionEvent evt) {
        int filaEst = tblEstudiantes.getSelectedRow();
        if (filaEst < 0) {
            lblMensaje.setForeground(java.awt.Color.RED);
            lblMensaje.setText("Seleccione un estudiante.");
            return;
        }
        String notaStr = javax.swing.JOptionPane.showInputDialog(this,
                "Ingrese la nota (1.0 - 7.0):", "Registrar Nota",
                javax.swing.JOptionPane.QUESTION_MESSAGE);
        if (notaStr == null) return;
        try {
            float nota = Float.parseFloat(notaStr.replace(',', '.'));
            int idxS = cmbSeccion.getSelectedIndex();
            int idxE = cmbEvaluacion.getSelectedIndex();
            if (idxS < 0 || idxE < 0) return;
            String res = controller.guardarNota(secciones.get(idxS), evaluaciones.get(idxE), filaEst, nota);
            lblMensaje.setForeground(res.startsWith("Error") ? java.awt.Color.RED : new java.awt.Color(0, 130, 0));
            lblMensaje.setText(res);
            if (!res.startsWith("Error")) cargarTablaEstudiantes();
        } catch (NumberFormatException ex) {
            lblMensaje.setForeground(java.awt.Color.RED);
            lblMensaje.setText("Formato de nota inválido.");
        }
    }

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {
        ventanaPadre.setVisible(true);
        dispose();
    }

    private void actualizarEvaluaciones() {
        cmbEvaluacion.removeAllItems();
        evaluaciones.clear();
        int idx = cmbSeccion.getSelectedIndex();
        if (idx < 0 || secciones == null || secciones.isEmpty()) return;
        evaluaciones = controller.getEvaluacionesDe(secciones.get(idx));
        for (Evaluacion ev : evaluaciones) cmbEvaluacion.addItem(ev.getTitulo());
    }

    private void cargarTablaEstudiantes() {
        modeloTabla.setRowCount(0);
        int idxS = cmbSeccion.getSelectedIndex();
        int idxE = cmbEvaluacion.getSelectedIndex();
        if (idxS < 0 || idxE < 0 || secciones == null || secciones.isEmpty() || evaluaciones.isEmpty()) return;
        for (Object[] row : controller.getTablaEstudiantes(secciones.get(idxS), evaluaciones.get(idxE))) {
            modeloTabla.addRow(row);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardarNota;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox cmbEvaluacion;
    private javax.swing.JComboBox cmbSeccion;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblEstudiantes;
    // End of variables declaration//GEN-END:variables
}

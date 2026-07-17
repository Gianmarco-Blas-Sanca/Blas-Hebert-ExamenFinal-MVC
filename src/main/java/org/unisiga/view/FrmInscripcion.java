package org.unisiga.view;

import javax.swing.table.DefaultTableModel;
import org.unisiga.controller.InscripcionController;
import org.unisiga.model.Seccion;

public class FrmInscripcion extends javax.swing.JFrame {

    private InscripcionController controller;
    private javax.swing.JFrame ventanaPadre;
    private DefaultTableModel modeloTabla;

    public FrmInscripcion(InscripcionController controller, javax.swing.JFrame ventanaPadre) {
        this.controller = controller;
        this.ventanaPadre = ventanaPadre;
        initComponents();
        setLocationRelativeTo(null);
        modeloTabla = (DefaultTableModel) tblSecciones.getModel();
        cargarTabla();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        lblTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSecciones = new javax.swing.JTable();
        btnInscribir = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        lblMensaje = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UNISIGA - Inscripción de Secciones");
        getContentPane().setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 15));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("INSCRIPCIÓN DE SECCIONES");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(0, 10, 700, 30);

        tblSecciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"Código", "Asignatura", "Sección", "Docente", "Horario"}
        ) {
            public boolean isCellEditable(int row, int col) { return false; }
        });
        tblSecciones.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblSecciones.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblSecciones);
        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 50, 680, 270);

        btnInscribir.setBackground(new java.awt.Color(50, 150, 50));
        btnInscribir.setForeground(new java.awt.Color(255, 255, 255));
        btnInscribir.setText("Inscribir Sección");
        btnInscribir.setFocusPainted(false);
        btnInscribir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInscribirActionPerformed(evt);
            }
        });
        getContentPane().add(btnInscribir);
        btnInscribir.setBounds(130, 330, 160, 30);

        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });
        getContentPane().add(btnVolver);
        btnVolver.setBounds(310, 330, 100, 30);

        getContentPane().add(lblMensaje);
        lblMensaje.setBounds(420, 330, 260, 30);

        setSize(700, 380);
    }// </editor-fold>

    private void btnInscribirActionPerformed(java.awt.event.ActionEvent evt) {
        int fila = tblSecciones.getSelectedRow();
        if (fila < 0) {
            lblMensaje.setForeground(java.awt.Color.RED);
            lblMensaje.setText("Seleccione una sección.");
            return;
        }
        Seccion seccion = controller.getSeccionByRow(fila);
        String resultado = controller.inscribirSeccionEstudiante(seccion);
        lblMensaje.setForeground(resultado.startsWith("Error") ? java.awt.Color.RED : new java.awt.Color(0, 130, 0));
        lblMensaje.setText(resultado);
        if (!resultado.startsWith("Error")) cargarTabla();
    }

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {
        ventanaPadre.setVisible(true);
        dispose();
    }

    private void cargarTabla() {
        modeloTabla.setRowCount(0);
        for (Object[] row : controller.getTablaSeccionesDisponibles()) {
            modeloTabla.addRow(row);
        }
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnInscribir;
    private javax.swing.JButton btnVolver;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblSecciones;
    // End of variables declaration
}

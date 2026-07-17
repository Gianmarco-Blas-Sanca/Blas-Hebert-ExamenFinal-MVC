package org.unisiga.view;

import org.unisiga.controller.*;
import org.unisiga.model.*;

public class FrmMenuPrincipal extends javax.swing.JFrame {

    private MenuController controller;

    public FrmMenuPrincipal(MenuController controller) {
        this.controller = controller;
        initComponents();
        setLocationRelativeTo(null);
        lblNombre.setText(controller.getNombreUsuario());
        configurarBotones();
    }

    private void configurarBotones() {
        btnInscribir.setVisible(controller.esEstudiante());
        btnNotas.setVisible(controller.esEstudiante());
        btnRegistrarNota.setVisible(controller.esAcademico());
        if (controller.esAcademico()) {
            btnRegistrarNota.setBounds(55, 100, 220, 35);
            btnSalir.setBounds(55, 155, 220, 35);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        btnInscribir = new javax.swing.JButton();
        btnNotas = new javax.swing.JButton();
        btnRegistrarNota = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UNISIGA - Menú Principal");
        setMinimumSize(new java.awt.Dimension(340, 340));
        setPreferredSize(new java.awt.Dimension(340, 340));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bienvenido/a");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 20, 300, 25);

        lblNombre.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        lblNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombre.setText("Nombre Usuario");
        getContentPane().add(lblNombre);
        lblNombre.setBounds(20, 50, 300, 30);

        btnInscribir.setBackground(new java.awt.Color(50, 100, 200));
        btnInscribir.setForeground(new java.awt.Color(255, 255, 255));
        btnInscribir.setText("Inscribirme en una Sección");
        btnInscribir.setFocusPainted(false);
        btnInscribir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInscribirActionPerformed(evt);
            }
        });
        getContentPane().add(btnInscribir);
        btnInscribir.setBounds(55, 100, 220, 35);

        btnNotas.setBackground(new java.awt.Color(50, 100, 200));
        btnNotas.setForeground(new java.awt.Color(255, 255, 255));
        btnNotas.setText("Ver Mis Calificaciones");
        btnNotas.setFocusPainted(false);
        btnNotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNotasActionPerformed(evt);
            }
        });
        getContentPane().add(btnNotas);
        btnNotas.setBounds(55, 145, 220, 35);

        btnRegistrarNota.setBackground(new java.awt.Color(50, 100, 200));
        btnRegistrarNota.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarNota.setText("Registrar Nota");
        btnRegistrarNota.setFocusPainted(false);
        btnRegistrarNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarNotaActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegistrarNota);
        btnRegistrarNota.setBounds(55, 100, 220, 35);

        btnSalir.setBackground(new java.awt.Color(190, 50, 50));
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setText("Cerrar Sesión");
        btnSalir.setFocusPainted(false);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir);
        btnSalir.setBounds(55, 200, 220, 35);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnInscribirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInscribirActionPerformed
        Estudiante est = (Estudiante) controller.getUsuario();
        InscripcionController ic = new InscripcionController(est, controller.getLoginController().getAsignaturasDb());
        new FrmInscripcion(ic, this).setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnInscribirActionPerformed

    private void btnNotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNotasActionPerformed
        Estudiante est = (Estudiante) controller.getUsuario();
        CalificacionController cc = new CalificacionController(est);
        new FrmMisCalificaciones(cc, this).setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnNotasActionPerformed

    private void btnRegistrarNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarNotaActionPerformed
        Academico acad = (Academico) controller.getUsuario();
        NotaController nc = new NotaController(acad);
        new FrmRegistrarNota(nc, this).setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnRegistrarNotaActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        new FrmLogin().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInscribir;
    private javax.swing.JButton btnNotas;
    private javax.swing.JButton btnRegistrarNota;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblNombre;
    // End of variables declaration//GEN-END:variables
}

package org.unisiga.view;

import java.util.List;
import org.unisiga.controller.LoginController;
import org.unisiga.controller.MenuController;
import org.unisiga.model.MiembroUniversitario;

public class FrmLogin extends javax.swing.JFrame {

    private LoginController controller;

    public FrmLogin() {
        controller = new LoginController();
        initComponents();
        setLocationRelativeTo(null);
        cargarUsuarios("Estudiante");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cmbUsuario = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnIngresar = new javax.swing.JButton();
        lblMensaje = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UNISIGA - Iniciar Sesión");
        setMinimumSize(new java.awt.Dimension(400, 280));
        setPreferredSize(new java.awt.Dimension(400, 280));
        setResizable(false);
        getContentPane().setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("SISTEMA UNISIGA v2");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(50, 20, 300, 30);

        jLabel2.setText("Tipo de usuario:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 65, 120, 25);

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Estudiante", "Académico" }));
        cmbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoActionPerformed(evt);
            }
        });
        getContentPane().add(cmbTipo);
        cmbTipo.setBounds(160, 65, 180, 25);

        jLabel3.setText("Usuario:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 100, 120, 25);

        getContentPane().add(cmbUsuario);
        cmbUsuario.setBounds(160, 100, 180, 25);

        jLabel4.setText("Contraseña:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(30, 135, 120, 25);

        getContentPane().add(txtPassword);
        txtPassword.setBounds(160, 135, 180, 25);

        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        getContentPane().add(btnIngresar);
        btnIngresar.setBounds(120, 175, 160, 30);

        lblMensaje.setForeground(new java.awt.Color(255, 0, 0));
        lblMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblMensaje);
        lblMensaje.setBounds(30, 215, 340, 25);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:initComponents
        lblMensaje.setText("");
        cargarUsuarios((String) cmbTipo.getSelectedItem());
    }//GEN-LAST:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:initComponents
        String tipo = (String) cmbTipo.getSelectedItem();
        String usuario = (String) cmbUsuario.getSelectedItem();
        String password = new String(txtPassword.getPassword());
        MiembroUniversitario user = controller.autenticar(tipo, usuario, password);
        if (user != null) {
            MenuController mc = new MenuController(user, controller);
            new FrmMenuPrincipal(mc).setVisible(true);
            dispose();
        } else {
            lblMensaje.setText("Credenciales incorrectas.");
        }
    }//GEN-LAST:initComponents

    private void cargarUsuarios(String tipo) {
        cmbUsuario.removeAllItems();
        List<String> nombres = "Estudiante".equals(tipo)
                ? controller.getNombresEstudiantes()
                : controller.getNombresAcademicos();
        for (String n : nombres) cmbUsuario.addItem(n);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JComboBox<String> cmbUsuario;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}

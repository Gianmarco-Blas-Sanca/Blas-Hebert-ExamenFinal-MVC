package org.unisiga.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import org.unisiga.controller.LoginController;
import org.unisiga.controller.MenuController;
import org.unisiga.model.MiembroUniversitario;

public class FrmLogin extends JFrame {
    private JComboBox<String> cmbTipo;
    private JComboBox<String> cmbUsuario;
    private JPasswordField txtPassword;
    private JButton btnIngresar;
    private JLabel lblMensaje;
    private LoginController controller;

    public FrmLogin() {
        controller = new LoginController();
        setTitle("UNISIGA - Iniciar Sesión");
        setSize(400, 280);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("SISTEMA UNISIGA v2", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(titulo, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Tipo de usuario:"), gbc);
        cmbTipo = new JComboBox<>(new String[]{"Estudiante", "Académico"});
        gbc.gridx = 1;
        panel.add(cmbTipo, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Usuario:"), gbc);
        cmbUsuario = new JComboBox<>();
        gbc.gridx = 1;
        panel.add(cmbUsuario, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Contraseña:"), gbc);
        txtPassword = new JPasswordField(15);
        gbc.gridx = 1;
        panel.add(txtPassword, gbc);

        btnIngresar = new JButton("Ingresar");
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        panel.add(btnIngresar, gbc);

        lblMensaje = new JLabel("", SwingConstants.CENTER);
        lblMensaje.setForeground(Color.RED);
        gbc.gridy = 5;
        panel.add(lblMensaje, gbc);

        add(panel);
        cargarUsuarios("Estudiante");

        cmbTipo.addActionListener(e -> {
            lblMensaje.setText("");
            cargarUsuarios((String) cmbTipo.getSelectedItem());
        });

        btnIngresar.addActionListener(e -> {
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
        });
    }

    private void cargarUsuarios(String tipo) {
        cmbUsuario.removeAllItems();
        List<String> nombres = "Estudiante".equals(tipo)
                ? controller.getNombresEstudiantes()
                : controller.getNombresAcademicos();
        for (String n : nombres) cmbUsuario.addItem(n);
    }
}

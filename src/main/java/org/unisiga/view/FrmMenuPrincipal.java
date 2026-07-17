package org.unisiga.view;

import javax.swing.*;
import java.awt.*;
import org.unisiga.controller.*;
import org.unisiga.model.*;

public class FrmMenuPrincipal extends JFrame {
    private MenuController controller;

    public FrmMenuPrincipal(MenuController controller) {
        this.controller = controller;
        setTitle("UNISIGA - Menú Principal");
        setSize(340, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 40, 25, 40));

        JLabel lblBienvenido = new JLabel("Bienvenido/a", SwingConstants.CENTER);
        lblBienvenido.setFont(new Font("Arial", Font.PLAIN, 13));
        lblBienvenido.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(lblBienvenido);

        JLabel lblNombre = new JLabel(controller.getNombreUsuario(), SwingConstants.CENTER);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 15));
        lblNombre.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(lblNombre);
        panel.add(Box.createVerticalStrut(20));

        if (controller.esEstudiante()) {
            JButton btnInscribir = crearBoton("Inscribirme en una Sección", new Color(50, 100, 200));
            JButton btnNotas = crearBoton("Ver Mis Calificaciones", new Color(50, 100, 200));

            panel.add(btnInscribir);
            panel.add(Box.createVerticalStrut(10));
            panel.add(btnNotas);
            panel.add(Box.createVerticalStrut(10));

            btnInscribir.addActionListener(e -> {
                Estudiante est = (Estudiante) controller.getUsuario();
                InscripcionController ic = new InscripcionController(est, controller.getLoginController().getAsignaturasDb());
                new FrmInscripcion(ic, this).setVisible(true);
                setVisible(false);
            });

            btnNotas.addActionListener(e -> {
                Estudiante est = (Estudiante) controller.getUsuario();
                CalificacionController cc = new CalificacionController(est);
                new FrmMisCalificaciones(cc, this).setVisible(true);
                setVisible(false);
            });
        }

        if (controller.esAcademico()) {
            JButton btnNota = crearBoton("Registrar Nota", new Color(50, 100, 200));
            panel.add(btnNota);
            panel.add(Box.createVerticalStrut(10));

            btnNota.addActionListener(e -> {
                Academico acad = (Academico) controller.getUsuario();
                NotaController nc = new NotaController(acad);
                new FrmRegistrarNota(nc, this).setVisible(true);
                setVisible(false);
            });
        }

        JButton btnSalir = crearBoton("Cerrar Sesión", new Color(190, 50, 50));
        panel.add(btnSalir);

        btnSalir.addActionListener(e -> {
            new FrmLogin().setVisible(true);
            dispose();
        });

        add(panel);
    }

    private JButton crearBoton(String texto, Color color) {
        JButton btn = new JButton(texto);
        btn.setAlignmentX(CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(220, 35));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        return btn;
    }
}

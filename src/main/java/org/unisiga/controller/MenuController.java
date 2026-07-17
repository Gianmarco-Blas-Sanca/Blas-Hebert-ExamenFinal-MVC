package org.unisiga.controller;

import org.unisiga.model.*;

public class MenuController {
    private MiembroUniversitario usuario;
    private LoginController loginController;

    public MenuController(MiembroUniversitario usuario, LoginController loginController) {
        this.usuario = usuario;
        this.loginController = loginController;
    }

    public boolean esEstudiante() { return usuario instanceof Estudiante; }
    public boolean esAcademico() { return usuario instanceof Academico; }
    public String getNombreUsuario() { return usuario.getNombre(); }
    public MiembroUniversitario getUsuario() { return usuario; }
    public LoginController getLoginController() { return loginController; }
}

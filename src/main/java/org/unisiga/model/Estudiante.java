package org.unisiga.model;

import java.util.ArrayList;
import java.util.List;

public class Estudiante extends MiembroUniversitario {
    private String matricula;
    private int anioIngreso;
    private float promedioPpa;
    private List<Inscripcion> inscripciones;

    public Estudiante(String rut, String nombre, String correo, String matricula, int anioIngreso, float promedioPpa) {
        super(rut, nombre, correo);
        this.matricula = matricula;
        this.anioIngreso = anioIngreso;
        this.promedioPpa = promedioPpa;
        this.inscripciones = new ArrayList<>();
    }

    @Override
    public boolean login(String password) {
        throw new UnsupportedOperationException("Método login() no implementado aún.");
    }

    public void inscribirSeccion(Seccion seccion) {
        throw new UnsupportedOperationException("Método inscribirSeccion() no implementado aún.");
    }

    public String getMatricula() { return matricula; }
    public float getPromedioPpa() { return promedioPpa; }
    public List<Inscripcion> getInscripciones() { return inscripciones; }
}

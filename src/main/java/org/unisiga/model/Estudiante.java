package org.unisiga.model;

import java.util.ArrayList;
import java.util.List;

public class Estudiante extends MiembroUniversitario {
    private String matricula;
    private int anioIngreso;
    private float promedio;
    private List<Inscripcion> inscripciones;

    public Estudiante(String rut, String nombre, String correo, String matricula, int anioIngreso, float promedio) {
        super(rut, nombre, correo);
        this.matricula = matricula;
        this.anioIngreso = anioIngreso;
        this.promedio = promedio;
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
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public int getAnioIngreso() { return anioIngreso; }
    public void setAnioIngreso(int anioIngreso) { this.anioIngreso = anioIngreso; }
    public float getPromedio() { return promedio; }
    public void setPromedio(float promedio) { this.promedio = promedio; }
    public List<Inscripcion> getInscripciones() { return inscripciones; }
}

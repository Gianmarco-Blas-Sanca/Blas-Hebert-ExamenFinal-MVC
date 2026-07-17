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
        return password != null && password.length() >= 8;
    }

    public void inscribirSeccion(Seccion seccion) {
        if (seccion == null) throw new IllegalArgumentException("La sección no puede ser nula.");
        if (seccion.getInscripciones().size() >= seccion.getCupoMaximo())
            throw new IllegalStateException("La sección no tiene cupos disponibles.");
        Inscripcion ins = new Inscripcion(this, seccion);
        inscripciones.add(ins);
        seccion.getInscripciones().add(ins);
    }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public int getAnioIngreso() { return anioIngreso; }
    public void setAnioIngreso(int anioIngreso) { this.anioIngreso = anioIngreso; }
    public float getPromedio() { return promedio; }
    public void setPromedio(float promedio) { this.promedio = promedio; }
    public List<Inscripcion> getInscripciones() { return inscripciones; }
}

package org.unisiga.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Inscripcion {
    private Estudiante estudiante;
    private Seccion seccion;
    private String estadoInscripcion;
    private Date fechaInscripcion;
    private List<Calificacion> calificaciones;

    public Inscripcion(Estudiante estudiante, Seccion seccion) {
        this.estudiante = estudiante;
        this.seccion = seccion;
        this.estadoInscripcion = "Inscrito";
        this.fechaInscripcion = new Date();
        this.calificaciones = new ArrayList<>();
    }

    public Estudiante getEstudiante() { return estudiante; }
    public Seccion getSeccion() { return seccion; }
    public String getEstadoInscripcion() { return estadoInscripcion; }
    public void setEstadoInscripcion(String estado) { this.estadoInscripcion = estado; }
    public Date getFechaInscripcion() { return fechaInscripcion; }
    public void setFechaInscripcion(Date fechaInscripcion) { this.fechaInscripcion = fechaInscripcion; }
    public List<Calificacion> getCalificaciones() { return calificaciones; }
}

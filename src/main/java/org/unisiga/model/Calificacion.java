package org.unisiga.model;

public class Calificacion {
    private float nota;
    private Inscripcion inscripcion;
    private Evaluacion evaluacion;

    public Calificacion(float nota, Inscripcion inscripcion, Evaluacion evaluacion) {
        this.nota = nota;
        this.inscripcion = inscripcion;
        this.evaluacion = evaluacion;
    }

    public float getNota() { return nota; }
    public void setNota(float nota) { this.nota = nota; }
    public Inscripcion getInscripcion() { return inscripcion; }
    public Evaluacion getEvaluacion() { return evaluacion; }
}

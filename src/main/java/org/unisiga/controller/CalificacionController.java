package org.unisiga.controller;

import org.unisiga.model.*;
import java.util.List;

public class CalificacionController {
    private Estudiante estudiante;

    public CalificacionController(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public List<Inscripcion> getInscripcionesActivas() {
        return estudiante.getInscripciones();
    }

    public Object[][] getTablaCalificaciones(Inscripcion inscripcion) {
        List<Evaluacion> evaluaciones = inscripcion.getSeccion().getAsignatura().getEvaluaciones();
        Object[][] data = new Object[evaluaciones.size()][3];
        for (int i = 0; i < evaluaciones.size(); i++) {
            Evaluacion ev = evaluaciones.get(i);
            data[i][0] = ev.getTitulo();
            data[i][1] = String.format("%.0f%%", ev.getPonderacion() * 100);
            float nota = 0f;
            for (Calificacion c : inscripcion.getCalificaciones()) {
                if (c.getEvaluacion() == ev) { nota = c.getNota(); break; }
            }
            data[i][2] = nota == 0f ? "Sin nota" : String.format("%.1f", nota);
        }
        return data;
    }

    public String calcularPromedio(Inscripcion inscripcion) {
        float total = 0f;
        float ponderacionTotal = 0f;
        for (Calificacion c : inscripcion.getCalificaciones()) {
            total += c.getNota() * c.getEvaluacion().getPonderacion();
            ponderacionTotal += c.getEvaluacion().getPonderacion();
        }
        if (ponderacionTotal == 0) return "Sin notas registradas";
        return String.format("%.1f", total / ponderacionTotal);
    }
}

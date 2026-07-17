package org.unisiga.controller;

import org.unisiga.model.*;
import java.util.List;

public class NotaController {
    private Academico academico;

    public NotaController(Academico academico) {
        this.academico = academico;
    }

    public List<Seccion> getSeccionesDelDocente() {
        return academico.getSeccionesDictadas();
    }

    public List<Evaluacion> getEvaluacionesDe(Seccion seccion) {
        return seccion.getAsignatura().getEvaluaciones();
    }

    public Object[][] getTablaEstudiantes(Seccion seccion, Evaluacion evaluacion) {
        List<Inscripcion> inscripciones = seccion.getInscripciones();
        Object[][] data = new Object[inscripciones.size()][3];
        for (int i = 0; i < inscripciones.size(); i++) {
            Inscripcion ins = inscripciones.get(i);
            data[i][0] = ins.getEstudiante().getNombre();
            data[i][1] = ins.getEstudiante().getMatricula();
            float nota = 0f;
            for (Calificacion c : ins.getCalificaciones()) {
                if (c.getEvaluacion() == evaluacion) { nota = c.getNota(); break; }
            }
            data[i][2] = nota == 0f ? "Sin nota" : String.format("%.1f", nota);
        }
        return data;
    }

    public String guardarNota(Seccion seccion, Evaluacion evaluacion, int rowEstudiante, float nota) {
        try {
            Inscripcion ins = seccion.getInscripciones().get(rowEstudiante);
            academico.registrarNota(ins, evaluacion, nota);
            return "Nota registrada correctamente.";
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }
}

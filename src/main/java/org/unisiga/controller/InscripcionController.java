package org.unisiga.controller;

import org.unisiga.model.*;
import java.util.ArrayList;
import java.util.List;

public class InscripcionController {
    private Estudiante estudiante;
    private List<Asignatura> asignaturasDb;

    public InscripcionController(Estudiante estudiante, List<Asignatura> asignaturasDb) {
        this.estudiante = estudiante;
        this.asignaturasDb = asignaturasDb;
    }

    public List<Seccion> getSeccionesDisponibles() {
        List<Seccion> disponibles = new ArrayList<>();
        for (Asignatura a : asignaturasDb) {
            for (Seccion s : a.getSecciones()) {
                if (s.getInscripciones().size() < s.getCupoMaximo() && !yaInscrito(s)) {
                    disponibles.add(s);
                }
            }
        }
        return disponibles;
    }

    private boolean yaInscrito(Seccion s) {
        for (Inscripcion ins : estudiante.getInscripciones()) {
            if (ins.getSeccion() == s) return true;
        }
        return false;
    }

    public String inscribirSeccionEstudiante(Seccion seccion) {
        Asignatura asignatura = seccion.getAsignatura();
        for (Asignatura pre : asignatura.getPrerrequisitos()) {
            boolean aprobado = false;
            for (Inscripcion ins : estudiante.getInscripciones()) {
                if (ins.getSeccion().getAsignatura().getCodigo().equals(pre.getCodigo())
                        && ins.getEstadoInscripcion().equals("Aprobado")) {
                    aprobado = true;
                    break;
                }
            }
            if (!aprobado) return "Error: Prerrequisito no aprobado: " + pre.getNombre();
        }
        try {
            estudiante.inscribirSeccion(seccion);
            return "Inscripción exitosa en " + asignatura.getNombre() + " Sección " + seccion.getIdGrupo();
        } catch (IllegalStateException e) {
            return "Error: " + e.getMessage();
        }
    }

    public Object[][] getTablaSeccionesDisponibles() {
        List<Seccion> secciones = getSeccionesDisponibles();
        Object[][] data = new Object[secciones.size()][5];
        for (int i = 0; i < secciones.size(); i++) {
            Seccion s = secciones.get(i);
            data[i][0] = s.getAsignatura().getCodigo();
            data[i][1] = s.getAsignatura().getNombre();
            data[i][2] = String.valueOf(s.getIdGrupo());
            data[i][3] = s.getDocenteDicta() != null ? s.getDocenteDicta().getNombre() : "Sin asignar";
            data[i][4] = s.getHorario();
        }
        return data;
    }

    public Seccion getSeccionByRow(int row) {
        return getSeccionesDisponibles().get(row);
    }
}

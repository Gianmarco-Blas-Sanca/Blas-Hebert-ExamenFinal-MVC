package org.unisiga.controller;

import java.util.ArrayList;
import java.util.List;
import org.unisiga.model.*;

public class InscripcionController {
    private List<Estudiante> estudiantesDb;
    private List<Asignatura> asignaturasDb;

    public InscripcionController() {
        this.estudiantesDb = new ArrayList<>();
        this.asignaturasDb = new ArrayList<>();
    }

    public void registrarEstudianteEnDb(Estudiante e) { estudiantesDb.add(e); }
    public void registrarAsignaturaEnDb(Asignatura a) { asignaturasDb.add(a); }

    public String inscribirSeccionEstudiante(String matricula, String codigoAsignatura, char idGrupo) {
        throw new UnsupportedOperationException("El controlador de inscripción no está implementado.");
    }
}

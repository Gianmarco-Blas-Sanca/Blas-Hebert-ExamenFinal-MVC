package org.unisiga.model;

import java.util.ArrayList;
import java.util.List;

public class Seccion {
    private char idGrupo;
    private int cupoMaximo;
    private String horario;
    private Asignatura asignatura;
    private Academico docenteDicta;
    private List<Inscripcion> inscripciones;

    Seccion(char idGrupo, int cupoMaximo, String horario, Asignatura asignatura) {
        this.idGrupo = idGrupo;
        this.cupoMaximo = cupoMaximo;
        this.horario = horario;
        this.asignatura = asignatura;
        this.inscripciones = new ArrayList<>();
    }

    public void asignarDocente(Academico docente) {
        throw new UnsupportedOperationException("Método asignarDocente() no implementado aún.");
    }

    public char getIdGrupo() { return idGrupo; }
    public int getCupoMaximo() { return cupoMaximo; }
    public String getHorario() { return horario; }
    public Asignatura getAsignatura() { return asignatura; }
    public Academico getDocenteDicta() { return docenteDicta; }
    public List<Inscripcion> getInscripciones() { return inscripciones; }
}

package org.unisiga.model;

import java.util.ArrayList;
import java.util.List;

public class Asignatura {
    private String codigo;
    private String nombre;
    private int creditosSct;
    
    private List<Asignatura> prerrequisitos;
    
    private List<Seccion> secciones;
    private List<Evaluacion> evaluaciones;

    public Asignatura(String codigo, String nombre, int creditosSct) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditosSct = creditosSct;
        this.prerrequisitos = new ArrayList<>();
        this.secciones = new ArrayList<>();
        this.evaluaciones = new ArrayList<>();
    }

    public void agregarPrerrequisito(Asignatura asig) {
        throw new UnsupportedOperationException("Método agregarPrerrequisito() no implementado aún.");
    }

    public Seccion crearSeccion(char idGrupo, int cupoMaximo, String horario) {
        throw new UnsupportedOperationException("Método crearSeccion() no implementado aún.");
    }

    public Evaluacion crearEvaluacion(int id, String titulo, float ponderacion) {
        throw new UnsupportedOperationException("Método crearEvaluacion() no implementado aún.");
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public List<Asignatura> getPrerrequisitos() { return prerrequisitos; }
    public List<Seccion> getSecciones() { return secciones; }
    public List<Evaluacion> getEvaluaciones() { return evaluaciones; }
}

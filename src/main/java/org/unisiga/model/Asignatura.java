package org.unisiga.model;

import java.util.ArrayList;
import java.util.List;

public class Asignatura {
    private String codigo;
    private String nombre;
    private int creditosSCT;

    private List<Asignatura> prerrequisitos;
    private List<Seccion> secciones;
    private List<Evaluacion> evaluaciones;

    public Asignatura(String codigo, String nombre, int creditosSCT) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditosSCT = creditosSCT;
        this.prerrequisitos = new ArrayList<>();
        this.secciones = new ArrayList<>();
        this.evaluaciones = new ArrayList<>();
    }

    public void agregarPrerrequisito(Asignatura asig) {
        if (asig != null && !prerrequisitos.contains(asig)) {
            prerrequisitos.add(asig);
        }
    }

    public Seccion crearSeccion(char idGrupo, int cupoMaximo, String horario) {
        Seccion s = new Seccion(idGrupo, cupoMaximo, horario, this);
        secciones.add(s);
        return s;
    }

    public Evaluacion crearEvaluacion(int id, String titulo, float ponderacion) {
        Evaluacion e = new Evaluacion(id, titulo, ponderacion, this);
        evaluaciones.add(e);
        return e;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getCreditosSCT() { return creditosSCT; }
    public void setCreditosSCT(int creditosSCT) { this.creditosSCT = creditosSCT; }
    public List<Asignatura> getPrerrequisitos() { return prerrequisitos; }
    public List<Seccion> getSecciones() { return secciones; }
    public List<Evaluacion> getEvaluaciones() { return evaluaciones; }
}

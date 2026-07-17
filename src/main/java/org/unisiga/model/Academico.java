package org.unisiga.model;

import java.util.ArrayList;
import java.util.List;

public class Academico extends MiembroUniversitario {
    private String idEmpleado;
    private String tipoContrato;
    private Departamento departamento;
    private List<Seccion> seccionesDictadas;

    public Academico(String rut, String nombre, String correo, String idEmpleado, String tipoContrato) {
        super(rut, nombre, correo);
        this.idEmpleado = idEmpleado;
        this.tipoContrato = tipoContrato;
        this.seccionesDictadas = new ArrayList<>();
    }

    @Override
    public boolean login(String password) {
        throw new UnsupportedOperationException("Método login() no implementado aún.");
    }

    public void registrarNota(Inscripcion inscripcion, Evaluacion evaluacion, float valorNota) {
        throw new UnsupportedOperationException("Método registrarNota() no implementado aún.");
    }

    public String getIdEmpleado() { return idEmpleado; }
    public Departamento getDepartamento() { return departamento; }
    public void setDepartamento(Departamento depto) { this.departamento = depto; }
    public List<Seccion> getSeccionesDictadas() { return seccionesDictadas; }
}

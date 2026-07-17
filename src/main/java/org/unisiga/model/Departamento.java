package org.unisiga.model;

import java.util.ArrayList;
import java.util.List;

public class Departamento {
    private String codigoDepto;
    private String nombre;
    private List<Academico> academicos;

    public Departamento(String codigoDepto, String nombre) {
        this.codigoDepto = codigoDepto;
        this.nombre = nombre;
        this.academicos = new ArrayList<>();
    }

    public void asociarAcademico(Academico acad) {
        throw new UnsupportedOperationException("Método asociarAcademico() no implementado aún.");
    }

    public String getCodigoDepto() { return codigoDepto; }
    public void setCodigoDepto(String codigoDepto) { this.codigoDepto = codigoDepto; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public List<Academico> getAcademicos() { return academicos; }
}

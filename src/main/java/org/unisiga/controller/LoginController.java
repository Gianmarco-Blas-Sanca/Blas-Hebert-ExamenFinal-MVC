package org.unisiga.controller;

import org.unisiga.model.*;
import java.util.ArrayList;
import java.util.List;

public class LoginController {
    private List<Estudiante> estudiantesDb;
    private List<Academico> academicosDb;
    private List<Asignatura> asignaturasDb;

    public LoginController() {
        estudiantesDb = new ArrayList<>();
        academicosDb = new ArrayList<>();
        asignaturasDb = new ArrayList<>();
        sembrarDatos();
    }

    private void sembrarDatos() {
        Departamento deptInf = new Departamento("D01", "Departamento de Informática");
        Departamento deptMat = new Departamento("D02", "Departamento de Matemáticas");

        Academico carlos = new Academico("12345678-9", "Dr. Carlos López", "carlos@unisiga.cl", "A001", "Planta");
        Academico ana = new Academico("98765432-1", "Dra. Ana Martínez", "ana@unisiga.cl", "A002", "Hora");
        carlos.setDepartamento(deptInf);
        ana.setDepartamento(deptMat);
        academicosDb.add(carlos);
        academicosDb.add(ana);

        Asignatura algebra = new Asignatura("MAT101", "Álgebra", 6);
        Asignatura prog = new Asignatura("INF101", "Programación Básica", 6);
        Asignatura poo = new Asignatura("INF201", "Prog. Orientada a Objetos", 6);
        poo.agregarPrerrequisito(prog);
        asignaturasDb.add(algebra);
        asignaturasDb.add(prog);
        asignaturasDb.add(poo);

        Seccion algA = algebra.crearSeccion('A', 30, "Lunes 08:00-10:00");
        Seccion algB = algebra.crearSeccion('B', 25, "Miércoles 10:00-12:00");
        algA.asignarDocente(carlos);
        algB.asignarDocente(ana);

        Seccion progA = prog.crearSeccion('A', 25, "Martes 10:00-12:00");
        progA.asignarDocente(ana);

        Seccion pooA = poo.crearSeccion('A', 20, "Miércoles 08:00-10:00");
        pooA.asignarDocente(carlos);

        algebra.crearEvaluacion(1, "Prueba 1", 0.30f);
        algebra.crearEvaluacion(2, "Prueba 2", 0.30f);
        algebra.crearEvaluacion(3, "Examen Final", 0.40f);

        prog.crearEvaluacion(1, "Prueba 1", 0.30f);
        prog.crearEvaluacion(2, "Prueba 2", 0.30f);
        prog.crearEvaluacion(3, "Examen Final", 0.40f);

        poo.crearEvaluacion(1, "Prueba 1", 0.30f);
        poo.crearEvaluacion(2, "Prueba 2", 0.30f);
        poo.crearEvaluacion(3, "Examen Final", 0.40f);

        Estudiante juan = new Estudiante("11111111-1", "Juan Pérez", "juan@unisiga.cl", "2023001", 2023, 5.5f);
        Inscripcion insJuanProg = new Inscripcion(juan, progA);
        insJuanProg.setEstadoInscripcion("Aprobado");
        juan.getInscripciones().add(insJuanProg);
        progA.getInscripciones().add(insJuanProg);
        estudiantesDb.add(juan);

        Estudiante maria = new Estudiante("22222222-2", "María García", "maria@unisiga.cl", "2023002", 2023, 4.8f);
        estudiantesDb.add(maria);
    }

    public MiembroUniversitario autenticar(String tipo, String nombreUsuario, String password) {
        if ("Estudiante".equals(tipo)) {
            for (Estudiante e : estudiantesDb) {
                if (e.getNombre().equals(nombreUsuario) && e.login(password)) return e;
            }
        } else {
            for (Academico a : academicosDb) {
                if (a.getNombre().equals(nombreUsuario) && a.login(password)) return a;
            }
        }
        return null;
    }

    public List<String> getNombresEstudiantes() {
        List<String> nombres = new ArrayList<>();
        for (Estudiante e : estudiantesDb) nombres.add(e.getNombre());
        return nombres;
    }

    public List<String> getNombresAcademicos() {
        List<String> nombres = new ArrayList<>();
        for (Academico a : academicosDb) nombres.add(a.getNombre());
        return nombres;
    }

    public List<Estudiante> getEstudiantesDb() { return estudiantesDb; }
    public List<Academico> getAcademicosDb() { return academicosDb; }
    public List<Asignatura> getAsignaturasDb() { return asignaturasDb; }
}

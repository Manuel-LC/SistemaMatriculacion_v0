package org.iesalandalus.programacion.matriculacion;


import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.negocio.*;
import org.iesalandalus.programacion.matriculacion.vista.*;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;

public class MainApp {
    public static final int CAPACIDAD=3;
    private static Alumnos alumnos = new Alumnos(CAPACIDAD);
    private static Asignaturas asignaturas = new Asignaturas(CAPACIDAD);
    private static CiclosFormativos ciclosFormativos = new CiclosFormativos(CAPACIDAD);
    private static Matriculas matriculas = new Matriculas(CAPACIDAD);

    public static void main(String[] args) {
        Opcion opcion;

        do {
            System.out.println("===============================================================================================");
            System.out.println("Sistema de matriculación del IES Al-Andalus");
            System.out.println("===============================================================================================");
            System.out.println();
            Consola.mostrarMenu();
            System.out.println();
            opcion = Consola.elegirOpcion();
            ejecutarOpcion(opcion);
        } while (opcion != Opcion.SALIR);

        System.out.println();
        System.out.println("Hasta luego!!!!");
    }

    private static void ejecutarOpcion(Opcion opcion) {
        switch (opcion) {
            case INSERTAR_ALUMNO: insertarAlumno();
            case BUSCAR_ALUMNO: buscarAlumno();
            case BORRAR_ALUMNO: borrarAlumno();
            case MOSTRAR_ALUMNOS: mostrarAlumnos();
            case INSERTAR_ASIGNATURA: insertarAsignatura();
            case BUSCAR_ASIGNATURA: buscarAsignatura();
            case BORRAR_ASIGNATURA: borrarAsignatura();
            case MOSTRAR_ASIGNATURAS: mostrarAsignaturas();
            case INSERTAR_CICLO_FORMATIVO: insertarCicloFormativo();
            case BUSCAR_CICLO_FORMATIVO: buscarCicloFormativo();
            case BORRAR_CICLO_FORMATIVO: borrarCicloFormativo();
            case MOSTRAR_CICLOS_FORMATIVOS: mostrarCiclosFormativos();
            case INSERTAR_MATRICULA: insertarMatricula();
            case BUSCAR_MATRICULA: buscarMatricula();
            case ANULAR_MATRICULA: anularMatricula();
            case MOSTRAR_MATRICULAS: mostrarMatriculas();
            case MOSTRAR_MATRICULAS_ALUMNO: mostrarMatriculasPorAlumno();
            case MOSTRAR_MATRICULAS_CICLO_FORMATIVO: mostrarMatriculasPorCicloFormativo();
            case MOSTRAR_MATRICULAS_CURSO_ACADEMICO: mostrarMatriculasPorCursoAcademico();
        }
    }

    private static void insertarAlumno() {
        try {
            Alumno alumno = Consola.leerAlumno();
            Alumnos.insertar(alumno);
            System.out.println("Alumno insertado correctamente.");
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void buscarAlumno() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            alumno = Alumnos.buscar(alumno);

            if (alumno != null) {
                System.out.println(alumno);
            } else {
                System.out.println("Alumno no encontrado.");
            }
        }
        catch (Exception e) {
            System.out.println("Error al buscar el alumno: " + e.getMessage());
        }
    }

    private static void borrarAlumno() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            Alumnos.borrar(alumno);
            System.out.println("Alumno borrado correctamente.");
        }
        catch (Exception e) {
            System.out.println("Error al borrar el alumno: " + e.getMessage());
        }
    }

    private static void mostrarAlumnos() {
        Alumno[] listaAlumnos = Alumnos.get();

        if (listaAlumnos.length == 0) {
            System.out.println("No hay alumnos registrados.");
        } else {
            for (Alumno alumno : listaAlumnos) {
                System.out.println(alumno);
            }
        }
    }

    private static void insertarAsignatura() {
        try {
            Asignatura asignatura = Consola.leerAsignatura();
            Asignaturas.insertar(asignatura);
            System.out.println("Asignatura insertada correctamente.");
        }
        catch (Exception e) {
            System.out.println("Error al insertar la asignatura: " + e.getMessage());
        }
    }

    private static void buscarAsignatura() {
        try {
            Asignatura asignatura = Consola.getAsignaturaPorCodigo();
            asignatura = Asignaturas.buscar(asignatura);

            if (asignatura != null) {
                System.out.println(asignatura);
            } else {
                System.out.println("Asignatura no encontrada.");
            }
        }
        catch (Exception e) {
            System.out.println("Error al buscar la asignatura: " + e.getMessage());
        }
    }

    private static void borrarAsignatura() {
        try {
            Asignatura asignatura = Consola.getAsignaturaPorCodigo();
            Asignaturas.borrar(asignatura);
            System.out.println("Asignatura borrada correctamente.");
        }
        catch (Exception e) {
            System.out.println("Error al borrar la asignatura: " + e.getMessage());
        }
    }

    private static void mostrarAsignaturas() {
        Asignatura[] listaAsignaturas = Asignaturas.get();

        if (listaAsignaturas.length == 0) {
            System.out.println("No hay asignaturas registradas.");
        } else {
            for (Asignatura asignatura : listaAsignaturas) {
                System.out.println(asignatura);
            }
        }
    }

    private static void insertarCicloFormativo() {
        try {
            CicloFormativo ciclo = Consola.leerCicloFormativo();
            CiclosFormativos.insertar(ciclo);
            System.out.println("Ciclo formativo insertado correctamente.");
        }
        catch (Exception e) {
            System.out.println("Error al insertar el ciclo formativo: " + e.getMessage());
        }
    }

    private static void buscarCicloFormativo() {
        try {
            CicloFormativo cicloFormativo = Consola.getCicloFormativoPorCodigo();
            cicloFormativo = CiclosFormativos.buscar(cicloFormativo);

            if (cicloFormativo != null) {
                System.out.println(cicloFormativo);
            } else {
                System.out.println("Ciclo formativo no encontrado.");
            }
        }
        catch (Exception e) {
            System.out.println("Error al buscar el ciclo formativo: " + e.getMessage());
        }
    }

    private static void borrarCicloFormativo() {
        try {
            CicloFormativo cicloFormativo = Consola.getCicloFormativoPorCodigo();
            CiclosFormativos.borrar(cicloFormativo);
            System.out.println("Ciclo formativo borrado correctamente.");
        }
        catch (Exception e) {
            System.out.println("Error al borrar el ciclo formativo: " + e.getMessage());
        }
    }

    private static void mostrarCiclosFormativos() {
    try {
        CiclosFormativos coleccionCiclos = ciclosFormativos;

        if (coleccionCiclos != null && coleccionCiclos.getTamano() > 0) {
            Consola.mostrarCiclosFormativos(coleccionCiclos);
        } else {
            System.out.println("No hay ciclos formativos registrados.");
        }
    } catch (Exception e) {
        System.out.println("Error al mostrar los ciclos formativos: " + e.getMessage());
    }
    }

    private static void insertarMatricula() {
        try {
            Matricula matricula = Consola.leerMatricula(alumnos, asignaturas);
            Matriculas.insertar(matricula);
            System.out.println("Matrícula insertada correctamente.");
        }
        catch (Exception e) {
            System.out.println("Error al insertar la matrícula: " + e.getMessage());
        }
    }

    private static void buscarMatricula() {
        try {
            Matricula matricula = Consola.getMatriculaPorIdentificador();
            matricula = Matriculas.buscar(matricula);

            if (matricula != null) {
                System.out.println("Matrícula encontrada: " + matricula);
            } else {
                System.out.println("No existe ninguna matrícula con ese identificador.");
            }
        }
        catch (Exception e) {
            System.out.println("Error al buscar la matrícula: " + e.getMessage());
        }
    }

    private static void anularMatricula() {
        try {
            if (matriculas.getTamano() == 0) {
                System.out.println("No hay matrículas registradas para anular.");
                return;
            }

            mostrarMatriculas();
            Matricula matricula = Consola.getMatriculaPorIdentificador();
            matricula = Matriculas.buscar(matricula);

            if (matricula != null) {
                LocalDate fechaAnulacion = Consola.leerFecha("Introduce la fecha de anulación: ");
                matricula.setFechaAnulacion(fechaAnulacion);
                Matriculas.borrar(matricula);
                System.out.println("Matrícula anulada correctamente.");
            } else {
                System.out.println("No existe ninguna matrícula con ese identificador.");
            }
        }
        catch (Exception e) {
            System.out.println("Error al anular la matrícula: " + e.getMessage());
        }
    }

    private static void mostrarMatriculas() {
         try {
            if (matriculas.getTamano() == 0) {
                System.out.println("No hay matrículas registradas.");
            } else {
                System.out.println("Matrículas registradas:");
                for (Matricula matricula : Matriculas.get()) {
                    System.out.println(matricula);
                }
            }
        }
         catch (Exception e) {
            System.out.println("Error al mostrar los ciclos formativos: " + e.getMessage());
        }
    }

    private static void mostrarMatriculasPorAlumno() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            alumno = Alumnos.buscar(alumno);

            if (alumno == null) {
                System.out.println("El alumno no está registrado.");
                return;
            }

            Matricula[] matriculasAlumno = Matriculas.get(alumno);

            if (matriculasAlumno.length == 0) {
                System.out.println("El alumno no tiene matrículas registradas.");
            } else {
                System.out.println("Matrículas del alumno " + alumno.getNombre() + ":");
                for (Matricula matricula : matriculasAlumno) {
                    System.out.println(matricula);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar las matrículas del alumno: " + e.getMessage());
        }
    }

    private static void mostrarMatriculasPorCicloFormativo() {
        try {
            if (ciclosFormativos.getTamano() == 0) {
                System.out.println("No hay ciclos formativos registrados.");
                return;
            }

            mostrarCiclosFormativos();
            CicloFormativo cicloFormativo = Consola.getCicloFormativoPorCodigo();
            cicloFormativo = CiclosFormativos.buscar(cicloFormativo);

            if (cicloFormativo == null) {
                System.out.println("El ciclo formativo no está registrado.");
                return;
            }

            Matricula[] matriculasCiclo = Matriculas.get(cicloFormativo);

            if (matriculasCiclo.length == 0) {
                System.out.println("El ciclo formativo no tiene matrículas registradas.");
            } else {
                System.out.println("Matrículas del ciclo formativo " + cicloFormativo.getNombre() + ":");
                for (Matricula matricula : matriculasCiclo) {
                    System.out.println(matricula);
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error al mostrar las matrículas del ciclo formativo: " + e.getMessage());
        }
    }

    private static void mostrarMatriculasPorCursoAcademico() {
        try {
            System.out.println("Introduce el curso academico:");
            String cursoAcademico = Entrada.cadena();
            Matricula[] matriculasCurso = Matriculas.get(cursoAcademico);

            if (matriculasCurso.length == 0) {
                System.out.println("No hay matrículas registradas para el curso académico " + cursoAcademico + ".");
            } else {
                System.out.println("Matrículas registradas para el curso académico " + cursoAcademico + ":");
                for (Matricula matricula : matriculasCurso) {
                    System.out.println(matricula);
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error al mostrar las matrículas por curso académico: " + e.getMessage());
        }
    }
}

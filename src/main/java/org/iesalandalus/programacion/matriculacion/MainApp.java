package org.iesalandalus.programacion.matriculacion;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.negocio.*;
import org.iesalandalus.programacion.matriculacion.vista.*;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;

public class MainApp {
    public static final int CAPACIDAD=3;
    private static final Alumnos alumnos = new Alumnos(CAPACIDAD);
    private static final Asignaturas asignaturas = new Asignaturas(CAPACIDAD);
    private static final CiclosFormativos ciclosFormativos = new CiclosFormativos(CAPACIDAD);
    private static final Matriculas matriculas = new Matriculas(CAPACIDAD);

    public static void main(String[] args) {
        Opcion opcion = Opcion.SALIR;

        do {
            try {
                System.out.println();
                System.out.println("===============================================================================================");
                System.out.println("Sistema de matriculación del IES Al-Andalus");
                System.out.println("===============================================================================================");
                Consola.mostrarMenu();
                System.out.println();
                opcion = Consola.elegirOpcion();
                ejecutarOpcion(opcion);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (opcion != Opcion.SALIR);

        System.out.println("Hasta luego!!!!");
    }

    private static void ejecutarOpcion(Opcion opcion) throws OperationNotSupportedException {
        switch (opcion) {
            case INSERTAR_ALUMNO: insertarAlumno(); break;
            case BUSCAR_ALUMNO: buscarAlumno(); break;
            case BORRAR_ALUMNO: borrarAlumno(); break;
            case MOSTRAR_ALUMNOS: mostrarAlumnos(); break;
            case INSERTAR_ASIGNATURA: insertarAsignatura(); break;
            case BUSCAR_ASIGNATURA: buscarAsignatura(); break;
            case BORRAR_ASIGNATURA: borrarAsignatura(); break;
            case MOSTRAR_ASIGNATURAS: mostrarAsignaturas(); break;
            case INSERTAR_CICLO_FORMATIVO: insertarCicloFormativo(); break;
            case BUSCAR_CICLO_FORMATIVO: buscarCicloFormativo(); break;
            case BORRAR_CICLO_FORMATIVO: borrarCicloFormativo(); break;
            case MOSTRAR_CICLOS_FORMATIVOS: mostrarCiclosFormativos(); break;
            case INSERTAR_MATRICULA: insertarMatricula(); break;
            case BUSCAR_MATRICULA: buscarMatricula(); break;
            case ANULAR_MATRICULA: anularMatricula(); break;
            case MOSTRAR_MATRICULAS: mostrarMatriculas(); break;
            case MOSTRAR_MATRICULAS_ALUMNO: mostrarMatriculasPorAlumno(); break;
            case MOSTRAR_MATRICULAS_CICLO_FORMATIVO: mostrarMatriculasPorCicloFormativo(); break;
            case MOSTRAR_MATRICULAS_CURSO_ACADEMICO: mostrarMatriculasPorCursoAcademico(); break;
        }
    }

    private static void insertarAlumno() {
        try {
            System.out.println();
            System.out.println("Datos del alumno");
            System.out.println("=============================================================================================");
            Alumno alumno = Consola.leerAlumno();
            Alumnos.insertar(alumno);
            System.out.println("Alumno insertado correctamente.");
        }
        catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
            System.out.println("*" + e.getMessage());
        }
    }

    private static void buscarAlumno() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            alumno = Alumnos.buscar(alumno);

            if (alumno != null) {
                System.out.println("Alumno encontrado:");
                System.out.println(alumno);
            } else {
                System.out.println("Alumno no encontrado.");
            }
        }
        catch (Exception e) {
            System.out.println("*" + e.getMessage());
        }
    }

    private static void borrarAlumno() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            Alumnos.borrar(alumno);
            System.out.println("Alumno borrado correctamente.");
        }
        catch (Exception e) {
            System.out.println("*" + e.getMessage());
        }
    }

    private static void mostrarAlumnos() {
        Alumno[] listaAlumnos = Alumnos.get();

        if (listaAlumnos[0] == null) {
            System.out.println("No hay alumnos registrados.");
        } else {
            System.out.println("Alumnos registrados:");
            for (Alumno alumno : listaAlumnos) {
                if (alumno != null) {
                    System.out.println(alumno);
                }
            }
        }
    }

    private static void insertarAsignatura() {
        try {
            System.out.println("Datos de la asignatura");
            System.out.println("=============================================================================================");
            Asignatura asignatura = Consola.leerAsignatura();
            Asignaturas.insertar(asignatura);
            System.out.println("Asignatura insertada correctamente.");
        }
        catch (Exception e) {
            System.out.println("*" + e.getMessage());
        }
    }

    private static void buscarAsignatura() {
        try {
            Asignatura asignatura = Consola.getAsignaturaPorCodigo();
            asignatura = Asignaturas.buscar(asignatura);
            System.out.println();

            if (asignatura != null) {
                System.out.println("Asignatura encontrada:");
                System.out.println(asignatura);
            } else {
                System.out.println("Asignatura no encontrada.");
            }
        }
        catch (Exception e) {
            System.out.println("*" + e.getMessage());
        }
    }

    private static void borrarAsignatura() {
        try {
            Asignatura asignatura = Consola.getAsignaturaPorCodigo();
            Asignaturas.borrar(asignatura);
            System.out.println();
            System.out.println("Asignatura borrada correctamente.");
        }
        catch (Exception e) {
            System.out.println("*" + e.getMessage());
        }
    }

    private static void mostrarAsignaturas() {
        Asignatura[] listaAsignaturas = Asignaturas.get();

        if (listaAsignaturas[0] == null) {
            System.out.println("No hay asignaturas registradas.");
        } else {
            System.out.println("Asignaturas registradas:");
            for (Asignatura asignatura : listaAsignaturas) {
                if (asignatura != null) {
                    System.out.println(asignatura);
                }
            }
        }
    }

    private static void insertarCicloFormativo() {
        try {
            System.out.println("Datos del ciclo formativo");
            System.out.println("=============================================================================================");
            CicloFormativo ciclo = Consola.leerCicloFormativo();
            CiclosFormativos.insertar(ciclo);
            System.out.println("Ciclo formativo insertado correctamente.");
        }
        catch (Exception e) {
            System.out.println("*" + e.getMessage());
        }
    }

    private static void buscarCicloFormativo() {
        try {
            CicloFormativo cicloFormativo = Consola.getCicloFormativoPorCodigo();
            cicloFormativo = CiclosFormativos.buscar(cicloFormativo);
            System.out.println();

            if (cicloFormativo != null) {
                System.out.println("Ciclo formativo encontrado:");
                System.out.println(cicloFormativo);
            } else {
                System.out.println("Ciclo formativo no encontrado.");
            }
        }
        catch (Exception e) {
            System.out.println("*" + e.getMessage());
        }
    }

    private static void borrarCicloFormativo() {
        try {
            CicloFormativo cicloFormativo = Consola.getCicloFormativoPorCodigo();
            CiclosFormativos.borrar(cicloFormativo);
            System.out.println();
            System.out.println("Ciclo formativo borrado correctamente.");
        }
        catch (Exception e) {
            System.out.println("*" + e.getMessage());
        }
    }

    private static void mostrarCiclosFormativos() {
        if (ciclosFormativos.getTamano() == 0) {
            System.out.println("No hay ciclos formativos registrados.");
        } else {
            Consola.mostrarCiclosFormativos(ciclosFormativos);
            System.out.println();
        }
    }

    private static void insertarMatricula() {
        try {
            System.out.println("Datos de la matrícula");
            System.out.println("=============================================================================================");
            Matricula matricula = Consola.leerMatricula(alumnos, asignaturas);
            Matriculas.insertar(matricula);
            System.out.println("Matrícula insertada correctamente.");
        }
        catch (Exception e) {
            System.out.println("*" + e.getMessage());
        }
    }

    private static void buscarMatricula() {
        try {
            Matricula matricula = Consola.getMatriculaPorIdentificador();
            matricula = Matriculas.buscar(matricula);
            System.out.println();

            if (matricula != null) {
                System.out.println("Matrícula encontrada:");
                System.out.println(matricula);
            } else {
                System.out.println("No existe ninguna matrícula con ese identificador.");
            }
        }
        catch (Exception e) {
            System.out.println("*" + e.getMessage());
        }
    }

    private static void anularMatricula() {
        try {
            if (matriculas.getTamano() == 0) {
                System.out.println("No hay matrículas registradas para anular.");
                return;
            }

            mostrarMatriculas();
            System.out.println();
            Matricula matricula = Consola.getMatriculaPorIdentificador();
            matricula = Matriculas.buscar(matricula);

            if (matricula != null) {
                LocalDate fechaAnulacion = Consola.leerFecha("Introduce la fecha de anulación");
                matricula.setFechaAnulacion(fechaAnulacion);
                Matriculas.borrar(matricula);
                System.out.println();
                System.out.println("Matrícula anulada correctamente el " + fechaAnulacion + ".");
            } else {
                System.out.println("No existe ninguna matrícula con ese identificador.");
            }
        }
        catch (Exception e) {
            System.out.println("*" + e.getMessage());
        }
    }

    private static void mostrarMatriculas() throws OperationNotSupportedException {
        Matricula[] listaMatriculas = matriculas.get();

        if (listaMatriculas[0] == null) {
            System.out.println("No hay matrículas registradas.");
        } else {
            System.out.println("Matrículas registradas:");
            for (Matricula matricula : listaMatriculas) {
                if (matricula != null)
                    System.out.println(matricula);
            }
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

            Matricula[] matriculasAlumno = matriculas.get(alumno);

            if (matriculasAlumno[0] == null) {
                System.out.println("El alumno no tiene matrículas registradas.");
            } else {
                System.out.println("Matrículas del alumno " + alumno.getNombre() + ":");
                for (Matricula matricula : matriculasAlumno) {
                    if (matricula != null)
                        System.out.println(matricula);
                }
            }
        } catch (Exception e) {
            System.out.println("*" + e.getMessage());
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

            Matricula[] matriculasCiclo = matriculas.get(cicloFormativo);

            if (matriculasCiclo[0] == null) {
                System.out.println("El ciclo formativo no tiene matrículas registradas.");
            } else {
                System.out.println();
                System.out.println("Matrículas del ciclo formativo " + cicloFormativo.getNombre() + ":");
                for (Matricula matricula : matriculasCiclo) {
                    if (matricula != null) {
                        System.out.println(matricula);
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println("*" + e.getMessage());
        }
    }

    private static void mostrarMatriculasPorCursoAcademico() {
        try {
            System.out.println("Introduce el curso academico:");
            String cursoAcademico = Entrada.cadena();

            Matricula[] matriculasCurso = matriculas.get(cursoAcademico);

            if (matriculasCurso[0] == null) {
                System.out.println("No hay matrículas registradas para el curso académico " + cursoAcademico + ".");
            } else {
                System.out.println();
                System.out.println("Matrículas registradas para el curso académico " + cursoAcademico + ":");
                for (Matricula matricula : matriculasCurso) {
                    if (matricula != null) {
                        System.out.println(matricula);
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println("*" + e.getMessage());
        }
    }
}

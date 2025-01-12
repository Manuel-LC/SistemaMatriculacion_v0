package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.dominio.*;
import org.iesalandalus.programacion.matriculacion.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.negocio.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.negocio.Asignaturas;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Consola {

    // Constructor privado para evitar instanciación
    private Consola() {
    }

    // Mostrar menú basado en las opciones del enumerado Opcion
    public static void mostrarMenu() {
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion);
        }
    }

    // Elegir una opción del enumerado
    public static Opcion elegirOpcion() {
        int opcion;

        do {
            System.out.print("Elige una opción: ");
            opcion = Entrada.entero();
            System.out.println();
        } while (opcion < 0 || opcion > Opcion.values().length);

        return Opcion.values()[opcion];
    }

    // Leer datos de un alumno
    public static Alumno leerAlumno() {

        System.out.print("Introduce el DNI del alumno: ");
        String dni = Entrada.cadena();
        System.out.print("Introduce el nombre del alumno: ");
        String nombre = Entrada.cadena();
        System.out.print("Introduce el correo del alumno: ");
        String correo = Entrada.cadena();
        System.out.print("Introduce el número de teléfono del alumno: ");
        String telefono = Entrada.cadena();

        LocalDate fechaNacimiento;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.print("Introduce la fecha de nacimiento del alumno: ");
        String fecha = Entrada.cadena();
        System.out.println();

        fechaNacimiento = LocalDate.parse(fecha, formatter);

        return new Alumno(nombre, dni, correo, telefono, fechaNacimiento);
    }

    // Leer un alumno por DNI
    public static Alumno getAlumnoPorDni() {
        System.out.print("Introduce el DNI del alumno: ");
        String dni = Entrada.cadena();
        System.out.println();

        return new Alumno("Pablo garcía", dni, "pg123@gmail.com", "123456789", LocalDate.of(2000, 3, 25));
    }

    // Leer una fecha
    public static LocalDate leerFecha(String mensaje) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = null;
        boolean fechaValida = false;

         do {
            System.out.print(mensaje + " (dd/MM/yyyy): ");
            String cad = Entrada.cadena();

            try {
                fecha = LocalDate.parse(cad, formatter);
                fechaValida = true;
            }
            catch (DateTimeParseException e) {
                System.out.println("ERROR: Fecha no válida.");
            }
         } while (!fechaValida);

        return fecha;
    }

    // Leer un grado
    public static Grado leerGrado() {

        for (Grado grado : Grado.values()) {
            System.out.println(grado.imprimir());
        }

        System.out.println("Introduce el grado (0-2): ");
        int opcion = Entrada.entero();

        if (opcion < 0 || opcion > 2) {
            throw new IllegalArgumentException("ERROR: Opción incorrecta.");
        }

        return Grado.values()[opcion];
    }

    // Leer un ciclo formativo
    public static CicloFormativo leerCicloFormativo() {
        System.out.print("Introduce el código del ciclo formativo (4 dígitos): ");
        int codigo = Entrada.entero();
        System.out.print("Introduce el nombre del ciclo formativo: ");
        String nombre = Entrada.cadena();
        System.out.print("Introduce la familia profesional del ciclo formativo: ");
        String familiaProfesional = Entrada.cadena();
        System.out.print("Introduce las horas del ciclo formativo (máx. 2000): ");
        int horas = Entrada.entero();
        Grado grado = leerGrado();
        System.out.println();

        return new CicloFormativo(codigo, familiaProfesional, grado, nombre, horas);
    }

    // Mostrar ciclos formativos
    public static void mostrarCiclosFormativos(CiclosFormativos ciclosFormativos) {
        CicloFormativo[] listaCiclos = ciclosFormativos.get();

        System.out.println("Lista de ciclos formativos disponibles:");
        for (CicloFormativo cicloFormativo : listaCiclos) {
            if (cicloFormativo != null) {
                System.out.println(cicloFormativo);
            }
        }
    }

    // Obtener un ciclo formativo por código
    public static CicloFormativo getCicloFormativoPorCodigo() {
        System.out.print("Introduce el código del ciclo formativo: ");
        int codigo = Entrada.entero();

        return new CicloFormativo(codigo, "Informática y Comunicaciones", Grado.GDCFGS, "DAW", 1300);
    }

    // Leer un curso
    public static Curso leerCurso() {

        for (Curso curso : Curso.values()) {
            System.out.println(curso.imprimir());
        }

        System.out.println("Introduce el curso (0 ó 1):");
        int opcion = Entrada.entero();

        if (opcion < 0 || opcion > 1) {
            throw new IllegalArgumentException("ERROR: Opción incorrecta.");
        }

        return Curso.values()[opcion];
    }

    // Leer especialidad del profesorado
    public static EspecialidadProfesorado leerEspecialidadProfesorado() {

        for (EspecialidadProfesorado especialidad : EspecialidadProfesorado.values()) {
            System.out.println(especialidad.imprimir());
        }

        System.out.println("Introduce la especialidad del profesorado (0-2):");
        int opcion = Entrada.entero();

        if (opcion < 0 || opcion > 2) {
            throw new IllegalArgumentException("ERROR: Opción incorrecta.");
        }

        return EspecialidadProfesorado.values()[opcion];
    }

    // Leer una asignatura
    public static Asignatura leerAsignatura() {
        System.out.print("Introduce el código de la asignatura (4 dígitos): ");
        String codigo = Entrada.cadena();
        System.out.print("Introduce el nombre de la asignatura: ");
        String nombre = Entrada.cadena();
        System.out.print("Introduce las horas anuales para la asignatura (máx. 300): ");
        int horasAnuales = Entrada.entero();
        System.out.print("Introduce las horas desdoble para la asignatura (máx. 6): ");
        int horasDesdoble = Entrada.entero();
        Curso curso = leerCurso();
        EspecialidadProfesorado especialidadProfesorado = leerEspecialidadProfesorado();

        CicloFormativo cicloFormativo = getCicloFormativoPorCodigo();
        cicloFormativo = CiclosFormativos.buscar(cicloFormativo);
        System.out.println();

        return new Asignatura(codigo, nombre, horasAnuales, curso, horasDesdoble, especialidadProfesorado, cicloFormativo);
    }

    // Obtener una asignatura por código
    public static Asignatura getAsignaturaPorCodigo() {
        System.out.print("Introduce el código de la asignatura: ");
        String codigo = Entrada.cadena();

        return new Asignatura(codigo, "Programación", 300, Curso.PRIMERO, 4, EspecialidadProfesorado.INFORMATICA, new CicloFormativo(1111, "Informática y comunicaciones", Grado.GDCFGM, "DAW", 1000));
    }

    // Mostrar asignaturas
    private static void mostrarAsignaturas(Asignaturas asignaturas) {
        Asignatura[] listaAsignaturas = asignaturas.get();

        System.out.println("Lista de asignaturas disponibles:");
        for (Asignatura asignatura : listaAsignaturas) {
            if (asignatura != null) {
                System.out.println(asignatura);
            }
        }
    }

    // Comprobar si la asignatura ya está matriculada
    private static boolean asignaturaYaMatriculada(Asignatura[] asignaturasMatricula, Asignatura asignatura) {
        boolean encontrada = false;

        for (Asignatura asignaturaMatriculada : asignaturasMatricula) {
            if (asignaturaMatriculada.equals(asignatura)) {
                encontrada = true;
                break;
            }
        }
        return encontrada;
    }

    // Leer una matrícula
    public static Matricula leerMatricula(Alumnos alumnos, Asignaturas asignaturas) throws OperationNotSupportedException {
        System.out.print("Introduce el identificador de la matrícula: ");
        int idMatricula = Entrada.entero();
        System.out.print("Introduce el curso académico: ");
        String cursoAcademico = Entrada.cadena();
        LocalDate fechaMatriculacion = leerFecha("Introduce la fecha de matriculación");

        Alumno alumno = getAlumnoPorDni();
        alumno = Alumnos.buscar(alumno);

        // Añadir asignaturas a la matrícula
        Asignatura[] asignaturasMatricula = new Asignatura[asignaturas.getCapacidad()];

        int opcion;
        do {
            mostrarAsignaturas(asignaturas);
            System.out.println();
            Asignatura asignatura = getAsignaturaPorCodigo();
            asignatura = Asignaturas.buscar(asignatura);

            // Buscar índice
            int indice = -1;
            boolean encontrado = false;

            for (int i = 0; i < asignaturasMatricula.length && !encontrado; i++) {
                if (asignaturasMatricula[i] == null || asignaturasMatricula[i].equals(asignatura)) {
                    encontrado = true;
                    indice = i;
                }
            }

            // Insertar asignatura en la matrícula
            if (indice != -1) {
                asignaturasMatricula[indice] = asignatura;
                if (asignaturaYaMatriculada(asignaturasMatricula, asignatura)) {
                    System.out.println();
                    System.out.println("Asignatura añadida.");
                    System.out.println();
                    System.out.println("¿Quieres añadir otra asignatura?");
                    System.out.println("0.- No");
                    System.out.println("1.- Si");
                    opcion = Entrada.entero();
                } else {
                    throw new OperationNotSupportedException("ERROR: Esa asignatura ya está matriculada.");
                }
            } else {
                throw new OperationNotSupportedException("ERROR: No se aceptan más asignaturas");
            }

        } while (opcion == 1);

        System.out.println();

        return new Matricula(idMatricula, cursoAcademico, fechaMatriculacion, alumno, asignaturasMatricula);
    }

    // Obtener una matrícula por identificador
    public static Matricula getMatriculaPorIdentificador() throws OperationNotSupportedException {
        System.out.print("Introduce el identificador de la matrícula: ");
        int idMatricula = Entrada.entero();

        return new Matricula(idMatricula, "24-25", LocalDate.now().minusDays(10), new Alumno("Antonio rodríguez cuenca", "77241354V", "arc123@hotmail.com", "676873431", LocalDate.of(1999, 6, 14)), new Asignatura[3]);
    }
}

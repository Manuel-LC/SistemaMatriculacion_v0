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
        System.out.print("Introduce la fecha de nacimiento del alumno: ");
        LocalDate fechaNacimiento = LocalDate.parse(Entrada.cadena());

        try {
            return new Alumno(nombre, dni, correo, telefono,  fechaNacimiento);
        }
        catch (IllegalArgumentException e) {
            System.out.println("ERROR: No se ha podido crear el alumno: " + e.getMessage());
            throw e;
        }
    }

    // Leer un alumno por DNI
    public static Alumno getAlumnoPorDni() {
        System.out.print("Introduce el DNI del alumno: ");
        String dni = Entrada.cadena();

        try {
            return new Alumno("Pablo garcía", dni, "pg123@gmail.com", "123456789", LocalDate.of(2000, 3, 25));
        }
        catch (IllegalArgumentException e) {
            System.out.println("ERROR: No se ha podido crear el alumno: " + e.getMessage());
            throw e;
        }
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

        System.out.println("Introduce el grado: ");
        int opcion = Entrada.entero();

        return Grado.values()[opcion];
    }

    // Leer un ciclo formativo
    public static CicloFormativo leerCicloFormativo() {
        System.out.print("Introduce el código del ciclo formativo: ");
        int codigo = Entrada.entero();
        System.out.print("Introduce el nombre del ciclo formativo: ");
        String nombre = Entrada.cadena();
        System.out.print("Introduce la familia profesional del ciclo formativo: ");
        String familiaProfesional = Entrada.cadena();
        System.out.print("Introduce las horas del ciclo formativo: ");
        int horas = Entrada.entero();

        try {
            return new CicloFormativo(codigo, familiaProfesional, leerGrado(), nombre, horas);
        }
        catch (IllegalArgumentException e) {
            System.out.println("ERROR: El ciclo formativo no existe o no es válido: " + e.getMessage());
            throw e;
        }
    }

    // Mostrar ciclos formativos
    public static void mostrarCiclosFormativos(CiclosFormativos ciclosFormativos) {
        for (CicloFormativo cicloFormativo : CiclosFormativos.get()) {
            System.out.println(cicloFormativo);
        }
    }

    // Obtener un ciclo formativo por código
    public static CicloFormativo getCicloFormativoPorCodigo() {
        System.out.print("Introduce el código del ciclo formativo: ");
        int codigo = Entrada.entero();

        try {
            return new CicloFormativo(codigo, "Informática y Comunicaciones", Grado.GDCFGS, "DAW", 1300);
        }
        catch (IllegalArgumentException e) {
            System.out.println("ERROR: No se ha podido crear el ciclo formativo: " + e.getMessage());
            throw e;
        }
    }

    // Leer un curso
    public static Curso leerCurso() {

        for (Curso curso : Curso.values()) {
            System.out.println(curso.imprimir());
        }

        System.out.println("Introduce el curso:");
        int opcion = Entrada.entero();

        return Curso.values()[opcion];
    }

    // Leer especialidad del profesorado
    public static EspecialidadProfesorado leerEspecialidadProfesorado() {

        for (EspecialidadProfesorado especialidad : EspecialidadProfesorado.values()) {
            System.out.println(especialidad.imprimir());
        }

        System.out.println("Introduce la especialidad del profesorado:");
        int opcion = Entrada.entero();

        return EspecialidadProfesorado.values()[opcion];
    }

    // Leer una asignatura
    public static Asignatura leerAsignatura() {
        System.out.print("Introduce el código de la asignatura: ");
        String codigo = Entrada.cadena();
        System.out.print("Introduce el nombre de la asignatura: ");
        String nombre = Entrada.cadena();
        System.out.print("Introduce el nombre de la asignatura: ");
        int horasAnuales = Entrada.entero();
        System.out.print("Introduce el nombre de la asignatura: ");
        int horasDesdoble = Entrada.entero();

        try {
            return new Asignatura(codigo, nombre, horasAnuales, leerCurso(), horasDesdoble, leerEspecialidadProfesorado(), leerCicloFormativo());
        }
        catch (IllegalArgumentException e) {
            System.out.println("ERROR: No se ha podido crear la asignatura: " + e.getMessage());
            throw e;
        }
    }

    // Obtener una asignatura por código
    public static Asignatura getAsignaturaPorCodigo() {
        System.out.print("Introduce el código de la asignatura: ");
        String codigo = Entrada.cadena();

        try {
            return new Asignatura(codigo, "Programación", 300, Curso.PRIMERO, 4, EspecialidadProfesorado.INFORMATICA, getCicloFormativoPorCodigo());
        }
        catch (IllegalArgumentException e) {
            System.out.println("ERROR: No se ha podido crear la asignatura: " + e.getMessage());
            throw e;
        }
    }

    // Mostrar asignaturas
    private static void mostrarAsignaturas(Asignaturas asignaturas) {
        for (Asignatura asignatura : Asignaturas.get()) {
            System.out.println(asignatura);
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
    public static Matricula leerMatricula(Alumnos alumnos, Asignaturas asignaturas) throws  OperationNotSupportedException {
        System.out.print("Introduce el identificador de la matrícula: ");
        int idMatricula = Entrada.entero();
        System.out.print("Introduce el curso académico: ");
        String cursoAcademico = Entrada.cadena();
        LocalDate fechaMatriculacion = leerFecha("Introduce la fecha de matriculación");

        Alumno alumno = getAlumnoPorDni();
        Asignatura[] asignaturasMatricula = new Asignatura[asignaturas.getCapacidad()];

        try {
            return new Matricula(idMatricula, cursoAcademico, fechaMatriculacion, alumno, asignaturasMatricula);
        }
        catch (IllegalArgumentException e) {
            System.out.println("Error al crear la matrícula: " + e.getMessage());
            throw e;
        }
        catch (OperationNotSupportedException o) {
            System.out.println("ERROR: Las horas de las asignaturas superan el máximo para la matrícula: " + o.getMessage());
            throw o;
        }
    }

    // Obtener una matrícula por identificador
    public static Matricula getMatriculaPorIdentificador() throws OperationNotSupportedException {
        System.out.print("Introduce el identificador de la matrícula: ");
        int idMatricula = Entrada.entero();

        try {
            return new Matricula(idMatricula, "24-25", LocalDate.now().minusDays(10), new Alumno("Antonio rodríguez cuenca", "46685429G", "arc123@hotmail.com", "676873431", LocalDate.of(1999, 6, 14)), new Asignatura[6]);
        }
        catch (IllegalArgumentException e) {
            System.out.println("Error al crear la matrícula ficticia: " + e.getMessage());
            throw e;
        }
        catch (OperationNotSupportedException o) {
            System.out.println("ERROR: Las horas de las asignaturas superan el máximo para la matrícula: " + o.getMessage());
            throw o;
        }
    }
}

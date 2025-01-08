package org.iesalandalus.programacion.matriculacion.negocio;


import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.dominio.Matricula;

import javax.naming.OperationNotSupportedException;

public class Matriculas {

    private static int capacidad;
    private static int tamano;
    private static Matricula[] coleccionMatriculas;

    public Matriculas(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }

        Matriculas.capacidad = capacidad;
        tamano = 0;
        coleccionMatriculas = new Matricula[capacidad];
    }

    public static Matricula[] get() throws OperationNotSupportedException {
        return copiaProfundaMatriculas();
    }

    private static Matricula[] copiaProfundaMatriculas() {
        Matricula[] copiaMatriculas = new Matricula[capacidad];

        for (int i = 0; i < capacidad; i++) {
            if (coleccionMatriculas[i] != null) {
                copiaMatriculas[i] = new Matricula(coleccionMatriculas[i]);
            }
        }

        return copiaMatriculas;
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public static void insertar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede insertar una matrícula nula.");
        }
        if (buscar(matricula) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe una matrícula con ese identificador.");
        }

        int i = buscarIndice(matricula);

        if (i != -1) {
            coleccionMatriculas[i] = matricula;
            tamano++;
        } else {
            throw new OperationNotSupportedException("ERROR: No se aceptan más matrículas.");
        }
    }

    private static int buscarIndice(Matricula matricula) {
        int indice = -1;
        boolean encontrado = false;

        for (int i = 0; i < capacidad && !encontrado; i++) {
            if (coleccionMatriculas[i] == null || coleccionMatriculas[i].equals(matricula)) {
                encontrado = true;
                indice = i;
            }
        }

        return indice;
    }

    private boolean tamanoSuperado(int indice) {
        return indice >= tamano;
    }

    private boolean capacidadSuperada(int indice) {
        return indice >= capacidad;
    }

    public static Matricula buscar(Matricula matricula) {
        int i;
        boolean encontrado = false;

        for(i = 0; i < capacidad && !encontrado; i++)
        {
            if (coleccionMatriculas[i] != null && coleccionMatriculas[i].equals(matricula)) {
                encontrado = true;
            }
        }

        if (encontrado == true) {
            return copiaProfundaMatriculas()[i - 1];
        } else {
            return null;
        }
    }

    public static void borrar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede borrar una matrícula nula.");
        }

        int i = buscarIndice(matricula);
        if (matricula.getIdMatricula() == coleccionMatriculas[i].getIdMatricula()) {
            desplazarUnaPosicionHaciaIzquierda(i);
            tamano--;
        } else {
            throw new OperationNotSupportedException("ERROR: No existe ninguna matrícula como la indicada.");
        }
    }

    private static void desplazarUnaPosicionHaciaIzquierda(int indice) {
        int i;

        for (i = indice; i < capacidad - 1 && coleccionMatriculas[i] != null; i++) {
            coleccionMatriculas[i] = coleccionMatriculas[i + 1];
        }
        coleccionMatriculas[i] = null;
    }

    public static Matricula[] get(Alumno alumno) throws OperationNotSupportedException {
        Matricula[] coleccionMatriculasAlumno = get();

        for (int i = 0; i < capacidad; i++) {
            if (coleccionMatriculas[i] != null && coleccionMatriculas[i].getAlumno().equals(alumno)) {
                coleccionMatriculasAlumno[i] = coleccionMatriculas[i];
            }
        }
        return coleccionMatriculasAlumno;
    }

    public static Matricula[] get(String cursoAcademico) throws OperationNotSupportedException {
        Matricula[] coleccionMatriculasCurso = get();

        for (int i = 0; i < capacidad; i++) {
            if (coleccionMatriculas[i] != null && coleccionMatriculas[i].getCursoAcademico().equals(cursoAcademico)) {
                coleccionMatriculasCurso[i] = coleccionMatriculas[i];
            }
        }
        return coleccionMatriculasCurso;
    }

    public static Matricula[] get(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        Matricula[] coleccionMatriculasCiclo = get();
        int indice = 0;
        Asignatura[] asignaturasMatricula = Matricula.getColeccionAsignaturas();

        if (cicloFormativo == null) {
            throw new IllegalArgumentException("ERROR: El ciclo formativo no puede ser nulo.");
        }

        for (int i = 0; i < capacidad; i++) {
            if (coleccionMatriculas[i] != null) {
                if (asignaturasMatricula[i] != null && asignaturasMatricula[i].getCicloFormativo().equals(cicloFormativo)) {
                    coleccionMatriculasCiclo[indice++] = coleccionMatriculas[i];
                }

            }
        }

        return coleccionMatriculasCiclo;
    }
}

package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;

import javax.naming.OperationNotSupportedException;

public class Asignaturas {

    private static int capacidad;
    private static int tamano;
    private static Asignatura[] coleccionAsignaturas;

    public Asignaturas(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }

        Asignaturas.capacidad = capacidad;
        tamano = 0;
        coleccionAsignaturas = new Asignatura[capacidad];
    }

    public static Asignatura[] get() {
        return copiaProfundaAsignaturas();
    }

    private static Asignatura[] copiaProfundaAsignaturas() {
        Asignatura[] copiaAsignaturas = new Asignatura[capacidad];

        for (int i = 0; i < capacidad; i++) {
            if (coleccionAsignaturas[i] != null) {
                copiaAsignaturas[i] = new Asignatura(coleccionAsignaturas[i]);
            }
        }

        return copiaAsignaturas;
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public static void insertar(Asignatura asignatura) throws OperationNotSupportedException {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede insertar una asignatura nula.");
        }
        if (buscar(asignatura) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe una asignatura con ese código.");
        }

        int i = buscarIndice(asignatura);

        if (i != -1) {
            coleccionAsignaturas[i] = asignatura;
            tamano++;
        } else {
            throw new OperationNotSupportedException("ERROR: No se aceptan más asignaturas.");
        }
    }

    private static int buscarIndice(Asignatura alumno) {
        int indice = -1;
        boolean encontrado = false;

        for (int i = 0; i < capacidad && !encontrado; i++) {
            if (coleccionAsignaturas[i] == null || coleccionAsignaturas[i].equals(alumno)) {
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

    public static Asignatura buscar(Asignatura cicloFormativo) {
        int i;
        boolean encontrado = false;

        for(i = 0; i < capacidad && !encontrado; i++)
        {
            if (coleccionAsignaturas[i] != null && coleccionAsignaturas[i].equals(cicloFormativo)) {
                encontrado = true;
            }
        }

        if (encontrado == true) {
            return copiaProfundaAsignaturas()[i - 1];
        } else {
            return null;
        }
    }

    public static void borrar(Asignatura asignatura) throws OperationNotSupportedException {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede borrar una asignatura nula.");
        }

        int i = buscarIndice(asignatura);
        if (asignatura.equals(coleccionAsignaturas[i])) {
            desplazarUnaPosicionHaciaIzquierda(i);
            tamano--;
        } else {
            throw new OperationNotSupportedException("ERROR: No existe ninguna asignatura como la indicada.");
        }
    }

    private static void desplazarUnaPosicionHaciaIzquierda(int indice) {
        int i;

        for (i = indice; i < capacidad - 1 && coleccionAsignaturas[i] != null; i++) {
            coleccionAsignaturas[i] = coleccionAsignaturas[i + 1];
        }
        coleccionAsignaturas[i] = null;
    }
}

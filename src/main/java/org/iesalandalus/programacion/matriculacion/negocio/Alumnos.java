package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;

import javax.naming.OperationNotSupportedException;

public class Alumnos {

    private static int capacidad;
    private static int tamano;
    private static Alumno[] coleccionAlumnos;

    public Alumnos(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }

        Alumnos.capacidad = capacidad;
        tamano = 0;
        coleccionAlumnos = new Alumno[capacidad];
    }

    public static Alumno[] get() {
        return copiaProfundaAlumnos();
    }

    private static Alumno[] copiaProfundaAlumnos() {
        Alumno[] copiaAlumnos = new Alumno[capacidad];

        for (int i = 0; i < tamano; i++) {
            if (coleccionAlumnos[i] != null) {
                copiaAlumnos[i] = new Alumno(coleccionAlumnos[i]);
            }
        }

        return copiaAlumnos;
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public static void insertar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
        }
        if (buscar(alumno) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese dni.");
        }

        int i = buscarIndice(alumno);

        if (i != -1) {
            coleccionAlumnos[i] = alumno;
            tamano++;
        } else {
            throw new OperationNotSupportedException("ERROR: No se aceptan más alumnos.");
        }
    }

    private static int buscarIndice(Alumno alumno) {
        int indice = -1;
        boolean encontrado = false;

        for (int i = 0; i < capacidad && !encontrado; i++) {
            if (coleccionAlumnos[i] == null || coleccionAlumnos[i].equals(alumno)) {
                encontrado = true;
                indice = i;
            }
        }

        return indice;
    }

    private boolean tamanoSuperado(int indice) {
        return indice > tamano;
    }

    private boolean capacidadSuperada(int indice) {
        return indice > capacidad;
    }

    public static Alumno buscar(Alumno alumno) {
        int i;
        boolean encontrado = false;

        for(i = 0; i < capacidad && !encontrado; i++)
        {
            if (coleccionAlumnos[i] != null && coleccionAlumnos[i].equals(alumno)) {
                encontrado = true;
            }
        }

        if (encontrado == true) {
            return copiaProfundaAlumnos()[i - 1];
        } else {
            return null;
        }
    }

    public static void borrar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede borrar un alumno nulo.");
        }

        int i = buscarIndice(alumno);
        if (alumno.equals(coleccionAlumnos[i])) {
            desplazarUnaPosicionHaciaIzquierda(i);
            tamano--;
        } else {
            throw new OperationNotSupportedException("ERROR: No existe ningún alumno como el indicado.");
        }
    }

    private static void desplazarUnaPosicionHaciaIzquierda(int indice) {
        int i;

        for (i = indice; i < capacidad - 1 && coleccionAlumnos[i] != null; i++) {
            coleccionAlumnos[i] = coleccionAlumnos[i + 1];
        }
        coleccionAlumnos[i] = null;
    }
}

package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;

import javax.naming.OperationNotSupportedException;

public class Alumnos {

    private int capacidad;
    private int tamano;
    private static Alumno[] coleccionAlumnos;

    public Alumnos(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }

        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionAlumnos = new Alumno[capacidad];
    }

    public Alumno[] get() {
        return copiaProfundaAlumnos(coleccionAlumnos);
    }

    private Alumno[] copiaProfundaAlumnos(Alumno[] alumnos) {
        Alumno[] copiaAlumnos = new Alumno[capacidad];
        for (int i = 0; i < capacidad; i++) {
            if (alumnos[i] != null) {
                copiaAlumnos[i] = new Alumno(alumnos[i]);
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

    public void insertar(Alumno alumno) throws OperationNotSupportedException {
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

    private int buscarIndice(Alumno alumno) {
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
        return indice >= tamano;
    }

    private boolean capacidadSuperada(int indice) {
        return indice >= capacidad;
    }

    public Alumno buscar(Alumno alumno) {
        int i;
        boolean encontrado = false;

        for(i = 0; i < capacidad && !encontrado; i++)
        {
            if (coleccionAlumnos[i] != null && coleccionAlumnos[i].equals(alumno)) {
                encontrado = true;
            }
        }

        if (encontrado == true) {
            return copiaProfundaAlumnos(coleccionAlumnos)[i - 1];
        } else {
            return null;
        }
    }

    public void borrar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede borrar un alumno nulo.");
        }

        int i = buscarIndice(alumno);
        if (alumno == coleccionAlumnos[i]) {
            desplazarUnaPosicionHaciaIzquierda(i);
            tamano--;
        } else {
            throw new OperationNotSupportedException("ERROR: No existe ningún alumno como el indicado.");
        }
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        int i;

        for (i = indice; i < capacidad - 1 && coleccionAlumnos[i] != null; i++) {
            coleccionAlumnos[i] = coleccionAlumnos[i + 1];
        }
        coleccionAlumnos[i] = null;
    }
}

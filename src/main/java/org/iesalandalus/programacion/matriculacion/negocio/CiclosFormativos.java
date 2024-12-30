package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;

import javax.naming.OperationNotSupportedException;

public class CiclosFormativos {

    private int capacidad;
    private int tamano;
    private static CicloFormativo[] coleccionCiclosFormativos;

    public CiclosFormativos(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }

        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionCiclosFormativos = new CicloFormativo[capacidad];
    }

    public CicloFormativo[] get() {
        return copiaProfundaCiclosFormativos(coleccionCiclosFormativos);
    }

    private CicloFormativo[] copiaProfundaCiclosFormativos(CicloFormativo[] cicloFormativos) {
        CicloFormativo[] copiaCiclosFormativos = new CicloFormativo[capacidad];
        for (int i = 0; i < capacidad; i++) {
            if (cicloFormativos[i] != null) {
                copiaCiclosFormativos[i] = new CicloFormativo(cicloFormativos[i]);
            }
        }

        return copiaCiclosFormativos;
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede insertar un ciclo formativo nulo.");
        }
        if (buscar(cicloFormativo) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe un ciclo formativo con ese código.");
        }

        int i = buscarIndice(cicloFormativo);

        if (i != -1) {
            coleccionCiclosFormativos[i] = cicloFormativo;
            tamano++;
        } else {
            throw new OperationNotSupportedException("ERROR: No se aceptan más ciclos formativos.");
        }
    }

    private int buscarIndice(CicloFormativo alumno) {
        int indice = -1;
        boolean encontrado = false;

        for (int i = 0; i < capacidad && !encontrado; i++) {
            if (coleccionCiclosFormativos[i] == null || coleccionCiclosFormativos[i].equals(alumno)) {
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

    public CicloFormativo buscar(CicloFormativo cicloFormativo) {
        int i;
        boolean encontrado = false;

        for(i = 0; i < capacidad && !encontrado; i++)
        {
            if (coleccionCiclosFormativos[i] != null && coleccionCiclosFormativos[i].equals(cicloFormativo)) {
                encontrado = true;
            }
        }

        if (encontrado == true) {
            return copiaProfundaCiclosFormativos(coleccionCiclosFormativos)[i - 1];
        } else {
            return null;
        }
    }

    public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede borrar un ciclo formativo nulo.");
        }

        int i = buscarIndice(cicloFormativo);
        if (cicloFormativo == coleccionCiclosFormativos[i]) {
            desplazarUnaPosicionHaciaIzquierda(i);
            tamano--;
        } else {
            throw new OperationNotSupportedException("ERROR: No existe ningún ciclo formativo como el indicado.");
        }
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        int i;

        for (i = indice; i < capacidad - 1 && coleccionCiclosFormativos[i] != null; i++) {
            coleccionCiclosFormativos[i] = coleccionCiclosFormativos[i + 1];
        }
        coleccionCiclosFormativos[i] = null;
    }
}

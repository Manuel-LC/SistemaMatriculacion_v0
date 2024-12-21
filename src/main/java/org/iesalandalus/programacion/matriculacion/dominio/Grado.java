package org.iesalandalus.programacion.matriculacion.dominio;

public enum Grado {
    GDCFGB("GDCFGB"), GDCFGM("GDCFGM"), GDCFGS("GDCFGS");

    private String cadenaAMostrar;

    private Grado(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    public String imprimir() {
        return String.format("dígito.-" + cadenaAMostrar);
    }

    @Override
    public String toString() {
        return cadenaAMostrar;
    }
}

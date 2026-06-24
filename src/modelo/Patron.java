package modelo;

import java.io.Serializable;

public class Patron implements Serializable {

    private String codigo;
    private String nombre;
    private int[][] patron;

    public Patron() {
    }

    public Patron(String codigo, String nombre, int[][] patron) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.patron = patron;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int[][] getPatron() {
        return patron;
    }

    public void setPatron(int[][] patron) {
        this.patron = patron;
    }
}
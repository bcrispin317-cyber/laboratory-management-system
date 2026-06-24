package modelo;

import java.io.Serializable;

public class Muestra implements Serializable {

    private String codigo;
    private String descripcion;
    private int[][] patron;
    private String estado;
    private String investigadorAsignado;

    public Muestra() {
    }

    public Muestra(String codigo, String descripcion, int[][] patron, String estado) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.patron = patron;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int[][] getPatron() {
        return patron;
    }

    public void setPatron(int[][] patron) {
        this.patron = patron;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getInvestigadorAsignado() {
        return investigadorAsignado;
    }

    public void setInvestigadorAsignado(String investigadorAsignado) {
        this.investigadorAsignado = investigadorAsignado;
    }
}
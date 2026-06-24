package modelo;

import java.io.Serializable;

public class Investigador implements Serializable {

    private String codigo;
    private String nombre;
    private String genero;
    private int experimentos;
    private String contrasenia;

    public Investigador() {
    }

    public Investigador(String codigo, String nombre, String genero, int experimentos, String contrasenia) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.genero = genero;
        this.experimentos = experimentos;
        this.contrasenia = contrasenia;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getExperimentos() {
        return experimentos;
    }

    public void setExperimentos(int experimentos) {
        this.experimentos = experimentos;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
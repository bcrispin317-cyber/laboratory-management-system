package modelo;

import java.io.Serializable;

public class Resultado implements Serializable {

    private int numero;
    private String codigoMuestra;
    private String codigoPatron;
    private String fecha;
    private String hora;
    private String resultado;

    public Resultado() {
    }

    public Resultado(int numero, String codigoMuestra, String codigoPatron,
            String fecha, String hora, String resultado) {
        this.numero = numero;
        this.codigoMuestra = codigoMuestra;
        this.codigoPatron = codigoPatron;
        this.fecha = fecha;
        this.hora = hora;
        this.resultado = resultado;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCodigoMuestra() {
        return codigoMuestra;
    }

    public void setCodigoMuestra(String codigoMuestra) {
        this.codigoMuestra = codigoMuestra;
    }

    public String getCodigoPatron() {
        return codigoPatron;
    }

    public void setCodigoPatron(String codigoPatron) {
        this.codigoPatron = codigoPatron;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
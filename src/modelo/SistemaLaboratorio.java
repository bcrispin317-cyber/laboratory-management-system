package modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class SistemaLaboratorio implements Serializable {

    private ArrayList<Investigador> investigadores;
    private ArrayList<Muestra> muestras;
    private ArrayList<Patron> patrones;
    private ArrayList<Resultado> resultados;

    public SistemaLaboratorio() {
        investigadores = new ArrayList<>();
        muestras = new ArrayList<>();
        patrones = new ArrayList<>();
        resultados = new ArrayList<>();
    }

    public ArrayList<Investigador> getInvestigadores() {
        return investigadores;
    }

    public void setInvestigadores(ArrayList<Investigador> investigadores) {
        this.investigadores = investigadores;
    }

    public ArrayList<Muestra> getMuestras() {
        return muestras;
    }

    public void setMuestras(ArrayList<Muestra> muestras) {
        this.muestras = muestras;
    }

    public ArrayList<Patron> getPatrones() {
        return patrones;
    }

    public void setPatrones(ArrayList<Patron> patrones) {
        this.patrones = patrones;
    }

    public ArrayList<Resultado> getResultados() {
        return resultados;
    }

    public void setResultados(ArrayList<Resultado> resultados) {
        this.resultados = resultados;
    }
}
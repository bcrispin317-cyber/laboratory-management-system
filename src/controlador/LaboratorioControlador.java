package controlador;

import modelo.SistemaLaboratorio;
import util.Serializador;

public class LaboratorioControlador {

    private final SistemaLaboratorio sistema;

    public LaboratorioControlador() {
        sistema = Serializador.cargar();
    }

    public SistemaLaboratorio getSistema() {
        return sistema;
    }

    public void guardarDatos() {
        Serializador.guardar(sistema);
    }
}
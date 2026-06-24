package util;

import java.io.*;
import modelo.SistemaLaboratorio;

public class Serializador {

    private static final String ARCHIVO = "laboratorio.dat";

    public static void guardar(SistemaLaboratorio sistema) {
        try {
            ObjectOutputStream salida =
                    new ObjectOutputStream(new FileOutputStream(ARCHIVO));

            salida.writeObject(sistema);
            salida.close();

        } catch (Exception e) {
            System.out.println("Error al guardar");
        }
    }

    public static SistemaLaboratorio cargar() {

        try {
            ObjectInputStream entrada =
                    new ObjectInputStream(new FileInputStream(ARCHIVO));

            SistemaLaboratorio sistema =
                    (SistemaLaboratorio) entrada.readObject();

            entrada.close();

            return sistema;

        } catch (Exception e) {
            return new SistemaLaboratorio();
        }
    }
}
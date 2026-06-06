package asignacionasientosvuelo;

public class AsignacionAsientosVuelo {

    static char[][] cabina = new char[20][6];

    public static void inicializarCabina(char[][] cabina) {

        for (int fila = 0; fila < 20; fila++) {

            for (int columna = 0; columna < 6; columna++) {

                cabina[fila][columna] = 'L';

            }
        }
    }

    public static void mostrarCabina(char[][] cabina) {

        for (int fila = 0; fila < 20; fila++) {

            for (int columna = 0; columna < 6; columna++) {

                System.out.print(cabina[fila][columna] + " ");

            }

            System.out.println();
        }
    }

    public static void main(String[] args) {

        inicializarCabina(cabina);
        mostrarCabina(cabina);

    }
}
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

        System.out.println("ASIGNACION DE ASIENTOS");
        System.out.println();

        System.out.println("      A B C || D E F");
        System.out.println();

        for (int fila = 0; fila < 20; fila++) {

            System.out.printf("%2d    ", fila + 1);

            for (int columna = 0; columna < 6; columna++) {

                if (columna == 3) {
                    System.out.print("|| ");
                }

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
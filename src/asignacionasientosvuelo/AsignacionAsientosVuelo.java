package asignacionasientosvuelo;

import java.util.Scanner;

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

    public static int obtenerColumna(char letra) {

        switch (Character.toUpperCase(letra)) {

            case 'A':
                return 0;

            case 'B':
                return 1;

            case 'C':
                return 2;

            case 'D':
                return 3;

            case 'E':
                return 4;

            case 'F':
                return 5;

            default:
                return -1;
        }
    }

    public static int obtenerFila(int numeroFila) {

        return numeroFila - 1;

    }

    public static boolean esVIP(int numeroFila) {

        return numeroFila >= 1 && numeroFila <= 5;

    }

    public static void ocuparAsiento(char letra, int numeroFila) {

        int columna = obtenerColumna(letra);

        if (columna == -1) {

            System.out.println("Error: columna invalida.");
            return;

        }

        if (numeroFila < 1 || numeroFila > 20) {

            System.out.println("Error: fila invalida.");
            return;

        }

        int fila = obtenerFila(numeroFila);

        if (cabina[fila][columna] == 'L') {

            cabina[fila][columna] = 'X';

            if (esVIP(numeroFila)) {

                System.out.println("Asiento VIP reservado correctamente.");

                if (fila > 0) {
                    cabina[fila - 1][columna] = 'B';
                }

                if (fila < 19) {
                    cabina[fila + 1][columna] = 'B';
                }

            } else {

                System.out.println("Asiento economico reservado correctamente.");

            }

        } else {

            System.out.println("El asiento ya esta ocupado.");

        }
    }

    public static void main(String[] args) {

        inicializarCabina(cabina);

        Scanner teclado = new Scanner(System.in);

        char continuar;

        do {

            System.out.print("Ingrese la columna (A-F): ");
            char letra = teclado.next().charAt(0);

            System.out.print("Ingrese la fila (1-20): ");
            int numeroFila = teclado.nextInt();

            ocuparAsiento(letra, numeroFila);

            mostrarCabina(cabina);

            System.out.print("Desea reservar otro asiento? (S/N): ");
            continuar = teclado.next().toUpperCase().charAt(0);

        } while (continuar == 'S');

    }
}
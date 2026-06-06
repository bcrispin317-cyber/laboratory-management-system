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

    public static boolean puedeReservarVIP(int fila, int columna) {

        if (fila > 0 && cabina[fila - 1][columna] != 'L') {
            return false;
        }

        if (fila < 19 && cabina[fila + 1][columna] != 'L') {
            return false;
        }

        return true;
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

        if (cabina[fila][columna] != 'L') {

            System.out.println("El asiento ya esta ocupado.");
            return;

        }

        if (esVIP(numeroFila)) {

            if (!puedeReservarVIP(fila, columna)) {

                System.out.println("No es posible reservar este asiento VIP.");
                return;

            }

            cabina[fila][columna] = 'X';

            if (fila > 0) {
                cabina[fila - 1][columna] = 'B';
            }

            if (fila < 19) {
                cabina[fila + 1][columna] = 'B';
            }

            System.out.println("Asiento VIP reservado correctamente.");

        } else {

            cabina[fila][columna] = 'X';

            System.out.println("Asiento economico reservado correctamente.");

        }
    }

    public static void main(String[] args) {

      inicializarCabina(cabina);

      Scanner teclado = new Scanner(System.in);

      int opcion;

      do {

          System.out.println("\n--- AERO-USAC: SISTEMA DE ABORDAJE ---");
          System.out.println("1. Venta de Boleto Individual");
          System.out.println("2. Buscar Boletos Contiguos");
          System.out.println("3. Asignacion Automatica");
          System.out.println("4. Mostrar Mapa de la Cabina");
          System.out.println("5. Reporte de Vuelo");
          System.out.println("6. Salir");
          System.out.print("Seleccione una opcion: ");

          opcion = teclado.nextInt();

            switch (opcion) {

               case 1:

                  System.out.print("Ingrese la columna (A-F): ");
                  char letra = teclado.next().charAt(0);

                  System.out.print("Ingrese la fila (1-20): ");
                  int numeroFila = teclado.nextInt();

                  ocuparAsiento(letra, numeroFila);

                break;

                case 2:

                  System.out.println("Funcion pendiente.");

                break;

                case 3:

                  System.out.println("Funcion pendiente.");

                break;

                case 4:

                  mostrarCabina(cabina);

                break;

                case 5:

                  System.out.println("Funcion pendiente.");

                break;

                case 6:

                  System.out.println("Saliendo del sistema...");

                break;

                default:

                  System.out.println("Opcion invalida.");

            }

        } while (opcion != 6);

    }
}
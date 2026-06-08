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

        System.out.println("\n____________ MAPA DE LA CABINA ___________\n");

        System.out.println("     A   B   C   ||  D   E   F");
        System.out.println();

        for (int fila = 0; fila < 20; fila++) {

          System.out.printf("%2d  ", fila + 1);

            for (int columna = 0; columna < 6; columna++) {

                if (columna == 3) {
                  System.out.print(" || ");
                }

                System.out.print("[" + cabina[fila][columna] + "] ");
            }

            System.out.println();
        }

      System.out.println("\nL = Libre | X = Ocupado | B = Bloqueado\n");
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
    
    public static void buscarBoletosContiguos(int clase) {

      int filaInicio;
      int filaFin;
      int precio;

        if (clase == 1) {

          filaInicio = 0;
          filaFin = 4;
          precio = 150;

        } else {

          filaInicio = 5;
          filaFin = 19;
          precio = 50;

        }

        for (int fila = filaInicio; fila <= filaFin; fila++) {

            if (cabina[fila][0] == 'L' && cabina[fila][1] == 'L') {

              cabina[fila][0] = 'X';
              cabina[fila][1] = 'X';

              System.out.println("Asientos contiguos asignados: A" + (fila + 1)
                    + " y B" + (fila + 1));
              System.out.println("Total a pagar: $" + (precio * 2));
              return;

            }

            if (cabina[fila][1] == 'L' && cabina[fila][2] == 'L') {

              cabina[fila][1] = 'X';
              cabina[fila][2] = 'X';

              System.out.println("Asientos contiguos asignados: B" + (fila + 1)
                    + " y C" + (fila + 1));
              System.out.println("Total a pagar: $" + (precio * 2));
              return;

            }

            if (cabina[fila][3] == 'L' && cabina[fila][4] == 'L') {

               cabina[fila][3] = 'X';
               cabina[fila][4] = 'X';

               System.out.println("Asientos contiguos asignados: D" + (fila + 1)
                    + " y E" + (fila + 1));
               System.out.println("Total a pagar: $" + (precio * 2));
               return;

            }

            if (cabina[fila][4] == 'L' && cabina[fila][5] == 'L') {

               cabina[fila][4] = 'X';
               cabina[fila][5] = 'X';

               System.out.println("Asientos contiguos asignados: E" + (fila + 1)
                    + " y F" + (fila + 1));
               System.out.println("Total a pagar: $" + (precio * 2));
               return;

            }

        }

        System.out.println("No se encontraron 2 asientos contiguos disponibles.");

    }
    
    public static void asignacionAutomatica(int clase) {

     int izquierda = 0;
     int derecha = 0;

        for (int fila = 0; fila < 20; fila++) {

           for (int columna = 0; columna < 3; columna++) {

                if (cabina[fila][columna] == 'X') {
                  izquierda++;
                }

            }

            for (int columna = 3; columna < 6; columna++) {

                if (cabina[fila][columna] == 'X') {
                 derecha++;
                }

            }

        }

        int filaInicio;
        int filaFin;
        int precio;

        if (clase == 1) {

           filaInicio = 0;
           filaFin = 4;
           precio = 150;

        } else {

          filaInicio = 5;
          filaFin = 19;
          precio = 50;

        }

        boolean buscarIzquierda;

        if (izquierda < derecha) {

         buscarIzquierda = true;

        } else if (derecha < izquierda) {

         buscarIzquierda = false;

        } else {

         System.out.println("   - Ambos lados se encuentran balanceados -  ");
         buscarIzquierda = true;

        }

        if (buscarIzquierda) {

            for (int fila = filaInicio; fila <= filaFin; fila++) {

                for (int columna = 0; columna < 3; columna++) {

                    if (cabina[fila][columna] == 'L') {

                      cabina[fila][columna] = 'X';

                      char letra = (char) ('A' + columna);

                      System.out.println("Se le ha asignado el asiento "
                            + letra + (fila + 1));
                      System.out.println("Total a pagar: $" + precio);

                      return;
                    }

                }

            }

        } else {

            for (int fila = filaInicio; fila <= filaFin; fila++) {

                for (int columna = 3; columna < 6; columna++) {

                    if (cabina[fila][columna] == 'L') {

                      cabina[fila][columna] = 'X';

                      char letra = (char) ('A' + columna);

                      System.out.println(" - Asiento asignado automaticamente con exito:  "
                            + letra + (fila + 1));
                      System.out.println("Total a pagar: $" + precio);

                      return;
                    }

                }

            }

        }

        System.out.println("No hay asientos disponibles en la clase seleccionada.");

    }

    public static void ocuparAsiento(char letra, int numeroFila) {

        int columna = obtenerColumna(letra);

        if (columna == -1) {

          System.out.println("Error: la columna ingresada no existe.");
          return;

        }

        if (numeroFila < 1 || numeroFila > 20) {

          System.out.println("Error: la fila ingresada esta fuera de rango.");
          return;

        }

        int fila = obtenerFila(numeroFila);

        if (cabina[fila][columna] != 'L') {

          System.out.println("El asiento seleccionado no esta disponible.");
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

          System.out.println("___________________________________________");
          System.out.println("Reserva realizada exitosamente.");
          System.out.println("Clase: Primera Clase (VIP)");
          System.out.println("Precio del boleto: $150");
          System.out.println("__________________________________________");

        } else {

           cabina[fila][columna] = 'X';

           System.out.println("__________________________________________");
           System.out.println("Reserva realizada exitosamente.");
           System.out.println("Clase: Clase Economica");
           System.out.println("Precio del boleto: $50");
           System.out.println("__________________________________________");

        }
    }
    
    public static void reporteVuelo() {

       int ocupados = 0;
       int libres = 0;
       int bloqueados = 0;
       int ingresos = 0;
       
       int izquierda = 0;
       int derecha = 0;

        for (int fila = 0; fila < 20; fila++) {

            for (int columna = 0; columna < 6; columna++) {

                if (cabina[fila][columna] == 'X') {

                 ocupados++;
                 
                    if (fila < 5) {
                     ingresos += 150;
                    }else {
                       ingresos += 50;
                    }
                    
                    if (columna < 3) {
                        izquierda++;
                    } else {
                        derecha++;
                    }
 
                } else if (cabina[fila][columna] == 'L') {

                  libres++;

                }else if (cabina[fila][columna] == 'B') {

                  bloqueados++;

                }

            }

        }

        double porcentaje = (ocupados * 100.0) / 120;

        System.out.println("\n           - REPORTE DE VUELO -            ");
        System.out.println("_____________________________________________");
        System.out.println("Asientos ocupados: " + ocupados);
        System.out.println("Asientos libres: " + libres);
        System.out.println("Asientos bloqueados: " + bloqueados);
        System.out.printf("Porcentaje de ocupacion: %.2f%%\n", porcentaje);
        
        double porcentajeIzquierda = 0;
        double porcentajeDerecha = 0;

        if (ocupados > 0) {

          porcentajeIzquierda = (izquierda * 100.0) / ocupados;
          porcentajeDerecha = (derecha * 100.0) / ocupados;
        }
        System.out.println("Ingresos recaudados: $" + ingresos);

        System.out.printf("Ocupacion lado izquierdo: %.2f%%\n",
        porcentajeIzquierda);

        System.out.printf("Ocupacion lado derecho: %.2f%%\n",
        porcentajeDerecha);
        System.out.println("_________________________________________________");
        
    }

    public static void main(String[] args) {

      inicializarCabina(cabina);

      Scanner teclado = new Scanner(System.in);

      int opcion;

      do {

          System.out.println("\n_____ AERO-USAC: SISTEMA DE ABORDAJE ________");
          System.out.println("_____________________________________________");
          System.out.println("1. Venta de Boleto Individual");
          System.out.println("2. Buscar Boletos Contiguos");
          System.out.println("3. Asignacion Automatica");
          System.out.println("4. Mostrar Mapa de la Cabina");
          System.out.println("5. Reporte de Vuelo");
          System.out.println("6. Salir");
          System.out.println("_____________________________________________");
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
                    
                  System.out.println("_____________________________________________");
                  System.out.println("En que clase desea buscar sus asientos?");
                  
                  System.out.println("1. Primera Clase (Filas 1 a 5)");
                  System.out.println("2. Clase Economica (Filas 6 a 20)");
                  System.out.println("_____________________________________________");
                  System.out.print("Seleccione: ");

                  int clase = teclado.nextInt();

                  buscarBoletosContiguos(clase);

                break;

                case 3:
                    
                   System.out.println("____________________________________________");
                   System.out.println("En que clase desea buscar el asiento?");
                   
                   System.out.println("1. Primera Clase (Filas 1 a 5)");
                   System.out.println("2. Clase Economica (Filas 6 a 20)");
                   System.out.println("_____________________________________________");
                   System.out.print("Seleccione: ");

                   int claseAuto = teclado.nextInt();

                   asignacionAutomatica(claseAuto);

                break;

                case 4:

                  mostrarCabina(cabina);

                break;

                case 5:
                  reporteVuelo();

                break;

                case 6:
                    
                  System.out.println("\nGracias por utilizar AERO-USAC.");
                  System.out.println("Sesion finalizada correctamente.");

                break;

                default:

                  System.out.println("Opcion invalida.");

            }

        } while (opcion != 6);

    }
}
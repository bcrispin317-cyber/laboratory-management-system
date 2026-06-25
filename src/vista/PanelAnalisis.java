package vista;

import modelo.Investigador;
import controlador.LaboratorioControlador;
import modelo.Muestra;
import modelo.Patron;

import javax.swing.*;
import java.awt.*;

import modelo.Resultado;
import java.time.LocalDate;
import java.time.LocalTime;

public class PanelAnalisis extends JPanel {

    private JComboBox<String> comboMuestras;
    private JComboBox<String> comboPatrones;

    private JButton btnAnalizar;

    private JLabel lblResultado;
    
    private JProgressBar barraProgreso;

    private LaboratorioControlador controlador;
    
    private Investigador investigador;

    public PanelAnalisis(Investigador investigador) {

        this.investigador = investigador;

        controlador = new LaboratorioControlador();

        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Muestra"));

        comboMuestras = new JComboBox<>();

        for (Muestra muestra :
                controlador.getSistema().getMuestras()) {

            if (investigador.getCodigo().equals(
                    muestra.getInvestigadorAsignado())
                    && muestra.getEstado().equals("En Proceso")) {

                comboMuestras.addItem(
                        muestra.getCodigo()
                );
            }
        }

        add(comboMuestras);

        add(new JLabel("Patrón"));

        comboPatrones = new JComboBox<>();

        for (Patron patron :
                controlador.getSistema().getPatrones()) {

            comboPatrones.addItem(
                    patron.getCodigo()
            );
        }

        add(comboPatrones);

        add(new JLabel());

        btnAnalizar = new JButton("Analizar");
        add(btnAnalizar);

        add(new JLabel("Resultado"));

        lblResultado = new JLabel("Pendiente");
        add(lblResultado);
        
        barraProgreso = new JProgressBar(0, 100);
        barraProgreso.setValue(0);
        barraProgreso.setStringPainted(true);

        add(new JLabel("Progreso"));
        add(barraProgreso);

        btnAnalizar.addActionListener(e -> {

        barraProgreso.setValue(0);
        btnAnalizar.setEnabled(false);

        if (comboMuestras.getSelectedItem() == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "No hay muestras pendientes."
            );

            btnAnalizar.setEnabled(true);

            return;
        }

        if (comboPatrones.getSelectedItem() == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "No existen patrones."
            );

            btnAnalizar.setEnabled(true);

            return;
        }

            String codigoMuestra =
                    comboMuestras.getSelectedItem().toString();

            String codigoPatron =
                    comboPatrones.getSelectedItem().toString();

            Muestra muestraSeleccionada = null;
            Patron patronSeleccionado = null;

            for (Muestra muestra :
                    controlador.getSistema().getMuestras()) {

                if (muestra.getCodigo().equals(codigoMuestra)) {

                    muestraSeleccionada = muestra;
                    break;
                }
            }

            for (Patron patron :
                    controlador.getSistema().getPatrones()) {

                if (patron.getCodigo().equals(codigoPatron)) {

                    patronSeleccionado = patron;
                    break;
                }
            }

            if (muestraSeleccionada == null
                    || patronSeleccionado == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "No fue posible encontrar la muestra o el patrón."
                );

                btnAnalizar.setEnabled(true);

                return;
            }

            int filasMuestra =
                    muestraSeleccionada.getPatron().length;

            int filasPatron =
                    patronSeleccionado.getPatron().length;

            if (filasMuestra != filasPatron) {

                lblResultado.setText(
                        "Las matrices no tienen el mismo tamaño"
                );

                btnAnalizar.setEnabled(true);

                return;
            }

            int[][] matrizMuestra =
                    muestraSeleccionada.getPatron();

            int n = matrizMuestra.length;

            int[][] mt1 = new int[n][n];
            int[][] mt2 = new int[n][n];

            for (int i = 0; i < n; i++) {

                for (int j = 0; j < n; j++) {

                    mt1[i][j] =
                            matrizMuestra[i][j] * 3;

                    mt2[i][j] =
                            matrizMuestra[i][j] * 7;
                }
            }

            System.out.println("MT1");

            for (int i = 0; i < n; i++) {

                for (int j = 0; j < n; j++) {

                    System.out.print(
                            mt1[i][j] + " "
                    );
                }

                System.out.println();
            }

            System.out.println("MT2");

            for (int i = 0; i < n; i++) {

                for (int j = 0; j < n; j++) {

                    System.out.print(
                            mt2[i][j] + " "
                    );
                }

                System.out.println();
            }

            int[][] mt3 = new int[n][n];

            for (int i = 0; i < n; i++) {

                for (int j = 0; j < n; j++) {

                    for (int k = 0; k < n; k++) {

                        mt3[i][j] +=
                                mt1[i][k] * mt2[k][j];
                    }
                }
            }

            System.out.println("MT3");

            for (int i = 0; i < n; i++) {

                for (int j = 0; j < n; j++) {

                    System.out.print(
                            mt3[i][j] + " "
                    );
                }

                System.out.println();
            }

            int[][] mr = new int[n][n];

            for (int i = 0; i < n; i++) {

                for (int j = 0; j < n; j++) {

                    mr[i][j] =
                            mt3[i][j] % 2;
                }
            }

            System.out.println("MR");

            for (int i = 0; i < n; i++) {

                for (int j = 0; j < n; j++) {

                    System.out.print(
                            mr[i][j] + " "
                    );
                }

                System.out.println();
            }

            int[][] matrizPatron =
                    patronSeleccionado.getPatron();

            boolean coincide = true;

            for (int i = 0; i < n; i++) {

                for (int j = 0; j < n; j++) {

                    if (mr[i][j] != matrizPatron[i][j]) {

                        coincide = false;
                        break;
                    }
                }

                if (!coincide) {

                    break;
                }
            }

            System.out.println(
                    "COINCIDE = " + coincide
            );

            if (coincide) {

                lblResultado.setText(
                        "La muestra coincide con "
                                + patronSeleccionado.getNombre()
                );

            } else {

                lblResultado.setText(
                        "La muestra NO coincide con "
                                + patronSeleccionado.getNombre()
                );
            }

            String resultadoTexto;

            if (coincide) {

                resultadoTexto = "Coincide";

            } else {

                resultadoTexto = "No Coincide";
            }

            int numeroResultado =
                    controlador.getSistema()
                            .getResultados()
                            .size() + 1;

            Resultado resultado =
                    new Resultado(
                            numeroResultado,
                            muestraSeleccionada.getCodigo(),
                            patronSeleccionado.getCodigo(),
                            LocalDate.now().toString(),
                            LocalTime.now().withNano(0).toString(),
                            resultadoTexto
                    );

            controlador.getSistema()
                    .getResultados()
                    .add(resultado);

            muestraSeleccionada.setEstado("Procesado");

            controlador.guardarDatos();

            System.out.println("Resultado registrado.");

            cargarMuestras();

            if (comboPatrones.getItemCount() > 0) {

                comboPatrones.setSelectedIndex(0);
            }

            if (comboMuestras.getItemCount() == 0) {

                lblResultado.setText(
                        "No hay muestras pendientes."
                );

                btnAnalizar.setEnabled(false);

            } else {

                lblResultado.setText("Pendiente");

                btnAnalizar.setEnabled(true);
            }

            System.out.println(
                    "Resultado guardado correctamente"
            );

            barraProgreso.setValue(100);
            
            if (comboMuestras.getItemCount() > 0) {

                comboMuestras.setSelectedIndex(0);
            }

        });
    }
    
    private void cargarMuestras() {

        comboMuestras.removeAllItems();

        controlador = new LaboratorioControlador();

        for (Muestra muestra :
                controlador.getSistema().getMuestras()) {

            if (investigador.getCodigo().equals(
                    muestra.getInvestigadorAsignado())
                    && muestra.getEstado().equals("En Proceso")) {

                comboMuestras.addItem(
                        muestra.getCodigo()
                );
            }
        }
    }
    
}
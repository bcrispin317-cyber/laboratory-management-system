package vista;

import controlador.LaboratorioControlador;
import modelo.Investigador;
import modelo.Muestra;

import javax.swing.*;
import java.awt.*;

public class PanelAsignacion extends JPanel {

    private JComboBox<String> comboInvestigadores;
    private JComboBox<String> comboMuestras;

    private JButton btnAsignar;

    private LaboratorioControlador controlador;

    public PanelAsignacion() {

        controlador = new LaboratorioControlador();

        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));

        add(new JLabel("Investigador"));

        comboInvestigadores = new JComboBox<>();
        comboInvestigadores.setPreferredSize(
                new Dimension(200, 30));
        add(comboInvestigadores);

        add(new JLabel("Muestra"));

        comboMuestras = new JComboBox<>();
        comboMuestras.setPreferredSize(
                new Dimension(200, 30));
        add(comboMuestras);

        btnAsignar = new JButton("Asignar");
        btnAsignar.setPreferredSize(
                new Dimension(150, 40));
        add(btnAsignar);

        cargarInvestigadores();
        cargarMuestras();

        btnAsignar.addActionListener(e -> asignar());
    }

    private void cargarInvestigadores() {

        comboInvestigadores.removeAllItems();

        controlador = new LaboratorioControlador();

        for (Investigador investigador :
                controlador.getSistema().getInvestigadores()) {

            comboInvestigadores.addItem(
                    investigador.getCodigo()
            );
        }
    }

    private void cargarMuestras() {

        comboMuestras.removeAllItems();

        controlador = new LaboratorioControlador();

        for (Muestra muestra :
                controlador.getSistema().getMuestras()) {

            if (!"Procesado".equalsIgnoreCase(
                    muestra.getEstado().trim())) {

                comboMuestras.addItem(
                        muestra.getCodigo()
                );
            }
        }
    }

    private void asignar() {

        String codigoInvestigador =
                (String) comboInvestigadores.getSelectedItem();

        String codigoMuestra =
                (String) comboMuestras.getSelectedItem();

        if (codigoInvestigador == null
                || codigoMuestra == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar investigador y muestra."
            );

            return;
        }

        for (Muestra muestra :
                controlador.getSistema().getMuestras()) {

            if (muestra.getCodigo().equals(codigoMuestra)) {

                muestra.setInvestigadorAsignado(
                        codigoInvestigador
                );

                muestra.setEstado("En Proceso");

                break;
            }
        }

        for (Investigador investigador :
                controlador.getSistema().getInvestigadores()) {

            if (investigador.getCodigo()
                    .equals(codigoInvestigador)) {

                investigador.setExperimentos(
                        investigador.getExperimentos() + 1
                );

                break;
            }
        }

        controlador.guardarDatos();

        JOptionPane.showMessageDialog(
                this,
                "Experimento asignado correctamente."
        );

        cargarMuestras();
    }
}
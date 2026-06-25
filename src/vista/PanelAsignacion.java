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

        Font fuente =
                new Font("Segoe UI", Font.PLAIN, 15);

        Font titulo =
                new Font("Segoe UI", Font.BOLD, 15);

        setLayout(new FlowLayout(
                FlowLayout.CENTER,
                20,
                30));

        setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );

        JLabel lblInvestigador =
                new JLabel("Investigador");

        lblInvestigador.setFont(titulo);

        add(lblInvestigador);

        comboInvestigadores = new JComboBox<>();

        comboInvestigadores.setFont(fuente);

        comboInvestigadores.setPreferredSize(
                new Dimension(230, 35)
        );

        add(comboInvestigadores);

        JLabel lblMuestra =
                new JLabel("Muestra");

        lblMuestra.setFont(titulo);

        add(lblMuestra);

        comboMuestras = new JComboBox<>();

        comboMuestras.setFont(fuente);

        comboMuestras.setPreferredSize(
                new Dimension(230, 35)
        );

        add(comboMuestras);

        btnAsignar = new JButton("Asignar");

        btnAsignar.setFont(
                new Font("Segoe UI",
                        Font.BOLD,
                        15)
        );

        btnAsignar.setBackground(
                new Color(52, 152, 219)
        );

        btnAsignar.setForeground(
                Color.WHITE
        );

        btnAsignar.setFocusPainted(false);

        btnAsignar.setPreferredSize(
                new Dimension(170, 45)
        );

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

    public void actualizar() {

        cargarInvestigadores();
        cargarMuestras();
    }
}
package vista;

import controlador.LaboratorioControlador;
import modelo.Investigador;
import modelo.Muestra;

import javax.swing.*;
import java.awt.*;

public class EliminarInvestigador extends JFrame {

    private JTextField txtCodigo;

    private JButton btnBuscar;
    private JButton btnEliminar;

    private LaboratorioControlador controlador;
    private Investigador investigadorActual;

    public EliminarInvestigador() {

        controlador = new LaboratorioControlador();

        setTitle("Eliminar Investigador");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Código"));

        txtCodigo = new JTextField();
        add(txtCodigo);

        btnBuscar = new JButton("Buscar");
        add(btnBuscar);

        btnEliminar = new JButton("Borrar");
        add(btnEliminar);

        btnBuscar.addActionListener(e -> buscar());

        btnEliminar.addActionListener(e -> eliminar());

        setVisible(true);
    }

    private void buscar() {

        String codigo = txtCodigo.getText().trim();

        if (codigo.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese un código."
            );

            return;
        }

        for (Investigador i :
                controlador.getSistema().getInvestigadores()) {

            if (i.getCodigo().equalsIgnoreCase(codigo)) {

                investigadorActual = i;

                JOptionPane.showMessageDialog(
                        this,
                        "Investigador encontrado: "
                                + i.getNombre()
                );

                return;
            }
        }

        JOptionPane.showMessageDialog(
                this,
                "Investigador no encontrado."
        );
    }

    private void eliminar() {

        if (investigadorActual == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Primero busque un investigador."
            );

            return;
        }

        // Verificar si tiene muestras asignadas
        for (Muestra muestra :
                controlador.getSistema().getMuestras()) {

            if (investigadorActual.getCodigo().equals(
                    muestra.getInvestigadorAsignado())) {

                JOptionPane.showMessageDialog(
                        this,
                        "No se puede eliminar el investigador porque tiene muestras asignadas."
                );

                return;
            }
        }

        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Desea eliminar el investigador?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {

            controlador.getSistema()
                    .getInvestigadores()
                    .remove(investigadorActual);

            controlador.guardarDatos();

            JOptionPane.showMessageDialog(
                    this,
                    "Investigador eliminado correctamente."
            );

            dispose();
        }
    }
}
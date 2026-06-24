package vista;

import controlador.LaboratorioControlador;
import modelo.Patron;

import javax.swing.*;
import java.awt.*;

public class EliminarPatron extends JFrame {

    private JTextField txtCodigo;

    private JButton btnBuscar;
    private JButton btnEliminar;

    private LaboratorioControlador controlador;
    private Patron patronActual;

    public EliminarPatron() {

        controlador = new LaboratorioControlador();

        setTitle("Eliminar Patrón");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Código"));

        txtCodigo = new JTextField();
        add(txtCodigo);

        btnBuscar = new JButton("Buscar");
        add(btnBuscar);

        btnEliminar = new JButton("Eliminar");
        add(btnEliminar);

        btnBuscar.addActionListener(e -> buscar());

        btnEliminar.addActionListener(e -> eliminar());

        setVisible(true);
    }

    private void buscar() {

        String codigo = txtCodigo.getText();

        for (Patron p :
                controlador.getSistema().getPatrones()) {

            if (p.getCodigo().equals(codigo)) {

                patronActual = p;

                JOptionPane.showMessageDialog(
                        this,
                        "Patrón encontrado: " + p.getNombre()
                );

                return;
            }
        }

        JOptionPane.showMessageDialog(
                this,
                "Patrón no encontrado"
        );
    }

    private void eliminar() {

        if (patronActual == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Primero busque un patrón"
            );

            return;
        }

        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Desea eliminar el patrón?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {

            controlador.getSistema()
                    .getPatrones()
                    .remove(patronActual);

            controlador.guardarDatos();

            JOptionPane.showMessageDialog(
                    this,
                    "Patrón eliminado"
            );

            dispose();
        }
    }
}
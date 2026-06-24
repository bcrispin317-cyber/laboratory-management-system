package vista;

import controlador.LaboratorioControlador;
import modelo.Muestra;

import javax.swing.*;
import java.awt.*;

public class EliminarMuestra extends JFrame {

    private JTextField txtCodigo;

    private JButton btnBuscar;
    private JButton btnEliminar;

    private LaboratorioControlador controlador;
    private Muestra muestraActual;

    public EliminarMuestra() {

        controlador = new LaboratorioControlador();

        setTitle("Eliminar Muestra");
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

        for (Muestra m :
                controlador.getSistema().getMuestras()) {

            if (m.getCodigo().equals(codigo)) {

                muestraActual = m;

                JOptionPane.showMessageDialog(
                        this,
                        "Muestra encontrada: "
                                + m.getDescripcion()
                );

                return;
            }
        }

        JOptionPane.showMessageDialog(
                this,
                "Muestra no encontrada"
        );
    }

    private void eliminar() {

        if (muestraActual == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Primero busque una muestra"
            );

            return;
        }

        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Desea eliminar la muestra?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {

            controlador.getSistema()
                    .getMuestras()
                    .remove(muestraActual);

            controlador.guardarDatos();

            JOptionPane.showMessageDialog(
                    this,
                    "Muestra eliminada"
            );

            dispose();
        }
    }
}
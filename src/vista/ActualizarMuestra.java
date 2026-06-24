package vista;

import controlador.LaboratorioControlador;
import modelo.Muestra;

import javax.swing.*;
import java.awt.*;

public class ActualizarMuestra extends JFrame {

    private JTextField txtCodigo;
    private JTextField txtDescripcion;

    private JButton btnBuscar;
    private JButton btnActualizar;

    private LaboratorioControlador controlador;
    private Muestra muestraActual;

    public ActualizarMuestra() {

        controlador = new LaboratorioControlador();

        setTitle("Actualizar Muestra");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 3, 10, 10));

        add(new JLabel("Código"));
        txtCodigo = new JTextField();
        add(txtCodigo);

        btnBuscar = new JButton("Buscar");
        add(btnBuscar);

        add(new JLabel("Descripción"));
        txtDescripcion = new JTextField();
        add(txtDescripcion);

        add(new JLabel());

        add(new JLabel());

        btnActualizar = new JButton("Actualizar");
        add(btnActualizar);

        add(new JLabel());

        btnBuscar.addActionListener(e -> buscar());

        btnActualizar.addActionListener(e -> actualizar());

        setVisible(true);
    }

    private void buscar() {

        String codigo = txtCodigo.getText();

        for (Muestra m : controlador.getSistema().getMuestras()) {

            if (m.getCodigo().equals(codigo)) {

                muestraActual = m;

                txtDescripcion.setText(
                        m.getDescripcion()
                );

                return;
            }
        }

        JOptionPane.showMessageDialog(
                this,
                "Muestra no encontrada"
        );
    }

    private void actualizar() {

        if (muestraActual == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Primero busque una muestra"
            );

            return;
        }

        muestraActual.setDescripcion(
                txtDescripcion.getText()
        );

        controlador.guardarDatos();

        JOptionPane.showMessageDialog(
                this,
                "Muestra actualizada"
        );

        dispose();
    }
}
package vista;

import controlador.LaboratorioControlador;
import modelo.Patron;

import javax.swing.*;
import java.awt.*;

public class ActualizarPatron extends JFrame {

    private JTextField txtCodigo;
    private JTextField txtNombre;

    private JButton btnBuscar;
    private JButton btnActualizar;

    private LaboratorioControlador controlador;
    private Patron patronActual;

    public ActualizarPatron() {

        controlador = new LaboratorioControlador();

        setTitle("Actualizar Patrón");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 3, 10, 10));

        add(new JLabel("Código"));
        txtCodigo = new JTextField();
        add(txtCodigo);

        btnBuscar = new JButton("Buscar");
        add(btnBuscar);

        add(new JLabel("Nombre"));
        txtNombre = new JTextField();
        add(txtNombre);

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

        controlador = new LaboratorioControlador();

        String codigo = txtCodigo.getText().trim();

        if (codigo.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese un código."
            );

            return;
        }

        for (Patron patron :
                controlador.getSistema().getPatrones()) {

            if (patron.getCodigo().equals(codigo)) {

                patronActual = patron;

                txtNombre.setText(
                        patron.getNombre()
                );

                return;
            }
        }

        JOptionPane.showMessageDialog(
                this,
                "Patrón no encontrado."
        );
    }

    private void actualizar() {

        if (patronActual == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Primero busque un patrón."
            );

            return;
        }

        if (txtNombre.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese un nombre."
            );

            return;
        }

        patronActual.setNombre(
                txtNombre.getText().trim()
        );

        controlador.guardarDatos();

        JOptionPane.showMessageDialog(
                this,
                "Patrón actualizado correctamente."
        );

        dispose();
    }
}
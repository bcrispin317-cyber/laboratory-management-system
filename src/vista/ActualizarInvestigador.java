package vista;

import controlador.LaboratorioControlador;
import modelo.Investigador;

import javax.swing.*;
import java.awt.*;

public class ActualizarInvestigador extends JFrame {

    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtGenero;
    private JTextField txtContrasenia;

    private JButton btnBuscar;
    private JButton btnActualizar;

    private LaboratorioControlador controlador;
    private Investigador investigadorActual;

    public ActualizarInvestigador() {

        controlador = new LaboratorioControlador();

        setTitle("Actualizar Investigador");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 3, 10, 10));

        add(new JLabel("Código"));
        txtCodigo = new JTextField();
        add(txtCodigo);

        btnBuscar = new JButton("Buscar");
        add(btnBuscar);

        add(new JLabel("Nombre"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel());

        add(new JLabel("Género"));
        txtGenero = new JTextField();
        add(txtGenero);

        add(new JLabel());

        add(new JLabel("Contraseña"));
        txtContrasenia = new JTextField();
        add(txtContrasenia);

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

        for (Investigador i :
                controlador.getSistema().getInvestigadores()) {

            if (i.getCodigo().equals(codigo)) {

                investigadorActual = i;

                txtNombre.setText(i.getNombre());
                txtGenero.setText(i.getGenero());
                txtContrasenia.setText(i.getContrasenia());

                return;
            }
        }

        JOptionPane.showMessageDialog(
                this,
                "Investigador no encontrado."
        );
    }

    private void actualizar() {

        if (investigadorActual == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Primero debe buscar un investigador."
            );

            return;
        }

        if (txtNombre.getText().trim().isEmpty()
                || txtGenero.getText().trim().isEmpty()
                || txtContrasenia.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Complete todos los campos."
            );

            return;
        }

        investigadorActual.setNombre(
                txtNombre.getText().trim());

        investigadorActual.setGenero(
                txtGenero.getText().trim());

        investigadorActual.setContrasenia(
                txtContrasenia.getText().trim());

        controlador.guardarDatos();

        JOptionPane.showMessageDialog(
                this,
                "Investigador actualizado correctamente."
        );

        dispose();
    }
}
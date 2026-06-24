package vista;

import controlador.LaboratorioControlador;
import modelo.Investigador;

import javax.swing.*;
import java.awt.*;

public class CrearInvestigador extends JFrame {

    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtGenero;
    private JTextField txtContrasenia;

    private JButton btnCrear;

    private LaboratorioControlador controlador;

    public CrearInvestigador() {

        controlador = new LaboratorioControlador();

        setTitle("Crear Investigador");
        setSize(350, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Código"));
        txtCodigo = new JTextField();
        add(txtCodigo);

        add(new JLabel("Nombre"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Género"));
        txtGenero = new JTextField();
        add(txtGenero);

        add(new JLabel("Contraseña"));
        txtContrasenia = new JTextField();
        add(txtContrasenia);

        add(new JLabel());

        btnCrear = new JButton("Crear");
        add(btnCrear);

        btnCrear.addActionListener(e -> {

            String codigo = txtCodigo.getText();
            String nombre = txtNombre.getText();
            String genero = txtGenero.getText();
            String contrasenia = txtContrasenia.getText();

            Investigador investigador = new Investigador(
                    codigo,
                    nombre,
                    genero,
                    0,
                    contrasenia
            );

            controlador.getSistema()
                    .getInvestigadores()
                    .add(investigador);

            controlador.guardarDatos();

            JOptionPane.showMessageDialog(
                    this,
                    "Investigador creado correctamente"
            );

            dispose();
        });

        setVisible(true);
    }
}
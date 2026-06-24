package vista;

import controlador.LaboratorioControlador;
import modelo.Patron;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class CrearPatron extends JFrame {

    private JTextField txtCodigo;
    private JTextField txtNombre;

    private JButton btnCargarPatron;
    private JButton btnCrear;

    private int[][] matrizPatron;

    private LaboratorioControlador controlador;

    public CrearPatron() {

        controlador = new LaboratorioControlador();

        setTitle("Crear Patrón");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Código"));
        txtCodigo = new JTextField();
        add(txtCodigo);

        add(new JLabel("Nombre"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Patrón"));

        btnCargarPatron = new JButton("Cargar Patrón");
        add(btnCargarPatron);

        add(new JLabel());

        btnCrear = new JButton("Crear");
        add(btnCrear);

        btnCargarPatron.addActionListener(e -> cargarPatron());

        btnCrear.addActionListener(e -> crearPatron());

        setVisible(true);
    }

    private void cargarPatron() {

        JFileChooser selector = new JFileChooser();

        int opcion = selector.showOpenDialog(this);

        if (opcion == JFileChooser.APPROVE_OPTION) {

            File archivo = selector.getSelectedFile();

            JOptionPane.showMessageDialog(
                    this,
                    "Archivo seleccionado:\n" + archivo.getName()
            );

            matrizPatron = new int[1][1];
        }
    }

    private void crearPatron() {

        String codigo = txtCodigo.getText();
        String nombre = txtNombre.getText();

        if (codigo.isEmpty() || nombre.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Complete todos los campos"
            );

            return;
        }

        if (matrizPatron == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe cargar un patrón"
            );

            return;
        }

        Patron patron = new Patron(
                codigo,
                nombre,
                matrizPatron
        );

        controlador.getSistema()
                .getPatrones()
                .add(patron);

        controlador.guardarDatos();

        JOptionPane.showMessageDialog(
                this,
                "Patrón creado correctamente"
        );

        dispose();
    }
}
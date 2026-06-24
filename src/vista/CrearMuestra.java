package vista;

import controlador.LaboratorioControlador;
import modelo.Muestra;

import javax.swing.*;
import java.awt.*;

public class CrearMuestra extends JFrame {

    private JTextField txtCodigo;
    private JTextField txtDescripcion;

    private JButton btnCrear;

    private LaboratorioControlador controlador;

    public CrearMuestra() {

        controlador = new LaboratorioControlador();

        setTitle("Crear Muestra");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Código"));
        txtCodigo = new JTextField();
        add(txtCodigo);

        add(new JLabel("Descripción"));
        txtDescripcion = new JTextField();
        add(txtDescripcion);

        add(new JLabel());

        btnCrear = new JButton("Crear");
        add(btnCrear);

        btnCrear.addActionListener(e -> crearMuestra());

        setVisible(true);
    }

    private void crearMuestra() {

        String codigo = txtCodigo.getText();
        String descripcion = txtDescripcion.getText();

        int[][] patron = new int[0][0];

        Muestra muestra = new Muestra(
                codigo,
                descripcion,
                patron,
                "Ingreso"
        );

        controlador.getSistema()
                .getMuestras()
                .add(muestra);

        controlador.guardarDatos();

        JOptionPane.showMessageDialog(
                this,
                "Muestra creada correctamente"
        );

        dispose();
    }
}
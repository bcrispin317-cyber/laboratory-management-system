package vista;

import controlador.LaboratorioControlador;
import modelo.Patron;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

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

        JFileChooser selector =
                new JFileChooser();

        int opcion =
                selector.showOpenDialog(this);

        if (opcion ==
                JFileChooser.APPROVE_OPTION) {

            File archivo =
                    selector.getSelectedFile();

            try {

                BufferedReader br =
                        new BufferedReader(
                                new FileReader(archivo)
                        );

                ArrayList<int[]> filas =
                        new ArrayList<>();

                String linea;

                while ((linea =
                        br.readLine()) != null) {

                    linea = linea.trim();

                    if (linea.isEmpty()) {
                        continue;
                    }

                    String[] datos =
                            linea.split(",");

                    int[] fila =
                            new int[datos.length];

                    for (int i = 0;
                            i < datos.length;
                            i++) {

                        fila[i] =
                                Integer.parseInt(
                                        datos[i].trim()
                                );
                    }

                    filas.add(fila);
                }

                br.close();

                matrizPatron =
                        new int[filas.size()]
                                [filas.get(0).length];

                for (int i = 0;
                        i < filas.size();
                        i++) {

                    matrizPatron[i] =
                            filas.get(i);
                }

                JOptionPane.showMessageDialog(
                        this,
                        "Patrón cargado correctamente."
                );

            } catch (Exception e) {

                JOptionPane.showMessageDialog(
                        this,
                        "Error al leer el archivo."
                );
            }
        }
    }

    private void crearPatron() {

        String codigo = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();

        // Validar campos vacíos
        if (codigo.isEmpty() || nombre.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Complete todos los campos."
            );

            return;
        }

        // Validar código repetido
        for (Patron patron :
                controlador.getSistema().getPatrones()) {

            if (patron.getCodigo().equalsIgnoreCase(codigo)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Ya existe un patrón con ese código."
                );

                return;
            }
        }

        // Validar que se haya cargado un patrón
        if (matrizPatron == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe cargar un patrón."
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
                "Patrón creado correctamente."
        );

        dispose();
    }
}
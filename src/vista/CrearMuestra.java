package vista;

import controlador.LaboratorioControlador;
import modelo.Muestra;

import javax.swing.*;
import java.awt.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class CrearMuestra extends JFrame {

    private JTextField txtCodigo;
    private JTextField txtDescripcion;

    private JButton btnCrear;
    
    private JButton btnCargarPatron;

    private int[][] matrizMuestra;

    private LaboratorioControlador controlador;

    public CrearMuestra() {

        controlador = new LaboratorioControlador();

        setTitle("Crear Muestra");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Código"));
        txtCodigo = new JTextField();
        add(txtCodigo);

        add(new JLabel("Descripción"));
        txtDescripcion = new JTextField();
        add(txtDescripcion);
        
        add(new JLabel("Patrón"));

        btnCargarPatron =
                new JButton("Cargar Patrón");

        add(btnCargarPatron);

        add(new JLabel());

        btnCrear = new JButton("Crear");
        add(btnCrear);
        
        btnCargarPatron.addActionListener(
                e -> cargarPatron()
        );

        btnCrear.addActionListener(e -> crearMuestra());

        setVisible(true);
    }

    private void crearMuestra() {

        String codigo = txtCodigo.getText();
        String descripcion = txtDescripcion.getText();
        
        if (matrizMuestra == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe cargar una matriz"
            );

            return;
        }

        Muestra muestra = new Muestra(
                codigo,
                descripcion,
                matrizMuestra,
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
    
    private void cargarPatron() {

        JFileChooser selector =
                new JFileChooser();

        int opcion =
                selector.showOpenDialog(this);

        if (opcion ==
                JFileChooser.APPROVE_OPTION) {

            try {

                File archivo =
                        selector.getSelectedFile();

                BufferedReader lector =
                        new BufferedReader(
                                new FileReader(archivo));

                String linea;

                java.util.ArrayList<Integer> datos =
                        new java.util.ArrayList<>();

                while ((linea = lector.readLine()) != null) {

                    String[] numeros =
                            linea.split(",");

                    for (String numero : numeros) {

                        datos.add(
                                Integer.parseInt(
                                        numero.trim()
                                )
                        );
                    }
                }

                lector.close();

                int tamanio =
                        (int) Math.sqrt(datos.size());

                matrizMuestra =
                        new int[tamanio][tamanio];

                int indice = 0;

                for (int i = 0; i < tamanio; i++) {

                    for (int j = 0; j < tamanio; j++) {

                        matrizMuestra[i][j] =
                                datos.get(indice);

                        indice++;
                    }
                }

                JOptionPane.showMessageDialog(
                        this,
                        "Matriz cargada correctamente"
                );

            } catch (Exception e) {

                JOptionPane.showMessageDialog(
                        this,
                        "Error al cargar matriz"
                );
            }
        }
    }

}

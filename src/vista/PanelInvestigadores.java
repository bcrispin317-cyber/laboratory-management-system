package vista;

import controlador.LaboratorioControlador;
import modelo.Investigador;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class PanelInvestigadores extends JPanel {

    private JTable tabla;
    private DefaultTableModel modelo;

    private JButton btnCrear;
    private JButton btnCargar;
    private JButton btnActualizar;
    private JButton btnEliminar;

    private LaboratorioControlador controlador;

    public PanelInvestigadores() {

        controlador = new LaboratorioControlador();

        setLayout(new BorderLayout());

        setBorder(
                BorderFactory.createEmptyBorder(
                        10, 10, 10, 10
                )
        );

        modelo = new DefaultTableModel();

        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("Género");
        modelo.addColumn("Experimentos");

        tabla = new JTable(modelo);

        tabla.setRowHeight(28);

        tabla.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );

        tabla.getTableHeader().setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        DefaultTableCellRenderer centro =
                new DefaultTableCellRenderer();

        centro.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        for (int i = 0; i < tabla.getColumnCount(); i++) {

            tabla.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(centro);
        }

        JScrollPane scroll = new JScrollPane(tabla);

        JPanel panelBotones = new JPanel();

        panelBotones.setLayout(
                new GridLayout(4, 1, 10, 10)
        );

        Font fuenteBoton =
                new Font("Segoe UI", Font.BOLD, 14);

        btnCrear = new JButton("Crear");
        btnCargar = new JButton("Cargar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");

        btnCrear.setFont(fuenteBoton);
        btnCargar.setFont(fuenteBoton);
        btnActualizar.setFont(fuenteBoton);
        btnEliminar.setFont(fuenteBoton);

        btnCrear.setBackground(
                new Color(46, 204, 113));
        btnCrear.setForeground(Color.WHITE);

        btnCargar.setBackground(
                new Color(52, 152, 219));
        btnCargar.setForeground(Color.WHITE);

        btnActualizar.setBackground(
                new Color(241, 196, 15));
        btnActualizar.setForeground(Color.BLACK);

        btnEliminar.setBackground(
                new Color(231, 76, 60));
        btnEliminar.setForeground(Color.WHITE);

        btnCrear.setFocusPainted(false);
        btnCargar.setFocusPainted(false);
        btnActualizar.setFocusPainted(false);
        btnEliminar.setFocusPainted(false);

        panelBotones.add(btnCrear);
        panelBotones.add(btnCargar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.EAST);

        cargarTabla();

        btnCrear.addActionListener((ActionEvent e) -> {

            new CrearInvestigador();

            cargarTabla();
        });

        btnCargar.addActionListener((ActionEvent e) -> {

            JFileChooser selector = new JFileChooser();

            int opcion = selector.showOpenDialog(null);

            if (opcion == JFileChooser.APPROVE_OPTION) {

                try {

                    File archivo = selector.getSelectedFile();

                    BufferedReader lector =
                            new BufferedReader(
                                    new FileReader(archivo));

                    String linea;

                    lector.readLine();

                    while ((linea = lector.readLine()) != null) {

                        String[] datos = linea.split(",");

                        String codigo = datos[0].trim();
                        String nombre = datos[1].trim();
                        String genero = datos[2].trim();

                        int experimentos =
                                Integer.parseInt(
                                        datos[3].trim());

                        String contrasenia =
                                datos[4].trim();

                        Investigador investigador =
                                new Investigador(
                                        codigo,
                                        nombre,
                                        genero,
                                        experimentos,
                                        contrasenia
                                );

                        controlador.getSistema()
                                .getInvestigadores()
                                .add(investigador);
                    }

                    lector.close();

                    controlador.guardarDatos();

                    cargarTabla();

                    JOptionPane.showMessageDialog(
                            null,
                            "Investigadores cargados correctamente"
                    );

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Error al cargar CSV"
                    );
                }
            }
        });

        btnActualizar.addActionListener((ActionEvent e) -> {

            new ActualizarInvestigador();

            cargarTabla();
        });

        btnEliminar.addActionListener((ActionEvent e) -> {

            new EliminarInvestigador();

            cargarTabla();
        });
    }

    public void actualizar() {

        cargarTabla();
    }

    private void cargarTabla() {

        controlador = new LaboratorioControlador();

        modelo.setRowCount(0);

        for (Investigador investigador :
                controlador.getSistema().getInvestigadores()) {

            modelo.addRow(new Object[]{
                    investigador.getCodigo(),
                    investigador.getNombre(),
                    investigador.getGenero(),
                    investigador.getExperimentos()
            });
        }
    }
}
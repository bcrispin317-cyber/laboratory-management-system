package vista;

import controlador.LaboratorioControlador;
import modelo.Patron;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.io.BufferedReader;
import java.io.FileReader;

import java.io.File;
import java.io.PrintWriter;
import java.awt.Desktop;

public class PanelPatrones extends JPanel {

    private JTable tabla;
    private DefaultTableModel modelo;

    private JButton btnCrear;
    private JButton btnCargar;
    private JButton btnVer;
    private JButton btnEliminar;

    private LaboratorioControlador controlador;

    public PanelPatrones() {

        controlador = new LaboratorioControlador();

        setLayout(new BorderLayout());

        modelo = new DefaultTableModel();

        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("Acciones");

        tabla = new JTable(modelo);
        tabla.setDefaultEditor(Object.class, null);

        JScrollPane scroll = new JScrollPane(tabla);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(4, 1, 10, 10));

        btnCrear = new JButton("Crear");
        btnCargar = new JButton("Cargar");
        btnVer = new JButton("Ver");
        btnEliminar = new JButton("Eliminar");

        panelBotones.add(btnCrear);
        panelBotones.add(btnCargar);
        panelBotones.add(btnVer);
        panelBotones.add(btnEliminar);

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.EAST);

        cargarTabla();

        btnCrear.addActionListener(e -> {
            new CrearPatron();
        });

        btnCargar.addActionListener(e -> {

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
                        String patronTexto = datos[2].trim();

                        int[][] matriz =
                                convertirPatron(patronTexto);

                        Patron patron = new Patron(
                                codigo,
                                nombre,
                                matriz
                        );

                        controlador.getSistema()
                                .getPatrones()
                                .add(patron);
                    }

                    lector.close();

                    controlador.guardarDatos();

                    cargarTabla();

                    JOptionPane.showMessageDialog(
                            null,
                            "Patrones cargados correctamente"
                    );

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Error al cargar CSV"
                    );
                }
            }
        });

        btnEliminar.addActionListener(e -> {
            new EliminarPatron();
        });
        
        btnVer.addActionListener(e -> {

            int fila = tabla.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(
                        null,
                        "Seleccione un patrón"
                );

                return;
            }

            String codigo =
                    modelo.getValueAt(fila, 0).toString();

            for (Patron patron :
                    controlador.getSistema().getPatrones()) {

                if (patron.getCodigo().equals(codigo)) {

                    generarHTML(patron);
                    return;
                }
            }
        });
    }
    
    

    private void cargarTabla() {
        
        controlador = new LaboratorioControlador();

        modelo.setRowCount(0);

        for (Patron patron :
                controlador.getSistema().getPatrones()) {

            modelo.addRow(new Object[]{
                patron.getCodigo(),
                patron.getNombre(),
                "Ver"
            });
        }
    }
    
    private void generarHTML(Patron patron) {

        try {

            String nombreArchivo =
                    "Patron_" + patron.getCodigo() + ".html";

            PrintWriter writer =
                    new PrintWriter(nombreArchivo);

            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>" + patron.getNombre() + "</title>");
            writer.println("</head>");
            writer.println("<body>");

            writer.println("<h1>Patrón: "
                    + patron.getNombre()
                    + "</h1>");

            writer.println("<h2>Código: "
                    + patron.getCodigo()
                    + "</h2>");

            writer.println("<table border='1'>");

            int[][] matriz = patron.getPatron();

            for (int i = 0; i < matriz.length; i++) {

                writer.println("<tr>");

                for (int j = 0; j < matriz[i].length; j++) {

                    writer.println(
                            "<td>"
                                    + matriz[i][j]
                                    + "</td>"
                    );
                }

                writer.println("</tr>");
            }

            writer.println("</table>");
            writer.println("</body>");
            writer.println("</html>");

            writer.close();

            Desktop.getDesktop().open(
                    new File(nombreArchivo)
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Error al generar HTML"
            );
        }
    }
    
    private int[][] convertirPatron(String texto) {

        String[] valores = texto.split(";");

        int tamanio =
                (int) Math.sqrt(valores.length);

        int[][] matriz =
                new int[tamanio][tamanio];

        int indice = 0;

        for (int i = 0; i < tamanio; i++) {

            for (int j = 0; j < tamanio; j++) {

                matriz[i][j] =
                        Integer.parseInt(
                                valores[indice]);

                indice++;
            }
        }

        return matriz;
    }
    
}
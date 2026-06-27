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
import javax.swing.table.DefaultTableCellRenderer;

public class PanelPatrones extends JPanel {

    private JTable tabla;
    private DefaultTableModel modelo;

    private JButton btnCrear;
    private JButton btnCargar;
    private JButton btnVer;
    private JButton btnVerTodos;
    private JButton btnEliminar;

    private LaboratorioControlador controlador;

    public PanelPatrones() {

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
        modelo.addColumn("Acciones");

       tabla = new JTable(modelo);

        tabla.setDefaultEditor(Object.class, null);

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
        
        tabla.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(90);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(5, 1, 10, 10));

        btnCrear = new JButton("Crear");
        btnCargar = new JButton("Cargar");
        btnVer = new JButton("Ver");
        btnVerTodos = new JButton("Ver Todos");
        btnEliminar = new JButton("Eliminar");
        
        Font fuenteBoton =
                new Font("Segoe UI", Font.BOLD, 14);

        btnCrear.setFont(fuenteBoton);
        btnCargar.setFont(fuenteBoton);
        btnVer.setFont(fuenteBoton);
        btnEliminar.setFont(fuenteBoton);

        btnCrear.setBackground(
                new Color(46,204,113));
        btnCrear.setForeground(Color.WHITE);

        btnCargar.setBackground(
                new Color(52,152,219));
        btnCargar.setForeground(Color.WHITE);

        btnVer.setBackground(
                new Color(155,89,182));
        btnVer.setForeground(Color.WHITE);

        btnEliminar.setBackground(
                new Color(231,76,60));
        btnEliminar.setForeground(Color.WHITE);

        btnCrear.setFocusPainted(false);
        btnCargar.setFocusPainted(false);
        btnVer.setFocusPainted(false);
        btnEliminar.setFocusPainted(false);
        

        panelBotones.add(btnCrear);
        panelBotones.add(btnCargar);
        panelBotones.add(btnVer);
        panelBotones.add(btnVerTodos);
        panelBotones.add(btnEliminar);

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.EAST);

        cargarTabla();

        btnCrear.addActionListener(e -> {

            CrearPatron ventana =
                    new CrearPatron();

            ventana.addWindowListener(
                    new java.awt.event.WindowAdapter() {

                @Override
                public void windowClosed(
                        java.awt.event.WindowEvent e) {

                    cargarTabla();
                }
            });
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

                        boolean existe = false;

                        for (Patron p :
                                controlador.getSistema().getPatrones()) {

                            if (p.getCodigo().equals(codigo)) {

                                existe = true;
                                break;
                            }
                        }

                        if (existe) {

                            continue;
                        }

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

            EliminarPatron ventana =
                    new EliminarPatron();

            ventana.addWindowListener(
                    new java.awt.event.WindowAdapter() {

                @Override
                public void windowClosed(
                        java.awt.event.WindowEvent e) {

                    cargarTabla();
                }
            });
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
        
        btnVerTodos.addActionListener(e -> {
            verTodosPatrones();
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
            writer.println("<style>");
            writer.println("body{font-family:Segoe UI;background:#f4f6f9;padding:30px;}");
            writer.println(".card{background:white;padding:25px;border-radius:15px;width:500px;margin:auto;box-shadow:0px 0px 10px gray;}");
            writer.println("h1{color:#3498db;}");
            writer.println("table{border-collapse:collapse;margin-top:20px;}");
            writer.println("td{border:1px solid #ccc;padding:10px;text-align:center;}");
            writer.println("</style>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<div class='card'>");
            writer.println("</body>");
            writer.println("</html>");

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

        texto = texto.trim();

        if (texto.contains("\n")) {

            String[] filas =
                    texto.split("\n");

            int[][] matriz =
                    new int[filas.length][filas.length];

            for (int i = 0; i < filas.length; i++) {

                String[] numeros =
                        filas[i].trim().split(",");

                for (int j = 0;
                        j < numeros.length;
                        j++) {

                    matriz[i][j] =
                            Integer.parseInt(
                                    numeros[j].trim()
                            );
                }
            }

            return matriz;
        }

        String[] valores =
                texto.split(";");

        int tamanio =
                (int) Math.sqrt(
                        valores.length
                );

        int[][] matriz =
                new int[tamanio][tamanio];

        int indice = 0;

        for (int i = 0; i < tamanio; i++) {

            for (int j = 0; j < tamanio; j++) {

                matriz[i][j] =
                        Integer.parseInt(
                                valores[indice].trim()
                        );

                indice++;
            }
        }

        return matriz;
    }
    
    private void verTodosPatrones() {

        try {

            String nombreArchivo =
                    "Patrones.html";

            PrintWriter writer =
                    new PrintWriter(nombreArchivo);

            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>Patrones del Sistema</title>");

            writer.println("<style>");
            writer.println(
                    "body{font-family:Segoe UI;"
                    + "background:#f4f6f9;"
                    + "padding:30px;}"
            );

            writer.println(
                    ".card{background:white;"
                    + "padding:25px;"
                    + "border-radius:15px;"
                    + "width:1000px;"
                    + "margin:auto;"
                    + "box-shadow:0px 0px 10px gray;}"
            );

            writer.println(
                    "h1{text-align:center;"
                    + "color:#3498db;}"
            );

            writer.println(
                    "table{width:100%;"
                    + "border-collapse:collapse;"
                    + "margin-top:20px;}"
            );

            writer.println(
                    "th,td{border:1px solid #ccc;"
                    + "padding:12px;"
                    + "text-align:center;}"
            );

            writer.println(
                    "th{background:#3498db;"
                    + "color:white;}"
            );

            writer.println("</style>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<div class='card'>");

            writer.println("<h1>Patrones del Sistema</h1>");

            writer.println("<table>");

            writer.println(
                    "<tr>"
                            + "<th>Código</th>"
                            + "<th>Nombre</th>"
                            + "<th>Matriz</th>"
                            + "</tr>"
            );

            for (Patron patron :
                    controlador.getSistema().getPatrones()) {

                writer.println("<tr>");

                writer.println(
                        "<td>"
                                + patron.getCodigo()
                                + "</td>"
                );

                writer.println(
                        "<td>"
                                + patron.getNombre()
                                + "</td>"
                );

                String matriz = "<table>";

                int[][] datos =
                        patron.getPatron();

                for (int i = 0;
                        i < datos.length;
                        i++) {

                    matriz += "<tr>";

                    for (int j = 0;
                            j < datos[i].length;
                            j++) {

                        matriz +=
                                "<td>"
                                + datos[i][j]
                                + "</td>";
                    }

                    matriz += "</tr>";
                }

                matriz += "</table>";

                writer.println(
                        "<td>"
                                + matriz
                                + "</td>"
                );

                writer.println("</tr>");
            }

            writer.println("</table>");
            writer.println("</div>");
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
    
    
    public void actualizar() {

        cargarTabla();
    }
    
    
}
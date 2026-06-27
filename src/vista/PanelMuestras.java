package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import controlador.LaboratorioControlador;
import modelo.Muestra;

import java.io.File;
import java.io.PrintWriter;
import java.awt.Desktop;

import javax.swing.table.DefaultTableCellRenderer;

import java.io.BufferedReader;
import java.io.FileReader;

public class PanelMuestras extends JPanel {

    private JTable tabla;
    private DefaultTableModel modelo;

    private JButton btnCrear;
    private JButton btnCargar;
    private JButton btnVer;
    private JButton btnActualizar;
    private JButton btnEliminar;
    
    private LaboratorioControlador controlador;

    public PanelMuestras() {

        controlador = new LaboratorioControlador();

        setLayout(new BorderLayout());
        
        setBorder(
                BorderFactory.createEmptyBorder(
                        10, 10, 10, 10
                )
        );

        modelo = new DefaultTableModel();

        modelo.addColumn("Código");
        modelo.addColumn("Descripción");
        modelo.addColumn("Estado");
        modelo.addColumn("Investigador");

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
        
        
        tabla.setDefaultRenderer(Object.class,
                new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(
                    JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {

                Component c =
                        super.getTableCellRendererComponent(
                                table,
                                value,
                                isSelected,
                                hasFocus,
                                row,
                                column);
                setHorizontalAlignment(
                        SwingConstants.CENTER
                );

                String estado =
                        table.getValueAt(row, 2).toString();

                if (!isSelected) {

                    if (estado.equals("Ingreso")) {

                        c.setBackground(Color.YELLOW);

                    } else if (estado.equals("En Proceso")) {

                        c.setBackground(Color.ORANGE);

                    } else if (estado.equals("Procesado")) {

                        c.setBackground(Color.GREEN);

                    } else {

                        c.setBackground(Color.WHITE);
                    }
                }

                return c;
            }
        });

        JScrollPane scroll = new JScrollPane(tabla);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(5, 1, 10, 10));

        btnCrear = new JButton("Crear");
        btnCargar = new JButton("Cargar");
        btnVer = new JButton("Ver");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        
        Font fuenteBoton =
                new Font("Segoe UI", Font.BOLD, 14);

        btnCrear.setFont(fuenteBoton);
        btnCargar.setFont(fuenteBoton);
        btnVer.setFont(fuenteBoton);
        btnActualizar.setFont(fuenteBoton);
        btnEliminar.setFont(fuenteBoton);

        btnCrear.setBackground(new Color(46,204,113));
        btnCrear.setForeground(Color.WHITE);

        btnCargar.setBackground(new Color(52,152,219));
        btnCargar.setForeground(Color.WHITE);

        btnVer.setBackground(new Color(155,89,182));
        btnVer.setForeground(Color.WHITE);

        btnActualizar.setBackground(new Color(241,196,15));
        btnActualizar.setForeground(Color.BLACK);

        btnEliminar.setBackground(new Color(231,76,60));
        btnEliminar.setForeground(Color.WHITE);

        btnCrear.setFocusPainted(false);
        btnCargar.setFocusPainted(false);
        btnVer.setFocusPainted(false);
        btnActualizar.setFocusPainted(false);
        btnEliminar.setFocusPainted(false);
        

        panelBotones.add(btnCrear);
        panelBotones.add(btnCargar);
        panelBotones.add(btnVer);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.EAST);

        cargarTabla();

        btnCrear.addActionListener(e -> {

            CrearMuestra ventana =
                    new CrearMuestra();

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
                                    new FileReader(archivo)
                            );

                    String linea;

                    lector.readLine();

                    while ((linea = lector.readLine()) != null) {

                        String[] datos = linea.split(",");

                        String codigo = datos[0].trim();
                        String descripcion = datos[1].trim();
                        String patronTexto = datos[2].trim();
                        String estado = "Ingreso";

                        if (datos.length > 3) {
                            estado = datos[3].trim();
                        }

                        boolean existe = false;

                        for (Muestra m :
                                controlador.getSistema().getMuestras()) {

                            if (m.getCodigo().equals(codigo)) {
                                existe = true;
                                break;
                            }
                        }

                        if (existe) {
                            continue;
                        }

                        int[][] patron =
                                convertirPatron(patronTexto);

                        Muestra muestra =
                                new Muestra(
                                        codigo,
                                        descripcion,
                                        patron,
                                        estado
                                );

                        controlador.getSistema()
                                .getMuestras()
                                .add(muestra);
                    }
                    lector.close();

                    controlador.guardarDatos();

                    cargarTabla();

                    JOptionPane.showMessageDialog(
                            null,
                            "Muestras cargadas correctamente"
                    );

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Error al cargar CSV"
                    );
                }
            }
        });
        
        btnActualizar.addActionListener(e -> {

            new ActualizarMuestra();

            cargarTabla();
        });
        
        btnEliminar.addActionListener(e -> {

            EliminarMuestra ventana =
                    new EliminarMuestra();

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
                        "Seleccione una muestra"
                );

                return;
            }

            String codigo =
                    modelo.getValueAt(fila, 0).toString();

            for (Muestra muestra :
                    controlador.getSistema().getMuestras()) {

                if (muestra.getCodigo().equals(codigo)) {

                    generarHTML(muestra);
                    return;
                }
            }
        });
        
    }

    private void cargarTabla() {

        controlador = new LaboratorioControlador();

        modelo.setRowCount(0);

        for (Muestra muestra :
                controlador.getSistema().getMuestras()) {

            modelo.addRow(new Object[]{
                muestra.getCodigo(),
                muestra.getDescripcion(),
                muestra.getEstado(),
                muestra.getInvestigadorAsignado()
            });
        }
    }
    
    private void generarHTML(Muestra muestra) {

        try {

            String nombreArchivo =
                    "Muestra_" + muestra.getCodigo() + ".html";

            PrintWriter writer =
                    new PrintWriter(nombreArchivo);

            writer.println("<html>");
            
            writer.println("<head>");

            writer.println("<style>");

            writer.println(
                    "body{"
                            + "font-family:Segoe UI;"
                            + "background:#f4f6f9;"
                            + "padding:30px;"
                            + "}"
            );

            writer.println(
                    ".card{"
                            + "background:white;"
                            + "padding:30px;"
                            + "width:700px;"
                            + "margin:auto;"
                            + "border-radius:20px;"
                            + "box-shadow:0px 0px 10px gray;"
                            + "}"
            );

            writer.println(
                    "h1{"
                            + "color:#3498db;"
                            + "}"
            );

            writer.println(
                    "table{"
                            + "border-collapse:collapse;"
                            + "margin-top:20px;"
                            + "}"
            );

            writer.println(
                    "td{"
                            + "border:1px solid #ccc;"
                            + "padding:12px;"
                            + "text-align:center;"
                            + "}"
            );

            writer.println("</style>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<div class='card'>");
            
            
            writer.println("<body>");

            writer.println(
                    "<h1>Muestra</h1>"
            );

            writer.println("<h2>Código: "
                    + muestra.getCodigo()
                    + "</h2>");

            writer.println("<h2>Descripción: "
                    + muestra.getDescripcion()
                    + "</h2>");

            writer.println("<h2>Estado: "
                    + muestra.getEstado()
                    + "</h2>");

            writer.println("<table border='1'>");

            int[][] matriz = muestra.getPatron();

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
            writer.println("</div>");
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
    
    
    
    public void actualizar() {

        cargarTabla();
    }
    
}
package vista;

import controlador.LaboratorioControlador;
import modelo.Resultado;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.Desktop;
import java.io.File;
import java.io.PrintWriter;

public class PanelResultados extends JPanel {

    private JTable tabla;
    private DefaultTableModel modelo;

    private JButton btnVer;
    private JButton btnVerTodos;
    private JButton btnActualizar;

    private LaboratorioControlador controlador;

    public PanelResultados() {

        controlador = new LaboratorioControlador();

        setLayout(new BorderLayout());

        setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
        );

        modelo = new DefaultTableModel();

        modelo.addColumn("No.");
        modelo.addColumn("Muestra");
        modelo.addColumn("Patrón");
        modelo.addColumn("Fecha");
        modelo.addColumn("Hora");
        modelo.addColumn("Resultado");

        tabla = new JTable(modelo);

        tabla.setRowHeight(28);

        tabla.setFont(
                new Font("Segoe UI",
                        Font.PLAIN,
                        13)
        );

        tabla.getTableHeader().setFont(
                new Font("Segoe UI",
                        Font.BOLD,
                        14)
        );

        tabla.setDefaultEditor(
                Object.class,
                null
        );

        DefaultTableCellRenderer centro =
                new DefaultTableCellRenderer();

        centro.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        for (int i = 0;
             i < tabla.getColumnCount();
             i++) {

            tabla.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(centro);
        }

        JScrollPane scroll =
                new JScrollPane(tabla);

        add(scroll, BorderLayout.CENTER);

        btnVer =new JButton("Ver Resultado");
        btnActualizar =new JButton("Actualizar");
        btnVerTodos = new JButton("Ver Todos");

        Font fuenteBoton =
                new Font("Segoe UI",
                        Font.BOLD,
                        14);

        btnVer.setFont(fuenteBoton);
        btnActualizar.setFont(fuenteBoton);

        btnVer.setBackground(
                new Color(155,89,182));

        btnVer.setForeground(
                Color.WHITE);

        btnActualizar.setBackground(
                new Color(52,152,219));

        btnActualizar.setForeground(
                Color.WHITE);

        btnVer.setFocusPainted(false);
        btnActualizar.setFocusPainted(false);

        JPanel panelBotones =
                new JPanel();

        panelBotones.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        0,
                        0,
                        0
                )
        );

        panelBotones.add(btnVer);
        panelBotones.add(btnVerTodos);
        panelBotones.add(btnActualizar);

        add(panelBotones,
                BorderLayout.SOUTH);

        cargarResultados();

        btnActualizar.addActionListener(e -> {

            cargarResultados();
        });

        btnVer.addActionListener(e -> {

            verResultado();
        });
        
        btnVerTodos.addActionListener(e -> {
            verTodosResultados();
        });
        
    }

    private void cargarResultados() {

        controlador =
                new LaboratorioControlador();

        modelo.setRowCount(0);

        for (Resultado resultado :
                controlador.getSistema().getResultados()) {

            modelo.addRow(new Object[]{
                    resultado.getNumero(),
                    resultado.getCodigoMuestra(),
                    resultado.getCodigoPatron(),
                    resultado.getFecha(),
                    resultado.getHora(),
                    resultado.getResultado()
            });
        }
    }

    private void verResultado() {

        int fila =
                tabla.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un resultado."
            );

            return;
        }

        Resultado resultado =
                controlador.getSistema()
                        .getResultados()
                        .get(fila);

        try {

            String nombreArchivo =
                    "Resultado_"
                            + resultado.getNumero()
                            + ".html";

            PrintWriter writer =
                    new PrintWriter(
                            nombreArchivo
                    );

            writer.println("<html>");
            writer.println("<head>");
            writer.println("<style>");
            writer.println("body{font-family:Segoe UI;background:#f4f6f9;padding:30px;}");
            writer.println(".card{background:white;padding:25px;border-radius:15px;width:600px;margin:auto;box-shadow:0px 0px 10px gray;}");
            writer.println("h1{color:#2c3e50;}");
            writer.println("p{font-size:18px;}");
            writer.println("</style>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<div class='card'>");
            writer.println("</body>");
            writer.println("</html>");

            writer.println(
                    "<p><b>No:</b> "
                            + resultado.getNumero()
                            + "</p>"
            );

            writer.println(
                    "<p><b>Muestra:</b> "
                            + resultado.getCodigoMuestra()
                            + "</p>"
            );

            writer.println(
                    "<p><b>Patrón:</b> "
                            + resultado.getCodigoPatron()
                            + "</p>"
            );

            writer.println(
                    "<p><b>Fecha:</b> "
                            + resultado.getFecha()
                            + "</p>"
            );

            writer.println(
                    "<p><b>Hora:</b> "
                            + resultado.getHora()
                            + "</p>"
            );

            writer.println(
                    "<p><b>Resultado:</b> "
                            + resultado.getResultado()
                            + "</p>"
            );

            writer.println("</body>");
            writer.println("</html>");

            writer.close();

            Desktop.getDesktop().open(
                    new File(nombreArchivo)
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al generar el reporte."
            );
        }
    }
    
    
    private void verTodosResultados() {

        try {

            String nombreArchivo =
                    "Resultados.html";

            PrintWriter writer =
                    new PrintWriter(nombreArchivo);

            writer.println("<html>");
            writer.println("<head>");
            writer.println("<style>");
            writer.println("body{font-family:Segoe UI;background:#f4f6f9;padding:30px;}");
            writer.println(".card{background:white;padding:25px;border-radius:15px;width:900px;margin:auto;box-shadow:0px 0px 10px gray;}");
            writer.println("h1{text-align:center;color:#2c3e50;}");
            writer.println("table{width:100%;border-collapse:collapse;margin-top:20px;}");
            writer.println("th,td{border:1px solid #ccc;padding:12px;text-align:center;}");
            writer.println("th{background:#3498db;color:white;}");
            writer.println("</style>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<div class='card'>");

            writer.println("<h1>Resultados del Sistema</h1>");

            writer.println("<table>");

            writer.println(
                    "<tr>"
                            + "<th>No.</th>"
                            + "<th>Muestra</th>"
                            + "<th>Patrón</th>"
                            + "<th>Fecha</th>"
                            + "<th>Hora</th>"
                            + "<th>Resultado</th>"
                            + "</tr>"
            );

            for (Resultado resultado :
                    controlador.getSistema().getResultados()) {

                writer.println(
                        "<tr>"
                                + "<td>" + resultado.getNumero() + "</td>"
                                + "<td>" + resultado.getCodigoMuestra() + "</td>"
                                + "<td>" + resultado.getCodigoPatron() + "</td>"
                                + "<td>" + resultado.getFecha() + "</td>"
                                + "<td>" + resultado.getHora() + "</td>"
                                + "<td>" + resultado.getResultado() + "</td>"
                                + "</tr>"
                );
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
                    this,
                    "Error al generar el reporte."
            );
        }
    }
    

    public void actualizar() {

        cargarResultados();
    }
}
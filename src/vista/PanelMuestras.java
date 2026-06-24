package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import controlador.LaboratorioControlador;
import modelo.Muestra;

import java.io.File;
import java.io.PrintWriter;
import java.awt.Desktop;

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

        modelo = new DefaultTableModel();

        modelo.addColumn("Código");
        modelo.addColumn("Descripción");
        modelo.addColumn("Estado");
        modelo.addColumn("Investigador");

        tabla = new JTable(modelo);
        tabla.setRowHeight(25);
        
        tabla.setDefaultRenderer(Object.class,
                new javax.swing.table.DefaultTableCellRenderer() {

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

        panelBotones.add(btnCrear);
        panelBotones.add(btnCargar);
        panelBotones.add(btnVer);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.EAST);

        cargarTabla();

        btnCrear.addActionListener(e -> {
            new CrearMuestra();
        });

        btnCargar.addActionListener(e -> {
            cargarTabla();
        });
        
        btnActualizar.addActionListener(e -> {
            new ActualizarMuestra();
        });
        
        btnEliminar.addActionListener(e -> {
            new EliminarMuestra();
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
            writer.println("<body>");

            writer.println("<h1>Muestra</h1>");

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
    
}
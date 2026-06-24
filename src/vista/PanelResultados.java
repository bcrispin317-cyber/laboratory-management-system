package vista;

import controlador.LaboratorioControlador;
import modelo.Resultado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import java.io.File;
import java.io.PrintWriter;
import java.awt.Desktop;

public class PanelResultados extends JPanel {

    private JTable tabla;
    private DefaultTableModel modelo;
    private JButton btnVer;

    private LaboratorioControlador controlador;

    public PanelResultados() {

        controlador = new LaboratorioControlador();

        setLayout(new BorderLayout());

        modelo = new DefaultTableModel();

        modelo.addColumn("No.");
        modelo.addColumn("Muestra");
        modelo.addColumn("Patrón");
        modelo.addColumn("Fecha");
        modelo.addColumn("Hora");
        modelo.addColumn("Resultado");

        tabla = new JTable(modelo);

        JScrollPane scroll =
                new JScrollPane(tabla);
        
        btnVer = new JButton("Ver Resultado");

        JPanel panel = new JPanel();

        panel.add(btnVer);

        add(panel, BorderLayout.SOUTH);

        add(scroll, BorderLayout.CENTER);

        cargarResultados();
        
        btnVer.addActionListener(e -> verResultado());
    }

    private void cargarResultados() {

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

        int fila = tabla.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un resultado"
            );

            return;
        }

        Resultado resultado =
                controlador.getSistema()
                        .getResultados()
                        .get(fila);

        try {

            String nombreArchivo =
                    "Resultado_" +
                            resultado.getNumero() +
                            ".html";

            PrintWriter writer =
                    new PrintWriter(nombreArchivo);

            writer.println("<html>");
            writer.println("<body>");

            writer.println("<h1>Resultado del Análisis</h1>");

            writer.println("<p><b>No:</b> "
                    + resultado.getNumero()
                    + "</p>");

            writer.println("<p><b>Muestra:</b> "
                    + resultado.getCodigoMuestra()
                    + "</p>");

            writer.println("<p><b>Patrón:</b> "
                    + resultado.getCodigoPatron()
                    + "</p>");

            writer.println("<p><b>Fecha:</b> "
                    + resultado.getFecha()
                    + "</p>");

            writer.println("<p><b>Hora:</b> "
                    + resultado.getHora()
                    + "</p>");

            writer.println("<p><b>Resultado:</b> "
                    + resultado.getResultado()
                    + "</p>");

            writer.println("</body>");
            writer.println("</html>");

            writer.close();

            Desktop.getDesktop().open(
                    new File(nombreArchivo)
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al generar HTML"
            );
        }
    }
}
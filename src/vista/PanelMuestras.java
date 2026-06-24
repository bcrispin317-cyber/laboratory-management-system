package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import controlador.LaboratorioControlador;
import modelo.Muestra;

public class PanelMuestras extends JPanel {

    private JTable tabla;
    private DefaultTableModel modelo;

    private JButton btnCrear;
    private JButton btnCargar;
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

        JScrollPane scroll = new JScrollPane(tabla);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(4, 1, 10, 10));

        btnCrear = new JButton("Crear");
        btnCargar = new JButton("Cargar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");

        panelBotones.add(btnCrear);
        panelBotones.add(btnCargar);
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
}
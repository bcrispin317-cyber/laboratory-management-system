package vista;

import controlador.LaboratorioControlador;
import modelo.Patron;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelPatrones extends JPanel {

    private JTable tabla;
    private DefaultTableModel modelo;

    private JButton btnCrear;
    private JButton btnCargar;
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
        panelBotones.setLayout(new GridLayout(3, 1, 10, 10));

        btnCrear = new JButton("Crear");
        btnCargar = new JButton("Cargar");
        btnEliminar = new JButton("Eliminar");

        panelBotones.add(btnCrear);
        panelBotones.add(btnCargar);
        panelBotones.add(btnEliminar);

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.EAST);

        cargarTabla();

        btnCrear.addActionListener(e -> {
            new CrearPatron();
        });

        btnCargar.addActionListener(e -> {
            cargarTabla();
        });

        btnEliminar.addActionListener(e -> {
            new EliminarPatron();
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
}
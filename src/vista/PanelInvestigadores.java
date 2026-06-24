package vista;

import controlador.LaboratorioControlador;
import modelo.Investigador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

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

        modelo = new DefaultTableModel();

        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("Género");
        modelo.addColumn("Experimentos");

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

        btnCrear.addActionListener((ActionEvent e) -> {
            new CrearInvestigador();
        });

        btnCargar.addActionListener((ActionEvent e) -> {
            cargarTabla();
        });
    }

    private void cargarTabla() {

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
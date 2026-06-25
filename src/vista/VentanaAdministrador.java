package vista;

import javax.swing.*;
import java.awt.*;

public class VentanaAdministrador extends JFrame {

    private JTabbedPane pestañas;

    private PanelInvestigadores panelInvestigadores;
    private PanelMuestras panelMuestras;
    private PanelAsignacion panelAsignacion;
    private PanelPatrones panelPatrones;
    private PanelEstadisticas panelEstadisticas;

    private JButton btnCerrarSesion;

    public VentanaAdministrador() {

        setTitle("Administrador - IPC Quimik");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        pestañas = new JTabbedPane();

        panelInvestigadores = new PanelInvestigadores();
        panelMuestras = new PanelMuestras();
        panelAsignacion = new PanelAsignacion();
        panelPatrones = new PanelPatrones();
        panelEstadisticas = new PanelEstadisticas();

        pestañas.addTab(
                "Investigadores",
                panelInvestigadores
        );

        pestañas.addTab(
                "Muestras",
                panelMuestras
        );

        pestañas.addTab(
                "Asignación de Experimentos",
                panelAsignacion
        );

        pestañas.addTab(
                "Patrones",
                panelPatrones
        );

        pestañas.addTab(
                "Estadísticas",
                panelEstadisticas
        );

        add(pestañas, BorderLayout.CENTER);

        btnCerrarSesion = new JButton("Cerrar Sesión");

        add(btnCerrarSesion, BorderLayout.SOUTH);

        btnCerrarSesion.addActionListener(e -> {

            dispose();

            new Login();
        });

        pestañas.addChangeListener(e -> {
            actualizarPaneles();
        });

        setVisible(true);
    }

    private void actualizarPaneles() {

        panelInvestigadores.actualizar();
        panelMuestras.actualizar();
        panelAsignacion.actualizar();
        panelPatrones.actualizar();
        panelEstadisticas.actualizar();
    }
}
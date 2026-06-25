package vista;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class VentanaAdministrador extends JFrame {

    private JTabbedPane pestañas;

    public VentanaAdministrador() {

        setTitle("Administrador - IPC Quimik");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pestañas = new JTabbedPane();

        pestañas.addTab("Investigadores", new PanelInvestigadores());
        pestañas.addTab("Muestras", new PanelMuestras());
        pestañas.addTab("Asignación de Experimentos", new PanelAsignacion());
        pestañas.addTab("Patrones", new PanelPatrones());
        pestañas.addTab(
                "Estadísticas",
                new PanelEstadisticas()
        );

        add(pestañas);

        setVisible(true);
    }
}
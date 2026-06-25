package vista;

import modelo.Investigador;

import javax.swing.*;
import java.awt.*;

public class VentanaInvestigador extends JFrame {

    private JTabbedPane pestanias;

    private JButton btnCerrarSesion;

    private Investigador investigador;

    private PanelAnalisis panelAnalisis;
    private PanelResultados panelResultados;

    public VentanaInvestigador(Investigador investigador) {

        this.investigador = investigador;

        setTitle("Investigación - IPC Quimik");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        pestanias = new JTabbedPane();

        panelAnalisis = new PanelAnalisis(investigador);
        panelResultados = new PanelResultados();

        pestanias.addTab(
                "Análisis",
                panelAnalisis
        );

        pestanias.addTab(
                "Resultados",
                panelResultados
        );

        pestanias.addChangeListener(e -> {

            if (pestanias.getSelectedIndex() == 1) {

                panelResultados.actualizar();
            }
        });

        add(pestanias, BorderLayout.CENTER);

        btnCerrarSesion =
                new JButton("Cerrar Sesión");

        add(btnCerrarSesion, BorderLayout.SOUTH);

        btnCerrarSesion.addActionListener(e -> {

            dispose();

            new Login();
        });

        setVisible(true);
    }
}
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

        setTitle("Investigador - IPC Quimik");
        setSize(760,520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        pestanias = new JTabbedPane();

        pestanias.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

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

        btnCerrarSesion.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        btnCerrarSesion.setBackground(
                new Color(231, 76, 60)
        );

        btnCerrarSesion.setForeground(
                Color.WHITE
        );

        btnCerrarSesion.setFocusPainted(false);

        add(btnCerrarSesion, BorderLayout.SOUTH);

        btnCerrarSesion.addActionListener(e -> {

            dispose();

            new Login();
        });

        setVisible(true);
    }
}
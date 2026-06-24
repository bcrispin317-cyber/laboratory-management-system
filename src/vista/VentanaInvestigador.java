package vista;

import modelo.Investigador;

import javax.swing.*;
import java.awt.*;

public class VentanaInvestigador extends JFrame {

    private JTabbedPane pestanias;

    private JButton btnCerrarSesion;

    private Investigador investigador;

    public VentanaInvestigador(Investigador investigador) {

        this.investigador = investigador;

        setTitle("Investigación - IPC Quimik");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        pestanias = new JTabbedPane();

        pestanias.addTab(
                "Análisis",
                new PanelAnalisis(investigador)
        );
        
        pestanias.addTab(
                "Resultados",
                new PanelResultados()
        );

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
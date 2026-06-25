package vista;

import controlador.LaboratorioControlador;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PanelEstadisticas extends JPanel {

    private JLabel lblInvestigadores;
    private JLabel lblMuestras;
    private JLabel lblPatrones;
    private JLabel lblResultados;

    private LaboratorioControlador controlador;

    public PanelEstadisticas() {

        controlador = new LaboratorioControlador();

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        //---------------- TITULO ----------------

        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setBackground(Color.WHITE);

        JLabel titulo = new JLabel(
                "ESTADÍSTICAS DEL SISTEMA",
                SwingConstants.CENTER
        );

        titulo.setFont(
                new Font("Segoe UI", Font.BOLD, 26)
        );

        JLabel subtitulo = new JLabel(
                "Resumen general del sistema",
                SwingConstants.CENTER
        );

        subtitulo.setFont(
                new Font("Segoe UI", Font.PLAIN, 16)
        );

        subtitulo.setForeground(Color.GRAY);

        panelTitulo.add(titulo, BorderLayout.CENTER);
        panelTitulo.add(subtitulo, BorderLayout.SOUTH);

        add(panelTitulo, BorderLayout.NORTH);

        //---------------- TARJETAS ----------------

        JPanel panelCentro = new JPanel(
                new GridLayout(1,4,18,18)
        );

        panelCentro.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        30,
                        25,
                        30
                )
        );

        panelCentro.setBackground(Color.WHITE);

        lblInvestigadores = new JLabel();
        lblMuestras = new JLabel();
        lblPatrones = new JLabel();
        lblResultados = new JLabel();

        panelCentro.add(
                crearTarjeta(
                        "INVESTIGADORES",
                        lblInvestigadores,
                        new Color(52,152,219)
                )
        );

        panelCentro.add(
                crearTarjeta(
                        "MUESTRAS",
                        lblMuestras,
                        new Color(46,204,113)
                )
        );

        panelCentro.add(
                crearTarjeta(
                        "PATRONES",
                        lblPatrones,
                        new Color(155,89,182)
                )
        );

        panelCentro.add(
                crearTarjeta(
                        "RESULTADOS",
                        lblResultados,
                        new Color(243,156,18)
                )
        );

        add(panelCentro, BorderLayout.CENTER);

        actualizar();
    }

    private JPanel crearTarjeta(
            String titulo,
            JLabel valor,
            Color color) {

        JPanel tarjeta = new JPanel(new BorderLayout());

        tarjeta.setBackground(Color.WHITE);

        tarjeta.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                new Color(220,220,220),
                                1,
                                true
                        ),
                        BorderFactory.createEmptyBorder(
                                15,
                                15,
                                15,
                                15
                        )
                )
        );

        JPanel centro = new JPanel(
                new GridLayout(2,1)
        );

        centro.setOpaque(false);

        JLabel lblTitulo =
                new JLabel(
                        titulo,
                        SwingConstants.CENTER
                );

        lblTitulo.setForeground(color);

        lblTitulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        valor.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        valor.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        38
                )
        );

        valor.setForeground(color);

        centro.add(lblTitulo);
        centro.add(valor);

        JPanel barra = new JPanel();
        barra.setBackground(color);
        barra.setPreferredSize(
                new Dimension(0,8)
        );

        tarjeta.add(centro, BorderLayout.CENTER);
        tarjeta.add(barra, BorderLayout.SOUTH);

        return tarjeta;
    }

    public void actualizar() {

        controlador = new LaboratorioControlador();

        lblInvestigadores.setText(
                String.valueOf(
                        controlador.getSistema()
                                .getInvestigadores()
                                .size()
                )
        );

        lblMuestras.setText(
                String.valueOf(
                        controlador.getSistema()
                                .getMuestras()
                                .size()
                )
        );

        lblPatrones.setText(
                String.valueOf(
                        controlador.getSistema()
                                .getPatrones()
                                .size()
                )
        );

        lblResultados.setText(
                String.valueOf(
                        controlador.getSistema()
                                .getResultados()
                                .size()
                )
        );
    }
}
package vista;

import controlador.LaboratorioControlador;

import javax.swing.*;
import java.awt.*;

public class PanelEstadisticas extends JPanel {

    private JLabel lblInvestigadores;
    private JLabel lblMuestras;
    private JLabel lblPatrones;
    private JLabel lblResultados;

    private LaboratorioControlador controlador;

    public PanelEstadisticas() {

        controlador = new LaboratorioControlador();

        setLayout(new GridLayout(4,1,10,10));

        lblInvestigadores = new JLabel();
        lblMuestras = new JLabel();
        lblPatrones = new JLabel();
        lblResultados = new JLabel();

        add(lblInvestigadores);
        add(lblMuestras);
        add(lblPatrones);
        add(lblResultados);

        actualizar();
    }

    public void actualizar() {

        lblInvestigadores.setText(
                "Investigadores: "
                + controlador.getSistema()
                .getInvestigadores().size());

        lblMuestras.setText(
                "Muestras: "
                + controlador.getSistema()
                .getMuestras().size());

        lblPatrones.setText(
                "Patrones: "
                + controlador.getSistema()
                .getPatrones().size());

        lblResultados.setText(
                "Resultados: "
                + controlador.getSistema()
                .getResultados().size());
    }
}
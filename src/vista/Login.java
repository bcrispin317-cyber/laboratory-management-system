package vista;

import controlador.LaboratorioControlador;
import modelo.Investigador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

    private JTextField txtCodigo;
    private JPasswordField txtContrasenia;
    private JButton btnIngresar;

    private LaboratorioControlador controlador;

    public Login() {

        controlador = new LaboratorioControlador();

        Font fuente = new Font("Segoe UI", Font.PLAIN, 15);
        Font titulo = new Font("Segoe UI", Font.BOLD, 16);

        setTitle("IPC Quimik");
        setSize(500, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        JPanel panelTitulo = new JPanel(new GridLayout(2, 1));
        panelTitulo.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel("IPC Quimik");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));

        JLabel lblSubtitulo = new JLabel("Sistema de Laboratorio");
        lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        panelTitulo.add(lblTitulo);
        panelTitulo.add(lblSubtitulo);

        add(panelTitulo, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 15, 15));
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );

        JLabel lblCodigo = new JLabel("Código");
        lblCodigo.setFont(titulo);
        panelFormulario.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setFont(fuente);
        panelFormulario.add(txtCodigo);

        JLabel lblContrasenia = new JLabel("Contraseña");
        lblContrasenia.setFont(titulo);
        panelFormulario.add(lblContrasenia);

        txtContrasenia = new JPasswordField();
        txtContrasenia.setFont(fuente);
        panelFormulario.add(txtContrasenia);

        panelFormulario.add(new JLabel());

        btnIngresar = new JButton("Iniciar Sesión");
        btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnIngresar.setBackground(new Color(52, 152, 219));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFocusPainted(false);

        panelFormulario.add(btnIngresar);

        add(panelFormulario, BorderLayout.CENTER);

        btnIngresar.addActionListener((ActionEvent e) -> {
            iniciarSesion();
        });

        setVisible(true);
    }

    private void iniciarSesion() {

        controlador = new LaboratorioControlador();

        String codigo = txtCodigo.getText().trim();
        String pass = new String(
                txtContrasenia.getPassword()
        ).trim();

        if (codigo.isEmpty() || pass.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Complete todos los campos."
            );

            return;
        }

        if (codigo.equals("admin")
                && pass.equals("admin")) {

            new VentanaAdministrador();
            dispose();
            return;
        }

        for (Investigador investigador :
                controlador.getSistema().getInvestigadores()) {

            if (investigador.getCodigo().equals(codigo)
                    && investigador.getContrasenia().equals(pass)) {

                new VentanaInvestigador(investigador);

                dispose();
                return;
            }
        }

        JOptionPane.showMessageDialog(
                this,
                "Código o contraseña incorrectos."
        );
    }
}
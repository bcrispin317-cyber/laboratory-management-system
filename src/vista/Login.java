package vista;

import controlador.LaboratorioControlador;
import modelo.Investigador;
import vista.VentanaAdministrador;
import vista.VentanaInvestigador;

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

        setTitle("IPC Quimik");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Código"));
        txtCodigo = new JTextField();
        add(txtCodigo);

        add(new JLabel("Contraseña"));
        txtContrasenia = new JPasswordField();
        add(txtContrasenia);

        add(new JLabel());

        btnIngresar = new JButton("Iniciar Sesión");
        add(btnIngresar);

        btnIngresar.addActionListener((ActionEvent e) -> {
            iniciarSesion();
        });

        setVisible(true);
    }

    private void iniciarSesion() {

        String codigo = txtCodigo.getText();
        String pass = new String(txtContrasenia.getPassword());

        if (codigo.equals("admin") && pass.equals("admin")) {

            new VentanaAdministrador();

            dispose();

            return;
        }

        for (Investigador i : controlador.getSistema().getInvestigadores()) {

            if (i.getCodigo().equals(codigo)
                    && i.getContrasenia().equals(pass)) {

                new VentanaInvestigador(i);

                dispose();

                return;
            }
        }

        JOptionPane.showMessageDialog(this,
                "Credenciales incorrectas");
    }
}
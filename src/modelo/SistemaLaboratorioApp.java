package modelo;

import vista.Login;

import javax.swing.UIManager;

public class SistemaLaboratorioApp {

    public static void main(String[] args) {

        try {

            for (UIManager.LookAndFeelInfo info :
                    UIManager.getInstalledLookAndFeels()) {

                if ("Nimbus".equals(info.getName())) {

                    UIManager.setLookAndFeel(
                            info.getClassName()
                    );

                    break;
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        new Login();
    }
}
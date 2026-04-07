package br.com.tcgbrasil;

import br.com.tcgbrasil.view.Login;

public class Main {

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {

            Login login = new Login();
            login.setVisible(true);

        });

    }
}
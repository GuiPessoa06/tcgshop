package br.com.tcgbrasil;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexaoJDBC {

    private static final String URL = "jdbc:mysql://localhost:3306/tcg_shop";
    private static final String USER = "root";
    private static final String PASS = "3a9xwtjA!";

    public static Connection getConnection() {

        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            throw new RuntimeException("Erro na conexão: " + e);
        }
    }
}
package br.com.tcgbrasil.dao;

import br.com.tcgbrasil.conexaoJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.com.tcgbrasil.model.Usuario;
import java.sql.Statement;

public class UsuarioDAO {

    public boolean login(String email, String senha) {

        String sql = "SELECT * FROM usuario WHERE email=? AND senha=?";

        try ( Connection conn = conexaoJDBC.getConnection();  PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public void cadastrar(Usuario u) {

        try ( Connection conn = conexaoJDBC.getConnection()) {

            // 1?? Inserir Jogador
            String sqlJogador = "INSERT INTO Jogador (Nome) VALUES (?)";

            PreparedStatement stmtJogador = conn.prepareStatement(sqlJogador, Statement.RETURN_GENERATED_KEYS);
            stmtJogador.setString(1, u.getEmail()); // pode trocar depois

            stmtJogador.execute();

            ResultSet rs = stmtJogador.getGeneratedKeys();
            rs.next();
            int idJogador = rs.getInt(1);

            // 2?? Inserir Usuario
            String sqlUsuario = "INSERT INTO Usuario (idJogador, Email, Senha, CPF, Endereco, Telefone) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement stmtUsuario = conn.prepareStatement(sqlUsuario);

            stmtUsuario.setInt(1, idJogador);
            stmtUsuario.setString(2, u.getEmail());
            stmtUsuario.setString(3, u.getSenha());
            stmtUsuario.setString(4, u.getCpf());
            stmtUsuario.setString(5, u.getEndereco());
            stmtUsuario.setString(6, u.getTelefone());

            stmtUsuario.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


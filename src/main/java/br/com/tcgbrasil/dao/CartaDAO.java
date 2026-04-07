package br.com.tcgbrasil.dao;

import br.com.tcgbrasil.model.Cartas;
import br.com.tcgbrasil.conexaoJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartaDAO {

    public void inserir(Cartas c) {

        String sql = "INSERT INTO Cartas (Nome,Preco) VALUES (?, ?)";

        try ( Connection conn = conexaoJDBC.getConnection();  PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, c.getNome());
            stmt.setDouble(2, c.getPreco());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                c.setIdCarta(rs.getInt(1));
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir carta", e);
        }
    }

    public List<Cartas> listar() {

        List<Cartas> lista = new ArrayList<>();

        String sql = "SELECT * FROM Cartas";

        try ( Connection conn = conexaoJDBC.getConnection();  PreparedStatement stmt = conn.prepareStatement(sql);  ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cartas c = new Cartas(
                        rs.getInt("idCarta"),
                        rs.getString("Nome"),
                        rs.getDouble("Preco")
                );
                lista.add(c);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar cartas", e);
        }

        return lista;
    }

    public Cartas buscarPorId(int id) {

        String sql = "SELECT * FROM Cartas WHERE idCarta = ?";

        try ( Connection conn = conexaoJDBC.getConnection();  PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Cartas(
                        rs.getInt("idCarta"),
                        rs.getString("Nome"),
                        rs.getDouble("Preco")
                );
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}

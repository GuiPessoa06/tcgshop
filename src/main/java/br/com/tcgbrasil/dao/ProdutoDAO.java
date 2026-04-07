package br.com.tcgbrasil.dao;

import br.com.tcgbrasil.model.Produtos;
import br.com.tcgbrasil.conexaoJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void inserir(Produtos p) {

        String sql = "INSERT INTO produtos (nome, preco) VALUES (?, ?)";

        try (Connection conn = conexaoJDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {


            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPreco());

            stmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Produtos> listar() {

        List<Produtos> lista = new ArrayList<>();

        String sql = "SELECT * FROM produtos";

        try (Connection conn = conexaoJDBC.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Produtos p = new Produtos(
                        rs.getInt("idProduto"),
                        rs.getString("nome"),
                        rs.getDouble("preco")
                );

                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
}
package br.com.tcgbrasil.dao;

import br.com.tcgbrasil.model.ItemCarrinho;
import br.com.tcgbrasil.conexaoJDBC;
import br.com.tcgbrasil.model.Pedido;
import br.com.tcgbrasil.model.Produtos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public void salvarPedido(List<ItemCarrinho> itens) {

        String sqlPedido = "INSERT INTO Pedidos () VALUES ()";

        try ( Connection conn = conexaoJDBC.getConnection()) {

            // 1. CRIA PEDIDO
            PreparedStatement stmtPedido = conn.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS);
            stmtPedido.executeUpdate();

            ResultSet rs = stmtPedido.getGeneratedKeys();
            rs.next();
            int idPedido = rs.getInt(1);

            // 2. INSERE ITENS
            String sqlItem = "INSERT INTO ItensPedido (idPedido, idProduto, idCarta, Quantidade) VALUES (?, ?, ?, ?)";

            PreparedStatement stmtItem = conn.prepareStatement(sqlItem);

            for (ItemCarrinho item : itens) {

                stmtItem.setInt(1, idPedido);

                if (item.getProduto() != null) {
                    stmtItem.setInt(2, item.getProduto().getIdProduto());
                    stmtItem.setNull(3, java.sql.Types.INTEGER);
                } else {
                    stmtItem.setNull(2, java.sql.Types.INTEGER);
                    stmtItem.setInt(3, item.getCarta().getIdCarta());
                }

                stmtItem.setInt(4, item.getQuantidade());

                stmtItem.executeUpdate();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Pedido> listar() {

        List<Pedido> lista = new ArrayList<>();

        String sql = "SELECT * FROM Pedidos";

        try ( Connection conn = conexaoJDBC.getConnection();  PreparedStatement stmt = conn.prepareStatement(sql);  ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Pedido p = new Pedido(
                        rs.getInt("idPedido"),
                        rs.getTimestamp("dataPedido")
                );

                lista.add(p);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar pedidos", e);
        }

        return lista;
    }

    public List<ItemCarrinho> listarItens(int idPedido) {

        List<ItemCarrinho> lista = new ArrayList<>();

        String sql = """
        SELECT i.quantidade, p.idProduto, p.nome, p.preco
        FROM itenspedido i
        LEFT JOIN produtos p ON i.idProduto = p.idProduto
        WHERE i.idPedido = ?
    """;

        try ( Connection conn = conexaoJDBC.getConnection();  PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPedido);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Produtos produto = new Produtos(
                        rs.getInt("idProduto"),
                        rs.getString("nome"),
                        rs.getDouble("preco")
                );

                ItemCarrinho item = new ItemCarrinho(
                        produto,
                        rs.getInt("quantidade")
                );

                lista.add(item);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return lista;
    }
}

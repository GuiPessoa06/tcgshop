package br.com.tcgbrasil.view;

import br.com.tcgbrasil.model.Pedido;
import br.com.tcgbrasil.dao.PedidoDAO;
import br.com.tcgbrasil.model.ItemCarrinho;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Pedidos extends JFrame {

    private DefaultTableModel modelPedidos;
    private DefaultTableModel modelItens;

    private JTable tabelaPedidos;
    private JTable tabelaItens;

    public Pedidos() {

        setTitle("Pedidos");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // =========================
        // TABELA PEDIDOS
        // =========================
        modelPedidos = new DefaultTableModel(
                new Object[]{"ID", "Data"}, 0
        );

        tabelaPedidos = new JTable(modelPedidos);

        // =========================
        // TABELA ITENS
        // =========================
        modelItens = new DefaultTableModel(
                new Object[]{"Produto", "Quantidade", "Total"}, 0
        );

        tabelaItens = new JTable(modelItens);

        // =========================
        // SPLIT (DIVISÃO)
        // =========================
        JSplitPane split = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                new JScrollPane(tabelaPedidos),
                new JScrollPane(tabelaItens)
        );

        split.setDividerLocation(200);

        add(split, BorderLayout.CENTER);

        atualizarTabela();

        // =========================
        // EVENTO AO CLICAR NO PEDIDO
        // =========================
        tabelaPedidos.getSelectionModel().addListSelectionListener(e -> {

            int linha = tabelaPedidos.getSelectedRow();

            if (linha >= 0) {

                int idPedido = (int) tabelaPedidos.getValueAt(linha, 0);

                carregarItens(idPedido);
            }
        });
    }

    // =========================
    // CARREGAR PEDIDOS
    // =========================
    private void atualizarTabela() {

        modelPedidos.setRowCount(0);

        PedidoDAO dao = new PedidoDAO();

        for (Pedido p : dao.listar()) {

            modelPedidos.addRow(new Object[]{
                p.getIdPedido(),
                p.getDataPedido()
            });
        }
    }

    // =========================
    // CARREGAR ITENS
    // =========================
    private void carregarItens(int idPedido) {

    modelItens.setRowCount(0);

    PedidoDAO dao = new PedidoDAO();

    for (ItemCarrinho item : dao.listarItens(idPedido)) {

        String nome;

        if (item.getProduto() != null) {
            nome = item.getProduto().getNome();
        } else {
            nome = item.getCarta().getNome();
        }

        modelItens.addRow(new Object[]{
            nome,
            item.getQuantidade(),
            item.getTotal()
        });
    }
}
}

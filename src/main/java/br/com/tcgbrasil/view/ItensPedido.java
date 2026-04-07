package br.com.tcgbrasil.view;

import br.com.tcgbrasil.dao.PedidoDAO;
import br.com.tcgbrasil.model.ItemCarrinho;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ItensPedido extends JFrame {

    public ItensPedido(int idPedido) {

        setTitle("Itens do Pedido #" + idPedido);
        setSize(500, 300);
        setLocationRelativeTo(null);

        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Produto", "Qtd", "Preço", "Total"}, 0
        );

        JTable tabela = new JTable(model);

        PedidoDAO dao = new PedidoDAO();

        for (ItemCarrinho item : dao.listarItens(idPedido)) {

            model.addRow(new Object[]{
                    item.getProduto().getNome(),
                    item.getQuantidade(),
                    item.getProduto().getPreco(),
                    item.getTotal()
            });
        }

        add(new JScrollPane(tabela), BorderLayout.CENTER);
    }
}
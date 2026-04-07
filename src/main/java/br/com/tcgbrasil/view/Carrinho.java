package br.com.tcgbrasil.view;

import br.com.tcgbrasil.model.ItemCarrinho;
import br.com.tcgbrasil.dao.CarrinhoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Carrinho extends JFrame {

    public Carrinho() {

        setTitle("Carrinho");
        setSize(600, 400);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Produto", "Qtd", "Total"}, 0);

        JTable tabela = new JTable(model);

        for (ItemCarrinho item : CarrinhoService.listar()) {

            String nome;

            if (item.getProduto() != null) {
                nome = item.getProduto().getNome();
            } else {
                nome = item.getCarta().getNome();
            }

            model.addRow(new Object[]{
                nome,
                item.getQuantidade(),
                item.getTotal()
            });
        }

        JLabel total = new JLabel("Total: R$ " + CarrinhoService.total());

        JButton finalizar = new JButton("Finalizar Pedido");

        add(new JScrollPane(tabela), BorderLayout.CENTER);
        add(total, BorderLayout.NORTH);
        add(finalizar, BorderLayout.SOUTH);

        finalizar.addActionListener(e -> finalizar());
    }

    private void finalizar() {

        new Pagamento().setVisible(true);
        dispose();
    }
}

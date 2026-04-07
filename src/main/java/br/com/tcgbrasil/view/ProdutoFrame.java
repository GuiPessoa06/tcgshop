package br.com.tcgbrasil.view;

import br.com.tcgbrasil.model.Produtos;
import br.com.tcgbrasil.dao.ProdutoDAO;
import br.com.tcgbrasil.dao.CarrinhoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProdutoFrame extends JFrame {

    private DefaultTableModel model;

    public ProdutoFrame() {

        setTitle("Produtos");
        setSize(600, 400);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        model = new DefaultTableModel(new Object[]{"Nome", "Preço"}, 0);
        JTable tabela = new JTable(model);

        atualizarTabela();

        JScrollPane scroll = new JScrollPane(tabela);

        // BOTÕES
        JButton addBtn = new JButton("Novo Produto");
        JButton carrinhoBtn = new JButton("Adicionar ao Carrinho");

        JPanel top = new JPanel();
        top.add(addBtn);
        top.add(carrinhoBtn);

        add(top, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // AÇÕES
        addBtn.addActionListener(e -> cadastrarProduto());

        carrinhoBtn.addActionListener(e -> {
            int linha = tabela.getSelectedRow();
            if (linha >= 0) {
                ProdutoDAO dao = new ProdutoDAO();
                Produtos p = dao.listar().get(linha);
                CarrinhoService.adicionarProduto(p);
                JOptionPane.showMessageDialog(this, "Adicionado ao carrinho!");
            }
        });
    }

    private void cadastrarProduto() {

        String nome = JOptionPane.showInputDialog("Nome:");
        String precoStr = JOptionPane.showInputDialog("Preço:");

        double preco = Double.parseDouble(precoStr);

        ProdutoDAO dao = new ProdutoDAO();
        dao.inserir(new Produtos(nome, preco));

        atualizarTabela();
    }

    private void atualizarTabela() {

        model.setRowCount(0);

        ProdutoDAO dao = new ProdutoDAO();

        for (Produtos p : dao.listar()) {
            model.addRow(new Object[]{p.getNome(), p.getPreco()});
        }
    }
}

package br.com.tcgbrasil.view;

import br.com.tcgbrasil.model.Cartas;
import br.com.tcgbrasil.dao.CartaDAO;
import br.com.tcgbrasil.dao.CarrinhoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CartasFrame extends JFrame {

    private DefaultTableModel model;
    private JTable tabela;

    public CartasFrame() {

        setTitle("Cartas");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // TABELA
        model = new DefaultTableModel(
                new Object[]{"ID", "Nome", "Preço"}, 0
        );

        tabela = new JTable(model);

        atualizarTabela();

        JScrollPane scroll = new JScrollPane(tabela);

        // BOTÕES
        JButton addBtn = new JButton("Nova Carta");
        JButton carrinhoBtn = new JButton("Adicionar ao Carrinho");

        JPanel top = new JPanel();
        top.add(addBtn);
        top.add(carrinhoBtn);

        add(top, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // AÇÕES
        addBtn.addActionListener(e -> cadastrarCarta());
        carrinhoBtn.addActionListener(e -> adicionarAoCarrinho());
    }

    // =========================
    // CADASTRAR CARTA
    // =========================
    private void cadastrarCarta() {

        String nome = JOptionPane.showInputDialog("Nome:");
        String precoStr = JOptionPane.showInputDialog("Preço:");

        double preco = Double.parseDouble(precoStr);

        Cartas c = new Cartas(nome, preco);

        CartaDAO dao = new CartaDAO();
        dao.inserir(c);

        atualizarTabela();
    }

    // =========================
    // ADICIONAR AO CARRINHO
    // =========================
    private void adicionarAoCarrinho() {

        int linha = tabela.getSelectedRow();

        if (linha >= 0) {

            int id = (int) tabela.getValueAt(linha, 0);
            String nome = (String) tabela.getValueAt(linha, 1);
            double preco = (double) tabela.getValueAt(linha, 2); // ?? era 3 (errado)

            Cartas c = new Cartas(id, nome, preco);

            CarrinhoService.adicionarCarta(c);

            JOptionPane.showMessageDialog(this, "Adicionado ao carrinho!");
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma carta!");
        }
    }

    // =========================
    // ATUALIZAR TABELA
    // =========================
    private void atualizarTabela() {

        model.setRowCount(0);

        CartaDAO dao = new CartaDAO();

        for (Cartas c : dao.listar()) {
            model.addRow(new Object[]{
                    c.getIdCarta(),
                    c.getNome(),
                    c.getPreco()
            });
        }
    }
}
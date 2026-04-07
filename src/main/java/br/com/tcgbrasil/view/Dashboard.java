package br.com.tcgbrasil.view;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    public Dashboard() {

        setTitle("TCG Brasil - Dashboard");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // MENU SUPERIOR
        JPanel menu = new JPanel();
        menu.setLayout(new FlowLayout());

        JButton produtosBtn = new JButton("Produtos");
        JButton cartasBtn = new JButton("Cartas");
        JButton pedidosBtn = new JButton("Pedidos");
        JButton carrinhoBtn = new JButton("Carrinho");
        JButton sairBtn = new JButton("Sair");

        menu.add(produtosBtn);
        menu.add(cartasBtn);
        menu.add(pedidosBtn);
        menu.add(carrinhoBtn);
        menu.add(sairBtn);

        add(menu, BorderLayout.NORTH);

        // CONTEÚDO CENTRAL
        JLabel titulo = new JLabel("Bem-vindo ao TCG Brasil", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        add(titulo, BorderLayout.CENTER);

        // AÇÕES
        sairBtn.addActionListener(e -> sair());

        produtosBtn.addActionListener(e -> {
            new ProdutoFrame().setVisible(true);
        });
        
        cartasBtn.addActionListener(e -> {
            new CartasFrame().setVisible(true);
        });
        pedidosBtn.addActionListener(e -> {
            new Pedidos().setVisible(true);
        });
        carrinhoBtn.addActionListener(e -> {
            new Carrinho().setVisible(true);
        });

    }

    private void sair() {

        new Login().setVisible(true);
        dispose();

    }
}

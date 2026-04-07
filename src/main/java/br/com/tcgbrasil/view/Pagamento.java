package br.com.tcgbrasil.view;

import br.com.tcgbrasil.dao.CarrinhoService;
import br.com.tcgbrasil.dao.PedidoDAO;

import javax.swing.*;
import java.awt.*;

public class Pagamento extends JFrame {

    public Pagamento() {

        setTitle("Pagamento");
        setSize(300, 200);
        setLocationRelativeTo(null);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel total = new JLabel("Total: R$ " + CarrinhoService.total());
        total.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton pagar = new JButton("Pagar");

        pagar.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createRigidArea(new Dimension(0, 20)));
        add(total);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(pagar);

        pagar.addActionListener(e -> pagar());
    }

    private void pagar() {

        PedidoDAO dao = new PedidoDAO();

        dao.salvarPedido(CarrinhoService.listar());

        CarrinhoService.limpar();

        JOptionPane.showMessageDialog(this, "Pedido finalizado!");

        dispose();
    }
}

package br.com.tcgbrasil.view;

import br.com.tcgbrasil.model.Usuario;
import br.com.tcgbrasil.dao.UsuarioDAO;

import javax.swing.*;
import java.awt.*;

public class Cadastro extends JFrame {

    private JTextField emailField;
    private JPasswordField senhaField;
    private JTextField cpfField;
    private JTextField telefoneField;
    private JTextField enderecoField;

    public Cadastro() {

        setTitle("Cadastro - TCG Brasil");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(7, 1));

        emailField = new JTextField();
        senhaField = new JPasswordField();
        cpfField = new JTextField();
        telefoneField = new JTextField();
        enderecoField = new JTextField();

        JButton cadastrarBtn = new JButton("Cadastrar");
        JButton voltarBtn = new JButton("Voltar");

        add(new JLabel("Email"));
        add(emailField);

        add(new JLabel("Senha"));
        add(senhaField);

        add(new JLabel("CPF"));
        add(cpfField);

        add(new JLabel("Telefone"));
        add(telefoneField);

        add(new JLabel("Endereço"));
        add(enderecoField);

        add(voltarBtn);
        add(cadastrarBtn);

        voltarBtn.addActionListener(e -> voltar());
        cadastrarBtn.addActionListener(e -> cadastrar());

    }

    private void cadastrar() {

        Usuario usuario = new Usuario(
                emailField.getText(),
                new String(senhaField.getPassword()),
                cpfField.getText(),
                telefoneField.getText(),
                enderecoField.getText()
        );

        UsuarioDAO dao = new UsuarioDAO();
        dao.cadastrar(usuario);

        JOptionPane.showMessageDialog(this, "Cadastro realizado!");

        new Login().setVisible(true);
        dispose();
    }

    private void voltar() {

        new Login().setVisible(true);
        dispose();

    }
}

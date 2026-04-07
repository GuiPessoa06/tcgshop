package br.com.tcgbrasil.view;

import br.com.tcgbrasil.dao.UsuarioDAO;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {

    private JTextField emailField;
    private JPasswordField senhaField;

    public Login() {

        setTitle("TCG Brasil - Login");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal vertical
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // TÍTULO
        JLabel titulo = new JLabel("TCG Brasil");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // EMAIL
        panel.add(new JLabel("Usuário / Email"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // SENHA
        panel.add(new JLabel("Senha"));
        senhaField = new JPasswordField();
        panel.add(senhaField);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // BOTÃO LOGIN
        JButton loginBtn = new JButton("Entrar");
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(loginBtn);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // BOTÃO CADASTRO
        JButton cadastroBtn = new JButton("Criar Conta");
        cadastroBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(cadastroBtn);

        add(panel);

        // AÇÕES
        loginBtn.addActionListener(e -> login());
        cadastroBtn.addActionListener(e -> abrirCadastro());
    }

    private void login() {

        String email = emailField.getText();
        String senha = new String(senhaField.getPassword());

        UsuarioDAO dao = new UsuarioDAO();

        if (dao.login(email, senha)) {

            JOptionPane.showMessageDialog(this, "Login realizado!");

            new Dashboard().setVisible(true);
            dispose();

        } else {

            JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos.");

        }
    }

    private void abrirCadastro() {

        new Cadastro().setVisible(true);
        dispose();

    }
}

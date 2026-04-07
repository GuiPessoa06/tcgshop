package br.com.tcgbrasil.model;

public class Produtos {

    private int idProduto;
    private String nome;
    private double preco;

    // CONSTRUTOR PARA CADASTRO
    public Produtos(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    // CONSTRUTOR COMPLETO (BANCO)
    public Produtos(int idProduto, String nome, double preco) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.preco = preco;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setId(int idProduto) {
        this.idProduto = idProduto;
    }
}

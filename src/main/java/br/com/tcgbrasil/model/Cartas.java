package br.com.tcgbrasil.model;

public class Cartas {

    private String nome;
    private double preco;
    private int idCarta;

    // CONSTRUTOR PARA CADASTRO
    public Cartas(int idCarta, String nome, double preco) {
        this.idCarta = idCarta;
        this.nome = nome;
        this.preco = preco;
    }

    public Cartas(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }


    public void setIdCarta(int idCarta) {
        this.idCarta = idCarta;
    }

    public int getIdCarta() {
        return idCarta;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

}

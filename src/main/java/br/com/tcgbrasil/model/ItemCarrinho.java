package br.com.tcgbrasil.model;

public class ItemCarrinho {

    private Produtos produto;
    private Cartas carta;
    private int quantidade;

    public ItemCarrinho(Produtos produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public ItemCarrinho(Cartas carta, int quantidade) {
        this.carta = carta;
        this.quantidade = quantidade;
    }

    public Produtos getProduto() {
        return produto;
    }

    public Cartas getCarta() {
        return carta;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getTotal() {
        if (produto != null) {
            return produto.getPreco() * quantidade;
        }
        if (carta != null) {
            return carta.getPreco() * quantidade;
        }
        return 0;
    }
    private String nome;
}

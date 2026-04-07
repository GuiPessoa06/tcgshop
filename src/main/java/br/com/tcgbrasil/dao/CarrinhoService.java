package br.com.tcgbrasil.dao;

import br.com.tcgbrasil.model.Cartas;
import br.com.tcgbrasil.model.ItemCarrinho;
import br.com.tcgbrasil.model.Produtos;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoService {

    private static List<ItemCarrinho> itens = new ArrayList<>();

    public static void adicionarProduto(Produtos p) {
        itens.add(new ItemCarrinho(p, 1));
    }

    public static void adicionarCarta(Cartas c) {
        itens.add(new ItemCarrinho(c, 1));
    }

    public static List<ItemCarrinho> listar() {
        return itens;
    }

    public static double total() {
        return itens.stream().mapToDouble(ItemCarrinho::getTotal).sum();
    }

    public static void limpar() {
        itens.clear();
    }
}

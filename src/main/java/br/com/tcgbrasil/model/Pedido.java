package br.com.tcgbrasil.model;

import java.sql.Timestamp;
import java.util.List;

public class Pedido {

    private int idPedido;
    private Timestamp dataPedido;

    public Pedido(int idPedido, Timestamp dataPedido) {
        this.idPedido = idPedido;
        this.dataPedido = dataPedido;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public Timestamp getDataPedido() {
        return dataPedido;
    }
}
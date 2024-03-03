package com.example.restauranteapp.Models;

public class PedidoDetalle {

 private int idPedidoDetalle ;

 private int idPedido;

 private int idPlato;

 private int cantidad;


    public PedidoDetalle(int idPedidoDetalle, int idPedido, int idPlato, int cantidad) {
        this.idPedidoDetalle = idPedidoDetalle;
        this.idPedido = idPedido;
        this.idPlato = idPlato;
        this.cantidad = cantidad;
    }

    public int getIdPedidoDetalle() {
        return idPedidoDetalle;
    }

    public void setIdPedidoDetalle(int idPedidoDetalle) {
        this.idPedidoDetalle = idPedidoDetalle;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(int idPlato) {
        this.idPlato = idPlato;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}

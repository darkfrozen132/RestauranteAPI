package com.example.restauranteapp.Models;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Timer;

public class Pedido {

private int idPedido;

private int idMesa;

private  int idPersonal;

private int  idCliente;

private Date dia;

private Timer horaInicioPedido;

private  Timer horaFinPedido;

private DecimalFormat total;

    public Pedido(int idPedido, int idMesa, int idPersonal, int idCliente, Date dia, Timer horaInicioPedido, Timer horaFinPedido, DecimalFormat total) {
        this.idPedido = idPedido;
        this.idMesa = idMesa;
        this.idPersonal = idPersonal;
        this.idCliente = idCliente;
        this.dia = dia;
        this.horaInicioPedido = horaInicioPedido;
        this.horaFinPedido = horaFinPedido;
        this.total = total;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Timer getHoraInicioPedido() {
        return horaInicioPedido;
    }

    public void setHoraInicioPedido(Timer horaInicioPedido) {
        this.horaInicioPedido = horaInicioPedido;
    }

    public Timer getHoraFinPedido() {
        return horaFinPedido;
    }

    public void setHoraFinPedido(Timer horaFinPedido) {
        this.horaFinPedido = horaFinPedido;
    }

    public DecimalFormat getTotal() {
        return total;
    }

    public void setTotal(DecimalFormat total) {
        this.total = total;
    }
}

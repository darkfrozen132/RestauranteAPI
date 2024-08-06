package com.example.restauranteapp.Models;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Timer;

public class Pedido {

    private int idPedido;


    private int idMesa;

    private int idPersonal;

    private int idCliente;

    private LocalDate dia;

    private LocalTime horaInicioPedido;

    private LocalTime horaFinPedido;

    private double total;

    public Pedido(int idPedido, int idMesa, int idPersonal, int idCliente, LocalDate dia, LocalTime horaInicioPedido, LocalTime horaFinPedido, double total) {
        this.idPedido = idPedido;
        this.idMesa = idMesa;
        this.idPersonal = idPersonal;
        this.idCliente = idCliente;
        this.dia = dia;
        this.horaInicioPedido = horaInicioPedido;
        this.horaFinPedido = horaFinPedido;
        this.total = total;
    }
}












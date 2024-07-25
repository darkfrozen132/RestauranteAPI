package com.example.restauranteapp.Models;

public class Mesas {

  private  int idMesa;
  private int cantidadAsientos;
  private String descripcion;
  private int reservado;

   private  int dLeft;

   private int dUp;

    public Mesas(int idMesa, int cantidadAsientos, String descripcion, int reservado, int dLeft, int dUp) {
        this.idMesa = idMesa;
        this.cantidadAsientos = cantidadAsientos;
        this.descripcion = descripcion;
        this.reservado = reservado;
        this.dLeft = dLeft;
        this.dUp = dUp;
    }

    public Mesas(int idMesa, int dLeft, int dUp) {
        this.idMesa = idMesa;
        this.dLeft = dLeft;
        this.dUp = dUp;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public int getCantidadAsientos() {
        return cantidadAsientos;
    }

    public void setCantidadAsientos(int cantidadAsientos) {
        this.cantidadAsientos = cantidadAsientos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getReservado() {
        return reservado;
    }

    public void setReservado(int reservado) {
        this.reservado = reservado;
    }

    public int getdLeft() {
        return dLeft;
    }

    public void setdLeft(int dLeft) {
        this.dLeft = dLeft;
    }

    public int getdUp() {
        return dUp;
    }

    public void setdUp(int dUp) {
        this.dUp = dUp;
    }
}



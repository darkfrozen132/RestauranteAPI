package com.example.restauranteapp.Models;

public class Cliente {

  private  int idCliente;
  private  String nombre;
  private int idTipoCliente;


    public Cliente(int idCliente, String nombre, int idTipoCliente) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.idTipoCliente = idTipoCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdTipoCliente() {
        return idTipoCliente;
    }

    public void setIdTipoCliente(int idTipoCliente) {
        this.idTipoCliente = idTipoCliente;
    }
}

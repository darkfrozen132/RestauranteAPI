package com.example.restauranteapp.Models;

public class TipoCliente {


  private  int idTipoCliente;

  private int porcentaje;


    public TipoCliente(int idTipoCliente, int porcentaje) {
        this.idTipoCliente = idTipoCliente;
        this.porcentaje = porcentaje;
    }

    public int getIdTipoCliente() {
        return idTipoCliente;
    }

    public void setIdTipoCliente(int idTipoCliente) {
        this.idTipoCliente = idTipoCliente;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }
}

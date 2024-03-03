package com.example.restauranteapp.Models;

import java.text.DecimalFormat;

public class Plato {

  private int idPlato;

  private String nombrePlato;

  private DecimalFormat costo;

  private int disponible;

  private String tipoPlato;

    public Plato(int idPlato, String nombrePlato, DecimalFormat costo, int disponible, String tipoPlato) {
        this.idPlato = idPlato;
        this.nombrePlato = nombrePlato;
        this.costo = costo;
        this.disponible = disponible;
        this.tipoPlato = tipoPlato;
    }

    public int getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(int idPlato) {
        this.idPlato = idPlato;
    }

    public String getNombrePlato() {
        return nombrePlato;
    }

    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    public DecimalFormat getCosto() {
        return costo;
    }

    public void setCosto(DecimalFormat costo) {
        this.costo = costo;
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    public String getTipoPlato() {
        return tipoPlato;
    }

    public void setTipoPlato(String tipoPlato) {
        this.tipoPlato = tipoPlato;
    }
}

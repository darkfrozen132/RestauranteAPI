package com.example.restauranteapp.Models;

public class Personal {
    private  int idPersonal;
    private String   nombrePersonal;

   private  int permisos;

    public Personal(int idPersonal, String nombrePersonal, int permisos) {
        this.idPersonal = idPersonal;
        this.nombrePersonal = nombrePersonal;
        this.permisos = permisos;
    }

    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }

    public String getNombrePersonal() {
        return nombrePersonal;
    }

    public void setNombrePersonal(String nombrePersonal) {
        this.nombrePersonal = nombrePersonal;
    }

    public int getPermisos() {
        return permisos;
    }

    public void setPermisos(int permisos) {
        this.permisos = permisos;
    }
}

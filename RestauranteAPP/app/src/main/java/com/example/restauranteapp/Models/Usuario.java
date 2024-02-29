package com.example.restauranteapp.Models;

public class Usuario {

    private int IdUsuario;

    private String categoria;

    private String usuario;

    private String contrasena;


    public Usuario(int idUsuario) {
        IdUsuario = idUsuario;
    }

    public Usuario(int idUsuario, String categoria, String usuario, String contrasena) {
        IdUsuario = idUsuario;
        this.categoria = categoria;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public Usuario(String categoria, String usuario, String contrasena) {
        this.categoria = categoria;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}

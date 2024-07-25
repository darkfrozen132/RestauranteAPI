package com.example.restauranteapp.Models;

public class Usuario {

    private int IdUsuario;

    private String Categoria;

    private String Usuario;

    private String Contrasena;

    public Usuario(){

    }
    public Usuario(int idUsuario, String categoria, String usuario, String contrasena) {
        IdUsuario = idUsuario;
        Categoria = categoria;
        Usuario = usuario;
        Contrasena = contrasena;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String contrasena) {
        Contrasena = contrasena;
    }
}

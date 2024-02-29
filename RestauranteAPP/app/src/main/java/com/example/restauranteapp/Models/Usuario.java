package com.example.restauranteapp.Models;

public class Usuario {

    private int idUsuario;

    private String categoria;

    private String usuario;

    private String contrasena;


<<<<<<< Updated upstream
    public Usuario(){

    }

    public Usuario(int idUsuario) {
        idUsuario = idUsuario;
    }

    public Usuario(int idUsuario, String categoria, String usuario, String contrasena) {
        this.idUsuario = idUsuario;
        this.categoria = categoria;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public Usuario(String categoria, String usuario, String contrasena) {
=======
    public Usuario(int idUsuario, String categoria, String usuario, String contrasena) {
        this.idUsuario = idUsuario;
>>>>>>> Stashed changes
        this.categoria = categoria;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
<<<<<<< Updated upstream
        idUsuario = idUsuario;
=======
        this.idUsuario = idUsuario;
>>>>>>> Stashed changes
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

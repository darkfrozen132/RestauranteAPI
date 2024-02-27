package com.example.restauranteapir.Model;

public class Usuarios {
  private int id_usuario;
  private String categoria;
  private String usuario;
  private String contrasena;

    public Usuarios(int id_usuario, String categoria, String usuario, String contrasena) {
        this.id_usuario = id_usuario;
        this.categoria = categoria;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
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

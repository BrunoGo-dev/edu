package com.TpIntegrado.edu.web.dto;

import com.TpIntegrado.edu.persistance.entity.Rol;

public class UsuarioResponse {
    private Long id;
    private String username;
    private String nombre;
    private String email;
    private Rol rol;
    private Boolean activo;

    public UsuarioResponse() {
    }

    public UsuarioResponse(Long id, String username, String nombre, String email, Rol rol, Boolean activo) {
        this.id = id;
        this.username = username;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}

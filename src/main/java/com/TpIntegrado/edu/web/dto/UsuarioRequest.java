package com.TpIntegrado.edu.web.dto;

import com.TpIntegrado.edu.persistance.entity.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioRequest {

    @NotBlank(message = "username es requerido")
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank(message = "password es requerido")
    @Size(min = 6, max = 100)
    private String password;

    @NotBlank(message = "nombre es requerido")
    @Size(max = 100)
    private String nombre;

    @NotBlank(message = "email es requerido")
    @Email(message = "email inválido")
    private String email;

    @NotNull(message = "rol es requerido")
    private Rol rol;

    private Boolean activo = true;

    public UsuarioRequest() {
    }

    // Getters / Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

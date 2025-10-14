package com.TpIntegrado.edu.web.dto;

import java.time.LocalDate;

public class TareaDTO {
    private Long id;
    private Long cursoId;
    private String cursoNombre;
    private String titulo;
    private String descripcion;
    private LocalDate fechaLimite;
    private Boolean activo;

    // Constructores
    public TareaDTO() {
    }

    public TareaDTO(Long id, Long cursoId, String cursoNombre, String titulo, 
                    String descripcion, LocalDate fechaLimite, Boolean activo) {
        this.id = id;
        this.cursoId = cursoId;
        this.cursoNombre = cursoNombre;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.activo = activo;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    public String getCursoNombre() {
        return cursoNombre;
    }

    public void setCursoNombre(String cursoNombre) {
        this.cursoNombre = cursoNombre;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(LocalDate fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}

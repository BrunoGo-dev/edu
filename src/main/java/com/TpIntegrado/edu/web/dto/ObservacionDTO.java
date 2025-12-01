package com.TpIntegrado.edu.web.dto;

import com.TpIntegrado.edu.persistance.entity.TipoObservacion;

import java.time.LocalDateTime;

public class ObservacionDTO {
    private Long id;
    private Long estudianteId;
    private String estudianteNombre;
    private Long docenteId;
    private String docenteNombre;
    private Long cursoId;
    private String cursoNombre;
    private String titulo;
    private String contenido;
    private TipoObservacion tipo;
    private LocalDateTime fechaCreacion;

    // Constructores
    public ObservacionDTO() {
    }

    public ObservacionDTO(Long id, Long estudianteId, String estudianteNombre, 
                         Long docenteId, String docenteNombre, Long cursoId, String cursoNombre,
                         String titulo, String contenido, TipoObservacion tipo, LocalDateTime fechaCreacion) {
        this.id = id;
        this.estudianteId = estudianteId;
        this.estudianteNombre = estudianteNombre;
        this.docenteId = docenteId;
        this.docenteNombre = docenteNombre;
        this.cursoId = cursoId;
        this.cursoNombre = cursoNombre;
        this.titulo = titulo;
        this.contenido = contenido;
        this.tipo = tipo;
        this.fechaCreacion = fechaCreacion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    public String getEstudianteNombre() {
        return estudianteNombre;
    }

    public void setEstudianteNombre(String estudianteNombre) {
        this.estudianteNombre = estudianteNombre;
    }

    public Long getDocenteId() {
        return docenteId;
    }

    public void setDocenteId(Long docenteId) {
        this.docenteId = docenteId;
    }

    public String getDocenteNombre() {
        return docenteNombre;
    }

    public void setDocenteNombre(String docenteNombre) {
        this.docenteNombre = docenteNombre;
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

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public TipoObservacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoObservacion tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}

package com.TpIntegrado.edu.web.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EntregaDTO {
    private Long id;
    private Long tareaId;
    private Long estudianteId;
    private String nombreEstudiante;
    private String contenido;
    private LocalDateTime fechaEntrega;
    private BigDecimal calificacion;
    private String observaciones;

    public EntregaDTO() {
    }

    public EntregaDTO(Long id, Long tareaId, Long estudianteId, String nombreEstudiante, String contenido,
            LocalDateTime fechaEntrega, BigDecimal calificacion, String observaciones) {
        this.id = id;
        this.tareaId = tareaId;
        this.estudianteId = estudianteId;
        this.nombreEstudiante = nombreEstudiante;
        this.contenido = contenido;
        this.fechaEntrega = fechaEntrega;
        this.calificacion = calificacion;
        this.observaciones = observaciones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTareaId() {
        return tareaId;
    }

    public void setTareaId(Long tareaId) {
        this.tareaId = tareaId;
    }

    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public BigDecimal getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(BigDecimal calificacion) {
        this.calificacion = calificacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}

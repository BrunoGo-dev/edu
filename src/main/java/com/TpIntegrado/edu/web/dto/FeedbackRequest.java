package com.TpIntegrado.edu.web.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class FeedbackRequest {

    @NotNull(message = "La calificación es obligatoria")
    @DecimalMin(value = "0.0", message = "La calificación mínima es 0")
    @DecimalMax(value = "10.0", message = "La calificación máxima es 10")
    private BigDecimal calificacion;

    private String observaciones;

    @NotNull(message = "El ID del docente es obligatorio")
    private Long docenteId;

    private String tipo;

    public FeedbackRequest() {
    }

    public FeedbackRequest(BigDecimal calificacion, String observaciones, Long docenteId, String tipo) {
        this.calificacion = calificacion;
        this.observaciones = observaciones;
        this.docenteId = docenteId;
        this.tipo = tipo;
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

    public Long getDocenteId() {
        return docenteId;
    }

    public void setDocenteId(Long docenteId) {
        this.docenteId = docenteId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

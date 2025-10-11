package com.TpIntegrado.edu.web.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class NotaRequest {
    
    @NotNull(message = "El ID del estudiante es obligatorio")
    private Long estudianteId;
    
    @NotNull(message = "El ID de la evaluación es obligatorio")
    private Long evaluacionId;
    
    @NotNull(message = "La nota es obligatoria")
    @DecimalMin(value = "0.0", message = "La nota mínima es 0")
    @DecimalMax(value = "100.0", message = "La nota máxima es 100")
    private BigDecimal nota;
    
    private String observaciones;

    // Constructores
    public NotaRequest() {
    }

    public NotaRequest(Long estudianteId, Long evaluacionId, BigDecimal nota, String observaciones) {
        this.estudianteId = estudianteId;
        this.evaluacionId = evaluacionId;
        this.nota = nota;
        this.observaciones = observaciones;
    }

    // Getters y Setters
    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    public Long getEvaluacionId() {
        return evaluacionId;
    }

    public void setEvaluacionId(Long evaluacionId) {
        this.evaluacionId = evaluacionId;
    }

    public BigDecimal getNota() {
        return nota;
    }

    public void setNota(BigDecimal nota) {
        this.nota = nota;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}

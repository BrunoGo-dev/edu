package com.TpIntegrado.edu.web.dto;

import java.math.BigDecimal;

public class NotaDTO {
    private Long id;
    private Long estudianteId;
    private String estudianteNombre;
    private Long evaluacionId;
    private String evaluacionNombre;
    private BigDecimal nota;
    private String observaciones;

    // Constructores
    public NotaDTO() {
    }

    public NotaDTO(Long id, Long estudianteId, String estudianteNombre, 
                   Long evaluacionId, String evaluacionNombre, 
                   BigDecimal nota, String observaciones) {
        this.id = id;
        this.estudianteId = estudianteId;
        this.estudianteNombre = estudianteNombre;
        this.evaluacionId = evaluacionId;
        this.evaluacionNombre = evaluacionNombre;
        this.nota = nota;
        this.observaciones = observaciones;
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

    public Long getEvaluacionId() {
        return evaluacionId;
    }

    public void setEvaluacionId(Long evaluacionId) {
        this.evaluacionId = evaluacionId;
    }

    public String getEvaluacionNombre() {
        return evaluacionNombre;
    }

    public void setEvaluacionNombre(String evaluacionNombre) {
        this.evaluacionNombre = evaluacionNombre;
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

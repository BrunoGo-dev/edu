package com.TpIntegrado.edu.web.dto;

import com.TpIntegrado.edu.persistance.entity.EstadoAsistencia;
import jakarta.validation.constraints.NotNull;

public class AsistenciaRequest {
    
    @NotNull(message = "El ID de la clase es obligatorio")
    private Long claseId;
    
    @NotNull(message = "El ID del estudiante es obligatorio")
    private Long estudianteId;
    
    @NotNull(message = "El estado de asistencia es obligatorio")
    private EstadoAsistencia estado;
    
    private String observaciones;

    // Constructores
    public AsistenciaRequest() {
    }

    public AsistenciaRequest(Long claseId, Long estudianteId, EstadoAsistencia estado, String observaciones) {
        this.claseId = claseId;
        this.estudianteId = estudianteId;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    // Getters y Setters
    public Long getClaseId() {
        return claseId;
    }

    public void setClaseId(Long claseId) {
        this.claseId = claseId;
    }

    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    public EstadoAsistencia getEstado() {
        return estado;
    }

    public void setEstado(EstadoAsistencia estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}

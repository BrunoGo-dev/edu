package com.TpIntegrado.edu.web.dto;

import com.TpIntegrado.edu.persistance.entity.EstadoAsistencia;

import java.time.LocalDateTime;

public class AsistenciaDTO {
    private Long id;
    private Long claseId;
    private Long estudianteId;
    private String estudianteNombre;
    private EstadoAsistencia estado;
    private LocalDateTime fechaRegistro;
    private String observaciones;

    // Constructores
    public AsistenciaDTO() {
    }

    public AsistenciaDTO(Long id, Long claseId, Long estudianteId, String estudianteNombre, 
                        EstadoAsistencia estado, LocalDateTime fechaRegistro, String observaciones) {
        this.id = id;
        this.claseId = claseId;
        this.estudianteId = estudianteId;
        this.estudianteNombre = estudianteNombre;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.observaciones = observaciones;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getEstudianteNombre() {
        return estudianteNombre;
    }

    public void setEstudianteNombre(String estudianteNombre) {
        this.estudianteNombre = estudianteNombre;
    }

    public EstadoAsistencia getEstado() {
        return estado;
    }

    public void setEstado(EstadoAsistencia estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}

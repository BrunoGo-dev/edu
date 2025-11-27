package com.TpIntegrado.edu.web.dto;

import com.TpIntegrado.edu.persistance.entity.EstadoAsistencia;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class AsistenciaRequest {

    @NotNull(message = "estudianteId es obligatorio")
    private Long estudianteId;

    @NotNull(message = "cursoId es obligatorio")
    private Long cursoId;

    @NotNull(message = "fecha es obligatoria")
    private LocalDate fecha;

    @NotNull(message = "estado es obligatorio")
    private EstadoAsistencia estado;

    private String observaciones;

    public AsistenciaRequest() {
    }

    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
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
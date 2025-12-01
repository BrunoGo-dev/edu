package com.TpIntegrado.edu.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CursoRequest {
    @NotNull(message = "El ID de materia es requerido")
    private Long materiaId;

    @NotNull(message = "El ID del docente es requerido")
    private Long docenteId;

    @NotBlank(message = "El nombre del curso es requerido")
    private String nombre;

    @NotBlank(message = "El período es requerido")
    private String periodo;

    // Constructores
    public CursoRequest() {
    }

    public CursoRequest(Long materiaId, Long docenteId, String nombre, String periodo) {
        this.materiaId = materiaId;
        this.docenteId = docenteId;
        this.nombre = nombre;
        this.periodo = periodo;
    }

    // Getters y Setters
    public Long getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(Long materiaId) {
        this.materiaId = materiaId;
    }

    public Long getDocenteId() {
        return docenteId;
    }

    public void setDocenteId(Long docenteId) {
        this.docenteId = docenteId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}

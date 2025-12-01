package com.TpIntegrado.edu.web.dto;

import com.TpIntegrado.edu.persistance.entity.TipoObservacion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ObservacionRequest {
    
    @NotNull(message = "El ID del estudiante es obligatorio")
    private Long estudianteId;
    
    @NotNull(message = "El ID del docente es obligatorio")
    private Long docenteId;
    
    @NotNull(message = "El ID del curso es obligatorio")
    private Long cursoId;
    
    @NotBlank(message = "El título es obligatorio")
    @Size(max = 200, message = "El título no puede exceder 200 caracteres")
    private String titulo;
    
    @NotBlank(message = "El contenido es obligatorio")
    private String contenido;
    
    @NotNull(message = "El tipo de observación es obligatorio")
    private TipoObservacion tipo;

    // Constructores
    public ObservacionRequest() {
    }

    public ObservacionRequest(Long estudianteId, Long docenteId, Long cursoId, 
                             String titulo, String contenido, TipoObservacion tipo) {
        this.estudianteId = estudianteId;
        this.docenteId = docenteId;
        this.cursoId = cursoId;
        this.titulo = titulo;
        this.contenido = contenido;
        this.tipo = tipo;
    }

    // Getters y Setters
    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    public Long getDocenteId() {
        return docenteId;
    }

    public void setDocenteId(Long docenteId) {
        this.docenteId = docenteId;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
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
}

package com.TpIntegrado.edu.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EntregaRequest {

    @NotNull(message = "El ID del estudiante es obligatorio")
    private Long estudianteId;

    @NotBlank(message = "El contenido es obligatorio")
    private String contenido;

    public EntregaRequest() {
    }

    public EntregaRequest(Long estudianteId, String contenido) {
        this.estudianteId = estudianteId;
        this.contenido = contenido;
    }

    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}

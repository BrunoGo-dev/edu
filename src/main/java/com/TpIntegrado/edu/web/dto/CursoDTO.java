package com.TpIntegrado.edu.web.dto;

public class CursoDTO {
    private Long id;
    private String nombre;
    private String periodo;
    private Boolean activo;
    private Long materiaId;
    private String materiaNombre;
    private Long docenteId;
    private String docenteNombre;

    // Constructores
    public CursoDTO() {
    }

    public CursoDTO(Long id, String nombre, String periodo, Boolean activo,
            Long materiaId, String materiaNombre, Long docenteId, String docenteNombre) {
        this.id = id;
        this.nombre = nombre;
        this.periodo = periodo;
        this.activo = activo;
        this.materiaId = materiaId;
        this.materiaNombre = materiaNombre;
        this.docenteId = docenteId;
        this.docenteNombre = docenteNombre;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Long getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(Long materiaId) {
        this.materiaId = materiaId;
    }

    public String getMateriaNombre() {
        return materiaNombre;
    }

    public void setMateriaNombre(String materiaNombre) {
        this.materiaNombre = materiaNombre;
    }

    public Long getDocenteId() {
        return docenteId;
    }

    public void setDocenteId(Long docenteId) {
        this.docenteId = docenteId;
    }

    public String getDocenteNombre() {
        return docenteNombre;
    }

    public void setDocenteNombre(String docenteNombre) {
        this.docenteNombre = docenteNombre;
    }
}

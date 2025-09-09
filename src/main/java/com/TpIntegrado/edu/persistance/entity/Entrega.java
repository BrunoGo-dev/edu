package com.TpIntegrado.edu.persistance.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "entregas", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "tarea_id", "estudiante_id" })
})
public class Entrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tarea_id", nullable = false)
    private Tarea tarea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Usuario estudiante;

    @Column(columnDefinition = "TEXT")
    private String contenido;

    @Column(name = "fecha_entrega")
    private LocalDateTime fechaEntrega = LocalDateTime.now();

    @Column(precision = 4, scale = 2)
    private BigDecimal calificacion;

    // Constructores
    public Entrega() {
    }

    public Entrega(Tarea tarea, Usuario estudiante, String contenido, LocalDateTime fechaEntrega,
            BigDecimal calificacion) {
        this.tarea = tarea;
        this.estudiante = estudiante;
        this.contenido = contenido;
        this.fechaEntrega = fechaEntrega;
        this.calificacion = calificacion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    public Usuario getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Usuario estudiante) {
        this.estudiante = estudiante;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public BigDecimal getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(BigDecimal calificacion) {
        this.calificacion = calificacion;
    }
}

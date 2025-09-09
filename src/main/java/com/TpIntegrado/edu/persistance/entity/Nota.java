package com.TpIntegrado.edu.persistance.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "notas", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "estudiante_id", "evaluacion_id" })
})
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Usuario estudiante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evaluacion_id", nullable = false)
    private Evaluacion evaluacion;

    @Column(precision = 4, scale = 2)
    private BigDecimal nota;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    // Constructores
    public Nota() {
    }

    public Nota(Usuario estudiante, Evaluacion evaluacion, BigDecimal nota) {
        this.estudiante = estudiante;
        this.evaluacion = evaluacion;
        this.nota = nota;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Usuario estudiante) {
        this.estudiante = estudiante;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
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

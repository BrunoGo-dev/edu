package com.TpIntegrado.edu.persistance.repository;

import com.TpIntegrado.edu.persistance.entity.Clase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClaseRepository extends CrudRepository<Clase, Long> {

    // Query Methods para consultas personalizadas
    List<Clase> findByCursoId(Long cursoId);

    Optional<Clase> findByCursoIdAndFecha(Long cursoId, LocalDate fecha);

    List<Clase> findByCursoIdAndFechaBetween(Long cursoId, LocalDate fechaInicio, LocalDate fechaFin);

    List<Clase> findByFecha(LocalDate fecha);

    List<Clase> findByCursoDocenteId(Long docenteId);
}

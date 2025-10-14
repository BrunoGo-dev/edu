package com.TpIntegrado.edu.persistance.repository;

import com.TpIntegrado.edu.persistance.entity.Clase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClaseRepository extends CrudRepository<Clase, Long> {
    
    // Query Methods para consultas personalizadas
    List<Clase> findByCursoId(Long cursoId);
    
    List<Clase> findByCursoIdAndFecha(Long cursoId, LocalDate fecha);
    
    List<Clase> findByCursoIdAndFechaBetween(Long cursoId, LocalDate fechaInicio, LocalDate fechaFin);
    
    List<Clase> findByFecha(LocalDate fecha);
    
    List<Clase> findByCursoDocenteId(Long docenteId);
}

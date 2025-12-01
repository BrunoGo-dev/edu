package com.TpIntegrado.edu.persistance.repository;

import com.TpIntegrado.edu.persistance.entity.Tarea;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TareaRepository extends CrudRepository<Tarea, Long> {
    
    // Query Methods para consultas personalizadas
    List<Tarea> findByCursoId(Long cursoId);
    
    List<Tarea> findByCursoIdAndActivo(Long cursoId, Boolean activo);
    
    List<Tarea> findByActivoTrue();
    
    List<Tarea> findByCursoIdAndFechaLimiteBefore(Long cursoId, LocalDate fecha);
    
    List<Tarea> findByCursoIdAndFechaLimiteAfter(Long cursoId, LocalDate fecha);
    
    List<Tarea> findByFechaLimiteBetween(LocalDate fechaInicio, LocalDate fechaFin);
}

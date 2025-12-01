package com.TpIntegrado.edu.persistance.repository;

import com.TpIntegrado.edu.persistance.entity.Nota;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotaRepository extends CrudRepository<Nota, Long> {
    
    // Query Methods para consultas personalizadas
    List<Nota> findByEstudianteId(Long estudianteId);
    
    List<Nota> findByEvaluacionId(Long evaluacionId);
    
    List<Nota> findByEvaluacionCursoId(Long cursoId);
    
    List<Nota> findByEstudianteIdAndEvaluacionCursoId(Long estudianteId, Long cursoId);
    
    Optional<Nota> findByEstudianteIdAndEvaluacionId(Long estudianteId, Long evaluacionId);
    
    boolean existsByEstudianteIdAndEvaluacionId(Long estudianteId, Long evaluacionId);
}

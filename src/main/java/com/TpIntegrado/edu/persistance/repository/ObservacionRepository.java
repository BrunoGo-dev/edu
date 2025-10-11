package com.TpIntegrado.edu.persistance.repository;

import com.TpIntegrado.edu.persistance.entity.Observacion;
import com.TpIntegrado.edu.persistance.entity.TipoObservacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObservacionRepository extends CrudRepository<Observacion, Long> {
    
    // Query Methods para consultas personalizadas
    List<Observacion> findByEstudianteId(Long estudianteId);
    
    List<Observacion> findByDocenteId(Long docenteId);
    
    List<Observacion> findByCursoId(Long cursoId);
    
    List<Observacion> findByEstudianteIdAndCursoId(Long estudianteId, Long cursoId);
    
    List<Observacion> findByEstudianteIdAndTipo(Long estudianteId, TipoObservacion tipo);
    
    List<Observacion> findByCursoIdAndTipo(Long cursoId, TipoObservacion tipo);
    
    List<Observacion> findByDocenteIdAndCursoId(Long docenteId, Long cursoId);
}

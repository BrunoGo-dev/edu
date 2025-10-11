package com.TpIntegrado.edu.persistance.repository;

import com.TpIntegrado.edu.persistance.entity.Asistencia;
import com.TpIntegrado.edu.persistance.entity.EstadoAsistencia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AsistenciaRepository extends CrudRepository<Asistencia, Long> {
    
    // Query Methods para consultas personalizadas
    List<Asistencia> findByEstudianteId(Long estudianteId);
    
    List<Asistencia> findByClaseId(Long claseId);
    
    List<Asistencia> findByEstudianteIdAndClaseCursoId(Long estudianteId, Long cursoId);
    
    List<Asistencia> findByClaseCursoIdAndClaseFecha(Long cursoId, LocalDate fecha);
    
    List<Asistencia> findByEstudianteIdAndEstado(Long estudianteId, EstadoAsistencia estado);
    
    Long countByEstudianteIdAndClaseCursoIdAndEstado(Long estudianteId, Long cursoId, EstadoAsistencia estado);
    
    List<Asistencia> findByClaseCursoId(Long cursoId);
    
    boolean existsByClaseIdAndEstudianteId(Long claseId, Long estudianteId);
}

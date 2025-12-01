package com.TpIntegrado.edu.persistance.repository;

import com.TpIntegrado.edu.persistance.entity.Inscripcion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscripcionRepository extends CrudRepository<Inscripcion, Long> {
    List<Inscripcion> findByEstudianteId(Long estudianteId);

    List<Inscripcion> findByCursoId(Long cursoId);

    List<Inscripcion> findByEstudianteIdAndCursoId(Long estudianteId, Long cursoId);
}

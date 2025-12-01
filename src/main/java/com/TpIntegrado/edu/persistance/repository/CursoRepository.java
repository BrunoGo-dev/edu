package com.TpIntegrado.edu.persistance.repository;

import com.TpIntegrado.edu.persistance.entity.Curso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends CrudRepository<Curso, Long> {
    List<Curso> findByDocenteId(Long docenteId);

    List<Curso> findByActivoTrue();

    List<Curso> findByDocenteIdAndActivoTrue(Long docenteId);
}

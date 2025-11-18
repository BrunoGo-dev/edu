package com.TpIntegrado.edu.persistance.repository;

import com.TpIntegrado.edu.persistance.entity.Curso;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends CrudRepository<Curso, Long> {
    List<Curso> findByDocenteId(Long docenteId);

    List<Curso> findByActivoTrue();

    List<Curso> findByDocenteIdAndActivoTrue(Long docenteId);

    // Query para obtener cursos de un estudiante
    @Query("SELECT c FROM Curso c JOIN Inscripcion i ON c.id = i.curso.id WHERE i.estudiante.id = :estudianteId")
    List<Curso> findCursosByEstudianteId(@Param("estudianteId") Long estudianteId);

    // Query para obtener cursos activos de un estudiante
    @Query("SELECT c FROM Curso c JOIN Inscripcion i ON c.id = i.curso.id WHERE i.estudiante.id = :estudianteId AND c.activo = true")
    List<Curso> findCursoActivosByEstudianteId(@Param("estudianteId") Long estudianteId);
}

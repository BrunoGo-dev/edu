package com.TpIntegrado.edu.persistance.repository;

import com.TpIntegrado.edu.persistance.entity.Materia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MateriaRepository extends CrudRepository<Materia, Long> {
    Optional<Materia> findByCodigo(String codigo);
    Optional<Materia> findByNombre(String nombre);
}

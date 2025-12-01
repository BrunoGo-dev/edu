package com.TpIntegrado.edu.persistance.repository;

import com.TpIntegrado.edu.persistance.entity.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {
    List<Entrega> findByTareaId(Long tareaId);

    List<Entrega> findByEstudianteId(Long estudianteId);

    List<Entrega> findByTareaIdAndEstudianteId(Long tareaId, Long estudianteId);
}

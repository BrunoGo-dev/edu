package com.TpIntegrado.edu.domain.service;

import com.TpIntegrado.edu.persistance.entity.Observacion;
import com.TpIntegrado.edu.persistance.entity.TipoObservacion;
import com.TpIntegrado.edu.persistance.repository.ObservacionRepository;
import com.TpIntegrado.edu.web.dto.ObservacionDTO;
import com.TpIntegrado.edu.web.mapper.ObservacionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ObservacionService {

    @Autowired
    private ObservacionRepository observacionRepository;

    @Autowired
    private ObservacionMapper observacionMapper;

    public List<ObservacionDTO> findAll() {
        return StreamSupport.stream(observacionRepository.findAll().spliterator(), false)
                .map(observacionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ObservacionDTO> findById(Long id) {
        return observacionRepository.findById(id)
                .map(observacionMapper::toDTO);
    }

    public List<ObservacionDTO> findByEstudianteId(Long estudianteId) {
        return observacionRepository.findByEstudianteId(estudianteId).stream()
                .map(observacionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ObservacionDTO> findByDocenteId(Long docenteId) {
        return observacionRepository.findByDocenteId(docenteId).stream()
                .map(observacionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ObservacionDTO> findByCursoId(Long cursoId) {
        return observacionRepository.findByCursoId(cursoId).stream()
                .map(observacionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ObservacionDTO> findByEstudianteAndCurso(Long estudianteId, Long cursoId) {
        return observacionRepository.findByEstudianteIdAndCursoId(estudianteId, cursoId).stream()
                .map(observacionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ObservacionDTO> findByEstudianteAndTipo(Long estudianteId, TipoObservacion tipo) {
        return observacionRepository.findByEstudianteIdAndTipo(estudianteId, tipo).stream()
                .map(observacionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ObservacionDTO save(Observacion observacion) {
        Observacion saved = observacionRepository.save(observacion);
        return observacionMapper.toDTO(saved);
    }

    @Transactional
    public ObservacionDTO update(Long id, Observacion observacion) {
        Optional<Observacion> existingOpt = observacionRepository.findById(id);
        if (existingOpt.isEmpty()) {
            throw new IllegalArgumentException("Observación no encontrada con id: " + id);
        }
        
        Observacion existing = existingOpt.get();
        existing.setTitulo(observacion.getTitulo());
        existing.setContenido(observacion.getContenido());
        existing.setTipo(observacion.getTipo());
        
        Observacion updated = observacionRepository.save(existing);
        return observacionMapper.toDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!observacionRepository.existsById(id)) {
            throw new IllegalArgumentException("Observación no encontrada con id: " + id);
        }
        observacionRepository.deleteById(id);
    }
}

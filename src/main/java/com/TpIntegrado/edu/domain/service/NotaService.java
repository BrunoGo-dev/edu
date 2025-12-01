package com.TpIntegrado.edu.domain.service;

import com.TpIntegrado.edu.persistance.entity.Nota;
import com.TpIntegrado.edu.persistance.repository.NotaRepository;
import com.TpIntegrado.edu.web.dto.NotaDTO;
import com.TpIntegrado.edu.web.mapper.NotaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private NotaMapper notaMapper;

    public List<NotaDTO> findAll() {
        return StreamSupport.stream(notaRepository.findAll().spliterator(), false)
                .map(notaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<NotaDTO> findById(Long id) {
        return notaRepository.findById(id)
                .map(notaMapper::toDTO);
    }

    public List<NotaDTO> findByEstudianteId(Long estudianteId) {
        return notaRepository.findByEstudianteId(estudianteId).stream()
                .map(notaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<NotaDTO> findByEvaluacionId(Long evaluacionId) {
        return notaRepository.findByEvaluacionId(evaluacionId).stream()
                .map(notaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<NotaDTO> findByCursoId(Long cursoId) {
        return notaRepository.findByEvaluacionCursoId(cursoId).stream()
                .map(notaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<NotaDTO> findByEstudianteAndCurso(Long estudianteId, Long cursoId) {
        return notaRepository.findByEstudianteIdAndEvaluacionCursoId(estudianteId, cursoId).stream()
                .map(notaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public NotaDTO save(Nota nota) {
        if (notaRepository.existsByEstudianteIdAndEvaluacionId(
                nota.getEstudiante().getId(), nota.getEvaluacion().getId())) {
            throw new IllegalArgumentException("Ya existe una nota para este estudiante en esta evaluación");
        }
        Nota saved = notaRepository.save(nota);
        return notaMapper.toDTO(saved);
    }

    @Transactional
    public NotaDTO update(Long id, Nota nota) {
        Optional<Nota> existingOpt = notaRepository.findById(id);
        if (existingOpt.isEmpty()) {
            throw new IllegalArgumentException("Nota no encontrada con id: " + id);
        }
        
        Nota existing = existingOpt.get();
        existing.setNota(nota.getNota());
        existing.setObservaciones(nota.getObservaciones());
        
        Nota updated = notaRepository.save(existing);
        return notaMapper.toDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!notaRepository.existsById(id)) {
            throw new IllegalArgumentException("Nota no encontrada con id: " + id);
        }
        notaRepository.deleteById(id);
    }
}

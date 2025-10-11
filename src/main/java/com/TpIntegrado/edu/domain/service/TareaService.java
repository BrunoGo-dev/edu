package com.TpIntegrado.edu.domain.service;

import com.TpIntegrado.edu.persistance.entity.Tarea;
import com.TpIntegrado.edu.persistance.repository.TareaRepository;
import com.TpIntegrado.edu.web.dto.TareaDTO;
import com.TpIntegrado.edu.web.mapper.TareaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private TareaMapper tareaMapper;

    public List<TareaDTO> findAll() {
        return StreamSupport.stream(tareaRepository.findAll().spliterator(), false)
                .map(tareaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<TareaDTO> findById(Long id) {
        return tareaRepository.findById(id)
                .map(tareaMapper::toDTO);
    }

    public List<TareaDTO> findByCursoId(Long cursoId) {
        return tareaRepository.findByCursoId(cursoId).stream()
                .map(tareaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<TareaDTO> findActiveByCursoId(Long cursoId) {
        return tareaRepository.findByCursoIdAndActivo(cursoId, true).stream()
                .map(tareaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<TareaDTO> findAllActive() {
        return tareaRepository.findByActivoTrue().stream()
                .map(tareaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<TareaDTO> findPendingByCurso(Long cursoId) {
        return tareaRepository.findByCursoIdAndFechaLimiteAfter(cursoId, LocalDate.now()).stream()
                .map(tareaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public TareaDTO save(Tarea tarea) {
        Tarea saved = tareaRepository.save(tarea);
        return tareaMapper.toDTO(saved);
    }

    @Transactional
    public TareaDTO update(Long id, Tarea tarea) {
        Optional<Tarea> existingOpt = tareaRepository.findById(id);
        if (existingOpt.isEmpty()) {
            throw new IllegalArgumentException("Tarea no encontrada con id: " + id);
        }
        
        Tarea existing = existingOpt.get();
        existing.setTitulo(tarea.getTitulo());
        existing.setDescripcion(tarea.getDescripcion());
        existing.setFechaLimite(tarea.getFechaLimite());
        existing.setActivo(tarea.getActivo());
        
        Tarea updated = tareaRepository.save(existing);
        return tareaMapper.toDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!tareaRepository.existsById(id)) {
            throw new IllegalArgumentException("Tarea no encontrada con id: " + id);
        }
        tareaRepository.deleteById(id);
    }
}

package com.TpIntegrado.edu.domain.service;

import com.TpIntegrado.edu.persistance.entity.Asistencia;
import com.TpIntegrado.edu.persistance.entity.EstadoAsistencia;
import com.TpIntegrado.edu.persistance.repository.AsistenciaRepository;
import com.TpIntegrado.edu.web.dto.AsistenciaDTO;
import com.TpIntegrado.edu.web.mapper.AsistenciaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @Autowired
    private AsistenciaMapper asistenciaMapper;

    public List<AsistenciaDTO> findAll() {
        return StreamSupport.stream(asistenciaRepository.findAll().spliterator(), false)
                .map(asistenciaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<AsistenciaDTO> findById(Long id) {
        return asistenciaRepository.findById(id)
                .map(asistenciaMapper::toDTO);
    }

    public List<AsistenciaDTO> findByEstudianteId(Long estudianteId) {
        return asistenciaRepository.findByEstudianteId(estudianteId).stream()
                .map(asistenciaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<AsistenciaDTO> findByClaseId(Long claseId) {
        return asistenciaRepository.findByClaseId(claseId).stream()
                .map(asistenciaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<AsistenciaDTO> findByEstudianteAndCurso(Long estudianteId, Long cursoId) {
        return asistenciaRepository.findByEstudianteIdAndClaseCursoId(estudianteId, cursoId).stream()
                .map(asistenciaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<AsistenciaDTO> findByCursoAndFecha(Long cursoId, LocalDate fecha) {
        return asistenciaRepository.findByClaseCursoIdAndClaseFecha(cursoId, fecha).stream()
                .map(asistenciaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Long countByEstudianteAndCursoAndEstado(Long estudianteId, Long cursoId, EstadoAsistencia estado) {
        return asistenciaRepository.countByEstudianteIdAndClaseCursoIdAndEstado(estudianteId, cursoId, estado);
    }

    @Transactional
    public AsistenciaDTO save(Asistencia asistencia) {
        if (asistenciaRepository.existsByClaseIdAndEstudianteId(
                asistencia.getClase().getId(), asistencia.getEstudiante().getId())) {
            throw new IllegalArgumentException(
                    "Ya existe un registro de asistencia para este estudiante en esta clase");
        }
        Asistencia saved = asistenciaRepository.save(asistencia);
        return asistenciaMapper.toDTO(saved);
    }

    @Transactional
    public AsistenciaDTO update(Long id, Asistencia asistencia) {
        Optional<Asistencia> existingOpt = asistenciaRepository.findById(id);
        if (existingOpt.isEmpty()) {
            throw new IllegalArgumentException("Asistencia no encontrada con id: " + id);
        }

        Asistencia existing = existingOpt.get();
        existing.setEstado(asistencia.getEstado());
        existing.setObservaciones(asistencia.getObservaciones());

        Asistencia updated = asistenciaRepository.save(existing);
        return asistenciaMapper.toDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!asistenciaRepository.existsById(id)) {
            throw new IllegalArgumentException("Asistencia no encontrada con id: " + id);
        }
        asistenciaRepository.deleteById(id);
    }
}

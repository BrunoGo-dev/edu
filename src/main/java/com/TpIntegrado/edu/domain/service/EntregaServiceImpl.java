package com.TpIntegrado.edu.domain.service;

import com.TpIntegrado.edu.persistance.entity.Entrega;
import com.TpIntegrado.edu.persistance.entity.Tarea;
import com.TpIntegrado.edu.persistance.entity.Usuario;
import com.TpIntegrado.edu.persistance.repository.EntregaRepository;
import com.TpIntegrado.edu.persistance.repository.TareaRepository;
import com.TpIntegrado.edu.persistance.repository.UsuarioRepository;
import com.TpIntegrado.edu.persistance.repository.ObservacionRepository;
import com.TpIntegrado.edu.web.dto.EntregaDTO;
import com.TpIntegrado.edu.web.dto.EntregaRequest;
import com.TpIntegrado.edu.web.dto.FeedbackRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntregaServiceImpl implements EntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ObservacionRepository observacionRepository;

    @Override
    @Transactional
    public EntregaDTO createEntrega(Long tareaId, EntregaRequest request) {
        Tarea tarea = tareaRepository.findById(tareaId)
                .orElseThrow(() -> new IllegalArgumentException("Tarea no encontrada"));

        Usuario estudiante = usuarioRepository.findById(request.getEstudianteId())
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado"));

        // Verificar si ya existe una entrega para esta tarea y estudiante
        if (!entregaRepository.findByTareaIdAndEstudianteId(tareaId, request.getEstudianteId()).isEmpty()) {
            throw new IllegalArgumentException("El estudiante ya ha realizado una entrega para esta tarea");
        }

        Entrega entrega = new Entrega();
        entrega.setTarea(tarea);
        entrega.setEstudiante(estudiante);
        entrega.setContenido(request.getContenido());
        entrega.setFechaEntrega(LocalDateTime.now());

        Entrega saved = entregaRepository.save(entrega);
        return mapToDTO(saved, null);
    }

    @Override
    @Transactional
    public EntregaDTO addFeedback(Long entregaId, FeedbackRequest request) {
        Entrega entrega = entregaRepository.findById(entregaId)
                .orElseThrow(() -> new IllegalArgumentException("Entrega no encontrada"));

        Usuario docente = usuarioRepository.findById(request.getDocenteId())
                .orElseThrow(() -> new IllegalArgumentException("Docente no encontrado"));

        entrega.setCalificacion(request.getCalificacion());
        Entrega updated = entregaRepository.save(entrega);

        // Crear Observación
        com.TpIntegrado.edu.persistance.entity.Observacion observacion = new com.TpIntegrado.edu.persistance.entity.Observacion();
        observacion.setEstudiante(entrega.getEstudiante());
        observacion.setDocente(docente);
        observacion.setCurso(entrega.getTarea().getCurso());
        observacion.setTitulo("Devolución Entrega: " + entrega.getTarea().getTitulo());
        observacion.setContenido(request.getObservaciones());

        try {
            if (request.getTipo() != null) {
                observacion.setTipo(com.TpIntegrado.edu.persistance.entity.TipoObservacion.valueOf(request.getTipo()));
            } else {
                observacion.setTipo(com.TpIntegrado.edu.persistance.entity.TipoObservacion.NEUTRAL);
            }
        } catch (IllegalArgumentException e) {
            observacion.setTipo(com.TpIntegrado.edu.persistance.entity.TipoObservacion.NEUTRAL);
        }

        observacion.setFechaCreacion(LocalDateTime.now());
        observacionRepository.save(observacion);

        return mapToDTO(updated, request.getObservaciones());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EntregaDTO> findByTareaId(Long tareaId) {
        return entregaRepository.findByTareaId(tareaId).stream()
                .map(e -> mapToDTO(e, null))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EntregaDTO> findByEstudianteId(Long estudianteId) {
        return entregaRepository.findByEstudianteId(estudianteId).stream()
                .map(e -> mapToDTO(e, null))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EntregaDTO getEntregaByTareaAndEstudiante(Long tareaId, Long estudianteId) {
        List<Entrega> entregas = entregaRepository.findByTareaIdAndEstudianteId(tareaId, estudianteId);
        if (entregas.isEmpty()) {
            throw new IllegalArgumentException("Entrega no encontrada para la tarea y estudiante especificados");
        }
        return mapToDTO(entregas.get(0), null);
    }

    private EntregaDTO mapToDTO(Entrega entrega, String observaciones) {
        return new EntregaDTO(
                entrega.getId(),
                entrega.getTarea().getId(),
                entrega.getEstudiante().getId(),
                entrega.getEstudiante().getNombre(),
                entrega.getContenido(),
                entrega.getFechaEntrega(),
                entrega.getCalificacion(),
                observaciones);
    }
}

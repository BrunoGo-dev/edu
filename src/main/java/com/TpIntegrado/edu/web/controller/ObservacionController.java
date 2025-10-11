package com.TpIntegrado.edu.web.controller;

import com.TpIntegrado.edu.domain.service.ObservacionService;
import com.TpIntegrado.edu.persistance.entity.Curso;
import com.TpIntegrado.edu.persistance.entity.Observacion;
import com.TpIntegrado.edu.persistance.entity.TipoObservacion;
import com.TpIntegrado.edu.persistance.entity.Usuario;
import com.TpIntegrado.edu.persistance.repository.CursoRepository;
import com.TpIntegrado.edu.persistance.repository.UsuarioRepository;
import com.TpIntegrado.edu.web.dto.ObservacionDTO;
import com.TpIntegrado.edu.web.dto.ObservacionRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controlador REST para la gestión de observaciones
 * Endpoints:
 * - GET    /api/observaciones                              - Obtener todas las observaciones
 * - GET    /api/observaciones/{id}                        - Obtener observación por ID
 * - POST   /api/observaciones                              - Crear nueva observación
 * - PUT    /api/observaciones/{id}                        - Actualizar observación
 * - DELETE /api/observaciones/{id}                        - Eliminar observación
 * - GET    /api/observaciones/estudiante/{id}             - Observaciones de un estudiante
 * - GET    /api/observaciones/docente/{id}                - Observaciones de un docente
 * - GET    /api/observaciones/curso/{id}                  - Observaciones de un curso
 * - GET    /api/observaciones/estudiante/{estudianteId}/curso/{cursoId} - Observaciones de estudiante en curso
 * - GET    /api/observaciones/estudiante/{estudianteId}/tipo/{tipo} - Observaciones de estudiante por tipo
 */
@RestController
@RequestMapping("/api/observaciones")
public class ObservacionController {

    @Autowired
    private ObservacionService observacionService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public ResponseEntity<List<ObservacionDTO>> getAllObservaciones() {
        List<ObservacionDTO> observaciones = observacionService.findAll();
        return ResponseEntity.ok(observaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObservacionDTO> getObservacionById(@PathVariable Long id) {
        return observacionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/estudiante/{estudianteId}")
    public ResponseEntity<List<ObservacionDTO>> getObservacionesByEstudiante(@PathVariable Long estudianteId) {
        List<ObservacionDTO> observaciones = observacionService.findByEstudianteId(estudianteId);
        return ResponseEntity.ok(observaciones);
    }

    @GetMapping("/docente/{docenteId}")
    public ResponseEntity<List<ObservacionDTO>> getObservacionesByDocente(@PathVariable Long docenteId) {
        List<ObservacionDTO> observaciones = observacionService.findByDocenteId(docenteId);
        return ResponseEntity.ok(observaciones);
    }

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<ObservacionDTO>> getObservacionesByCurso(@PathVariable Long cursoId) {
        List<ObservacionDTO> observaciones = observacionService.findByCursoId(cursoId);
        return ResponseEntity.ok(observaciones);
    }

    @GetMapping("/estudiante/{estudianteId}/curso/{cursoId}")
    public ResponseEntity<List<ObservacionDTO>> getObservacionesByEstudianteAndCurso(
            @PathVariable Long estudianteId,
            @PathVariable Long cursoId) {
        List<ObservacionDTO> observaciones = observacionService.findByEstudianteAndCurso(estudianteId, cursoId);
        return ResponseEntity.ok(observaciones);
    }

    @GetMapping("/estudiante/{estudianteId}/tipo/{tipo}")
    public ResponseEntity<List<ObservacionDTO>> getObservacionesByEstudianteAndTipo(
            @PathVariable Long estudianteId,
            @PathVariable TipoObservacion tipo) {
        List<ObservacionDTO> observaciones = observacionService.findByEstudianteAndTipo(estudianteId, tipo);
        return ResponseEntity.ok(observaciones);
    }

    @PostMapping
    public ResponseEntity<?> createObservacion(@Valid @RequestBody ObservacionRequest request) {
        Usuario estudiante = usuarioRepository.findById(request.getEstudianteId())
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado"));
        Usuario docente = usuarioRepository.findById(request.getDocenteId())
                .orElseThrow(() -> new IllegalArgumentException("Docente no encontrado"));
        Curso curso = cursoRepository.findById(request.getCursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));

        Observacion observacion = new Observacion(estudiante, docente, curso, 
                request.getTitulo(), request.getContenido(), request.getTipo());

        ObservacionDTO saved = observacionService.save(observacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateObservacion(@PathVariable Long id, @Valid @RequestBody ObservacionRequest request) {
        Observacion observacion = new Observacion();
        observacion.setTitulo(request.getTitulo());
        observacion.setContenido(request.getContenido());
        observacion.setTipo(request.getTipo());

        ObservacionDTO updated = observacionService.update(id, observacion);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteObservacion(@PathVariable Long id) {
        observacionService.delete(id);
        return ResponseEntity.ok(Map.of("message", "Observación eliminada exitosamente"));
    }
}

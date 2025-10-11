package com.TpIntegrado.edu.web.controller;

import com.TpIntegrado.edu.domain.service.NotaService;
import com.TpIntegrado.edu.persistance.entity.Evaluacion;
import com.TpIntegrado.edu.persistance.entity.Nota;
import com.TpIntegrado.edu.persistance.entity.Usuario;
import com.TpIntegrado.edu.persistance.repository.EvaluacionRepository;
import com.TpIntegrado.edu.persistance.repository.UsuarioRepository;
import com.TpIntegrado.edu.web.dto.NotaDTO;
import com.TpIntegrado.edu.web.dto.NotaRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controlador REST para la gestión de notas
 * Endpoints:
 * - GET    /api/notas                          - Obtener todas las notas
 * - GET    /api/notas/{id}                    - Obtener nota por ID
 * - POST   /api/notas                          - Crear nueva nota
 * - PUT    /api/notas/{id}                    - Actualizar nota
 * - DELETE /api/notas/{id}                    - Eliminar nota
 * - GET    /api/notas/estudiante/{id}         - Notas de un estudiante
 * - GET    /api/notas/evaluacion/{id}         - Notas de una evaluación
 * - GET    /api/notas/curso/{id}              - Notas de un curso
 * - GET    /api/notas/estudiante/{estudianteId}/curso/{cursoId} - Notas de estudiante en curso
 */
@RestController
@RequestMapping("/api/notas")
public class NotaController {

    @Autowired
    private NotaService notaService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    @GetMapping
    public ResponseEntity<List<NotaDTO>> getAllNotas() {
        List<NotaDTO> notas = notaService.findAll();
        return ResponseEntity.ok(notas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaDTO> getNotaById(@PathVariable Long id) {
        return notaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/estudiante/{estudianteId}")
    public ResponseEntity<List<NotaDTO>> getNotasByEstudiante(@PathVariable Long estudianteId) {
        List<NotaDTO> notas = notaService.findByEstudianteId(estudianteId);
        return ResponseEntity.ok(notas);
    }

    @GetMapping("/evaluacion/{evaluacionId}")
    public ResponseEntity<List<NotaDTO>> getNotasByEvaluacion(@PathVariable Long evaluacionId) {
        List<NotaDTO> notas = notaService.findByEvaluacionId(evaluacionId);
        return ResponseEntity.ok(notas);
    }

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<NotaDTO>> getNotasByCurso(@PathVariable Long cursoId) {
        List<NotaDTO> notas = notaService.findByCursoId(cursoId);
        return ResponseEntity.ok(notas);
    }

    @GetMapping("/estudiante/{estudianteId}/curso/{cursoId}")
    public ResponseEntity<List<NotaDTO>> getNotasByEstudianteAndCurso(
            @PathVariable Long estudianteId,
            @PathVariable Long cursoId) {
        List<NotaDTO> notas = notaService.findByEstudianteAndCurso(estudianteId, cursoId);
        return ResponseEntity.ok(notas);
    }

    @PostMapping
    public ResponseEntity<?> createNota(@Valid @RequestBody NotaRequest request) {
        Usuario estudiante = usuarioRepository.findById(request.getEstudianteId())
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado"));
        Evaluacion evaluacion = evaluacionRepository.findById(request.getEvaluacionId())
                .orElseThrow(() -> new IllegalArgumentException("Evaluación no encontrada"));

        Nota nota = new Nota(estudiante, evaluacion, request.getNota());
        nota.setObservaciones(request.getObservaciones());

        NotaDTO saved = notaService.save(nota);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNota(@PathVariable Long id, @Valid @RequestBody NotaRequest request) {
        Nota nota = new Nota();
        nota.setNota(request.getNota());
        nota.setObservaciones(request.getObservaciones());

        NotaDTO updated = notaService.update(id, nota);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNota(@PathVariable Long id) {
        notaService.delete(id);
        return ResponseEntity.ok(Map.of("message", "Nota eliminada exitosamente"));
    }
}

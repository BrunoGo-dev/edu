package com.TpIntegrado.edu.web.controller;

import com.TpIntegrado.edu.domain.service.TareaService;
import com.TpIntegrado.edu.persistance.entity.Curso;
import com.TpIntegrado.edu.persistance.entity.Tarea;
import com.TpIntegrado.edu.persistance.repository.CursoRepository;
import com.TpIntegrado.edu.web.dto.TareaDTO;
import com.TpIntegrado.edu.web.dto.TareaRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controlador REST para la gestión de tareas
 * Endpoints:
 * - GET    /api/tareas                    - Obtener todas las tareas
 * - GET    /api/tareas/{id}              - Obtener tarea por ID
 * - POST   /api/tareas                    - Crear nueva tarea
 * - PUT    /api/tareas/{id}              - Actualizar tarea
 * - DELETE /api/tareas/{id}              - Eliminar tarea
 * - GET    /api/tareas/curso/{id}        - Tareas de un curso
 * - GET    /api/tareas/curso/{id}/activas - Tareas activas de un curso
 * - GET    /api/tareas/activas           - Todas las tareas activas
 * - GET    /api/tareas/curso/{id}/pendientes - Tareas pendientes de un curso
 */
@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public ResponseEntity<List<TareaDTO>> getAllTareas() {
        List<TareaDTO> tareas = tareaService.findAll();
        return ResponseEntity.ok(tareas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TareaDTO> getTareaById(@PathVariable Long id) {
        return tareaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/curso/{cursoId}")
    public ResponseEntity<List<TareaDTO>> getTareasByCurso(@PathVariable Long cursoId) {
        List<TareaDTO> tareas = tareaService.findByCursoId(cursoId);
        return ResponseEntity.ok(tareas);
    }

    @GetMapping("/curso/{cursoId}/activas")
    public ResponseEntity<List<TareaDTO>> getTareasActivasByCurso(@PathVariable Long cursoId) {
        List<TareaDTO> tareas = tareaService.findActiveByCursoId(cursoId);
        return ResponseEntity.ok(tareas);
    }

    @GetMapping("/activas")
    public ResponseEntity<List<TareaDTO>> getAllTareasActivas() {
        List<TareaDTO> tareas = tareaService.findAllActive();
        return ResponseEntity.ok(tareas);
    }

    @GetMapping("/curso/{cursoId}/pendientes")
    public ResponseEntity<List<TareaDTO>> getTareasPendientesByCurso(@PathVariable Long cursoId) {
        List<TareaDTO> tareas = tareaService.findPendingByCurso(cursoId);
        return ResponseEntity.ok(tareas);
    }

    @PostMapping
    public ResponseEntity<?> createTarea(@Valid @RequestBody TareaRequest request) {
        Curso curso = cursoRepository.findById(request.getCursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));

        Tarea tarea = new Tarea(curso, request.getTitulo(), request.getDescripcion(), request.getFechaLimite());

        TareaDTO saved = tareaService.save(tarea);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTarea(@PathVariable Long id, @Valid @RequestBody TareaRequest request) {
        Tarea tarea = new Tarea();
        tarea.setTitulo(request.getTitulo());
        tarea.setDescripcion(request.getDescripcion());
        tarea.setFechaLimite(request.getFechaLimite());
        tarea.setActivo(true);

        TareaDTO updated = tareaService.update(id, tarea);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTarea(@PathVariable Long id) {
        tareaService.delete(id);
        return ResponseEntity.ok(Map.of("message", "Tarea eliminada exitosamente"));
    }
}

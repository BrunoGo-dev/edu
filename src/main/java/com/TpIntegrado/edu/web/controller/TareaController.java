package com.TpIntegrado.edu.web.controller;

import com.TpIntegrado.edu.domain.service.TareaService;
import com.TpIntegrado.edu.persistance.entity.Curso;
import com.TpIntegrado.edu.persistance.entity.Tarea;
import com.TpIntegrado.edu.persistance.repository.CursoRepository;
import com.TpIntegrado.edu.web.dto.TareaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public ResponseEntity<?> createTarea(@RequestBody Map<String, Object> request) {
        try {
            Long cursoId = Long.valueOf(request.get("cursoId").toString());
            String titulo = request.get("titulo").toString();
            String descripcion = request.get("descripcion") != null ? request.get("descripcion").toString() : null;
            LocalDate fechaLimite = LocalDate.parse(request.get("fechaLimite").toString());

            Curso curso = cursoRepository.findById(cursoId)
                    .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));

            Tarea tarea = new Tarea(curso, titulo, descripcion, fechaLimite);

            TareaDTO saved = tareaService.save(tarea);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al crear la tarea"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTarea(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        try {
            String titulo = request.get("titulo").toString();
            String descripcion = request.get("descripcion") != null ? request.get("descripcion").toString() : null;
            LocalDate fechaLimite = LocalDate.parse(request.get("fechaLimite").toString());
            Boolean activo = request.get("activo") != null ? Boolean.valueOf(request.get("activo").toString()) : true;

            Tarea tarea = new Tarea();
            tarea.setTitulo(titulo);
            tarea.setDescripcion(descripcion);
            tarea.setFechaLimite(fechaLimite);
            tarea.setActivo(activo);

            TareaDTO updated = tareaService.update(id, tarea);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al actualizar la tarea"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTarea(@PathVariable Long id) {
        try {
            tareaService.delete(id);
            return ResponseEntity.ok(Map.of("message", "Tarea eliminada exitosamente"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al eliminar la tarea"));
        }
    }
}

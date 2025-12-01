package com.TpIntegrado.edu.web.controller;

import com.TpIntegrado.edu.domain.service.EntregaService;
import com.TpIntegrado.edu.web.dto.EntregaDTO;
import com.TpIntegrado.edu.web.dto.EntregaRequest;
import com.TpIntegrado.edu.web.dto.FeedbackRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de entregas de tareas
 * Endpoints:
 * - POST /api/tareas/{id}/entrega - Crear nueva entrega
 * - POST /api/entregas/{id}/feedback - Agregar retroalimentación a una entrega
 * - GET /api/tareas/{id}/entregas - Obtener todas las entregas de una tarea
 */
@RestController
@RequestMapping("/api")
public class EntregaController {

    @Autowired
    private EntregaService entregaService;

    @PostMapping("/tareas/{id}/entrega")
    public ResponseEntity<EntregaDTO> createEntrega(@PathVariable Long id, @Valid @RequestBody EntregaRequest request) {
        EntregaDTO entrega = entregaService.createEntrega(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(entrega);
    }

    @PostMapping("/entregas/{id}/feedback")
    public ResponseEntity<EntregaDTO> addFeedback(@PathVariable Long id, @Valid @RequestBody FeedbackRequest request) {
        EntregaDTO entrega = entregaService.addFeedback(id, request);
        return ResponseEntity.ok(entrega);
    }

    @GetMapping("/tareas/{id}/entregas")
    public ResponseEntity<List<EntregaDTO>> getEntregasByTarea(@PathVariable Long id) {
        List<EntregaDTO> entregas = entregaService.findByTareaId(id);
        return ResponseEntity.ok(entregas);
    }

    @GetMapping("/tareas/{tareaId}/entregas/estudiante/{estudianteId}")
    public ResponseEntity<EntregaDTO> getEntregaByTareaAndEstudiante(@PathVariable Long tareaId,
            @PathVariable Long estudianteId) {
        try {
            EntregaDTO entrega = entregaService.getEntregaByTareaAndEstudiante(tareaId, estudianteId);
            return ResponseEntity.ok(entrega);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

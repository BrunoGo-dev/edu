package com.TpIntegrado.edu.web.controller;

import com.TpIntegrado.edu.domain.service.AsistenciaService;
import com.TpIntegrado.edu.persistance.entity.Asistencia;
import com.TpIntegrado.edu.persistance.entity.Clase;
import com.TpIntegrado.edu.persistance.entity.EstadoAsistencia;
import com.TpIntegrado.edu.persistance.entity.Usuario;
import com.TpIntegrado.edu.persistance.repository.ClaseRepository;
import com.TpIntegrado.edu.persistance.repository.UsuarioRepository;
import com.TpIntegrado.edu.web.dto.AsistenciaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Controlador REST para la gestión de asistencias
 * Endpoints:
 * - GET    /api/asistencias                    - Obtener todas las asistencias
 * - GET    /api/asistencias/{id}              - Obtener asistencia por ID
 * - POST   /api/asistencias                    - Crear nueva asistencia
 * - PUT    /api/asistencias/{id}              - Actualizar asistencia
 * - DELETE /api/asistencias/{id}              - Eliminar asistencia
 * - GET    /api/asistencias/estudiante/{id}   - Asistencias de un estudiante
 * - GET    /api/asistencias/clase/{id}        - Asistencias de una clase
 * - GET    /api/asistencias/curso/{cursoId}/fecha/{fecha} - Asistencias por curso y fecha
 * - GET    /api/asistencias/estudiante/{estudianteId}/curso/{cursoId} - Asistencias de estudiante en curso
 * - GET    /api/asistencias/estudiante/{estudianteId}/curso/{cursoId}/resumen - Resumen de asistencias
 */
@RestController
@RequestMapping("/api/asistencias")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;

    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<AsistenciaDTO>> getAllAsistencias() {
        List<AsistenciaDTO> asistencias = asistenciaService.findAll();
        return ResponseEntity.ok(asistencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsistenciaDTO> getAsistenciaById(@PathVariable Long id) {
        return asistenciaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/estudiante/{estudianteId}")
    public ResponseEntity<List<AsistenciaDTO>> getAsistenciasByEstudiante(@PathVariable Long estudianteId) {
        List<AsistenciaDTO> asistencias = asistenciaService.findByEstudianteId(estudianteId);
        return ResponseEntity.ok(asistencias);
    }

    @GetMapping("/clase/{claseId}")
    public ResponseEntity<List<AsistenciaDTO>> getAsistenciasByClase(@PathVariable Long claseId) {
        List<AsistenciaDTO> asistencias = asistenciaService.findByClaseId(claseId);
        return ResponseEntity.ok(asistencias);
    }

    @GetMapping("/estudiante/{estudianteId}/curso/{cursoId}")
    public ResponseEntity<List<AsistenciaDTO>> getAsistenciasByEstudianteAndCurso(
            @PathVariable Long estudianteId, 
            @PathVariable Long cursoId) {
        List<AsistenciaDTO> asistencias = asistenciaService.findByEstudianteAndCurso(estudianteId, cursoId);
        return ResponseEntity.ok(asistencias);
    }

    @GetMapping("/curso/{cursoId}/fecha/{fecha}")
    public ResponseEntity<List<AsistenciaDTO>> getAsistenciasByCursoAndFecha(
            @PathVariable Long cursoId,
            @PathVariable String fecha) {
        LocalDate localDate = LocalDate.parse(fecha);
        List<AsistenciaDTO> asistencias = asistenciaService.findByCursoAndFecha(cursoId, localDate);
        return ResponseEntity.ok(asistencias);
    }

    @GetMapping("/estudiante/{estudianteId}/curso/{cursoId}/resumen")
    public ResponseEntity<Map<String, Long>> getResumenAsistencias(
            @PathVariable Long estudianteId,
            @PathVariable Long cursoId) {
        Long presentes = asistenciaService.countByEstudianteAndCursoAndEstado(estudianteId, cursoId, EstadoAsistencia.PRESENTE);
        Long ausentes = asistenciaService.countByEstudianteAndCursoAndEstado(estudianteId, cursoId, EstadoAsistencia.AUSENTE);
        Long tardanzas = asistenciaService.countByEstudianteAndCursoAndEstado(estudianteId, cursoId, EstadoAsistencia.TARDANZA);
        Long justificadas = asistenciaService.countByEstudianteAndCursoAndEstado(estudianteId, cursoId, EstadoAsistencia.JUSTIFICADO);
        
        Map<String, Long> resumen = Map.of(
            "presentes", presentes,
            "ausentes", ausentes,
            "tardanzas", tardanzas,
            "justificadas", justificadas
        );
        
        return ResponseEntity.ok(resumen);
    }

    @PostMapping
    public ResponseEntity<?> createAsistencia(@RequestBody Map<String, Object> request) {
        try {
            Long claseId = Long.valueOf(request.get("claseId").toString());
            Long estudianteId = Long.valueOf(request.get("estudianteId").toString());
            EstadoAsistencia estado = EstadoAsistencia.valueOf(request.get("estado").toString());
            String observaciones = request.get("observaciones") != null ? request.get("observaciones").toString() : null;

            Clase clase = claseRepository.findById(claseId)
                    .orElseThrow(() -> new IllegalArgumentException("Clase no encontrada"));
            Usuario estudiante = usuarioRepository.findById(estudianteId)
                    .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado"));

            Asistencia asistencia = new Asistencia(clase, estudiante, estado);
            asistencia.setObservaciones(observaciones);

            AsistenciaDTO saved = asistenciaService.save(asistencia);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al crear la asistencia"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAsistencia(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        try {
            EstadoAsistencia estado = EstadoAsistencia.valueOf(request.get("estado").toString());
            String observaciones = request.get("observaciones") != null ? request.get("observaciones").toString() : null;

            Asistencia asistencia = new Asistencia();
            asistencia.setEstado(estado);
            asistencia.setObservaciones(observaciones);

            AsistenciaDTO updated = asistenciaService.update(id, asistencia);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al actualizar la asistencia"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAsistencia(@PathVariable Long id) {
        try {
            asistenciaService.delete(id);
            return ResponseEntity.ok(Map.of("message", "Asistencia eliminada exitosamente"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al eliminar la asistencia"));
        }
    }
}

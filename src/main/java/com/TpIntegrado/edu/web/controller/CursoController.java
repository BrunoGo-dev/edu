package com.TpIntegrado.edu.web.controller;

import com.TpIntegrado.edu.domain.service.CursoService;
import com.TpIntegrado.edu.persistance.entity.Curso;
import com.TpIntegrado.edu.persistance.entity.Materia;
import com.TpIntegrado.edu.persistance.entity.Usuario;
import com.TpIntegrado.edu.persistance.repository.MateriaRepository;
import com.TpIntegrado.edu.persistance.repository.UsuarioRepository;
import com.TpIntegrado.edu.web.dto.CursoDTO;
import com.TpIntegrado.edu.web.dto.CursoRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controlador REST para la gestión de cursos
 * Endpoints:
 * - GET /api/cursos - Obtener todos los cursos
 * - GET /api/cursos/{id} - Obtener curso por ID
 * - GET /api/cursos/activos - Obtener todos los cursos activos
 * - GET /api/cursos/docente/{docenteId} - Cursos de un docente
 * - GET /api/cursos/docente/{docenteId}/activos - Cursos activos de un docente
 * - GET /api/cursos/estudiante/{estudianteId} - Cursos de un estudiante
 * - GET /api/cursos/estudiante/{estudianteId}/activos - Cursos activos de un
 * estudiante
 * - POST /api/cursos - Crear nuevo curso
 * - PUT /api/cursos/{id} - Actualizar curso
 * - DELETE /api/cursos/{id} - Eliminar curso
 */
@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    // Endpoints generales
    @GetMapping
    public ResponseEntity<List<CursoDTO>> getAllCursos() {
        List<CursoDTO> cursos = cursoService.findAll();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> getCursoById(@PathVariable Long id) {
        return cursoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<CursoDTO>> getAllCursosActivos() {
        List<CursoDTO> cursos = cursoService.findAllActivos();
        return ResponseEntity.ok(cursos);
    }

    // Endpoints por Docente
    @GetMapping("/docente/{docenteId}")
    public ResponseEntity<List<CursoDTO>> getCursosByDocente(@PathVariable Long docenteId) {
        List<CursoDTO> cursos = cursoService.findByDocenteId(docenteId);
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/docente/{docenteId}/activos")
    public ResponseEntity<List<CursoDTO>> getCursosActivosByDocente(@PathVariable Long docenteId) {
        List<CursoDTO> cursos = cursoService.findActivosByDocenteId(docenteId);
        return ResponseEntity.ok(cursos);
    }

    // Endpoints por Estudiante
    @GetMapping("/estudiante/{estudianteId}")
    public ResponseEntity<List<CursoDTO>> getCursosByEstudiante(@PathVariable Long estudianteId) {
        List<CursoDTO> cursos = cursoService.findByEstudianteId(estudianteId);
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/estudiante/{estudianteId}/activos")
    public ResponseEntity<List<CursoDTO>> getCursosActivosByEstudiante(@PathVariable Long estudianteId) {
        List<CursoDTO> cursos = cursoService.findActivosByEstudianteId(estudianteId);
        return ResponseEntity.ok(cursos);
    }

    // CRUD operations
    @PostMapping
    public ResponseEntity<?> createCurso(@Valid @RequestBody CursoRequest request) {
        Materia materia = materiaRepository.findById(request.getMateriaId())
                .orElseThrow(() -> new IllegalArgumentException("Materia no encontrada"));
        Usuario docente = usuarioRepository.findById(request.getDocenteId())
                .orElseThrow(() -> new IllegalArgumentException("Docente no encontrado"));

        Curso curso = new Curso(materia, docente, request.getNombre(), request.getPeriodo());
        CursoDTO saved = cursoService.save(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCurso(@PathVariable Long id, @Valid @RequestBody CursoRequest request) {
        Materia materia = materiaRepository.findById(request.getMateriaId())
                .orElseThrow(() -> new IllegalArgumentException("Materia no encontrada"));
        Usuario docente = usuarioRepository.findById(request.getDocenteId())
                .orElseThrow(() -> new IllegalArgumentException("Docente no encontrado"));

        Curso curso = new Curso();
        curso.setNombre(request.getNombre());
        curso.setPeriodo(request.getPeriodo());
        curso.setDocente(docente);
        curso.setMateria(materia);
        curso.setActivo(true);

        CursoDTO updated = cursoService.update(id, curso);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCurso(@PathVariable Long id) {
        cursoService.delete(id);
        return ResponseEntity.ok(Map.of("message", "Curso eliminado exitosamente"));
    }
}

package com.TpIntegrado.edu.domain.service;

import com.TpIntegrado.edu.persistance.entity.Curso;
import com.TpIntegrado.edu.persistance.repository.CursoRepository;
import com.TpIntegrado.edu.persistance.repository.InscripcionRepository;
import com.TpIntegrado.edu.persistance.repository.UsuarioRepository;
import com.TpIntegrado.edu.web.dto.CursoDTO;
import com.TpIntegrado.edu.web.dto.UsuarioResponse;
import com.TpIntegrado.edu.web.mapper.CursoMapper;
import com.TpIntegrado.edu.web.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoMapper cursoMapper;

    @Autowired
    private UserMapper userMapper;

    // Métodos generales
    public List<CursoDTO> findAll() {
        return StreamSupport.stream(cursoRepository.findAll().spliterator(), false)
                .map(cursoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<CursoDTO> findById(Long id) {
        return cursoRepository.findById(id)
                .map(cursoMapper::toDTO);
    }

    public List<CursoDTO> findAllActivos() {
        return cursoRepository.findByActivoTrue().stream()
                .map(cursoMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Métodos por Docente
    public List<CursoDTO> findByDocenteId(Long docenteId) {
        if (!usuarioRepository.existsById(docenteId)) {
            throw new IllegalArgumentException("Docente no encontrado con id: " + docenteId);
        }
        return cursoRepository.findByDocenteId(docenteId).stream()
                .map(cursoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<CursoDTO> findActivosByDocenteId(Long docenteId) {
        if (!usuarioRepository.existsById(docenteId)) {
            throw new IllegalArgumentException("Docente no encontrado con id: " + docenteId);
        }
        return cursoRepository.findByDocenteIdAndActivoTrue(docenteId).stream()
                .map(cursoMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Métodos por Estudiante
    public List<CursoDTO> findByEstudianteId(Long estudianteId) {
        if (!usuarioRepository.existsById(estudianteId)) {
            throw new IllegalArgumentException("Estudiante no encontrado con id: " + estudianteId);
        }
        return inscripcionRepository.findByEstudianteId(estudianteId).stream()
                .map(inscripcion -> inscripcion.getCurso())
                .map(cursoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<CursoDTO> findActivosByEstudianteId(Long estudianteId) {
        if (!usuarioRepository.existsById(estudianteId)) {
            throw new IllegalArgumentException("Estudiante no encontrado con id: " + estudianteId);
        }
        return inscripcionRepository.findByEstudianteId(estudianteId).stream()
                .map(inscripcion -> inscripcion.getCurso())
                .filter(curso -> curso.getActivo())
                .map(cursoMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Método para obtener estudiantes de un curso
    public List<UsuarioResponse> findEstudiantesByCursoId(Long cursoId) {
        if (!cursoRepository.existsById(cursoId)) {
            throw new IllegalArgumentException("Curso no encontrado con id: " + cursoId);
        }
        return inscripcionRepository.findByCursoId(cursoId).stream()
                .map(inscripcion -> inscripcion.getEstudiante())
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    // CRUD operations
    @Transactional
    public CursoDTO save(Curso curso) {
        Curso saved = cursoRepository.save(curso);
        return cursoMapper.toDTO(saved);
    }

    @Transactional
    public CursoDTO update(Long id, Curso curso) {
        Optional<Curso> existingOpt = cursoRepository.findById(id);
        if (existingOpt.isEmpty()) {
            throw new IllegalArgumentException("Curso no encontrado con id: " + id);
        }

        Curso existing = existingOpt.get();
        existing.setNombre(curso.getNombre());
        existing.setPeriodo(curso.getPeriodo());
        existing.setActivo(curso.getActivo());

        // Solo actualizar docente y materia si se proporcionan
        if (curso.getDocente() != null) {
            existing.setDocente(curso.getDocente());
        }
        if (curso.getMateria() != null) {
            existing.setMateria(curso.getMateria());
        }

        Curso updated = cursoRepository.save(existing);
        return cursoMapper.toDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!cursoRepository.existsById(id)) {
            throw new IllegalArgumentException("Curso no encontrado con id: " + id);
        }
        cursoRepository.deleteById(id);
    }
}

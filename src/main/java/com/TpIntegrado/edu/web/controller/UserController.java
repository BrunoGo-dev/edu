package com.TpIntegrado.edu.web.controller;

import com.TpIntegrado.edu.persistance.entity.Usuario;
import com.TpIntegrado.edu.persistance.repository.UsuarioRepository;
import com.TpIntegrado.edu.web.dto.UsuarioRequest;
import com.TpIntegrado.edu.web.dto.UsuarioResponse;
import com.TpIntegrado.edu.web.mapper.UserMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UserMapper mapper;

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listAll() {
        List<UsuarioResponse> users = ((List<Usuario>) usuarioRepository.findAll())
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Usuario> user = usuarioRepository.findById(id);
        return user.map(u -> ResponseEntity.ok(mapper.toResponse(u)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UsuarioRequest req) {
        if (usuarioRepository.existsByUsername(req.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("username ya existe");
        }
        if (req.getEmail() != null && usuarioRepository.existsByEmail(req.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("email ya existe");
        }

        Usuario toSave = mapper.toEntity(req);
        Usuario saved = usuarioRepository.save(toSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody UsuarioRequest req) {
        Optional<Usuario> existingOpt = usuarioRepository.findById(id);
        if (existingOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Usuario existing = existingOpt.get();

        if (req.getUsername() != null && !req.getUsername().equals(existing.getUsername())
                && usuarioRepository.existsByUsername(req.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("username ya existe");
        }
        if (req.getEmail() != null && !req.getEmail().equals(existing.getEmail())
                && usuarioRepository.existsByEmail(req.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("email ya existe");
        }

        mapper.updateEntityFromRequest(existing, req);
        Usuario saved = usuarioRepository.save(existing);
        return ResponseEntity.ok(mapper.toResponse(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!usuarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        usuarioRepository.deleteById(id);
        return ResponseEntity.ok().body("Usuario eliminado");
    }
}

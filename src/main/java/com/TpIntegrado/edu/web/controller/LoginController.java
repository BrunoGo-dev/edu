package com.TpIntegrado.edu.web.controller;

import com.TpIntegrado.edu.persistance.entity.Usuario;
import com.TpIntegrado.edu.persistance.repository.UsuarioRepository;
import com.TpIntegrado.edu.security.JwtUtil;
import com.TpIntegrado.edu.web.dto.AuthResponse;
import com.TpIntegrado.edu.web.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // POST /login - recibe username/password y devuelve token + role
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<Usuario> userOpt = usuarioRepository.findByUsername(request.getUsername());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }

        Usuario usuario = userOpt.get();
        // PasswordEncoder
        if (usuario.getPassword() == null || !usuario.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }

        if (Boolean.FALSE.equals(usuario.getActivo())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuario inactivo");
        }

        String token = jwtUtil.generateToken(usuario);
        String role = usuario.getRol() != null ? usuario.getRol().name() : null;

        AuthResponse resp = new AuthResponse(token, role);
        return ResponseEntity.ok(resp);
    }

}
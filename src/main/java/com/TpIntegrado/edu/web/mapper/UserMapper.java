package com.TpIntegrado.edu.web.mapper;

import com.TpIntegrado.edu.persistance.entity.Usuario;
import com.TpIntegrado.edu.web.dto.UsuarioRequest;
import com.TpIntegrado.edu.web.dto.UsuarioResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public Usuario toEntity(UsuarioRequest req) {
        if (req == null)
            return null;
        Usuario u = new Usuario();
        u.setUsername(req.getUsername());
        u.setPassword(req.getPassword());
        u.setNombre(req.getNombre());
        u.setEmail(req.getEmail());
        u.setRol(req.getRol());
        u.setActivo(req.getActivo() != null ? req.getActivo() : true);
        return u;
    }

    public UsuarioResponse toResponse(Usuario u) {
        if (u == null)
            return null;
        UsuarioResponse r = new UsuarioResponse();
        r.setId(u.getId());
        r.setUsername(u.getUsername());
        r.setNombre(u.getNombre());
        r.setEmail(u.getEmail());
        r.setRol(u.getRol());
        r.setActivo(u.getActivo());
        return r;
    }

    public void updateEntityFromRequest(Usuario existing, UsuarioRequest req) {
        if (req == null || existing == null)
            return;
        if (req.getUsername() != null)
            existing.setUsername(req.getUsername());
        if (req.getPassword() != null)
            existing.setPassword(req.getPassword());
        if (req.getNombre() != null)
            existing.setNombre(req.getNombre());
        if (req.getEmail() != null)
            existing.setEmail(req.getEmail());
        if (req.getRol() != null)
            existing.setRol(req.getRol());
        if (req.getActivo() != null)
            existing.setActivo(req.getActivo());
    }
}

package com.TpIntegrado.edu.web.mapper;

import com.TpIntegrado.edu.persistance.entity.Curso;
import com.TpIntegrado.edu.web.dto.CursoDTO;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {

    public CursoDTO toDTO(Curso curso) {
        if (curso == null) {
            return null;
        }

        CursoDTO dto = new CursoDTO();
        dto.setId(curso.getId());
        dto.setNombre(curso.getNombre());
        dto.setPeriodo(curso.getPeriodo());
        dto.setActivo(curso.getActivo());
        dto.setMateriaId(curso.getMateria().getId());
        dto.setMateriaNombre(curso.getMateria().getNombre());
        dto.setDocenteId(curso.getDocente().getId());
        dto.setDocenteNombre(curso.getDocente().getNombre());

        return dto;
    }

    public Curso toEntity(CursoDTO dto) {
        if (dto == null) {
            return null;
        }

        Curso curso = new Curso();
        curso.setId(dto.getId());
        curso.setNombre(dto.getNombre());
        curso.setPeriodo(dto.getPeriodo());
        curso.setActivo(dto.getActivo());

        return curso;
    }
}

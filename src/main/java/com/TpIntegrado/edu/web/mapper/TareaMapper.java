package com.TpIntegrado.edu.web.mapper;

import com.TpIntegrado.edu.persistance.entity.Tarea;
import com.TpIntegrado.edu.web.dto.TareaDTO;
import org.springframework.stereotype.Component;

@Component
public class TareaMapper {

    public TareaDTO toDTO(Tarea tarea) {
        if (tarea == null) {
            return null;
        }

        TareaDTO dto = new TareaDTO();
        dto.setId(tarea.getId());
        dto.setCursoId(tarea.getCurso().getId());
        dto.setCursoNombre(tarea.getCurso().getNombre());
        dto.setTitulo(tarea.getTitulo());
        dto.setDescripcion(tarea.getDescripcion());
        dto.setFechaLimite(tarea.getFechaLimite());
        dto.setActivo(tarea.getActivo());
        
        return dto;
    }

    public Tarea toEntity(TareaDTO dto) {
        if (dto == null) {
            return null;
        }

        Tarea tarea = new Tarea();
        tarea.setId(dto.getId());
        tarea.setTitulo(dto.getTitulo());
        tarea.setDescripcion(dto.getDescripcion());
        tarea.setFechaLimite(dto.getFechaLimite());
        tarea.setActivo(dto.getActivo());
        
        return tarea;
    }
}

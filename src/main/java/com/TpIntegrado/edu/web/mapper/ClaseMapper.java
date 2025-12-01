package com.TpIntegrado.edu.web.mapper;

import com.TpIntegrado.edu.persistance.entity.Clase;
import com.TpIntegrado.edu.web.dto.ClaseDTO;
import org.springframework.stereotype.Component;

@Component
public class ClaseMapper {

    public ClaseDTO toDTO(Clase clase) {
        if (clase == null) {
            return null;
        }

        ClaseDTO dto = new ClaseDTO();
        dto.setId(clase.getId());
        dto.setCursoId(clase.getCurso().getId());
        dto.setCursoNombre(clase.getCurso().getNombre());
        dto.setFecha(clase.getFecha());
        dto.setHoraInicio(clase.getHoraInicio());
        dto.setHoraFin(clase.getHoraFin());
        dto.setTema(clase.getTema());
        dto.setDescripcion(clase.getDescripcion());
        
        return dto;
    }

    public Clase toEntity(ClaseDTO dto) {
        if (dto == null) {
            return null;
        }

        Clase clase = new Clase();
        clase.setId(dto.getId());
        clase.setFecha(dto.getFecha());
        clase.setHoraInicio(dto.getHoraInicio());
        clase.setHoraFin(dto.getHoraFin());
        clase.setTema(dto.getTema());
        clase.setDescripcion(dto.getDescripcion());
        
        return clase;
    }
}

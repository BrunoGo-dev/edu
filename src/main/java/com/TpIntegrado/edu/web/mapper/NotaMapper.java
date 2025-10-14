package com.TpIntegrado.edu.web.mapper;

import com.TpIntegrado.edu.persistance.entity.Nota;
import com.TpIntegrado.edu.web.dto.NotaDTO;
import org.springframework.stereotype.Component;

@Component
public class NotaMapper {

    public NotaDTO toDTO(Nota nota) {
        if (nota == null) {
            return null;
        }

        NotaDTO dto = new NotaDTO();
        dto.setId(nota.getId());
        dto.setEstudianteId(nota.getEstudiante().getId());
        dto.setEstudianteNombre(nota.getEstudiante().getNombre());
        dto.setEvaluacionId(nota.getEvaluacion().getId());
        dto.setEvaluacionNombre(nota.getEvaluacion().getNombre());
        dto.setNota(nota.getNota());
        dto.setObservaciones(nota.getObservaciones());
        
        return dto;
    }

    public Nota toEntity(NotaDTO dto) {
        if (dto == null) {
            return null;
        }

        Nota nota = new Nota();
        nota.setId(dto.getId());
        nota.setNota(dto.getNota());
        nota.setObservaciones(dto.getObservaciones());
        
        return nota;
    }
}

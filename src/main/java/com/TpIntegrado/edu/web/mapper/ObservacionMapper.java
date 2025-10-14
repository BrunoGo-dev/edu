package com.TpIntegrado.edu.web.mapper;

import com.TpIntegrado.edu.persistance.entity.Observacion;
import com.TpIntegrado.edu.web.dto.ObservacionDTO;
import org.springframework.stereotype.Component;

@Component
public class ObservacionMapper {

    public ObservacionDTO toDTO(Observacion observacion) {
        if (observacion == null) {
            return null;
        }

        ObservacionDTO dto = new ObservacionDTO();
        dto.setId(observacion.getId());
        dto.setEstudianteId(observacion.getEstudiante().getId());
        dto.setEstudianteNombre(observacion.getEstudiante().getNombre());
        dto.setDocenteId(observacion.getDocente().getId());
        dto.setDocenteNombre(observacion.getDocente().getNombre());
        dto.setCursoId(observacion.getCurso().getId());
        dto.setCursoNombre(observacion.getCurso().getNombre());
        dto.setTitulo(observacion.getTitulo());
        dto.setContenido(observacion.getContenido());
        dto.setTipo(observacion.getTipo());
        dto.setFechaCreacion(observacion.getFechaCreacion());
        
        return dto;
    }

    public Observacion toEntity(ObservacionDTO dto) {
        if (dto == null) {
            return null;
        }

        Observacion observacion = new Observacion();
        observacion.setId(dto.getId());
        observacion.setTitulo(dto.getTitulo());
        observacion.setContenido(dto.getContenido());
        observacion.setTipo(dto.getTipo());
        observacion.setFechaCreacion(dto.getFechaCreacion());
        
        return observacion;
    }
}

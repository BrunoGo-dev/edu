package com.TpIntegrado.edu.web.mapper;

import com.TpIntegrado.edu.persistance.entity.Asistencia;
import com.TpIntegrado.edu.web.dto.AsistenciaDTO;
import org.springframework.stereotype.Component;

@Component
public class AsistenciaMapper {

    public AsistenciaDTO toDTO(Asistencia asistencia) {
        if (asistencia == null) {
            return null;
        }

        AsistenciaDTO dto = new AsistenciaDTO();
        dto.setId(asistencia.getId());
        dto.setClaseId(asistencia.getClase().getId());
        dto.setEstudianteId(asistencia.getEstudiante().getId());
        dto.setEstudianteNombre(asistencia.getEstudiante().getNombre());
        dto.setEstado(asistencia.getEstado());
        dto.setFechaRegistro(asistencia.getFechaRegistro());
        dto.setObservaciones(asistencia.getObservaciones());
        
        return dto;
    }

    public Asistencia toEntity(AsistenciaDTO dto) {
        if (dto == null) {
            return null;
        }

        Asistencia asistencia = new Asistencia();
        asistencia.setId(dto.getId());
        asistencia.setEstado(dto.getEstado());
        asistencia.setFechaRegistro(dto.getFechaRegistro());
        asistencia.setObservaciones(dto.getObservaciones());
        
        return asistencia;
    }
}

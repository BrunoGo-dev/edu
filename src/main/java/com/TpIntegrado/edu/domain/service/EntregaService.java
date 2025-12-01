package com.TpIntegrado.edu.domain.service;

import com.TpIntegrado.edu.web.dto.EntregaDTO;
import com.TpIntegrado.edu.web.dto.EntregaRequest;
import com.TpIntegrado.edu.web.dto.FeedbackRequest;

import java.util.List;

public interface EntregaService {
    EntregaDTO createEntrega(Long tareaId, EntregaRequest request);

    EntregaDTO addFeedback(Long entregaId, FeedbackRequest request);

    List<EntregaDTO> findByTareaId(Long tareaId);

    List<EntregaDTO> findByEstudianteId(Long estudianteId);

    EntregaDTO getEntregaByTareaAndEstudiante(Long tareaId, Long estudianteId);
}

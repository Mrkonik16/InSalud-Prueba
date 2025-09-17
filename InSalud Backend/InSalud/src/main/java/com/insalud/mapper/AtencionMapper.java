package com.insalud.mapper;

import com.insalud.dto.AtencionResponseDto;
import com.insalud.entity.Atencion;

public class AtencionMapper {
    public static AtencionResponseDto toDto(Atencion a) {
        if (a == null) return null;

        AtencionResponseDto dto = new AtencionResponseDto();
        dto.setId(a.getId());
        dto.setFecha(a.getFecha());
        dto.setMotivo(a.getMotivo());
        dto.setPacienteId(a.getPaciente() != null ? a.getPaciente().getId() : null);
        dto.setPacienteNombre(a.getPaciente() != null ? a.getPaciente().getNombre() : null);
        dto.setMedicoId(a.getMedico() != null ? a.getMedico().getId() : null);
        dto.setMedicoNombre(a.getMedico() != null ? a.getMedico().getNombre() : null);
        dto.setEstado(a.getEstado() != null ? a.getEstado().name() : null);

        return dto;
    }
}

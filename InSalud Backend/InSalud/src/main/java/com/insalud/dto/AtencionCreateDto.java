package com.insalud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class AtencionCreateDto {
    @NotNull(message = "fecha es obligatoria")
    private LocalDateTime fecha;

    @NotBlank(message = "motivo es obligatorio")
    private String motivo;

    @NotNull(message = "pacienteId es obligatorio")
    private Long pacienteId;

    @NotNull(message = "medicoId es obligatorio")
    private Long medicoId;

    public AtencionCreateDto() {
    }

    public AtencionCreateDto(Long pacienteId, Long medicoId, LocalDateTime fecha, String motivo) {
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.fecha = fecha;
        this.motivo = motivo;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}

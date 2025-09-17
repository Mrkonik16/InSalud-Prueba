package com.insalud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class AtencionUpdateDto {
    @NotNull(message = "fecha es obligatoria")
    private LocalDateTime fecha;

    @NotBlank(message = "motivo es obligatorio")
    private String motivo;

    @NotNull(message = "medicoId es obligatorio")
    private Long medicoId;

    @NotNull(message = "estado es obligatorio")
    private String estado;

    public AtencionUpdateDto() {
    }

    public AtencionUpdateDto(Long medicoId, LocalDateTime fecha, String motivo, String estado) {
        this.medicoId = medicoId;
        this.fecha = fecha;
        this.motivo = motivo;
        this.estado = estado;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

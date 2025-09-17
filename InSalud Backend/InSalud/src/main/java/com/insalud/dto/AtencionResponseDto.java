package com.insalud.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

public class AtencionResponseDto {
    private Long id;
    private LocalDateTime fecha;
    private String motivo;
    private Long pacienteId;
    private String pacienteNombre;
    private Long medicoId;
    private String medicoNombre;
    private String estado;

    public AtencionResponseDto() {
    }

    public AtencionResponseDto(Long id, Long pacienteId, String pacienteNombre, Long medicoId, String medicoNombre, LocalDateTime fecha, String motivo, String estado) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.pacienteNombre = pacienteNombre;
        this.medicoId = medicoId;
        this.medicoNombre = medicoNombre;
        this.fecha = fecha;
        this.motivo = motivo;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getPacienteNombre() {
        return pacienteNombre;
    }

    public void setPacienteNombre(String pacienteNombre) {
        this.pacienteNombre = pacienteNombre;
    }

    public Long getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }

    public String getMedicoNombre() {
        return medicoNombre;
    }

    public void setMedicoNombre(String medicoNombre) {
        this.medicoNombre = medicoNombre;
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

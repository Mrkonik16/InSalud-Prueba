package com.insalud.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "atenciones", indexes = {
        @Index(name = "idx_atencion_fecha", columnList = "fecha"),
        @Index(name = "idx_atencion_medico", columnList = "medico_id")
})
public class Atencion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime fecha;

    @NotBlank
    @Column(nullable = false, length = 1000)
    private String motivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id", nullable = false)
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Medico medico;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoAtencion estado = EstadoAtencion.PENDIENTE;

    // Constructores
    public Atencion() {
    }

    public Atencion(LocalDateTime fecha, String motivo, Paciente paciente, Medico medico, EstadoAtencion estado) {
        this.fecha = fecha;
        this.motivo = motivo;
        this.paciente = paciente;
        this.medico = medico;
        this.estado = estado;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public EstadoAtencion getEstado() {
        return estado;
    }

    public void setEstado(EstadoAtencion estado) {
        this.estado = estado;
    }
}

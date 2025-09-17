package com.insalud.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "medicos")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado = Estado.ACTIVO;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "medico_especialidades",
            joinColumns = @JoinColumn(name = "medico_id"),
            inverseJoinColumns = @JoinColumn(name = "especialidad_id")
    )
    private Set<Especialidad> especialidades = new HashSet<>();

    // Constructores
    public Medico() {}

    public Medico(String nombre, Set<Especialidad> especialidades, Estado estado) {
        this.nombre = nombre;
        this.especialidades = especialidades;
        this.estado = estado;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Set<Especialidad> getEspecialidades() { return especialidades; }
    public void setEspecialidades(Set<Especialidad> especialidades) { this.especialidades = especialidades; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }
}

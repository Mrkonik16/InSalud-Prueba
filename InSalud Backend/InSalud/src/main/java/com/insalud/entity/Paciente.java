package com.insalud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pacientes", indexes = {
        @Index(name = "idx_paciente_email", columnList = "email", unique = true)
})
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nombre;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado = Estado.ACTIVO;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "paciente_roles", joinColumns = @JoinColumn(name = "paciente_id"))
    @Enumerated(EnumType.STRING)
    private Set<Rol> roles = new HashSet<>();

    // Constructores
    public Paciente() {}

    public Paciente(String nombre, String email, String password, Estado estado, Set<Rol> roles) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.estado = estado;
        this.roles = roles;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }

    public Set<Rol> getRoles() { return roles; }
    public void setRoles(Set<Rol> roles) { this.roles = roles; }
}

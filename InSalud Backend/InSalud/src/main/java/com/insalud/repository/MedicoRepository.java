package com.insalud.repository;

import com.insalud.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Optional<Medico> findByNombreContainingIgnoreCase(String nombre);
}

package com.insalud.repository;

import com.insalud.entity.Atencion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AtencionRepository extends JpaRepository<Atencion, Long> {

    List<Atencion> findByPacienteId(Long pacienteId);
    List<Atencion> findByMedicoId(Long medicoId);
    List<Atencion> findByFechaBetween(LocalDateTime from, LocalDateTime to);

    //Consulta flexible - filtro por medicoId y/o rango de fechas en caso se proporcione
    @Query("SELECT a FROM Atencion a " +
            "WHERE (:medicoId IS NULL OR a.medico.id = :medicoId) " +
            "AND (:from IS NULL OR a.fecha >= :from) " +
            "AND (:to IS NULL OR a.fecha <= :to)")
    List<Atencion> searchByMedicoAndFechaRange(
            @Param("medicoId") Long medicoId,
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to
    );
}

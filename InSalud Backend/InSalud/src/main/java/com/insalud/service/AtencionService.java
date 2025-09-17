package com.insalud.service;

import com.insalud.dto.AtencionCreateDto;
import com.insalud.dto.AtencionResponseDto;
import com.insalud.dto.AtencionUpdateDto;
import com.insalud.entity.Atencion;
import com.insalud.entity.EstadoAtencion;
import com.insalud.entity.Medico;
import com.insalud.entity.Paciente;
import com.insalud.exception.ResourceNotFoundException;
import com.insalud.mapper.AtencionMapper;
import com.insalud.repository.AtencionRepository;
import com.insalud.repository.MedicoRepository;
import com.insalud.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AtencionService {
    private final AtencionRepository atencionRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    public AtencionService(AtencionRepository atencionRepository,
                           PacienteRepository pacienteRepository,
                           MedicoRepository medicoRepository) {
        this.atencionRepository = atencionRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    public List<AtencionResponseDto> listAll() {
        return atencionRepository.findAll().stream()
                .map(AtencionMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<AtencionResponseDto> listByPacienteId(Long pacienteId) {
        return atencionRepository.findByPacienteId(pacienteId).stream()
                .map(AtencionMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<AtencionResponseDto> listByPacienteEmail(String email) {
        Paciente paciente = pacienteRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con email: " + email));
        return listByPacienteId(paciente.getId());
    }

    public AtencionResponseDto create(AtencionCreateDto dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado with id: " + dto.getPacienteId()));

        Medico medico = medicoRepository.findById(dto.getMedicoId())
                .orElseThrow(() -> new ResourceNotFoundException("Medico no encontrado with id: " + dto.getMedicoId()));

        Atencion a = new Atencion();
        a.setFecha(dto.getFecha());
        a.setMotivo(dto.getMotivo());
        a.setPaciente(paciente);
        a.setMedico(medico);
        a.setEstado(EstadoAtencion.PENDIENTE);

        Atencion saved = atencionRepository.save(a);
        return AtencionMapper.toDto(saved);
    }

    public AtencionResponseDto update(Long id, AtencionUpdateDto dto) {
        Atencion existing = atencionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Atencion no encontrada con id: " + id));

        Medico medico = medicoRepository.findById(dto.getMedicoId())
                .orElseThrow(() -> new ResourceNotFoundException("Medico no encontrado with id: " + dto.getMedicoId()));

        existing.setFecha(dto.getFecha());
        existing.setMotivo(dto.getMotivo());
        existing.setMedico(medico);
        try {
            existing.setEstado(EstadoAtencion.valueOf(dto.getEstado().toUpperCase()));
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Estado inválido. Debe ser PENDIENTE, REALIZADA o CANCELADA");
        }

        Atencion saved = atencionRepository.save(existing);
        return AtencionMapper.toDto(saved);
    }

    public void delete(Long id) {
        Atencion existing = atencionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Atencion no encontrada con id: " + id));
        atencionRepository.delete(existing);
    }

    public List<AtencionResponseDto> search(Long medicoId, LocalDateTime from, LocalDateTime to) {
        List<Atencion> results = atencionRepository.searchByMedicoAndFechaRange(medicoId, from, to);
        return results.stream().map(AtencionMapper::toDto).collect(Collectors.toList());
    }
}

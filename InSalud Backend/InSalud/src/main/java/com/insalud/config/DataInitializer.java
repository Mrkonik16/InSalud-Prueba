package com.insalud.config;

import com.insalud.entity.*;
import com.insalud.repository.EspecialidadRepository;
import com.insalud.repository.MedicoRepository;
import com.insalud.repository.PacienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {
    private final PacienteRepository pacienteRepository;
    private final EspecialidadRepository especialidadRepository;
    private final MedicoRepository medicoRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(PacienteRepository pacienteRepository,
                           EspecialidadRepository especialidadRepository,
                           MedicoRepository medicoRepository,
                           PasswordEncoder passwordEncoder) {
        this.pacienteRepository = pacienteRepository;
        this.especialidadRepository = especialidadRepository;
        this.medicoRepository = medicoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // ADMIN
        String adminEmail = "adminInsalud@gmail.com";
        if (!pacienteRepository.existsByEmail(adminEmail)) {
            Paciente admin = new Paciente();
            admin.setNombre("Admin");
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEstado(Estado.ACTIVO);
            admin.setRoles(Set.of(Rol.ROLE_ADMIN));
            pacienteRepository.save(admin);
            System.out.println("Admin creado: " + adminEmail + " / admin123");
        }

        // PACIENTE
        String userEmail = "userInsalud@gmail.com";
        if (!pacienteRepository.existsByEmail(userEmail)) {
            Paciente paciente = new Paciente();
            paciente.setNombre("Paciente");
            paciente.setEmail(userEmail);
            paciente.setPassword(passwordEncoder.encode("user123"));
            paciente.setEstado(Estado.ACTIVO);
            paciente.setRoles(Set.of(Rol.ROLE_PACIENTE));
            pacienteRepository.save(paciente);
            System.out.println("Paciente creado: " + userEmail + " / user123");
        }

        // Especialidad
        String espName = "Cardiología";
        Especialidad esp = especialidadRepository.findByNombre(espName)
                .orElseGet(() -> {
                    Especialidad e = new Especialidad();
                    e.setNombre(espName);
                    e.setEstado(Estado.ACTIVO);
                    return especialidadRepository.save(e);
                });

        // Medico
        if (medicoRepository.findByNombreContainingIgnoreCase("Dr. Prueba").isEmpty()) {
            Medico m = new Medico();
            m.setNombre("Dr. Prueba");
            m.setEstado(Estado.ACTIVO);
            m.setEspecialidades(new HashSet<>() {{
                add(esp);
            }});
            medicoRepository.save(m);
            System.out.println("Medico creado: Dr. Prueba");
        }
    }
}

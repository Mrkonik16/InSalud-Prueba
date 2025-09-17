package com.insalud.service;

import com.insalud.entity.Paciente;
import com.insalud.repository.PacienteRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final PacienteRepository pacienteRepository;

    public CustomUserDetailsService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Paciente p = pacienteRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado" + email));

        var authorities = p.getRoles().stream()
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        if (authorities.isEmpty()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_PACIENTE"));
        }

        return User.builder()
                .username(p.getEmail())
                .password(p.getPassword())
                .authorities(authorities)
                .disabled(!p.getEstado().equals(com.insalud.entity.Estado.ACTIVO))
                .build();
    }
}

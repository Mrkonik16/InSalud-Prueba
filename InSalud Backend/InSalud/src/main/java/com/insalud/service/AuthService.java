package com.insalud.service;

import com.insalud.dto.AuthResponse;
import com.insalud.entity.Paciente;
import com.insalud.repository.PacienteRepository;
import com.insalud.security.JwtUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtUtil jwtUtil;
    private final PacienteRepository pacienteRepository;

    public AuthService(AuthenticationConfiguration authenticationConfiguration,
                       JwtUtil jwtUtil,
                       PacienteRepository pacienteRepository) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtil = jwtUtil;
        this.pacienteRepository = pacienteRepository;
    }

    public AuthResponse authenticateAndCreateToken(String email, String password) {
        try {
            var authenticationManager = authenticationConfiguration.getAuthenticationManager();

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            // Authentication success -> genera token
            String principal = authentication.getName();
            List<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            if (roles.isEmpty()) {
                roles.add("ROLE_PACIENTE");
            }

            String token = jwtUtil.generateToken(principal, roles);

            Paciente paciente = pacienteRepository.findByEmail(email).orElse(null);
            Long userId = paciente != null ? paciente.getId() : null;

            return new AuthResponse(token, "Bearer", userId, email);
        } catch (Exception ex) {
            // Lanzar excepción clara para controller
            throw new BadCredentialsException("Email o contraseña incorrectos", ex);
        }
    }
}

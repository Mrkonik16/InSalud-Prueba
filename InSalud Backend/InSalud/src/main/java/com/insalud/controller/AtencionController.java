package com.insalud.controller;

import com.insalud.dto.AtencionCreateDto;
import com.insalud.dto.AtencionResponseDto;
import com.insalud.dto.AtencionUpdateDto;
import com.insalud.service.AtencionService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/atenciones")
public class AtencionController {
    private final AtencionService atencionService;

    public AtencionController(AtencionService atencionService) {
        this.atencionService = atencionService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<AtencionResponseDto>> getAll() {
        return ResponseEntity.ok(atencionService.listAll());
    }

    @GetMapping("/mias")
    public ResponseEntity<List<AtencionResponseDto>> getMine() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        List<AtencionResponseDto> items = atencionService.listByPacienteEmail(email);
        return ResponseEntity.ok(items);
    }

    private List<AtencionResponseDto> atencionServiceListMineByEmail(String email) {
        throw new UnsupportedOperationException("Controller helper not implemented - update AtencionService to include listByPacienteEmail(email)");
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<AtencionResponseDto> create(@Valid @RequestBody AtencionCreateDto dto) {
        AtencionResponseDto created = atencionService.create(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<AtencionResponseDto> update(@PathVariable Long id,
                                                      @Valid @RequestBody AtencionUpdateDto dto) {
        AtencionResponseDto updated = atencionService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        atencionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<AtencionResponseDto>> search(
            @RequestParam(required = false) Long medicoId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to
    ) {
        List<AtencionResponseDto> results = atencionService.search(medicoId, from, to);
        return ResponseEntity.ok(results);
    }
}

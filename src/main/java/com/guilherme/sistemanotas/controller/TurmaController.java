package com.guilherme.sistemanotas.controller;

import com.guilherme.sistemanotas.model.Turma;
import com.guilherme.sistemanotas.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaService turmaService;

    public TurmaController(
            TurmaService turmaService) {

        this.turmaService =
                turmaService;
    }

    @PostMapping
    public Turma criar(
            @Valid
            @RequestBody Turma turma) {

        return turmaService.salvar(
                turma
        );
    }

    @GetMapping
    public List<Turma> listar() {

        return turmaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscarPorId(
            @PathVariable Integer id) {

        Optional<Turma> turma =
                turmaService.buscarPorId(
                        id
                );

        if (turma.isPresent()) {

            return ResponseEntity.ok(
                    turma.get()
            );
        }

        return ResponseEntity
                .notFound()
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizar(
            @PathVariable Integer id,
            @Valid
            @RequestBody Turma dados,
            Authentication authentication) {

        Turma atualizada =
                turmaService.atualizar(
                        id,
                        dados,
                        authentication.getName()
                );

        return ResponseEntity.ok(
                atualizada
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Integer id,
            Authentication authentication) {

        turmaService.excluir(
                id,
                authentication.getName()
        );

        return ResponseEntity
                .noContent()
                .build();
    }
}
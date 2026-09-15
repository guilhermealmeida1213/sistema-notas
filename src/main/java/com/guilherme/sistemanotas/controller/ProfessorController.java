package com.guilherme.sistemanotas.controller;

import com.guilherme.sistemanotas.dto.AtualizarProfessorDTO;
import com.guilherme.sistemanotas.model.Professor;
import com.guilherme.sistemanotas.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(
            ProfessorService professorService) {

        this.professorService =
                professorService;
    }

    @PostMapping
    public Professor criar(
            @RequestBody Professor professor,
            Authentication authentication) {

        return professorService.salvar(
                professor,
                authentication.getName()
        );
    }

    @GetMapping
    public List<Professor> listar() {

        return professorService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscarPorId(
            @PathVariable Integer id) {

        Optional<Professor> professor =
                professorService.buscarPorId(
                        id
                );

        if (professor.isPresent()) {

            return ResponseEntity.ok(
                    professor.get()
            );
        }

        return ResponseEntity
                .notFound()
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizar(
            @PathVariable Integer id,
            @Valid
            @RequestBody AtualizarProfessorDTO dados,
            Authentication authentication) {

        Professor professorAtualizado =
                professorService.atualizar(
                        id,
                        dados,
                        authentication.getName()
                );

        return ResponseEntity.ok(
                professorAtualizado
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Integer id,
            Authentication authentication) {

        professorService.excluir(
                id,
                authentication.getName()
        );

        return ResponseEntity
                .noContent()
                .build();
    }
}
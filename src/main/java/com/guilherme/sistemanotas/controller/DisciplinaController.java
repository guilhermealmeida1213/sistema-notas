package com.guilherme.sistemanotas.controller;

import com.guilherme.sistemanotas.model.Disciplina;
import com.guilherme.sistemanotas.service.DisciplinaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    public DisciplinaController(
            DisciplinaService disciplinaService) {

        this.disciplinaService =
                disciplinaService;
    }

    @PostMapping
    public Disciplina criar(
            @Valid
            @RequestBody Disciplina disciplina) {

        return disciplinaService.salvar(
                disciplina
        );
    }

    @GetMapping
    public List<Disciplina> listar() {

        return disciplinaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> buscarPorId(
            @PathVariable Integer id) {

        Optional<Disciplina> disciplina =
                disciplinaService
                        .buscarPorId(id);

        if (disciplina.isPresent()) {

            return ResponseEntity.ok(
                    disciplina.get()
            );
        }

        return ResponseEntity
                .notFound()
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> atualizar(
            @PathVariable Integer id,
            @Valid
            @RequestBody Disciplina dados,
            Authentication authentication) {

        Disciplina atualizada =
                disciplinaService.atualizar(
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

        disciplinaService.excluir(
                id,
                authentication.getName()
        );

        return ResponseEntity
                .noContent()
                .build();
    }
}
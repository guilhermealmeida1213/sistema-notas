package com.guilherme.sistemanotas.controller;

import com.guilherme.sistemanotas.dto.AtualizarProfessorTurmaDisciplinaDTO;
import com.guilherme.sistemanotas.model.TurmaDisciplina;
import com.guilherme.sistemanotas.service.TurmaDisciplinaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turmas-disciplinas")
public class TurmaDisciplinaController {

    private final TurmaDisciplinaService turmaDisciplinaService;

    public TurmaDisciplinaController(
            TurmaDisciplinaService turmaDisciplinaService) {

        this.turmaDisciplinaService =
                turmaDisciplinaService;
    }

    @GetMapping("/minhas")
    public List<TurmaDisciplina> listarMinhas(
            Authentication authentication) {

        return turmaDisciplinaService
                .listarMinhas(
                        authentication.getName()
                );
    }

    @PostMapping
    public TurmaDisciplina criar(
            @RequestBody TurmaDisciplina turmaDisciplina,
            Authentication authentication) {

        return turmaDisciplinaService
                .salvar(
                        turmaDisciplina,
                        authentication.getName()
                );
    }

    @GetMapping
    public List<TurmaDisciplina> listar() {

        return turmaDisciplinaService
                .listar();
    }

    @PutMapping("/{id}/professor")
    public TurmaDisciplina atualizarProfessor(
            @PathVariable Integer id,
            @RequestBody AtualizarProfessorTurmaDisciplinaDTO dados,
            Authentication authentication) {

        return turmaDisciplinaService
                .atualizarProfessor(
                        id,
                        dados,
                        authentication.getName()
                );
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<TurmaDisciplina> buscarPorId(
            @PathVariable Integer id) {

        Optional<TurmaDisciplina> turmaDisciplina =
                turmaDisciplinaService
                        .buscarPorId(id);

        if (turmaDisciplina.isPresent()) {

            return ResponseEntity.ok(
                    turmaDisciplina.get()
            );
        }

        return ResponseEntity
                .notFound()
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Integer id,
            Authentication authentication) {

        turmaDisciplinaService.excluir(
                id,
                authentication.getName()
        );

        return ResponseEntity
                .noContent()
                .build();
    }
}
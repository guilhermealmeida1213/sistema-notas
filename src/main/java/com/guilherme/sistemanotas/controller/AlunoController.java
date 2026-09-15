package com.guilherme.sistemanotas.controller;

import com.guilherme.sistemanotas.model.Aluno;
import com.guilherme.sistemanotas.service.AlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public Aluno criar(@RequestBody Aluno aluno) {
        return alunoService.salvar(aluno);
    }

    @GetMapping
    public List<Aluno> listar() {
        return alunoService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarPorId(@PathVariable Integer id) {

        Optional<Aluno> aluno =
                alunoService.buscarPorId(id);

        if (aluno.isPresent()) {
            return ResponseEntity.ok(
                    aluno.get()
            );
        }

        return ResponseEntity
                .notFound()
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Integer id) {

        alunoService.excluir(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
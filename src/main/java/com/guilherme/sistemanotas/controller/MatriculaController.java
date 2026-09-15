package com.guilherme.sistemanotas.controller;

import com.guilherme.sistemanotas.dto.AtualizarMatriculaDTO;
import com.guilherme.sistemanotas.dto.MatriculaAlunoDTO;
import com.guilherme.sistemanotas.model.Matricula;
import com.guilherme.sistemanotas.service.MatriculaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    private final MatriculaService matriculaService;

    public MatriculaController(
            MatriculaService matriculaService) {

        this.matriculaService =
                matriculaService;
    }

    @PostMapping
    public Matricula criar(
            @RequestBody Matricula matricula,
            Authentication authentication) {

        return matriculaService
                .salvar(
                        matricula,
                        authentication.getName()
                );
    }

    @GetMapping
    public List<Matricula> listar() {

        return matriculaService
                .listar();
    }

    @GetMapping("/minhas")
    public List<MatriculaAlunoDTO> listarMinhasMatriculas(
            Authentication authentication) {

        String email =
                authentication.getName();

        return matriculaService
                .listarMinhasMatriculas(
                        email
                );
    }

    @GetMapping("/turma/{idTurma}")
    public List<Matricula> listarPorTurma(
            @PathVariable Integer idTurma,
            Authentication authentication) {

        String emailProfessor =
                authentication.getName();

        return matriculaService
                .listarPorTurmaProfessor(
                        idTurma,
                        emailProfessor
                );
    }

    @PutMapping("/{id}")
    public Matricula atualizar(
            @PathVariable Integer id,
            @RequestBody AtualizarMatriculaDTO dados,
            Authentication authentication) {

        return matriculaService
                .atualizar(
                        id,
                        dados,
                        authentication.getName()
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Integer id,
            Authentication authentication) {

        matriculaService.excluir(
                id,
                authentication.getName()
        );

        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matricula> buscarPorId(
            @PathVariable Integer id) {

        Optional<Matricula> matricula =
                matriculaService
                        .buscarPorId(
                                id
                        );

        if (matricula.isPresent()) {

            return ResponseEntity.ok(
                    matricula.get()
            );
        }

        return ResponseEntity
                .notFound()
                .build();
    }
}
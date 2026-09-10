package com.guilherme.sistemanotas.controller;

import com.guilherme.sistemanotas.dto.AtualizarFaltaDTO;
import com.guilherme.sistemanotas.dto.FaltaAdminDTO;
import com.guilherme.sistemanotas.dto.FaltaAlunoDTO;
import com.guilherme.sistemanotas.dto.FaltaProfessorDTO;
import com.guilherme.sistemanotas.model.Falta;
import com.guilherme.sistemanotas.service.FaltaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faltas")
public class FaltaController {

    private final FaltaService faltaService;

    public FaltaController(FaltaService faltaService) {
        this.faltaService = faltaService;
    }

    // =========================
    // CADASTRAR FALTA
    // =========================

    @PostMapping
    public Falta criar(
            @RequestBody Falta falta,
            Authentication authentication) {

        String emailUsuario =
                authentication.getName();

        return faltaService.salvar(
                falta,
                emailUsuario
        );
    }

    // =========================
    // ATUALIZAR FALTA
    // PROFESSOR / ADMIN
    // =========================

    @PutMapping("/{id}")
    public Falta atualizar(
            @PathVariable Integer id,
            @RequestBody AtualizarFaltaDTO dados,
            Authentication authentication) {

        String emailUsuario =
                authentication.getName();

        return faltaService.atualizar(
                id,
                dados,
                emailUsuario
        );
    }

    // =========================
    // LISTAR TODAS AS FALTAS
    // ADMIN
    // =========================

    @GetMapping
    public List<FaltaAdminDTO> listar() {

        return faltaService.listar();
    }

    // =========================
    // LISTAR MINHAS FALTAS
    // ALUNO
    // =========================

    @GetMapping("/minhas")
    public List<FaltaAlunoDTO> listarMinhasFaltas(
            Authentication authentication) {

        String email =
                authentication.getName();

        return faltaService.listarMinhasFaltas(email);
    }

    // =========================
    // LISTAR FALTAS DAS MINHAS TURMAS
    // PROFESSOR
    // =========================

    @GetMapping("/minhas-turmas")
    public List<FaltaProfessorDTO> listarFaltasDasMinhasTurmas(
            Authentication authentication) {

        String email =
                authentication.getName();

        return faltaService
                .listarFaltasDasMinhasTurmas(email);
    }

    // =========================
    // BUSCAR FALTA POR ID
    // ADMIN
    // =========================

    @GetMapping("/id/{id}")
    public ResponseEntity<Falta> buscarPorId(
            @PathVariable Integer id) {

        Optional<Falta> falta =
                faltaService.buscarPorId(id);

        if (falta.isPresent()) {

            return ResponseEntity.ok(
                    falta.get()
            );
        }

        return ResponseEntity
                .notFound()
                .build();
    }
}
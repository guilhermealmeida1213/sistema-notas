package com.guilherme.sistemanotas.controller;

import com.guilherme.sistemanotas.dto.AtualizarAviDTO;
import com.guilherme.sistemanotas.dto.AtualizarNotaDTO;
import com.guilherme.sistemanotas.dto.LancamentoAviDTO;
import com.guilherme.sistemanotas.dto.MediaDisciplinaDTO;
import com.guilherme.sistemanotas.dto.NotaAdminDTO;
import com.guilherme.sistemanotas.dto.NotaAlunoDTO;
import com.guilherme.sistemanotas.dto.NotaProfessorDTO;
import com.guilherme.sistemanotas.dto.ResumoDisciplinaDTO;
import com.guilherme.sistemanotas.model.Nota;
import com.guilherme.sistemanotas.service.NotaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notas")
public class NotaController {

    private final NotaService notaService;

    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    @PostMapping
    public Nota criar(
            @RequestBody Nota nota,
            Authentication authentication) {

        String emailUsuario =
                authentication.getName();

        return notaService.salvar(
                nota,
                emailUsuario
        );
    }

    @PostMapping("/avi")
    public List<Nota> lancarAvi(
            @RequestBody LancamentoAviDTO dados,
            Authentication authentication) {

        String emailUsuario =
                authentication.getName();

        return notaService.lancarAvi(
                dados,
                emailUsuario
        );
    }

    @PutMapping("/avi/{idMatricula}")
    public List<Nota> atualizarAviIntegrada(
            @PathVariable Integer idMatricula,
            @RequestBody AtualizarAviDTO dados,
            Authentication authentication) {

        String emailUsuario =
                authentication.getName();

        return notaService.atualizarAviIntegrada(
                idMatricula,
                dados,
                emailUsuario
        );
    }

    @PutMapping("/{id}")
    public Nota atualizar(
            @PathVariable Integer id,
            @RequestBody AtualizarNotaDTO dados,
            Authentication authentication) {

        String emailUsuario =
                authentication.getName();

        return notaService.atualizar(
                id,
                dados,
                emailUsuario
        );
    }

    @GetMapping
    public List<NotaAdminDTO> listar() {

        return notaService.listar();
    }

    @GetMapping("/minhas")
    public List<NotaAlunoDTO> listarMinhasNotas(
            Authentication authentication) {

        String email =
                authentication.getName();

        return notaService.listarMinhasNotas(email);
    }

    @GetMapping("/minhas-medias")
    public List<MediaDisciplinaDTO> listarMinhasMedias(
            Authentication authentication) {

        String email =
                authentication.getName();

        return notaService.calcularMinhasMedias(email);
    }

    @GetMapping("/meu-resumo")
    public List<ResumoDisciplinaDTO> meuResumo(
            Authentication authentication) {

        String email =
                authentication.getName();

        return notaService.gerarMeuResumo(email);
    }

    @GetMapping("/minhas-turmas")
    public List<NotaProfessorDTO> listarNotasDasMinhasTurmas(
            Authentication authentication) {

        String email =
                authentication.getName();

        return notaService
                .listarNotasDasMinhasTurmas(email);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Nota> buscarPorId(
            @PathVariable Integer id) {

        Optional<Nota> nota =
                notaService.buscarPorId(id);

        if (nota.isPresent()) {

            return ResponseEntity.ok(
                    nota.get()
            );
        }

        return ResponseEntity
                .notFound()
                .build();
    }
}
package com.guilherme.sistemanotas.service;

import com.guilherme.sistemanotas.model.Turma;
import com.guilherme.sistemanotas.repository.MatriculaRepository;
import com.guilherme.sistemanotas.repository.TurmaDisciplinaRepository;
import com.guilherme.sistemanotas.repository.TurmaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final MatriculaRepository matriculaRepository;
    private final TurmaDisciplinaRepository turmaDisciplinaRepository;
    private final AuditoriaService auditoriaService;

    public TurmaService(
            TurmaRepository turmaRepository,
            MatriculaRepository matriculaRepository,
            TurmaDisciplinaRepository turmaDisciplinaRepository,
            AuditoriaService auditoriaService) {

        this.turmaRepository =
                turmaRepository;

        this.matriculaRepository =
                matriculaRepository;

        this.turmaDisciplinaRepository =
                turmaDisciplinaRepository;

        this.auditoriaService =
                auditoriaService;
    }

    // =========================
    // CADASTRAR TURMA
    // =========================

    public Turma salvar(
            Turma turma) {

        return turmaRepository.save(
                turma
        );
    }

    // =========================
    // LISTAR TURMAS
    // =========================

    public List<Turma> listar() {

        return turmaRepository
                .findAllByOrderByNomeAsc();
    }

    // =========================
    // BUSCAR POR ID
    // =========================

    public Optional<Turma> buscarPorId(
            Integer id) {

        return turmaRepository.findById(
                id
        );
    }

    // =========================
    // ATUALIZAR TURMA
    // =========================

    @Transactional
    public Turma atualizar(
            Integer id,
            Turma dados,
            String emailAdministrador) {

        Turma turma =
                turmaRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Turma não encontrada"
                                )
                        );

        String nomeAnterior =
                turma.getNome();

        Integer semestreAnterior =
                turma.getSemestre();

        Integer anoAnterior =
                turma.getAno();

        turma.setNome(
                dados.getNome().trim()
        );

        turma.setSemestre(
                dados.getSemestre()
        );

        turma.setAno(
                dados.getAno()
        );

        Turma atualizada =
                turmaRepository.save(
                        turma
                );

        auditoriaService.registrar(
                emailAdministrador,
                "ALTERACAO_TURMA",
                "Turma alterada de "
                        + nomeAnterior
                        + " - "
                        + anoAnterior
                        + "/"
                        + semestreAnterior
                        + " para "
                        + atualizada.getNome()
                        + " - "
                        + atualizada.getAno()
                        + "/"
                        + atualizada.getSemestre()
        );

        return atualizada;
    }

    // =========================
    // EXCLUIR TURMA
    // =========================

    @Transactional
    public void excluir(
            Integer id,
            String emailAdministrador) {

        Turma turma =
                turmaRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Turma não encontrada"
                                )
                        );

        boolean possuiMatriculas =
                matriculaRepository
                        .existsByTurma_IdTurma(
                                id
                        );

        if (possuiMatriculas) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "A turma possui alunos matriculados e não pode ser excluída"
            );
        }

        boolean possuiDisciplinas =
                turmaDisciplinaRepository
                        .existsByTurma_IdTurma(
                                id
                        );

        if (possuiDisciplinas) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "A turma possui disciplinas vinculadas e não pode ser excluída"
            );
        }

        String descricao =
                turma.getNome()
                        + " - "
                        + turma.getAno()
                        + "/"
                        + turma.getSemestre();

        turmaRepository.delete(
                turma
        );

        auditoriaService.registrar(
                emailAdministrador,
                "EXCLUSAO_TURMA",
                "Turma "
                        + descricao
                        + " foi excluída"
        );
    }
}
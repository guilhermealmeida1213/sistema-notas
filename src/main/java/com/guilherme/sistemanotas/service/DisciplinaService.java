package com.guilherme.sistemanotas.service;

import com.guilherme.sistemanotas.model.Disciplina;
import com.guilherme.sistemanotas.repository.DisciplinaRepository;
import com.guilherme.sistemanotas.repository.TurmaDisciplinaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;
    private final TurmaDisciplinaRepository turmaDisciplinaRepository;
    private final AuditoriaService auditoriaService;

    public DisciplinaService(
            DisciplinaRepository disciplinaRepository,
            TurmaDisciplinaRepository turmaDisciplinaRepository,
            AuditoriaService auditoriaService) {

        this.disciplinaRepository =
                disciplinaRepository;

        this.turmaDisciplinaRepository =
                turmaDisciplinaRepository;

        this.auditoriaService =
                auditoriaService;
    }

    // =========================
    // CADASTRAR DISCIPLINA
    // =========================

    public Disciplina salvar(
            Disciplina disciplina) {

        boolean nomeJaExiste =
                disciplinaRepository
                        .findAll()
                        .stream()
                        .anyMatch(d ->
                                d.getNome()
                                        .equalsIgnoreCase(
                                                disciplina.getNome()
                                        )
                        );

        if (nomeJaExiste) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Já existe uma disciplina com esse nome"
            );
        }

        return disciplinaRepository.save(
                disciplina
        );
    }

    // =========================
    // LISTAR DISCIPLINAS
    // =========================

    public List<Disciplina> listar() {

        return disciplinaRepository
                .findAllByOrderByNomeAsc();
    }

    // =========================
    // BUSCAR POR ID
    // =========================

    public Optional<Disciplina> buscarPorId(
            Integer id) {

        return disciplinaRepository
                .findById(id);
    }

    // =========================
    // ATUALIZAR DISCIPLINA
    // =========================

    @Transactional
    public Disciplina atualizar(
            Integer id,
            Disciplina dados,
            String emailAdministrador) {

        Disciplina disciplina =
                disciplinaRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Disciplina não encontrada"
                                )
                        );

        boolean nomeJaExiste =
                disciplinaRepository
                        .findAll()
                        .stream()
                        .anyMatch(d ->
                                !d.getIdDisciplina().equals(id)
                                        &&
                                        d.getNome()
                                                .equalsIgnoreCase(
                                                        dados.getNome()
                                                )
                        );

        if (nomeJaExiste) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Já existe uma disciplina com esse nome"
            );
        }

        String nomeAnterior =
                disciplina.getNome();

        Integer cargaAnterior =
                disciplina.getCargaHoraria();

        disciplina.setNome(
                dados.getNome().trim()
        );

        disciplina.setCargaHoraria(
                dados.getCargaHoraria()
        );

        Disciplina atualizada =
                disciplinaRepository.save(
                        disciplina
                );

        auditoriaService.registrar(
                emailAdministrador,
                "ALTERACAO_DISCIPLINA",
                "Disciplina alterada de "
                        + nomeAnterior
                        + " ("
                        + cargaAnterior
                        + "h) para "
                        + atualizada.getNome()
                        + " ("
                        + atualizada.getCargaHoraria()
                        + "h)"
        );

        return atualizada;
    }

    // =========================
    // EXCLUIR DISCIPLINA
    // =========================

    @Transactional
    public void excluir(
            Integer id,
            String emailAdministrador) {

        Disciplina disciplina =
                disciplinaRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Disciplina não encontrada"
                                )
                        );

        boolean possuiVinculo =
                turmaDisciplinaRepository
                        .existsByDisciplina_IdDisciplina(
                                id
                        );

        if (possuiVinculo) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "A disciplina está vinculada a uma turma e não pode ser excluída"
            );
        }

        String nome =
                disciplina.getNome();

        Integer carga =
                disciplina.getCargaHoraria();

        disciplinaRepository.delete(
                disciplina
        );

        auditoriaService.registrar(
                emailAdministrador,
                "EXCLUSAO_DISCIPLINA",
                "Disciplina "
                        + nome
                        + " ("
                        + carga
                        + "h) foi excluída"
        );
    }
}
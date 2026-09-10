package com.guilherme.sistemanotas.service;

import com.guilherme.sistemanotas.dto.AtualizarProfessorTurmaDisciplinaDTO;
import com.guilherme.sistemanotas.model.Disciplina;
import com.guilherme.sistemanotas.model.Professor;
import com.guilherme.sistemanotas.model.Turma;
import com.guilherme.sistemanotas.model.TurmaDisciplina;
import com.guilherme.sistemanotas.repository.DisciplinaRepository;
import com.guilherme.sistemanotas.repository.ProfessorRepository;
import com.guilherme.sistemanotas.repository.TurmaDisciplinaRepository;
import com.guilherme.sistemanotas.repository.TurmaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaDisciplinaService {

    private final TurmaDisciplinaRepository turmaDisciplinaRepository;
    private final TurmaRepository turmaRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final ProfessorRepository professorRepository;
    private final AuditoriaService auditoriaService;

    public TurmaDisciplinaService(
            TurmaDisciplinaRepository turmaDisciplinaRepository,
            TurmaRepository turmaRepository,
            DisciplinaRepository disciplinaRepository,
            ProfessorRepository professorRepository,
            AuditoriaService auditoriaService) {

        this.turmaDisciplinaRepository =
                turmaDisciplinaRepository;

        this.turmaRepository =
                turmaRepository;

        this.disciplinaRepository =
                disciplinaRepository;

        this.professorRepository =
                professorRepository;

        this.auditoriaService =
                auditoriaService;
    }

    // =========================
    // CRIAR VÍNCULO
    // ADMIN
    // =========================

    @Transactional
    public TurmaDisciplina salvar(
            TurmaDisciplina turmaDisciplina,
            String emailAdministrador) {

        // =========================
        // VALIDAR TURMA
        // =========================

        if (turmaDisciplina.getTurma() == null ||
                turmaDisciplina
                        .getTurma()
                        .getIdTurma() == null) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Turma é obrigatória"
            );
        }

        // =========================
        // VALIDAR DISCIPLINA
        // =========================

        if (turmaDisciplina.getDisciplina() == null ||
                turmaDisciplina
                        .getDisciplina()
                        .getIdDisciplina() == null) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Disciplina é obrigatória"
            );
        }

        // =========================
        // VALIDAR PROFESSOR
        // =========================

        if (turmaDisciplina.getProfessor() == null ||
                turmaDisciplina
                        .getProfessor()
                        .getIdProfessor() == null) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Professor é obrigatório"
            );
        }

        Integer idTurma =
                turmaDisciplina
                        .getTurma()
                        .getIdTurma();

        Integer idDisciplina =
                turmaDisciplina
                        .getDisciplina()
                        .getIdDisciplina();

        Integer idProfessor =
                turmaDisciplina
                        .getProfessor()
                        .getIdProfessor();

        // =========================
        // BUSCAR TURMA
        // =========================

        Turma turma =
                turmaRepository
                        .findById(idTurma)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Turma não encontrada"
                                )
                        );

        // =========================
        // BUSCAR DISCIPLINA
        // =========================

        Disciplina disciplina =
                disciplinaRepository
                        .findById(idDisciplina)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Disciplina não encontrada"
                                )
                        );

        // =========================
        // BUSCAR PROFESSOR
        // =========================

        Professor professor =
                professorRepository
                        .findById(idProfessor)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Professor não encontrado"
                                )
                        );

        // =========================
        // IMPEDIR VÍNCULO DUPLICADO
        // =========================

        boolean vinculoJaExiste =
                turmaDisciplinaRepository
                        .existsByTurma_IdTurmaAndDisciplina_IdDisciplina(
                                idTurma,
                                idDisciplina
                        );

        if (vinculoJaExiste) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Essa disciplina já está vinculada à turma"
            );
        }

        turmaDisciplina.setTurma(
                turma
        );

        turmaDisciplina.setDisciplina(
                disciplina
        );

        turmaDisciplina.setProfessor(
                professor
        );

        TurmaDisciplina vinculoSalvo =
                turmaDisciplinaRepository
                        .save(
                                turmaDisciplina
                        );

        // =========================
        // AUDITORIA
        // =========================

        auditoriaService.registrar(
                emailAdministrador,
                "CRIACAO_VINCULO_ACADEMICO",
                "Disciplina "
                        + disciplina.getNome()
                        + " vinculada à turma "
                        + turma.getNome()
                        + " com o professor "
                        + professor.getUsuario().getNome()
                        + " ("
                        + professor.getRegistro()
                        + ")"
        );

        return vinculoSalvo;
    }

    // =========================
    // LISTAR TODOS
    // ADMIN
    // =========================

    public List<TurmaDisciplina> listar() {

        return turmaDisciplinaRepository
                .findAll();
    }

    // =========================
    // LISTAR MEUS VÍNCULOS
    // PROFESSOR
    // =========================

    public List<TurmaDisciplina> listarMinhas(
            String email) {

        Professor professor =
                professorRepository
                        .findByUsuario_Email(email)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Professor não encontrado"
                                )
                        );

        return turmaDisciplinaRepository
                .findByProfessor_IdProfessor(
                        professor.getIdProfessor()
                );
    }

    // =========================
    // BUSCAR POR ID
    // =========================

    public Optional<TurmaDisciplina> buscarPorId(
            Integer id) {

        return turmaDisciplinaRepository
                .findById(id);
    }

    // =========================
    // TROCAR PROFESSOR
    // ADMIN
    // =========================

    @Transactional
    public TurmaDisciplina atualizarProfessor(
            Integer idTurmaDisciplina,
            AtualizarProfessorTurmaDisciplinaDTO dados,
            String emailAdministrador) {

        if (dados == null ||
                dados.getIdProfessor() == null) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Professor é obrigatório"
            );
        }

        TurmaDisciplina vinculo =
                turmaDisciplinaRepository
                        .findById(idTurmaDisciplina)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Turma/Disciplina não encontrada"
                                )
                        );

        Professor novoProfessor =
                professorRepository
                        .findById(
                                dados.getIdProfessor()
                        )
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Professor não encontrado"
                                )
                        );

        Professor professorAnterior =
                vinculo.getProfessor();

        // =========================
        // EVITAR ALTERAÇÃO DESNECESSÁRIA
        // =========================

        if (professorAnterior != null &&
                professorAnterior
                        .getIdProfessor()
                        .equals(
                                novoProfessor.getIdProfessor()
                        )) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "O professor informado já é responsável por esta disciplina"
            );
        }

        String nomeProfessorAnterior =
                professorAnterior != null
                        ? professorAnterior
                        .getUsuario()
                        .getNome()
                        : "Sem professor";

        String registroProfessorAnterior =
                professorAnterior != null
                        ? professorAnterior
                        .getRegistro()
                        : "--";

        vinculo.setProfessor(
                novoProfessor
        );

        TurmaDisciplina vinculoAtualizado =
                turmaDisciplinaRepository
                        .save(
                                vinculo
                        );

        // =========================
        // AUDITORIA
        // =========================

        auditoriaService.registrar(
                emailAdministrador,
                "ALTERACAO_PROFESSOR_DISCIPLINA",
                "Professor responsável pela disciplina "
                        + vinculo.getDisciplina().getNome()
                        + " da turma "
                        + vinculo.getTurma().getNome()
                        + " alterado de "
                        + nomeProfessorAnterior
                        + " ("
                        + registroProfessorAnterior
                        + ") para "
                        + novoProfessor.getUsuario().getNome()
                        + " ("
                        + novoProfessor.getRegistro()
                        + ")"
        );

        return vinculoAtualizado;
    }

    // =========================
// EXCLUIR VÍNCULO
// ADMIN
// =========================

    @Transactional
    public void excluir(
            Integer idTurmaDisciplina,
            String emailAdministrador) {

        TurmaDisciplina vinculo =
                turmaDisciplinaRepository
                        .findById(idTurmaDisciplina)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Vínculo acadêmico não encontrado"
                                )
                        );

        String nomeTurma =
                vinculo.getTurma().getNome();

        String nomeDisciplina =
                vinculo.getDisciplina().getNome();

        String nomeProfessor =
                vinculo.getProfessor() != null
                        ? vinculo.getProfessor()
                        .getUsuario()
                        .getNome()
                        : "Sem professor";

        String registroProfessor =
                vinculo.getProfessor() != null
                        ? vinculo.getProfessor()
                        .getRegistro()
                        : "--";

        turmaDisciplinaRepository.delete(vinculo);

        // =========================
        // AUDITORIA
        // =========================

        auditoriaService.registrar(
                emailAdministrador,
                "EXCLUSAO_VINCULO_ACADEMICO",
                "Vínculo da disciplina "
                        + nomeDisciplina
                        + " com a turma "
                        + nomeTurma
                        + " e professor "
                        + nomeProfessor
                        + " ("
                        + registroProfessor
                        + ") excluído"
        );
    }
}
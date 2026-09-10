package com.guilherme.sistemanotas.service;

import com.guilherme.sistemanotas.model.Aluno;
import com.guilherme.sistemanotas.model.Matricula;
import com.guilherme.sistemanotas.model.Nota;
import com.guilherme.sistemanotas.model.Turma;
import com.guilherme.sistemanotas.repository.AlunoRepository;
import com.guilherme.sistemanotas.repository.FaltaRepository;
import com.guilherme.sistemanotas.repository.MatriculaRepository;
import com.guilherme.sistemanotas.repository.NotaRepository;
import com.guilherme.sistemanotas.repository.ProfessorRepository;
import com.guilherme.sistemanotas.repository.TurmaDisciplinaRepository;
import com.guilherme.sistemanotas.repository.TurmaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MatriculaServiceTest {

    private MatriculaService matriculaService;

    private MatriculaRepository matriculaRepository;
    private AlunoRepository alunoRepository;
    private TurmaRepository turmaRepository;
    private ProfessorRepository professorRepository;
    private TurmaDisciplinaRepository turmaDisciplinaRepository;
    private NotaRepository notaRepository;
    private FaltaRepository faltaRepository;
    private AuditoriaService auditoriaService;

    @BeforeEach
    void setUp() {

        matriculaRepository =
                mock(MatriculaRepository.class);

        alunoRepository =
                mock(AlunoRepository.class);

        turmaRepository =
                mock(TurmaRepository.class);

        professorRepository =
                mock(ProfessorRepository.class);

        turmaDisciplinaRepository =
                mock(TurmaDisciplinaRepository.class);

        notaRepository =
                mock(NotaRepository.class);

        faltaRepository =
                mock(FaltaRepository.class);

        auditoriaService =
                mock(AuditoriaService.class);

        matriculaService =
                new MatriculaService(
                        matriculaRepository,
                        alunoRepository,
                        turmaRepository,
                        professorRepository,
                        turmaDisciplinaRepository,
                        notaRepository,
                        faltaRepository,
                        auditoriaService
                );
    }

    // =========================
    // TESTE 1
    // NÃO PERMITE MATRÍCULA
    // DUPLICADA
    // =========================

    @Test
    void deveImpedirMatriculaDuplicada() {

        Aluno aluno =
                new Aluno();

        aluno.setIdAluno(1);

        Turma turma =
                new Turma();

        turma.setIdTurma(1);

        Matricula matricula =
                new Matricula();

        matricula.setAluno(aluno);
        matricula.setTurma(turma);
        matricula.setDataMatricula(
                LocalDate.of(2026, 9, 8)
        );
        matricula.setStatus("ATIVA");

        matricula.setSemestreAluno(3);

        when(
                alunoRepository.findById(1)
        ).thenReturn(
                Optional.of(aluno)
        );

        when(
                turmaRepository.findById(1)
        ).thenReturn(
                Optional.of(turma)
        );

        when(
                matriculaRepository
                        .existsByAluno_IdAlunoAndTurma_IdTurma(
                                1,
                                1
                        )
        ).thenReturn(true);

        ResponseStatusException exception =
                assertThrows(
                        ResponseStatusException.class,
                        () ->
                                matriculaService.salvar(
                                        matricula,
                                        "admin@teste.com"
                                )
                );

        assertEquals(
                HttpStatus.CONFLICT,
                exception.getStatusCode()
        );

        assertEquals(
                "Aluno já está matriculado nesta turma",
                exception.getReason()
        );

        verify(
                matriculaRepository,
                never()
        ).save(
                matricula
        );
    }

    // =========================
    // TESTE 2
    // NÃO PERMITE EXCLUIR
    // MATRÍCULA COM NOTAS
    // =========================

    @Test
    void deveImpedirExclusaoDeMatriculaComNotas() {

        Matricula matricula =
                new Matricula();

        matricula.setIdMatricula(1);

        Nota nota =
                new Nota();

        when(
                matriculaRepository.findById(1)
        ).thenReturn(
                Optional.of(matricula)
        );

        when(
                notaRepository
                        .findByMatricula_IdMatricula(1)
        ).thenReturn(
                List.of(nota)
        );

        ResponseStatusException exception =
                assertThrows(
                        ResponseStatusException.class,
                        () ->
                                matriculaService.excluir(
                                        1,
                                        "admin@teste.com"
                                )
                );

        assertEquals(
                HttpStatus.CONFLICT,
                exception.getStatusCode()
        );

        assertEquals(
                "Não é possível excluir a matrícula porque ela possui notas vinculadas",
                exception.getReason()
        );

        verify(
                matriculaRepository,
                never()
        ).delete(
                matricula
        );
    }
}
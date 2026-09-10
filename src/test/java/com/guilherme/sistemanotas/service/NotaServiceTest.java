package com.guilherme.sistemanotas.service;

import com.guilherme.sistemanotas.model.Matricula;
import com.guilherme.sistemanotas.model.Nota;
import com.guilherme.sistemanotas.model.Professor;
import com.guilherme.sistemanotas.model.TipoAvaliacao;
import com.guilherme.sistemanotas.model.Turma;
import com.guilherme.sistemanotas.model.TurmaDisciplina;
import com.guilherme.sistemanotas.repository.AlunoRepository;
import com.guilherme.sistemanotas.repository.FaltaRepository;
import com.guilherme.sistemanotas.repository.MatriculaRepository;
import com.guilherme.sistemanotas.repository.NotaRepository;
import com.guilherme.sistemanotas.repository.ProfessorRepository;
import com.guilherme.sistemanotas.repository.TurmaDisciplinaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NotaServiceTest {

    private NotaService notaService;

    private NotaRepository notaRepository;
    private MatriculaRepository matriculaRepository;
    private TurmaDisciplinaRepository turmaDisciplinaRepository;
    private AlunoRepository alunoRepository;
    private ProfessorRepository professorRepository;
    private FaltaRepository faltaRepository;
    private AuditoriaService auditoriaService;

    @BeforeEach
    void setUp() {

        notaRepository =
                mock(NotaRepository.class);

        matriculaRepository =
                mock(MatriculaRepository.class);

        turmaDisciplinaRepository =
                mock(TurmaDisciplinaRepository.class);

        alunoRepository =
                mock(AlunoRepository.class);

        professorRepository =
                mock(ProfessorRepository.class);

        faltaRepository =
                mock(FaltaRepository.class);

        auditoriaService =
                mock(AuditoriaService.class);

        notaService =
                new NotaService(
                        notaRepository,
                        matriculaRepository,
                        turmaDisciplinaRepository,
                        alunoRepository,
                        professorRepository,
                        faltaRepository,
                        auditoriaService
                );
    }

    // =========================
    // TESTE 1
    // REGRA NORMAL:
    // DUAS MAIORES NOTAS
    // =========================

    @Test
    void deveCalcularMediaComAsDuasMaioresNotas() {

        BigDecimal avc =
                new BigDecimal("7.00");

        BigDecimal avg =
                new BigDecimal("8.50");

        BigDecimal avi =
                new BigDecimal("8.00");

        BigDecimal media =
                notaService.calcularMediaUnisa(
                        avc,
                        avg,
                        avi
                );

        assertEquals(
                new BigDecimal("8.25"),
                media
        );
    }

    // =========================
    // TESTE 2
    // REGRA COM ZERO:
    // MÉDIA DAS TRÊS NOTAS
    // =========================

    @Test
    void deveCalcularMediaDasTresNotasQuandoExistirNotaZero() {

        BigDecimal avc =
                new BigDecimal("0.00");

        BigDecimal avg =
                new BigDecimal("8.00");

        BigDecimal avi =
                new BigDecimal("8.00");

        BigDecimal media =
                notaService.calcularMediaUnisa(
                        avc,
                        avg,
                        avi
                );

        assertEquals(
                new BigDecimal("5.33"),
                media
        );
    }

    // =========================
    // TESTE 3
    // SEGURANÇA:
    // PROFESSOR NÃO PODE LANÇAR
    // NOTA EM DISCIPLINA DE OUTRO
    // PROFESSOR
    // =========================

    @Test
    void deveImpedirProfessorDeLancarNotaEmDisciplinaDeOutroProfessor() {

        String emailProfessorAutenticado =
                "professor1@teste.com";

        // =========================
        // TURMA
        // =========================

        Turma turma =
                new Turma();

        turma.setIdTurma(1);

        // =========================
        // MATRÍCULA
        // =========================

        Matricula matricula =
                new Matricula();

        matricula.setIdMatricula(1);
        matricula.setTurma(turma);

        // =========================
        // PROFESSOR AUTENTICADO
        // =========================

        Professor professorAutenticado =
                new Professor();

        professorAutenticado.setIdProfessor(1);

        // =========================
        // PROFESSOR RESPONSÁVEL
        // PELA DISCIPLINA
        // =========================

        Professor professorResponsavel =
                new Professor();

        professorResponsavel.setIdProfessor(2);

        // =========================
        // TURMA + DISCIPLINA
        // =========================

        TurmaDisciplina turmaDisciplina =
                new TurmaDisciplina();

        turmaDisciplina.setIdTurmaDisciplina(1);
        turmaDisciplina.setTurma(turma);
        turmaDisciplina.setProfessor(
                professorResponsavel
        );

        // =========================
        // NOTA QUE O PROFESSOR
        // TENTA LANÇAR
        // =========================

        Nota nota =
                new Nota();

        nota.setMatricula(
                matricula
        );

        nota.setTurmaDisciplina(
                turmaDisciplina
        );

        nota.setTipoAvaliacao(
                TipoAvaliacao.AVC
        );

        nota.setValor(
                new BigDecimal("8.00")
        );

        // =========================
        // COMPORTAMENTO DOS
        // REPOSITÓRIOS MOCKADOS
        // =========================

        when(
                matriculaRepository.findById(1)
        ).thenReturn(
                Optional.of(matricula)
        );

        when(
                turmaDisciplinaRepository.findById(1)
        ).thenReturn(
                Optional.of(turmaDisciplina)
        );

        when(
                professorRepository.findByUsuario_Email(
                        emailProfessorAutenticado
                )
        ).thenReturn(
                Optional.of(professorAutenticado)
        );

        // =========================
        // EXECUTA E ESPERA 403
        // =========================

        ResponseStatusException exception =
                assertThrows(
                        ResponseStatusException.class,
                        () ->
                                notaService.salvar(
                                        nota,
                                        emailProfessorAutenticado
                                )
                );

        assertEquals(
                HttpStatus.FORBIDDEN,
                exception.getStatusCode()
        );

        assertEquals(
                "Professor não possui permissão para lançar ou alterar nota nesta disciplina",
                exception.getReason()
        );
    }
}
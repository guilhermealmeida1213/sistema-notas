package com.guilherme.sistemanotas.service;

import com.guilherme.sistemanotas.dto.AtualizarMatriculaDTO;
import com.guilherme.sistemanotas.dto.MatriculaAlunoDTO;
import com.guilherme.sistemanotas.model.Aluno;
import com.guilherme.sistemanotas.model.Matricula;
import com.guilherme.sistemanotas.model.Professor;
import com.guilherme.sistemanotas.model.Turma;
import com.guilherme.sistemanotas.repository.AlunoRepository;
import com.guilherme.sistemanotas.repository.FaltaRepository;
import com.guilherme.sistemanotas.repository.MatriculaRepository;
import com.guilherme.sistemanotas.repository.NotaRepository;
import com.guilherme.sistemanotas.repository.ProfessorRepository;
import com.guilherme.sistemanotas.repository.TurmaDisciplinaRepository;
import com.guilherme.sistemanotas.repository.TurmaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

@Service
public class MatriculaService {

    private static final String STATUS_ATIVA = "ATIVA";
    private static final Set<String> STATUS_PERMITIDOS = Set.of(
            "ATIVA",
            "TRANCADA",
            "CANCELADA",
            "CONCLUIDA"
    );

    private final MatriculaRepository matriculaRepository;
    private final AlunoRepository alunoRepository;
    private final TurmaRepository turmaRepository;
    private final ProfessorRepository professorRepository;
    private final TurmaDisciplinaRepository turmaDisciplinaRepository;
    private final NotaRepository notaRepository;
    private final FaltaRepository faltaRepository;
    private final AuditoriaService auditoriaService;

    public MatriculaService(
            MatriculaRepository matriculaRepository,
            AlunoRepository alunoRepository,
            TurmaRepository turmaRepository,
            ProfessorRepository professorRepository,
            TurmaDisciplinaRepository turmaDisciplinaRepository,
            NotaRepository notaRepository,
            FaltaRepository faltaRepository,
            AuditoriaService auditoriaService) {
        this.matriculaRepository = matriculaRepository;
        this.alunoRepository = alunoRepository;
        this.turmaRepository = turmaRepository;
        this.professorRepository = professorRepository;
        this.turmaDisciplinaRepository = turmaDisciplinaRepository;
        this.notaRepository = notaRepository;
        this.faltaRepository = faltaRepository;
        this.auditoriaService = auditoriaService;
    }

    @Transactional
    public Matricula salvar(Matricula matricula, String emailAdministrador) {
        if (matricula.getAluno() == null || matricula.getAluno().getIdAluno() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aluno é obrigatório");
        }

        if (matricula.getTurma() == null || matricula.getTurma().getIdTurma() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Turma é obrigatória");
        }

        if (matricula.getDataMatricula() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data da matrícula é obrigatória");
        }

        String status = validarENormalizarStatus(matricula.getStatus());
        validarSemestreAluno(matricula.getSemestreAluno());

        Integer idAluno = matricula.getAluno().getIdAluno();
        Integer idTurma = matricula.getTurma().getIdTurma();

        Aluno aluno = alunoRepository.findById(idAluno)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));

        Turma turma = turmaRepository.findById(idTurma)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada"));

        if (matriculaRepository.existsByAluno_IdAlunoAndTurma_IdTurma(idAluno, idTurma)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Aluno já possui matrícula nesta turma. Atualize a matrícula existente se precisar alterar o status"
            );
        }

        matricula.setAluno(aluno);
        matricula.setTurma(turma);
        matricula.setStatus(status);

        Matricula matriculaSalva = matriculaRepository.save(matricula);

        auditoriaService.registrar(
                emailAdministrador,
                "CRIACAO_MATRICULA",
                "Aluno " + aluno.getUsuario().getNome()
                        + " matriculado na turma " + turma.getNome()
                        + " com status " + matriculaSalva.getStatus()
        );

        return matriculaSalva;
    }

    public List<Matricula> listar() {
        return matriculaRepository.findAll();
    }

    public Optional<Matricula> buscarPorId(Integer id) {
        return matriculaRepository.findById(id);
    }

    @Transactional
    public Matricula atualizar(Integer idMatricula, AtualizarMatriculaDTO dados, String emailAdministrador) {
        if (dados == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados da matrícula são obrigatórios");
        }

        Matricula matricula = matriculaRepository.findById(idMatricula)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Matrícula não encontrada"));

        if (dados.getDataMatricula() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data da matrícula é obrigatória");
        }

        String novoStatus = validarENormalizarStatus(dados.getStatus());
        validarSemestreAluno(dados.getSemestreAluno());

        String statusAnterior = matricula.getStatus();
        Integer semestreAnterior = matricula.getSemestreAluno();
        String dataAnterior = matricula.getDataMatricula() != null
                ? matricula.getDataMatricula().toString()
                : "--";

        matricula.setDataMatricula(dados.getDataMatricula());
        matricula.setStatus(novoStatus);
        matricula.setSemestreAluno(dados.getSemestreAluno());

        Matricula matriculaAtualizada = matriculaRepository.save(matricula);

        auditoriaService.registrar(
                emailAdministrador,
                "ALTERACAO_MATRICULA",
                "Matrícula do aluno " + matricula.getAluno().getUsuario().getNome()
                        + " na turma " + matricula.getTurma().getNome()
                        + " alterada. Data: " + dataAnterior
                        + " -> " + matriculaAtualizada.getDataMatricula()
                        + ", status: " + statusAnterior
                        + " -> " + matriculaAtualizada.getStatus()
                        + ", semestre do aluno: " + semestreAnterior
                        + " -> " + matriculaAtualizada.getSemestreAluno()
        );

        return matriculaAtualizada;
    }

    @Transactional
    public void excluir(Integer idMatricula, String emailAdministrador) {
        Matricula matricula = matriculaRepository.findById(idMatricula)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Matrícula não encontrada"));

        if (!notaRepository.findByMatricula_IdMatricula(idMatricula).isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Não é possível excluir a matrícula porque ela possui notas vinculadas"
            );
        }

        if (!faltaRepository.findByMatricula_IdMatricula(idMatricula).isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Não é possível excluir a matrícula porque ela possui faltas vinculadas"
            );
        }

        String nomeAluno = matricula.getAluno().getUsuario().getNome();
        String nomeTurma = matricula.getTurma().getNome();

        matriculaRepository.delete(matricula);

        auditoriaService.registrar(
                emailAdministrador,
                "EXCLUSAO_MATRICULA",
                "Matrícula do aluno " + nomeAluno + " na turma " + nomeTurma + " excluída"
        );
    }

    public List<MatriculaAlunoDTO> listarMinhasMatriculas(String email) {
        Aluno aluno = alunoRepository.findByUsuario_Email(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));

        List<Matricula> matriculas = matriculaRepository.findByAluno_IdAluno(aluno.getIdAluno());
        List<MatriculaAlunoDTO> matriculasDTO = new ArrayList<>();

        for (Matricula matricula : matriculas) {
            matriculasDTO.add(
                    new MatriculaAlunoDTO(
                            matricula.getIdMatricula(),
                            matricula.getTurma().getNome(),
                            matricula.getTurma().getSemestre(),
                            matricula.getSemestreAluno(),
                            matricula.getTurma().getAno(),
                            matricula.getDataMatricula(),
                            matricula.getStatus()
                    )
            );
        }

        return matriculasDTO;
    }

    public List<Matricula> listarPorTurmaProfessor(Integer idTurma, String emailProfessor) {
        turmaRepository.findById(idTurma)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turma não encontrada"));

        Professor professor = professorRepository.findByUsuario_Email(emailProfessor)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor não encontrado"));

        boolean possuiAcesso = turmaDisciplinaRepository
                .existsByTurma_IdTurmaAndProfessor_IdProfessor(idTurma, professor.getIdProfessor());

        if (!possuiAcesso) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você não possui acesso a esta turma");
        }

        return matriculaRepository.findByTurma_IdTurmaAndStatusIgnoreCase(idTurma, STATUS_ATIVA);
    }

    private String validarENormalizarStatus(String status) {
        if (status == null || status.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status é obrigatório");
        }

        String statusNormalizado = status.trim().toUpperCase(Locale.ROOT);

        if (!STATUS_PERMITIDOS.contains(statusNormalizado)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Status inválido. Use ATIVA, TRANCADA, CANCELADA ou CONCLUIDA"
            );
        }

        return statusNormalizado;
    }

    private void validarSemestreAluno(Integer semestreAluno) {
        if (semestreAluno == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Semestre do aluno é obrigatório");
        }

        if (semestreAluno < 1 || semestreAluno > 4) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Semestre do aluno deve ser entre 1 e 4"
            );
        }
    }
}

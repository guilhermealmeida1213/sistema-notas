package com.guilherme.sistemanotas.service;

import com.guilherme.sistemanotas.dto.AtualizarFaltaDTO;
import com.guilherme.sistemanotas.dto.FaltaAdminDTO;
import com.guilherme.sistemanotas.dto.FaltaAlunoDTO;
import com.guilherme.sistemanotas.dto.FaltaProfessorDTO;
import com.guilherme.sistemanotas.model.Aluno;
import com.guilherme.sistemanotas.model.Falta;
import com.guilherme.sistemanotas.model.Matricula;
import com.guilherme.sistemanotas.model.Professor;
import com.guilherme.sistemanotas.model.TurmaDisciplina;
import com.guilherme.sistemanotas.repository.AlunoRepository;
import com.guilherme.sistemanotas.repository.FaltaRepository;
import com.guilherme.sistemanotas.repository.MatriculaRepository;
import com.guilherme.sistemanotas.repository.ProfessorRepository;
import com.guilherme.sistemanotas.repository.TurmaDisciplinaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FaltaService {

    private final FaltaRepository faltaRepository;
    private final MatriculaRepository matriculaRepository;
    private final TurmaDisciplinaRepository turmaDisciplinaRepository;
    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;
    private final AuditoriaService auditoriaService;

    public FaltaService(
            FaltaRepository faltaRepository,
            MatriculaRepository matriculaRepository,
            TurmaDisciplinaRepository turmaDisciplinaRepository,
            AlunoRepository alunoRepository,
            ProfessorRepository professorRepository,
            AuditoriaService auditoriaService) {

        this.faltaRepository = faltaRepository;
        this.matriculaRepository = matriculaRepository;
        this.turmaDisciplinaRepository = turmaDisciplinaRepository;
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
        this.auditoriaService = auditoriaService;
    }

    // =========================
    // CADASTRAR FALTA
    // =========================

    @Transactional
    public Falta salvar(
            Falta falta,
            String emailUsuario) {

        if (falta.getMatricula() == null ||
                falta.getMatricula().getIdMatricula() == null) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Matrícula é obrigatória"
            );
        }

        if (falta.getTurmaDisciplina() == null ||
                falta.getTurmaDisciplina().getIdTurmaDisciplina() == null) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Turma/Disciplina é obrigatória"
            );
        }

        if (falta.getQuantidade() == null ||
                falta.getQuantidade() <= 0) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Quantidade de faltas deve ser maior que zero"
            );
        }

        if (falta.getDataFalta() == null) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Data da falta é obrigatória"
            );
        }

        Integer idMatricula =
                falta.getMatricula()
                        .getIdMatricula();

        Integer idTurmaDisciplina =
                falta.getTurmaDisciplina()
                        .getIdTurmaDisciplina();

        Matricula matricula =
                matriculaRepository
                        .findById(idMatricula)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Matrícula não encontrada"
                                )
                        );

        TurmaDisciplina turmaDisciplina =
                turmaDisciplinaRepository
                        .findById(idTurmaDisciplina)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Turma/Disciplina não encontrada"
                                )
                        );

        validarMesmaTurma(
                matricula,
                turmaDisciplina
        );

        validarProfessorResponsavel(
                turmaDisciplina,
                emailUsuario
        );

        falta.setMatricula(
                matricula
        );

        falta.setTurmaDisciplina(
                turmaDisciplina
        );

        Falta faltaSalva =
                faltaRepository.save(
                        falta
                );

        auditoriaService.registrar(
                emailUsuario,
                "LANCAMENTO_FALTA",
                "Falta lançada para a matrícula "
                        + matricula.getIdMatricula()
                        + " na disciplina "
                        + turmaDisciplina
                        .getDisciplina()
                        .getNome()
                        + " com quantidade "
                        + faltaSalva.getQuantidade()
        );

        return faltaSalva;
    }

    // =========================
    // ATUALIZAR FALTA
    // =========================

    @Transactional
    public Falta atualizar(
            Integer id,
            AtualizarFaltaDTO dados,
            String emailUsuario) {

        Falta falta =
                faltaRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Falta não encontrada"
                                )
                        );

        // IMPORTANTE:
        // autorização antes de qualquer alteração
        validarProfessorResponsavel(
                falta.getTurmaDisciplina(),
                emailUsuario
        );

        if (dados == null) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Dados da falta são obrigatórios"
            );
        }

        if (dados.getQuantidade() == null ||
                dados.getQuantidade() <= 0) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Quantidade de faltas deve ser maior que zero"
            );
        }

        if (dados.getDataFalta() == null) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Data da falta é obrigatória"
            );
        }

        Integer quantidadeAnterior =
                falta.getQuantidade();

        falta.setQuantidade(
                dados.getQuantidade()
        );

        falta.setDataFalta(
                dados.getDataFalta()
        );

        Falta faltaAtualizada =
                faltaRepository.save(
                        falta
                );

        auditoriaService.registrar(
                emailUsuario,
                "ALTERACAO_FALTA",
                "Falta "
                        + faltaAtualizada.getIdFalta()
                        + " da disciplina "
                        + faltaAtualizada
                        .getTurmaDisciplina()
                        .getDisciplina()
                        .getNome()
                        + " alterada de "
                        + quantidadeAnterior
                        + " para "
                        + faltaAtualizada.getQuantidade()
        );

        return faltaAtualizada;
    }

    // =========================
    // LISTAR TODAS AS FALTAS
    // ADMIN
    // =========================

    public List<FaltaAdminDTO> listar() {

        List<Falta> faltas =
                faltaRepository.findAll();

        List<FaltaAdminDTO> faltasDTO =
                new ArrayList<>();

        for (Falta falta : faltas) {

            String nomeProfessor = null;

            if (falta.getTurmaDisciplina().getProfessor() != null &&
                    falta.getTurmaDisciplina()
                            .getProfessor()
                            .getUsuario() != null) {

                nomeProfessor =
                        falta.getTurmaDisciplina()
                                .getProfessor()
                                .getUsuario()
                                .getNome();
            }

            FaltaAdminDTO dto =
                    new FaltaAdminDTO(
                            falta.getIdFalta(),

                            falta.getMatricula()
                                    .getIdMatricula(),

                            falta.getMatricula()
                                    .getAluno()
                                    .getUsuario()
                                    .getNome(),

                            falta.getMatricula()
                                    .getAluno()
                                    .getMatricula(),

                            falta.getTurmaDisciplina()
                                    .getTurma()
                                    .getNome(),

                            falta.getTurmaDisciplina()
                                    .getDisciplina()
                                    .getNome(),

                            nomeProfessor,

                            falta.getDataFalta(),

                            falta.getQuantidade()
                    );

            faltasDTO.add(
                    dto
            );
        }

        return faltasDTO;
    }

    // =========================
    // BUSCAR FALTA POR ID
    // =========================

    public Optional<Falta> buscarPorId(
            Integer id) {

        return faltaRepository.findById(
                id
        );
    }

    // =========================
    // LISTAR MINHAS FALTAS
    // ALUNO
    // =========================

    public List<FaltaAlunoDTO> listarMinhasFaltas(
            String email) {

        Aluno aluno =
                alunoRepository
                        .findByUsuario_Email(email)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Aluno não encontrado"
                                )
                        );

        List<Matricula> matriculas =
                matriculaRepository
                        .findByAluno_IdAluno(
                                aluno.getIdAluno()
                        );

        List<FaltaAlunoDTO> faltasDTO =
                new ArrayList<>();

        for (Matricula matricula : matriculas) {

            List<Falta> faltasDaMatricula =
                    faltaRepository
                            .findByMatricula_IdMatricula(
                                    matricula.getIdMatricula()
                            );

            for (Falta falta : faltasDaMatricula) {

                FaltaAlunoDTO dto =
                        new FaltaAlunoDTO(
                                falta.getIdFalta(),

                                falta.getTurmaDisciplina()
                                        .getDisciplina()
                                        .getNome(),

                                falta.getDataFalta(),

                                falta.getQuantidade()
                        );

                faltasDTO.add(
                        dto
                );
            }
        }

        return faltasDTO;
    }

    // =========================
    // LISTAR FALTAS DAS MINHAS TURMAS
    // PROFESSOR
    // =========================

    public List<FaltaProfessorDTO> listarFaltasDasMinhasTurmas(
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

        List<Falta> faltas =
                faltaRepository
                        .findByTurmaDisciplina_Professor_IdProfessor(
                                professor.getIdProfessor()
                        );

        List<FaltaProfessorDTO> faltasDTO =
                new ArrayList<>();

        for (Falta falta : faltas) {

            FaltaProfessorDTO dto =
                    new FaltaProfessorDTO(
                            falta.getIdFalta(),

                            falta.getMatricula()
                                    .getIdMatricula(),

                            falta.getMatricula()
                                    .getAluno()
                                    .getUsuario()
                                    .getNome(),

                            falta.getMatricula()
                                    .getAluno()
                                    .getMatricula(),

                            falta.getTurmaDisciplina()
                                    .getTurma()
                                    .getNome(),

                            falta.getTurmaDisciplina()
                                    .getDisciplina()
                                    .getNome(),

                            falta.getDataFalta(),

                            falta.getQuantidade()
                    );

            faltasDTO.add(
                    dto
            );
        }

        return faltasDTO;
    }

    // =========================
    // VALIDAR MESMA TURMA
    // =========================

    private void validarMesmaTurma(
            Matricula matricula,
            TurmaDisciplina turmaDisciplina) {

        Integer idTurmaMatricula =
                matricula
                        .getTurma()
                        .getIdTurma();

        Integer idTurmaVinculo =
                turmaDisciplina
                        .getTurma()
                        .getIdTurma();

        if (!idTurmaMatricula.equals(
                idTurmaVinculo
        )) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "A matrícula não pertence à turma desta disciplina"
            );
        }
    }

    // =========================
    // VALIDAR PROFESSOR / ADMIN
    // =========================

    private void validarProfessorResponsavel(
            TurmaDisciplina turmaDisciplina,
            String emailUsuario) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated()) {

            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Usuário não autenticado"
            );
        }

        boolean administrador =
                authentication
                        .getAuthorities()
                        .stream()
                        .anyMatch(authority ->
                                authority
                                        .getAuthority()
                                        .equals("ROLE_ADMINISTRADOR")
                        );

        // Administrador pode realizar a operação
        if (administrador) {
            return;
        }

        // Se não é administrador, obrigatoriamente
        // precisa existir como professor.
        Professor professorAutenticado =
                professorRepository
                        .findByUsuario_Email(emailUsuario)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.FORBIDDEN,
                                        "Professor autenticado não encontrado"
                                )
                        );

        if (turmaDisciplina.getProfessor() == null ||
                turmaDisciplina
                        .getProfessor()
                        .getIdProfessor() == null) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Turma/Disciplina não possui professor responsável"
            );
        }

        Integer idProfessorAutenticado =
                professorAutenticado
                        .getIdProfessor();

        Integer idProfessorResponsavel =
                turmaDisciplina
                        .getProfessor()
                        .getIdProfessor();

        if (!idProfessorAutenticado.equals(
                idProfessorResponsavel
        )) {

            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Professor não possui permissão para lançar ou alterar falta nesta disciplina"
            );
        }
    }
}
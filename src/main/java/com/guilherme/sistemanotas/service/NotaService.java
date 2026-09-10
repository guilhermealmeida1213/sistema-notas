package com.guilherme.sistemanotas.service;

import com.guilherme.sistemanotas.dto.AtualizarAviDTO;
import com.guilherme.sistemanotas.dto.AtualizarNotaDTO;
import com.guilherme.sistemanotas.dto.LancamentoAviDTO;
import com.guilherme.sistemanotas.dto.MediaDisciplinaDTO;
import com.guilherme.sistemanotas.dto.NotaAdminDTO;
import com.guilherme.sistemanotas.dto.NotaAlunoDTO;
import com.guilherme.sistemanotas.dto.NotaProfessorDTO;
import com.guilherme.sistemanotas.dto.ResumoDisciplinaDTO;
import com.guilherme.sistemanotas.model.Aluno;
import com.guilherme.sistemanotas.model.Falta;
import com.guilherme.sistemanotas.model.Matricula;
import com.guilherme.sistemanotas.model.Nota;
import com.guilherme.sistemanotas.model.Professor;
import com.guilherme.sistemanotas.model.TipoAvaliacao;
import com.guilherme.sistemanotas.model.TurmaDisciplina;
import com.guilherme.sistemanotas.repository.AlunoRepository;
import com.guilherme.sistemanotas.repository.FaltaRepository;
import com.guilherme.sistemanotas.repository.MatriculaRepository;
import com.guilherme.sistemanotas.repository.NotaRepository;
import com.guilherme.sistemanotas.repository.ProfessorRepository;
import com.guilherme.sistemanotas.repository.TurmaDisciplinaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    private final NotaRepository notaRepository;
    private final MatriculaRepository matriculaRepository;
    private final TurmaDisciplinaRepository turmaDisciplinaRepository;
    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;
    private final FaltaRepository faltaRepository;
    private final AuditoriaService auditoriaService;

    public NotaService(
            NotaRepository notaRepository,
            MatriculaRepository matriculaRepository,
            TurmaDisciplinaRepository turmaDisciplinaRepository,
            AlunoRepository alunoRepository,
            ProfessorRepository professorRepository,
            FaltaRepository faltaRepository,
            AuditoriaService auditoriaService) {

        this.notaRepository = notaRepository;
        this.matriculaRepository = matriculaRepository;
        this.turmaDisciplinaRepository = turmaDisciplinaRepository;
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
        this.faltaRepository = faltaRepository;
        this.auditoriaService = auditoriaService;
    }

    // =========================
    // CADASTRAR AVC / AVG
    // =========================

    @Transactional
    public Nota salvar(
            Nota nota,
            String emailUsuario) {

        if (nota.getMatricula() == null ||
                nota.getMatricula().getIdMatricula() == null) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Matrícula é obrigatória"
            );
        }

        if (nota.getTurmaDisciplina() == null ||
                nota.getTurmaDisciplina().getIdTurmaDisciplina() == null) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Turma/Disciplina é obrigatória"
            );
        }

        if (nota.getTipoAvaliacao() == null) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Tipo de avaliação é obrigatório"
            );
        }

        if (nota.getTipoAvaliacao() == TipoAvaliacao.AVI) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "AVI deve ser lançada pelo endpoint específico /notas/avi"
            );
        }

        if (nota.getValor() == null) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Valor da nota é obrigatório"
            );
        }

        validarValorNota(
                nota.getValor()
        );

        Integer idMatricula =
                nota.getMatricula()
                        .getIdMatricula();

        Integer idTurmaDisciplina =
                nota.getTurmaDisciplina()
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

        // =========================
        // PRIMEIRO VALIDA PERMISSÃO
        // =========================

        validarProfessorResponsavel(
                turmaDisciplina,
                emailUsuario
        );

        // =========================
        // DEPOIS VERIFICA DUPLICIDADE
        // =========================

        boolean avaliacaoJaExiste =
                notaRepository
                        .existsByMatricula_IdMatriculaAndTurmaDisciplina_IdTurmaDisciplinaAndTipoAvaliacao(
                                idMatricula,
                                idTurmaDisciplina,
                                nota.getTipoAvaliacao()
                        );

        if (avaliacaoJaExiste) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Já existe uma nota "
                            + nota.getTipoAvaliacao()
                            + " para este aluno nesta disciplina"
            );
        }

        nota.setMatricula(
                matricula
        );

        nota.setTurmaDisciplina(
                turmaDisciplina
        );

        Nota notaSalva =
                notaRepository.save(nota);

        auditoriaService.registrar(
                emailUsuario,
                "LANCAMENTO_NOTA",
                "Nota "
                        + notaSalva.getTipoAvaliacao()
                        + " lançada para a matrícula "
                        + matricula.getIdMatricula()
                        + " na disciplina "
                        + turmaDisciplina.getDisciplina().getNome()
                        + " com valor "
                        + notaSalva.getValor()
        );

        return notaSalva;
    }

    // =========================
    // LANÇAR AVI INTEGRADA
    // =========================

    @Transactional
    public List<Nota> lancarAvi(
            LancamentoAviDTO dados,
            String emailUsuario) {

        if (dados.getIdMatricula() == null) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Matrícula é obrigatória"
            );
        }

        if (dados.getValor() == null) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Valor da AVI é obrigatório"
            );
        }

        if (dados.getDataAvaliacao() == null) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Data da AVI é obrigatória"
            );
        }

        validarValorNota(
                dados.getValor()
        );

        Matricula matricula =
                matriculaRepository
                        .findById(dados.getIdMatricula())
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Matrícula não encontrada"
                                )
                        );

        Integer idTurma =
                matricula
                        .getTurma()
                        .getIdTurma();

        List<TurmaDisciplina> vinculos =
                turmaDisciplinaRepository
                        .findByTurma_IdTurma(
                                idTurma
                        );

        if (vinculos.isEmpty()) {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "A turma não possui disciplinas vinculadas"
            );
        }

        for (TurmaDisciplina vinculo : vinculos) {

            boolean aviJaExiste =
                    notaRepository
                            .existsByMatricula_IdMatriculaAndTurmaDisciplina_IdTurmaDisciplinaAndTipoAvaliacao(
                                    matricula.getIdMatricula(),
                                    vinculo.getIdTurmaDisciplina(),
                                    TipoAvaliacao.AVI
                            );

            if (aviJaExiste) {

                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Já existe AVI lançada para uma ou mais disciplinas desta matrícula"
                );
            }
        }

        List<Nota> notasAvi =
                new ArrayList<>();

        for (TurmaDisciplina vinculo : vinculos) {

            Nota nota =
                    new Nota();

            nota.setMatricula(
                    matricula
            );

            nota.setTurmaDisciplina(
                    vinculo
            );

            nota.setTipoAvaliacao(
                    TipoAvaliacao.AVI
            );

            nota.setValor(
                    dados.getValor()
            );

            nota.setDataAvaliacao(
                    dados.getDataAvaliacao()
            );

            notasAvi.add(
                    notaRepository.save(nota)
            );
        }

        auditoriaService.registrar(
                emailUsuario,
                "LANCAMENTO_AVI",
                "AVI integrada lançada para a matrícula "
                        + matricula.getIdMatricula()
                        + " com valor "
                        + dados.getValor()
                        + " em "
                        + notasAvi.size()
                        + " disciplinas"
        );

        return notasAvi;
    }

    // =========================
    // ATUALIZAR AVI INTEGRADA
    // ADMIN
    // =========================

    @Transactional
    public List<Nota> atualizarAviIntegrada(
            Integer idMatricula,
            AtualizarAviDTO dados,
            String emailUsuario) {

        if (idMatricula == null) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Matrícula é obrigatória"
            );
        }

        Matricula matricula =
                matriculaRepository
                        .findById(idMatricula)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Matrícula não encontrada"
                                )
                        );

        if (dados == null ||
                dados.getValor() == null) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Valor da AVI é obrigatório"
            );
        }

        validarValorNota(
                dados.getValor()
        );

        if (dados.getDataAvaliacao() == null) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Data da AVI é obrigatória"
            );
        }

        List<Nota> notasAvi =
                notaRepository
                        .findByMatricula_IdMatriculaAndTipoAvaliacao(
                                matricula.getIdMatricula(),
                                TipoAvaliacao.AVI
                        );

        if (notasAvi.isEmpty()) {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Nenhuma AVI encontrada para esta matrícula"
            );
        }

        Integer idTurma =
                matricula
                        .getTurma()
                        .getIdTurma();

        List<TurmaDisciplina> disciplinasDaTurma =
                turmaDisciplinaRepository
                        .findByTurma_IdTurma(
                                idTurma
                        );

        if (notasAvi.size() !=
                disciplinasDaTurma.size()) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "AVI integrada está inconsistente: nem todas as disciplinas possuem AVI"
            );
        }

        BigDecimal valorAnterior =
                notasAvi
                        .get(0)
                        .getValor();

        for (Nota nota : notasAvi) {

            nota.setValor(
                    dados.getValor()
            );

            nota.setDataAvaliacao(
                    dados.getDataAvaliacao()
            );
        }

        List<Nota> notasAtualizadas =
                notaRepository.saveAll(
                        notasAvi
                );

        auditoriaService.registrar(
                emailUsuario,
                "ALTERACAO_AVI",
                "AVI integrada da matrícula "
                        + idMatricula
                        + " alterada de "
                        + valorAnterior
                        + " para "
                        + dados.getValor()
        );

        return notasAtualizadas;
    }

    // =========================
    // LISTAR TODAS AS NOTAS
    // ADMIN
    // =========================

    public List<NotaAdminDTO> listar() {

        List<Nota> notas =
                notaRepository.findAll();

        List<NotaAdminDTO> notasDTO =
                new ArrayList<>();

        for (Nota nota : notas) {

            String nomeProfessor =
                    null;

            if (nota.getTurmaDisciplina().getProfessor() != null &&
                    nota.getTurmaDisciplina()
                            .getProfessor()
                            .getUsuario() != null) {

                nomeProfessor =
                        nota.getTurmaDisciplina()
                                .getProfessor()
                                .getUsuario()
                                .getNome();
            }

            NotaAdminDTO dto =
                    new NotaAdminDTO(
                            nota.getIdNota(),
                            nota.getMatricula()
                                    .getIdMatricula(),
                            nota.getMatricula()
                                    .getAluno()
                                    .getUsuario()
                                    .getNome(),
                            nota.getMatricula()
                                    .getAluno()
                                    .getMatricula(),
                            nota.getTurmaDisciplina()
                                    .getTurma()
                                    .getNome(),
                            nota.getTurmaDisciplina()
                                    .getDisciplina()
                                    .getNome(),
                            nomeProfessor,
                            nota.getTipoAvaliacao()
                                    .name(),
                            nota.getValor(),
                            nota.getDataAvaliacao()
                    );

            notasDTO.add(
                    dto
            );
        }

        return notasDTO;
    }

    // =========================
    // BUSCAR NOTA POR ID
    // =========================

    public Optional<Nota> buscarPorId(
            Integer id) {

        return notaRepository.findById(
                id
        );
    }

    // =========================
    // ATUALIZAR NOTA AVC / AVG
    // =========================

    @Transactional
    public Nota atualizar(
            Integer id,
            AtualizarNotaDTO dados,
            String emailUsuario) {

        Nota nota =
                notaRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Nota não encontrada"
                                )
                        );

        if (nota.getTipoAvaliacao() ==
                TipoAvaliacao.AVI) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "AVI não pode ser alterada individualmente"
            );
        }

        validarProfessorResponsavel(
                nota.getTurmaDisciplina(),
                emailUsuario
        );

        if (dados.getValor() == null) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Valor da nota é obrigatório"
            );
        }

        validarValorNota(
                dados.getValor()
        );

        if (dados.getDataAvaliacao() == null) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Data da avaliação é obrigatória"
            );
        }

        BigDecimal valorAnterior =
                nota.getValor();

        nota.setValor(
                dados.getValor()
        );

        nota.setDataAvaliacao(
                dados.getDataAvaliacao()
        );

        Nota notaAtualizada =
                notaRepository.save(
                        nota
                );

        auditoriaService.registrar(
                emailUsuario,
                "ALTERACAO_NOTA",
                "Nota "
                        + notaAtualizada.getIdNota()
                        + " ("
                        + notaAtualizada.getTipoAvaliacao()
                        + ") da disciplina "
                        + notaAtualizada
                        .getTurmaDisciplina()
                        .getDisciplina()
                        .getNome()
                        + " alterada de "
                        + valorAnterior
                        + " para "
                        + dados.getValor()
        );

        return notaAtualizada;
    }

    // =========================
    // LISTAR MINHAS NOTAS
    // ALUNO
    // =========================

    public List<NotaAlunoDTO> listarMinhasNotas(
            String email) {

        Aluno aluno =
                buscarAlunoPorEmail(
                        email
                );

        List<Matricula> matriculas =
                matriculaRepository
                        .findByAluno_IdAluno(
                                aluno.getIdAluno()
                        );

        List<NotaAlunoDTO> notasDTO =
                new ArrayList<>();

        for (Matricula matricula : matriculas) {

            List<Nota> notasDaMatricula =
                    notaRepository
                            .findByMatricula_IdMatricula(
                                    matricula.getIdMatricula()
                            );

            for (Nota nota : notasDaMatricula) {

                NotaAlunoDTO dto =
                        new NotaAlunoDTO(
                                nota.getIdNota(),
                                nota.getTurmaDisciplina()
                                        .getDisciplina()
                                        .getNome(),
                                nota.getTipoAvaliacao()
                                        .name(),
                                nota.getValor(),
                                nota.getDataAvaliacao()
                        );

                notasDTO.add(
                        dto
                );
            }
        }

        return notasDTO;
    }

    // =========================
    // CALCULAR MINHAS MÉDIAS
    // =========================

    public List<MediaDisciplinaDTO> calcularMinhasMedias(
            String email) {

        Aluno aluno =
                buscarAlunoPorEmail(
                        email
                );

        List<Matricula> matriculas =
                matriculaRepository
                        .findByAluno_IdAluno(
                                aluno.getIdAluno()
                        );

        if (matriculas.isEmpty()) {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Aluno não possui matrícula"
            );
        }

        List<MediaDisciplinaDTO> medias =
                new ArrayList<>();

        for (Matricula matricula : matriculas) {

            List<TurmaDisciplina> disciplinas =
                    turmaDisciplinaRepository
                            .findByTurma_IdTurma(
                                    matricula
                                            .getTurma()
                                            .getIdTurma()
                            );

            for (TurmaDisciplina turmaDisciplina : disciplinas) {

                List<Nota> notas =
                        notaRepository
                                .findByMatricula_IdMatriculaAndTurmaDisciplina_IdTurmaDisciplina(
                                        matricula.getIdMatricula(),
                                        turmaDisciplina.getIdTurmaDisciplina()
                                );

                BigDecimal avc =
                        buscarValorPorTipo(
                                notas,
                                TipoAvaliacao.AVC
                        );

                BigDecimal avg =
                        buscarValorPorTipo(
                                notas,
                                TipoAvaliacao.AVG
                        );

                BigDecimal avi =
                        buscarValorPorTipo(
                                notas,
                                TipoAvaliacao.AVI
                        );

                BigDecimal media =
                        null;

                String situacao;

                if (possuiTodasAsNotas(
                        avc,
                        avg,
                        avi)) {

                    media =
                            calcularMediaUnisa(
                                    avc,
                                    avg,
                                    avi
                            );

                    situacao =
                            media.compareTo(
                                    BigDecimal.valueOf(6)
                            ) >= 0
                                    ? "APROVADO"
                                    : "REPROVADO";

                } else {

                    situacao =
                            "EM ANDAMENTO";
                }

                medias.add(
                        new MediaDisciplinaDTO(
                                turmaDisciplina
                                        .getDisciplina()
                                        .getNome(),
                                avc,
                                avg,
                                avi,
                                media,
                                situacao
                        )
                );
            }
        }

        return medias;
    }

    // =========================
// RESUMO COMPLETO
// =========================

    public List<ResumoDisciplinaDTO> gerarMeuResumo(
            String email) {

        Aluno aluno =
                buscarAlunoPorEmail(
                        email
                );

        List<Matricula> matriculas =
                matriculaRepository
                        .findByAluno_IdAluno(
                                aluno.getIdAluno()
                        );

        if (matriculas.isEmpty()) {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Aluno não possui matrícula"
            );
        }

        List<ResumoDisciplinaDTO> resumos =
                new ArrayList<>();

        for (Matricula matricula : matriculas) {

            List<TurmaDisciplina> disciplinas =
                    turmaDisciplinaRepository
                            .findByTurma_IdTurma(
                                    matricula
                                            .getTurma()
                                            .getIdTurma()
                            );

            for (TurmaDisciplina turmaDisciplina : disciplinas) {

                Integer idMatricula =
                        matricula
                                .getIdMatricula();

                Integer idTurmaDisciplina =
                        turmaDisciplina
                                .getIdTurmaDisciplina();

                List<Nota> notas =
                        notaRepository
                                .findByMatricula_IdMatriculaAndTurmaDisciplina_IdTurmaDisciplina(
                                        idMatricula,
                                        idTurmaDisciplina
                                );

                BigDecimal avc =
                        buscarValorPorTipo(
                                notas,
                                TipoAvaliacao.AVC
                        );

                BigDecimal avg =
                        buscarValorPorTipo(
                                notas,
                                TipoAvaliacao.AVG
                        );

                BigDecimal avi =
                        buscarValorPorTipo(
                                notas,
                                TipoAvaliacao.AVI
                        );

                List<Falta> faltas =
                        faltaRepository
                                .findByMatricula_IdMatriculaAndTurmaDisciplina_IdTurmaDisciplina(
                                        idMatricula,
                                        idTurmaDisciplina
                                );

                int totalFaltas =
                        faltas
                                .stream()
                                .mapToInt(
                                        Falta::getQuantidade
                                )
                                .sum();

                Integer cargaHoraria =
                        turmaDisciplina
                                .getDisciplina()
                                .getCargaHoraria();

                BigDecimal frequencia =
                        calcularFrequencia(
                                cargaHoraria,
                                totalFaltas
                        );

                BigDecimal media =
                        null;

                String situacao;
                String situacaoNota;
                String situacaoFrequencia;

                // =========================
                // SITUAÇÃO DA FREQUÊNCIA
                // =========================

                situacaoFrequencia =
                        calcularSituacaoFrequencia(
                                frequencia
                        );

                // =========================
                // SITUAÇÃO DAS NOTAS
                // =========================

                if (possuiTodasAsNotas(
                        avc,
                        avg,
                        avi)) {

                    media =
                            calcularMediaUnisa(
                                    avc,
                                    avg,
                                    avi
                            );

                    situacaoNota =
                            calcularSituacaoNota(
                                    media
                            );

                    situacao =
                            calcularSituacaoFinal(
                                    media,
                                    frequencia
                            );

                } else {

                    situacaoNota =
                            "EM ANDAMENTO";

                    situacao =
                            "EM ANDAMENTO";
                }

                ResumoDisciplinaDTO dto =
                        new ResumoDisciplinaDTO(
                                turmaDisciplina
                                        .getDisciplina()
                                        .getNome(),
                                avc,
                                avg,
                                avi,
                                media,
                                totalFaltas,
                                frequencia,
                                situacao,
                                situacaoNota,
                                situacaoFrequencia
                        );

                resumos.add(
                        dto
                );
            }
        }

        return resumos;
    }

    // =========================
    // NOTAS DAS TURMAS DO PROFESSOR
    // =========================

    public List<NotaProfessorDTO> listarNotasDasMinhasTurmas(
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

        List<Nota> notas =
                notaRepository
                        .findByTurmaDisciplina_Professor_IdProfessor(
                                professor.getIdProfessor()
                        );

        List<NotaProfessorDTO> notasDTO =
                new ArrayList<>();

        for (Nota nota : notas) {

            NotaProfessorDTO dto =
                    new NotaProfessorDTO(
                            nota.getIdNota(),
                            nota.getMatricula()
                                    .getIdMatricula(),
                            nota.getMatricula()
                                    .getAluno()
                                    .getUsuario()
                                    .getNome(),
                            nota.getMatricula()
                                    .getAluno()
                                    .getMatricula(),
                            nota.getTurmaDisciplina()
                                    .getTurma()
                                    .getNome(),
                            nota.getTurmaDisciplina()
                                    .getDisciplina()
                                    .getNome(),
                            nota.getTipoAvaliacao()
                                    .name(),
                            nota.getValor(),
                            nota.getDataAvaliacao()
                    );

            notasDTO.add(
                    dto
            );
        }

        return notasDTO;
    }

    // =========================
    // MÉTODOS AUXILIARES
    // =========================

    private Aluno buscarAlunoPorEmail(
            String email) {

        return alunoRepository
                .findByUsuario_Email(email)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Aluno não encontrado"
                        )
                );
    }

    private BigDecimal buscarValorPorTipo(
            List<Nota> notas,
            TipoAvaliacao tipo) {

        return notas
                .stream()
                .filter(nota ->
                        nota.getTipoAvaliacao() ==
                                tipo
                )
                .map(
                        Nota::getValor
                )
                .findFirst()
                .orElse(
                        null
                );
    }

    private boolean possuiTodasAsNotas(
            BigDecimal avc,
            BigDecimal avg,
            BigDecimal avi) {

        return avc != null &&
                avg != null &&
                avi != null;
    }

    // =========================
    // REGRA DE MÉDIA
    // =========================

     BigDecimal calcularMediaUnisa(
            BigDecimal avc,
            BigDecimal avg,
            BigDecimal avi) {

        boolean existeNotaZero =
                avc.compareTo(
                        BigDecimal.ZERO
                ) == 0 ||
                        avg.compareTo(
                                BigDecimal.ZERO
                        ) == 0 ||
                        avi.compareTo(
                                BigDecimal.ZERO
                        ) == 0;

        if (existeNotaZero) {

            return avc
                    .add(avg)
                    .add(avi)
                    .divide(
                            BigDecimal.valueOf(3),
                            2,
                            RoundingMode.HALF_UP
                    );
        }

        List<BigDecimal> notas =
                new ArrayList<>(
                        List.of(
                                avc,
                                avg,
                                avi
                        )
                );

        notas.sort(
                Comparator.reverseOrder()
        );

        return notas
                .get(0)
                .add(
                        notas.get(1)
                )
                .divide(
                        BigDecimal.valueOf(2),
                        2,
                        RoundingMode.HALF_UP
                );
    }

    // =========================
    // REGRA DE FREQUÊNCIA
    // =========================

    private BigDecimal calcularFrequencia(
            Integer cargaHoraria,
            Integer faltas) {

        if (cargaHoraria == null ||
                cargaHoraria <= 0) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Carga horária da disciplina é inválida"
            );
        }

        BigDecimal carga =
                BigDecimal.valueOf(
                        cargaHoraria
                );

        BigDecimal quantidadeFaltas =
                BigDecimal.valueOf(
                        faltas
                );

        BigDecimal aulasPresentes =
                carga.subtract(
                        quantidadeFaltas
                );

        if (aulasPresentes.compareTo(
                BigDecimal.ZERO
        ) < 0) {

            aulasPresentes =
                    BigDecimal.ZERO;
        }

        return aulasPresentes
                .divide(
                        carga,
                        4,
                        RoundingMode.HALF_UP
                )
                .multiply(
                        BigDecimal.valueOf(100)
                )
                .setScale(
                        2,
                        RoundingMode.HALF_UP
                );
    }

    // =========================
// SITUAÇÃO DA NOTA
// =========================

    private String calcularSituacaoNota(
            BigDecimal media) {

        if (media.compareTo(
                BigDecimal.valueOf(6)
        ) >= 0) {

            return "APROVADO";
        }

        return "REPROVADO POR NOTA";
    }


    // =========================
    // SITUAÇÃO DA FREQUÊNCIA
    // =========================

    private String calcularSituacaoFrequencia(
            BigDecimal frequencia) {

        if (frequencia.compareTo(
                BigDecimal.valueOf(75)
        ) >= 0) {

            return "FREQUÊNCIA REGULAR";
        }

        return "REPROVADO POR FALTA";
    }

    // =========================
    // SITUAÇÃO FINAL
    // =========================

    private String calcularSituacaoFinal(
            BigDecimal media,
            BigDecimal frequencia) {

        boolean aprovadoNota =
                media.compareTo(
                        BigDecimal.valueOf(6)
                ) >= 0;

        boolean aprovadoFrequencia =
                frequencia.compareTo(
                        BigDecimal.valueOf(75)
                ) >= 0;

        if (aprovadoNota &&
                aprovadoFrequencia) {

            return "APROVADO";
        }

        if (!aprovadoNota &&
                aprovadoFrequencia) {

            return "REPROVADO POR NOTA";
        }

        if (aprovadoNota) {

            return "REPROVADO POR FALTA";
        }

        return "REPROVADO POR NOTA E FALTA";
    }

    // =========================
    // VALIDAÇÕES
    // =========================

    private void validarValorNota(
            BigDecimal valor) {

        if (valor.compareTo(
                BigDecimal.ZERO
        ) < 0 ||
                valor.compareTo(
                        BigDecimal.TEN
                ) > 0) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "A nota deve estar entre 0 e 10"
            );
        }
    }

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

    private void validarProfessorResponsavel(
            TurmaDisciplina turmaDisciplina,
            String emailUsuario) {

        Optional<Professor> professorAutenticado =
                professorRepository
                        .findByUsuario_Email(
                                emailUsuario
                        );

        if (professorAutenticado.isPresent()) {

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
                            .get()
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
                        "Professor não possui permissão para lançar ou alterar nota nesta disciplina"
                );
            }
        }
    }
}
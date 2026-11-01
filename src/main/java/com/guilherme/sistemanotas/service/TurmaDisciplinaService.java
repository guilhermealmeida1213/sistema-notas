package com.guilherme.sistemanotas.service;

import com.guilherme.sistemanotas.dto.AtualizarProfessorTurmaDisciplinaDTO;
import com.guilherme.sistemanotas.dto.HistoricoProfessorTurmaDisciplinaDTO;
import com.guilherme.sistemanotas.model.Disciplina;
import com.guilherme.sistemanotas.model.HistoricoProfessorTurmaDisciplina;
import com.guilherme.sistemanotas.model.Professor;
import com.guilherme.sistemanotas.model.Turma;
import com.guilherme.sistemanotas.model.TurmaDisciplina;
import com.guilherme.sistemanotas.repository.DisciplinaRepository;
import com.guilherme.sistemanotas.repository.FaltaRepository;
import com.guilherme.sistemanotas.repository.HistoricoProfessorTurmaDisciplinaRepository;
import com.guilherme.sistemanotas.repository.NotaRepository;
import com.guilherme.sistemanotas.repository.ProfessorRepository;
import com.guilherme.sistemanotas.repository.TurmaDisciplinaRepository;
import com.guilherme.sistemanotas.repository.TurmaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class TurmaDisciplinaService {

    private final TurmaDisciplinaRepository turmaDisciplinaRepository;
    private final TurmaRepository turmaRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final ProfessorRepository professorRepository;
    private final NotaRepository notaRepository;
    private final FaltaRepository faltaRepository;
    private final AuditoriaService auditoriaService;
    private final HistoricoProfessorTurmaDisciplinaRepository historicoProfessorRepository;

    public TurmaDisciplinaService(
            TurmaDisciplinaRepository turmaDisciplinaRepository,
            TurmaRepository turmaRepository,
            DisciplinaRepository disciplinaRepository,
            ProfessorRepository professorRepository,
            NotaRepository notaRepository,
            FaltaRepository faltaRepository,
            AuditoriaService auditoriaService,
            HistoricoProfessorTurmaDisciplinaRepository historicoProfessorRepository) {

        this.turmaDisciplinaRepository =
                turmaDisciplinaRepository;

        this.turmaRepository =
                turmaRepository;

        this.disciplinaRepository =
                disciplinaRepository;

        this.professorRepository =
                professorRepository;

        this.notaRepository = notaRepository;
        this.faltaRepository = faltaRepository;

        this.auditoriaService =
                auditoriaService;
        this.historicoProfessorRepository = historicoProfessorRepository;
    }

    // =========================
    // CRIAR VÍNCULO
    // ADMIN
    // =========================

    @Transactional
    public TurmaDisciplina salvar(
            TurmaDisciplina turmaDisciplina,
            String emailAdministrador) {

        if (turmaDisciplina.getAulasPrevistas() == null || turmaDisciplina.getAulasPrevistas() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Informe o total de aulas previstas para esta disciplina nesta turma");
        }

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

        validarProfessorAtivo(professor);

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

        registrarNovaAtribuicao(
                vinculoSalvo,
                professor,
                emailAdministrador,
                "Atribuição inicial do professor ao vínculo acadêmico."
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

    @Transactional
    public TurmaDisciplina atualizarAulasPrevistas(Integer id, Integer aulasPrevistas, String emailAdministrador) {
        if (aulasPrevistas == null || aulasPrevistas <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O total de aulas previstas deve ser maior que zero");
        }
        TurmaDisciplina vinculo = turmaDisciplinaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vínculo acadêmico não encontrado"));
        Integer anterior = vinculo.getAulasPrevistas();
        vinculo.setAulasPrevistas(aulasPrevistas);
        TurmaDisciplina atualizado = turmaDisciplinaRepository.save(vinculo);
        auditoriaService.registrar(emailAdministrador, "ALTERACAO_AULAS_PREVISTAS",
                "Aulas previstas da disciplina " + vinculo.getDisciplina().getNome() + " da turma " + vinculo.getTurma().getNome() + " alterados de " + (anterior == null ? "não configurado" : anterior) + " para " + aulasPrevistas);
        return atualizado;
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

        if (dados == null || dados.getIdProfessor() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Professor é obrigatório"
            );
        }

        if (dados.getMotivo() != null && dados.getMotivo().length() > 500) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O motivo deve ter no máximo 500 caracteres"
            );
        }

        TurmaDisciplina vinculo = turmaDisciplinaRepository
                .findById(idTurmaDisciplina)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Turma/Disciplina não encontrada"
                ));

        Professor novoProfessor = professorRepository
                .findById(dados.getIdProfessor())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Professor não encontrado"
                ));

        validarProfessorAtivo(novoProfessor);

        Professor professorAnterior = vinculo.getProfessor();
        if (professorAnterior == null) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "O vínculo não possui um professor atual para registrar a substituição"
            );
        }

        if (professorAnterior.getIdProfessor().equals(novoProfessor.getIdProfessor())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "O professor informado já é responsável por esta disciplina"
            );
        }

        LocalDateTime agora = LocalDateTime.now();
        String motivo = dados.getMotivo() == null || dados.getMotivo().isBlank()
                ? "Substituição do professor responsável."
                : dados.getMotivo().trim();

        // Fecha a atribuição atualmente aberta. Para vínculos anteriores ao
        // controle de histórico, cria um registro legado sem inventar a data inicial.
        HistoricoProfessorTurmaDisciplina historicoAtual = historicoProfessorRepository
                .findFirstByTurmaDisciplina_IdTurmaDisciplinaAndDataFimIsNullOrderByRegistradoEmDesc(
                        idTurmaDisciplina
                )
                .orElse(null);

        if (historicoAtual != null) {
            if (!historicoAtual.getProfessor().getIdProfessor().equals(professorAnterior.getIdProfessor())) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "O histórico de responsáveis está divergente do professor atual. A alteração foi cancelada para preservar os registros."
                );
            }
            historicoAtual.setDataFim(agora);
            historicoProfessorRepository.save(historicoAtual);
        } else {
            HistoricoProfessorTurmaDisciplina historicoLegado =
                    criarRegistroHistorico(
                            vinculo,
                            professorAnterior,
                            null,
                            agora,
                            null,
                            "Registro anterior ao controle de histórico. A data inicial e o responsável pela atribuição original não estavam armazenados."
                    );
            historicoProfessorRepository.save(historicoLegado);
        }

        String nomeProfessorAnterior = professorAnterior.getUsuario().getNome();
        String registroProfessorAnterior = professorAnterior.getRegistro();

        vinculo.setProfessor(novoProfessor);
        TurmaDisciplina vinculoAtualizado = turmaDisciplinaRepository.save(vinculo);

        registrarNovaAtribuicao(
                vinculoAtualizado,
                novoProfessor,
                emailAdministrador,
                motivo
        );

        auditoriaService.registrar(
                emailAdministrador,
                "ALTERACAO_PROFESSOR_DISCIPLINA",
                "Professor responsável pela disciplina "
                        + vinculo.getDisciplina().getNome()
                        + " da turma "
                        + vinculo.getTurma().getNome()
                        + " alterado de "
                        + nomeProfessorAnterior
                        + " (" + registroProfessorAnterior + ") para "
                        + novoProfessor.getUsuario().getNome()
                        + " (" + novoProfessor.getRegistro() + ")"
                        + ". Motivo: " + motivo
        );

        return vinculoAtualizado;
    }

    @Transactional(readOnly = true)
    public List<HistoricoProfessorTurmaDisciplinaDTO> listarHistoricoProfessores(
            Integer idTurmaDisciplina) {

        TurmaDisciplina vinculo = turmaDisciplinaRepository.findById(idTurmaDisciplina)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Turma/Disciplina não encontrada"
                ));

        List<HistoricoProfessorTurmaDisciplina> registros =
                historicoProfessorRepository
                        .findByTurmaDisciplina_IdTurmaDisciplinaOrderByRegistradoEmDesc(idTurmaDisciplina);

        List<HistoricoProfessorTurmaDisciplinaDTO> resposta = new ArrayList<>();
        for (HistoricoProfessorTurmaDisciplina registro : registros) {
            resposta.add(toHistoricoDTO(registro));
        }

        // Vínculos criados antes desta melhoria ainda não têm linha do tempo.
        // Exibimos o responsável atual, deixando explícito que a data inicial é desconhecida.
        if (resposta.isEmpty() && vinculo.getProfessor() != null) {
            Professor professor = vinculo.getProfessor();
            resposta.add(new HistoricoProfessorTurmaDisciplinaDTO(
                    null,
                    professor.getIdProfessor(),
                    professor.getUsuario() != null ? professor.getUsuario().getNome() : "Professor",
                    professor.getRegistro(),
                    null,
                    null,
                    null,
                    "Não registrado (vínculo anterior ao histórico)",
                    "Responsável atual identificado no cadastro. A data de início não era registrada na versão anterior.",
                    true
            ));
        }

        return resposta;
    }

    private void registrarNovaAtribuicao(
            TurmaDisciplina vinculo,
            Professor professor,
            String emailResponsavel,
            String motivo) {

        LocalDateTime agora = LocalDateTime.now();
        historicoProfessorRepository.save(
                criarRegistroHistorico(
                        vinculo,
                        professor,
                        agora,
                        null,
                        emailResponsavel,
                        motivo
                )
        );
    }

    private HistoricoProfessorTurmaDisciplina criarRegistroHistorico(
            TurmaDisciplina vinculo,
            Professor professor,
            LocalDateTime dataInicio,
            LocalDateTime dataFim,
            String emailResponsavel,
            String motivo) {

        HistoricoProfessorTurmaDisciplina historico = new HistoricoProfessorTurmaDisciplina();
        historico.setTurmaDisciplina(vinculo);
        historico.setProfessor(professor);
        historico.setDataInicio(dataInicio);
        historico.setDataFim(dataFim);
        historico.setRegistradoEm(LocalDateTime.now());
        historico.setRegistradoPorEmail(emailResponsavel);
        historico.setMotivo(motivo);
        return historico;
    }

    private HistoricoProfessorTurmaDisciplinaDTO toHistoricoDTO(
            HistoricoProfessorTurmaDisciplina historico) {

        Professor professor = historico.getProfessor();
        return new HistoricoProfessorTurmaDisciplinaDTO(
                historico.getIdHistoricoProfessor(),
                professor.getIdProfessor(),
                professor.getUsuario() != null ? professor.getUsuario().getNome() : "Professor",
                professor.getRegistro(),
                historico.getDataInicio(),
                historico.getDataFim(),
                historico.getRegistradoEm(),
                historico.getRegistradoPorEmail() == null
                        ? "Não registrado"
                        : historico.getRegistradoPorEmail(),
                historico.getMotivo(),
                historico.getDataInicio() == null
        );
    }

    private void validarProfessorAtivo(Professor professor) {
        if (professor.getUsuario() == null || !Boolean.TRUE.equals(professor.getUsuario().getAtivo())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Não é possível atribuir uma disciplina a um professor com usuário inativo"
            );
        }
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

        boolean possuiNotas = notaRepository
                .existsByTurmaDisciplina_IdTurmaDisciplina(idTurmaDisciplina);
        boolean possuiFaltas = faltaRepository
                .existsByTurmaDisciplina_IdTurmaDisciplina(idTurmaDisciplina);

        if (possuiNotas || possuiFaltas) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Não é possível excluir este vínculo porque existem notas ou faltas registradas. Preserve o histórico acadêmico e, se necessário, altere o professor do vínculo."
            );
        }

        boolean possuiSubstituicaoAnterior = historicoProfessorRepository
                .existsByTurmaDisciplina_IdTurmaDisciplinaAndDataFimIsNotNull(idTurmaDisciplina);

        if (possuiSubstituicaoAnterior) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Não é possível excluir este vínculo porque já houve substituição de professor. Preserve o histórico de responsáveis e mantenha o vínculo acadêmico."
            );
        }

        // Se só existe a atribuição inicial e ainda não há notas ou faltas,
        // o vínculo pode ser removido sem apagar um histórico de substituições.
        historicoProfessorRepository.deleteByTurmaDisciplina_IdTurmaDisciplina(idTurmaDisciplina);

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
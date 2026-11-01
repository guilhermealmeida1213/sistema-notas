package com.guilherme.sistemanotas.service;

import com.guilherme.sistemanotas.dto.AssistenteRespostaDTO;
import com.guilherme.sistemanotas.dto.ResumoDisciplinaDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.util.List;

@Service
public class AssistenteService {

    private final NotaService notaService;
    private final NvidiaAiService nvidiaAiService;

    public AssistenteService(
            NotaService notaService,
            NvidiaAiService nvidiaAiService) {

        this.notaService = notaService;
        this.nvidiaAiService = nvidiaAiService;
    }

    // =========================
    // RESPONDER
    // =========================

    public AssistenteRespostaDTO responder(
            String email,
            String pergunta) {

        if (
                pergunta == null ||
                        pergunta.trim().isEmpty()
        ) {

            return new AssistenteRespostaDTO(
                    pergunta,
                    "Digite uma pergunta para que eu possa ajudar."
            );
        }

        // =========================
        // SEGURANÇA
        // ANTES DE BUSCAR DADOS
        // =========================

        String bloqueioSeguranca =
                validarPerguntaSeguranca(pergunta);

        if (bloqueioSeguranca != null) {

            return new AssistenteRespostaDTO(
                    pergunta,
                    bloqueioSeguranca
            );
        }

        // =========================
        // DADOS DO ALUNO
        // =========================

        List<ResumoDisciplinaDTO> resumos =
                notaService.gerarMeuResumo(email).stream()
                        .filter(resumo -> "ATIVA".equalsIgnoreCase(resumo.getStatusMatricula()))
                        .filter(resumo -> resumo.getFrequencia() != null)
                        .toList();

        String perguntaNormalizada = normalizarPergunta(pergunta);

        // ==================================================
        // RESPOSTAS ACADÊMICAS DIRETAS
        // A linguagem pode variar; os fatos vêm do Java.
        // ==================================================

        if (perguntaSobreFaltas(perguntaNormalizada)) {
            return new AssistenteRespostaDTO(
                    pergunta,
                    responderSobreFaltas(resumos)
            );
        }

        if (perguntaComparacaoDesempenho(perguntaNormalizada)) {
            return new AssistenteRespostaDTO(
                    pergunta,
                    responderMateriaParaMelhorar(resumos)
            );
        }

        if (perguntaSobreMateriasAbaixoDaMedia(perguntaNormalizada)) {
            return new AssistenteRespostaDTO(
                    pergunta,
                    responderMateriasAbaixoDaMedia(resumos)
            );
        }

        if (perguntaSobreNotas(perguntaNormalizada)) {
            return new AssistenteRespostaDTO(
                    pergunta,
                    responderSobreNotas(resumos)
            );
        }

        if (perguntaResumoGeral(perguntaNormalizada)) {
            return new AssistenteRespostaDTO(
                    pergunta,
                    gerarResumoGeral(resumos)
            );
        }

        // ==================================================
        // OUTRAS ANÁLISES DETERMINÍSTICAS
        // ==================================================

        if (
                perguntaProblemaNotaOuFalta(
                        perguntaNormalizada
                )
        ) {

            return new AssistenteRespostaDTO(
                    pergunta,
                    responderProblemaPrincipal(
                            resumos
                    )
            );
        }

        if (
                perguntaPrioridadeEstudos(
                        perguntaNormalizada
                )
        ) {

            return new AssistenteRespostaDTO(
                    pergunta,
                    responderPrioridadeEstudos(
                            resumos
                    )
            );
        }

        // =========================
        // CONTEXTO PARA IA
        // =========================

        String contexto =
                montarContextoAcademico(
                        resumos
                );

        String resposta;

        try {

            resposta =
                    nvidiaAiService
                            .perguntar(
                                    contexto,
                                    pergunta
                            );

        } catch (Exception e) {

            resposta =
                    responderLocalmente(
                            resumos,
                            pergunta
                    );
        }

        return new AssistenteRespostaDTO(
                pergunta,
                resposta
        );
    }

    private String normalizarPergunta(String pergunta) {

        String semAcentos = Normalizer.normalize(
                pergunta == null ? "" : pergunta.toLowerCase().trim(),
                Normalizer.Form.NFD
        ).replaceAll("\\p{M}+", "");

        return semAcentos.replaceAll("[^a-z0-9\\s]", " ")
                .replaceAll("\\s+", " ")
                .trim();
    }

    private boolean perguntaSobreFaltas(String pergunta) {

        boolean mencionaFalta = pergunta.contains("falta")
                || pergunta.contains("faltas")
                || pergunta.contains("frequencia")
                || pergunta.contains("presenca")
                || pergunta.contains("presencas");

        boolean pedeConsulta = pergunta.contains("quantas")
                || pergunta.contains("quanto")
                || pergunta.contains("como")
                || pergunta.contains("minha")
                || pergunta.contains("meu")
                || pergunta.contains("estou")
                || pergunta.contains("to ")
                || pergunta.contains("tenho")
                || pergunta.contains("situacao")
                || pergunta.contains("materia")
                || pergunta.contains("disciplina")
                || pergunta.contains("total")
                || pergunta.contains("muito")
                || pergunta.contains("risco");

        return mencionaFalta && pedeConsulta;
    }

    private boolean perguntaComparacaoDesempenho(String pergunta) {

        boolean pedeComparacao = pergunta.contains("pior")
                || pergunta.contains("menor media")
                || pergunta.contains("media mais baixa")
                || pergunta.contains("nota mais baixa")
                || pergunta.contains("qual materia")
                || pergunta.contains("qual disciplina");

        boolean querOrientacao = pergunta.contains("estudar")
                || pergunta.contains("melhorar")
                || pergunta.contains("focar")
                || pergunta.contains("foco")
                || pergunta.contains("desempenho")
                || pergunta.contains("preciso")
                || pergunta.contains("devo");

        return pedeComparacao && querOrientacao
                || pergunta.contains("em qual materia estou pior")
                || pergunta.contains("em qual disciplina estou pior");
    }

    private boolean perguntaSobreNotas(String pergunta) {

        boolean mencionaNota = pergunta.contains("nota")
                || pergunta.contains("notas")
                || pergunta.contains("media")
                || pergunta.contains("medias")
                || pergunta.contains("desempenho");

        boolean pedeConsulta = pergunta.contains("como")
                || pergunta.contains("quais")
                || pergunta.contains("qual")
                || pergunta.contains("minha")
                || pergunta.contains("meu")
                || pergunta.contains("estou")
                || pergunta.contains("to ")
                || pergunta.contains("tenho")
                || pergunta.contains("cada")
                || pergunta.contains("materia")
                || pergunta.contains("disciplina")
                || pergunta.contains("situacao")
                || pergunta.contains("abaixo")
                || pergunta.contains("melhor")
                || pergunta.contains("pior");

        return mencionaNota && pedeConsulta;
    }

    private boolean perguntaSobreMateriasAbaixoDaMedia(String pergunta) {

        return (pergunta.contains("abaixo da media")
                || pergunta.contains("abaixo da minima")
                || pergunta.contains("reprovada por nota")
                || pergunta.contains("reprovadas por nota")
                || pergunta.contains("quais materias estou reprovado")
                || pergunta.contains("quais disciplinas estou reprovado"))
                && (pergunta.contains("materia")
                || pergunta.contains("disciplina")
                || pergunta.contains("quais")
                || pergunta.contains("qual"));
    }

    private boolean perguntaResumoGeral(String pergunta) {

        return pergunta.contains("resumo academico")
                || pergunta.contains("resumo geral")
                || pergunta.contains("como estou indo")
                || pergunta.contains("como estou no geral")
                || pergunta.contains("meu desempenho geral")
                || pergunta.contains("meu desempenho academico");
    }

    private String responderMateriasAbaixoDaMedia(
            List<ResumoDisciplinaDTO> resumos) {

        List<ResumoDisciplinaDTO> abaixo = resumos.stream()
                .filter(r -> possuiTexto(r.getSituacaoNota(), "REPROVADO"))
                .toList();

        if (abaixo.isEmpty()) {
            return "Pelos resultados registrados no sistema, nenhuma disciplina aparece reprovada por nota. Consulte a média e a situação de cada disciplina para ver os detalhes.";
        }

        StringBuilder resposta = new StringBuilder(
                "Disciplinas com situação de reprovação por nota: "
        );

        for (int i = 0; i < abaixo.size(); i++) {

            ResumoDisciplinaDTO resumo = abaixo.get(i);

            if (i > 0) {
                resposta.append("; ");
            }

            resposta.append(resumo.getDisciplina())
                    .append(" (média ")
                    .append(formatarNota(resumo.getMedia()))
                    .append(")");
        }

        resposta.append(". Usei a situação por nota calculada pelo sistema, sem criar um limite de aprovação novo.");

        return resposta.toString();
    }

    // ==================================================
    // IDENTIFICAR PERGUNTA:
    // NOTA OU FALTA
    // ==================================================

    private boolean perguntaProblemaNotaOuFalta(
            String pergunta) {

        boolean falaProblema =
                pergunta.contains("problema") ||
                        pergunta.contains("preocupar") ||
                        pergunta.contains("atenção") ||
                        pergunta.contains("atencao");

        boolean falaNota =
                pergunta.contains("nota") ||
                        pergunta.contains("média") ||
                        pergunta.contains("media");

        boolean falaFalta =
                pergunta.contains("falta") ||
                        pergunta.contains("frequência") ||
                        pergunta.contains("frequencia");

        return falaProblema &&
                falaNota &&
                falaFalta;
    }

    // ==================================================
    // RESPONDER:
    // PROBLEMA PRINCIPAL
    // ==================================================

    private String responderProblemaPrincipal(
            List<ResumoDisciplinaDTO> resumos) {

        boolean existeProblemaNota = false;
        boolean existeProblemaFrequencia = false;

        for (ResumoDisciplinaDTO resumo : resumos) {

            if (
                    possuiTexto(
                            resumo.getSituacaoNota(),
                            "REPROVADO"
                    )
            ) {

                existeProblemaNota = true;
            }

            if (
                    possuiTexto(
                            resumo.getSituacaoFrequencia(),
                            "REPROVADO"
                    ) ||
                            possuiTexto(
                                    resumo.getSituacaoFrequencia(),
                                    "IRREGULAR"
                            )
            ) {

                existeProblemaFrequencia = true;
            }
        }

        if (
                existeProblemaNota &&
                        existeProblemaFrequencia
        ) {

            ResumoDisciplinaDTO prioritario =
                    encontrarDisciplinaPrioritaria(
                            resumos
                    );

            if (prioritario != null) {

                String resposta =
                        "A disciplina que mais precisa de atenção é "
                                + prioritario.getDisciplina()
                                + ", porque sua situação é "
                                + formatarTexto(
                                prioritario.getSituacao()
                        )
                                + ". Sua média é "
                                + formatarNota(
                                prioritario.getMedia()
                        )
                                + ".";

                String menorAvaliacao =
                        identificarMenorAvaliacao(
                                prioritario
                        );

                if (menorAvaliacao != null) {

                    resposta +=
                            " A avaliação com menor desempenho é "
                                    + menorAvaliacao
                                    + ".";
                }

                return resposta;
            }

            return "Seu desempenho precisa de atenção tanto nas notas quanto na frequência.";
        }

        if (existeProblemaNota) {

            ResumoDisciplinaDTO prioritario =
                    encontrarDisciplinaPrioritaria(
                            resumos
                    );

            if (prioritario != null) {

                return "Seu principal problema acadêmico atual é nota. "
                        + prioritario.getDisciplina()
                        + " está com média "
                        + formatarNota(
                        prioritario.getMedia()
                )
                        + " e situação "
                        + formatarTexto(
                        prioritario.getSituacaoNota()
                )
                        + ". Sua frequência nessa disciplina está "
                        + formatarTexto(
                        prioritario.getSituacaoFrequencia()
                )
                        + ".";
            }

            return "Seu principal problema acadêmico atual é nota.";
        }

        if (existeProblemaFrequencia) {

            return "Seu principal problema acadêmico atual é frequência.";
        }

        return "No momento, o sistema não identificou reprovação por nota nem problema de frequência.";
    }

    // ==================================================
    // IDENTIFICAR PERGUNTA:
    // PRIORIDADE DE ESTUDOS
    // ==================================================

    private boolean perguntaPrioridadeEstudos(
            String pergunta) {

        return pergunta.contains("priorizar") ||
                pergunta.contains("prioridade") ||
                pergunta.contains("onde focar") ||
                pergunta.contains("no que focar") ||
                pergunta.contains("o que estudar primeiro");
    }

    // ==================================================
    // RESPONDER:
    // PRIORIDADE DE ESTUDOS
    // ==================================================

    private String responderPrioridadeEstudos(
            List<ResumoDisciplinaDTO> resumos) {

        ResumoDisciplinaDTO prioritario =
                encontrarDisciplinaPrioritaria(
                        resumos
                );

        if (prioritario == null) {

            return "Ainda não existem dados suficientes para definir uma prioridade de estudos.";
        }

        String resposta =
                "Priorize "
                        + prioritario.getDisciplina()
                        + ". Sua média é "
                        + formatarNota(
                        prioritario.getMedia()
                )
                        + " e sua situação geral é "
                        + formatarTexto(
                        prioritario.getSituacao()
                )
                        + ".";

        String menorAvaliacao =
                identificarMenorAvaliacao(
                        prioritario
                );

        if (menorAvaliacao != null) {

            resposta +=
                    " A avaliação com menor desempenho é "
                            + menorAvaliacao
                            + ".";
        }

        if (
                "FREQUÊNCIA REGULAR".equalsIgnoreCase(
                        prioritario.getSituacaoFrequencia()
                )
        ) {

            resposta +=
                    " Sua frequência está regular, então o principal ponto de atenção está no desempenho das avaliações.";
        }

        return resposta;
    }

    // =========================
    // SEGURANÇA DO ASSISTENTE
    // =========================

    private String validarPerguntaSeguranca(
            String pergunta) {

        String texto =
                pergunta
                        .toLowerCase()
                        .trim();

        // =========================
        // PROMPT / REGRAS INTERNAS
        // =========================

        if (
                texto.contains("prompt") ||
                        texto.contains("instrução interna") ||
                        texto.contains("instruções internas") ||
                        texto.contains("instrucao interna") ||
                        texto.contains("instrucoes internas") ||
                        texto.contains("regras internas") ||
                        texto.contains("regras que você recebeu") ||
                        texto.contains("regras que voce recebeu") ||
                        texto.contains("texto que define seu comportamento") ||
                        texto.contains("como você foi instruído") ||
                        texto.contains("como voce foi instruido") ||
                        texto.contains("como você funciona internamente") ||
                        texto.contains("como voce funciona internamente")
        ) {

            return "Não posso fornecer prompts, instruções internas ou configurações do sistema.";
        }

        // =========================
        // IGNORAR REGRAS
        // =========================

        if (
                texto.contains("ignore as regras") ||
                        texto.contains("ignore todas as regras") ||
                        texto.contains("ignore as instruções") ||
                        texto.contains("ignore as instrucoes") ||
                        texto.contains("ignore todas as instruções") ||
                        texto.contains("ignore todas as instrucoes") ||
                        texto.contains("esqueça as regras") ||
                        texto.contains("esqueca as regras") ||
                        texto.contains("desconsidere as regras") ||
                        texto.contains("desconsidere as instruções") ||
                        texto.contains("desconsidere as instrucoes")
        ) {

            return "Não posso ignorar as regras de segurança ou alterar o funcionamento do sistema.";
        }

        // =========================
        // ELEVAÇÃO DE PRIVILÉGIO
        // =========================

        if (
                texto.contains("finja que sou professor") ||
                        texto.contains("finja que eu sou professor") ||
                        texto.contains("finja que sou administrador") ||
                        texto.contains("finja que eu sou administrador") ||
                        texto.contains("assuma o papel de professor") ||
                        texto.contains("assuma o papel de administrador") ||
                        texto.contains("papel de administrador") ||
                        texto.contains("papel de professor") ||
                        texto.contains("permissão total") ||
                        texto.contains("permissao total") ||
                        texto.contains("acesso total") ||
                        texto.contains("acesso de administrador") ||
                        texto.contains("privilégio de administrador") ||
                        texto.contains("privilegio de administrador")
        ) {

            return "Seu acesso permanece limitado ao perfil autenticado no sistema.";
        }

        // =========================
        // CREDENCIAIS
        // =========================

        if (
                texto.contains("senha") ||
                        texto.contains("credencial") ||
                        texto.contains("token de acesso") ||
                        texto.contains("token do usuário") ||
                        texto.contains("token do usuario")
        ) {

            return "Não posso fornecer senhas, credenciais ou informações de autenticação.";
        }

        // =========================
        // DADOS DE OUTROS USUÁRIOS
        // =========================

        if (
                texto.contains("outro aluno") ||
                        texto.contains("outros alunos") ||
                        texto.contains("todos os alunos") ||
                        texto.contains("alunos cadastrados") ||
                        texto.contains("dados dos alunos") ||
                        texto.contains("dados de alunos") ||
                        texto.contains("notas dos alunos")
        ) {

            return "Não posso fornecer dados acadêmicos de outros usuários.";
        }

        // =========================
        // ALTERAÇÃO DE DADOS
        // =========================

        boolean mencionaAlteracao =
                texto.contains("altere") ||
                        texto.contains("alterar") ||
                        texto.contains("mude") ||
                        texto.contains("mudar") ||
                        texto.contains("modifique") ||
                        texto.contains("modificar") ||
                        texto.contains("apague") ||
                        texto.contains("apagar");

        boolean mencionaDadoAcademico =
                texto.contains("nota") ||
                        texto.contains("média") ||
                        texto.contains("media") ||
                        texto.contains("falta") ||
                        texto.contains("frequência") ||
                        texto.contains("frequencia") ||
                        texto.contains("situação") ||
                        texto.contains("situacao");

        if (
                mencionaAlteracao &&
                        mencionaDadoAcademico
        ) {

            return "O assistente pode consultar dados acadêmicos, mas não pode alterá-los.";
        }

        return null;
    }

    // =========================
    // FALLBACK LOCAL
    // =========================

    private String responderLocalmente(
            List<ResumoDisciplinaDTO> resumos,
            String pergunta) {

        String perguntaNormalizada = normalizarPergunta(pergunta);

        ResumoDisciplinaDTO disciplinaEncontrada =
                buscarDisciplinaNaPergunta(
                        resumos,
                        perguntaNormalizada
                );

        if (disciplinaEncontrada != null) {

            return responderSobreDisciplina(
                    disciplinaEncontrada,
                    perguntaNormalizada
            );
        }

        boolean perguntaNota =
                perguntaNormalizada.contains("nota") ||
                        perguntaNormalizada.contains("média") ||
                        perguntaNormalizada.contains("media");

        boolean perguntaFalta =
                perguntaNormalizada.contains("falta") ||
                        perguntaNormalizada.contains("frequência") ||
                        perguntaNormalizada.contains("frequencia");

        if (
                perguntaNota &&
                        perguntaFalta
        ) {

            return gerarResumoGeral(resumos);
        }

        if (perguntaNota) {

            return responderSobreNotas(resumos);
        }

        if (perguntaFalta) {

            return responderSobreFaltas(resumos);
        }

        if (
                perguntaNormalizada.contains("risco") ||
                        perguntaNormalizada.contains("reprova") ||
                        perguntaNormalizada.contains("situação") ||
                        perguntaNormalizada.contains("situacao") ||
                        perguntaNormalizada.contains("aprovado")
        ) {

            return responderSobreSituacao(resumos);
        }

        if (
                perguntaNormalizada.contains("melhorar") ||
                        perguntaNormalizada.contains("pior") ||
                        perguntaNormalizada.contains("mais baixa") ||
                        perguntaNormalizada.contains("atenção") ||
                        perguntaNormalizada.contains("atencao") ||
                        perguntaNormalizada.contains("priorizar")
        ) {

            return responderMateriaParaMelhorar(resumos);
        }

        return gerarResumoGeral(resumos);
    }

    // =========================
    // IDENTIFICAR DISCIPLINA
    // =========================

    private ResumoDisciplinaDTO buscarDisciplinaNaPergunta(
            List<ResumoDisciplinaDTO> resumos,
            String pergunta) {

        for (ResumoDisciplinaDTO resumo : resumos) {

            if (resumo.getDisciplina() == null) {
                continue;
            }

            String nomeDisciplina =
                    resumo
                            .getDisciplina()
                            .toLowerCase();

            if (
                    pergunta.contains(
                            nomeDisciplina
                    )
            ) {

                return resumo;
            }

            String[] palavras =
                    nomeDisciplina.split(" ");

            for (String palavra : palavras) {

                if (
                        palavra.length() >= 5 &&
                                pergunta.contains(palavra)
                ) {

                    return resumo;
                }
            }
        }

        return null;
    }

    // =========================
    // DISCIPLINA ESPECÍFICA
    // =========================

    private String responderSobreDisciplina(
            ResumoDisciplinaDTO resumo,
            String pergunta) {

        String disciplina =
                resumo.getDisciplina();

        if (pergunta.contains("falta")) {

            return "Em "
                    + disciplina
                    + ", você possui "
                    + resumo.getFaltas()
                    + " faltas, frequência de "
                    + formatarPercentual(
                    resumo.getFrequencia()
            )
                    + "% e situação de frequência: "
                    + formatarTexto(
                    resumo.getSituacaoFrequencia()
            )
                    + ".";
        }

        if (
                pergunta.contains("frequência") ||
                        pergunta.contains("frequencia")
        ) {

            return "Sua frequência em "
                    + disciplina
                    + " é de "
                    + formatarPercentual(
                    resumo.getFrequencia()
            )
                    + "%. Situação de frequência: "
                    + formatarTexto(
                    resumo.getSituacaoFrequencia()
            )
                    + ".";
        }

        if (
                pergunta.contains("situação") ||
                        pergunta.contains("situacao") ||
                        pergunta.contains("aprovado") ||
                        pergunta.contains("reprovado") ||
                        pergunta.contains("risco")
        ) {

            return "Sua situação geral em "
                    + disciplina
                    + " é "
                    + formatarTexto(
                    resumo.getSituacao()
            )
                    + ". Situação por nota: "
                    + formatarTexto(
                    resumo.getSituacaoNota()
            )
                    + ". Situação de frequência: "
                    + formatarTexto(
                    resumo.getSituacaoFrequencia()
            )
                    + ".";
        }

        if (
                pergunta.contains("nota") ||
                        pergunta.contains("média") ||
                        pergunta.contains("media")
        ) {

            return "Sua média em "
                    + disciplina
                    + " é "
                    + formatarNota(
                    resumo.getMedia()
            )
                    + ". AVC: "
                    + formatarNota(
                    resumo.getAvc()
            )
                    + ", AVG: "
                    + formatarNota(
                    resumo.getAvg()
            )
                    + " e AVI: "
                    + formatarNota(
                    resumo.getAvi()
            )
                    + ". Situação por nota: "
                    + formatarTexto(
                    resumo.getSituacaoNota()
            )
                    + ".";
        }

        return disciplina
                + ": média "
                + formatarNota(
                resumo.getMedia()
        )
                + ", "
                + resumo.getFaltas()
                + " faltas, frequência de "
                + formatarPercentual(
                resumo.getFrequencia()
        )
                + "% e situação geral "
                + formatarTexto(
                resumo.getSituacao()
        )
                + ".";
    }

    // =========================
    // NOTAS GERAIS
    // =========================

    private String responderSobreNotas(
            List<ResumoDisciplinaDTO> resumos) {

        StringBuilder resposta =
                new StringBuilder(
                        "Resumo das suas notas: "
                );

        for (ResumoDisciplinaDTO resumo : resumos) {

            resposta.append(
                    resumo.getDisciplina()
            );

            resposta.append(
                    ": média "
            );

            resposta.append(
                    formatarNota(
                            resumo.getMedia()
                    )
            );

            resposta.append(
                    " - "
            );

            resposta.append(
                    formatarTexto(
                            resumo.getSituacaoNota()
                    )
            );

            resposta.append(
                    ". "
            );
        }

        return resposta
                .toString()
                .trim();
    }

    // =========================
    // FALTAS GERAIS
    // =========================

    private String responderSobreFaltas(
            List<ResumoDisciplinaDTO> resumos) {

        StringBuilder resposta =
                new StringBuilder(
                        "Sua frequência por disciplina: "
                );

        for (ResumoDisciplinaDTO resumo : resumos) {

            resposta.append(
                    resumo.getDisciplina()
            );

            resposta.append(
                    ": "
            );

            resposta.append(
                    resumo.getFaltas()
            );

            resposta.append(
                    " faltas, "
            );

            resposta.append(
                    formatarPercentual(
                            resumo.getFrequencia()
                    )
            );

            resposta.append(
                    "% de frequência - "
            );

            resposta.append(
                    formatarTexto(
                            resumo.getSituacaoFrequencia()
                    )
            );

            resposta.append(
                    ". "
            );
        }

        return resposta
                .toString()
                .trim();
    }

    // =========================
    // SITUAÇÃO GERAL
    // =========================

    private String responderSobreSituacao(
            List<ResumoDisciplinaDTO> resumos) {

        StringBuilder resposta =
                new StringBuilder(
                        "Sua situação acadêmica atual é: "
                );

        for (ResumoDisciplinaDTO resumo : resumos) {

            resposta.append(
                    resumo.getDisciplina()
            );

            resposta.append(
                    ": "
            );

            resposta.append(
                    formatarTexto(
                            resumo.getSituacao()
                    )
            );

            resposta.append(
                    ". "
            );
        }

        return resposta
                .toString()
                .trim();
    }

    // =========================
    // MATÉRIA PARA MELHORAR
    // =========================

    private String responderMateriaParaMelhorar(
            List<ResumoDisciplinaDTO> resumos) {

        // Ordena somente as disciplinas que possuem média registrada.
        List<ResumoDisciplinaDTO> ordenadas = resumos.stream()
                .filter(resumo -> resumo.getMedia() != null)
                .sorted((a, b) -> a.getMedia().compareTo(b.getMedia()))
                .toList();

        if (ordenadas.isEmpty()) {
            return "Ainda não existem médias registradas suficientes para comparar suas disciplinas.";
        }

        ResumoDisciplinaDTO prioritario = ordenadas.get(0);

        StringBuilder resposta = new StringBuilder();

        resposta.append(
                        "Comparando as médias registradas, a disciplina que mais precisa de atenção é "
                )
                .append(prioritario.getDisciplina())
                .append(", com média ")
                .append(formatarNota(prioritario.getMedia()))
                .append(".");

        if (ordenadas.size() > 1) {

            resposta.append(
                    " Para comparar, suas médias da menor para a maior são: "
            );

            for (int i = 0; i < ordenadas.size(); i++) {

                ResumoDisciplinaDTO resumo = ordenadas.get(i);

                if (i > 0) {
                    resposta.append("; ");
                }

                resposta.append(resumo.getDisciplina())
                        .append(" (")
                        .append(formatarNota(resumo.getMedia()))
                        .append(")");
            }

            resposta.append(".");
        }

        resposta.append(
                        " Eu começaria revisando "
                )
                .append(prioritario.getDisciplina())
                .append(
                        " e depois seguiria para a próxima menor média. Essa comparação considera as médias disponíveis e não substitui a situação oficial calculada pelo sistema."
                );

        String menorAvaliacao = identificarMenorAvaliacao(prioritario);

        if (menorAvaliacao != null) {

            resposta.append(
                            " Na disciplina prioritária, a avaliação com menor desempenho é "
                    )
                    .append(menorAvaliacao)
                    .append(".");
        }

        if ("FREQUÊNCIA REGULAR".equalsIgnoreCase(prioritario.getSituacaoFrequencia())) {

            resposta.append(
                    " Sua frequência nessa disciplina está regular."
            );
        }

        return resposta.toString();
    }

    // =========================
    // RESUMO GERAL
    // =========================

    private String gerarResumoGeral(
            List<ResumoDisciplinaDTO> resumos) {

        int totalFaltas = 0;
        int reprovacoes = 0;

        BigDecimal somaMedias =
                BigDecimal.ZERO;

        int quantidadeMedias = 0;

        for (ResumoDisciplinaDTO resumo : resumos) {

            totalFaltas +=
                    resumo.getFaltas() != null
                            ? resumo.getFaltas()
                            : 0;

            if (
                    possuiTexto(
                            resumo.getSituacao(),
                            "REPROVADO"
                    )
            ) {

                reprovacoes++;
            }

            if (resumo.getMedia() != null) {

                somaMedias =
                        somaMedias.add(
                                resumo.getMedia()
                        );

                quantidadeMedias++;
            }
        }

        BigDecimal mediaGeral =
                quantidadeMedias > 0
                        ? somaMedias.divide(
                        BigDecimal.valueOf(
                                quantidadeMedias
                        ),
                        2,
                        java.math.RoundingMode.HALF_UP
                )
                        : null;

        String resposta =
                "Seu resumo acadêmico atual: ";

        if (mediaGeral != null) {

            resposta +=
                    "média geral "
                            + mediaGeral
                            + ", ";
        }

        resposta +=
                totalFaltas
                        + " faltas no total";

        if (reprovacoes > 0) {

            resposta +=
                    " e "
                            + reprovacoes
                            + " disciplina(s) em situação de reprovação.";
        } else {

            resposta +=
                    " e nenhuma disciplina em situação de reprovação.";
        }

        return resposta;
    }

    // ==================================================
    // ANÁLISE DETERMINÍSTICA
    // O JAVA DEFINE OS FATOS
    // ==================================================

    private String montarAnaliseDeterministica(
            List<ResumoDisciplinaDTO> resumos) {

        StringBuilder analise =
                new StringBuilder();

        boolean existeReprovacaoNota = false;
        boolean existeProblemaFrequencia = false;

        for (ResumoDisciplinaDTO resumo : resumos) {

            if (
                    possuiTexto(
                            resumo.getSituacaoNota(),
                            "REPROVADO"
                    )
            ) {

                existeReprovacaoNota = true;
            }

            if (
                    possuiTexto(
                            resumo.getSituacaoFrequencia(),
                            "REPROVADO"
                    ) ||
                            possuiTexto(
                                    resumo.getSituacaoFrequencia(),
                                    "IRREGULAR"
                            )
            ) {

                existeProblemaFrequencia = true;
            }
        }

        String problemaPrioritario;

        if (
                existeReprovacaoNota &&
                        existeProblemaFrequencia
        ) {

            problemaPrioritario =
                    "NOTA E FREQUÊNCIA";

        } else if (existeReprovacaoNota) {

            problemaPrioritario =
                    "NOTA";

        } else if (existeProblemaFrequencia) {

            problemaPrioritario =
                    "FREQUÊNCIA";

        } else {

            problemaPrioritario =
                    "NENHUM PROBLEMA CRÍTICO IDENTIFICADO";
        }

        analise.append(
                "ANÁLISE ACADÊMICA CALCULADA PELO JAVA\n"
        );

        analise.append(
                "Problema acadêmico prioritário: "
        );

        analise.append(
                problemaPrioritario
        );

        analise.append("\n");

        analise.append(
                "Existe reprovação por nota: "
        );

        analise.append(
                existeReprovacaoNota
                        ? "SIM"
                        : "NÃO"
        );

        analise.append("\n");

        analise.append(
                "Existe problema de frequência: "
        );

        analise.append(
                existeProblemaFrequencia
                        ? "SIM"
                        : "NÃO"
        );

        analise.append("\n");

        ResumoDisciplinaDTO prioritario =
                encontrarDisciplinaPrioritaria(
                        resumos
                );

        if (prioritario != null) {

            analise.append(
                    "Disciplina prioritária: "
            );

            analise.append(
                    prioritario.getDisciplina()
            );

            analise.append("\n");

            analise.append(
                    "Motivo principal da prioridade: "
            );

            analise.append(
                    identificarMotivoPrincipal(
                            prioritario
                    )
            );

            analise.append("\n");

            analise.append(
                    "Média da disciplina prioritária: "
            );

            analise.append(
                    formatarNota(
                            prioritario.getMedia()
                    )
            );

            analise.append("\n");

            analise.append(
                    "Situação por nota da disciplina prioritária: "
            );

            analise.append(
                    formatarTexto(
                            prioritario.getSituacaoNota()
                    )
            );

            analise.append("\n");

            analise.append(
                    "Situação de frequência da disciplina prioritária: "
            );

            analise.append(
                    formatarTexto(
                            prioritario.getSituacaoFrequencia()
                    )
            );

            analise.append("\n");

            String menorAvaliacao =
                    identificarMenorAvaliacao(
                            prioritario
                    );

            if (menorAvaliacao != null) {

                analise.append(
                        "Avaliação com menor desempenho: "
                );

                analise.append(
                        menorAvaliacao
                );

                analise.append("\n");
            }
        }

        analise.append(
                "==================================================\n"
        );

        return analise.toString();
    }

    // =========================
    // MENOR MÉDIA REGISTRADA
    // =========================

    private ResumoDisciplinaDTO encontrarDisciplinaComMenorMedia(
            List<ResumoDisciplinaDTO> resumos) {

        ResumoDisciplinaDTO menorMedia = null;

        for (ResumoDisciplinaDTO resumo : resumos) {

            if (resumo.getMedia() == null) {
                continue;
            }

            if (menorMedia == null || mediaMenor(resumo, menorMedia)) {
                menorMedia = resumo;
            }
        }

        return menorMedia;
    }

    // =========================
    // DISCIPLINA PRIORITÁRIA
    // =========================

    private ResumoDisciplinaDTO encontrarDisciplinaPrioritaria(
            List<ResumoDisciplinaDTO> resumos) {

        ResumoDisciplinaDTO prioritario =
                null;

        // Primeiro procura disciplinas reprovadas
        for (ResumoDisciplinaDTO resumo : resumos) {

            if (
                    possuiTexto(
                            resumo.getSituacao(),
                            "REPROVADO"
                    )
            ) {

                if (
                        prioritario == null ||
                                mediaMenor(
                                        resumo,
                                        prioritario
                                )
                ) {

                    prioritario = resumo;
                }
            }
        }

        if (prioritario != null) {
            return prioritario;
        }

        // Se ninguém estiver reprovado,
        // utiliza a menor média disponível
        for (ResumoDisciplinaDTO resumo : resumos) {

            if (resumo.getMedia() == null) {
                continue;
            }

            if (
                    prioritario == null ||
                            mediaMenor(
                                    resumo,
                                    prioritario
                            )
            ) {

                prioritario = resumo;
            }
        }

        return prioritario;
    }

    // =========================
    // COMPARAR MÉDIAS
    // =========================

    private boolean mediaMenor(
            ResumoDisciplinaDTO candidato,
            ResumoDisciplinaDTO atual) {

        if (candidato.getMedia() == null) {
            return false;
        }

        if (atual.getMedia() == null) {
            return true;
        }

        return candidato
                .getMedia()
                .compareTo(
                        atual.getMedia()
                ) < 0;
    }

    // =========================
    // MOTIVO PRINCIPAL
    // =========================

    private String identificarMotivoPrincipal(
            ResumoDisciplinaDTO resumo) {

        boolean problemaNota =
                possuiTexto(
                        resumo.getSituacaoNota(),
                        "REPROVADO"
                );

        boolean problemaFrequencia =
                possuiTexto(
                        resumo.getSituacaoFrequencia(),
                        "REPROVADO"
                ) ||
                        possuiTexto(
                                resumo.getSituacaoFrequencia(),
                                "IRREGULAR"
                        );

        if (
                problemaNota &&
                        problemaFrequencia
        ) {

            return "NOTA E FREQUÊNCIA";
        }

        if (problemaNota) {

            return "NOTA";
        }

        if (problemaFrequencia) {

            return "FREQUÊNCIA";
        }

        return "MENOR DESEMPENHO ENTRE AS DISCIPLINAS";
    }

    // =========================
    // MENOR AVALIAÇÃO
    // =========================

    private String identificarMenorAvaliacao(
            ResumoDisciplinaDTO resumo) {

        String tipo = null;
        BigDecimal menor = null;

        if (resumo.getAvc() != null) {

            tipo = "AVC";
            menor = resumo.getAvc();
        }

        if (
                resumo.getAvg() != null &&
                        (
                                menor == null ||
                                        resumo
                                                .getAvg()
                                                .compareTo(menor) < 0
                        )
        ) {

            tipo = "AVG";
            menor = resumo.getAvg();
        }

        if (
                resumo.getAvi() != null &&
                        (
                                menor == null ||
                                        resumo
                                                .getAvi()
                                                .compareTo(menor) < 0
                        )
        ) {

            tipo = "AVI";
            menor = resumo.getAvi();
        }

        if (
                tipo == null ||
                        menor == null
        ) {

            return null;
        }

        return tipo
                + " = "
                + formatarNota(
                menor
        );
    }

    // =========================
    // VERIFICAR TEXTO
    // =========================

    private boolean possuiTexto(
            String texto,
            String trecho) {

        if (
                texto == null ||
                        trecho == null
        ) {

            return false;
        }

        return texto
                .toUpperCase()
                .contains(
                        trecho.toUpperCase()
                );
    }

    // =========================
    // CONTEXTO PARA IA
    // =========================

    private String montarContextoAcademico(
            List<ResumoDisciplinaDTO> resumos) {

        StringBuilder contexto =
                new StringBuilder();

        contexto.append(
                "IMPORTANTE: todos os valores e situações abaixo foram calculados pelo sistema Java.\n"
        );

        contexto.append(
                "A IA deve apenas interpretar estes resultados.\n"
        );

        contexto.append(
                "A IA não deve substituir, recalcular ou contradizer a análise feita pelo Java.\n"
        );

        contexto.append(
                "==================================================\n"
        );

        // =========================
        // ANÁLISE PRONTA DO JAVA
        // =========================

        contexto.append(
                montarAnaliseDeterministica(
                        resumos
                )
        );

        // =========================
        // DADOS DETALHADOS
        // =========================

        contexto.append(
                "DADOS DETALHADOS DAS DISCIPLINAS\n"
        );

        contexto.append(
                "==================================================\n"
        );

        for (ResumoDisciplinaDTO resumo : resumos) {

            contexto.append(
                    "Disciplina: "
            );

            contexto.append(
                    resumo.getDisciplina()
            );

            contexto.append("\n");

            contexto.append(
                    "AVC registrada no sistema: "
            );

            contexto.append(
                    formatarNota(
                            resumo.getAvc()
                    )
            );

            contexto.append("\n");

            contexto.append(
                    "AVG registrada no sistema: "
            );

            contexto.append(
                    formatarNota(
                            resumo.getAvg()
                    )
            );

            contexto.append("\n");

            contexto.append(
                    "AVI registrada no sistema: "
            );

            contexto.append(
                    formatarNota(
                            resumo.getAvi()
                    )
            );

            contexto.append("\n");

            contexto.append(
                    "Média calculada pelo sistema: "
            );

            contexto.append(
                    formatarNota(
                            resumo.getMedia()
                    )
            );

            contexto.append("\n");

            contexto.append(
                    "Situação por nota calculada pelo sistema: "
            );

            contexto.append(
                    formatarTexto(
                            resumo.getSituacaoNota()
                    )
            );

            contexto.append("\n");

            contexto.append(
                    "Quantidade de faltas registrada no sistema: "
            );

            contexto.append(
                    resumo.getFaltas() != null
                            ? resumo.getFaltas()
                            : 0
            );

            contexto.append("\n");

            contexto.append(
                    "Frequência calculada pelo sistema: "
            );

            contexto.append(
                    formatarPercentual(
                            resumo.getFrequencia()
                    )
            );

            contexto.append("%\n");

            contexto.append(
                    "Situação de frequência calculada pelo sistema: "
            );

            contexto.append(
                    formatarTexto(
                            resumo.getSituacaoFrequencia()
                    )
            );

            contexto.append("\n");

            contexto.append(
                    "Situação geral calculada pelo sistema: "
            );

            contexto.append(
                    formatarTexto(
                            resumo.getSituacao()
                    )
            );

            contexto.append("\n");

            contexto.append(
                    "--------------------------------------------------\n"
            );
        }

        return contexto.toString();
    }

    // =========================
    // FORMATAR NOTA
    // =========================

    private String formatarNota(
            BigDecimal nota) {

        if (nota == null) {

            return "não disponível";
        }

        return nota
                .stripTrailingZeros()
                .toPlainString();
    }

    // =========================
    // FORMATAR PERCENTUAL
    // =========================

    private String formatarPercentual(
            BigDecimal valor) {

        if (valor == null) {

            return "não disponível";
        }

        return valor
                .stripTrailingZeros()
                .toPlainString();
    }

    // =========================
    // FORMATAR TEXTO
    // =========================

    private String formatarTexto(
            String texto) {

        if (
                texto == null ||
                        texto.isBlank()
        ) {

            return "não disponível";
        }

        return texto;
    }
}
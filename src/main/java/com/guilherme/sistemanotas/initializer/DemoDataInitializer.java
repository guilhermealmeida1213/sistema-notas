package com.guilherme.sistemanotas.initializer;

import com.guilherme.sistemanotas.model.*;
import com.guilherme.sistemanotas.repository.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component
public class DemoDataInitializer implements CommandLineRunner {

    private final boolean demoDataEnabled;
    private final String demoPassword;

    private final PasswordEncoder passwordEncoder;

    private final UsuarioRepository usuarioRepository;
    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;
    private final TurmaRepository turmaRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final TurmaDisciplinaRepository turmaDisciplinaRepository;
    private final MatriculaRepository matriculaRepository;
    private final NotaRepository notaRepository;
    private final FaltaRepository faltaRepository;

    public DemoDataInitializer(
            @Value("${demo.data.enabled:false}") boolean demoDataEnabled,
            @Value("${DEMO_PASSWORD:}") String demoPassword,
            PasswordEncoder passwordEncoder,
            UsuarioRepository usuarioRepository,
            AlunoRepository alunoRepository,
            ProfessorRepository professorRepository,
            TurmaRepository turmaRepository,
            DisciplinaRepository disciplinaRepository,
            TurmaDisciplinaRepository turmaDisciplinaRepository,
            MatriculaRepository matriculaRepository,
            NotaRepository notaRepository,
            FaltaRepository faltaRepository) {

        this.demoDataEnabled = demoDataEnabled;
        this.demoPassword = demoPassword;
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
        this.turmaRepository = turmaRepository;
        this.disciplinaRepository = disciplinaRepository;
        this.turmaDisciplinaRepository = turmaDisciplinaRepository;
        this.matriculaRepository = matriculaRepository;
        this.notaRepository = notaRepository;
        this.faltaRepository = faltaRepository;
    }

    @Override
    public void run(String... args) {

        if (!demoDataEnabled) {
            return;
        }

        validarConfiguracao();

        criarAdministrador();

        System.out.println("=========================================");
        System.out.println(" INICIANDO DADOS DE DEMONSTRAÇÃO");
        System.out.println("=========================================");

        // =====================================================
        // 1. TURMAS
        // =====================================================

        Turma turma4AdsA = criarTurmaSeNaoExistir(
                "4ADS-A",
                2,
                2026
        );

        Turma turma3AdsB = criarTurmaSeNaoExistir(
                "3ADS-B",
                2,
                2026
        );

        Turma turma2AdsC = criarTurmaSeNaoExistir(
                "2ADS-C",
                2,
                2026
        );

        // =====================================================
        // 2. DISCIPLINAS
        // =====================================================

        Disciplina tecnologiasEmergentes =
                criarDisciplinaSeNaoExistir(
                        "Tecnologias Emergentes",
                        80
                );

        Disciplina tecnicasProgramacao =
                criarDisciplinaSeNaoExistir(
                        "Técnicas de Programação",
                        80
                );

        Disciplina governanca =
                criarDisciplinaSeNaoExistir(
                        "Governança em Tecnologia da Informação",
                        80
                );

        Disciplina bancoDados =
                criarDisciplinaSeNaoExistir(
                        "Banco de Dados",
                        80
                );

        Disciplina analiseProjeto =
                criarDisciplinaSeNaoExistir(
                        "Análise e Projeto de Sistemas",
                        80
                );

        Disciplina poo =
                criarDisciplinaSeNaoExistir(
                        "POO (Programação Orientada a Objetos)",
                        80
                );

        Disciplina logica =
                criarDisciplinaSeNaoExistir(
                        "Lógica de Programação",
                        80
                );

        Disciplina engenhariaSoftware =
                criarDisciplinaSeNaoExistir(
                        "Engenharia de Software",
                        80
                );

        Disciplina dataScience =
                criarDisciplinaSeNaoExistir(
                        "Data Science e Big Data",
                        80
                );

        // =====================================================
        // 3. PROFESSORES
        // =====================================================

        Professor rafael = criarProfessorSeNaoExistir(
                "Rafael Guem",
                "rafael.guem@professor.unisa.br",
                "PROF0001"
        );

        Professor angelo = criarProfessorSeNaoExistir(
                "Angelo Luiz",
                "angelo.luiz@professor.unisa.br",
                "PROF0002"
        );

        Professor dirk = criarProfessorSeNaoExistir(
                "Dirk de Mello",
                "dirk.mello@professor.unisa.br",
                "PROF0003"
        );

        Professor rubens = criarProfessorSeNaoExistir(
                "Rubens Zampar",
                "rubens.zampar@professor.unisa.br",
                "PROF0004"
        );

        Professor amanda = criarProfessorSeNaoExistir(
                "Amanda da Silva",
                "amanda.silva@professor.unisa.br",
                "PROF0005"
        );

        Professor ingrid = criarProfessorSeNaoExistir(
                "Ingrid Batista",
                "ingrid.batista@professor.unisa.br",
                "PROF0006"
        );

        // =====================================================
        // 4. VÍNCULOS TURMA + DISCIPLINA + PROFESSOR
        // =====================================================

        criarVinculoSeNaoExistir(
                turma4AdsA,
                tecnologiasEmergentes,
                rafael
        );

        criarVinculoSeNaoExistir(
                turma4AdsA,
                tecnicasProgramacao,
                angelo
        );

        criarVinculoSeNaoExistir(
                turma4AdsA,
                governanca,
                dirk
        );

        criarVinculoSeNaoExistir(
                turma3AdsB,
                bancoDados,
                rubens
        );

        criarVinculoSeNaoExistir(
                turma3AdsB,
                analiseProjeto,
                amanda
        );

        criarVinculoSeNaoExistir(
                turma3AdsB,
                poo,
                angelo
        );

        criarVinculoSeNaoExistir(
                turma2AdsC,
                logica,
                rafael
        );

        criarVinculoSeNaoExistir(
                turma2AdsC,
                engenhariaSoftware,
                ingrid
        );

        criarVinculoSeNaoExistir(
                turma2AdsC,
                dataScience,
                rubens
        );

        // =====================================================
        // 5. ALUNOS
        // =====================================================

        String[] alunos4AdsA = {
                "Guilherme Almeida",
                "Ana Beatriz Martins",
                "Arthur Henrique Lima",
                "Beatriz Oliveira Santos",
                "Bruno Gabriel Costa",
                "Camila Rodrigues Alves",
                "Daniel Henrique Souza",
                "Eduarda Fernandes Lima",
                "Felipe Augusto Martins",
                "Gabriela Moreira Santos",
                "Gustavo Henrique Rocha",
                "Isabela Cristina Alves",
                "João Pedro Ferreira",
                "Júlia Martins Oliveira",
                "Leonardo Gabriel Souza",
                "Letícia Rodrigues Costa",
                "Lucas Henrique Almeida",
                "Mariana Fernandes Santos",
                "Pedro Henrique Lima",
                "Vitória Oliveira Rocha"
        };

        String[] alunos3AdsB = {
                "Alice Ferreira Costa",
                "Amanda Rodrigues Lima",
                "André Luiz Santos",
                "Bianca Martins Souza",
                "Caio Henrique Oliveira",
                "Carolina Fernandes Rocha",
                "Diego Gabriel Alves",
                "Elisa Moreira Santos",
                "Gabriel Henrique Costa",
                "Giovanna Rodrigues Lima",
                "Henrique Augusto Souza",
                "Larissa Martins Oliveira",
                "Matheus Gabriel Rocha",
                "Melissa Fernandes Alves",
                "Nicolas Henrique Santos",
                "Rafaela Cristina Lima",
                "Samuel Oliveira Costa",
                "Sofia Rodrigues Martins",
                "Thiago Henrique Souza",
                "Yasmin Ferreira Rocha"
        };

        String[] alunos2AdsC = {
                "Adriana Martins Costa",
                "Alexandre Henrique Lima",
                "Bárbara Oliveira Rocha",
                "Bernardo Gabriel Santos",
                "Clara Fernandes Alves",
                "Davi Henrique Souza",
                "Emanuel Rodrigues Costa",
                "Esther Martins Lima",
                "Fernando Augusto Rocha",
                "Helena Cristina Santos",
                "Igor Henrique Alves",
                "Laura Fernandes Costa",
                "Miguel Gabriel Oliveira",
                "Natália Rodrigues Souza",
                "Rafael Henrique Martins",
                "Rebeca Oliveira Lima",
                "Theo Gabriel Costa",
                "Valentina Fernandes Rocha",
                "Vinícius Henrique Santos",
                "Zoe Martins Alves"
        };

        criarAlunosDaTurma(
                alunos4AdsA,
                2026001,
                turma4AdsA,
                3,
                4
        );

        criarAlunosDaTurma(
                alunos3AdsB,
                2026021,
                turma3AdsB,
                2,
                3
        );

        criarAlunosDaTurma(
                alunos2AdsC,
                2026041,
                turma2AdsC,
                1,
                2
        );

        System.out.println("=========================================");
        System.out.println(" DADOS DE DEMONSTRAÇÃO FINALIZADOS");
        System.out.println("=========================================");
    }

    // =====================================================
    // CONFIGURAÇÃO
    // =====================================================

    private void validarConfiguracao() {

        if (demoPassword == null || demoPassword.isBlank()) {
            throw new IllegalStateException(
                    "DEMO_PASSWORD não foi configurada. " +
                            "Defina a variável de ambiente antes de habilitar os dados de demonstração."
            );
        }

        if (demoPassword.length() < 6) {
            throw new IllegalStateException(
                    "DEMO_PASSWORD deve possuir pelo menos 6 caracteres."
            );
        }
    }

    // =====================================================
    // ADMINISTRADOR
    // =====================================================

    private void criarAdministrador() {

        String email = "admin@unisa.br";

        Optional<Usuario> adminExistente =
                usuarioRepository.findByEmail(email);

        if (adminExistente.isPresent()) {

            Usuario admin =
                    adminExistente.get();

            admin.setSenha(
                    passwordEncoder.encode(demoPassword)
            );

            usuarioRepository.save(admin);

            return;
        }

        Usuario admin = new Usuario();

        admin.setNome("Administrador Acadêmico");
        admin.setEmail(email);
        admin.setSenha(
                passwordEncoder.encode(demoPassword)
        );
        admin.setPerfil(
                PerfilUsuario.ADMINISTRADOR
        );

        usuarioRepository.save(admin);
    }

    // =====================================================
    // TURMA
    // =====================================================

    private Turma criarTurmaSeNaoExistir(
            String nome,
            Integer semestre,
            Integer ano) {

        return turmaRepository
                .findByNome(nome)
                .orElseGet(() -> {

                    Turma turma = new Turma();

                    turma.setNome(nome);
                    turma.setSemestre(semestre);
                    turma.setAno(ano);

                    return turmaRepository.save(turma);
                });
    }

    // =====================================================
    // DISCIPLINA
    // =====================================================

    private Disciplina criarDisciplinaSeNaoExistir(
            String nome,
            Integer cargaHoraria) {

        return disciplinaRepository
                .findByNome(nome)
                .orElseGet(() -> {

                    Disciplina disciplina =
                            new Disciplina();

                    disciplina.setNome(nome);
                    disciplina.setCargaHoraria(cargaHoraria);

                    return disciplinaRepository.save(disciplina);
                });
    }

    // =====================================================
// PROFESSOR
// =====================================================

    private Professor criarProfessorSeNaoExistir(
            String nome,
            String email,
            String registro) {

        Optional<Professor> professorExistente =
                professorRepository.findByRegistro(registro);

        if (professorExistente.isPresent()) {

            Professor professor =
                    professorExistente.get();

            Usuario usuario =
                    professor.getUsuario();

            usuario.setSenha(
                    passwordEncoder.encode(demoPassword)
            );

            usuarioRepository.save(usuario);

            return professor;
        }

        Usuario usuario =
                usuarioRepository
                        .findByEmail(email)
                        .orElseGet(() -> {

                            Usuario novoUsuario =
                                    new Usuario();

                            novoUsuario.setNome(nome);
                            novoUsuario.setEmail(email);
                            novoUsuario.setPerfil(
                                    PerfilUsuario.PROFESSOR
                            );

                            return usuarioRepository.save(
                                    novoUsuario
                            );
                        });

        usuario.setSenha(
                passwordEncoder.encode(demoPassword)
        );

        usuarioRepository.save(usuario);

        Professor professor =
                new Professor();

        professor.setRegistro(registro);
        professor.setUsuario(usuario);

        return professorRepository.save(
                professor
        );
    }

    // =====================================================
    // VÍNCULO TURMA / DISCIPLINA / PROFESSOR
    // =====================================================

    private TurmaDisciplina criarVinculoSeNaoExistir(
            Turma turma,
            Disciplina disciplina,
            Professor professor) {

        return turmaDisciplinaRepository
                .findByTurma_IdTurmaAndDisciplina_IdDisciplina(
                        turma.getIdTurma(),
                        disciplina.getIdDisciplina()
                )
                .orElseGet(() -> {

                    TurmaDisciplina vinculo =
                            new TurmaDisciplina();

                    vinculo.setTurma(turma);
                    vinculo.setDisciplina(disciplina);
                    vinculo.setProfessor(professor);

                    return turmaDisciplinaRepository.save(
                            vinculo
                    );
                });
    }

    // =====================================================
    // ALUNOS DE UMA TURMA
    // =====================================================

    private void criarAlunosDaTurma(
            String[] nomes,
            int matriculaInicial,
            Turma turma,
            int semestreA,
            int semestreB) {

        for (int i = 0; i < nomes.length; i++) {

            String nome = nomes[i];
            String numeroMatricula =
                    String.valueOf(matriculaInicial + i);

            int semestreAluno =
                    (i % 2 == 0)
                            ? semestreA
                            : semestreB;

            Aluno aluno =
                    criarAlunoSeNaoExistir(
                            nome,
                            numeroMatricula,
                            i
                    );

            Matricula matricula =
                    criarMatriculaSeNaoExistir(
                            aluno,
                            turma,
                            semestreAluno
                    );

            criarDadosAcademicos(
                    matricula,
                    i
            );
        }
    }

    // =====================================================
// ALUNO
// =====================================================

    private Aluno criarAlunoSeNaoExistir(
            String nome,
            String numeroMatricula,
            int indice) {

        Optional<Aluno> alunoExistente =
                alunoRepository.findByMatricula(numeroMatricula);

        if (alunoExistente.isPresent()) {

            Aluno aluno =
                    alunoExistente.get();

            Usuario usuario =
                    aluno.getUsuario();

            usuario.setSenha(
                    passwordEncoder.encode(demoPassword)
            );

            usuarioRepository.save(usuario);

            return aluno;
        }

        String email =
                gerarEmailAluno(nome);

        Usuario usuario =
                usuarioRepository
                        .findByEmail(email)
                        .orElseGet(() -> {

                            Usuario novoUsuario =
                                    new Usuario();

                            novoUsuario.setNome(nome);
                            novoUsuario.setEmail(email);
                            novoUsuario.setPerfil(
                                    PerfilUsuario.ALUNO
                            );

                            return usuarioRepository.save(
                                    novoUsuario
                            );
                        });

        usuario.setSenha(
                passwordEncoder.encode(demoPassword)
        );

        usuarioRepository.save(usuario);

        Aluno aluno =
                new Aluno();

        aluno.setMatricula(numeroMatricula);

        /*
         * Datas apenas para demonstração.
         * Variadas de forma determinística.
         */
        aluno.setDataNascimento(
                LocalDate.of(
                        2002 + (indice % 5),
                        1 + (indice % 12),
                        1 + (indice % 27)
                )
        );

        aluno.setUsuario(usuario);

        return alunoRepository.save(aluno);
    }

    // =====================================================
    // MATRÍCULA
    // =====================================================

    private Matricula criarMatriculaSeNaoExistir(
            Aluno aluno,
            Turma turma,
            Integer semestreAluno) {

        List<Matricula> matriculas =
                matriculaRepository
                        .findByAluno_IdAluno(
                                aluno.getIdAluno()
                        );

        for (Matricula matricula : matriculas) {

            if (
                    matricula.getTurma()
                            .getIdTurma()
                            .equals(turma.getIdTurma())
            ) {
                return matricula;
            }
        }

        Matricula matricula =
                new Matricula();

        matricula.setAluno(aluno);
        matricula.setTurma(turma);
        matricula.setDataMatricula(
                LocalDate.of(2026, 8, 3)
        );
        matricula.setStatus("ATIVA");
        matricula.setSemestreAluno(
                semestreAluno
        );

        return matriculaRepository.save(
                matricula
        );
    }

    // =====================================================
    // DADOS ACADÊMICOS
    // =====================================================

    private void criarDadosAcademicos(
            Matricula matricula,
            int indiceAluno) {

        List<TurmaDisciplina> vinculos =
                turmaDisciplinaRepository
                        .findByTurma_IdTurma(
                                matricula
                                        .getTurma()
                                        .getIdTurma()
                        );

        /*
         * A AVI é integrada.
         *
         * Portanto o MESMO valor é utilizado nas
         * três disciplinas do aluno.
         */
        BigDecimal avi =
                obterAvi(indiceAluno);

        for (int i = 0; i < vinculos.size(); i++) {

            TurmaDisciplina vinculo =
                    vinculos.get(i);

            BigDecimal avc =
                    obterAvc(
                            indiceAluno,
                            i
                    );

            BigDecimal avg =
                    obterAvg(
                            indiceAluno,
                            i
                    );

            criarNotaSeNaoExistir(
                    matricula,
                    vinculo,
                    TipoAvaliacao.AVC,
                    avc,
                    LocalDate.of(
                            2026,
                            9,
                            21
                    )
            );

            criarNotaSeNaoExistir(
                    matricula,
                    vinculo,
                    TipoAvaliacao.AVG,
                    avg,
                    LocalDate.of(
                            2026,
                            11,
                            23
                    )
            );

            criarNotaSeNaoExistir(
                    matricula,
                    vinculo,
                    TipoAvaliacao.AVI,
                    avi,
                    LocalDate.of(
                            2026,
                            12,
                            7
                    )
            );

            int quantidadeFaltas =
                    obterQuantidadeFaltas(
                            indiceAluno,
                            i
                    );

            criarFaltaSeNaoExistir(
                    matricula,
                    vinculo,
                    quantidadeFaltas
            );
        }
    }

    // =====================================================
    // NOTA
    // =====================================================

    private void criarNotaSeNaoExistir(
            Matricula matricula,
            TurmaDisciplina vinculo,
            TipoAvaliacao tipo,
            BigDecimal valor,
            LocalDate data) {

        boolean existe =
                notaRepository
                        .existsByMatricula_IdMatriculaAndTurmaDisciplina_IdTurmaDisciplinaAndTipoAvaliacao(
                                matricula.getIdMatricula(),
                                vinculo.getIdTurmaDisciplina(),
                                tipo
                        );

        if (existe) {
            return;
        }

        Nota nota =
                new Nota();

        nota.setMatricula(matricula);
        nota.setTurmaDisciplina(vinculo);
        nota.setTipoAvaliacao(tipo);
        nota.setValor(valor);
        nota.setDataAvaliacao(data);

        notaRepository.save(nota);
    }

    // =====================================================
    // FALTA
    // =====================================================

    private void criarFaltaSeNaoExistir(
            Matricula matricula,
            TurmaDisciplina vinculo,
            int quantidade) {

        /*
         * Zero faltas não precisa gerar registro.
         */
        if (quantidade <= 0) {
            return;
        }

        List<Falta> faltasExistentes =
                faltaRepository
                        .findByMatricula_IdMatriculaAndTurmaDisciplina_IdTurmaDisciplina(
                                matricula.getIdMatricula(),
                                vinculo.getIdTurmaDisciplina()
                        );

        if (!faltasExistentes.isEmpty()) {
            return;
        }

        Falta falta =
                new Falta();

        falta.setMatricula(matricula);
        falta.setTurmaDisciplina(vinculo);

        /*
         * Um registro consolidado é suficiente
         * para os dados de demonstração.
         */
        falta.setQuantidade(quantidade);

        falta.setDataFalta(
                LocalDate.of(
                        2026,
                        10,
                        15
                )
        );

        faltaRepository.save(falta);
    }

    // =====================================================
    // GERAÇÃO CONTROLADA DE NOTAS
    // =====================================================

    private BigDecimal obterAvc(
            int aluno,
            int disciplina) {

        int caso = aluno % 10;

        double valor;

        switch (caso) {

            // Aprovado normal
            case 0 ->
                    valor = 7.2 + disciplina * 0.2;

            // Nota baixa descartada
            case 1 ->
                    valor = 4.0 + disciplina * 0.2;

            // Reprovação por nota
            case 2 ->
                    valor = 4.5 + disciplina * 0.1;

            // Regra especial: existe nota zero
            case 3 ->
                    valor = 0.0;

            // Nota boa, mas reprovação por falta
            case 4 ->
                    valor = 8.0 + disciplina * 0.2;

            // Nota ruim + falta
            case 5 ->
                    valor = 4.0 + disciplina * 0.2;

            // Desempenho excelente
            case 6 ->
                    valor = 9.0 + disciplina * 0.2;

            // Aprovação próxima da média
            case 7 ->
                    valor = 6.0 + disciplina * 0.1;

            // Bom desempenho
            case 8 ->
                    valor = 7.0 + disciplina * 0.3;

            // Risco acadêmico
            default ->
                    valor = 4.8 + disciplina * 0.2;
        }

        return nota(valor);
    }

    private BigDecimal obterAvg(
            int aluno,
            int disciplina) {

        int caso = aluno % 10;

        double valor;

        switch (caso) {

            case 0 ->
                    valor = 8.1 + disciplina * 0.2;

            case 1 ->
                    valor = 7.0 + disciplina * 0.2;

            case 2 ->
                    valor = 5.0 + disciplina * 0.1;

            case 3 ->
                    valor = 8.0 + disciplina * 0.1;

            case 4 ->
                    valor = 8.5 + disciplina * 0.2;

            case 5 ->
                    valor = 5.0 + disciplina * 0.2;

            case 6 ->
                    valor = 9.2 + disciplina * 0.2;

            case 7 ->
                    valor = 6.2 + disciplina * 0.2;

            case 8 ->
                    valor = 8.0 + disciplina * 0.2;

            default ->
                    valor = 5.0 + disciplina * 0.1;
        }

        return nota(valor);
    }

    /*
     * AVI INTEGRADA:
     * o valor depende apenas do aluno,
     * nunca da disciplina.
     */
    private BigDecimal obterAvi(
            int aluno) {

        return switch (aluno % 10) {

            case 0 -> nota(7.5);
            case 1 -> nota(8.0);
            case 2 -> nota(5.5);
            case 3 -> nota(8.0);
            case 4 -> nota(8.5);
            case 5 -> nota(5.5);
            case 6 -> nota(9.0);
            case 7 -> nota(6.5);
            case 8 -> nota(7.8);
            default -> nota(4.8);
        };
    }

    // =====================================================
    // FALTAS
    // =====================================================

    private int obterQuantidadeFaltas(
            int aluno,
            int disciplina) {

        int caso = aluno % 10;

        /*
         * Carga horária = 80.
         *
         * 20 faltas = 75% de frequência.
         * 21 ou mais = abaixo de 75%.
         */

        return switch (caso) {

            case 0 -> 4 + disciplina * 2;

            case 1 -> 8 + disciplina;

            case 2 -> 10 + disciplina * 2;

            case 3 -> 6 + disciplina * 2;

            /*
             * Reprovação proposital por falta.
             */
            case 4 -> 24 + disciplina;

            /*
             * Nota e falta.
             */
            case 5 -> 26 + disciplina;

            case 6 -> disciplina * 2;

            case 7 -> 18 + disciplina;

            case 8 -> 12 + disciplina * 2;

            default -> 16 + disciplina * 2;
        };
    }

    // =====================================================
    // UTILITÁRIOS
    // =====================================================

    private BigDecimal nota(double valor) {

        /*
         * Proteção adicional para nunca criar
         * uma nota fora de 0 a 10.
         */

        double valorSeguro =
            Math.max(
                0.0,
                Math.min(
                        10.0,
                        valor
                )
            );

        return BigDecimal
                .valueOf(valorSeguro)
                .setScale(
                        2,
                        RoundingMode.HALF_UP
                );
    }

    private String gerarEmailAluno(
            String nome) {

        String normalizado =
                Normalizer.normalize(
                        nome,
                        Normalizer.Form.NFD
                );

        normalizado =
                normalizado.replaceAll(
                        "\\p{M}",
                        ""
                );

        String[] partes =
                normalizado
                        .trim()
                        .toLowerCase(Locale.ROOT)
                        .split("\\s+");

        String primeiroNome =
                partes[0];

        String ultimoNome =
                partes[partes.length - 1];

        return primeiroNome
                + "."
                + ultimoNome
                + "@aluno.unisa.br";
    }
}
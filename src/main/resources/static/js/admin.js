// =========================
// MATRÍCULAS
// =========================

const listaMatriculasElemento =
    document.getElementById("listaMatriculas");

const botaoAbrirMatricula =
    document.getElementById("botaoAbrirMatricula");

const modalMatricula =
    document.getElementById("modalMatricula");

const botaoFecharMatricula =
    document.getElementById("botaoFecharMatricula");

const formMatricula =
    document.getElementById("formMatricula");

const matriculaAluno =
    document.getElementById("matriculaAluno");

const matriculaTurma =
    document.getElementById("matriculaTurma");

const matriculaSemestreAluno =
    document.getElementById("matriculaSemestreAluno");

const matriculaData =
    document.getElementById("matriculaData");

const matriculaStatus =
    document.getElementById("matriculaStatus");

const mensagemMatricula =
    document.getElementById("mensagemMatricula");


// =========================
// EDITAR MATRÍCULA
// =========================

const modalEditarMatricula =
    document.getElementById("modalEditarMatricula");

const botaoFecharEditarMatricula =
    document.getElementById("botaoFecharEditarMatricula");

const formEditarMatricula =
    document.getElementById("formEditarMatricula");

const editarMatriculaId =
    document.getElementById("editarMatriculaId");

const editarMatriculaAluno =
    document.getElementById("editarMatriculaAluno");

const editarMatriculaTurma =
    document.getElementById("editarMatriculaTurma");

const editarMatriculaSemestreAluno =
    document.getElementById("editarMatriculaSemestreAluno");

const editarMatriculaData =
    document.getElementById("editarMatriculaData");

const editarMatriculaStatus =
    document.getElementById("editarMatriculaStatus");

const mensagemEditarMatricula =
    document.getElementById("mensagemEditarMatricula");

// =========================
// AUTENTICAÇÃO
// =========================

const token =
    localStorage.getItem("token");

const perfil =
    localStorage.getItem("perfil");

const idUsuarioLogado =
    Number(
        localStorage.getItem("idUsuario")
    );


// =========================
// ELEMENTOS PRINCIPAIS
// =========================

const menuItens =
    document.querySelectorAll(".menu-item");

const secoes =
    document.querySelectorAll(".secao");

const botaoSair =
    document.getElementById("botaoSair");


// =========================
// CARDS
// =========================

const totalUsuariosElemento =
    document.getElementById("totalUsuarios");

const totalProfessoresElemento =
    document.getElementById("totalProfessores");

const totalNotasElemento =
    document.getElementById("totalNotas");

const totalFaltasElemento =
    document.getElementById("totalFaltas");

const mensagemInicioElemento =
    document.getElementById("mensagemInicio");


// =========================
// LISTAS
// =========================

const listaUsuariosElemento =
    document.getElementById("listaUsuarios");

const listaProfessoresElemento =
    document.getElementById("listaProfessores");

const listaTurmasElemento =
    document.getElementById("listaTurmas");

const listaDisciplinasElemento =
    document.getElementById("listaDisciplinas");

const listaVinculosElemento =
    document.getElementById("listaVinculos");

const listaNotasElemento =
    document.getElementById("listaNotas");

const listaFaltasElemento =
    document.getElementById("listaFaltas");

const listaAuditoriasElemento =
    document.getElementById("listaAuditorias");

// =========================
// FILTROS DE NOTAS
// =========================

const filtroNotaTurma =
    document.getElementById(
        "filtroNotaTurma"
    );

const filtroNotaDisciplina =
    document.getElementById(
        "filtroNotaDisciplina"
    );

const filtroNotaAluno =
    document.getElementById(
        "filtroNotaAluno"
    );

const filtroNotaProfessor =
    document.getElementById(
        "filtroNotaProfessor"
    );

const filtroNotaAvaliacao =
    document.getElementById(
        "filtroNotaAvaliacao"
    );

const limparFiltrosNotas =
    document.getElementById(
        "limparFiltrosNotas"
    );

const contadorNotas =
    document.getElementById(
        "contadorNotas"
    );

// =========================
// MODAL LANÇAR AVI
// =========================

const botaoAbrirAvi =
    document.getElementById(
        "botaoAbrirAvi"
    );

const modalAvi =
    document.getElementById(
        "modalAvi"
    );

const botaoFecharAvi =
    document.getElementById(
        "botaoFecharAvi"
    );

const formAvi =
    document.getElementById(
        "formAvi"
    );

const aviMatricula =
    document.getElementById(
        "aviMatricula"
    );

const aviAlunoNome =
    document.getElementById(
        "aviAlunoNome"
    );

const aviTurmaNome =
    document.getElementById(
        "aviTurmaNome"
    );

let aviIdMatriculaSelecionada = null;

const aviValor =
    document.getElementById(
        "aviValor"
    );

const aviData =
    document.getElementById(
        "aviData"
    );

const mensagemAvi =
    document.getElementById(
        "mensagemAvi"
    );

// =========================
// MODAL NOVO USUÁRIO
// =========================

const botaoAbrirUsuario =
    document.getElementById("botaoAbrirUsuario");

const botaoFecharUsuario =
    document.getElementById("botaoFecharUsuario");

const modalUsuario =
    document.getElementById("modalUsuario");

const formUsuario =
    document.getElementById("formUsuario");

const usuarioNome =
    document.getElementById("usuarioNome");

const usuarioEmail =
    document.getElementById("usuarioEmail");

const usuarioSenha =
    document.getElementById("usuarioSenha");

const usuarioPerfil =
    document.getElementById("usuarioPerfil");

const mensagemUsuario =
    document.getElementById("mensagemUsuario");


// =========================
// MODAL EDITAR USUÁRIO
// =========================

const modalEditarUsuario =
    document.getElementById(
        "modalEditarUsuario"
    );

const botaoFecharEditarUsuario =
    document.getElementById(
        "botaoFecharEditarUsuario"
    );

const formEditarUsuario =
    document.getElementById(
        "formEditarUsuario"
    );

const editarUsuarioId =
    document.getElementById(
        "editarUsuarioId"
    );

const editarUsuarioNome =
    document.getElementById(
        "editarUsuarioNome"
    );

const editarUsuarioEmail =
    document.getElementById(
        "editarUsuarioEmail"
    );

const editarUsuarioPerfil =
    document.getElementById(
        "editarUsuarioPerfil"
    );

const editarUsuarioSenha =
    document.getElementById(
        "editarUsuarioSenha"
    );

const mensagemEditarUsuario =
    document.getElementById(
        "mensagemEditarUsuario"
    );


// =========================
// MODAL NOVO PROFESSOR
// =========================

const botaoAbrirProfessor =
    document.getElementById(
        "botaoAbrirProfessor"
    );

const botaoFecharProfessor =
    document.getElementById(
        "botaoFecharProfessor"
    );

const modalProfessor =
    document.getElementById(
        "modalProfessor"
    );

const formProfessor =
    document.getElementById(
        "formProfessor"
    );

const professorUsuario =
    document.getElementById(
        "professorUsuario"
    );

const professorRegistro =
    document.getElementById(
        "professorRegistro"
    );

const mensagemProfessor =
    document.getElementById(
        "mensagemProfessor"
    );


// =========================
// MODAL EDITAR PROFESSOR
// =========================

const modalEditarProfessor =
    document.getElementById(
        "modalEditarProfessor"
    );

const botaoFecharEditarProfessor =
    document.getElementById(
        "botaoFecharEditarProfessor"
    );

const formEditarProfessor =
    document.getElementById(
        "formEditarProfessor"
    );

const editarProfessorId =
    document.getElementById(
        "editarProfessorId"
    );

const editarProfessorNome =
    document.getElementById(
        "editarProfessorNome"
    );

const editarProfessorRegistro =
    document.getElementById(
        "editarProfessorRegistro"
    );

const mensagemEditarProfessor =
    document.getElementById(
        "mensagemEditarProfessor"
    );

// =========================
// MODAL NOVO VÍNCULO
// =========================

const botaoAbrirVinculo =
    document.getElementById(
        "botaoAbrirVinculo"
    );

const botaoFecharVinculo =
    document.getElementById(
        "botaoFecharVinculo"
    );

const modalVinculo =
    document.getElementById(
        "modalVinculo"
    );

const formVinculo =
    document.getElementById(
        "formVinculo"
    );

const vinculoTurma =
    document.getElementById(
        "vinculoTurma"
    );

const vinculoDisciplina =
    document.getElementById(
        "vinculoDisciplina"
    );

const vinculoProfessor =
    document.getElementById(
        "vinculoProfessor"
    );

const mensagemVinculo =
    document.getElementById(
        "mensagemVinculo"
    );


// =========================
// MODAL TROCAR PROFESSOR
// =========================

const modalTrocarProfessor =
    document.getElementById(
        "modalTrocarProfessor"
    );

const botaoFecharTrocarProfessor =
    document.getElementById(
        "botaoFecharTrocarProfessor"
    );

const formTrocarProfessor =
    document.getElementById(
        "formTrocarProfessor"
    );

const trocarProfessorVinculoId =
    document.getElementById(
        "trocarProfessorVinculoId"
    );

const trocarProfessorTurmaDisciplina =
    document.getElementById(
        "trocarProfessorTurmaDisciplina"
    );

const trocarProfessorAtual =
    document.getElementById(
        "trocarProfessorAtual"
    );

const trocarProfessorNovo =
    document.getElementById(
        "trocarProfessorNovo"
    );

const mensagemTrocarProfessor =
    document.getElementById(
        "mensagemTrocarProfessor"
    );


// =========================
// MODAL NOVA TURMA
// =========================

const botaoAbrirTurma =
    document.getElementById(
        "botaoAbrirTurma"
    );

const botaoFecharTurma =
    document.getElementById(
        "botaoFecharTurma"
    );

const modalTurma =
    document.getElementById(
        "modalTurma"
    );

const formTurma =
    document.getElementById(
        "formTurma"
    );

const turmaNome =
    document.getElementById(
        "turmaNome"
    );

const turmaSemestre =
    document.getElementById(
        "turmaSemestre"
    );

const turmaAno =
    document.getElementById(
        "turmaAno"
    );

const mensagemTurma =
    document.getElementById(
        "mensagemTurma"
    );

// =========================
// MODAL NOVA DISCIPLINA
// =========================

const botaoAbrirDisciplina =
    document.getElementById(
        "botaoAbrirDisciplina"
    );

const botaoFecharDisciplina =
    document.getElementById(
        "botaoFecharDisciplina"
    );

const modalDisciplina =
    document.getElementById(
        "modalDisciplina"
    );

const formDisciplina =
    document.getElementById(
        "formDisciplina"
    );

const disciplinaNome =
    document.getElementById(
        "disciplinaNome"
    );

const disciplinaCargaHoraria =
    document.getElementById(
        "disciplinaCargaHoraria"
    );

const mensagemDisciplina =
    document.getElementById(
        "mensagemDisciplina"
    );

// =========================
// MODAL EDITAR TURMA
// =========================

const modalEditarTurma =
    document.getElementById(
        "modalEditarTurma"
    );

const botaoFecharEditarTurma =
    document.getElementById(
        "botaoFecharEditarTurma"
    );

const formEditarTurma =
    document.getElementById(
        "formEditarTurma"
    );

const editarTurmaId =
    document.getElementById(
        "editarTurmaId"
    );

const editarTurmaNome =
    document.getElementById(
        "editarTurmaNome"
    );

const editarTurmaSemestre =
    document.getElementById(
        "editarTurmaSemestre"
    );

const editarTurmaAno =
    document.getElementById(
        "editarTurmaAno"
    );

const mensagemEditarTurma =
    document.getElementById(
        "mensagemEditarTurma"
    );


// =========================
// MODAL EDITAR DISCIPLINA
// =========================

const modalEditarDisciplina =
    document.getElementById(
        "modalEditarDisciplina"
    );

const botaoFecharEditarDisciplina =
    document.getElementById(
        "botaoFecharEditarDisciplina"
    );

const formEditarDisciplina =
    document.getElementById(
        "formEditarDisciplina"
    );

const editarDisciplinaId =
    document.getElementById(
        "editarDisciplinaId"
    );

const editarDisciplinaNome =
    document.getElementById(
        "editarDisciplinaNome"
    );

const editarDisciplinaCargaHoraria =
    document.getElementById(
        "editarDisciplinaCargaHoraria"
    );

const mensagemEditarDisciplina =
    document.getElementById(
        "mensagemEditarDisciplina"
    );

// =========================
// FILTROS DE AUDITORIA
// =========================

const filtroAuditoriaUsuario =
    document.getElementById(
        "filtroAuditoriaUsuario"
    );

const filtroAuditoriaAcao =
    document.getElementById(
        "filtroAuditoriaAcao"
    );

const filtroAuditoriaDescricao =
    document.getElementById(
        "filtroAuditoriaDescricao"
    );

const filtroAuditoriaData =
    document.getElementById(
        "filtroAuditoriaData"
    );

const limparFiltrosAuditoria =
    document.getElementById(
        "limparFiltrosAuditoria"
    );

const contadorAuditorias =
    document.getElementById(
        "contadorAuditorias"
    );

const paginacaoAuditorias =
    document.getElementById(
        "paginacaoAuditorias"
    );

// =========================
// DADOS EM MEMÓRIA
// =========================

let usuarios = [];
let professores = [];
let vinculos = [];

let turmas = [];
let disciplinas = [];

let notas = [];
let faltas = [];
let auditorias = [];

let matriculas = [];
let alunos = [];

let auditoriasFiltradas = [];
let paginaAtualAuditoria = 1;

const registrosPorPaginaAuditoria = 10;


// =========================
// PROTEGER PÁGINA
// =========================

if (
    !token ||
    perfil !== "ADMINISTRADOR"
) {

    localStorage.clear();

    window.location.href =
        "/login.html";
}


// =========================
// MENU
// =========================

menuItens.forEach(item => {

    item.addEventListener(
        "click",
        function () {

            const secaoAlvo =
                item.getAttribute(
                    "data-secao"
                );

            menuItens.forEach(menu => {

                menu.classList.remove(
                    "ativo"
                );
            });

            secoes.forEach(secao => {

                secao.classList.remove(
                    "ativa"
                );
            });

            item.classList.add(
                "ativo"
            );

            const secao =
                document.getElementById(
                    secaoAlvo
                );

            if (secao) {

                secao.classList.add(
                    "ativa"
                );
            }
        }
    );
});


// =========================
// REQUISIÇÃO AUTENTICADA
// =========================

async function requisicaoAutenticada(
    url,
    opcoes = {}
) {

    const configuracao = {
        ...opcoes,

        headers: {

            "Content-Type":
                "application/json",

            "Authorization":
                `Bearer ${token}`,

            ...(opcoes.headers || {})
        }
    };

    const response =
        await fetch(
            url,
            configuracao
        );

    if (response.status === 401) {

        localStorage.clear();

        window.location.href =
            "/login.html";

        return null;
    }

    if (response.status === 403) {

        console.error(
            "Acesso negado para:",
            url
        );
    }

    return response;
}


// =========================
// CARREGAR TODOS OS DADOS
// =========================

async function carregarDados() {

    await carregarUsuarios();
    await carregarProfessores();
    await carregarTurmas();
    await carregarDisciplinas();
    await carregarVinculos();

    await carregarAlunos();
    await carregarMatriculas();

    await carregarNotas();
    await carregarFaltas();
    await carregarAuditorias();

    atualizarMensagemInicio();
}


// =========================
// USUÁRIOS
// =========================

async function carregarUsuarios() {

    try {

        const response =
            await requisicaoAutenticada(
                "/usuarios"
            );

        if (!response) {
            return;
        }

        if (!response.ok) {

            listaUsuariosElemento.textContent =
                "Não foi possível carregar os usuários.";

            totalUsuariosElemento.textContent =
                "--";

            return;
        }

        usuarios =
            await response.json();

        totalUsuariosElemento.textContent =
            usuarios.length;

        montarTabelaUsuarios(
            usuarios
        );

    } catch (erro) {

        console.error(
            "Erro ao carregar usuários:",
            erro
        );

        listaUsuariosElemento.textContent =
            "Erro ao conectar com o servidor.";

        totalUsuariosElemento.textContent =
            "--";
    }
}


// =========================
// TABELA DE USUÁRIOS
// =========================

function montarTabelaUsuarios(
    dados
) {

    if (
        !dados ||
        dados.length === 0
    ) {

        listaUsuariosElemento.textContent =
            "Nenhum usuário cadastrado.";

        return;
    }

    let html = `
        <div class="tabela-container">

            <table>

                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>E-mail</th>
                        <th>Perfil</th>
                        <th>Ações</th>
                    </tr>
                </thead>

                <tbody>
    `;

    dados.forEach(usuario => {

        const proprioUsuario =
            usuario.idUsuario ===
            idUsuarioLogado;

        html += `
            <tr>

                <td>
                    ${usuario.idUsuario ?? "--"}
                </td>

                <td>
                    ${usuario.nome ?? "--"}
                </td>

                <td>
                    ${usuario.email ?? "--"}
                </td>

                <td>
                    ${montarBadgePerfil(
            usuario.perfil
        )}
                </td>

                <td>

                    <button
                        type="button"
                        class="botao-secundario"
                        onclick="abrirModalEditarUsuario(${usuario.idUsuario})">

                        Editar

                    </button>

                    ${
            proprioUsuario
                ? `
                                <span
                                    title="Você não pode excluir o usuário da sessão atual">
                                    --
                                </span>
                              `
                : `
                                <button
                                    type="button"
                                    class="botao-perigo"
                                    onclick="excluirUsuario(${usuario.idUsuario})">

                                    Excluir

                                </button>
                              `
        }

                </td>

            </tr>
        `;
    });

    html += `
                </tbody>
            </table>
        </div>
    `;

    listaUsuariosElemento.innerHTML =
        html;
}


// =========================
// PROFESSORES
// =========================

async function carregarProfessores() {

    try {

        const response =
            await requisicaoAutenticada(
                "/professores"
            );

        if (!response) {
            return;
        }

        if (!response.ok) {

            listaProfessoresElemento.textContent =
                "Não foi possível carregar os professores.";

            totalProfessoresElemento.textContent =
                "--";

            return;
        }

        professores =
            await response.json();

        totalProfessoresElemento.textContent =
            professores.length;

        montarTabelaProfessores(
            professores
        );

    } catch (erro) {

        console.error(
            "Erro ao carregar professores:",
            erro
        );

        listaProfessoresElemento.textContent =
            "Erro ao conectar com o servidor.";

        totalProfessoresElemento.textContent =
            "--";
    }
}


// =========================
// TABELA DE PROFESSORES
// =========================

function montarTabelaProfessores(
    dados
) {

    if (
        !dados ||
        dados.length === 0
    ) {

        listaProfessoresElemento.textContent =
            "Nenhum professor cadastrado.";

        return;
    }

    let html = `
        <div class="tabela-container">

            <table>

                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Registro</th>
                        <th>Nome</th>
                        <th>E-mail</th>
                        <th>Ações</th>
                    </tr>
                </thead>

                <tbody>
    `;

    dados.forEach(professor => {

        html += `
            <tr>

                <td>
                    ${professor.idProfessor ?? "--"}
                </td>

                <td>
                    ${professor.registro ?? "--"}
                </td>

                <td>
                    ${professor.usuario?.nome ?? "--"}
                </td>

                <td>
                    ${professor.usuario?.email ?? "--"}
                </td>

                <td>

                    <button
                        type="button"
                        class="botao-secundario"
                        onclick="abrirModalEditarProfessor(${professor.idProfessor})">

                        Editar

                    </button>

                    <button
                        type="button"
                        class="botao-perigo"
                        onclick="excluirProfessor(${professor.idProfessor})">

                        Excluir

                    </button>

                </td>

            </tr>
        `;
    });

    html += `
                </tbody>
            </table>
        </div>
    `;

    listaProfessoresElemento.innerHTML =
        html;
}


// =========================
// USUÁRIOS PROFESSORES
// DISPONÍVEIS
// =========================

function obterUsuariosProfessoresDisponiveis() {

    return usuarios.filter(usuario => {

        if (
            usuario.perfil !==
            "PROFESSOR"
        ) {

            return false;
        }

        const jaPossuiProfessor =
            professores.some(
                professor =>
                    professor.usuario
                        ?.idUsuario ===
                    usuario.idUsuario
            );

        return !jaPossuiProfessor;
    });
}


// =========================
// PREENCHER SELECT PROFESSOR
// =========================

function preencherSelectProfessor() {

    professorUsuario.innerHTML = `
        <option value="">
            Selecione um usuário professor
        </option>
    `;

    const disponiveis =
        obterUsuariosProfessoresDisponiveis();

    if (disponiveis.length === 0) {

        const option =
            document.createElement(
                "option"
            );

        option.value =
            "";

        option.textContent =
            "Nenhum usuário PROFESSOR disponível";

        professorUsuario.appendChild(
            option
        );

        professorUsuario.disabled =
            true;

        return;
    }

    professorUsuario.disabled =
        false;

    disponiveis.forEach(usuario => {

        const option =
            document.createElement(
                "option"
            );

        option.value =
            usuario.idUsuario;

        option.textContent =
            `${usuario.nome} - ${usuario.email}`;

        professorUsuario.appendChild(
            option
        );
    });
}


// =========================
// ABRIR NOVO PROFESSOR
// =========================

function abrirModalProfessor() {

    formProfessor.reset();

    mensagemProfessor.textContent =
        "";

    mensagemProfessor.className =
        "mensagem-formulario";

    preencherSelectProfessor();

    modalProfessor.classList.add(
        "ativo"
    );
}


// =========================
// FECHAR NOVO PROFESSOR
// =========================

function fecharModalProfessor() {

    modalProfessor.classList.remove(
        "ativo"
    );

    formProfessor.reset();

    mensagemProfessor.textContent =
        "";

    mensagemProfessor.className =
        "mensagem-formulario";
}


botaoAbrirProfessor.addEventListener(
    "click",
    abrirModalProfessor
);


botaoFecharProfessor.addEventListener(
    "click",
    fecharModalProfessor
);


modalProfessor.addEventListener(
    "click",
    function (event) {

        if (
            event.target ===
            modalProfessor
        ) {

            fecharModalProfessor();
        }
    }
);


// =========================
// CADASTRAR PROFESSOR
// =========================

formProfessor.addEventListener(
    "submit",
    async function (event) {

        event.preventDefault();

        mensagemProfessor.className =
            "mensagem-formulario";

        mensagemProfessor.textContent =
            "Salvando professor...";

        const idUsuario =
            Number(
                professorUsuario.value
            );

        const registro =
            professorRegistro
                .value
                .trim();

        if (!idUsuario) {

            mensagemProfessor.className =
                "mensagem-formulario erro";

            mensagemProfessor.textContent =
                "Selecione um usuário professor.";

            return;
        }

        if (!registro) {

            mensagemProfessor.className =
                "mensagem-formulario erro";

            mensagemProfessor.textContent =
                "Informe o registro do professor.";

            return;
        }

        const dados = {

            registro:
            registro,

            usuario: {

                idUsuario:
                idUsuario
            }
        };

        try {

            const response =
                await requisicaoAutenticada(
                    "/professores",
                    {
                        method:
                            "POST",

                        body:
                            JSON.stringify(
                                dados
                            )
                    }
                );

            if (!response) {
                return;
            }

            const resposta =
                await lerJsonSeguro(
                    response
                );

            if (!response.ok) {

                mensagemProfessor.className =
                    "mensagem-formulario erro";

                mensagemProfessor.textContent =
                    resposta?.mensagem ??
                    "Não foi possível cadastrar o professor.";

                return;
            }

            mensagemProfessor.className =
                "mensagem-formulario sucesso";

            mensagemProfessor.textContent =
                "Professor cadastrado com sucesso!";

            await carregarProfessores();
            await carregarAuditorias();

            setTimeout(
                fecharModalProfessor,
                900
            );

        } catch (erro) {

            console.error(
                "Erro ao cadastrar professor:",
                erro
            );

            mensagemProfessor.className =
                "mensagem-formulario erro";

            mensagemProfessor.textContent =
                "Erro ao conectar com o servidor.";
        }
    }
);


// =========================
// ABRIR EDITAR PROFESSOR
// =========================

function abrirModalEditarProfessor(
    id
) {

    const professor =
        professores.find(
            item =>
                item.idProfessor === id
        );

    if (!professor) {

        alert(
            "Professor não encontrado."
        );

        return;
    }

    editarProfessorId.value =
        professor.idProfessor;

    editarProfessorNome.value =
        professor.usuario?.nome ??
        "";

    editarProfessorRegistro.value =
        professor.registro ??
        "";

    mensagemEditarProfessor.textContent =
        "";

    mensagemEditarProfessor.className =
        "mensagem-formulario";

    modalEditarProfessor.classList.add(
        "ativo"
    );
}


// =========================
// FECHAR EDITAR PROFESSOR
// =========================

function fecharModalEditarProfessor() {

    modalEditarProfessor.classList.remove(
        "ativo"
    );

    formEditarProfessor.reset();

    editarProfessorId.value =
        "";

    mensagemEditarProfessor.textContent =
        "";

    mensagemEditarProfessor.className =
        "mensagem-formulario";
}


botaoFecharEditarProfessor.addEventListener(
    "click",
    fecharModalEditarProfessor
);


modalEditarProfessor.addEventListener(
    "click",
    function (event) {

        if (
            event.target ===
            modalEditarProfessor
        ) {

            fecharModalEditarProfessor();
        }
    }
);


// =========================
// SALVAR EDIÇÃO PROFESSOR
// =========================

formEditarProfessor.addEventListener(
    "submit",
    async function (event) {

        event.preventDefault();

        const id =
            Number(
                editarProfessorId.value
            );

        if (!id) {

            mensagemEditarProfessor.className =
                "mensagem-formulario erro";

            mensagemEditarProfessor.textContent =
                "Professor inválido.";

            return;
        }

        const registro =
            editarProfessorRegistro
                .value
                .trim();

        if (!registro) {

            mensagemEditarProfessor.className =
                "mensagem-formulario erro";

            mensagemEditarProfessor.textContent =
                "Informe o registro do professor.";

            return;
        }

        mensagemEditarProfessor.className =
            "mensagem-formulario";

        mensagemEditarProfessor.textContent =
            "Salvando alterações...";

        const dados = {

            registro:
            registro
        };

        try {

            const response =
                await requisicaoAutenticada(
                    `/professores/${id}`,
                    {
                        method:
                            "PUT",

                        body:
                            JSON.stringify(
                                dados
                            )
                    }
                );

            if (!response) {
                return;
            }

            const resposta =
                await lerJsonSeguro(
                    response
                );

            if (!response.ok) {

                mensagemEditarProfessor.className =
                    "mensagem-formulario erro";

                mensagemEditarProfessor.textContent =
                    resposta?.mensagem ??
                    "Não foi possível atualizar o professor.";

                return;
            }

            mensagemEditarProfessor.className =
                "mensagem-formulario sucesso";

            mensagemEditarProfessor.textContent =
                "Professor atualizado com sucesso!";

            await carregarProfessores();

            await carregarVinculos();

            await carregarAuditorias();

            setTimeout(
                fecharModalEditarProfessor,
                900
            );

        } catch (erro) {

            console.error(
                "Erro ao atualizar professor:",
                erro
            );

            mensagemEditarProfessor.className =
                "mensagem-formulario erro";

            mensagemEditarProfessor.textContent =
                "Erro ao conectar com o servidor.";
        }
    }
);


// =========================
// EXCLUIR PROFESSOR
// =========================

async function excluirProfessor(
    id
) {

    const professor =
        professores.find(
            item =>
                item.idProfessor === id
        );

    if (!professor) {

        alert(
            "Professor não encontrado."
        );

        return;
    }

    const nome =
        professor.usuario?.nome ??
        professor.registro ??
        "Professor";

    const confirmar =
        window.confirm(
            `Deseja realmente excluir o cadastro do professor "${nome}"?`
        );

    if (!confirmar) {
        return;
    }

    try {

        const response =
            await requisicaoAutenticada(
                `/professores/${id}`,
                {
                    method:
                        "DELETE"
                }
            );

        if (!response) {
            return;
        }

        if (!response.ok) {

            const resposta =
                await lerJsonSeguro(
                    response
                );

            alert(
                resposta?.mensagem ??
                "Não foi possível excluir o professor."
            );

            return;
        }

        await carregarProfessores();

        await carregarAuditorias();

        alert(
            "Professor excluído com sucesso."
        );

    } catch (erro) {

        console.error(
            "Erro ao excluir professor:",
            erro
        );

        alert(
            "Erro ao conectar com o servidor."
        );
    }
}

// =========================
// CARREGAR TURMAS
// =========================

async function carregarTurmas() {

    try {

        const response =
            await requisicaoAutenticada(
                "/turmas"
            );

        if (!response) {
            return;
        }

        if (!response.ok) {

            console.error(
                "Não foi possível carregar as turmas."
            );

            return;
        }

        turmas =
            await response.json();
        montarTabelaTurmas(turmas);

    } catch (erro) {

        console.error(
            "Erro ao carregar turmas:",
            erro
        );
    }
}


// =========================
// CARREGAR DISCIPLINAS
// =========================

async function carregarDisciplinas() {

    try {

        const response =
            await requisicaoAutenticada(
                "/disciplinas"
            );

        if (!response) {
            return;
        }

        if (!response.ok) {

            console.error(
                "Não foi possível carregar as disciplinas."
            );

            return;
        }

        disciplinas =
            await response.json();
        montarTabelaDisciplinas(disciplinas);

    } catch (erro) {

        console.error(
            "Erro ao carregar disciplinas:",
            erro
        );
    }
}

// =========================
// TABELA DE TURMAS
// =========================

function montarTabelaTurmas(dados) {

    if (!dados || dados.length === 0) {

        listaTurmasElemento.textContent =
            "Nenhuma turma cadastrada.";

        return;
    }

    let html = `
        <div class="tabela-container">

            <table>

                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Turma</th>
                        <th>Ano</th>
                        <th>Semestre</th>
                        <th>Ações</th>
                    </tr>
                </thead>

                <tbody>
    `;

    dados.forEach(turma => {

        html += `
            <tr>

                <td>
                    ${turma.idTurma ?? "--"}
                </td>

                <td>
                    ${turma.nome ?? "--"}
                </td>

                <td>
                    ${turma.ano ?? "--"}
                </td>

                <td>
                    ${turma.semestre ?? "--"}º
                </td>

                <td>

                    <button
                        type="button"
                        class="botao-secundario"
                        onclick="abrirModalEditarTurma(${turma.idTurma})">

                        Editar

                    </button>

                    <button
                        type="button"
                        class="botao-perigo"
                        onclick="excluirTurma(${turma.idTurma})">

                        Excluir

                    </button>

                </td>

            </tr>
        `;
    });

    html += `
                </tbody>
            </table>

        </div>
    `;

    listaTurmasElemento.innerHTML =
        html;
}


// =========================
// TABELA DE DISCIPLINAS
// =========================

function montarTabelaDisciplinas(dados) {

    if (!dados || dados.length === 0) {

        listaDisciplinasElemento.textContent =
            "Nenhuma disciplina cadastrada.";

        return;
    }

    let html = `
        <div class="tabela-container">

            <table>

                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Disciplina</th>
                        <th>Carga horária</th>
                        <th>Ações</th>
                    </tr>
                </thead>

                <tbody>
    `;

    dados.forEach(disciplina => {

        html += `
            <tr>

                <td>
                    ${disciplina.idDisciplina ?? "--"}
                </td>

                <td>
                    ${disciplina.nome ?? "--"}
                </td>

                <td>
                    ${disciplina.cargaHoraria ?? "--"}h
                </td>

                <td>

                    <button
                        type="button"
                        class="botao-secundario"
                        onclick="abrirModalEditarDisciplina(${disciplina.idDisciplina})">

                        Editar

                    </button>

                    <button
                        type="button"
                        class="botao-perigo"
                        onclick="excluirDisciplina(${disciplina.idDisciplina})">

                        Excluir

                    </button>

                </td>

            </tr>
        `;
    });

    html += `
                </tbody>
            </table>

        </div>
    `;

    listaDisciplinasElemento.innerHTML =
        html;
}

// =========================
// EDITAR TURMA
// =========================

function abrirModalEditarTurma(id) {

    const turma =
        turmas.find(
            item =>
                item.idTurma === id
        );

    if (!turma) {

        alert(
            "Turma não encontrada."
        );

        return;
    }

    editarTurmaId.value =
        turma.idTurma;

    editarTurmaNome.value =
        turma.nome ?? "";

    editarTurmaSemestre.value =
        turma.semestre ?? "";

    editarTurmaAno.value =
        turma.ano ?? "";

    mensagemEditarTurma.textContent =
        "";

    mensagemEditarTurma.className =
        "mensagem-formulario";

    modalEditarTurma.classList.add(
        "ativo"
    );
}


function fecharModalEditarTurma() {

    modalEditarTurma.classList.remove(
        "ativo"
    );

    formEditarTurma.reset();

    editarTurmaId.value =
        "";

    mensagemEditarTurma.textContent =
        "";

    mensagemEditarTurma.className =
        "mensagem-formulario";
}


botaoFecharEditarTurma.addEventListener(
    "click",
    fecharModalEditarTurma
);


modalEditarTurma.addEventListener(
    "click",
    function (event) {

        if (event.target === modalEditarTurma) {

            fecharModalEditarTurma();
        }
    }
);


formEditarTurma.addEventListener(
    "submit",
    async function (event) {

        event.preventDefault();

        const id =
            Number(
                editarTurmaId.value
            );

        const dados = {

            nome:
                editarTurmaNome
                    .value
                    .trim(),

            semestre:
                Number(
                    editarTurmaSemestre.value
                ),

            ano:
                Number(
                    editarTurmaAno.value
                )
        };

        try {

            const response =
                await requisicaoAutenticada(
                    `/turmas/${id}`,
                    {
                        method:
                            "PUT",

                        body:
                            JSON.stringify(
                                dados
                            )
                    }
                );

            if (!response) {
                return;
            }

            const resposta =
                await lerJsonSeguro(
                    response
                );

            if (!response.ok) {

                mensagemEditarTurma.className =
                    "mensagem-formulario erro";

                mensagemEditarTurma.textContent =
                    resposta?.mensagem ??
                    "Não foi possível atualizar a turma.";

                return;
            }

            mensagemEditarTurma.className =
                "mensagem-formulario sucesso";

            mensagemEditarTurma.textContent =
                "Turma atualizada com sucesso!";

            await carregarTurmas();

            await carregarVinculos();

            await carregarAuditorias();

            setTimeout(
                fecharModalEditarTurma,
                900
            );

        } catch (erro) {

            console.error(
                "Erro ao atualizar turma:",
                erro
            );

            mensagemEditarTurma.className =
                "mensagem-formulario erro";

            mensagemEditarTurma.textContent =
                "Erro ao conectar com o servidor.";
        }
    }
);


// =========================
// EXCLUIR TURMA
// =========================

async function excluirTurma(id) {

    const turma =
        turmas.find(
            item =>
                item.idTurma === id
        );

    if (!turma) {

        alert(
            "Turma não encontrada."
        );

        return;
    }

    const confirmar =
        window.confirm(
            `Deseja realmente excluir a turma "${turma.nome}"?`
        );

    if (!confirmar) {
        return;
    }

    try {

        const response =
            await requisicaoAutenticada(
                `/turmas/${id}`,
                {
                    method:
                        "DELETE"
                }
            );

        if (!response) {
            return;
        }

        if (!response.ok) {

            const resposta =
                await lerJsonSeguro(
                    response
                );

            alert(
                resposta?.mensagem ??
                "Não foi possível excluir a turma."
            );

            return;
        }

        await carregarTurmas();

        await carregarAuditorias();

        alert(
            "Turma excluída com sucesso."
        );

    } catch (erro) {

        console.error(
            "Erro ao excluir turma:",
            erro
        );

        alert(
            "Erro ao conectar com o servidor."
        );
    }
}

async function excluirVinculo(id) {

    try {

        const buscarResponse =
            await requisicaoAutenticada(
                `/turmas-disciplinas/${id}`,
                {
                    method:
                        "GET"
                }
            );

        if (!buscarResponse) {
            return;
        }

        if (!buscarResponse.ok) {

            const resposta =
                await lerJsonSeguro(
                    buscarResponse
                );

            alert(
                resposta?.mensagem ??
                "Vínculo acadêmico não encontrado."
            );

            return;
        }

        const vinculo =
            await buscarResponse.json();

        const confirmar =
            window.confirm(
                `Deseja realmente excluir o vínculo entre "${vinculo.turma?.nome ?? "--"}" e "${vinculo.disciplina?.nome ?? "--"}"?`
            );

        if (!confirmar) {
            return;
        }

        const response =
            await requisicaoAutenticada(
                `/turmas-disciplinas/${id}`,
                {
                    method:
                        "DELETE"
                }
            );

        if (!response) {
            return;
        }

        if (!response.ok) {

            const resposta =
                await lerJsonSeguro(
                    response
                );

            alert(
                resposta?.mensagem ??
                "Não foi possível excluir o vínculo acadêmico."
            );

            return;
        }

        await carregarVinculos();

        await carregarAuditorias();

        alert(
            "Vínculo acadêmico excluído com sucesso."
        );

    } catch (erro) {

        console.error(
            "Erro ao excluir vínculo acadêmico:",
            erro
        );

        alert(
            "Erro ao conectar com o servidor."
        );
    }
}



// =========================
// EDITAR DISCIPLINA
// =========================

function abrirModalEditarDisciplina(id) {

    const disciplina =
        disciplinas.find(
            item =>
                item.idDisciplina === id
        );

    if (!disciplina) {

        alert(
            "Disciplina não encontrada."
        );

        return;
    }

    editarDisciplinaId.value =
        disciplina.idDisciplina;

    editarDisciplinaNome.value =
        disciplina.nome ?? "";

    editarDisciplinaCargaHoraria.value =
        disciplina.cargaHoraria ?? "";

    mensagemEditarDisciplina.textContent =
        "";

    mensagemEditarDisciplina.className =
        "mensagem-formulario";

    modalEditarDisciplina.classList.add(
        "ativo"
    );
}


function fecharModalEditarDisciplina() {

    modalEditarDisciplina.classList.remove(
        "ativo"
    );

    formEditarDisciplina.reset();

    editarDisciplinaId.value =
        "";

    mensagemEditarDisciplina.textContent =
        "";

    mensagemEditarDisciplina.className =
        "mensagem-formulario";
}


botaoFecharEditarDisciplina.addEventListener(
    "click",
    fecharModalEditarDisciplina
);


modalEditarDisciplina.addEventListener(
    "click",
    function (event) {

        if (
            event.target ===
            modalEditarDisciplina
        ) {

            fecharModalEditarDisciplina();
        }
    }
);


formEditarDisciplina.addEventListener(
    "submit",
    async function (event) {

        event.preventDefault();

        const id =
            Number(
                editarDisciplinaId.value
            );

        const dados = {

            nome:
                editarDisciplinaNome
                    .value
                    .trim(),

            cargaHoraria:
                Number(
                    editarDisciplinaCargaHoraria.value
                )
        };

        try {

            const response =
                await requisicaoAutenticada(
                    `/disciplinas/${id}`,
                    {
                        method:
                            "PUT",

                        body:
                            JSON.stringify(
                                dados
                            )
                    }
                );

            if (!response) {
                return;
            }

            const resposta =
                await lerJsonSeguro(
                    response
                );

            if (!response.ok) {

                mensagemEditarDisciplina.className =
                    "mensagem-formulario erro";

                mensagemEditarDisciplina.textContent =
                    resposta?.mensagem ??
                    "Não foi possível atualizar a disciplina.";

                return;
            }

            mensagemEditarDisciplina.className =
                "mensagem-formulario sucesso";

            mensagemEditarDisciplina.textContent =
                "Disciplina atualizada com sucesso!";

            await carregarDisciplinas();

            await carregarVinculos();

            await carregarAuditorias();

            setTimeout(
                fecharModalEditarDisciplina,
                900
            );

        } catch (erro) {

            console.error(
                "Erro ao atualizar disciplina:",
                erro
            );

            mensagemEditarDisciplina.className =
                "mensagem-formulario erro";

            mensagemEditarDisciplina.textContent =
                "Erro ao conectar com o servidor.";
        }
    }
);


// =========================
// EXCLUIR DISCIPLINA
// =========================

async function excluirDisciplina(id) {

    const disciplina =
        disciplinas.find(
            item =>
                item.idDisciplina === id
        );

    if (!disciplina) {

        alert(
            "Disciplina não encontrada."
        );

        return;
    }

    const confirmar =
        window.confirm(
            `Deseja realmente excluir a disciplina "${disciplina.nome}"?`
        );

    if (!confirmar) {
        return;
    }

    try {

        const response =
            await requisicaoAutenticada(
                `/disciplinas/${id}`,
                {
                    method:
                        "DELETE"
                }
            );

        if (!response) {
            return;
        }

        if (!response.ok) {

            const resposta =
                await lerJsonSeguro(
                    response
                );

            alert(
                resposta?.mensagem ??
                "Não foi possível excluir a disciplina."
            );

            return;
        }

        await carregarDisciplinas();

        await carregarAuditorias();

        alert(
            "Disciplina excluída com sucesso."
        );

    } catch (erro) {

        console.error(
            "Erro ao excluir disciplina:",
            erro
        );

        alert(
            "Erro ao conectar com o servidor."
        );
    }
}

// =========================
// VÍNCULOS
// =========================

async function carregarVinculos() {

    try {

        const response =
            await requisicaoAutenticada(
                "/turmas-disciplinas"
            );

        if (!response) {
            return;
        }

        if (!response.ok) {

            listaVinculosElemento.textContent =
                "Não foi possível carregar os vínculos.";

            return;
        }

        vinculos =
            await response.json();

        montarTabelaVinculos(
            vinculos
        );

    } catch (erro) {

        console.error(
            "Erro ao carregar vínculos:",
            erro
        );

        listaVinculosElemento.textContent =
            "Erro ao conectar com o servidor.";
    }
}


// =========================
// TABELA VÍNCULOS
// =========================

function montarTabelaVinculos(
    dados
) {

    if (
        !dados ||
        dados.length === 0
    ) {

        listaVinculosElemento.textContent =
            "Nenhum vínculo encontrado.";

        return;
    }

    let html = `
        <div class="tabela-container">

            <table>

                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Turma</th>
                        <th>Disciplina</th>
                        <th>Professor</th>
                        <th>Registro</th>
                        <th>Ações</th>
                    </tr>
                </thead>

                <tbody>
    `;

    dados.forEach(vinculo => {

        html += `
            <tr>

                <td>
                    ${vinculo.idTurmaDisciplina ?? "--"}
                </td>

                <td>
                    ${vinculo.turma?.nome ?? "--"}
                </td>

                <td>
                    ${vinculo.disciplina?.nome ?? "--"}
                </td>

                <td>
                    ${vinculo.professor?.usuario?.nome ?? "--"}
                </td>

                <td>
                    ${vinculo.professor?.registro ?? "--"}
                </td>

                <td>

    <button
        type="button"
        class="botao-secundario"
        onclick="abrirModalTrocarProfessor(${vinculo.idTurmaDisciplina})"
    >
        Trocar professor
    </button>

    <button
        type="button"
        class="botao-perigo"
        onclick="excluirVinculo(${vinculo.idTurmaDisciplina})"
    >
        Excluir
    </button>

</td>

            </tr>
        `;
    });

    html += `
                </tbody>
            </table>
        </div>
    `;

    listaVinculosElemento.innerHTML =
        html;
}

// =========================
// UTILITÁRIOS DOS FILTROS
// =========================

function valoresUnicos(
    itens,
    campo
) {

    return [
        ...new Set(
            itens
                .map(item => item[campo])
                .filter(valor =>
                    valor !== null &&
                    valor !== undefined &&
                    valor !== ""
                )
        )
    ].sort(
        (a, b) =>
            String(a).localeCompare(
                String(b),
                "pt-BR"
            )
    );
}


function preencherFiltro(
    select,
    valores,
    textoPadrao
) {

    const valorAtual =
        select.value;

    select.innerHTML =
        `<option value="">${textoPadrao}</option>`;

    valores.forEach(valor => {

        const option =
            document.createElement(
                "option"
            );

        option.value =
            valor;

        option.textContent =
            valor;

        select.appendChild(
            option
        );
    });

    const existeValorAtual =
        valores.some(
            valor =>
                String(valor) ===
                String(valorAtual)
        );

    if (existeValorAtual) {

        select.value =
            valorAtual;
    }
}


// =========================
// FILTROS DE NOTAS
// =========================

function atualizarFiltrosNotas() {

    const turmaSelecionada =
        filtroNotaTurma.value;

    const disciplinaSelecionada =
        filtroNotaDisciplina.value;


    const notasPorTurma =
        turmaSelecionada
            ? notas.filter(
                nota =>
                    nota.turma ===
                    turmaSelecionada
            )
            : notas;


    preencherFiltro(
        filtroNotaTurma,
        valoresUnicos(
            notas,
            "turma"
        ),
        "Todas as turmas"
    );


    preencherFiltro(
        filtroNotaDisciplina,
        valoresUnicos(
            notasPorTurma,
            "disciplina"
        ),
        "Todas as disciplinas"
    );


    const notasParaAluno =
        notasPorTurma.filter(
            nota =>
                !disciplinaSelecionada ||
                nota.disciplina ===
                disciplinaSelecionada
        );


    preencherFiltro(
        filtroNotaAluno,
        valoresUnicos(
            notasParaAluno,
            "aluno"
        ),
        "Todos os alunos"
    );


    preencherFiltro(
        filtroNotaProfessor,
        valoresUnicos(
            notasParaAluno,
            "professor"
        ),
        "Todos os professores"
    );
}


function aplicarFiltrosNotas() {

    const turma =
        filtroNotaTurma.value;

    const disciplina =
        filtroNotaDisciplina.value;

    const aluno =
        filtroNotaAluno.value;

    const professor =
        filtroNotaProfessor.value;

    const avaliacao =
        filtroNotaAvaliacao.value;


    const notasFiltradas =
        notas.filter(
            nota => {

                const turmaOk =
                    !turma ||
                    nota.turma === turma;

                const disciplinaOk =
                    !disciplina ||
                    nota.disciplina ===
                    disciplina;

                const alunoOk =
                    !aluno ||
                    nota.aluno === aluno;

                const professorOk =
                    !professor ||
                    nota.professor ===
                    professor;

                const avaliacaoOk =
                    !avaliacao ||
                    nota.tipoAvaliacao ===
                    avaliacao;


                return (
                    turmaOk &&
                    disciplinaOk &&
                    alunoOk &&
                    professorOk &&
                    avaliacaoOk
                );
            }
        );


    montarTabelaNotas(
        notasFiltradas
    );


    contadorNotas.textContent =
        `${notasFiltradas.length} de ${notas.length} registro(s) exibido(s).`;
}

// =========================
// EVENTOS DOS FILTROS DE NOTAS
// =========================

filtroNotaTurma.addEventListener(
    "change",
    function () {

        filtroNotaDisciplina.value =
            "";

        filtroNotaAluno.value =
            "";

        filtroNotaProfessor.value =
            "";

        atualizarFiltrosNotas();

        aplicarFiltrosNotas();
    }
);


filtroNotaDisciplina.addEventListener(
    "change",
    function () {

        filtroNotaAluno.value =
            "";

        filtroNotaProfessor.value =
            "";

        atualizarFiltrosNotas();

        aplicarFiltrosNotas();
    }
);


filtroNotaAluno.addEventListener(
    "change",
    aplicarFiltrosNotas
);


filtroNotaProfessor.addEventListener(
    "change",
    aplicarFiltrosNotas
);


filtroNotaAvaliacao.addEventListener(
    "change",
    aplicarFiltrosNotas
);


limparFiltrosNotas.addEventListener(
    "click",
    function () {

        filtroNotaTurma.value =
            "";

        filtroNotaDisciplina.value =
            "";

        filtroNotaAluno.value =
            "";

        filtroNotaProfessor.value =
            "";

        filtroNotaAvaliacao.value =
            "";

        atualizarFiltrosNotas();

        aplicarFiltrosNotas();
    }
);

// =====================================================
// MATRÍCULAS
// =====================================================


// =========================
// CARREGAR ALUNOS
// =========================

async function carregarAlunos() {

    try {

        const response =
            await requisicaoAutenticada(
                "/alunos"
            );

        if (!response) {
            return;
        }

        if (!response.ok) {

            console.error(
                "Não foi possível carregar os alunos."
            );

            alunos = [];

            return;
        }

        alunos =
            await response.json();

    } catch (erro) {

        console.error(
            "Erro ao carregar alunos:",
            erro
        );

        alunos = [];
    }
}


// =========================
// CARREGAR MATRÍCULAS
// =========================

async function carregarMatriculas() {

    if (!listaMatriculasElemento) {
        return;
    }

    try {

        const response =
            await requisicaoAutenticada(
                "/matriculas"
            );

        if (!response) {
            return;
        }

        if (!response.ok) {

            listaMatriculasElemento.textContent =
                "Não foi possível carregar as matrículas.";

            return;
        }

        matriculas =
            await response.json();

        montarTabelaMatriculas(
            matriculas
        );

    } catch (erro) {

        console.error(
            "Erro ao carregar matrículas:",
            erro
        );

        listaMatriculasElemento.textContent =
            "Erro ao conectar com o servidor.";
    }
}


// =========================
// TABELA DE MATRÍCULAS
// =========================

function montarTabelaMatriculas(
    dados
) {

    if (
        !dados ||
        dados.length === 0
    ) {

        listaMatriculasElemento.textContent =
            "Nenhuma matrícula encontrada.";

        return;
    }

    let html = `
        <div class="tabela-container">

            <table>

                <thead>

                    <tr>
                        <th>ID</th>
                        <th>Aluno</th>
                        <th>Matrícula</th>
                        <th>Turma</th>
                        <th>Semestre do curso</th>
                        <th>Data</th>
                        <th>Status</th>
                        <th>Ações</th>
                    </tr>

                </thead>

                <tbody>
    `;

    dados.forEach(
        matricula => {

            const aluno =
                matricula.aluno;

            const turma =
                matricula.turma;

            const nomeAluno =
                aluno?.usuario?.nome ??
                "--";

            const numeroMatricula =
                aluno?.matricula ??
                "--";

            const nomeTurma =
                turma?.nome ??
                "--";

            const semestreAluno =
                matricula.semestreAluno ??
                "--";

            html += `
                <tr>

                    <td>
                        ${matricula.idMatricula ?? "--"}
                    </td>

                    <td>
                        ${nomeAluno}
                    </td>

                    <td>
                        ${numeroMatricula}
                    </td>

                    <td>
                        ${nomeTurma}
                    </td>
                    
                    <td>
                        ${semestreAluno !== "--"
                        ? semestreAluno + "º"
                        : "--"}
                    </td>

                    <td>
                        ${formatarData(
                matricula.dataMatricula
            )}
                    </td>

                    <td>
                        ${matricula.status ?? "--"}
                    </td>

                    <td>

                        <div class="acoes-tabela">

                    <button
                    type="button"
                    class="botao-secundario"
                    onclick="abrirModalEditarMatricula(${matricula.idMatricula})">

                    Editar

                        </button>

                        <button
                            type="button"
                            class="botao-perigo"
                            onclick="excluirMatricula(${matricula.idMatricula})">

                            Excluir

                        </button>

                    </div>

                    </td>

                </tr>
            `;
        }
    );

    html += `
                </tbody>

            </table>

        </div>
    `;

    listaMatriculasElemento.innerHTML =
        html;
}


// =========================
// SELECTS DA NOVA MATRÍCULA
// =========================

function preencherSelectsMatricula() {

    matriculaAluno.innerHTML = `
        <option value="">
            Selecione o aluno
        </option>
    `;

    matriculaTurma.innerHTML = `
        <option value="">
            Selecione a turma
        </option>
    `;


    alunos.forEach(
        aluno => {

            const option =
                document.createElement(
                    "option"
                );

            option.value =
                aluno.idAluno;

            const nome =
                aluno.usuario?.nome ??
                "Aluno";

            const numero =
                aluno.matricula ??
                "--";

            option.textContent =
                `${nome} - ${numero}`;

            matriculaAluno.appendChild(
                option
            );
        }
    );


    turmas.forEach(
        turma => {

            const option =
                document.createElement(
                    "option"
                );

            option.value =
                turma.idTurma;

            option.textContent =
                `${turma.nome} - ${turma.ano}/${turma.semestre}`;

            matriculaTurma.appendChild(
                option
            );
        }
    );
}


// =========================
// ABRIR NOVA MATRÍCULA
// =========================

function abrirModalMatricula() {

    formMatricula.reset();

    mensagemMatricula.textContent =
        "";

    mensagemMatricula.className =
        "mensagem-formulario";

    preencherSelectsMatricula();

    matriculaData.value =
        new Date()
            .toISOString()
            .split("T")[0];

    modalMatricula.classList.add(
        "ativo"
    );
}


// =========================
// FECHAR NOVA MATRÍCULA
// =========================

function fecharModalMatricula() {

    modalMatricula.classList.remove(
        "ativo"
    );

    formMatricula.reset();

    mensagemMatricula.textContent =
        "";

    mensagemMatricula.className =
        "mensagem-formulario";
}


// =========================
// SALVAR NOVA MATRÍCULA
// =========================

formMatricula.addEventListener(
    "submit",
    async function (event) {

        event.preventDefault();

        const idAluno =
            Number(
                matriculaAluno.value
            );

        const idTurma =
            Number(
                matriculaTurma.value
            );

        const semestreAluno =
            Number(
                matriculaSemestreAluno.value
            );

        const dataMatricula =
            matriculaData.value;

        const status =
            matriculaStatus
                .value
                .trim();


        if (!idAluno) {

            mensagemMatricula.className =
                "mensagem-formulario erro";

            mensagemMatricula.textContent =
                "Selecione um aluno.";

            return;
        }


        if (!idTurma) {

            mensagemMatricula.className =
                "mensagem-formulario erro";

            mensagemMatricula.textContent =
                "Selecione uma turma.";

            return;
        }


        if (
            !dataMatricula ||
            !status
        ) {

            mensagemMatricula.className =
                "mensagem-formulario erro";

            mensagemMatricula.textContent =
                "Preencha todos os campos.";

            return;
        }


        const dados = {

            aluno: {
                idAluno: idAluno
            },

            turma: {
                idTurma: idTurma
            },

            semestreAluno:
            semestreAluno,

            dataMatricula:
            dataMatricula,

            status:
            status
        };


        mensagemMatricula.className =
            "mensagem-formulario";

        mensagemMatricula.textContent =
            "Salvando matrícula...";


        try {

            const response =
                await requisicaoAutenticada(
                    "/matriculas",
                    {
                        method: "POST",

                        headers: {
                            "Content-Type":
                                "application/json"
                        },

                        body:
                            JSON.stringify(
                                dados
                            )
                    }
                );

            if (!response) {
                return;
            }

            const resposta =
                await lerJsonSeguro(
                    response
                );


            if (!response.ok) {

                mensagemMatricula.className =
                    "mensagem-formulario erro";

                mensagemMatricula.textContent =
                    resposta?.erro ??
                    resposta?.message ??
                    "Não foi possível criar a matrícula.";

                return;
            }


            mensagemMatricula.className =
                "mensagem-formulario sucesso";

            mensagemMatricula.textContent =
                "Matrícula criada com sucesso.";


            await carregarMatriculas();

            await carregarAuditorias();


            setTimeout(
                fecharModalMatricula,
                600
            );

        } catch (erro) {

            console.error(
                "Erro ao criar matrícula:",
                erro
            );

            mensagemMatricula.className =
                "mensagem-formulario erro";

            mensagemMatricula.textContent =
                "Erro ao conectar com o servidor.";
        }
    }
);


// =========================
// ABRIR EDITAR MATRÍCULA
// =========================

function abrirModalEditarMatricula(
    idMatricula
) {

    const matricula =
        matriculas.find(
            item =>
                item.idMatricula ===
                idMatricula
        );


    if (!matricula) {

        alert(
            "Matrícula não encontrada."
        );

        return;
    }


    editarMatriculaId.value =
        matricula.idMatricula;


    editarMatriculaAluno.value =
        `${matricula.aluno?.usuario?.nome ?? "--"} - ${matricula.aluno?.matricula ?? "--"}`;


    editarMatriculaTurma.value =
        matricula.turma?.nome ??
        "--";

    editarMatriculaSemestreAluno.value =
        matricula.semestreAluno ??
        "";

    editarMatriculaData.value =
        matricula.dataMatricula ??
        "";


    editarMatriculaStatus.value =
        matricula.status ??
        "";


    mensagemEditarMatricula.textContent =
        "";

    mensagemEditarMatricula.className =
        "mensagem-formulario";


    modalEditarMatricula.classList.add(
        "ativo"
    );
}


// =========================
// FECHAR EDITAR MATRÍCULA
// =========================

function fecharModalEditarMatricula() {

    modalEditarMatricula.classList.remove(
        "ativo"
    );

    formEditarMatricula.reset();

    mensagemEditarMatricula.textContent =
        "";

    mensagemEditarMatricula.className =
        "mensagem-formulario";
}


// =========================
// SALVAR EDIÇÃO
// =========================

formEditarMatricula.addEventListener(
    "submit",
    async function (event) {

        event.preventDefault();


        const id =
            Number(
                editarMatriculaId.value
            );

        const semestreAluno =
            Number(
                editarMatriculaSemestreAluno.value
            );


        const dataMatricula =
            editarMatriculaData.value;


        const status =
            editarMatriculaStatus
                .value
                .trim();


        if (
            !id ||
            !dataMatricula ||
            !status
        ) {

            mensagemEditarMatricula.className =
                "mensagem-formulario erro";

            mensagemEditarMatricula.textContent =
                "Preencha os campos obrigatórios.";

            return;
        }

        if (
            !semestreAluno ||
            semestreAluno < 1 ||
            semestreAluno > 4
        ) {

            mensagemEditarMatricula.className =
                "mensagem-formulario erro";

            mensagemEditarMatricula.textContent =
                "Selecione um semestre válido.";

            return;
        }


        const dados = {
            semestreAluno: semestreAluno,
            dataMatricula: dataMatricula,
            status: status
        };

        mensagemEditarMatricula.className =
            "mensagem-formulario";

        mensagemEditarMatricula.textContent =
            "Salvando alterações...";


        try {

            const response =
                await requisicaoAutenticada(
                    `/matriculas/${id}`,
                    {
                        method: "PUT",

                        headers: {
                            "Content-Type":
                                "application/json"
                        },

                        body:
                            JSON.stringify(
                                dados
                            )
                    }
                );


            if (!response) {
                return;
            }


            const resposta =
                await lerJsonSeguro(
                    response
                );


            if (!response.ok) {

                mensagemEditarMatricula.className =
                    "mensagem-formulario erro";

                mensagemEditarMatricula.textContent =
                    resposta?.erro ??
                    resposta?.message ??
                    "Não foi possível alterar a matrícula.";

                return;
            }


            mensagemEditarMatricula.className =
                "mensagem-formulario sucesso";

            mensagemEditarMatricula.textContent =
                "Matrícula atualizada com sucesso.";


            await carregarMatriculas();

            await carregarAuditorias();


            setTimeout(
                fecharModalEditarMatricula,
                600
            );

        } catch (erro) {

            console.error(
                "Erro ao editar matrícula:",
                erro
            );

            mensagemEditarMatricula.className =
                "mensagem-formulario erro";

            mensagemEditarMatricula.textContent =
                "Erro ao conectar com o servidor.";
        }
    }
);


// =========================
// EXCLUIR MATRÍCULA
// =========================

async function excluirMatricula(
    idMatricula
) {

    const matricula =
        matriculas.find(
            item =>
                item.idMatricula ===
                idMatricula
        );


    if (!matricula) {

        alert(
            "Matrícula não encontrada."
        );

        return;
    }


    const nomeAluno =
        matricula.aluno?.usuario?.nome ??
        "Aluno";


    const turma =
        matricula.turma?.nome ??
        "--";


    const confirmou =
        confirm(
            `Deseja excluir a matrícula de ${nomeAluno} na turma ${turma}?`
        );


    if (!confirmou) {
        return;
    }


    try {

        const response =
            await requisicaoAutenticada(
                `/matriculas/${idMatricula}`,
                {
                    method: "DELETE"
                }
            );


        if (!response) {
            return;
        }


        if (!response.ok) {

            const resposta =
                await lerJsonSeguro(
                    response
                );

            alert(
                resposta?.erro ??
                resposta?.message ??
                "Não foi possível excluir a matrícula."
            );

            return;
        }


        alert(
            "Matrícula excluída com sucesso."
        );


        await carregarMatriculas();

        await carregarAuditorias();


    } catch (erro) {

        console.error(
            "Erro ao excluir matrícula:",
            erro
        );

        alert(
            "Erro ao conectar com o servidor."
        );
    }
}


// =========================
// EVENTOS DOS MODAIS
// =========================

botaoAbrirMatricula.addEventListener(
    "click",
    abrirModalMatricula
);


botaoFecharMatricula.addEventListener(
    "click",
    fecharModalMatricula
);


botaoFecharEditarMatricula.addEventListener(
    "click",
    fecharModalEditarMatricula
);


modalMatricula.addEventListener(
    "click",
    function (event) {

        if (
            event.target ===
            modalMatricula
        ) {

            fecharModalMatricula();
        }
    }
);


modalEditarMatricula.addEventListener(
    "click",
    function (event) {

        if (
            event.target ===
            modalEditarMatricula
        ) {

            fecharModalEditarMatricula();
        }
    }
);

// =========================
// NOTAS
// =========================

async function carregarNotas() {

    try {

        const response =
            await requisicaoAutenticada(
                "/notas"
            );

        if (!response) {
            return;
        }

        if (!response.ok) {

            listaNotasElemento.textContent =
                "Não foi possível carregar as notas.";

            totalNotasElemento.textContent =
                "--";

            return;
        }

        notas =
            await response.json();

        totalNotasElemento.textContent =
            notas.length;

        atualizarFiltrosNotas();

        aplicarFiltrosNotas();

    } catch (erro) {

        console.error(
            "Erro ao carregar notas:",
            erro
        );

        listaNotasElemento.textContent =
            "Erro ao conectar com o servidor.";

        totalNotasElemento.textContent =
            "--";
    }
}

// =========================
// ABRIR TROCAR PROFESSOR
// =========================

function abrirModalTrocarProfessor(
    idVinculo
) {

    const vinculo =
        vinculos.find(
            item =>
                item.idTurmaDisciplina ===
                idVinculo
        );

    if (!vinculo) {

        alert(
            "Vínculo não encontrado."
        );

        return;
    }

    trocarProfessorVinculoId.value =
        vinculo.idTurmaDisciplina;

    trocarProfessorTurmaDisciplina.value =
        `${vinculo.turma?.nome ?? "--"} / ${vinculo.disciplina?.nome ?? "--"}`;

    trocarProfessorAtual.value =
        `${vinculo.professor?.usuario?.nome ?? "--"} - ${vinculo.professor?.registro ?? "--"}`;

    trocarProfessorNovo.innerHTML = `
        <option value="">
            Selecione o novo professor
        </option>
    `;

    professores
        .filter(
            professor =>
                professor.idProfessor !==
                vinculo.professor?.idProfessor
        )
        .forEach(professor => {

            const option =
                document.createElement(
                    "option"
                );

            option.value =
                professor.idProfessor;

            option.textContent =
                `${professor.usuario?.nome ?? "Professor"} - ${professor.registro}`;

            trocarProfessorNovo.appendChild(
                option
            );
        });

    mensagemTrocarProfessor.textContent =
        "";

    mensagemTrocarProfessor.className =
        "mensagem-formulario";

    modalTrocarProfessor.classList.add(
        "ativo"
    );
}

// =========================
// PREENCHER SELECTS DO VÍNCULO
// =========================

function preencherSelectsVinculo() {

    vinculoTurma.innerHTML = `
        <option value="">
            Selecione a turma
        </option>
    `;

    vinculoDisciplina.innerHTML = `
        <option value="">
            Selecione a disciplina
        </option>
    `;

    vinculoProfessor.innerHTML = `
        <option value="">
            Selecione o professor
        </option>
    `;


    // =========================
    // TURMAS
    // =========================

    turmas.forEach(turma => {

        const option =
            document.createElement(
                "option"
            );

        option.value =
            turma.idTurma;

        option.textContent =
            `${turma.nome} - ${turma.ano}/${turma.semestre}`;

        vinculoTurma.appendChild(
            option
        );
    });


    // =========================
    // DISCIPLINAS
    // =========================

    disciplinas.forEach(disciplina => {

        const option =
            document.createElement(
                "option"
            );

        option.value =
            disciplina.idDisciplina;

        option.textContent =
            disciplina.nome;

        vinculoDisciplina.appendChild(
            option
        );
    });


    // =========================
    // PROFESSORES
    // =========================

    professores.forEach(professor => {

        const option =
            document.createElement(
                "option"
            );

        option.value =
            professor.idProfessor;

        option.textContent =
            `${professor.usuario?.nome ?? "Professor"} - ${professor.registro}`;

        vinculoProfessor.appendChild(
            option
        );
    });
}

// =========================
// ABRIR NOVO VÍNCULO
// =========================

function abrirModalVinculo() {

    formVinculo.reset();

    mensagemVinculo.textContent =
        "";

    mensagemVinculo.className =
        "mensagem-formulario";

    preencherSelectsVinculo();

    modalVinculo.classList.add(
        "ativo"
    );
}


// =========================
// FECHAR NOVO VÍNCULO
// =========================

function fecharModalVinculo() {

    modalVinculo.classList.remove(
        "ativo"
    );

    formVinculo.reset();

    mensagemVinculo.textContent =
        "";

    mensagemVinculo.className =
        "mensagem-formulario";
}


// =========================
// EVENTOS DO MODAL VÍNCULO
// =========================

botaoAbrirVinculo.addEventListener(
    "click",
    abrirModalVinculo
);

botaoFecharVinculo.addEventListener(
    "click",
    fecharModalVinculo
);

modalVinculo.addEventListener(
    "click",
    function (event) {

        if (
            event.target === modalVinculo
        ) {

            fecharModalVinculo();
        }
    }
);

// =========================
// SALVAR NOVO VÍNCULO
// =========================

formVinculo.addEventListener(
    "submit",
    async function (event) {

        event.preventDefault();

        const idTurma =
            Number(
                vinculoTurma.value
            );

        const idDisciplina =
            Number(
                vinculoDisciplina.value
            );

        const idProfessor =
            Number(
                vinculoProfessor.value
            );


        if (!idTurma) {

            mensagemVinculo.className =
                "mensagem-formulario erro";

            mensagemVinculo.textContent =
                "Selecione uma turma.";

            return;
        }

        if (
            !semestreAluno ||
            semestreAluno < 1 ||
            semestreAluno > 4
        ) {

            mensagemMatricula.className =
                "mensagem-formulario erro";

            mensagemMatricula.textContent =
                "Selecione um semestre válido.";

            return;
        }


        if (!idDisciplina) {

            mensagemVinculo.className =
                "mensagem-formulario erro";

            mensagemVinculo.textContent =
                "Selecione uma disciplina.";

            return;
        }


        if (!idProfessor) {

            mensagemVinculo.className =
                "mensagem-formulario erro";

            mensagemVinculo.textContent =
                "Selecione um professor.";

            return;
        }


        mensagemVinculo.className =
            "mensagem-formulario";

        mensagemVinculo.textContent =
            "Criando vínculo...";


        const dados = {

            turma: {
                idTurma:
                idTurma
            },

            disciplina: {
                idDisciplina:
                idDisciplina
            },

            professor: {
                idProfessor:
                idProfessor
            }
        };


        try {

            const response =
                await requisicaoAutenticada(
                    "/turmas-disciplinas",
                    {
                        method:
                            "POST",

                        body:
                            JSON.stringify(
                                dados
                            )
                    }
                );


            if (!response) {
                return;
            }


            const resposta =
                await lerJsonSeguro(
                    response
                );


            if (!response.ok) {

                mensagemVinculo.className =
                    "mensagem-formulario erro";

                mensagemVinculo.textContent =
                    resposta?.mensagem ??
                    "Não foi possível criar o vínculo.";

                return;
            }


            mensagemVinculo.className =
                "mensagem-formulario sucesso";

            mensagemVinculo.textContent =
                "Vínculo criado com sucesso!";


            await carregarVinculos();

            await carregarAuditorias();


            setTimeout(
                fecharModalVinculo,
                900
            );


        } catch (erro) {

            console.error(
                "Erro ao criar vínculo:",
                erro
            );

            mensagemVinculo.className =
                "mensagem-formulario erro";

            mensagemVinculo.textContent =
                "Erro ao conectar com o servidor.";
        }
    }
);


// =========================
// FECHAR TROCAR PROFESSOR
// =========================

function fecharModalTrocarProfessor() {

    modalTrocarProfessor.classList.remove(
        "ativo"
    );

    formTrocarProfessor.reset();

    mensagemTrocarProfessor.textContent =
        "";

    mensagemTrocarProfessor.className =
        "mensagem-formulario";
}


// =========================
// EVENTOS TROCAR PROFESSOR
// =========================

botaoFecharTrocarProfessor.addEventListener(
    "click",
    fecharModalTrocarProfessor
);


modalTrocarProfessor.addEventListener(
    "click",
    function (event) {

        if (
            event.target ===
            modalTrocarProfessor
        ) {

            fecharModalTrocarProfessor();
        }
    }
);


// =========================
// SALVAR TROCA DE PROFESSOR
// =========================

formTrocarProfessor.addEventListener(
    "submit",
    async function (event) {

        event.preventDefault();

        const idVinculo =
            Number(
                trocarProfessorVinculoId.value
            );

        const idProfessor =
            Number(
                trocarProfessorNovo.value
            );

        if (!idProfessor) {

            mensagemTrocarProfessor.className =
                "mensagem-formulario erro";

            mensagemTrocarProfessor.textContent =
                "Selecione um novo professor.";

            return;
        }

        mensagemTrocarProfessor.className =
            "mensagem-formulario";

        mensagemTrocarProfessor.textContent =
            "Salvando alteração...";

        try {

            const response =
                await requisicaoAutenticada(
                    `/turmas-disciplinas/${idVinculo}/professor`,
                    {
                        method:
                            "PUT",

                        body:
                            JSON.stringify({
                                idProfessor:
                                idProfessor
                            })
                    }
                );

            if (!response) {
                return;
            }

            const resposta =
                await lerJsonSeguro(
                    response
                );

            if (!response.ok) {

                mensagemTrocarProfessor.className =
                    "mensagem-formulario erro";

                mensagemTrocarProfessor.textContent =
                    resposta?.mensagem ??
                    "Não foi possível trocar o professor.";

                return;
            }

            mensagemTrocarProfessor.className =
                "mensagem-formulario sucesso";

            mensagemTrocarProfessor.textContent =
                "Professor alterado com sucesso!";

            await carregarVinculos();

            await carregarAuditorias();

            setTimeout(
                fecharModalTrocarProfessor,
                900
            );

        } catch (erro) {

            console.error(
                "Erro ao trocar professor:",
                erro
            );

            mensagemTrocarProfessor.className =
                "mensagem-formulario erro";

            mensagemTrocarProfessor.textContent =
                "Erro ao conectar com o servidor.";
        }
    }
);

// =========================
// LOCALIZAR MATRÍCULA AVI
// =========================

function localizarMatriculaAvi() {

    const numeroDigitado =
        aviMatricula.value.trim();

    aviIdMatriculaSelecionada = null;

    aviAlunoNome.value = "";
    aviTurmaNome.value = "";

    mensagemAvi.textContent = "";
    mensagemAvi.className =
        "mensagem-formulario";

    if (!numeroDigitado) {
        return;
    }


    const matriculaEncontrada =
        matriculas.find(matricula => {

            const numeroMatricula =
                String(
                    matricula.aluno?.matricula ?? ""
                );

            return numeroMatricula ===
                numeroDigitado;
        });


    if (!matriculaEncontrada) {

        mensagemAvi.textContent =
            "Matrícula não encontrada.";

        return;
    }


    aviIdMatriculaSelecionada =
        matriculaEncontrada.idMatricula;


    aviAlunoNome.value =
        matriculaEncontrada.aluno
            ?.usuario
            ?.nome ?? "";


    aviTurmaNome.value =
        matriculaEncontrada.turma
            ?.nome ?? "";


    mensagemAvi.textContent =
        "Aluno localizado com sucesso.";
}

// =========================
// ABRIR MODAL AVI
// =========================

function abrirModalAvi() {

    formAvi.reset();

    aviAlunoNome.value = "";
    aviTurmaNome.value = "";

    aviIdMatriculaSelecionada =
        null;

    mensagemAvi.textContent =
        "";

    mensagemAvi.className =
        "mensagem-formulario";

    modalAvi.classList.add(
        "ativo"
    );

    aviMatricula.focus();
}


// =========================
// FECHAR MODAL AVI
// =========================

function fecharModalAvi() {

    modalAvi.classList.remove(
        "ativo"
    );

    formAvi.reset();

    mensagemAvi.textContent =
        "";

    mensagemAvi.className =
        "mensagem-formulario";
}


// =========================
// EVENTOS MODAL AVI
// =========================

botaoAbrirAvi.addEventListener(
    "click",
    abrirModalAvi
);

botaoFecharAvi.addEventListener(
    "click",
    fecharModalAvi
);

modalAvi.addEventListener(
    "click",
    function (event) {

        if (
            event.target ===
            modalAvi
        ) {

            fecharModalAvi();
        }
    }
);

aviMatricula.addEventListener(
    "input",
    localizarMatriculaAvi
);

// =========================
// LANÇAR AVI
// =========================

formAvi.addEventListener(
    "submit",
    async function (event) {

        event.preventDefault();

        mensagemAvi.textContent = "";

        if (!aviIdMatriculaSelecionada) {

            mensagemAvi.textContent =
                "Informe uma matrícula válida.";

            return;
        }


        const valor =
            Number(aviValor.value);

        if (
            Number.isNaN(valor) ||
            valor < 0 ||
            valor > 10
        ) {

            mensagemAvi.textContent =
                "A nota deve estar entre 0 e 10.";

            return;
        }


        if (!aviData.value) {

            mensagemAvi.textContent =
                "Informe a data da avaliação.";

            return;
        }


        const dados = {

            idMatricula:
            aviIdMatriculaSelecionada,

            valor:
            valor,

            dataAvaliacao:
            aviData.value
        };


        try {

            const response =
                await requisicaoAutenticada(
                    "/notas/avi",
                    {
                        method: "POST",
                        headers: {
                            "Content-Type":
                                "application/json"
                        },
                        body:
                            JSON.stringify(dados)
                    }
                );

            if (!response) {
                return;
            }

            if (!response.ok) {

                let mensagemErro =
                    "Não foi possível lançar a AVI.";

                try {

                    const erro =
                        await response.json();

                    mensagemErro =
                        erro.mensagem ||
                        erro.message ||
                        erro.detail ||
                        mensagemErro;

                } catch (erro) {

                    console.error(
                        "Não foi possível ler a resposta de erro.",
                        erro
                    );
                }

                throw new Error(
                    mensagemErro
                );
            }

            mensagemAvi.textContent =
                "AVI lançada com sucesso!";


            await carregarNotas();


            setTimeout(
                function () {

                    fecharModalAvi();

                },
                1000
            );

        } catch (erro) {

        mensagemAvi.textContent =
        erro.message ||
        "Não foi possível lançar a AVI.";
        }
    }
);


// =========================
// TABELA NOTAS
// =========================

function montarTabelaNotas(
    dados
) {

    if (
        !dados ||
        dados.length === 0
    ) {

        listaNotasElemento.textContent =
            "Nenhuma nota encontrada.";

        return;
    }

    let html = `
        <div class="tabela-container">

            <table>

                <thead>
                    <tr>
                        <th>Aluno</th>
                        <th>Matrícula</th>
                        <th>Turma</th>
                        <th>Disciplina</th>
                        <th>Professor</th>
                        <th>Avaliação</th>
                        <th>Nota</th>
                        <th>Data</th>
                    </tr>
                </thead>

                <tbody>
    `;

    dados.forEach(nota => {

        html += `
            <tr>

                <td>
                    ${nota.aluno ?? "--"}
                </td>

                <td>
                    ${nota.matriculaAluno ?? "--"}
                </td>

                <td>
                    ${nota.turma ?? "--"}
                </td>

                <td>
                    ${nota.disciplina ?? "--"}
                </td>

                <td>
                    ${nota.professor ?? "--"}
                </td>

                <td>
                    ${nota.tipoAvaliacao ?? "--"}
                </td>

                <td>
                    ${formatarNota(
            nota.valor
        )}
                </td>

                <td>
                    ${formatarData(
            nota.dataAvaliacao
        )}
                </td>

            </tr>
        `;
    });

    html += `
                </tbody>
            </table>
        </div>
    `;

    listaNotasElemento.innerHTML =
        html;
}

// =========================
// FILTROS DE FALTAS
// =========================

const filtroFaltaTurma =
    document.getElementById(
        "filtroFaltaTurma"
    );

const filtroFaltaDisciplina =
    document.getElementById(
        "filtroFaltaDisciplina"
    );

const filtroFaltaAluno =
    document.getElementById(
        "filtroFaltaAluno"
    );

const filtroFaltaProfessor =
    document.getElementById(
        "filtroFaltaProfessor"
    );

const limparFiltrosFaltas =
    document.getElementById(
        "limparFiltrosFaltas"
    );

const contadorFaltas =
    document.getElementById(
        "contadorFaltas"
    );

// =========================
// FILTROS DE FALTAS
// =========================

function atualizarFiltrosFaltas() {

    const turmaSelecionada =
        filtroFaltaTurma.value;

    const disciplinaSelecionada =
        filtroFaltaDisciplina.value;


    const faltasPorTurma =
        turmaSelecionada
            ? faltas.filter(
                falta =>
                    falta.turma ===
                    turmaSelecionada
            )
            : faltas;


    preencherFiltro(
        filtroFaltaTurma,
        valoresUnicos(
            faltas,
            "turma"
        ),
        "Todas as turmas"
    );


    preencherFiltro(
        filtroFaltaDisciplina,
        valoresUnicos(
            faltasPorTurma,
            "disciplina"
        ),
        "Todas as disciplinas"
    );


    const faltasParaAluno =
        faltasPorTurma.filter(
            falta =>
                !disciplinaSelecionada ||
                falta.disciplina ===
                disciplinaSelecionada
        );


    preencherFiltro(
        filtroFaltaAluno,
        valoresUnicos(
            faltasParaAluno,
            "aluno"
        ),
        "Todos os alunos"
    );


    preencherFiltro(
        filtroFaltaProfessor,
        valoresUnicos(
            faltasParaAluno,
            "professor"
        ),
        "Todos os professores"
    );
}


function aplicarFiltrosFaltas() {

    const turma =
        filtroFaltaTurma.value;

    const disciplina =
        filtroFaltaDisciplina.value;

    const aluno =
        filtroFaltaAluno.value;

    const professor =
        filtroFaltaProfessor.value;


    const faltasFiltradas =
        faltas.filter(
            falta => {

                const turmaOk =
                    !turma ||
                    falta.turma === turma;

                const disciplinaOk =
                    !disciplina ||
                    falta.disciplina ===
                    disciplina;

                const alunoOk =
                    !aluno ||
                    falta.aluno === aluno;

                const professorOk =
                    !professor ||
                    falta.professor ===
                    professor;


                return (
                    turmaOk &&
                    disciplinaOk &&
                    alunoOk &&
                    professorOk
                );
            }
        );


    montarTabelaFaltas(
        faltasFiltradas
    );


    contadorFaltas.textContent =
        `${faltasFiltradas.length} de ${faltas.length} registro(s) exibido(s).`;
}

// =========================
// EVENTOS DOS FILTROS DE FALTAS
// =========================

filtroFaltaTurma.addEventListener(
    "change",
    function () {

        filtroFaltaDisciplina.value = "";
        filtroFaltaAluno.value = "";
        filtroFaltaProfessor.value = "";

        atualizarFiltrosFaltas();
        aplicarFiltrosFaltas();
    }
);


filtroFaltaDisciplina.addEventListener(
    "change",
    function () {

        filtroFaltaAluno.value = "";
        filtroFaltaProfessor.value = "";

        atualizarFiltrosFaltas();
        aplicarFiltrosFaltas();
    }
);


filtroFaltaAluno.addEventListener(
    "change",
    aplicarFiltrosFaltas
);


filtroFaltaProfessor.addEventListener(
    "change",
    aplicarFiltrosFaltas
);


limparFiltrosFaltas.addEventListener(
    "click",
    function () {

        filtroFaltaTurma.value = "";
        filtroFaltaDisciplina.value = "";
        filtroFaltaAluno.value = "";
        filtroFaltaProfessor.value = "";

        atualizarFiltrosFaltas();
        aplicarFiltrosFaltas();
    }
);

// =========================
// FALTAS
// =========================

async function carregarFaltas() {

    try {

        const response =
            await requisicaoAutenticada(
                "/faltas"
            );

        if (!response) {
            return;
        }

        if (!response.ok) {

            listaFaltasElemento.textContent =
                "Não foi possível carregar as faltas.";

            totalFaltasElemento.textContent =
                "--";

            return;
        }

        faltas =
            await response.json();

        const quantidadeTotal =
            faltas.reduce(
                function (
                    total,
                    falta
                ) {

                    return total +
                        Number(
                            falta.quantidade ||
                            0
                        );
                },
                0
            );

        totalFaltasElemento.textContent =
            quantidadeTotal;

        atualizarFiltrosFaltas();

        aplicarFiltrosFaltas();

    } catch (erro) {

        console.error(
            "Erro ao carregar faltas:",
            erro
        );

        listaFaltasElemento.textContent =
            "Erro ao conectar com o servidor.";

        totalFaltasElemento.textContent =
            "--";
    }
}


// =========================
// TABELA FALTAS
// =========================

function montarTabelaFaltas(
    dados
) {

    if (
        !dados ||
        dados.length === 0
    ) {

        listaFaltasElemento.textContent =
            "Nenhuma falta encontrada.";

        return;
    }

    let html = `
        <div class="tabela-container">

            <table>

                <thead>
                    <tr>
                        <th>Aluno</th>
                        <th>Matrícula</th>
                        <th>Turma</th>
                        <th>Disciplina</th>
                        <th>Professor</th>
                        <th>Quantidade</th>
                        <th>Data</th>
                    </tr>
                </thead>

                <tbody>
    `;

    dados.forEach(falta => {

        html += `
            <tr>

                <td>
                    ${falta.aluno ?? "--"}
                </td>

                <td>
                    ${falta.matriculaAluno ?? "--"}
                </td>

                <td>
                    ${falta.turma ?? "--"}
                </td>

                <td>
                    ${falta.disciplina ?? "--"}
                </td>

                <td>
                    ${falta.professor ?? "--"}
                </td>

                <td>
                    ${falta.quantidade ?? 0}
                </td>

                <td>
                    ${formatarData(
            falta.dataFalta
        )}
                </td>

            </tr>
        `;
    });

    html += `
                </tbody>
            </table>
        </div>
    `;

    listaFaltasElemento.innerHTML =
        html;
}

// =========================
// FILTROS DE AUDITORIA
// =========================

function obterUsuarioAuditoria(auditoria) {

    return (
        auditoria.emailUsuario ??
        auditoria.usuario ??
        auditoria.email ??
        "--"
    );
}


function obterAcaoAuditoria(auditoria) {

    return (
        auditoria.acao ??
        auditoria.tipoAcao ??
        "--"
    );
}


function obterDescricaoAuditoria(auditoria) {

    return (
        auditoria.descricao ??
        auditoria.detalhes ??
        "--"
    );
}


function obterDataHoraAuditoria(auditoria) {

    return (
        auditoria.dataHora ??
        auditoria.data ??
        auditoria.criadoEm ??
        null
    );
}


function atualizarFiltrosAuditoria() {

    preencherFiltro(
        filtroAuditoriaUsuario,
        [
            ...new Set(
                auditorias
                    .map(obterUsuarioAuditoria)
                    .filter(valor =>
                        valor &&
                        valor !== "--"
                    )
            )
        ].sort(
            (a, b) =>
                String(a).localeCompare(
                    String(b),
                    "pt-BR"
                )
        ),
        "Todos os usuários"
    );


    preencherFiltro(
        filtroAuditoriaAcao,
        [
            ...new Set(
                auditorias
                    .map(obterAcaoAuditoria)
                    .filter(valor =>
                        valor &&
                        valor !== "--"
                    )
            )
        ].sort(
            (a, b) =>
                String(a).localeCompare(
                    String(b),
                    "pt-BR"
                )
        ),
        "Todas as ações"
    );
}


function aplicarFiltrosAuditoria() {

    const usuario =
        filtroAuditoriaUsuario.value;

    const acao =
        filtroAuditoriaAcao.value;

    const descricao =
        filtroAuditoriaDescricao
            .value
            .trim()
            .toLowerCase();

    const dataSelecionada =
        filtroAuditoriaData.value;


    auditoriasFiltradas =
        auditorias.filter(
            auditoria => {

                const usuarioAuditoria =
                    obterUsuarioAuditoria(
                        auditoria
                    );

                const acaoAuditoria =
                    obterAcaoAuditoria(
                        auditoria
                    );

                const descricaoAuditoria =
                    String(
                        obterDescricaoAuditoria(
                            auditoria
                        )
                    ).toLowerCase();

                const dataHoraAuditoria =
                    obterDataHoraAuditoria(
                        auditoria
                    );


                const usuarioOk =
                    !usuario ||
                    usuarioAuditoria ===
                    usuario;

                const acaoOk =
                    !acao ||
                    acaoAuditoria ===
                    acao;

                const descricaoOk =
                    !descricao ||
                    descricaoAuditoria
                        .includes(
                            descricao
                        );


                let dataOk = true;

                if (
                    dataSelecionada &&
                    dataHoraAuditoria
                ) {

                    const dataAuditoria =
                        new Date(
                            dataHoraAuditoria
                        );

                    if (
                        !isNaN(
                            dataAuditoria.getTime()
                        )
                    ) {

                        const ano =
                            dataAuditoria
                                .getFullYear();

                        const mes =
                            String(
                                dataAuditoria
                                    .getMonth() + 1
                            ).padStart(
                                2,
                                "0"
                            );

                        const dia =
                            String(
                                dataAuditoria
                                    .getDate()
                            ).padStart(
                                2,
                                "0"
                            );

                        const dataFormatada =
                            `${ano}-${mes}-${dia}`;

                        dataOk =
                            dataFormatada ===
                            dataSelecionada;

                    } else {

                        dataOk =
                            false;
                    }
                }


                return (
                    usuarioOk &&
                    acaoOk &&
                    descricaoOk &&
                    dataOk
                );
            }
        );


    paginaAtualAuditoria = 1;

    renderizarPaginaAuditoria();
}


// =========================
// PAGINAÇÃO DA AUDITORIA
// =========================

function renderizarPaginaAuditoria() {

    const totalRegistros =
        auditoriasFiltradas.length;

    const totalPaginas =
        Math.max(
            1,
            Math.ceil(
                totalRegistros /
                registrosPorPaginaAuditoria
            )
        );


    if (
        paginaAtualAuditoria >
        totalPaginas
    ) {

        paginaAtualAuditoria =
            totalPaginas;
    }


    const inicio =
        (
            paginaAtualAuditoria - 1
        ) *
        registrosPorPaginaAuditoria;

    const fim =
        inicio +
        registrosPorPaginaAuditoria;


    const registrosDaPagina =
        auditoriasFiltradas.slice(
            inicio,
            fim
        );


    montarTabelaAuditorias(
        registrosDaPagina
    );


    contadorAuditorias.textContent =
        `${totalRegistros} de ${auditorias.length} registro(s) encontrado(s).`;


    montarPaginacaoAuditoria(
        totalPaginas
    );
}


function montarPaginacaoAuditoria(
    totalPaginas
) {

    if (
        totalPaginas <= 1
    ) {

        paginacaoAuditorias.innerHTML =
            "";

        return;
    }


    let html = `

        <button
            type="button"
            class="botao-paginacao"
            ${paginaAtualAuditoria === 1
        ? "disabled"
        : ""}
            onclick="mudarPaginaAuditoria(${paginaAtualAuditoria - 1})">

            Anterior

        </button>


        <span class="pagina-atual">

            Página ${paginaAtualAuditoria}
            de ${totalPaginas}

        </span>


        <button
            type="button"
            class="botao-paginacao"
            ${paginaAtualAuditoria === totalPaginas
        ? "disabled"
        : ""}
            onclick="mudarPaginaAuditoria(${paginaAtualAuditoria + 1})">

            Próxima

        </button>
    `;


    paginacaoAuditorias.innerHTML =
        html;
}


function mudarPaginaAuditoria(
    pagina
) {

    paginaAtualAuditoria =
        pagina;

    renderizarPaginaAuditoria();
}


// =========================
// EVENTOS DOS FILTROS DE AUDITORIA
// =========================

filtroAuditoriaUsuario.addEventListener(
    "change",
    aplicarFiltrosAuditoria
);


filtroAuditoriaAcao.addEventListener(
    "change",
    aplicarFiltrosAuditoria
);


filtroAuditoriaDescricao.addEventListener(
    "input",
    aplicarFiltrosAuditoria
);


filtroAuditoriaData.addEventListener(
    "change",
    aplicarFiltrosAuditoria
);


limparFiltrosAuditoria.addEventListener(
    "click",
    function () {

        filtroAuditoriaUsuario.value =
            "";

        filtroAuditoriaAcao.value =
            "";

        filtroAuditoriaDescricao.value =
            "";

        filtroAuditoriaData.value =
            "";

        atualizarFiltrosAuditoria();

        aplicarFiltrosAuditoria();
    }
);

// =========================
// AUDITORIAS
// =========================

async function carregarAuditorias() {

    try {

        const response =
            await requisicaoAutenticada(
                "/auditorias"
            );

        if (!response) {
            return;
        }

        if (!response.ok) {

            listaAuditoriasElemento.textContent =
                "Não foi possível carregar a auditoria.";

            return;
        }

        auditorias =
            await response.json();

        atualizarFiltrosAuditoria();

        auditoriasFiltradas =
            [...auditorias];

        paginaAtualAuditoria =
            1;

        renderizarPaginaAuditoria();

    } catch (erro) {

        console.error(
            "Erro ao carregar auditorias:",
            erro
        );

        listaAuditoriasElemento.textContent =
            "Erro ao conectar com o servidor.";
    }
}


// =========================
// TABELA AUDITORIA
// =========================

function montarTabelaAuditorias(
    dados
) {

    if (
        !dados ||
        dados.length === 0
    ) {

        listaAuditoriasElemento.textContent =
            "Nenhum registro de auditoria encontrado.";

        return;
    }

    let html = `
        <div class="tabela-container">

            <table>

                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Usuário</th>
                        <th>Ação</th>
                        <th>Descrição</th>
                        <th>Data/Hora</th>
                    </tr>
                </thead>

                <tbody>
    `;

    dados.forEach(auditoria => {

        const id =
            auditoria.idAuditoria ??
            auditoria.id ??
            "--";

        const usuario =
            auditoria.emailUsuario ??
            auditoria.usuario ??
            auditoria.email ??
            "--";

        const acao =
            auditoria.acao ??
            auditoria.tipoAcao ??
            "--";

        const descricao =
            auditoria.descricao ??
            auditoria.detalhes ??
            "--";

        const dataHora =
            auditoria.dataHora ??
            auditoria.data ??
            auditoria.criadoEm ??
            "--";

        html += `
            <tr>

                <td>${id}</td>

                <td>${usuario}</td>

                <td>${acao}</td>

                <td>${descricao}</td>

                <td>
                    ${formatarDataHora(
            dataHora
        )}
                </td>

            </tr>
        `;
    });

    html += `
                </tbody>
            </table>
        </div>
    `;

    listaAuditoriasElemento.innerHTML =
        html;
}


// =========================
// ABRIR NOVO USUÁRIO
// =========================

function abrirModalUsuario() {

    formUsuario.reset();

    mensagemUsuario.textContent =
        "";

    mensagemUsuario.className =
        "mensagem-formulario";

    modalUsuario.classList.add(
        "ativo"
    );
}


function fecharModalUsuario() {

    modalUsuario.classList.remove(
        "ativo"
    );

    formUsuario.reset();

    mensagemUsuario.textContent =
        "";

    mensagemUsuario.className =
        "mensagem-formulario";
}


botaoAbrirUsuario.addEventListener(
    "click",
    abrirModalUsuario
);


botaoFecharUsuario.addEventListener(
    "click",
    fecharModalUsuario
);


modalUsuario.addEventListener(
    "click",
    function (event) {

        if (
            event.target ===
            modalUsuario
        ) {

            fecharModalUsuario();
        }
    }
);

// =========================
// ABRIR NOVA TURMA
// =========================

function abrirModalTurma() {

    formTurma.reset();

    mensagemTurma.textContent =
        "";

    mensagemTurma.className =
        "mensagem-formulario";

    modalTurma.classList.add(
        "ativo"
    );
}


// =========================
// FECHAR NOVA TURMA
// =========================

function fecharModalTurma() {

    modalTurma.classList.remove(
        "ativo"
    );

    formTurma.reset();

    mensagemTurma.textContent =
        "";

    mensagemTurma.className =
        "mensagem-formulario";
}


// =========================
// EVENTOS NOVA TURMA
// =========================

botaoAbrirTurma.addEventListener(
    "click",
    abrirModalTurma
);

botaoFecharTurma.addEventListener(
    "click",
    fecharModalTurma
);

modalTurma.addEventListener(
    "click",
    function (event) {

        if (event.target === modalTurma) {

            fecharModalTurma();
        }
    }
);


// =========================
// SALVAR NOVA TURMA
// =========================

formTurma.addEventListener(
    "submit",
    async function (event) {

        event.preventDefault();

        const nome =
            turmaNome.value.trim();

        const semestre =
            Number(turmaSemestre.value);

        const ano =
            Number(turmaAno.value);

        if (!nome) {

            mensagemTurma.className =
                "mensagem-formulario erro";

            mensagemTurma.textContent =
                "Informe o nome da turma.";

            return;
        }

        if (!semestre) {

            mensagemTurma.className =
                "mensagem-formulario erro";

            mensagemTurma.textContent =
                "Selecione o semestre.";

            return;
        }

        if (!ano) {

            mensagemTurma.className =
                "mensagem-formulario erro";

            mensagemTurma.textContent =
                "Informe o ano.";

            return;
        }

        mensagemTurma.className =
            "mensagem-formulario";

        mensagemTurma.textContent =
            "Salvando turma...";

        const dados = {
            nome: nome,
            semestre: semestre,
            ano: ano
        };

        try {

            const response =
                await requisicaoAutenticada(
                    "/turmas",
                    {
                        method: "POST",

                        body:
                            JSON.stringify(
                                dados
                            )
                    }
                );

            if (!response) {
                return;
            }

            const resposta =
                await lerJsonSeguro(
                    response
                );

            if (!response.ok) {

                mensagemTurma.className =
                    "mensagem-formulario erro";

                mensagemTurma.textContent =
                    resposta?.mensagem ??
                    "Não foi possível cadastrar a turma.";

                return;
            }

            mensagemTurma.className =
                "mensagem-formulario sucesso";

            mensagemTurma.textContent =
                "Turma cadastrada com sucesso!";

            await carregarTurmas();

            setTimeout(
                fecharModalTurma,
                900
            );

        } catch (erro) {

            console.error(
                "Erro ao cadastrar turma:",
                erro
            );

            mensagemTurma.className =
                "mensagem-formulario erro";

            mensagemTurma.textContent =
                "Erro ao conectar com o servidor.";
        }
    }
);

// =========================
// ABRIR NOVA DISCIPLINA
// =========================

function abrirModalDisciplina() {

    formDisciplina.reset();

    mensagemDisciplina.textContent =
        "";

    mensagemDisciplina.className =
        "mensagem-formulario";

    modalDisciplina.classList.add(
        "ativo"
    );
}


// =========================
// FECHAR NOVA DISCIPLINA
// =========================

function fecharModalDisciplina() {

    modalDisciplina.classList.remove(
        "ativo"
    );

    formDisciplina.reset();

    mensagemDisciplina.textContent =
        "";

    mensagemDisciplina.className =
        "mensagem-formulario";
}


// =========================
// EVENTOS NOVA DISCIPLINA
// =========================

botaoAbrirDisciplina.addEventListener(
    "click",
    abrirModalDisciplina
);

botaoFecharDisciplina.addEventListener(
    "click",
    fecharModalDisciplina
);

modalDisciplina.addEventListener(
    "click",
    function (event) {

        if (event.target === modalDisciplina) {

            fecharModalDisciplina();
        }
    }
);

// =========================
// SALVAR NOVA DISCIPLINA
// =========================

formDisciplina.addEventListener(
    "submit",
    async function (event) {

        event.preventDefault();

        const nome =
            disciplinaNome.value.trim();

        const cargaHoraria =
            Number(
                disciplinaCargaHoraria.value
            );

        if (!nome) {

            mensagemDisciplina.className =
                "mensagem-formulario erro";

            mensagemDisciplina.textContent =
                "Informe o nome da disciplina.";

            return;
        }

        if (!cargaHoraria || cargaHoraria <= 0) {

            mensagemDisciplina.className =
                "mensagem-formulario erro";

            mensagemDisciplina.textContent =
                "Informe uma carga horária válida.";

            return;
        }

        mensagemDisciplina.className =
            "mensagem-formulario";

        mensagemDisciplina.textContent =
            "Salvando disciplina...";

        const dados = {
            nome: nome,
            cargaHoraria: cargaHoraria
        };

        try {

            const response =
                await requisicaoAutenticada(
                    "/disciplinas",
                    {
                        method: "POST",

                        body:
                            JSON.stringify(
                                dados
                            )
                    }
                );

            if (!response) {
                return;
            }

            const resposta =
                await lerJsonSeguro(
                    response
                );

            if (!response.ok) {

                mensagemDisciplina.className =
                    "mensagem-formulario erro";

                mensagemDisciplina.textContent =
                    resposta?.mensagem ??
                    "Não foi possível cadastrar a disciplina.";

                return;
            }

            mensagemDisciplina.className =
                "mensagem-formulario sucesso";

            mensagemDisciplina.textContent =
                "Disciplina cadastrada com sucesso!";

            await carregarDisciplinas();

            setTimeout(
                fecharModalDisciplina,
                900
            );

        } catch (erro) {

            console.error(
                "Erro ao cadastrar disciplina:",
                erro
            );

            mensagemDisciplina.className =
                "mensagem-formulario erro";

            mensagemDisciplina.textContent =
                "Erro ao conectar com o servidor.";
        }
    }
);

// =========================
// CRIAR USUÁRIO
// =========================

formUsuario.addEventListener(
    "submit",
    async function (event) {

        event.preventDefault();

        mensagemUsuario.className =
            "mensagem-formulario";

        mensagemUsuario.textContent =
            "Salvando usuário...";

        const dados = {

            nome:
                usuarioNome
                    .value
                    .trim(),

            email:
                usuarioEmail
                    .value
                    .trim(),

            senha:
            usuarioSenha.value,

            perfil:
            usuarioPerfil.value
        };

        try {

            const response =
                await requisicaoAutenticada(
                    "/usuarios",
                    {
                        method:
                            "POST",

                        body:
                            JSON.stringify(
                                dados
                            )
                    }
                );

            if (!response) {
                return;
            }

            const resposta =
                await lerJsonSeguro(
                    response
                );

            if (!response.ok) {

                mensagemUsuario.className =
                    "mensagem-formulario erro";

                mensagemUsuario.textContent =
                    resposta?.mensagem ??
                    "Não foi possível cadastrar o usuário.";

                return;
            }

            mensagemUsuario.className =
                "mensagem-formulario sucesso";

            mensagemUsuario.textContent =
                "Usuário cadastrado com sucesso!";

            await carregarUsuarios();

            await carregarAuditorias();

            setTimeout(
                fecharModalUsuario,
                900
            );

        } catch (erro) {

            console.error(
                "Erro ao cadastrar usuário:",
                erro
            );

            mensagemUsuario.className =
                "mensagem-formulario erro";

            mensagemUsuario.textContent =
                "Erro ao conectar com o servidor.";
        }
    }
);


// =========================
// ABRIR EDIÇÃO DE USUÁRIO
// =========================

function abrirModalEditarUsuario(
    id
) {

    const usuario =
        usuarios.find(
            item =>
                item.idUsuario === id
        );

    if (!usuario) {

        alert(
            "Usuário não encontrado."
        );

        return;
    }

    editarUsuarioId.value =
        usuario.idUsuario;

    editarUsuarioNome.value =
        usuario.nome ?? "";

    editarUsuarioEmail.value =
        usuario.email ?? "";

    editarUsuarioPerfil.value =
        usuario.perfil ?? "";

    const possuiVinculoAcademico =
        alunos.some(
            aluno =>
                aluno.usuario?.idUsuario ===
                usuario.idUsuario
        ) ||
        professores.some(
            professor =>
                professor.usuario?.idUsuario ===
                usuario.idUsuario
        );

    editarUsuarioPerfil.disabled =
        possuiVinculoAcademico;

    editarUsuarioPerfil.title =
        possuiVinculoAcademico
            ? "O perfil não pode ser alterado porque este usuário possui vínculo acadêmico."
            : "";

    editarUsuarioSenha.value =
        "";

    mensagemEditarUsuario.textContent =
        "";

    mensagemEditarUsuario.className =
        "mensagem-formulario";

    modalEditarUsuario.classList.add(
        "ativo"
    );
}


// =========================
// FECHAR EDIÇÃO USUÁRIO
// =========================

function fecharModalEditarUsuario() {

    modalEditarUsuario.classList.remove(
        "ativo"
    );

    formEditarUsuario.reset();

    editarUsuarioPerfil.disabled = false;
    editarUsuarioPerfil.title = "";

    editarUsuarioId.value =
        "";

    mensagemEditarUsuario.textContent =
        "";

    mensagemEditarUsuario.className =
        "mensagem-formulario";
}


botaoFecharEditarUsuario.addEventListener(
    "click",
    fecharModalEditarUsuario
);


modalEditarUsuario.addEventListener(
    "click",
    function (event) {

        if (
            event.target ===
            modalEditarUsuario
        ) {

            fecharModalEditarUsuario();
        }
    }
);


// =========================
// SALVAR EDIÇÃO USUÁRIO
// =========================

formEditarUsuario.addEventListener(
    "submit",
    async function (event) {

        event.preventDefault();

        const id =
            Number(
                editarUsuarioId.value
            );

        if (!id) {

            mensagemEditarUsuario.className =
                "mensagem-formulario erro";

            mensagemEditarUsuario.textContent =
                "Usuário inválido.";

            return;
        }

        mensagemEditarUsuario.className =
            "mensagem-formulario";

        mensagemEditarUsuario.textContent =
            "Salvando alterações...";

        const dados = {

            nome:
                editarUsuarioNome
                    .value
                    .trim(),

            email:
                editarUsuarioEmail
                    .value
                    .trim(),

            perfil:
            editarUsuarioPerfil.value
        };

        if (
            editarUsuarioSenha
                .value
                .trim()
        ) {

            dados.senha =
                editarUsuarioSenha.value;
        }

        try {

            const response =
                await requisicaoAutenticada(
                    `/usuarios/${id}`,
                    {
                        method:
                            "PUT",

                        body:
                            JSON.stringify(
                                dados
                            )
                    }
                );

            if (!response) {
                return;
            }

            const resposta =
                await lerJsonSeguro(
                    response
                );

            if (!response.ok) {

                mensagemEditarUsuario.className =
                    "mensagem-formulario erro";

                mensagemEditarUsuario.textContent =
                    resposta?.mensagem ??
                    "Não foi possível atualizar o usuário.";

                return;
            }

            mensagemEditarUsuario.className =
                "mensagem-formulario sucesso";

            mensagemEditarUsuario.textContent =
                "Usuário atualizado com sucesso!";

            await carregarUsuarios();
            await carregarProfessores();
            await carregarAuditorias();

            if (
                id === idUsuarioLogado &&
                dados.perfil !==
                "ADMINISTRADOR"
            ) {

                localStorage.clear();

                alert(
                    "Seu perfil foi alterado. Faça login novamente."
                );

                window.location.href =
                    "/login.html";

                return;
            }

            setTimeout(
                fecharModalEditarUsuario,
                900
            );

        } catch (erro) {

            console.error(
                "Erro ao atualizar usuário:",
                erro
            );

            mensagemEditarUsuario.className =
                "mensagem-formulario erro";

            mensagemEditarUsuario.textContent =
                "Erro ao conectar com o servidor.";
        }
    }
);


// =========================
// EXCLUIR USUÁRIO
// =========================

async function excluirUsuario(
    id
) {

    if (
        id ===
        idUsuarioLogado
    ) {

        alert(
            "Você não pode excluir o usuário da sessão atual."
        );

        return;
    }

    const usuario =
        usuarios.find(
            item =>
                item.idUsuario === id
        );

    if (!usuario) {

        alert(
            "Usuário não encontrado."
        );

        return;
    }

    const confirmar =
        window.confirm(
            `Deseja realmente excluir o usuário "${usuario.nome}"?`
        );

    if (!confirmar) {
        return;
    }

    try {

        const response =
            await requisicaoAutenticada(
                `/usuarios/${id}`,
                {
                    method:
                        "DELETE"
                }
            );

        if (!response) {
            return;
        }

        if (!response.ok) {

            const resposta =
                await lerJsonSeguro(
                    response
                );

            alert(
                resposta?.mensagem ??
                "Não foi possível excluir o usuário."
            );

            return;
        }

        await carregarUsuarios();
        await carregarProfessores();
        await carregarAuditorias();

        alert(
            "Usuário excluído com sucesso."
        );

    } catch (erro) {

        console.error(
            "Erro ao excluir usuário:",
            erro
        );

        alert(
            "Erro ao conectar com o servidor."
        );
    }
}


// =========================
// BADGE PERFIL
// =========================

function montarBadgePerfil(
    perfilUsuario
) {

    if (
        perfilUsuario ===
        "ALUNO"
    ) {

        return `
            <span class="badge badge-aluno">
                ALUNO
            </span>
        `;
    }

    if (
        perfilUsuario ===
        "PROFESSOR"
    ) {

        return `
            <span class="badge badge-professor">
                PROFESSOR
            </span>
        `;
    }

    if (
        perfilUsuario ===
        "ADMINISTRADOR"
    ) {

        return `
            <span class="badge badge-admin">
                ADMINISTRADOR
            </span>
        `;
    }

    return perfilUsuario ?? "--";
}


// =========================
// JSON SEGURO
// =========================

async function lerJsonSeguro(
    response
) {

    try {

        return await response.json();

    } catch {

        return null;
    }
}


// =========================
// FORMATAR NOTA
// =========================

function formatarNota(
    valor
) {

    if (
        valor === null ||
        valor === undefined
    ) {

        return "--";
    }

    return Number(valor)
        .toFixed(2);
}


// =========================
// FORMATAR DATA
// =========================

function formatarData(
    data
) {

    if (!data) {

        return "--";
    }

    const texto =
        String(data);

    if (
        !texto.includes("-")
    ) {

        return texto;
    }

    const partes =
        texto.split("-");

    if (
        partes.length !== 3
    ) {

        return texto;
    }

    return `${partes[2]}/${partes[1]}/${partes[0]}`;
}


// =========================
// FORMATAR DATA/HORA
// =========================

function formatarDataHora(
    dataHora
) {

    if (
        !dataHora ||
        dataHora === "--"
    ) {

        return "--";
    }

    try {

        const data =
            new Date(
                dataHora
            );

        if (
            isNaN(
                data.getTime()
            )
        ) {

            return dataHora;
        }

        return data.toLocaleString(
            "pt-BR"
        );

    } catch {

        return dataHora;
    }
}


// =========================
// MENSAGEM INICIAL
// =========================

function atualizarMensagemInicio() {

    mensagemInicioElemento.textContent =
        "Utilize o menu lateral para gerenciar usuários, professores, vínculos acadêmicos, notas, faltas e consultar os registros de auditoria.";
}


// =========================
// LOGOUT
// =========================

botaoSair.addEventListener(
    "click",
    function () {

        localStorage.clear();

        window.location.href =
            "/login.html";
    }
);

// =====================================================
// MENU MOBILE - ADMIN
// =====================================================

const botaoMenuMobileAdmin =
    document.getElementById(
        "botaoMenuMobileAdmin"
    );

const sidebarAdmin =
    document.getElementById(
        "sidebarAdmin"
    );

const overlayMenuAdmin =
    document.getElementById(
        "overlayMenuAdmin"
    );


function atualizarMenuMobileAdmin(
    menuAberto
) {

    if (
        !botaoMenuMobileAdmin ||
        !sidebarAdmin ||
        !overlayMenuAdmin
    ) {
        return;
    }


    sidebarAdmin.classList.toggle(
        "aberta",
        menuAberto
    );


    overlayMenuAdmin.classList.toggle(
        "ativo",
        menuAberto
    );


    botaoMenuMobileAdmin.textContent =
        menuAberto
            ? "✕"
            : "☰";


    botaoMenuMobileAdmin.setAttribute(
        "aria-expanded",
        String(menuAberto)
    );


    botaoMenuMobileAdmin.setAttribute(
        "aria-label",
        menuAberto
            ? "Fechar menu"
            : "Abrir menu"
    );
}


if (
    botaoMenuMobileAdmin &&
    sidebarAdmin &&
    overlayMenuAdmin
) {

    botaoMenuMobileAdmin.addEventListener(
        "click",
        function () {

            const menuAberto =
                !sidebarAdmin
                    .classList
                    .contains("aberta");

            atualizarMenuMobileAdmin(
                menuAberto
            );
        }
    );


    overlayMenuAdmin.addEventListener(
        "click",
        function () {

            atualizarMenuMobileAdmin(
                false
            );
        }
    );


    document
        .querySelectorAll(".menu-item")
        .forEach(function (item) {

            item.addEventListener(
                "click",
                function () {

                    if (
                        window.innerWidth <= 768
                    ) {
                        atualizarMenuMobileAdmin(
                            false
                        );
                    }
                }
            );
        });
}


window.addEventListener(
    "resize",
    function () {

        if (
            window.innerWidth > 768
        ) {
            atualizarMenuMobileAdmin(
                false
            );
        }
    }
);

// =========================
// INICIALIZAÇÃO
// =========================

carregarDados();
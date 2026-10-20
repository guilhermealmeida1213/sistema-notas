// =====================================================
// AUTENTICAÇÃO E SESSÃO
// =====================================================
const token = localStorage.getItem("token");
const perfil = localStorage.getItem("perfil");
const idUsuarioLogado = Number(localStorage.getItem("idUsuario"));

if (!token || perfil !== "ADMINISTRADOR") {
    localStorage.clear();
    window.location.href = "/login.html";
}

// =====================================================
// ELEMENTOS DO DOM (MAPEAMENTO GLOBAL)
// =====================================================

// Navegação e Layout
const menuItens = document.querySelectorAll(".menu-item");
const secoes = document.querySelectorAll(".secao");
const botaoSair = document.getElementById("botaoSair");
const botaoMenuMobileAdmin = document.getElementById("botaoMenuMobileAdmin");
const sidebarAdmin = document.getElementById("sidebarAdmin");
const overlayMenuAdmin = document.getElementById("overlayMenuAdmin");

// Cards e Mensagens
const totalUsuariosElemento = document.getElementById("totalUsuarios");
const totalProfessoresElemento = document.getElementById("totalProfessores");
const totalNotasElemento = document.getElementById("totalNotas");
const totalFaltasElemento = document.getElementById("totalFaltas");
const mensagemInicioElemento = document.getElementById("mensagemInicio");

// Listas / Tabelas
const listaUsuariosElemento = document.getElementById("listaUsuarios");
const listaProfessoresElemento = document.getElementById("listaProfessores");
const listaTurmasElemento = document.getElementById("listaTurmas");
const listaDisciplinasElemento = document.getElementById("listaDisciplinas");
const listaVinculosElemento = document.getElementById("listaVinculos");
const listaNotasElemento = document.getElementById("listaNotas");
const listaFaltasElemento = document.getElementById("listaFaltas");
const listaAuditoriasElemento = document.getElementById("listaAuditorias");
const listaMatriculasElemento = document.getElementById("listaMatriculas");

// Modais - Usuário
const modalUsuario = document.getElementById("modalUsuario");
const botaoAbrirUsuario = document.getElementById("botaoAbrirUsuario");
const botaoFecharUsuario = document.getElementById("botaoFecharUsuario");
const formUsuario = document.getElementById("formUsuario");
const usuarioNome = document.getElementById("usuarioNome");
const usuarioEmail = document.getElementById("usuarioEmail");
const usuarioSenha = document.getElementById("usuarioSenha");
const usuarioPerfil = document.getElementById("usuarioPerfil");
const mensagemUsuario = document.getElementById("mensagemUsuario");

// Modais - Editar Usuário
const modalEditarUsuario = document.getElementById("modalEditarUsuario");
const botaoFecharEditarUsuario = document.getElementById("botaoFecharEditarUsuario");
const formEditarUsuario = document.getElementById("formEditarUsuario");
const editarUsuarioId = document.getElementById("editarUsuarioId");
const editarUsuarioNome = document.getElementById("editarUsuarioNome");
const editarUsuarioEmail = document.getElementById("editarUsuarioEmail");
const editarUsuarioPerfil = document.getElementById("editarUsuarioPerfil");
const editarUsuarioSenha = document.getElementById("editarUsuarioSenha");
const mensagemEditarUsuario = document.getElementById("mensagemEditarUsuario");

// Modais - Aluno
const modalAluno = document.getElementById("modalAluno");
const botaoAbrirAluno = document.getElementById("botaoAbrirAluno");
const botaoFecharAluno = document.getElementById("botaoFecharAluno");
const formAluno = document.getElementById("formAluno");
const alunoUsuario = document.getElementById("alunoUsuario");
const alunoMatricula = document.getElementById("alunoMatricula");
const mensagemAluno = document.getElementById("mensagemAluno");

// Modais - Professor
const modalProfessor = document.getElementById("modalProfessor");
const botaoAbrirProfessor = document.getElementById("botaoAbrirProfessor");
const botaoFecharProfessor = document.getElementById("botaoFecharProfessor");
const formProfessor = document.getElementById("formProfessor");
const professorUsuario = document.getElementById("professorUsuario");
const professorRegistro = document.getElementById("professorRegistro");
const mensagemProfessor = document.getElementById("mensagemProfessor");

// Modais - Editar Professor
const modalEditarProfessor = document.getElementById("modalEditarProfessor");
const botaoFecharEditarProfessor = document.getElementById("botaoFecharEditarProfessor");
const formEditarProfessor = document.getElementById("formEditarProfessor");
const editarProfessorId = document.getElementById("editarProfessorId");
const editarProfessorNome = document.getElementById("editarProfessorNome");
const editarProfessorRegistro = document.getElementById("editarProfessorRegistro");
const mensagemEditarProfessor = document.getElementById("mensagemEditarProfessor");

// Modais - Turma
const modalTurma = document.getElementById("modalTurma");
const botaoAbrirTurma = document.getElementById("botaoAbrirTurma");
const botaoFecharTurma = document.getElementById("botaoFecharTurma");
const formTurma = document.getElementById("formTurma");
const turmaNome = document.getElementById("turmaNome");
const turmaSemestre = document.getElementById("turmaSemestre");
const turmaAno = document.getElementById("turmaAno");
const mensagemTurma = document.getElementById("mensagemTurma");

// Modais - Editar Turma
const modalEditarTurma = document.getElementById("modalEditarTurma");
const botaoFecharEditarTurma = document.getElementById("botaoFecharEditarTurma");
const formEditarTurma = document.getElementById("formEditarTurma");
const editarTurmaId = document.getElementById("editarTurmaId");
const editarTurmaNome = document.getElementById("editarTurmaNome");
const editarTurmaSemestre = document.getElementById("editarTurmaSemestre");
const editarTurmaAno = document.getElementById("editarTurmaAno");
const mensagemEditarTurma = document.getElementById("mensagemEditarTurma");

// Modais - Disciplina
const modalDisciplina = document.getElementById("modalDisciplina");
const botaoAbrirDisciplina = document.getElementById("botaoAbrirDisciplina");
const botaoFecharDisciplina = document.getElementById("botaoFecharDisciplina");
const formDisciplina = document.getElementById("formDisciplina");
const disciplinaNome = document.getElementById("disciplinaNome");
const disciplinaCargaHoraria = document.getElementById("disciplinaCargaHoraria");
const mensagemDisciplina = document.getElementById("mensagemDisciplina");

// Modais - Editar Disciplina
const modalEditarDisciplina = document.getElementById("modalEditarDisciplina");
const botaoFecharEditarDisciplina = document.getElementById("botaoFecharEditarDisciplina");
const formEditarDisciplina = document.getElementById("formEditarDisciplina");
const editarDisciplinaId = document.getElementById("editarDisciplinaId");
const editarDisciplinaNome = document.getElementById("editarDisciplinaNome");
const editarDisciplinaCargaHoraria = document.getElementById("editarDisciplinaCargaHoraria");
const mensagemEditarDisciplina = document.getElementById("mensagemEditarDisciplina");

// Modais - Vínculo Acadêmico
const modalVinculo = document.getElementById("modalVinculo");
const botaoAbrirVinculo = document.getElementById("botaoAbrirVinculo");
const botaoFecharVinculo = document.getElementById("botaoFecharVinculo");
const formVinculo = document.getElementById("formVinculo");
const vinculoTurma = document.getElementById("vinculoTurma");
const vinculoDisciplina = document.getElementById("vinculoDisciplina");
const vinculoProfessor = document.getElementById("vinculoProfessor");
const mensagemVinculo = document.getElementById("mensagemVinculo");

// Modais - Trocar Professor
const modalTrocarProfessor = document.getElementById("modalTrocarProfessor");
const botaoFecharTrocarProfessor = document.getElementById("botaoFecharTrocarProfessor");
const formTrocarProfessor = document.getElementById("formTrocarProfessor");
const trocarProfessorVinculoId = document.getElementById("trocarProfessorVinculoId");
const trocarProfessorTurmaDisciplina = document.getElementById("trocarProfessorTurmaDisciplina");
const trocarProfessorAtual = document.getElementById("trocarProfessorAtual");
const trocarProfessorNovo = document.getElementById("trocarProfessorNovo");
const mensagemTrocarProfessor = document.getElementById("mensagemTrocarProfessor");

// Modais - Matrícula
const modalMatricula = document.getElementById("modalMatricula");
const botaoAbrirMatricula = document.getElementById("botaoAbrirMatricula");
const botaoFecharMatricula = document.getElementById("botaoFecharMatricula");
const formMatricula = document.getElementById("formMatricula");
const matriculaAluno = document.getElementById("matriculaAluno");
const matriculaTurma = document.getElementById("matriculaTurma");
const matriculaSemestreAluno = document.getElementById("matriculaSemestreAluno");
const matriculaData = document.getElementById("matriculaData");
const matriculaStatus = document.getElementById("matriculaStatus");
const mensagemMatricula = document.getElementById("mensagemMatricula");

// Modais - Editar Matrícula
const modalEditarMatricula = document.getElementById("modalEditarMatricula");
const botaoFecharEditarMatricula = document.getElementById("botaoFecharEditarMatricula");
const formEditarMatricula = document.getElementById("formEditarMatricula");
const editarMatriculaId = document.getElementById("editarMatriculaId");
const editarMatriculaAluno = document.getElementById("editarMatriculaAluno");
const editarMatriculaTurma = document.getElementById("editarMatriculaTurma");
const editarMatriculaSemestreAluno = document.getElementById("editarMatriculaSemestreAluno");
const editarMatriculaData = document.getElementById("editarMatriculaData");
const editarMatriculaStatus = document.getElementById("editarMatriculaStatus");
const mensagemEditarMatricula = document.getElementById("mensagemEditarMatricula");

// Modais - Lançar AVI
const modalAvi = document.getElementById("modalAvi");
const botaoAbrirAvi = document.getElementById("botaoAbrirAvi");
const botaoFecharAvi = document.getElementById("botaoFecharAvi");
const formAvi = document.getElementById("formAvi");
const aviMatricula = document.getElementById("aviMatricula");
const aviAlunoNome = document.getElementById("aviAlunoNome");
const aviTurmaNome = document.getElementById("aviTurmaNome");
const aviValor = document.getElementById("aviValor");
const aviData = document.getElementById("aviData");
const mensagemAvi = document.getElementById("mensagemAvi");
let aviIdMatriculaSelecionada = null;

// Filtros de Notas
const filtroNotaTurma = document.getElementById("filtroNotaTurma");
const filtroNotaDisciplina = document.getElementById("filtroNotaDisciplina");
const filtroNotaAluno = document.getElementById("filtroNotaAluno");
const filtroNotaProfessor = document.getElementById("filtroNotaProfessor");
const filtroNotaAvaliacao = document.getElementById("filtroNotaAvaliacao");
const limparFiltrosNotas = document.getElementById("limparFiltrosNotas");
const contadorNotas = document.getElementById("contadorNotas");

// Filtros de Faltas
const filtroFaltaTurma = document.getElementById("filtroFaltaTurma");
const filtroFaltaDisciplina = document.getElementById("filtroFaltaDisciplina");
const filtroFaltaAluno = document.getElementById("filtroFaltaAluno");
const filtroFaltaProfessor = document.getElementById("filtroFaltaProfessor");
const limparFiltrosFaltas = document.getElementById("limparFiltrosFaltas");
const contadorFaltas = document.getElementById("contadorFaltas");

// Filtros de Auditoria
const filtroAuditoriaUsuario = document.getElementById("filtroAuditoriaUsuario");
const filtroAuditoriaAcao = document.getElementById("filtroAuditoriaAcao");
const filtroAuditoriaDescricao = document.getElementById("filtroAuditoriaDescricao");
const filtroAuditoriaData = document.getElementById("filtroAuditoriaData");
const limparFiltrosAuditoria = document.getElementById("limparFiltrosAuditoria");
const contadorAuditorias = document.getElementById("contadorAuditorias");
const paginacaoAuditorias = document.getElementById("paginacaoAuditorias");

// =====================================================
// ESTADO / DADOS EM MEMÓRIA
// =====================================================
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

// =====================================================
// REQUISIÇÃO AUTENTICADA (CLIENT API)
// =====================================================
async function requisicaoAutenticada(url, opcoes = {}) {
    const configuracao = {
        ...opcoes,
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`,
            ...(opcoes.headers || {})
        }
    };
    try {
        const response = await fetch(url, configuracao);
        if (response.status === 401) {
            localStorage.clear();
            window.location.href = "/login.html";
            return null;
        }
        if (response.status === 403) {
            console.error("Acesso negado para:", url);
        }
        return response;
    } catch (erro) {
        console.error("Erro ao conectar com o servidor:", erro);
        return null;
    }
}

// =====================================================
// CARREGAMENTO GLOBAL
// =====================================================
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

// =====================================================
// GESTÃO DE USUÁRIOS
// =====================================================
async function carregarUsuarios() {
    try {
        const response = await requisicaoAutenticada("/usuarios");
        if (!response || !response.ok) {
            if (listaUsuariosElemento) listaUsuariosElemento.innerHTML = "<p>Não foi possível carregar os usuários.</p>";
            atualizarContadorUsuarios(0);
            return;
        }
        usuarios = await response.json();
        if (totalUsuariosElemento) totalUsuariosElemento.textContent = usuarios.length;
        aplicarFiltrosUsuarios();
    } catch (erro) {
        console.error("Erro ao carregar usuários:", erro);
        if (listaUsuariosElemento) listaUsuariosElemento.innerHTML = "<p>Erro ao carregar usuários.</p>";
        atualizarContadorUsuarios(0);
    }
}

function aplicarFiltrosUsuarios() {
    const busca = document.getElementById("filtroUsuarioBusca")?.value.trim().toLowerCase() || "";
    const perfilFiltro = document.getElementById("filtroUsuarioPerfil")?.value || "";
    const status = document.getElementById("filtroUsuarioStatus")?.value || "";

    const usuariosFiltrados = usuarios.filter(usuario => {
        const nome = (usuario.nome || "").toLowerCase();
        const email = (usuario.email || "").toLowerCase();
        const perfilUsuario = usuario.perfil || "";
        const statusUsuario = usuario.ativo === false ? "INATIVO" : "ATIVO";

        const correspondeBusca = !busca || nome.includes(busca) || email.includes(busca);
        const correspondePerfil = !perfilFiltro || perfilUsuario === perfilFiltro;
        const correspondeStatus = !status || statusUsuario === status;

        return correspondeBusca && correspondePerfil && correspondeStatus;
    });

    montarTabelaUsuarios(usuariosFiltrados);
    atualizarContadorUsuarios(usuariosFiltrados.length);
}

function atualizarContadorUsuarios(quantidadeExibida) {
    const contador = document.getElementById("contadorUsuarios");
    if (contador) {
        contador.textContent = `${quantidadeExibida} de ${usuarios.length} usuário(s) exibido(s).`;
    }
}

function limparFiltrosUsuarios() {
    if (document.getElementById("filtroUsuarioBusca")) document.getElementById("filtroUsuarioBusca").value = "";
    if (document.getElementById("filtroUsuarioPerfil")) document.getElementById("filtroUsuarioPerfil").value = "";
    if (document.getElementById("filtroUsuarioStatus")) document.getElementById("filtroUsuarioStatus").value = "";
    aplicarFiltrosUsuarios();
}

function montarTabelaUsuarios(dados) {
    if (!listaUsuariosElemento) return;
    if (!dados || dados.length === 0) {
        listaUsuariosElemento.textContent = "Nenhum usuário cadastrado.";
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
                        <th>Status</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
    `;
    dados.forEach(usuario => {
        const proprioUsuario = usuario.idUsuario === idUsuarioLogado;
        html += `
            <tr>
                <td>${usuario.idUsuario ?? "--"}</td>
                <td>${usuario.nome ?? "--"}</td>
                <td>${usuario.email ?? "--"}</td>
                <td>${montarBadgePerfil(usuario.perfil)}</td>
                <td>${usuario.ativo === false ? "Inativo" : "Ativo"}</td>
                <td>
                    <button type="button" class="botao-secundario" onclick="abrirModalEditarUsuario(${usuario.idUsuario})">
                        Editar
                    </button>
                    ${proprioUsuario ? `
                        <span title="Você não pode desativar o usuário da sessão atual">--</span>
                    ` : `
                        <button type="button" class="${usuario.ativo === false ? "botao-secundario" : "botao-perigo"}" onclick="alterarStatusUsuario(${usuario.idUsuario},${usuario.ativo === false})">
                            ${usuario.ativo === false ? "Reativar" : "Desativar"}
                        </button>
                    `}
                </td>
            </tr>
        `;
    });
    html += `</tbody></table></div>`;
    listaUsuariosElemento.innerHTML = html;
}

function abrirModalUsuario() {
    if (!formUsuario) return;
    formUsuario.reset();
    mensagemUsuario.textContent = "";
    mensagemUsuario.className = "mensagem-formulario";
    modalUsuario.classList.add("ativo");
}

function fecharModalUsuario() {
    if (!modalUsuario) return;
    modalUsuario.classList.remove("ativo");
    formUsuario.reset();
    mensagemUsuario.textContent = "";
    mensagemUsuario.className = "mensagem-formulario";
}

if (formUsuario) {
    formUsuario.addEventListener("submit", async function (event) {
        event.preventDefault();
        mensagemUsuario.className = "mensagem-formulario";
        mensagemUsuario.textContent = "Salvando usuário...";

        const dados = {
            nome: usuarioNome.value.trim(),
            email: usuarioEmail.value.trim(),
            senha: usuarioSenha.value,
            perfil: usuarioPerfil.value
        };

        try {
            const response = await requisicaoAutenticada("/usuarios", { method: "POST", body: JSON.stringify(dados) });
            if (!response) return;
            const resposta = await lerJsonSeguro(response);
            if (!response.ok) {
                mensagemUsuario.className = "mensagem-formulario erro";
                mensagemUsuario.textContent = resposta?.mensagem ?? "Não foi possível cadastrar o usuário.";
                return;
            }
            mensagemUsuario.className = "mensagem-formulario sucesso";
            mensagemUsuario.textContent = "Usuário cadastrado com sucesso!";
            await carregarUsuarios();
            await carregarAuditorias();
            setTimeout(fecharModalUsuario, 900);
        } catch (erro) {
            console.error("Erro ao cadastrar usuário:", erro);
            mensagemUsuario.className = "mensagem-formulario erro";
            mensagemUsuario.textContent = "Erro ao conectar com o servidor.";
        }
    });
}

function abrirModalEditarUsuario(id) {
    const usuario = usuarios.find(item => item.idUsuario === id);
    if (!usuario) {
        alert("Usuário não encontrado.");
        return;
    }
    editarUsuarioId.value = usuario.idUsuario;
    editarUsuarioNome.value = usuario.nome ?? "";
    editarUsuarioEmail.value = usuario.email ?? "";
    editarUsuarioPerfil.value = usuario.perfil ?? "";

    const possuiVinculoAcademico = alunos.some(aluno => aluno.usuario?.idUsuario === usuario.idUsuario) ||
        professores.some(professor => professor.usuario?.idUsuario === usuario.idUsuario);
    editarUsuarioPerfil.disabled = possuiVinculoAcademico;
    editarUsuarioPerfil.title = possuiVinculoAcademico ? "O perfil não pode ser alterado porque este usuário possui vínculo acadêmico." : "";
    editarUsuarioSenha.value = "";
    mensagemEditarUsuario.textContent = "";
    mensagemEditarUsuario.className = "mensagem-formulario";
    modalEditarUsuario.classList.add("ativo");
}

function fecharModalEditarUsuario() {
    if (!modalEditarUsuario) return;
    modalEditarUsuario.classList.remove("ativo");
    formEditarUsuario.reset();
    editarUsuarioPerfil.disabled = false;
    editarUsuarioPerfil.title = "";
    editarUsuarioId.value = "";
    mensagemEditarUsuario.textContent = "";
    mensagemEditarUsuario.className = "mensagem-formulario";
}

if (formEditarUsuario) {
    formEditarUsuario.addEventListener("submit", async function (event) {
        event.preventDefault();
        const id = Number(editarUsuarioId.value);
        if (!id) {
            mensagemEditarUsuario.className = "mensagem-formulario erro";
            mensagemEditarUsuario.textContent = "Usuário inválido.";
            return;
        }

        mensagemEditarUsuario.className = "mensagem-formulario";
        mensagemEditarUsuario.textContent = "Salvando alterações...";

        const dados = {
            nome: editarUsuarioNome.value.trim(),
            email: editarUsuarioEmail.value.trim(),
            perfil: editarUsuarioPerfil.value
        };

        if (editarUsuarioSenha.value.trim()) {
            dados.senha = editarUsuarioSenha.value;
        }

        try {
            const response = await requisicaoAutenticada(`/usuarios/${id}`, { method: "PUT", body: JSON.stringify(dados) });
            if (!response) return;
            const resposta = await lerJsonSeguro(response);
            if (!response.ok) {
                mensagemEditarUsuario.className = "mensagem-formulario erro";
                mensagemEditarUsuario.textContent = resposta?.mensagem ?? "Não foi possível atualizar o usuário.";
                return;
            }
            mensagemEditarUsuario.className = "mensagem-formulario sucesso";
            mensagemEditarUsuario.textContent = "Usuário atualizado com sucesso!";
            await carregarUsuarios();
            await carregarProfessores();
            await carregarAuditorias();

            if (id === idUsuarioLogado && dados.perfil !== "ADMINISTRADOR") {
                localStorage.clear();
                alert("Seu perfil foi alterado. Faça login novamente.");
                window.location.href = "/login.html";
                return;
            }
            setTimeout(fecharModalEditarUsuario, 900);
        } catch (erro) {
            console.error("Erro ao atualizar usuário:", erro);
            mensagemEditarUsuario.className = "mensagem-formulario erro";
            mensagemEditarUsuario.textContent = "Erro ao conectar com o servidor.";
        }
    });
}

async function alterarStatusUsuario(id, reativar) {
    if (id === idUsuarioLogado && !reativar) {
        alert("Você não pode desativar o usuário da sessão atual.");
        return;
    }
    const usuario = usuarios.find(item => item.idUsuario === id);
    if (!usuario) {
        alert("Usuário não encontrado.");
        return;
    }
    const acao = reativar ? "reativar" : "desativar";
    if (!window.confirm(`Deseja realmente ${acao} o usuário "${usuario.nome}"?`)) return;

    try {
        const response = await requisicaoAutenticada(`/usuarios/${id}/${acao}`, { method: "PUT" });
        if (!response) return;
        if (!response.ok) {
            const resposta = await lerJsonSeguro(response);
            alert(resposta?.mensagem ?? `Não foi possível ${acao} o usuário.`);
            return;
        }
        await carregarUsuarios();
        await carregarProfessores();
        await carregarAlunos();
        await carregarAuditorias();
        alert(`Usuário ${reativar ? "reativado" : "desativado"} com sucesso.`);
    } catch (erro) {
        console.error(`Erro ao ${acao} usuário:`, erro);
        alert("Erro ao conectar com o servidor.");
    }
}

// =====================================================
// GESTÃO DE PROFESSORES
// =====================================================
async function carregarProfessores() {
    try {
        const response = await requisicaoAutenticada("/professores");
        if (!response || !response.ok) {
            if (listaProfessoresElemento) listaProfessoresElemento.textContent = "Não foi possível carregar os professores.";
            if (totalProfessoresElemento) totalProfessoresElemento.textContent = "--";
            return;
        }
        professores = await response.json();
        if (totalProfessoresElemento) totalProfessoresElemento.textContent = professores.length;
        aplicarFiltroProfessores();
    } catch (erro) {
        console.error("Erro ao carregar professores:", erro);
        if (listaProfessoresElemento) listaProfessoresElemento.textContent = "Erro ao conectar com o servidor.";
        if (totalProfessoresElemento) totalProfessoresElemento.textContent = "--";
    }
}

function aplicarFiltroProfessores() {
    const campoBusca = document.getElementById("filtroProfessorBusca");
    const contador = document.getElementById("contadorProfessores");
    if (!campoBusca) {
        montarTabelaProfessores(professores);
        return;
    }
    const busca = campoBusca.value.trim().toLowerCase();
    const professoresFiltrados = professores.filter(professor => {
        const registro = String(professor.registro ?? "").toLowerCase();
        const nome = String(professor.usuario?.nome ?? "").toLowerCase();
        const email = String(professor.usuario?.email ?? "").toLowerCase();
        return (!busca || registro.includes(busca) || nome.includes(busca) || email.includes(busca));
    });
    montarTabelaProfessores(professoresFiltrados);
    if (contador) contador.textContent = `${professoresFiltrados.length} de ${professores.length} professor(es) exibido(s).`;
}

function limparFiltroProfessores() {
    const campoBusca = document.getElementById("filtroProfessorBusca");
    if (campoBusca) campoBusca.value = "";
    aplicarFiltroProfessores();
}

function montarTabelaProfessores(dados) {
    if (!listaProfessoresElemento) return;
    if (!dados || dados.length === 0) {
        listaProfessoresElemento.textContent = "Nenhum professor cadastrado.";
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
                <td>${professor.idProfessor ?? "--"}</td>
                <td>${professor.registro ?? "--"}</td>
                <td>${professor.usuario?.nome ?? "--"}</td>
                <td>${professor.usuario?.email ?? "--"}</td>
                <td>
                    <button type="button" class="botao-secundario" onclick="abrirModalEditarProfessor(${professor.idProfessor})">Editar</button>
                    <button type="button" class="botao-perigo" onclick="excluirProfessor(${professor.idProfessor})">Excluir</button>
                </td>
            </tr>
        `;
    });
    html += `</tbody></table></div>`;
    listaProfessoresElemento.innerHTML = html;
}

function obterUsuariosProfessoresDisponiveis() {
    return usuarios.filter(usuario => {
        if (usuario.ativo === false || usuario.perfil !== "PROFESSOR") return false;
        return !professores.some(professor => professor.usuario?.idUsuario === usuario.idUsuario);
    });
}

function preencherSelectProfessor() {
    if (!professorUsuario) return;
    professorUsuario.innerHTML = `<option value="">Selecione um usuário professor</option>`;
    const disponiveis = obterUsuariosProfessoresDisponiveis();
    if (disponiveis.length === 0) {
        const option = document.createElement("option");
        option.value = "";
        option.textContent = "Nenhum usuário PROFESSOR disponível";
        professorUsuario.appendChild(option);
        professorUsuario.disabled = true;
        return;
    }
    professorUsuario.disabled = false;
    disponiveis.forEach(usuario => {
        const option = document.createElement("option");
        option.value = usuario.idUsuario;
        option.textContent = `${usuario.nome} - ${usuario.email}`;
        professorUsuario.appendChild(option);
    });
}

function abrirModalProfessor() {
    if (!formProfessor) return;
    formProfessor.reset();
    mensagemProfessor.textContent = "";
    mensagemProfessor.className = "mensagem-formulario";
    preencherSelectProfessor();
    modalProfessor.classList.add("ativo");
}

function fecharModalProfessor() {
    if (!modalProfessor) return;
    modalProfessor.classList.remove("ativo");
    formProfessor.reset();
    mensagemProfessor.textContent = "";
    mensagemProfessor.className = "mensagem-formulario";
}

if (formProfessor) {
    formProfessor.addEventListener("submit", async function (event) {
        event.preventDefault();
        mensagemProfessor.className = "mensagem-formulario";
        mensagemProfessor.textContent = "Salvando professor...";
        const idUsuario = Number(professorUsuario.value);
        const registro = professorRegistro.value.trim();
        if (!idUsuario) {
            mensagemProfessor.className = "mensagem-formulario erro";
            mensagemProfessor.textContent = "Selecione um usuário professor.";
            return;
        }
        if (!registro) {
            mensagemProfessor.className = "mensagem-formulario erro";
            mensagemProfessor.textContent = "Informe o registro do professor.";
            return;
        }
        const dados = { registro: registro, usuario: { idUsuario: idUsuario } };
        try {
            const response = await requisicaoAutenticada("/professores", { method: "POST", body: JSON.stringify(dados) });
            if (!response) return;
            const resposta = await lerJsonSeguro(response);
            if (!response.ok) {
                mensagemProfessor.className = "mensagem-formulario erro";
                mensagemProfessor.textContent = resposta?.mensagem ?? "Não foi possível cadastrar o professor.";
                return;
            }
            mensagemProfessor.className = "mensagem-formulario sucesso";
            mensagemProfessor.textContent = "Professor cadastrado com sucesso!";
            await carregarProfessores();
            await carregarAuditorias();
            setTimeout(fecharModalProfessor, 900);
        } catch (erro) {
            console.error("Erro ao cadastrar professor:", erro);
            mensagemProfessor.className = "mensagem-formulario erro";
            mensagemProfessor.textContent = "Erro ao conectar com o servidor.";
        }
    });
}

function abrirModalEditarProfessor(id) {
    const professor = professores.find(item => item.idProfessor === id);
    if (!professor) {
        alert("Professor não encontrado.");
        return;
    }
    editarProfessorId.value = professor.idProfessor;
    editarProfessorNome.value = professor.usuario?.nome ?? "";
    editarProfessorRegistro.value = professor.registro ?? "";
    mensagemEditarProfessor.textContent = "";
    mensagemEditarProfessor.className = "mensagem-formulario";
    modalEditarProfessor.classList.add("ativo");
}

function fecharModalEditarProfessor() {
    if (!modalEditarProfessor) return;
    modalEditarProfessor.classList.remove("ativo");
    formEditarProfessor.reset();
    editarProfessorId.value = "";
    mensagemEditarProfessor.textContent = "";
    mensagemEditarProfessor.className = "mensagem-formulario";
}

if (formEditarProfessor) {
    formEditarProfessor.addEventListener("submit", async function (event) {
        event.preventDefault();
        const id = Number(editarProfessorId.value);
        if (!id) {
            mensagemEditarProfessor.className = "mensagem-formulario erro";
            mensagemEditarProfessor.textContent = "Professor inválido.";
            return;
        }
        const registro = editarProfessorRegistro.value.trim();
        if (!registro) {
            mensagemEditarProfessor.className = "mensagem-formulario erro";
            mensagemEditarProfessor.textContent = "Informe o registro do professor.";
            return;
        }
        mensagemEditarProfessor.className = "mensagem-formulario";
        mensagemEditarProfessor.textContent = "Salvando alterações...";
        const dados = { registro: registro };
        try {
            const response = await requisicaoAutenticada(`/professores/${id}`, { method: "PUT", body: JSON.stringify(dados) });
            if (!response) return;
            const resposta = await lerJsonSeguro(response);
            if (!response.ok) {
                mensagemEditarProfessor.className = "mensagem-formulario erro";
                mensagemEditarProfessor.textContent = resposta?.mensagem ?? "Não foi possível atualizar o professor.";
                return;
            }
            mensagemEditarProfessor.className = "mensagem-formulario sucesso";
            mensagemEditarProfessor.textContent = "Professor atualizado com sucesso!";
            await carregarProfessores();
            await carregarVinculos();
            await carregarAuditorias();
            setTimeout(fecharModalEditarProfessor, 900);
        } catch (erro) {
            console.error("Erro ao atualizar professor:", erro);
            mensagemEditarProfessor.className = "mensagem-formulario erro";
            mensagemEditarProfessor.textContent = "Erro ao conectar com o servidor.";
        }
    });
}

async function excluirProfessor(id) {
    const professor = professores.find(item => item.idProfessor === id);
    if (!professor) {
        alert("Professor não encontrado.");
        return;
    }
    const nome = professor.usuario?.nome ?? professor.registro ?? "Professor";
    if (!window.confirm(`Deseja realmente excluir o cadastro do professor "${nome}"?`)) return;

    try {
        const response = await requisicaoAutenticada(`/professores/${id}`, { method: "DELETE" });
        if (!response) return;
        if (!response.ok) {
            const resposta = await lerJsonSeguro(response);
            alert(resposta?.mensagem ?? "Não foi possível excluir o professor.");
            return;
        }
        await carregarProfessores();
        await carregarAuditorias();
        alert("Professor excluído com sucesso.");
    } catch (erro) {
        console.error("Erro ao excluir professor:", erro);
        alert("Erro ao conectar com o servidor.");
    }
}

// =====================================================
// GESTÃO DE ALUNOS
// =====================================================
async function carregarAlunos() {
    try {
        const response = await requisicaoAutenticada("/alunos");
        if (!response || !response.ok) {
            alunos = [];
            montarTabelaAlunos(alunos);
            return;
        }
        alunos = await response.json();
        aplicarFiltroAlunos();
    } catch (erro) {
        console.error("Erro ao carregar alunos:", erro);
        alunos = [];
        montarTabelaAlunos(alunos);
    }
}

function aplicarFiltroAlunos() {
    const campoBusca = document.getElementById("filtroAlunoBusca");
    const contador = document.getElementById("contadorAlunos");
    if (!campoBusca) {
        montarTabelaAlunos(alunos);
        return;
    }
    const busca = campoBusca.value.trim().toLowerCase();
    const alunosFiltrados = alunos.filter(aluno => {
        const matricula = String(aluno.matricula ?? "").toLowerCase();
        const nome = String(aluno.usuario?.nome ?? "").toLowerCase();
        const email = String(aluno.usuario?.email ?? "").toLowerCase();
        return (!busca || matricula.includes(busca) || nome.includes(busca) || email.includes(busca));
    });
    montarTabelaAlunos(alunosFiltrados);
    if (contador) contador.textContent = `${alunosFiltrados.length} de ${alunos.length} aluno(s) exibido(s).`;
}

function limparFiltroAlunos() {
    const campoBusca = document.getElementById("filtroAlunoBusca");
    if (campoBusca) campoBusca.value = "";
    aplicarFiltroAlunos();
}

function montarTabelaAlunos(dados) {
    const listaAlunos = document.getElementById("listaAlunos");
    if (!listaAlunos) return;
    if (!dados || dados.length === 0) {
        listaAlunos.innerHTML = "Nenhum aluno cadastrado.";
        return;
    }
    let html = `
        <div class="tabela-container">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Matrícula</th>
                        <th>Nome</th>
                        <th>E-mail</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
    `;
    dados.forEach(aluno => {
        html += `
            <tr>
                <td>${aluno.idAluno}</td>
                <td>${aluno.matricula}</td>
                <td>${aluno.usuario?.nome ?? "-"}</td>
                <td>${aluno.usuario?.email ?? "-"}</td>
                <td>
                    <button type="button" class="botao-perigo" onclick="excluirAluno(${aluno.idAluno})">Excluir</button>
                </td>
            </tr>
        `;
    });
    html += `</tbody></table></div>`;
    listaAlunos.innerHTML = html;
}

function obterUsuariosAlunosDisponiveis() {
    return usuarios.filter(usuario => {
        if (usuario.ativo === false || usuario.perfil !== "ALUNO") return false;
        return !alunos.some(aluno => aluno.usuario?.idUsuario === usuario.idUsuario);
    });
}

function preencherSelectAluno() {
    if (!alunoUsuario) return;
    alunoUsuario.innerHTML = `<option value="">Selecione um usuário aluno</option>`;
    const disponiveis = obterUsuariosAlunosDisponiveis();
    if (disponiveis.length === 0) {
        const option = document.createElement("option");
        option.value = "";
        option.textContent = "Nenhum usuário ALUNO disponível";
        alunoUsuario.appendChild(option);
        alunoUsuario.disabled = true;
        return;
    }
    alunoUsuario.disabled = false;
    disponiveis.forEach(usuario => {
        const option = document.createElement("option");
        option.value = usuario.idUsuario;
        option.textContent = `${usuario.nome} - ${usuario.email}`;
        alunoUsuario.appendChild(option);
    });
}

function abrirModalAluno() {
    if (!formAluno) return;
    formAluno.reset();
    mensagemAluno.textContent = "";
    mensagemAluno.className = "mensagem-formulario";
    preencherSelectAluno();
    modalAluno.classList.add("ativo");
}

function fecharModalAluno() {
    if (!modalAluno) return;
    modalAluno.classList.remove("ativo");
    formAluno.reset();
    mensagemAluno.textContent = "";
    mensagemAluno.className = "mensagem-formulario";
}

if (formAluno) {
    formAluno.addEventListener("submit", async function (event) {
        event.preventDefault();
        mensagemAluno.className = "mensagem-formulario";
        mensagemAluno.textContent = "Salvando aluno...";
        const idUsuario = Number(alunoUsuario.value);
        const matricula = alunoMatricula.value.trim();
        if (!idUsuario) {
            mensagemAluno.className = "mensagem-formulario erro";
            mensagemAluno.textContent = "Selecione um usuário aluno.";
            return;
        }
        if (!matricula) {
            mensagemAluno.className = "mensagem-formulario erro";
            mensagemAluno.textContent = "Informe a matrícula do aluno.";
            return;
        }
        const dados = { matricula: matricula, usuario: { idUsuario: idUsuario } };
        try {
            const response = await requisicaoAutenticada("/alunos", { method: "POST", body: JSON.stringify(dados) });
            if (!response) return;
            const resposta = await lerJsonSeguro(response);
            if (!response.ok) {
                mensagemAluno.className = "mensagem-formulario erro";
                mensagemAluno.textContent = resposta?.mensagem ?? "Não foi possível cadastrar o aluno.";
                return;
            }
            mensagemAluno.className = "mensagem-formulario sucesso";
            mensagemAluno.textContent = "Aluno cadastrado com sucesso!";
            await carregarAlunos();
            setTimeout(fecharModalAluno, 900);
        } catch (erro) {
            console.error("Erro ao cadastrar aluno:", erro);
            mensagemAluno.className = "mensagem-formulario erro";
            mensagemAluno.textContent = "Erro ao conectar com o servidor.";
        }
    });
}

async function excluirAluno(idAluno) {
    const aluno = alunos.find(item => item.idAluno === idAluno);
    if (!aluno) {
        alert("Aluno não encontrado.");
        return;
    }
    const nomeAluno = aluno.usuario?.nome ?? "Aluno";
    if (!confirm(`Deseja excluir o cadastro acadêmico de ${nomeAluno}?`)) return;

    try {
        const response = await requisicaoAutenticada(`/alunos/${idAluno}`, { method: "DELETE" });
        if (!response) return;
        if (!response.ok) {
            const resposta = await lerJsonSeguro(response);
            alert(resposta?.erro ?? resposta?.mensagem ?? resposta?.message ?? "Não foi possível excluir o aluno.");
            return;
        }
        alert("Aluno excluído com sucesso.");
        await carregarAlunos();
    } catch (erro) {
        console.error("Erro ao excluir aluno:", erro);
        alert("Erro ao conectar com o servidor.");
    }
}

// =====================================================
// GESTÃO DE TURMAS
// =====================================================
async function carregarTurmas() {
    try {
        const response = await requisicaoAutenticada("/turmas");
        if (!response || !response.ok) return;
        turmas = await response.json();
        aplicarFiltroTurmas();
    } catch (erro) {
        console.error("Erro ao carregar turmas:", erro);
    }
}

function aplicarFiltroTurmas() {
    const campoBusca = document.getElementById("filtroTurmaBusca");
    const contador = document.getElementById("contadorTurmas");
    if (!campoBusca) {
        montarTabelaTurmas(turmas);
        return;
    }
    const busca = campoBusca.value.trim().toLowerCase();
    const turmasFiltradas = turmas.filter(turma => {
        const nome = String(turma.nome ?? "").toLowerCase();
        const ano = String(turma.ano ?? "").toLowerCase();
        const semestre = String(turma.semestre ?? "").toLowerCase();
        return (!busca || nome.includes(busca) || ano.includes(busca) || semestre.includes(busca));
    });
    montarTabelaTurmas(turmasFiltradas);
    if (contador) contador.textContent = `${turmasFiltradas.length} de ${turmas.length} turma(s) exibida(s).`;
}

function limparBuscaTurmas() {
    const campoBusca = document.getElementById("filtroTurmaBusca");
    if (campoBusca) campoBusca.value = "";
    aplicarFiltroTurmas();
}

function montarTabelaTurmas(dados) {
    if (!listaTurmasElemento) return;
    if (!dados || dados.length === 0) {
        listaTurmasElemento.textContent = "Nenhuma turma cadastrada.";
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
                <td>${turma.idTurma ?? "--"}</td>
                <td>${turma.nome ?? "--"}</td>
                <td>${turma.ano ?? "--"}</td>
                <td>${turma.semestre ?? "--"}º</td>
                <td>
                    <button type="button" class="botao-secundario" onclick="abrirModalEditarTurma(${turma.idTurma})">Editar</button>
                    <button type="button" class="botao-perigo" onclick="excluirTurma(${turma.idTurma})">Excluir</button>
                </td>
            </tr>
        `;
    });
    html += `</tbody></table></div>`;
    listaTurmasElemento.innerHTML = html;
}

function abrirModalTurma() {
    if (!formTurma) return;
    formTurma.reset();
    mensagemTurma.textContent = "";
    mensagemTurma.className = "mensagem-formulario";
    modalTurma.classList.add("ativo");
}

function fecharModalTurma() {
    if (!modalTurma) return;
    modalTurma.classList.remove("ativo");
    formTurma.reset();
    mensagemTurma.textContent = "";
    mensagemTurma.className = "mensagem-formulario";
}

if (formTurma) {
    formTurma.addEventListener("submit", async function (event) {
        event.preventDefault();
        const nome = turmaNome.value.trim();
        const semestre = Number(turmaSemestre.value);
        const ano = Number(turmaAno.value);

        if (!nome || !semestre || !ano) {
            mensagemTurma.className = "mensagem-formulario erro";
            mensagemTurma.textContent = "Preencha todos os campos obrigatórios.";
            return;
        }

        mensagemTurma.className = "mensagem-formulario";
        mensagemTurma.textContent = "Salvando turma...";
        const dados = { nome, semestre, ano };

        try {
            const response = await requisicaoAutenticada("/turmas", { method: "POST", body: JSON.stringify(dados) });
            if (!response) return;
            const resposta = await lerJsonSeguro(response);
            if (!response.ok) {
                mensagemTurma.className = "mensagem-formulario erro";
                mensagemTurma.textContent = resposta?.mensagem ?? "Não foi possível cadastrar a turma.";
                return;
            }
            mensagemTurma.className = "mensagem-formulario sucesso";
            mensagemTurma.textContent = "Turma cadastrada com sucesso!";
            await carregarTurmas();
            setTimeout(fecharModalTurma, 900);
        } catch (erro) {
            console.error("Erro ao cadastrar turma:", erro);
            mensagemTurma.className = "mensagem-formulario erro";
            mensagemTurma.textContent = "Erro ao conectar com o servidor.";
        }
    });
}

function abrirModalEditarTurma(id) {
    const turma = turmas.find(item => item.idTurma === id);
    if (!turma) {
        alert("Turma não encontrada.");
        return;
    }
    editarTurmaId.value = turma.idTurma;
    editarTurmaNome.value = turma.nome ?? "";
    editarTurmaSemestre.value = turma.semestre ?? "";
    editarTurmaAno.value = turma.ano ?? "";
    mensagemEditarTurma.textContent = "";
    mensagemEditarTurma.className = "mensagem-formulario";
    modalEditarTurma.classList.add("ativo");
}

function fecharModalEditarTurma() {
    if (!modalEditarTurma) return;
    modalEditarTurma.classList.remove("ativo");
    formEditarTurma.reset();
    editarTurmaId.value = "";
    mensagemEditarTurma.textContent = "";
    mensagemEditarTurma.className = "mensagem-formulario";
}

if (formEditarTurma) {
    formEditarTurma.addEventListener("submit", async function (event) {
        event.preventDefault();
        const id = Number(editarTurmaId.value);
        const dados = {
            nome: editarTurmaNome.value.trim(),
            semestre: Number(editarTurmaSemestre.value),
            ano: Number(editarTurmaAno.value)
        };
        try {
            const response = await requisicaoAutenticada(`/turmas/${id}`, { method: "PUT", body: JSON.stringify(dados) });
            if (!response) return;
            const resposta = await lerJsonSeguro(response);
            if (!response.ok) {
                mensagemEditarTurma.className = "mensagem-formulario erro";
                mensagemEditarTurma.textContent = resposta?.mensagem ?? "Não foi possível atualizar a turma.";
                return;
            }
            mensagemEditarTurma.className = "mensagem-formulario sucesso";
            mensagemEditarTurma.textContent = "Turma atualizada com sucesso!";
            await carregarTurmas();
            await carregarVinculos();
            await carregarAuditorias();
            setTimeout(fecharModalEditarTurma, 900);
        } catch (erro) {
            console.error("Erro ao atualizar turma:", erro);
            mensagemEditarTurma.className = "mensagem-formulario erro";
            mensagemEditarTurma.textContent = "Erro ao conectar com o servidor.";
        }
    });
}

async function excluirTurma(id) {
    const turma = turmas.find(item => item.idTurma === id);
    if (!turma) {
        alert("Turma não encontrada.");
        return;
    }
    if (!window.confirm(`Deseja realmente excluir a turma "${turma.nome}"?`)) return;

    try {
        const response = await requisicaoAutenticada(`/turmas/${id}`, { method: "DELETE" });
        if (!response) return;
        if (!response.ok) {
            const resposta = await lerJsonSeguro(response);
            alert(resposta?.mensagem ?? "Não foi possível excluir a turma.");
            return;
        }
        await carregarTurmas();
        await carregarAuditorias();
        alert("Turma excluída com sucesso.");
    } catch (erro) {
        console.error("Erro ao excluir turma:", erro);
        alert("Erro ao conectar com o servidor.");
    }
}

// =====================================================
// GESTÃO DE DISCIPLINAS
// =====================================================
async function carregarDisciplinas() {
    try {
        const response = await requisicaoAutenticada("/disciplinas");
        if (!response || !response.ok) return;
        disciplinas = await response.json();
        aplicarFiltroDisciplinas();
    } catch (erro) {
        console.error("Erro ao carregar disciplinas:", erro);
    }
}

function aplicarFiltroDisciplinas() {
    const campoBusca = document.getElementById("filtroDisciplinaBusca");
    const contador = document.getElementById("contadorDisciplinas");
    if (!campoBusca) {
        montarTabelaDisciplinas(disciplinas);
        return;
    }
    const busca = campoBusca.value.trim().toLowerCase();
    const disciplinasFiltradas = disciplinas.filter(disciplina => {
        const nome = String(disciplina.nome ?? "").toLowerCase();
        return (!busca || nome.includes(busca));
    });
    montarTabelaDisciplinas(disciplinasFiltradas);
    if (contador) contador.textContent = `${disciplinasFiltradas.length} de ${disciplinas.length} disciplina(s) exibida(s).`;
}

function limparBuscaDisciplinas() {
    const campoBusca = document.getElementById("filtroDisciplinaBusca");
    if (campoBusca) campoBusca.value = "";
    aplicarFiltroDisciplinas();
}

function montarTabelaDisciplinas(dados) {
    if (!listaDisciplinasElemento) return;
    if (!dados || dados.length === 0) {
        listaDisciplinasElemento.textContent = "Nenhuma disciplina cadastrada.";
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
                <td>${disciplina.idDisciplina ?? "--"}</td>
                <td>${disciplina.nome ?? "--"}</td>
                <td>${disciplina.cargaHoraria ?? "--"}h</td>
                <td>
                    <button type="button" class="botao-secundario" onclick="abrirModalEditarDisciplina(${disciplina.idDisciplina})">Editar</button>
                    <button type="button" class="botao-perigo" onclick="excluirDisciplina(${disciplina.idDisciplina})">Excluir</button>
                </td>
            </tr>
        `;
    });
    html += `</tbody></table></div>`;
    listaDisciplinasElemento.innerHTML = html;
}

function abrirModalDisciplina() {
    if (!formDisciplina) return;
    formDisciplina.reset();
    mensagemDisciplina.textContent = "";
    mensagemDisciplina.className = "mensagem-formulario";
    modalDisciplina.classList.add("ativo");
}

function fecharModalDisciplina() {
    if (!modalDisciplina) return;
    modalDisciplina.classList.remove("ativo");
    formDisciplina.reset();
    mensagemDisciplina.textContent = "";
    mensagemDisciplina.className = "mensagem-formulario";
}

if (formDisciplina) {
    formDisciplina.addEventListener("submit", async function (event) {
        event.preventDefault();
        const nome = disciplinaNome.value.trim();
        const cargaHoraria = Number(disciplinaCargaHoraria.value);

        if (!nome || !cargaHoraria || cargaHoraria <= 0) {
            mensagemDisciplina.className = "mensagem-formulario erro";
            mensagemDisciplina.textContent = "Informe nome e uma carga horária válida.";
            return;
        }

        mensagemDisciplina.className = "mensagem-formulario";
        mensagemDisciplina.textContent = "Salvando disciplina...";
        const dados = { nome, cargaHoraria };

        try {
            const response = await requisicaoAutenticada("/disciplinas", { method: "POST", body: JSON.stringify(dados) });
            if (!response) return;
            const resposta = await lerJsonSeguro(response);
            if (!response.ok) {
                mensagemDisciplina.className = "mensagem-formulario erro";
                mensagemDisciplina.textContent = resposta?.mensagem ?? "Não foi possível cadastrar a disciplina.";
                return;
            }
            mensagemDisciplina.className = "mensagem-formulario sucesso";
            mensagemDisciplina.textContent = "Disciplina cadastrada com sucesso!";
            await carregarDisciplinas();
            setTimeout(fecharModalDisciplina, 900);
        } catch (erro) {
            console.error("Erro ao cadastrar disciplina:", erro);
            mensagemDisciplina.className = "mensagem-formulario erro";
            mensagemDisciplina.textContent = "Erro ao conectar com o servidor.";
        }
    });
}

function abrirModalEditarDisciplina(id) {
    const disciplina = disciplinas.find(item => item.idDisciplina === id);
    if (!disciplina) {
        alert("Disciplina não encontrada.");
        return;
    }
    editarDisciplinaId.value = disciplina.idDisciplina;
    editarDisciplinaNome.value = disciplina.nome ?? "";
    editarDisciplinaCargaHoraria.value = disciplina.cargaHoraria ?? "";
    mensagemEditarDisciplina.textContent = "";
    mensagemEditarDisciplina.className = "mensagem-formulario";
    modalEditarDisciplina.classList.add("ativo");
}

function fecharModalEditarDisciplina() {
    if (!modalEditarDisciplina) return;
    modalEditarDisciplina.classList.remove("ativo");
    formEditarDisciplina.reset();
    editarDisciplinaId.value = "";
    mensagemEditarDisciplina.textContent = "";
    mensagemEditarDisciplina.className = "mensagem-formulario";
}

if (formEditarDisciplina) {
    formEditarDisciplina.addEventListener("submit", async function (event) {
        event.preventDefault();
        const id = Number(editarDisciplinaId.value);
        const dados = {
            nome: editarDisciplinaNome.value.trim(),
            cargaHoraria: Number(editarDisciplinaCargaHoraria.value)
        };
        try {
            const response = await requisicaoAutenticada(`/disciplinas/${id}`, { method: "PUT", body: JSON.stringify(dados) });
            if (!response) return;
            const resposta = await lerJsonSeguro(response);
            if (!response.ok) {
                mensagemEditarDisciplina.className = "mensagem-formulario erro";
                mensagemEditarDisciplina.textContent = resposta?.mensagem ?? "Não foi possível atualizar a disciplina.";
                return;
            }
            mensagemEditarDisciplina.className = "mensagem-formulario sucesso";
            mensagemEditarDisciplina.textContent = "Disciplina atualizada com sucesso!";
            await carregarDisciplinas();
            await carregarVinculos();
            await carregarAuditorias();
            setTimeout(fecharModalEditarDisciplina, 900);
        } catch (erro) {
            console.error("Erro ao atualizar disciplina:", erro);
            mensagemEditarDisciplina.className = "mensagem-formulario erro";
            mensagemEditarDisciplina.textContent = "Erro ao conectar com o servidor.";
        }
    });
}

async function excluirDisciplina(id) {
    const disciplina = disciplinas.find(item => item.idDisciplina === id);
    if (!disciplina) {
        alert("Disciplina não encontrada.");
        return;
    }
    if (!window.confirm(`Deseja realmente excluir a disciplina "${disciplina.nome}"?`)) return;

    try {
        const response = await requisicaoAutenticada(`/disciplinas/${id}`, { method: "DELETE" });
        if (!response) return;
        if (!response.ok) {
            const resposta = await lerJsonSeguro(response);
            alert(resposta?.mensagem ?? "Não foi possível excluir a disciplina.");
            return;
        }
        await carregarDisciplinas();
        await carregarAuditorias();
        alert("Disciplina excluída com sucesso.");
    } catch (erro) {
        console.error("Erro ao excluir disciplina:", erro);
        alert("Erro ao conectar com o servidor.");
    }
}

// =====================================================
// GESTÃO DE VÍNCULOS ACADÊMICOS
// =====================================================
async function carregarVinculos() {
    try {
        const response = await requisicaoAutenticada("/turmas-disciplinas");
        if (!response || !response.ok) {
            if (listaVinculosElemento) listaVinculosElemento.textContent = "Não foi possível carregar os vínculos.";
            return;
        }
        vinculos = await response.json();
        preencherFiltrosVinculos();
        aplicarFiltrosVinculos();
    } catch (erro) {
        console.error("Erro ao carregar vínculos:", erro);
        if (listaVinculosElemento) listaVinculosElemento.textContent = "Erro ao conectar com o servidor.";
    }
}

function preencherFiltrosVinculos() {
    const selectTurma = document.getElementById("filtroVinculoTurma");
    const selectDisciplina = document.getElementById("filtroVinculoDisciplina");
    if (!selectTurma || !selectDisciplina) return;

    const turmaAtual = selectTurma.value;
    const disciplinaAtual = selectDisciplina.value;

    const turmasUnicas = [...new Set(vinculos.map(vinculo => vinculo.turma?.nome).filter(Boolean))].sort((a, b) => a.localeCompare(b, "pt-BR"));
    const disciplinasUnicas = [...new Set(vinculos.map(vinculo => vinculo.disciplina?.nome).filter(Boolean))].sort((a, b) => a.localeCompare(b, "pt-BR"));

    selectTurma.innerHTML = `<option value="">Todas as turmas</option>`;
    turmasUnicas.forEach(nome => {
        const option = document.createElement("option");
        option.value = nome;
        option.textContent = nome;
        selectTurma.appendChild(option);
    });

    selectDisciplina.innerHTML = `<option value="">Todas as disciplinas</option>`;
    disciplinasUnicas.forEach(nome => {
        const option = document.createElement("option");
        option.value = nome;
        option.textContent = nome;
        selectDisciplina.appendChild(option);
    });

    if (turmasUnicas.includes(turmaAtual)) selectTurma.value = turmaAtual;
    if (disciplinasUnicas.includes(disciplinaAtual)) selectDisciplina.value = disciplinaAtual;
}

function aplicarFiltrosVinculos() {
    const campoTurma = document.getElementById("filtroVinculoTurma");
    const campoDisciplina = document.getElementById("filtroVinculoDisciplina");
    const campoProfessor = document.getElementById("filtroVinculoProfessor");
    const contador = document.getElementById("contadorVinculos");
    if (!campoTurma || !campoDisciplina || !campoProfessor) {
        montarTabelaVinculos(vinculos);
        return;
    }
    const turma = campoTurma.value;
    const disciplina = campoDisciplina.value;
    const professorBusca = campoProfessor.value.trim().toLowerCase();

    const vinculosFiltrados = vinculos.filter(vinculo => {
        const nomeTurma = String(vinculo.turma?.nome ?? "");
        const nomeDisciplina = String(vinculo.disciplina?.nome ?? "");
        const nomeProfessor = String(vinculo.professor?.usuario?.nome ?? "").toLowerCase();
        const registroProfessor = String(vinculo.professor?.registro ?? "").toLowerCase();

        const turmaOk = !turma || nomeTurma === turma;
        const disciplinaOk = !disciplina || nomeDisciplina === disciplina;
        const professorOk = !professorBusca || nomeProfessor.includes(professorBusca) || registroProfessor.includes(professorBusca);

        return turmaOk && disciplinaOk && professorOk;
    });

    montarTabelaVinculos(vinculosFiltrados);
    if (contador) contador.textContent = `${vinculosFiltrados.length} de ${vinculos.length} vínculo(s) exibido(s).`;
}

function limparBuscaVinculos() {
    const campoTurma = document.getElementById("filtroVinculoTurma");
    const campoDisciplina = document.getElementById("filtroVinculoDisciplina");
    const campoProfessor = document.getElementById("filtroVinculoProfessor");
    if (!campoTurma || !campoDisciplina || !campoProfessor) return;

    campoTurma.value = "";
    campoDisciplina.value = "";
    campoProfessor.value = "";
    aplicarFiltrosVinculos();
}

function montarTabelaVinculos(dados) {
    if (!listaVinculosElemento) return;
    if (!dados || dados.length === 0) {
        listaVinculosElemento.textContent = "Nenhum vínculo encontrado.";
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
                <td>${vinculo.idTurmaDisciplina ?? "--"}</td>
                <td>${vinculo.turma?.nome ?? "--"}</td>
                <td>${vinculo.disciplina?.nome ?? "--"}</td>
                <td>${vinculo.professor?.usuario?.nome ?? "--"}</td>
                <td>${vinculo.professor?.registro ?? "--"}</td>
                <td>
                    <button type="button" class="botao-secundario" onclick="abrirModalTrocarProfessor(${vinculo.idTurmaDisciplina})">
                        Trocar professor
                    </button>
                    <button type="button" class="botao-perigo" onclick="excluirVinculo(${vinculo.idTurmaDisciplina})">
                        Excluir
                    </button>
                </td>
            </tr>
        `;
    });
    html += `</tbody></table></div>`;
    listaVinculosElemento.innerHTML = html;
}

function preencherSelectsVinculo() {
    if (!vinculoTurma || !vinculoDisciplina || !vinculoProfessor) return;
    vinculoTurma.innerHTML = `<option value="">Selecione a turma</option>`;
    vinculoDisciplina.innerHTML = `<option value="">Selecione a disciplina</option>`;
    vinculoProfessor.innerHTML = `<option value="">Selecione o professor</option>`;

    turmas.forEach(turma => {
        const option = document.createElement("option");
        option.value = turma.idTurma;
        option.textContent = `${turma.nome} - ${turma.ano}/${turma.semestre}`;
        vinculoTurma.appendChild(option);
    });

    disciplinas.forEach(disciplina => {
        const option = document.createElement("option");
        option.value = disciplina.idDisciplina;
        option.textContent = disciplina.nome;
        vinculoDisciplina.appendChild(option);
    });

    professores.forEach(professor => {
        const option = document.createElement("option");
        option.value = professor.idProfessor;
        option.textContent = `${professor.usuario?.nome ?? "Professor"} - ${professor.registro}`;
        vinculoProfessor.appendChild(option);
    });
}

function abrirModalVinculo() {
    if (!formVinculo) return;
    formVinculo.reset();
    mensagemVinculo.textContent = "";
    mensagemVinculo.className = "mensagem-formulario";
    preencherSelectsVinculo();
    modalVinculo.classList.add("ativo");
}

function fecharModalVinculo() {
    if (!modalVinculo) return;
    modalVinculo.classList.remove("ativo");
    formVinculo.reset();
    mensagemVinculo.textContent = "";
    mensagemVinculo.className = "mensagem-formulario";
}

if (formVinculo) {
    formVinculo.addEventListener("submit", async function (event) {
        event.preventDefault();
        const idTurma = Number(vinculoTurma.value);
        const idDisciplina = Number(vinculoDisciplina.value);
        const idProfessor = Number(vinculoProfessor.value);

        if (!idTurma) {
            mensagemVinculo.className = "mensagem-formulario erro";
            mensagemVinculo.textContent = "Selecione uma turma.";
            return;
        }
        if (!idDisciplina) {
            mensagemVinculo.className = "mensagem-formulario erro";
            mensagemVinculo.textContent = "Selecione uma disciplina.";
            return;
        }
        if (!idProfessor) {
            mensagemVinculo.className = "mensagem-formulario erro";
            mensagemVinculo.textContent = "Selecione um professor.";
            return;
        }

        mensagemVinculo.className = "mensagem-formulario";
        mensagemVinculo.textContent = "Criando vínculo...";
        const dados = {
            turma: { idTurma: idTurma },
            disciplina: { idDisciplina: idDisciplina },
            professor: { idProfessor: idProfessor }
        };

        try {
            const response = await requisicaoAutenticada("/turmas-disciplinas", { method: "POST", body: JSON.stringify(dados) });
            if (!response) return;
            const resposta = await lerJsonSeguro(response);
            if (!response.ok) {
                mensagemVinculo.className = "mensagem-formulario erro";
                mensagemVinculo.textContent = resposta?.mensagem ?? "Não foi possível criar o vínculo.";
                return;
            }
            mensagemVinculo.className = "mensagem-formulario sucesso";
            mensagemVinculo.textContent = "Vínculo criado com sucesso!";
            await carregarVinculos();
            await carregarAuditorias();
            setTimeout(fecharModalVinculo, 900);
        } catch (erro) {
            console.error("Erro ao criar vínculo:", erro);
            mensagemVinculo.className = "mensagem-formulario erro";
            mensagemVinculo.textContent = "Erro ao conectar com o servidor.";
        }
    });
}

function abrirModalTrocarProfessor(idVinculo) {
    const vinculo = vinculos.find(item => item.idTurmaDisciplina === idVinculo);
    if (!vinculo) {
        alert("Vínculo não encontrado.");
        return;
    }
    trocarProfessorVinculoId.value = vinculo.idTurmaDisciplina;
    trocarProfessorTurmaDisciplina.value = `${vinculo.turma?.nome ?? "--"} / ${vinculo.disciplina?.nome ?? "--"}`;
    trocarProfessorAtual.value = `${vinculo.professor?.usuario?.nome ?? "--"} - ${vinculo.professor?.registro ?? "--"}`;
    trocarProfessorNovo.innerHTML = `<option value="">Selecione o novo professor</option>`;

    professores
        .filter(professor => professor.idProfessor !== vinculo.professor?.idProfessor)
        .forEach(professor => {
            const option = document.createElement("option");
            option.value = professor.idProfessor;
            option.textContent = `${professor.usuario?.nome ?? "Professor"} - ${professor.registro}`;
            trocarProfessorNovo.appendChild(option);
        });

    mensagemTrocarProfessor.textContent = "";
    mensagemTrocarProfessor.className = "mensagem-formulario";
    modalTrocarProfessor.classList.add("ativo");
}

function fecharModalTrocarProfessor() {
    if (!modalTrocarProfessor) return;
    modalTrocarProfessor.classList.remove("ativo");
    formTrocarProfessor.reset();
    mensagemTrocarProfessor.textContent = "";
    mensagemTrocarProfessor.className = "mensagem-formulario";
}

if (formTrocarProfessor) {
    formTrocarProfessor.addEventListener("submit", async function (event) {
        event.preventDefault();
        const idVinculo = Number(trocarProfessorVinculoId.value);
        const idProfessor = Number(trocarProfessorNovo.value);

        if (!idProfessor) {
            mensagemTrocarProfessor.className = "mensagem-formulario erro";
            mensagemTrocarProfessor.textContent = "Selecione um novo professor.";
            return;
        }

        mensagemTrocarProfessor.className = "mensagem-formulario";
        mensagemTrocarProfessor.textContent = "Salvando alteração...";

        try {
            const response = await requisicaoAutenticada(`/turmas-disciplinas/${idVinculo}/professor`, {
                method: "PUT",
                body: JSON.stringify({ idProfessor: idProfessor })
            });
            if (!response) return;
            const resposta = await lerJsonSeguro(response);
            if (!response.ok) {
                mensagemTrocarProfessor.className = "mensagem-formulario erro";
                mensagemTrocarProfessor.textContent = resposta?.mensagem ?? "Não foi possível trocar o professor.";
                return;
            }
            mensagemTrocarProfessor.className = "mensagem-formulario sucesso";
            mensagemTrocarProfessor.textContent = "Professor alterado com sucesso!";
            await carregarVinculos();
            await carregarAuditorias();
            setTimeout(fecharModalTrocarProfessor, 900);
        } catch (erro) {
            console.error("Erro ao trocar professor:", erro);
            mensagemTrocarProfessor.className = "mensagem-formulario erro";
            mensagemTrocarProfessor.textContent = "Erro ao conectar com o servidor.";
        }
    });
}

async function excluirVinculo(id) {
    try {
        const buscarResponse = await requisicaoAutenticada(`/turmas-disciplinas/${id}`, { method: "GET" });
        if (!buscarResponse || !buscarResponse.ok) {
            const resposta = await lerJsonSeguro(buscarResponse);
            alert(resposta?.mensagem ?? "Vínculo acadêmico não encontrado.");
            return;
        }
        const vinculo = await buscarResponse.json();
        if (!window.confirm(`Deseja realmente excluir o vínculo entre "${vinculo.turma?.nome ?? "--"}" e "${vinculo.disciplina?.nome ?? "--"}"?`)) return;

        const response = await requisicaoAutenticada(`/turmas-disciplinas/${id}`, { method: "DELETE" });
        if (!response) return;
        if (!response.ok) {
            const resposta = await lerJsonSeguro(response);
            alert(resposta?.mensagem ?? "Não foi possível excluir o vínculo acadêmico.");
            return;
        }
        await carregarVinculos();
        await carregarAuditorias();
        alert("Vínculo acadêmico excluído com sucesso.");
    } catch (erro) {
        console.error("Erro ao excluir vínculo acadêmico:", erro);
        alert("Erro ao conectar com o servidor.");
    }
}

// =====================================================
// GESTÃO DE MATRÍCULAS
// =====================================================
async function carregarMatriculas() {
    if (!listaMatriculasElemento) return;
    try {
        const response = await requisicaoAutenticada("/matriculas");
        if (!response || !response.ok) {
            listaMatriculasElemento.textContent = "Não foi possível carregar as matrículas.";
            return;
        }
        matriculas = await response.json();
        preencherFiltrosMatriculas();
        aplicarFiltrosMatriculas();
    } catch (erro) {
        console.error("Erro ao carregar matrículas:", erro);
        listaMatriculasElemento.textContent = "Erro ao conectar com o servidor.";
    }
}

function preencherFiltrosMatriculas() {
    const selectTurma = document.getElementById("filtroMatriculaTurma");
    if (!selectTurma) return;
    const valorAtual = selectTurma.value;
    const turmasUnicas = [...new Map(matriculas.filter(m => m.turma?.idTurma).map(m => [m.turma.idTurma, m.turma])).values()]
        .sort((a, b) => (a.nome || "").localeCompare(b.nome || "", "pt-BR"));

    selectTurma.innerHTML = `<option value="">Todas as turmas</option>`;
    turmasUnicas.forEach(turma => {
        const option = document.createElement("option");
        option.value = String(turma.idTurma);
        option.textContent = turma.nome || "-";
        selectTurma.appendChild(option);
    });
    selectTurma.value = valorAtual;
}

function aplicarFiltrosMatriculas() {
    const campoBusca = document.getElementById("filtroMatriculaBusca");
    const campoTurma = document.getElementById("filtroMatriculaTurma");
    const campoStatus = document.getElementById("filtroMatriculaStatus");
    const contador = document.getElementById("contadorMatriculas");
    if (!campoBusca || !campoTurma || !campoStatus) {
        montarTabelaMatriculas(matriculas);
        return;
    }
    const busca = campoBusca.value.trim().toLowerCase();
    const turma = campoTurma.value;
    const status = campoStatus.value.trim().toUpperCase();

    const matriculasFiltradas = matriculas.filter(matricula => {
        const numeroMatricula = String(matricula.aluno?.matricula ?? "").toLowerCase();
        const nomeAluno = String(matricula.aluno?.usuario?.nome ?? "").toLowerCase();
        const idTurma = String(matricula.turma?.idTurma ?? "");
        const statusMatricula = String(matricula.status ?? "").trim().toUpperCase();

        const correspondeBusca = !busca || numeroMatricula.includes(busca) || nomeAluno.includes(busca);
        const correspondeTurma = !turma || idTurma === turma;
        const correspondeStatus = !status || statusMatricula === status;

        return correspondeBusca && correspondeTurma && correspondeStatus;
    });

    montarTabelaMatriculas(matriculasFiltradas);
    if (contador) contador.textContent = `${matriculasFiltradas.length} de ${matriculas.length} matrícula(s) exibida(s).`;
}

function limparFiltrosMatriculas() {
    if (document.getElementById("filtroMatriculaBusca")) document.getElementById("filtroMatriculaBusca").value = "";
    if (document.getElementById("filtroMatriculaTurma")) document.getElementById("filtroMatriculaTurma").value = "";
    if (document.getElementById("filtroMatriculaStatus")) document.getElementById("filtroMatriculaStatus").value = "";
    aplicarFiltrosMatriculas();
}

function montarTabelaMatriculas(dados) {
    if (!listaMatriculasElemento) return;
    if (!dados || dados.length === 0) {
        listaMatriculasElemento.textContent = "Nenhuma matrícula encontrada.";
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
    dados.forEach(matricula => {
        const aluno = matricula.aluno;
        const turma = matricula.turma;
        const nomeAluno = aluno?.usuario?.nome ?? "--";
        const numeroMatricula = aluno?.matricula ?? "--";
        const nomeTurma = turma?.nome ?? "--";
        const semestreAluno = matricula.semestreAluno ?? "--";
        html += `
            <tr>
                <td>${matricula.idMatricula ?? "--"}</td>
                <td>${nomeAluno}</td>
                <td>${numeroMatricula}</td>
                <td>${nomeTurma}</td>
                <td>${semestreAluno !== "--" ? semestreAluno + "º" : "--"}</td>
                <td>${formatarData(matricula.dataMatricula)}</td>
                <td>${matricula.status ?? "--"}</td>
                <td>
                    <div class="acoes-tabela">
                        <button type="button" class="botao-secundario" onclick="abrirModalEditarMatricula(${matricula.idMatricula})">Editar</button>
                        <button type="button" class="botao-perigo" onclick="excluirMatricula(${matricula.idMatricula})">Excluir</button>
                    </div>
                </td>
            </tr>
        `;
    });
    html += `</tbody></table></div>`;
    listaMatriculasElemento.innerHTML = html;
}

function preencherSelectsMatricula() {
    if (!matriculaAluno || !matriculaTurma) return;
    matriculaAluno.innerHTML = `<option value="">Selecione o aluno</option>`;
    matriculaTurma.innerHTML = `<option value="">Selecione a turma</option>`;

    alunos.forEach(aluno => {
        const option = document.createElement("option");
        option.value = aluno.idAluno;
        option.textContent = `${aluno.usuario?.nome ?? "Aluno"} - ${aluno.matricula ?? "--"}`;
        matriculaAluno.appendChild(option);
    });

    turmas.forEach(turma => {
        const option = document.createElement("option");
        option.value = turma.idTurma;
        option.textContent = `${turma.nome} - ${turma.ano}/${turma.semestre}`;
        matriculaTurma.appendChild(option);
    });
}

function abrirModalMatricula() {
    if (!formMatricula) return;
    formMatricula.reset();
    mensagemMatricula.textContent = "";
    mensagemMatricula.className = "mensagem-formulario";
    preencherSelectsMatricula();
    matriculaData.value = new Date().toISOString().split("T")[0];
    modalMatricula.classList.add("ativo");
}

function fecharModalMatricula() {
    if (!modalMatricula) return;
    modalMatricula.classList.remove("ativo");
    formMatricula.reset();
    mensagemMatricula.textContent = "";
    mensagemMatricula.className = "mensagem-formulario";
}

if (formMatricula) {
    formMatricula.addEventListener("submit", async function (event) {
        event.preventDefault();
        const idAluno = Number(matriculaAluno.value);
        const idTurma = Number(matriculaTurma.value);
        const semestreAluno = Number(matriculaSemestreAluno.value);
        const dataMatricula = matriculaData.value;
        const status = matriculaStatus.value.trim();

        if (!idAluno) {
            mensagemMatricula.className = "mensagem-formulario erro";
            mensagemMatricula.textContent = "Selecione um aluno.";
            return;
        }
        if (!idTurma) {
            mensagemMatricula.className = "mensagem-formulario erro";
            mensagemMatricula.textContent = "Selecione uma turma.";
            return;
        }
        if (!semestreAluno || semestreAluno < 1 || semestreAluno > 4) {
            mensagemMatricula.className = "mensagem-formulario erro";
            mensagemMatricula.textContent = "Selecione um semestre válido.";
            return;
        }
        if (!dataMatricula || !status) {
            mensagemMatricula.className = "mensagem-formulario erro";
            mensagemMatricula.textContent = "Preencha todos os campos.";
            return;
        }

        const dados = {
            aluno: { idAluno: idAluno },
            turma: { idTurma: idTurma },
            semestreAluno: semestreAluno,
            dataMatricula: dataMatricula,
            status: status
        };

        mensagemMatricula.className = "mensagem-formulario";
        mensagemMatricula.textContent = "Salvando matrícula...";

        try {
            const response = await requisicaoAutenticada("/matriculas", { method: "POST", body: JSON.stringify(dados) });
            if (!response) return;
            const resposta = await lerJsonSeguro(response);
            if (!response.ok) {
                mensagemMatricula.className = "mensagem-formulario erro";
                mensagemMatricula.textContent = resposta?.erro ?? resposta?.message ?? "Não foi possível criar a matrícula.";
                return;
            }
            mensagemMatricula.className = "mensagem-formulario sucesso";
            mensagemMatricula.textContent = "Matrícula criada com sucesso.";
            await carregarMatriculas();
            await carregarAuditorias();
            setTimeout(fecharModalMatricula, 600);
        } catch (erro) {
            console.error("Erro ao criar matrícula:", erro);
            mensagemMatricula.className = "mensagem-formulario erro";
            mensagemMatricula.textContent = "Erro ao conectar com o servidor.";
        }
    });
}

function abrirModalEditarMatricula(idMatricula) {
    const matricula = matriculas.find(item => item.idMatricula === idMatricula);
    if (!matricula) {
        alert("Matrícula não encontrada.");
        return;
    }
    editarMatriculaId.value = matricula.idMatricula;
    editarMatriculaAluno.value = `${matricula.aluno?.usuario?.nome ?? "--"} - ${matricula.aluno?.matricula ?? "--"}`;
    editarMatriculaTurma.value = matricula.turma?.nome ?? "--";
    editarMatriculaSemestreAluno.value = matricula.semestreAluno ?? "";
    editarMatriculaData.value = matricula.dataMatricula ?? "";
    editarMatriculaStatus.value = matricula.status ?? "";
    mensagemEditarMatricula.textContent = "";
    mensagemEditarMatricula.className = "mensagem-formulario";
    modalEditarMatricula.classList.add("ativo");
}

function fecharModalEditarMatricula() {
    if (!modalEditarMatricula) return;
    modalEditarMatricula.classList.remove("ativo");
    formEditarMatricula.reset();
    mensagemEditarMatricula.textContent = "";
    mensagemEditarMatricula.className = "mensagem-formulario";
}

if (formEditarMatricula) {
    formEditarMatricula.addEventListener("submit", async function (event) {
        event.preventDefault();
        const id = Number(editarMatriculaId.value);
        const semestreAluno = Number(editarMatriculaSemestreAluno.value);
        const dataMatricula = editarMatriculaData.value;
        const status = editarMatriculaStatus.value.trim();

        if (!id || !dataMatricula || !status) {
            mensagemEditarMatricula.className = "mensagem-formulario erro";
            mensagemEditarMatricula.textContent = "Preencha os campos obrigatórios.";
            return;
        }
        if (!semestreAluno || semestreAluno < 1 || semestreAluno > 4) {
            mensagemEditarMatricula.className = "mensagem-formulario erro";
            mensagemEditarMatricula.textContent = "Selecione um semestre válido.";
            return;
        }

        const dados = { semestreAluno: semestreAluno, dataMatricula: dataMatricula, status: status };
        mensagemEditarMatricula.className = "mensagem-formulario";
        mensagemEditarMatricula.textContent = "Salvando alterações...";

        try {
            const response = await requisicaoAutenticada(`/matriculas/${id}`, { method: "PUT", body: JSON.stringify(dados) });
            if (!response) return;
            const resposta = await lerJsonSeguro(response);
            if (!response.ok) {
                mensagemEditarMatricula.className = "mensagem-formulario erro";
                mensagemEditarMatricula.textContent = resposta?.erro ?? resposta?.message ?? "Não foi possível alterar a matrícula.";
                return;
            }
            mensagemEditarMatricula.className = "mensagem-formulario sucesso";
            mensagemEditarMatricula.textContent = "Matrícula atualizada com sucesso.";
            await carregarMatriculas();
            await carregarAuditorias();
            setTimeout(fecharModalEditarMatricula, 600);
        } catch (erro) {
            console.error("Erro ao editar matrícula:", erro);
            mensagemEditarMatricula.className = "mensagem-formulario erro";
            mensagemEditarMatricula.textContent = "Erro ao conectar com o servidor.";
        }
    });
}

async function excluirMatricula(idMatricula) {
    const matricula = matriculas.find(item => item.idMatricula === idMatricula);
    if (!matricula) {
        alert("Matrícula não encontrada.");
        return;
    }
    const nomeAluno = matricula.aluno?.usuario?.nome ?? "Aluno";
    const turma = matricula.turma?.nome ?? "--";
    if (!confirm(`Deseja excluir a matrícula de ${nomeAluno} na turma ${turma}?`)) return;

    try {
        const response = await requisicaoAutenticada(`/matriculas/${idMatricula}`, { method: "DELETE" });
        if (!response) return;
        if (!response.ok) {
            const resposta = await lerJsonSeguro(response);
            alert(resposta?.mensagem ?? resposta?.erro ?? resposta?.message ?? "Não foi possível excluir a matrícula.");
            return;
        }
        alert("Matrícula excluída com sucesso.");
        await carregarMatriculas();
        await carregarAuditorias();
    } catch (erro) {
        console.error("Erro ao excluir matrícula:", erro);
        alert("Erro ao conectar com o servidor.");
    }
}

// =====================================================
// GESTÃO DE NOTAS & AVALIAÇÕES
// =====================================================
async function carregarNotas() {
    try {
        const response = await requisicaoAutenticada("/notas");
        if (!response || !response.ok) {
            if (listaNotasElemento) listaNotasElemento.textContent = "Não foi possível carregar as notas.";
            if (totalNotasElemento) totalNotasElemento.textContent = "--";
            return;
        }
        notas = await response.json();
        if (totalNotasElemento) totalNotasElemento.textContent = notas.length;
        atualizarFiltrosNotas();
        aplicarFiltrosNotas();
    } catch (erro) {
        console.error("Erro ao carregar notas:", erro);
        if (listaNotasElemento) listaNotasElemento.textContent = "Erro ao conectar com o servidor.";
        if (totalNotasElemento) totalNotasElemento.textContent = "--";
    }
}

function atualizarFiltrosNotas() {
    if (!filtroNotaTurma || !filtroNotaDisciplina || !filtroNotaAluno || !filtroNotaProfessor) return;
    const turmaSelecionada = filtroNotaTurma.value;
    const disciplinaSelecionada = filtroNotaDisciplina.value;
    const notasPorTurma = turmaSelecionada ? notas.filter(nota => nota.turma === turmaSelecionada) : notas;

    preencherFiltro(filtroNotaTurma, valoresUnicos(notas, "turma"), "Todas as turmas");
    preencherFiltro(filtroNotaDisciplina, valoresUnicos(notasPorTurma, "disciplina"), "Todas as disciplinas");

    const notasParaAluno = notasPorTurma.filter(nota => !disciplinaSelecionada || nota.disciplina === disciplinaSelecionada);
    preencherFiltro(filtroNotaAluno, valoresUnicos(notasParaAluno, "aluno"), "Todos os alunos");
    preencherFiltro(filtroNotaProfessor, valoresUnicos(notasParaAluno, "professor"), "Todos os professores");
}

function aplicarFiltrosNotas() {
    if (!filtroNotaTurma) return;
    const turma = filtroNotaTurma.value;
    const disciplina = filtroNotaDisciplina.value;
    const aluno = filtroNotaAluno.value;
    const professor = filtroNotaProfessor.value;
    const avaliacao = filtroNotaAvaliacao.value;

    const notasFiltradas = notas.filter(nota => {
        const turmaOk = !turma || nota.turma === turma;
        const disciplinaOk = !disciplina || nota.disciplina === disciplina;
        const alunoOk = !aluno || nota.aluno === aluno;
        const professorOk = !professor || nota.professor === professor;
        const avaliacaoOk = !avaliacao || nota.tipoAvaliacao === avaliacao;
        return turmaOk && disciplinaOk && alunoOk && professorOk && avaliacaoOk;
    });

    montarTabelaNotas(notasFiltradas);
    if (contadorNotas) contadorNotas.textContent = `${notasFiltradas.length} de ${notas.length} registro(s) exibido(s).`;
}

function montarTabelaNotas(dados) {
    if (!listaNotasElemento) return;
    if (!dados || dados.length === 0) {
        listaNotasElemento.textContent = "Nenhuma nota encontrada.";
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
                <td>${nota.aluno ?? "--"}</td>
                <td>${nota.matriculaAluno ?? "--"}</td>
                <td>${nota.turma ?? "--"}</td>
                <td>${nota.disciplina ?? "--"}</td>
                <td>${nota.professor ?? "--"}</td>
                <td>${nota.tipoAvaliacao ?? "--"}</td>
                <td>${formatarNota(nota.valor)}</td>
                <td>${formatarData(nota.dataAvaliacao)}</td>
            </tr>
        `;
    });
    html += `</tbody></table></div>`;
    listaNotasElemento.innerHTML = html;
}

function localizarMatriculaAvi() {
    if (!aviMatricula) return;
    const numeroDigitado = aviMatricula.value.trim();
    aviIdMatriculaSelecionada = null;
    aviAlunoNome.value = "";
    aviTurmaNome.value = "";
    mensagemAvi.textContent = "";
    mensagemAvi.className = "mensagem-formulario";

    if (!numeroDigitado) return;

    const matriculaEncontrada = matriculas.find(matricula => String(matricula.aluno?.matricula ?? "") === numeroDigitado);

    if (!matriculaEncontrada) {
        mensagemAvi.textContent = "Matrícula não encontrada.";
        return;
    }

    aviIdMatriculaSelecionada = matriculaEncontrada.idMatricula;
    aviAlunoNome.value = matriculaEncontrada.aluno?.usuario?.nome ?? "";
    aviTurmaNome.value = matriculaEncontrada.turma?.nome ?? "";
    mensagemAvi.textContent = "Aluno localizado com sucesso.";
}

function abrirModalAvi() {
    if (!formAvi) return;
    formAvi.reset();
    aviAlunoNome.value = "";
    aviTurmaNome.value = "";
    aviIdMatriculaSelecionada = null;
    mensagemAvi.textContent = "";
    mensagemAvi.className = "mensagem-formulario";
    modalAvi.classList.add("ativo");
    aviMatricula.focus();
}

function fecharModalAvi() {
    if (!modalAvi) return;
    modalAvi.classList.remove("ativo");
    formAvi.reset();
    mensagemAvi.textContent = "";
    mensagemAvi.className = "mensagem-formulario";
}

if (formAvi) {
    formAvi.addEventListener("submit", async function (event) {
        event.preventDefault();
        mensagemAvi.textContent = "";
        if (!aviIdMatriculaSelecionada) {
            mensagemAvi.textContent = "Informe uma matrícula válida.";
            return;
        }
        const valor = Number(aviValor.value);
        if (Number.isNaN(valor) || valor < 0 || valor > 10) {
            mensagemAvi.textContent = "A nota deve estar entre 0 e 10.";
            return;
        }
        if (!aviData.value) {
            mensagemAvi.textContent = "Informe a data da avaliação.";
            return;
        }

        const dados = {
            idMatricula: aviIdMatriculaSelecionada,
            valor: valor,
            dataAvaliacao: aviData.value
        };

        try {
            const response = await requisicaoAutenticada("/notas/avi", { method: "POST", body: JSON.stringify(dados) });
            if (!response) return;
            if (!response.ok) {
                let mensagemErro = "Não foi possível lançar a AVI.";
                try {
                    const erro = await response.json();
                    mensagemErro = erro.mensagem || erro.message || erro.detail || mensagemErro;
                } catch (e) {
                    console.error("Não foi possível ler a resposta de erro.", e);
                }
                throw new Error(mensagemErro);
            }
            mensagemAvi.textContent = "AVI lançada com sucesso!";
            await carregarNotas();
            setTimeout(fecharModalAvi, 1000);
        } catch (erro) {
            mensagemAvi.textContent = erro.message || "Não foi possível lançar a AVI.";
        }
    });
}

// =====================================================
// GESTÃO DE FALTAS
// =====================================================
async function carregarFaltas() {
    try {
        const response = await requisicaoAutenticada("/faltas");
        if (!response || !response.ok) {
            if (listaFaltasElemento) listaFaltasElemento.textContent = "Não foi possível carregar as faltas.";
            if (totalFaltasElemento) totalFaltasElemento.textContent = "--";
            return;
        }
        faltas = await response.json();
        const quantidadeTotal = faltas.reduce((total, falta) => total + Number(falta.quantidade || 0), 0);
        if (totalFaltasElemento) totalFaltasElemento.textContent = quantidadeTotal;
        atualizarFiltrosFaltas();
        aplicarFiltrosFaltas();
    } catch (erro) {
        console.error("Erro ao carregar faltas:", erro);
        if (listaFaltasElemento) listaFaltasElemento.textContent = "Erro ao conectar com o servidor.";
        if (totalFaltasElemento) totalFaltasElemento.textContent = "--";
    }
}

function atualizarFiltrosFaltas() {
    if (!filtroFaltaTurma || !filtroFaltaDisciplina || !filtroFaltaAluno || !filtroFaltaProfessor) return;
    const turmaSelecionada = filtroFaltaTurma.value;
    const disciplinaSelecionada = filtroFaltaDisciplina.value;
    const faltasPorTurma = turmaSelecionada ? faltas.filter(falta => falta.turma === turmaSelecionada) : faltas;

    preencherFiltro(filtroFaltaTurma, valoresUnicos(faltas, "turma"), "Todas as turmas");
    preencherFiltro(filtroFaltaDisciplina, valoresUnicos(faltasPorTurma, "disciplina"), "Todas as disciplinas");

    const faltasParaAluno = faltasPorTurma.filter(falta => !disciplinaSelecionada || falta.disciplina === disciplinaSelecionada);
    preencherFiltro(filtroFaltaAluno, valoresUnicos(faltasParaAluno, "aluno"), "Todos os alunos");
    preencherFiltro(filtroFaltaProfessor, valoresUnicos(faltasParaAluno, "professor"), "Todos os professores");
}

function aplicarFiltrosFaltas() {
    if (!filtroFaltaTurma) return;
    const turma = filtroFaltaTurma.value;
    const disciplina = filtroFaltaDisciplina.value;
    const aluno = filtroFaltaAluno.value;
    const professor = filtroFaltaProfessor.value;

    const faltasFiltradas = faltas.filter(falta => {
        const turmaOk = !turma || falta.turma === turma;
        const disciplinaOk = !disciplina || falta.disciplina === disciplina;
        const alunoOk = !aluno || falta.aluno === aluno;
        const professorOk = !professor || falta.professor === professor;
        return turmaOk && disciplinaOk && alunoOk && professorOk;
    });

    montarTabelaFaltas(faltasFiltradas);
    if (contadorFaltas) contadorFaltas.textContent = `${faltasFiltradas.length} de ${faltas.length} registro(s) exibido(s).`;
}

function montarTabelaFaltas(dados) {
    if (!listaFaltasElemento) return;
    if (!dados || dados.length === 0) {
        listaFaltasElemento.textContent = "Nenhuma falta encontrada.";
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
                <td>${falta.aluno ?? "--"}</td>
                <td>${falta.matriculaAluno ?? "--"}</td>
                <td>${falta.turma ?? "--"}</td>
                <td>${falta.disciplina ?? "--"}</td>
                <td>${falta.professor ?? "--"}</td>
                <td>${falta.quantidade ?? 0}</td>
                <td>${formatarData(falta.dataFalta)}</td>
            </tr>
        `;
    });
    html += `</tbody></table></div>`;
    listaFaltasElemento.innerHTML = html;
}

// =====================================================
// REGISTROS DE AUDITORIA
// =====================================================
function obterUsuarioAuditoria(auditoria) {
    return auditoria.emailUsuario ?? auditoria.usuario ?? auditoria.email ?? "--";
}
function obterAcaoAuditoria(auditoria) {
    return auditoria.acao ?? auditoria.tipoAcao ?? "--";
}
function obterDescricaoAuditoria(auditoria) {
    return auditoria.descricao ?? auditoria.detalhes ?? "--";
}
function obterDataHoraAuditoria(auditoria) {
    return auditoria.dataHora ?? auditoria.data ?? auditoria.criadoEm ?? null;
}

function atualizarFiltrosAuditoria() {
    if (!filtroAuditoriaUsuario || !filtroAuditoriaAcao) return;
    preencherFiltro(filtroAuditoriaUsuario, [...new Set(auditorias.map(obterUsuarioAuditoria).filter(v => v && v !== "--"))].sort((a, b) => String(a).localeCompare(String(b), "pt-BR")), "Todos os usuários");
    preencherFiltro(filtroAuditoriaAcao, [...new Set(auditorias.map(obterAcaoAuditoria).filter(v => v && v !== "--"))].sort((a, b) => String(a).localeCompare(String(b), "pt-BR")), "Todas as ações");
}

function aplicarFiltrosAuditoria() {
    if (!filtroAuditoriaUsuario) return;
    const usuario = filtroAuditoriaUsuario.value;
    const acao = filtroAuditoriaAcao.value;
    const descricao = filtroAuditoriaDescricao.value.trim().toLowerCase();
    const dataSelecionada = filtroAuditoriaData.value;

    auditoriasFiltradas = auditorias.filter(auditoria => {
        const usuarioAuditoria = obterUsuarioAuditoria(auditoria);
        const acaoAuditoria = obterAcaoAuditoria(auditoria);
        const descricaoAuditoria = String(obterDescricaoAuditoria(auditoria)).toLowerCase();
        const dataHoraAuditoria = obterDataHoraAuditoria(auditoria);

        const usuarioOk = !usuario || usuarioAuditoria === usuario;
        const acaoOk = !acao || acaoAuditoria === acao;
        const descricaoOk = !descricao || descricaoAuditoria.includes(descricao);

        let dataOk = true;
        if (dataSelecionada && dataHoraAuditoria) {
            const dataAuditoria = new Date(dataHoraAuditoria);
            if (!isNaN(dataAuditoria.getTime())) {
                const ano = dataAuditoria.getFullYear();
                const mes = String(dataAuditoria.getMonth() + 1).padStart(2, "0");
                const dia = String(dataAuditoria.getDate()).padStart(2, "0");
                dataOk = `${ano}-${mes}-${dia}` === dataSelecionada;
            } else {
                dataOk = false;
            }
        }
        return usuarioOk && acaoOk && descricaoOk && dataOk;
    });

    paginaAtualAuditoria = 1;
    renderizarPaginaAuditoria();
}

function renderizarPaginaAuditoria() {
    const totalRegistros = auditoriasFiltradas.length;
    const totalPaginas = Math.max(1, Math.ceil(totalRegistros / registrosPorPaginaAuditoria));
    if (paginaAtualAuditoria > totalPaginas) paginaAtualAuditoria = totalPaginas;

    const inicio = (paginaAtualAuditoria - 1) * registrosPorPaginaAuditoria;
    const fim = inicio + registrosPorPaginaAuditoria;
    const registrosDaPagina = auditoriasFiltradas.slice(inicio, fim);

    montarTabelaAuditorias(registrosDaPagina);
    if (contadorAuditorias) contadorAuditorias.textContent = `${totalRegistros} de ${auditorias.length} registro(s) encontrado(s).`;
    montarPaginacaoAuditoria(totalPaginas);
}

function montarPaginacaoAuditoria(totalPaginas) {
    if (!paginacaoAuditorias) return;
    if (totalPaginas <= 1) {
        paginacaoAuditorias.innerHTML = "";
        return;
    }
    paginacaoAuditorias.innerHTML = `
        <button type="button" class="botao-paginacao" ${paginaAtualAuditoria === 1 ? "disabled" : ""} onclick="mudarPaginaAuditoria(${paginaAtualAuditoria - 1})">Anterior</button>
        <span class="pagina-atual">Página ${paginaAtualAuditoria} de ${totalPaginas}</span>
        <button type="button" class="botao-paginacao" ${paginaAtualAuditoria === totalPaginas ? "disabled" : ""} onclick="mudarPaginaAuditoria(${paginaAtualAuditoria + 1})">Próxima</button>
    `;
}

function mudarPaginaAuditoria(pagina) {
    paginaAtualAuditoria = pagina;
    renderizarPaginaAuditoria();
}

async function carregarAuditorias() {
    try {
        const response = await requisicaoAutenticada("/auditorias");
        if (!response || !response.ok) {
            if (listaAuditoriasElemento) listaAuditoriasElemento.textContent = "Não foi possível carregar a auditoria.";
            return;
        }
        auditorias = await response.json();
        atualizarFiltrosAuditoria();
        auditoriasFiltradas = [...auditorias];
        paginaAtualAuditoria = 1;
        renderizarPaginaAuditoria();
    } catch (erro) {
        console.error("Erro ao carregar auditorias:", erro);
        if (listaAuditoriasElemento) listaAuditoriasElemento.textContent = "Erro ao conectar com o servidor.";
    }
}

function montarTabelaAuditorias(dados) {
    if (!listaAuditoriasElemento) return;
    if (!dados || dados.length === 0) {
        listaAuditoriasElemento.textContent = "Nenhum registro de auditoria encontrado.";
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
        const id = auditoria.idAuditoria ?? auditoria.id ?? "--";
        const usuario = auditoria.emailUsuario ?? auditoria.usuario ?? auditoria.email ?? "--";
        const acao = auditoria.acao ?? auditoria.tipoAcao ?? "--";
        const descricao = auditoria.descricao ?? auditoria.detalhes ?? "--";
        const dataHora = auditoria.dataHora ?? auditoria.data ?? auditoria.criadoEm ?? "--";
        html += `
            <tr>
                <td>${id}</td>
                <td>${usuario}</td>
                <td>${acao}</td>
                <td>${descricao}</td>
                <td>${formatarDataHora(dataHora)}</td>
            </tr>
        `;
    });
    html += `</tbody></table></div>`;
    listaAuditoriasElemento.innerHTML = html;
}

// =====================================================
// MÉTODOS UTILITÁRIOS E HELPERS
// =====================================================
function valoresUnicos(itens, campo) {
    return [...new Set(itens.map(item => item[campo]).filter(valor => valor !== null && valor !== undefined && valor !== ""))].sort((a, b) => String(a).localeCompare(String(b), "pt-BR"));
}

function preencherFiltro(select, valores, textoPadrao) {
    if (!select) return;
    const valorAtual = select.value;
    select.innerHTML = `<option value="">${textoPadrao}</option>`;
    valores.forEach(valor => {
        const option = document.createElement("option");
        option.value = valor;
        option.textContent = valor;
        select.appendChild(option);
    });
    if (valores.some(valor => String(valor) === String(valorAtual))) {
        select.value = valorAtual;
    }
}

function montarBadgePerfil(perfilUsuario) {
    if (perfilUsuario === "ALUNO") return `<span class="badge badge-aluno">ALUNO</span>`;
    if (perfilUsuario === "PROFESSOR") return `<span class="badge badge-professor">PROFESSOR</span>`;
    if (perfilUsuario === "ADMINISTRADOR") return `<span class="badge badge-admin">ADMINISTRADOR</span>`;
    return perfilUsuario ?? "--";
}

async function lerJsonSeguro(response) {
    try {
        return await response.json();
    } catch {
        return null;
    }
}

function formatarNota(valor) {
    if (valor === null || valor === undefined) return "--";
    return Number(valor).toFixed(2);
}

function formatarData(data) {
    if (!data) return "--";
    const texto = String(data);
    if (!texto.includes("-")) return texto;
    const partes = texto.split("-");
    if (partes.length !== 3) return texto;
    return `${partes[2]}/${partes[1]}/${partes[0]}`;
}

function formatarDataHora(dataHora) {
    if (!dataHora || dataHora === "--") return "--";
    try {
        const data = new Date(dataHora);
        if (isNaN(data.getTime())) return dataHora;
        return data.toLocaleString("pt-BR");
    } catch {
        return dataHora;
    }
}

function atualizarMensagemInicio() {
    if (mensagemInicioElemento) {
        mensagemInicioElemento.textContent = "Utilize o menu lateral para gerenciar usuários, professores, vínculos acadêmicos, notas, faltas e consultar os registros de auditoria.";
    }
}

function atualizarMenuMobileAdmin(menuAberto) {
    if (!botaoMenuMobileAdmin || !sidebarAdmin || !overlayMenuAdmin) return;
    sidebarAdmin.classList.toggle("aberta", menuAberto);
    overlayMenuAdmin.classList.toggle("ativo", menuAberto);
    botaoMenuMobileAdmin.textContent = menuAberto ? "✕" : "☰";
    botaoMenuMobileAdmin.setAttribute("aria-expanded", String(menuAberto));
    botaoMenuMobileAdmin.setAttribute("aria-label", menuAberto ? "Fechar menu" : "Abrir menu");
}

// =====================================================
// REGISTRO DE EVENTOS DOM E INICIALIZAÇÃO
// =====================================================
document.addEventListener("DOMContentLoaded", function () {

    // Navegação
    menuItens.forEach(item => {
        item.addEventListener("click", function () {
            const secaoAlvo = item.getAttribute("data-secao");
            menuItens.forEach(menu => menu.classList.remove("ativo"));
            secoes.forEach(secao => secao.classList.remove("ativa"));
            item.classList.add("ativo");
            const secao = document.getElementById(secaoAlvo);
            if (secao) secao.classList.add("ativa");

            if (window.innerWidth <= 768) {
                atualizarMenuMobileAdmin(false);
            }
        });
    });

    if (botaoSair) {
        botaoSair.addEventListener("click", function () {
            localStorage.clear();
            window.location.href = "/login.html";
        });
    }

    // Menu Mobile
    if (botaoMenuMobileAdmin && sidebarAdmin && overlayMenuAdmin) {
        botaoMenuMobileAdmin.addEventListener("click", function () {
            const menuAberto = !sidebarAdmin.classList.contains("aberta");
            atualizarMenuMobileAdmin(menuAberto);
        });
        overlayMenuAdmin.addEventListener("click", function () {
            atualizarMenuMobileAdmin(false);
        });
    }

    window.addEventListener("resize", function () {
        if (window.innerWidth > 768) {
            atualizarMenuMobileAdmin(false);
        }
    });

    // Eventos de Filtros - Usuários
    document.getElementById("filtroUsuarioBusca")?.addEventListener("input", aplicarFiltrosUsuarios);
    document.getElementById("filtroUsuarioPerfil")?.addEventListener("change", aplicarFiltrosUsuarios);
    document.getElementById("filtroUsuarioStatus")?.addEventListener("change", aplicarFiltrosUsuarios);
    document.getElementById("limparFiltrosUsuarios")?.addEventListener("click", limparFiltrosUsuarios);

    // Eventos de Filtros - Professores
    const filtroProfessorBusca = document.getElementById("filtroProfessorBusca");
    const limparFiltrosProfessores = document.getElementById("limparFiltrosProfessores");
    if (filtroProfessorBusca) filtroProfessorBusca.addEventListener("input", aplicarFiltroProfessores);
    if (limparFiltrosProfessores) limparFiltrosProfessores.addEventListener("click", limparFiltroProfessores);

    // Eventos de Filtros - Turmas e Disciplinas
    document.getElementById("filtroTurmaBusca")?.addEventListener("input", aplicarFiltroTurmas);
    document.getElementById("limparFiltroTurmas")?.addEventListener("click", limparBuscaTurmas);
    document.getElementById("filtroDisciplinaBusca")?.addEventListener("input", aplicarFiltroDisciplinas);
    document.getElementById("limparFiltroDisciplinas")?.addEventListener("click", limparBuscaDisciplinas);

    // Eventos de Filtros - Vínculos
    document.getElementById("filtroVinculoTurma")?.addEventListener("change", aplicarFiltrosVinculos);
    document.getElementById("filtroVinculoDisciplina")?.addEventListener("change", aplicarFiltrosVinculos);
    document.getElementById("filtroVinculoProfessor")?.addEventListener("input", aplicarFiltrosVinculos);
    document.getElementById("limparFiltrosVinculos")?.addEventListener("click", limparBuscaVinculos);

    // Eventos de Filtros - Alunos
    document.getElementById("filtroAlunoBusca")?.addEventListener("input", aplicarFiltroAlunos);
    document.getElementById("limparFiltrosAlunos")?.addEventListener("click", limparFiltroAlunos);

    // Eventos de Filtros - Matrículas
    document.getElementById("filtroMatriculaBusca")?.addEventListener("input", aplicarFiltrosMatriculas);
    document.getElementById("filtroMatriculaTurma")?.addEventListener("change", aplicarFiltrosMatriculas);
    document.getElementById("filtroMatriculaStatus")?.addEventListener("change", aplicarFiltrosMatriculas);
    document.getElementById("limparFiltrosMatriculas")?.addEventListener("click", limparFiltrosMatriculas);

    // Eventos de Filtros - Notas
    if (filtroNotaTurma) {
        filtroNotaTurma.addEventListener("change", function () {
            if (filtroNotaDisciplina) filtroNotaDisciplina.value = "";
            if (filtroNotaAluno) filtroNotaAluno.value = "";
            if (filtroNotaProfessor) filtroNotaProfessor.value = "";
            atualizarFiltrosNotas();
            aplicarFiltrosNotas();
        });
    }
    if (filtroNotaDisciplina) {
        filtroNotaDisciplina.addEventListener("change", function () {
            if (filtroNotaAluno) filtroNotaAluno.value = "";
            if (filtroNotaProfessor) filtroNotaProfessor.value = "";
            atualizarFiltrosNotas();
            aplicarFiltrosNotas();
        });
    }
    if (filtroNotaAluno) filtroNotaAluno.addEventListener("change", aplicarFiltrosNotas);
    if (filtroNotaProfessor) filtroNotaProfessor.addEventListener("change", aplicarFiltrosNotas);
    if (filtroNotaAvaliacao) filtroNotaAvaliacao.addEventListener("change", aplicarFiltrosNotas);
    if (limparFiltrosNotas) {
        limparFiltrosNotas.addEventListener("click", function () {
            filtroNotaTurma.value = "";
            filtroNotaDisciplina.value = "";
            filtroNotaAluno.value = "";
            filtroNotaProfessor.value = "";
            filtroNotaAvaliacao.value = "";
            atualizarFiltrosNotas();
            aplicarFiltrosNotas();
        });
    }

    // Eventos de Filtros - Faltas
    if (filtroFaltaTurma) {
        filtroFaltaTurma.addEventListener("change", function () {
            if (filtroFaltaDisciplina) filtroFaltaDisciplina.value = "";
            if (filtroFaltaAluno) filtroFaltaAluno.value = "";
            if (filtroFaltaProfessor) filtroFaltaProfessor.value = "";
            atualizarFiltrosFaltas();
            aplicarFiltrosFaltas();
        });
    }
    if (filtroFaltaDisciplina) {
        filtroFaltaDisciplina.addEventListener("change", function () {
            if (filtroFaltaAluno) filtroFaltaAluno.value = "";
            if (filtroFaltaProfessor) filtroFaltaProfessor.value = "";
            atualizarFiltrosFaltas();
            aplicarFiltrosFaltas();
        });
    }
    if (filtroFaltaAluno) filtroFaltaAluno.addEventListener("change", aplicarFiltrosFaltas);
    if (filtroFaltaProfessor) filtroFaltaProfessor.addEventListener("change", aplicarFiltrosFaltas);
    if (limparFiltrosFaltas) {
        limparFiltrosFaltas.addEventListener("click", function () {
            filtroFaltaTurma.value = "";
            filtroFaltaDisciplina.value = "";
            filtroFaltaAluno.value = "";
            filtroFaltaProfessor.value = "";
            atualizarFiltrosFaltas();
            aplicarFiltrosFaltas();
        });
    }

    // Eventos de Filtros - Auditoria
    if (filtroAuditoriaUsuario) filtroAuditoriaUsuario.addEventListener("change", aplicarFiltrosAuditoria);
    if (filtroAuditoriaAcao) filtroAuditoriaAcao.addEventListener("change", aplicarFiltrosAuditoria);
    if (filtroAuditoriaDescricao) filtroAuditoriaDescricao.addEventListener("input", aplicarFiltrosAuditoria);
    if (filtroAuditoriaData) filtroAuditoriaData.addEventListener("change", aplicarFiltrosAuditoria);
    if (limparFiltrosAuditoria) {
        limparFiltrosAuditoria.addEventListener("click", function () {
            filtroAuditoriaUsuario.value = "";
            filtroAuditoriaAcao.value = "";
            filtroAuditoriaDescricao.value = "";
            filtroAuditoriaData.value = "";
            atualizarFiltrosAuditoria();
            aplicarFiltrosAuditoria();
        });
    }

    // Abertura e Fechamento de Modais
    if (botaoAbrirUsuario) botaoAbrirUsuario.addEventListener("click", abrirModalUsuario);
    if (botaoFecharUsuario) botaoFecharUsuario.addEventListener("click", fecharModalUsuario);
    if (modalUsuario) modalUsuario.addEventListener("click", e => { if (e.target === modalUsuario) fecharModalUsuario(); });

    if (botaoFecharEditarUsuario) botaoFecharEditarUsuario.addEventListener("click", fecharModalEditarUsuario);
    if (modalEditarUsuario) modalEditarUsuario.addEventListener("click", e => { if (e.target === modalEditarUsuario) fecharModalEditarUsuario(); });

    if (botaoAbrirAluno) botaoAbrirAluno.addEventListener("click", abrirModalAluno);
    if (botaoFecharAluno) botaoFecharAluno.addEventListener("click", fecharModalAluno);
    if (modalAluno) modalAluno.addEventListener("click", e => { if (e.target === modalAluno) fecharModalAluno(); });

    if (botaoAbrirProfessor) botaoAbrirProfessor.addEventListener("click", abrirModalProfessor);
    if (botaoFecharProfessor) botaoFecharProfessor.addEventListener("click", fecharModalProfessor);
    if (modalProfessor) modalProfessor.addEventListener("click", e => { if (e.target === modalProfessor) fecharModalProfessor(); });

    if (botaoFecharEditarProfessor) botaoFecharEditarProfessor.addEventListener("click", fecharModalEditarProfessor);
    if (modalEditarProfessor) modalEditarProfessor.addEventListener("click", e => { if (e.target === modalEditarProfessor) fecharModalEditarProfessor(); });

    if (botaoAbrirTurma) botaoAbrirTurma.addEventListener("click", abrirModalTurma);
    if (botaoFecharTurma) botaoFecharTurma.addEventListener("click", fecharModalTurma);
    if (modalTurma) modalTurma.addEventListener("click", e => { if (e.target === modalTurma) fecharModalTurma(); });

    if (botaoFecharEditarTurma) botaoFecharEditarTurma.addEventListener("click", fecharModalEditarTurma);
    if (modalEditarTurma) modalEditarTurma.addEventListener("click", e => { if (e.target === modalEditarTurma) fecharModalEditarTurma(); });

    if (botaoAbrirDisciplina) botaoAbrirDisciplina.addEventListener("click", abrirModalDisciplina);
    if (botaoFecharDisciplina) botaoFecharDisciplina.addEventListener("click", fecharModalDisciplina);
    if (modalDisciplina) modalDisciplina.addEventListener("click", e => { if (e.target === modalDisciplina) fecharModalDisciplina(); });

    if (botaoFecharEditarDisciplina) botaoFecharEditarDisciplina.addEventListener("click", fecharModalEditarDisciplina);
    if (modalEditarDisciplina) modalEditarDisciplina.addEventListener("click", e => { if (e.target === modalEditarDisciplina) fecharModalEditarDisciplina(); });

    if (botaoAbrirVinculo) botaoAbrirVinculo.addEventListener("click", abrirModalVinculo);
    if (botaoFecharVinculo) botaoFecharVinculo.addEventListener("click", fecharModalVinculo);
    if (modalVinculo) modalVinculo.addEventListener("click", e => { if (e.target === modalVinculo) fecharModalVinculo(); });

    if (botaoFecharTrocarProfessor) botaoFecharTrocarProfessor.addEventListener("click", fecharModalTrocarProfessor);
    if (modalTrocarProfessor) modalTrocarProfessor.addEventListener("click", e => { if (e.target === modalTrocarProfessor) fecharModalTrocarProfessor(); });

    if (botaoAbrirMatricula) botaoAbrirMatricula.addEventListener("click", abrirModalMatricula);
    if (botaoFecharMatricula) botaoFecharMatricula.addEventListener("click", fecharModalMatricula);
    if (modalMatricula) modalMatricula.addEventListener("click", e => { if (e.target === modalMatricula) fecharModalMatricula(); });

    if (botaoFecharEditarMatricula) botaoFecharEditarMatricula.addEventListener("click", fecharModalEditarMatricula);
    if (modalEditarMatricula) modalEditarMatricula.addEventListener("click", e => { if (e.target === modalEditarMatricula) fecharModalEditarMatricula(); });

    if (botaoAbrirAvi) botaoAbrirAvi.addEventListener("click", abrirModalAvi);
    if (botaoFecharAvi) botaoFecharAvi.addEventListener("click", fecharModalAvi);
    if (modalAvi) modalAvi.addEventListener("click", e => { if (e.target === modalAvi) fecharModalAvi(); });
    if (aviMatricula) aviMatricula.addEventListener("input", localizarMatriculaAvi);

    // Ativa primeira aba por padrão caso nenhuma esteja
    const primeiraAba = document.querySelector(".menu-item");
    if (primeiraAba && !document.querySelector(".menu-item.ativo")) {
        primeiraAba.click();
    }

    // Dispara a requisição para carregar todos os dados da API
    carregarDados();
});
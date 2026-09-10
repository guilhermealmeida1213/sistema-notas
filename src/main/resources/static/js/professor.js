// =========================
// AUTENTICAÇÃO
// =========================

const token =
    localStorage.getItem("token");

const perfil =
    localStorage.getItem("perfil");


// =========================
// ELEMENTOS PRINCIPAIS
// =========================

const menuItens =
    document.querySelectorAll(".menu-item");

const secoes =
    document.querySelectorAll(".secao");

const totalNotasElemento =
    document.getElementById("totalNotas");

const totalFaltasElemento =
    document.getElementById("totalFaltas");

const mensagemInicioElemento =
    document.getElementById("mensagemInicio");

const listaNotasElemento =
    document.getElementById("listaNotas");

const listaFaltasElemento =
    document.getElementById("listaFaltas");

const botaoSair =
    document.getElementById("botaoSair");


// =========================
// FILTROS DE NOTAS
// =========================

const filtroNotaTurma =
    document.getElementById("filtroNotaTurma");

const filtroNotaDisciplina =
    document.getElementById("filtroNotaDisciplina");

const filtroNotaAluno =
    document.getElementById("filtroNotaAluno");

const filtroNotaAvaliacao =
    document.getElementById("filtroNotaAvaliacao");

const botaoLimparFiltrosNotas =
    document.getElementById("botaoLimparFiltrosNotas");

const resultadoFiltroNotas =
    document.getElementById("resultadoFiltroNotas");


// =========================
// FILTROS DE FALTAS
// =========================

const filtroFaltaTurma =
    document.getElementById("filtroFaltaTurma");

const filtroFaltaDisciplina =
    document.getElementById("filtroFaltaDisciplina");

const filtroFaltaAluno =
    document.getElementById("filtroFaltaAluno");

const botaoLimparFiltrosFaltas =
    document.getElementById("botaoLimparFiltrosFaltas");

const resultadoFiltroFaltas =
    document.getElementById("resultadoFiltroFaltas");


// =========================
// LANÇAR NOTA
// =========================

const botaoAbrirNota =
    document.getElementById("botaoAbrirNota");

const botaoFecharNota =
    document.getElementById("botaoFecharNota");

const modalNota =
    document.getElementById("modalNota");

const formNota =
    document.getElementById("formNota");

const notaVinculo =
    document.getElementById("notaVinculo");

const notaAluno =
    document.getElementById("notaAluno");

const notaTipoAvaliacao =
    document.getElementById("notaTipoAvaliacao");

const notaValor =
    document.getElementById("notaValor");

const notaData =
    document.getElementById("notaData");

const mensagemNota =
    document.getElementById("mensagemNota");


// =========================
// EDITAR NOTA
// =========================

const modalEditarNota =
    document.getElementById("modalEditarNota");

const botaoFecharEditarNota =
    document.getElementById(
        "botaoFecharEditarNota"
    );

const formEditarNota =
    document.getElementById(
        "formEditarNota"
    );

const editarNotaId =
    document.getElementById(
        "editarNotaId"
    );

const editarNotaAluno =
    document.getElementById(
        "editarNotaAluno"
    );

const editarNotaDisciplina =
    document.getElementById(
        "editarNotaDisciplina"
    );

const editarNotaTipo =
    document.getElementById(
        "editarNotaTipo"
    );

const editarNotaValor =
    document.getElementById(
        "editarNotaValor"
    );

const editarNotaData =
    document.getElementById(
        "editarNotaData"
    );

const mensagemEditarNota =
    document.getElementById(
        "mensagemEditarNota"
    );


// =========================
// LANÇAR FALTA
// =========================

const botaoAbrirFalta =
    document.getElementById("botaoAbrirFalta");

const botaoFecharFalta =
    document.getElementById("botaoFecharFalta");

const modalFalta =
    document.getElementById("modalFalta");

const formFalta =
    document.getElementById("formFalta");

const faltaVinculo =
    document.getElementById("faltaVinculo");

const faltaAluno =
    document.getElementById("faltaAluno");

const faltaQuantidade =
    document.getElementById("faltaQuantidade");

const faltaData =
    document.getElementById("faltaData");

const mensagemFalta =
    document.getElementById("mensagemFalta");


// =========================
// EDITAR FALTA
// =========================

const modalEditarFalta =
    document.getElementById(
        "modalEditarFalta"
    );

const botaoFecharEditarFalta =
    document.getElementById(
        "botaoFecharEditarFalta"
    );

const formEditarFalta =
    document.getElementById(
        "formEditarFalta"
    );

const editarFaltaId =
    document.getElementById(
        "editarFaltaId"
    );

const editarFaltaAluno =
    document.getElementById(
        "editarFaltaAluno"
    );

const editarFaltaDisciplina =
    document.getElementById(
        "editarFaltaDisciplina"
    );

const editarFaltaQuantidade =
    document.getElementById(
        "editarFaltaQuantidade"
    );

const editarFaltaData =
    document.getElementById(
        "editarFaltaData"
    );

const mensagemEditarFalta =
    document.getElementById(
        "mensagemEditarFalta"
    );


// =========================
// DADOS EM MEMÓRIA
// =========================

let notasDoProfessor = [];

let faltasDoProfessor = [];

let turmasDisciplinas = [];

let matriculasDaTurma = [];


// =========================
// PROTEGER PÁGINA
// =========================

if (!token || perfil !== "PROFESSOR") {

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
// SAIR
// =========================

botaoSair.addEventListener(
    "click",
    function () {

        localStorage.clear();

        window.location.href =
            "/login.html";
    }
);


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

    return response;
}


// =========================
// CARREGAR DADOS
// =========================

async function carregarDados() {

    await Promise.all([
        carregarNotas(),
        carregarFaltas(),
        carregarTurmasDisciplinas()
    ]);

    atualizarMensagemInicio();
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
// CARREGAR NOTAS
// =========================

async function carregarNotas() {

    try {

        const response =
            await requisicaoAutenticada(
                "/notas/minhas-turmas"
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

        notasDoProfessor =
            await response.json();

        totalNotasElemento.textContent =
            notasDoProfessor.length;

        atualizarFiltrosNotas();

        aplicarFiltrosNotas();

    } catch (erro) {

        console.error(
            "Erro ao carregar notas:",
            erro
        );

        listaNotasElemento.textContent =
            "Erro ao conectar com o servidor.";
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
            ? notasDoProfessor.filter(
                nota =>
                    nota.turma ===
                    turmaSelecionada
            )
            : notasDoProfessor;

    preencherFiltro(
        filtroNotaTurma,
        valoresUnicos(
            notasDoProfessor,
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
}


function aplicarFiltrosNotas() {

    const turma =
        filtroNotaTurma.value;

    const disciplina =
        filtroNotaDisciplina.value;

    const aluno =
        filtroNotaAluno.value;

    const avaliacao =
        filtroNotaAvaliacao.value;

    const notasFiltradas =
        notasDoProfessor.filter(
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

                const avaliacaoOk =
                    !avaliacao ||
                    nota.tipoAvaliacao ===
                    avaliacao;

                return (
                    turmaOk &&
                    disciplinaOk &&
                    alunoOk &&
                    avaliacaoOk
                );
            }
        );

    montarTabelaNotas(
        notasFiltradas
    );

    resultadoFiltroNotas.textContent =
        `${notasFiltradas.length} de ${notasDoProfessor.length} registro(s) exibido(s).`;
}


filtroNotaTurma.addEventListener(
    "change",
    function () {

        filtroNotaDisciplina.value =
            "";

        filtroNotaAluno.value =
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

        atualizarFiltrosNotas();

        aplicarFiltrosNotas();
    }
);


filtroNotaAluno.addEventListener(
    "change",
    aplicarFiltrosNotas
);


filtroNotaAvaliacao.addEventListener(
    "change",
    aplicarFiltrosNotas
);


botaoLimparFiltrosNotas.addEventListener(
    "click",
    function () {

        filtroNotaTurma.value =
            "";

        filtroNotaDisciplina.value =
            "";

        filtroNotaAluno.value =
            "";

        filtroNotaAvaliacao.value =
            "";

        atualizarFiltrosNotas();

        aplicarFiltrosNotas();
    }
);


// =========================
// TABELA DE NOTAS
// =========================

function montarTabelaNotas(notas) {

    if (!notas ||
        notas.length === 0) {

        listaNotasElemento.innerHTML = `
            <div class="estado-vazio">
                Nenhuma nota encontrada com os filtros selecionados.
            </div>
        `;

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
                        <th>Avaliação</th>
                        <th>Nota</th>
                        <th>Data</th>
                        <th>Ação</th>
                    </tr>
                </thead>

                <tbody>
    `;

    notas.forEach(nota => {

        let acao;

        if (
            nota.tipoAvaliacao === "AVC" ||
            nota.tipoAvaliacao === "AVG"
        ) {

            acao = `
                <button
                        type="button"
                        class="botao-editar"
                        onclick="abrirModalEditarNota(${nota.idNota})">

                    Editar

                </button>
            `;

        } else {

            acao = `
                <span class="somente-admin">
                    Somente Admin
                </span>
            `;
        }

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

                <td>
                    ${acao}
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
// EDITAR NOTA
// =========================

function abrirModalEditarNota(
    idNota
) {

    const nota =
        notasDoProfessor.find(
            item =>
                Number(item.idNota) ===
                Number(idNota)
        );

    if (!nota) {
        return;
    }

    if (nota.tipoAvaliacao === "AVI") {
        return;
    }

    editarNotaId.value =
        nota.idNota;

    editarNotaAluno.textContent =
        nota.aluno;

    editarNotaDisciplina.textContent =
        nota.disciplina;

    editarNotaTipo.textContent =
        `Avaliação: ${nota.tipoAvaliacao}`;

    editarNotaValor.value =
        nota.valor;

    editarNotaData.value =
        nota.dataAvaliacao;

    mensagemEditarNota.textContent =
        "";

    mensagemEditarNota.className =
        "mensagem-formulario";

    modalEditarNota.classList.add(
        "ativo"
    );
}


function fecharModalEditarNota() {

    modalEditarNota.classList.remove(
        "ativo"
    );

    formEditarNota.reset();

    mensagemEditarNota.textContent =
        "";
}


formEditarNota.addEventListener(
    "submit",
    async function (event) {

        event.preventDefault();

        const idNota =
            editarNotaId.value;

        const dados = {

            valor:
                Number(
                    editarNotaValor.value
                ),

            dataAvaliacao:
            editarNotaData.value
        };

        mensagemEditarNota.className =
            "mensagem-formulario";

        mensagemEditarNota.textContent =
            "Salvando alteração...";

        try {

            const response =
                await requisicaoAutenticada(
                    `/notas/${idNota}`,
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

                mensagemEditarNota.className =
                    "mensagem-formulario erro";

                mensagemEditarNota.textContent =
                    resposta?.mensagem
                    ?? "Não foi possível alterar a nota.";

                return;
            }

            mensagemEditarNota.className =
                "mensagem-formulario sucesso";

            mensagemEditarNota.textContent =
                "Nota alterada com sucesso!";

            await carregarNotas();

            setTimeout(
                function () {

                    fecharModalEditarNota();
                },
                700
            );

        } catch (erro) {

            console.error(
                "Erro ao alterar nota:",
                erro
            );

            mensagemEditarNota.className =
                "mensagem-formulario erro";

            mensagemEditarNota.textContent =
                "Erro ao conectar com o servidor.";
        }
    }
);


botaoFecharEditarNota.addEventListener(
    "click",
    fecharModalEditarNota
);


modalEditarNota.addEventListener(
    "click",
    function (event) {

        if (
            event.target ===
            modalEditarNota
        ) {

            fecharModalEditarNota();
        }
    }
);


// =========================
// CARREGAR FALTAS
// =========================

async function carregarFaltas() {

    try {

        const response =
            await requisicaoAutenticada(
                "/faltas/minhas-turmas"
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

        faltasDoProfessor =
            await response.json();

        const total =
            faltasDoProfessor.reduce(
                function (soma, falta) {

                    return soma +
                        Number(
                            falta.quantidade || 0
                        );
                },
                0
            );

        totalFaltasElemento.textContent =
            total;

        atualizarFiltrosFaltas();

        aplicarFiltrosFaltas();

    } catch (erro) {

        console.error(
            "Erro ao carregar faltas:",
            erro
        );

        listaFaltasElemento.textContent =
            "Erro ao conectar com o servidor.";
    }
}


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
            ? faltasDoProfessor.filter(
                falta =>
                    falta.turma ===
                    turmaSelecionada
            )
            : faltasDoProfessor;

    preencherFiltro(
        filtroFaltaTurma,
        valoresUnicos(
            faltasDoProfessor,
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
}


function aplicarFiltrosFaltas() {

    const turma =
        filtroFaltaTurma.value;

    const disciplina =
        filtroFaltaDisciplina.value;

    const aluno =
        filtroFaltaAluno.value;

    const faltasFiltradas =
        faltasDoProfessor.filter(
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

                return (
                    turmaOk &&
                    disciplinaOk &&
                    alunoOk
                );
            }
        );

    montarTabelaFaltas(
        faltasFiltradas
    );

    resultadoFiltroFaltas.textContent =
        `${faltasFiltradas.length} de ${faltasDoProfessor.length} registro(s) exibido(s).`;
}


filtroFaltaTurma.addEventListener(
    "change",
    function () {

        filtroFaltaDisciplina.value =
            "";

        filtroFaltaAluno.value =
            "";

        atualizarFiltrosFaltas();

        aplicarFiltrosFaltas();
    }
);


filtroFaltaDisciplina.addEventListener(
    "change",
    function () {

        filtroFaltaAluno.value =
            "";

        atualizarFiltrosFaltas();

        aplicarFiltrosFaltas();
    }
);


filtroFaltaAluno.addEventListener(
    "change",
    aplicarFiltrosFaltas
);


botaoLimparFiltrosFaltas.addEventListener(
    "click",
    function () {

        filtroFaltaTurma.value =
            "";

        filtroFaltaDisciplina.value =
            "";

        filtroFaltaAluno.value =
            "";

        atualizarFiltrosFaltas();

        aplicarFiltrosFaltas();
    }
);


// =========================
// TABELA DE FALTAS
// =========================

function montarTabelaFaltas(faltas) {

    if (!faltas ||
        faltas.length === 0) {

        listaFaltasElemento.innerHTML = `
            <div class="estado-vazio">
                Nenhuma falta encontrada com os filtros selecionados.
            </div>
        `;

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
                        <th>Quantidade</th>
                        <th>Data</th>
                        <th>Ação</th>
                    </tr>
                </thead>

                <tbody>
    `;

    faltas.forEach(falta => {

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
                    ${falta.quantidade ?? 0}
                </td>

                <td>
                    ${formatarData(
            falta.dataFalta
        )}
                </td>

                <td>

                    <button
                            type="button"
                            class="botao-editar"
                            onclick="abrirModalEditarFalta(${falta.idFalta})">

                        Editar

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

    listaFaltasElemento.innerHTML =
        html;
}


// =========================
// EDITAR FALTA
// =========================

function abrirModalEditarFalta(
    idFalta
) {

    const falta =
        faltasDoProfessor.find(
            item =>
                Number(item.idFalta) ===
                Number(idFalta)
        );

    if (!falta) {
        return;
    }

    editarFaltaId.value =
        falta.idFalta;

    editarFaltaAluno.textContent =
        falta.aluno;

    editarFaltaDisciplina.textContent =
        falta.disciplina;

    editarFaltaQuantidade.value =
        falta.quantidade;

    editarFaltaData.value =
        falta.dataFalta;

    mensagemEditarFalta.textContent =
        "";

    mensagemEditarFalta.className =
        "mensagem-formulario";

    modalEditarFalta.classList.add(
        "ativo"
    );
}


function fecharModalEditarFalta() {

    modalEditarFalta.classList.remove(
        "ativo"
    );

    formEditarFalta.reset();

    mensagemEditarFalta.textContent =
        "";

    mensagemEditarFalta.className =
        "mensagem-formulario";
}


formEditarFalta.addEventListener(
    "submit",
    async function (event) {

        event.preventDefault();

        const idFalta =
            editarFaltaId.value;

        const dados = {

            quantidade:
                Number(
                    editarFaltaQuantidade.value
                ),

            dataFalta:
            editarFaltaData.value
        };

        mensagemEditarFalta.className =
            "mensagem-formulario";

        mensagemEditarFalta.textContent =
            "Salvando alteração...";

        try {

            const response =
                await requisicaoAutenticada(
                    `/faltas/${idFalta}`,
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

                mensagemEditarFalta.className =
                    "mensagem-formulario erro";

                mensagemEditarFalta.textContent =
                    resposta?.mensagem
                    ?? "Não foi possível alterar a falta.";

                return;
            }

            mensagemEditarFalta.className =
                "mensagem-formulario sucesso";

            mensagemEditarFalta.textContent =
                "Falta alterada com sucesso!";

            await carregarFaltas();

            setTimeout(
                function () {

                    fecharModalEditarFalta();
                },
                700
            );

        } catch (erro) {

            console.error(
                "Erro ao alterar falta:",
                erro
            );

            mensagemEditarFalta.className =
                "mensagem-formulario erro";

            mensagemEditarFalta.textContent =
                "Erro ao conectar com o servidor.";
        }
    }
);


botaoFecharEditarFalta.addEventListener(
    "click",
    fecharModalEditarFalta
);


modalEditarFalta.addEventListener(
    "click",
    function (event) {

        if (
            event.target ===
            modalEditarFalta
        ) {

            fecharModalEditarFalta();
        }
    }
);


// =========================
// CARREGAR VÍNCULOS
// =========================

async function carregarTurmasDisciplinas() {

    try {

        const response =
            await requisicaoAutenticada(
                "/turmas-disciplinas/minhas"
            );

        if (!response ||
            !response.ok) {

            return;
        }

        turmasDisciplinas =
            await response.json();

    } catch (erro) {

        console.error(
            "Erro ao carregar vínculos:",
            erro
        );
    }
}


// =========================
// SELECT DE VÍNCULOS
// =========================

function preencherSelectVinculos(
    select
) {

    select.innerHTML = `
        <option value="">
            Selecione a turma e disciplina
        </option>
    `;

    turmasDisciplinas.forEach(
        vinculo => {

            const option =
                document.createElement(
                    "option"
                );

            option.value =
                vinculo.idTurmaDisciplina;

            option.dataset.idTurma =
                vinculo.turma?.idTurma;

            option.textContent =
                `${vinculo.turma?.nome ?? "--"} - ${vinculo.disciplina?.nome ?? "--"}`;

            select.appendChild(
                option
            );
        }
    );
}


// =========================
// CARREGAR ALUNOS DA TURMA
// =========================

async function carregarAlunosDaTurma(
    idTurma,
    selectAluno
) {

    selectAluno.innerHTML = `
        <option value="">
            Carregando alunos...
        </option>
    `;

    selectAluno.disabled =
        true;

    try {

        const response =
            await requisicaoAutenticada(
                `/matriculas/turma/${idTurma}`
            );

        if (!response) {
            return;
        }

        if (!response.ok) {

            selectAluno.innerHTML = `
                <option value="">
                    Não foi possível carregar os alunos
                </option>
            `;

            return;
        }

        matriculasDaTurma =
            await response.json();

        selectAluno.innerHTML = `
            <option value="">
                Selecione o aluno
            </option>
        `;

        matriculasDaTurma.forEach(
            matricula => {

                const option =
                    document.createElement(
                        "option"
                    );

                option.value =
                    matricula.idMatricula;

                const nome =
                    matricula.aluno
                        ?.usuario
                        ?.nome ??
                    matricula.aluno
                        ?.nome ??
                    "Aluno";

                const matriculaAluno =
                    matricula.aluno
                        ?.matricula ??
                    matricula.aluno
                        ?.registroAcademico ??
                    "";

                option.textContent =
                    matriculaAluno
                        ? `${nome} - ${matriculaAluno}`
                        : nome;

                selectAluno.appendChild(
                    option
                );
            }
        );

        selectAluno.disabled =
            matriculasDaTurma.length === 0;

        if (
            matriculasDaTurma.length === 0
        ) {

            selectAluno.innerHTML = `
                <option value="">
                    Nenhum aluno matriculado
                </option>
            `;
        }

    } catch (erro) {

        console.error(
            "Erro ao carregar alunos:",
            erro
        );

        selectAluno.innerHTML = `
            <option value="">
                Erro ao carregar alunos
            </option>
        `;
    }
}


// =========================
// LANÇAR NOTA
// =========================

function abrirModalNota() {

    formNota.reset();

    preencherSelectVinculos(
        notaVinculo
    );

    notaAluno.innerHTML = `
        <option value="">
            Selecione primeiro a turma
        </option>
    `;

    notaAluno.disabled =
        true;

    mensagemNota.textContent =
        "";

    mensagemNota.className =
        "mensagem-formulario";

    modalNota.classList.add(
        "ativo"
    );
}


function fecharModalNota() {

    modalNota.classList.remove(
        "ativo"
    );

    formNota.reset();

    mensagemNota.textContent =
        "";
}


botaoAbrirNota.addEventListener(
    "click",
    abrirModalNota
);


botaoFecharNota.addEventListener(
    "click",
    fecharModalNota
);


modalNota.addEventListener(
    "click",
    function (event) {

        if (event.target === modalNota) {

            fecharModalNota();
        }
    }
);


notaVinculo.addEventListener(
    "change",
    function () {

        const selecionado =
            notaVinculo.options[
                notaVinculo.selectedIndex
                ];

        const idTurma =
            Number(
                selecionado
                    ?.dataset
                    ?.idTurma
            );

        if (!idTurma) {

            notaAluno.disabled =
                true;

            notaAluno.innerHTML = `
                <option value="">
                    Selecione primeiro a turma
                </option>
            `;

            return;
        }

        carregarAlunosDaTurma(
            idTurma,
            notaAluno
        );
    }
);


formNota.addEventListener(
    "submit",
    async function (event) {

        event.preventDefault();

        const dados = {

            matricula: {
                idMatricula:
                    Number(
                        notaAluno.value
                    )
            },

            turmaDisciplina: {
                idTurmaDisciplina:
                    Number(
                        notaVinculo.value
                    )
            },

            tipoAvaliacao:
            notaTipoAvaliacao.value,

            valor:
                Number(
                    notaValor.value
                ),

            dataAvaliacao:
            notaData.value
        };

        mensagemNota.className =
            "mensagem-formulario";

        mensagemNota.textContent =
            "Salvando nota...";

        try {

            const response =
                await requisicaoAutenticada(
                    "/notas",
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

                mensagemNota.className =
                    "mensagem-formulario erro";

                mensagemNota.textContent =
                    resposta?.mensagem
                    ?? "Não foi possível lançar a nota.";

                return;
            }

            mensagemNota.className =
                "mensagem-formulario sucesso";

            mensagemNota.textContent =
                "Nota lançada com sucesso!";

            await carregarNotas();

            setTimeout(
                fecharModalNota,
                700
            );

        } catch (erro) {

            console.error(
                "Erro ao lançar nota:",
                erro
            );

            mensagemNota.className =
                "mensagem-formulario erro";

            mensagemNota.textContent =
                "Erro ao conectar com o servidor.";
        }
    }
);


// =========================
// LANÇAR FALTA
// =========================

function abrirModalFalta() {

    formFalta.reset();

    preencherSelectVinculos(
        faltaVinculo
    );

    faltaAluno.innerHTML = `
        <option value="">
            Selecione primeiro a turma
        </option>
    `;

    faltaAluno.disabled =
        true;

    mensagemFalta.textContent =
        "";

    mensagemFalta.className =
        "mensagem-formulario";

    modalFalta.classList.add(
        "ativo"
    );
}


function fecharModalFalta() {

    modalFalta.classList.remove(
        "ativo"
    );

    formFalta.reset();

    mensagemFalta.textContent =
        "";
}


botaoAbrirFalta.addEventListener(
    "click",
    abrirModalFalta
);


botaoFecharFalta.addEventListener(
    "click",
    fecharModalFalta
);


modalFalta.addEventListener(
    "click",
    function (event) {

        if (event.target === modalFalta) {

            fecharModalFalta();
        }
    }
);


faltaVinculo.addEventListener(
    "change",
    function () {

        const selecionado =
            faltaVinculo.options[
                faltaVinculo.selectedIndex
                ];

        const idTurma =
            Number(
                selecionado
                    ?.dataset
                    ?.idTurma
            );

        if (!idTurma) {

            faltaAluno.disabled =
                true;

            faltaAluno.innerHTML = `
                <option value="">
                    Selecione primeiro a turma
                </option>
            `;

            return;
        }

        carregarAlunosDaTurma(
            idTurma,
            faltaAluno
        );
    }
);


formFalta.addEventListener(
    "submit",
    async function (event) {

        event.preventDefault();

        const dados = {

            matricula: {
                idMatricula:
                    Number(
                        faltaAluno.value
                    )
            },

            turmaDisciplina: {
                idTurmaDisciplina:
                    Number(
                        faltaVinculo.value
                    )
            },

            quantidade:
                Number(
                    faltaQuantidade.value
                ),

            dataFalta:
            faltaData.value
        };

        mensagemFalta.className =
            "mensagem-formulario";

        mensagemFalta.textContent =
            "Salvando falta...";

        try {

            const response =
                await requisicaoAutenticada(
                    "/faltas",
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

                mensagemFalta.className =
                    "mensagem-formulario erro";

                mensagemFalta.textContent =
                    resposta?.mensagem
                    ?? "Não foi possível lançar a falta.";

                return;
            }

            mensagemFalta.className =
                "mensagem-formulario sucesso";

            mensagemFalta.textContent =
                "Falta lançada com sucesso!";

            await carregarFaltas();

            setTimeout(
                fecharModalFalta,
                700
            );

        } catch (erro) {

            console.error(
                "Erro ao lançar falta:",
                erro
            );

            mensagemFalta.className =
                "mensagem-formulario erro";

            mensagemFalta.textContent =
                "Erro ao conectar com o servidor.";
        }
    }
);


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

function formatarNota(valor) {

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

function formatarData(data) {

    if (!data) {

        return "--";
    }

    const partes =
        data.split("-");

    if (partes.length !== 3) {

        return data;
    }

    return `${partes[2]}/${partes[1]}/${partes[0]}`;
}


// =========================
// RESUMO
// =========================

function atualizarMensagemInicio() {

    if (
        !turmasDisciplinas ||
        turmasDisciplinas.length === 0
    ) {

        mensagemInicioElemento.textContent =
            "Nenhuma turma ou disciplina está vinculada ao seu professor.";

        return;
    }

    const mapaTurmas =
        new Map();

    turmasDisciplinas.forEach(
        vinculo => {

            const nomeTurma =
                vinculo.turma?.nome ??
                "Turma";

            const disciplina =
                vinculo.disciplina?.nome ??
                "Disciplina";

            if (
                !mapaTurmas.has(
                    nomeTurma
                )
            ) {

                mapaTurmas.set(
                    nomeTurma,
                    []
                );
            }

            mapaTurmas
                .get(nomeTurma)
                .push(
                    disciplina
                );
        }
    );

    let html = "";

    mapaTurmas.forEach(
        (
            disciplinas,
            turma
        ) => {

            html += `
                <div class="turma-resumo">

                    <strong>
                        ${turma}
                    </strong>

                    <span>
                        ${disciplinas.join(" • ")}
                    </span>

                </div>
            `;
        }
    );

    mensagemInicioElemento.innerHTML =
        html;
}

// =====================================================
// MENU MOBILE - PROFESSOR
// =====================================================

const botaoMenuMobileProfessor =
    document.getElementById(
        "botaoMenuMobileProfessor"
    );

const sidebarProfessor =
    document.getElementById(
        "sidebarProfessor"
    );

const overlayMenuProfessor =
    document.getElementById(
        "overlayMenuProfessor"
    );


function atualizarMenuMobileProfessor(
    menuAberto
) {

    if (
        !botaoMenuMobileProfessor ||
        !sidebarProfessor ||
        !overlayMenuProfessor
    ) {
        return;
    }


    sidebarProfessor.classList.toggle(
        "aberta",
        menuAberto
    );


    overlayMenuProfessor.classList.toggle(
        "ativo",
        menuAberto
    );


    botaoMenuMobileProfessor.textContent =
        menuAberto
            ? "✕"
            : "☰";


    botaoMenuMobileProfessor.setAttribute(
        "aria-expanded",
        String(menuAberto)
    );


    botaoMenuMobileProfessor.setAttribute(
        "aria-label",

        menuAberto
            ? "Fechar menu"
            : "Abrir menu"
    );
}


if (
    botaoMenuMobileProfessor &&
    sidebarProfessor &&
    overlayMenuProfessor
) {

    botaoMenuMobileProfessor.addEventListener(
        "click",
        function () {

            const menuAberto =
                !sidebarProfessor
                    .classList
                    .contains(
                        "aberta"
                    );


            atualizarMenuMobileProfessor(
                menuAberto
            );
        }
    );


    overlayMenuProfessor.addEventListener(
        "click",
        function () {

            atualizarMenuMobileProfessor(
                false
            );
        }
    );


    document
        .querySelectorAll(
            ".menu-item"
        )
        .forEach(
            function (item) {

                item.addEventListener(
                    "click",
                    function () {

                        if (
                            window.innerWidth <= 768
                        ) {

                            atualizarMenuMobileProfessor(
                                false
                            );
                        }
                    }
                );
            }
        );
}


/* Fecha o menu se voltar para desktop */

window.addEventListener(
    "resize",
    function () {

        if (
            window.innerWidth > 768
        ) {

            atualizarMenuMobileProfessor(
                false
            );
        }
    }
);


// =========================
// INICIALIZAÇÃO
// =========================

carregarDados();
const token = localStorage.getItem("token");
const perfil = localStorage.getItem("perfil");

const menuItens = document.querySelectorAll(".menu-item");
const secoes = document.querySelectorAll(".secao");

const mediaGeralElemento = document.getElementById("mediaGeral");
const situacaoElemento = document.getElementById("situacao");
const totalFaltasElemento = document.getElementById("totalFaltas");
const mensagemResumoElemento = document.getElementById("mensagemResumo");

const listaNotasElemento = document.getElementById("listaNotas");
const listaFaltasElemento = document.getElementById("listaFaltas");

const botaoSair = document.getElementById("botaoSair");

const formAssistente = document.getElementById("formAssistente");
const perguntaAssistente = document.getElementById("perguntaAssistente");
const chat = document.getElementById("chat");

// =========================
// PROTEGER PÁGINA
// =========================

if (!token || perfil !== "ALUNO") {

    localStorage.clear();

    window.location.href = "/login.html";
}

// =========================
// MENU
// =========================

menuItens.forEach(item => {

    item.addEventListener("click", () => {

        const secaoAlvo =
            item.getAttribute("data-secao");

        menuItens.forEach(menu => {
            menu.classList.remove("ativo");
        });

        secoes.forEach(secao => {
            secao.classList.remove("ativa");
        });

        item.classList.add("ativo");

        document
            .getElementById(secaoAlvo)
            .classList.add("ativa");
    });
});

// =========================
// FETCH AUTENTICADO
// =========================

async function requisicaoAutenticada(url, opcoes = {}) {

    const configuracao = {
        ...opcoes,

        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`,
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
// CARREGAR RESUMO
// =========================

async function carregarResumo() {

    try {

        const response =
            await requisicaoAutenticada(
                "/notas/meu-resumo"
            );

        if (!response) {
            return;
        }

        if (!response.ok) {

            mensagemResumoElemento.textContent =
                "Não foi possível carregar o resumo acadêmico.";

            return;
        }

        const resumos =
            await response.json();

        montarResumo(resumos);
        montarTabelaNotas(resumos);
        montarTabelaFaltas(resumos);

    } catch (erro) {

        console.error(
            "Erro ao carregar resumo:",
            erro
        );

        mensagemResumoElemento.textContent =
            "Erro ao conectar com o servidor.";
    }
}

// =========================
// MONTAR CARDS
// =========================

function montarResumo(resumos) {

    if (!resumos ||
        resumos.length === 0) {

        mediaGeralElemento.textContent = "--";
        situacaoElemento.textContent = "Sem dados";
        totalFaltasElemento.textContent = "0";

        mensagemResumoElemento.textContent =
            "Nenhuma informação acadêmica disponível.";

        return;
    }

    let somaMedias = 0;
    let quantidadeMedias = 0;
    let totalFaltas = 0;

    let possuiReprovacao = false;
    let possuiAndamento = false;

    resumos.forEach(resumo => {

        if (resumo.media !== null &&
            resumo.media !== undefined) {

            somaMedias +=
                Number(resumo.media);

            quantidadeMedias++;
        }

        totalFaltas +=
            Number(resumo.faltas || 0);

        if (resumo.situacao &&
            resumo.situacao.includes("REPROVADO")) {

            possuiReprovacao = true;
        }

        if (resumo.situacao ===
            "EM ANDAMENTO") {

            possuiAndamento = true;
        }
    });

    if (quantidadeMedias > 0) {

        const mediaGeral =
            somaMedias /
            quantidadeMedias;

        mediaGeralElemento.textContent =
            mediaGeral.toFixed(2);

    } else {

        mediaGeralElemento.textContent =
            "--";
    }

    totalFaltasElemento.textContent =
        totalFaltas;

    if (possuiReprovacao) {

        situacaoElemento.textContent =
            "Atenção";

        situacaoElemento.className =
            "status-reprovado";

        mensagemResumoElemento.textContent =
            "Há pelo menos uma disciplina que precisa de atenção. Consulte suas notas e frequências.";

    } else if (possuiAndamento) {

        situacaoElemento.textContent =
            "Em andamento";

        situacaoElemento.className =
            "status-andamento";

        mensagemResumoElemento.textContent =
            "Seu semestre ainda está em andamento. Continue acompanhando suas notas e frequência.";

    } else {

        situacaoElemento.textContent =
            "Regular";

        situacaoElemento.className =
            "status-aprovado";

        mensagemResumoElemento.textContent =
            "Seu desempenho acadêmico está regular nas disciplinas com avaliações concluídas.";
    }
}

// =========================
// TABELA DE NOTAS
// =========================

function montarTabelaNotas(resumos) {

    if (!resumos ||
        resumos.length === 0) {

        listaNotasElemento.textContent =
            "Nenhuma nota encontrada.";

        return;
    }

    let html = `
        <div class="tabela-container">
            <table>
                <thead>
                    <tr>
                        <th>Disciplina</th>
                        <th>AVC</th>
                        <th>AVG</th>
                        <th>AVI</th>
                        <th>Média</th>
                        <th>Situação</th>
                    </tr>
                </thead>

                <tbody>
    `;

    resumos.forEach(resumo => {

        html += `
            <tr>
                <td>${resumo.disciplina}</td>

                <td>
                    ${formatarNota(resumo.avc)}
                </td>

                <td>
                    ${formatarNota(resumo.avg)}
                </td>

                <td>
                    ${formatarNota(resumo.avi)}
                </td>

                <td>
                    ${formatarNota(resumo.media)}
                </td>

               <td class="${classeSituacao(resumo.situacaoNota)}">
                ${resumo.situacaoNota}
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
// TABELA DE FALTAS
// =========================

function montarTabelaFaltas(resumos) {

    if (!resumos ||
        resumos.length === 0) {

        listaFaltasElemento.textContent =
            "Nenhuma informação de frequência encontrada.";

        return;
    }

    let html = `
        <div class="tabela-container">
            <table>
                <thead>
                    <tr>
                        <th>Disciplina</th>
                        <th>Faltas</th>
                        <th>Frequência</th>
                        <th>Situação</th>
                    </tr>
                </thead>

                <tbody>
    `;

    resumos.forEach(resumo => {

        html += `
            <tr>
                <td>${resumo.disciplina}</td>

                <td>
                    ${resumo.faltas}
                </td>

                <td>
                    ${Number(resumo.frequencia).toFixed(2)}%
                </td>

                <td class="${classeSituacao(resumo.situacaoFrequencia)}">
                    ${resumo.situacaoFrequencia}
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
// ASSISTENTE IA
// =========================

formAssistente.addEventListener(
    "submit",
    async function (event) {

        event.preventDefault();

        const pergunta =
            perguntaAssistente
                .value
                .trim();

        if (!pergunta) {
            return;
        }

        adicionarMensagemChat(
            pergunta,
            "aluno"
        );

        perguntaAssistente.value =
            "";

        const mensagemCarregando =
            adicionarMensagemChat(
                "Pensando...",
                "assistente"
            );

        try {

            const response =
                await requisicaoAutenticada(
                    "/assistente/perguntar",
                    {
                        method: "POST",

                        body: JSON.stringify({
                            pergunta: pergunta
                        })
                    }
                );

            if (!response) {
                return;
            }

            const dados =
                await response.json();

            mensagemCarregando.remove();

            if (!response.ok) {

                adicionarMensagemChat(
                    dados.mensagem ||
                    "Não foi possível consultar o assistente.",
                    "assistente"
                );

                return;
            }

            adicionarMensagemChat(
                dados.resposta,
                "assistente"
            );

        } catch (erro) {

            console.error(
                "Erro no assistente:",
                erro
            );

            mensagemCarregando.remove();

            adicionarMensagemChat(
                "Não foi possível conectar ao assistente.",
                "assistente"
            );
        }
    }
);

// =========================
// CHAT
// =========================

function adicionarMensagemChat(
    texto,
    tipo) {

    const mensagem =
        document.createElement("div");

    mensagem.classList.add(
        "mensagem",
        tipo
    );

    mensagem.textContent =
        texto;

    chat.appendChild(
        mensagem
    );

    chat.scrollTop =
        chat.scrollHeight;

    return mensagem;
}

// =========================
// AUXILIARES
// =========================

function formatarNota(valor) {

    if (valor === null ||
        valor === undefined) {

        return "--";
    }

    return Number(valor)
        .toFixed(2);
}

function classeSituacao(situacao) {

    if (!situacao) {
        return "";
    }

    if (
        situacao.includes("REPROVADO")
    ) {

        return "status-reprovado";
    }

    if (
        situacao === "EM ANDAMENTO"
    ) {

        return "status-andamento";
    }

    if (
        situacao === "APROVADO" ||
        situacao === "FREQUÊNCIA REGULAR"
    ) {

        return "status-aprovado";
    }

    return "";
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

// =========================
// MENU MOBILE
// =========================

const botaoMenuMobile =
    document.getElementById("botaoMenuMobile");

const sidebarAluno =
    document.getElementById("sidebarAluno");

const overlayMenu =
    document.getElementById("overlayMenu");


function atualizarMenuMobile(menuAberto) {

    sidebarAluno.classList.toggle(
        "aberta",
        menuAberto
    );

    overlayMenu.classList.toggle(
        "ativo",
        menuAberto
    );

    botaoMenuMobile.textContent =
        menuAberto ? "✕" : "☰";

    botaoMenuMobile.setAttribute(
        "aria-expanded",
        menuAberto
    );

    botaoMenuMobile.setAttribute(
        "aria-label",
        menuAberto
            ? "Fechar menu"
            : "Abrir menu"
    );
}


if (
    botaoMenuMobile &&
    sidebarAluno &&
    overlayMenu
) {

    botaoMenuMobile.addEventListener(
        "click",
        function () {

            const menuAberto =
                !sidebarAluno.classList.contains(
                    "aberta"
                );

            atualizarMenuMobile(
                menuAberto
            );
        }
    );


    overlayMenu.addEventListener(
        "click",
        function () {

            atualizarMenuMobile(false);
        }
    );
}

document
    .querySelectorAll(".menu-item")
    .forEach(function (item) {

        item.addEventListener(
            "click",
            function () {

                if (
                    window.innerWidth <= 768 &&
                    sidebarAluno.classList.contains("aberta")
                ) {
                    atualizarMenuMobile(false);
                }
            }
        );
    });

// =========================
// INICIALIZAÇÃO
// =========================

carregarResumo();
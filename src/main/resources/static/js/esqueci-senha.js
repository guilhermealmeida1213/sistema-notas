const form = document.getElementById("recuperarForm");
const email = document.getElementById("email");
const mensagem = document.getElementById("mensagem");
const botao = document.getElementById("botaoEnviar");

form.addEventListener("submit", async event => {
    event.preventDefault();
    mensagem.className = "mensagem-erro";
    mensagem.textContent = "";
    botao.disabled = true;
    botao.textContent = "Enviando...";

    try {
        const response = await fetch("/senha/esqueci", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({email: email.value.trim()})
        });
        const dados = await response.json();
        if (!response.ok) throw new Error(dados.mensagem || "Não foi possível processar a solicitação.");
        mensagem.className = "mensagem-sucesso";
        mensagem.textContent = dados.mensagem;
        form.reset();
    } catch (erro) {
        mensagem.className = "mensagem-erro";
        mensagem.textContent = erro.message || "Não foi possível conectar ao servidor.";
    } finally {
        botao.disabled = false;
        botao.textContent = "Enviar link";
    }
});

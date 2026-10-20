const form = document.getElementById("redefinirForm");
const novaSenha = document.getElementById("novaSenha");
const confirmarSenha = document.getElementById("confirmarSenha");
const mensagem = document.getElementById("mensagem");
const botao = document.getElementById("botaoSalvar");
const token = new URLSearchParams(window.location.search).get("token");

if (!token) {
    mensagem.textContent = "Link de redefinição inválido ou incompleto.";
    botao.disabled = true;
}

form.addEventListener("submit", async event => {
    event.preventDefault();
    mensagem.className = "mensagem-erro";
    mensagem.textContent = "";

    if (novaSenha.value !== confirmarSenha.value) {
        mensagem.textContent = "As senhas não coincidem.";
        return;
    }

    botao.disabled = true;
    botao.textContent = "Salvando...";

    try {
        const response = await fetch("/senha/redefinir", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({token, novaSenha: novaSenha.value})
        });
        const dados = await response.json();
        if (!response.ok) throw new Error(dados.mensagem || "Não foi possível redefinir a senha.");
        mensagem.className = "mensagem-sucesso";
        mensagem.textContent = "Senha redefinida. Redirecionando para o login...";
        form.reset();
        setTimeout(() => window.location.href = "/login.html", 1800);
    } catch (erro) {
        mensagem.className = "mensagem-erro";
        mensagem.textContent = erro.message || "Não foi possível conectar ao servidor.";
        botao.disabled = false;
        botao.textContent = "Salvar nova senha";
    }
});

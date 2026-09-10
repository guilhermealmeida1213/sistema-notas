const loginForm =
    document.getElementById("loginForm");

const emailInput =
    document.getElementById("email");

const senhaInput =
    document.getElementById("senha");

const mensagemErro =
    document.getElementById("mensagemErro");

const botaoEntrar =
    document.getElementById("botaoEntrar");


loginForm.addEventListener(
    "submit",
    async function (event) {

        event.preventDefault();

        mensagemErro.textContent = "";

        botaoEntrar.disabled = true;
        botaoEntrar.textContent = "Entrando...";


        const dadosLogin = {
            email: emailInput.value.trim(),
            senha: senhaInput.value
        };


        try {

            const response =
                await fetch(
                    "/usuarios/login",
                    {
                        method: "POST",

                        headers: {
                            "Content-Type":
                                "application/json"
                        },

                        body:
                            JSON.stringify(
                                dadosLogin
                            )
                    }
                );


            const dados =
                await response.json();


            if (!response.ok) {

                mensagemErro.textContent =
                    dados.mensagem ||
                    "E-mail ou senha inválidos";

                return;
            }


            // =========================
            // SALVA A SESSÃO
            // =========================

            localStorage.setItem(
                "token",
                dados.token
            );

            localStorage.setItem(
                "perfil",
                dados.perfil
            );

            // =========================
            // REDIRECIONAMENTO
            // =========================

            switch (dados.perfil) {

                case "ALUNO":

                    window.location.href =
                        "/aluno.html";

                    break;


                case "PROFESSOR":

                    window.location.href =
                        "/professor.html";

                    break;


                case "ADMINISTRADOR":

                    window.location.href =
                        "/admin.html";

                    break;


                default:

                    console.error(
                        "Perfil desconhecido:",
                        dados.perfil
                    );

                    localStorage.removeItem(
                        "token"
                    );

                    localStorage.removeItem(
                        "perfil"
                    );

                    mensagemErro.textContent =
                        "Perfil de usuário inválido.";
            }


        } catch (erro) {

            console.error(
                "Erro ao realizar login:",
                erro
            );

            mensagemErro.textContent =
                "Não foi possível conectar ao servidor.";

        } finally {

            botaoEntrar.disabled = false;
            botaoEntrar.textContent = "Entrar";
        }
    }
);
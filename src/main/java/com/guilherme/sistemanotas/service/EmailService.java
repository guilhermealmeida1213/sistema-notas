package com.guilherme.sistemanotas.service;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final Resend resend;
    private final String remetente;

    public EmailService(
            @Value("${resend.api.key}") String apiKey,
            @Value("${app.mail.from}") String remetente) {

        this.resend = new Resend(apiKey);
        this.remetente = remetente;
    }

    public void enviarRecuperacaoSenha(String destino, String nome, String link) {
        String conteudo = """
                Olá, %s.

                Recebemos uma solicitação para redefinir sua senha.

                Acesse o link abaixo em até 30 minutos:
                %s

                Se você não solicitou essa alteração, ignore este e-mail.
                """.formatted(nome, link);

        CreateEmailOptions email = CreateEmailOptions.builder()
                .from(remetente)
                .to(destino)
                .subject("Redefinição de senha - Sistema Acadêmico")
                .text(conteudo)
                .build();

        try {
            resend.emails().send(email);
        } catch (ResendException e) {
            throw new IllegalStateException("Não foi possível enviar o e-mail de recuperação de senha.", e);
        }
    }
}
package com.guilherme.sistemanotas.dto;

public class AssistentePerguntaDTO {

    private String pergunta;

    public AssistentePerguntaDTO() {
    }

    public AssistentePerguntaDTO(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }
}
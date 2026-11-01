package com.guilherme.sistemanotas.dto;

public class AtualizarProfessorTurmaDisciplinaDTO {

    private Integer idProfessor;
    private String motivo;

    public AtualizarProfessorTurmaDisciplinaDTO() {
    }

    public Integer getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(Integer idProfessor) {
        this.idProfessor = idProfessor;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
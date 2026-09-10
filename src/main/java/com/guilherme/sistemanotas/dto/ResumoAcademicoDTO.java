package com.guilherme.sistemanotas.dto;

import java.math.BigDecimal;

public class ResumoAcademicoDTO {

    private String aluno;
    private String turma;
    private BigDecimal mediaAtual;
    private Integer totalFaltas;

    public ResumoAcademicoDTO() {
    }

    public ResumoAcademicoDTO(
            String aluno,
            String turma,
            BigDecimal mediaAtual,
            Integer totalFaltas) {

        this.aluno = aluno;
        this.turma = turma;
        this.mediaAtual = mediaAtual;
        this.totalFaltas = totalFaltas;
    }

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public BigDecimal getMediaAtual() {
        return mediaAtual;
    }

    public void setMediaAtual(BigDecimal mediaAtual) {
        this.mediaAtual = mediaAtual;
    }

    public Integer getTotalFaltas() {
        return totalFaltas;
    }

    public void setTotalFaltas(Integer totalFaltas) {
        this.totalFaltas = totalFaltas;
    }
}
package com.guilherme.sistemanotas.dto;

import java.math.BigDecimal;

public class ResumoDisciplinaDTO {

    private String disciplina;

    private BigDecimal avc;

    private BigDecimal avg;

    private BigDecimal avi;

    private BigDecimal media;

    private Integer faltas;

    private BigDecimal frequencia;

    // Situação geral da disciplina
    private String situacao;

    // Situação considerando somente as notas
    private String situacaoNota;

    // Situação considerando somente a frequência
    private String situacaoFrequencia;


    public ResumoDisciplinaDTO() {
    }


    // =========================
    // CONSTRUTOR ANTIGO
    // Mantido para não quebrar
    // código existente
    // =========================

    public ResumoDisciplinaDTO(
            String disciplina,
            BigDecimal avc,
            BigDecimal avg,
            BigDecimal avi,
            BigDecimal media,
            Integer faltas,
            BigDecimal frequencia,
            String situacao) {

        this.disciplina =
                disciplina;

        this.avc =
                avc;

        this.avg =
                avg;

        this.avi =
                avi;

        this.media =
                media;

        this.faltas =
                faltas;

        this.frequencia =
                frequencia;

        this.situacao =
                situacao;
    }


    // =========================
    // NOVO CONSTRUTOR
    // =========================

    public ResumoDisciplinaDTO(
            String disciplina,
            BigDecimal avc,
            BigDecimal avg,
            BigDecimal avi,
            BigDecimal media,
            Integer faltas,
            BigDecimal frequencia,
            String situacao,
            String situacaoNota,
            String situacaoFrequencia) {

        this.disciplina =
                disciplina;

        this.avc =
                avc;

        this.avg =
                avg;

        this.avi =
                avi;

        this.media =
                media;

        this.faltas =
                faltas;

        this.frequencia =
                frequencia;

        this.situacao =
                situacao;

        this.situacaoNota =
                situacaoNota;

        this.situacaoFrequencia =
                situacaoFrequencia;
    }


    // =========================
    // GETTERS / SETTERS
    // =========================

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(
            String disciplina) {

        this.disciplina =
                disciplina;
    }


    public BigDecimal getAvc() {
        return avc;
    }

    public void setAvc(
            BigDecimal avc) {

        this.avc =
                avc;
    }


    public BigDecimal getAvg() {
        return avg;
    }

    public void setAvg(
            BigDecimal avg) {

        this.avg =
                avg;
    }


    public BigDecimal getAvi() {
        return avi;
    }

    public void setAvi(
            BigDecimal avi) {

        this.avi =
                avi;
    }


    public BigDecimal getMedia() {
        return media;
    }

    public void setMedia(
            BigDecimal media) {

        this.media =
                media;
    }


    public Integer getFaltas() {
        return faltas;
    }

    public void setFaltas(
            Integer faltas) {

        this.faltas =
                faltas;
    }


    public BigDecimal getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(
            BigDecimal frequencia) {

        this.frequencia =
                frequencia;
    }


    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(
            String situacao) {

        this.situacao =
                situacao;
    }


    public String getSituacaoNota() {
        return situacaoNota;
    }

    public void setSituacaoNota(
            String situacaoNota) {

        this.situacaoNota =
                situacaoNota;
    }


    public String getSituacaoFrequencia() {
        return situacaoFrequencia;
    }

    public void setSituacaoFrequencia(
            String situacaoFrequencia) {

        this.situacaoFrequencia =
                situacaoFrequencia;
    }
}
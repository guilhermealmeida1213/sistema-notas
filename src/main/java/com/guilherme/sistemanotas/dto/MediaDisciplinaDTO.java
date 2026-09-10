package com.guilherme.sistemanotas.dto;

import java.math.BigDecimal;

public class MediaDisciplinaDTO {

    private String disciplina;
    private BigDecimal avc;
    private BigDecimal avg;
    private BigDecimal avi;
    private BigDecimal media;
    private String situacao;

    public MediaDisciplinaDTO() {
    }

    public MediaDisciplinaDTO(
            String disciplina,
            BigDecimal avc,
            BigDecimal avg,
            BigDecimal avi,
            BigDecimal media,
            String situacao) {

        this.disciplina = disciplina;
        this.avc = avc;
        this.avg = avg;
        this.avi = avi;
        this.media = media;
        this.situacao = situacao;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public BigDecimal getAvc() {
        return avc;
    }

    public void setAvc(BigDecimal avc) {
        this.avc = avc;
    }

    public BigDecimal getAvg() {
        return avg;
    }

    public void setAvg(BigDecimal avg) {
        this.avg = avg;
    }

    public BigDecimal getAvi() {
        return avi;
    }

    public void setAvi(BigDecimal avi) {
        this.avi = avi;
    }

    public BigDecimal getMedia() {
        return media;
    }

    public void setMedia(BigDecimal media) {
        this.media = media;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
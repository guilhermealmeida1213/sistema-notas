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
    private String situacao;
    private String situacaoNota;
    private String situacaoFrequencia;
    private String turma;
    private Integer anoTurma;
    private Integer semestreTurma;
    private String statusMatricula;
    private Integer idMatricula;
    private Integer aulasPrevistas;

    public ResumoDisciplinaDTO() { }

    public ResumoDisciplinaDTO(String disciplina, BigDecimal avc, BigDecimal avg, BigDecimal avi, BigDecimal media, Integer faltas, BigDecimal frequencia, String situacao) {
        this(disciplina, avc, avg, avi, media, faltas, frequencia, situacao, null, null, null, null, null, null, null);
    }

    public ResumoDisciplinaDTO(String disciplina, BigDecimal avc, BigDecimal avg, BigDecimal avi, BigDecimal media, Integer faltas, BigDecimal frequencia, String situacao, String situacaoNota, String situacaoFrequencia) {
        this(disciplina, avc, avg, avi, media, faltas, frequencia, situacao, situacaoNota, situacaoFrequencia, null, null, null, null, null);
    }

    public ResumoDisciplinaDTO(String disciplina, BigDecimal avc, BigDecimal avg, BigDecimal avi, BigDecimal media, Integer faltas, BigDecimal frequencia, String situacao, String situacaoNota, String situacaoFrequencia, String turma, Integer anoTurma, Integer semestreTurma, String statusMatricula, Integer idMatricula) {
        this.disciplina = disciplina; this.avc = avc; this.avg = avg; this.avi = avi; this.media = media; this.faltas = faltas; this.frequencia = frequencia; this.situacao = situacao; this.situacaoNota = situacaoNota; this.situacaoFrequencia = situacaoFrequencia; this.turma = turma; this.anoTurma = anoTurma; this.semestreTurma = semestreTurma; this.statusMatricula = statusMatricula; this.idMatricula = idMatricula;
    }

    public String getDisciplina() { return disciplina; } public void setDisciplina(String v) { disciplina = v; }
    public BigDecimal getAvc() { return avc; } public void setAvc(BigDecimal v) { avc = v; }
    public BigDecimal getAvg() { return avg; } public void setAvg(BigDecimal v) { avg = v; }
    public BigDecimal getAvi() { return avi; } public void setAvi(BigDecimal v) { avi = v; }
    public BigDecimal getMedia() { return media; } public void setMedia(BigDecimal v) { media = v; }
    public Integer getFaltas() { return faltas; } public void setFaltas(Integer v) { faltas = v; }
    public BigDecimal getFrequencia() { return frequencia; } public void setFrequencia(BigDecimal v) { frequencia = v; }
    public String getSituacao() { return situacao; } public void setSituacao(String v) { situacao = v; }
    public String getSituacaoNota() { return situacaoNota; } public void setSituacaoNota(String v) { situacaoNota = v; }
    public String getSituacaoFrequencia() { return situacaoFrequencia; } public void setSituacaoFrequencia(String v) { situacaoFrequencia = v; }
    public String getTurma() { return turma; } public void setTurma(String v) { turma = v; }
    public Integer getAnoTurma() { return anoTurma; } public void setAnoTurma(Integer v) { anoTurma = v; }
    public Integer getSemestreTurma() { return semestreTurma; } public void setSemestreTurma(Integer v) { semestreTurma = v; }
    public String getStatusMatricula() { return statusMatricula; } public void setStatusMatricula(String v) { statusMatricula = v; }
    public Integer getIdMatricula() { return idMatricula; } public void setIdMatricula(Integer v) { idMatricula = v; }
    public Integer getAulasPrevistas() { return aulasPrevistas; } public void setAulasPrevistas(Integer v) { aulasPrevistas = v; }
}

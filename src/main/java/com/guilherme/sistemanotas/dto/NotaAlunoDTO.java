package com.guilherme.sistemanotas.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class NotaAlunoDTO {

    private Integer idNota;
    private String disciplina;
    private String tipoAvaliacao;
    private BigDecimal valor;
    private LocalDate dataAvaliacao;

    public NotaAlunoDTO() {
    }

    public NotaAlunoDTO(
            Integer idNota,
            String disciplina,
            String tipoAvaliacao,
            BigDecimal valor,
            LocalDate dataAvaliacao) {

        this.idNota = idNota;
        this.disciplina = disciplina;
        this.tipoAvaliacao = tipoAvaliacao;
        this.valor = valor;
        this.dataAvaliacao = dataAvaliacao;
    }

    public Integer getIdNota() {
        return idNota;
    }

    public void setIdNota(Integer idNota) {
        this.idNota = idNota;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getTipoAvaliacao() {
        return tipoAvaliacao;
    }

    public void setTipoAvaliacao(String tipoAvaliacao) {
        this.tipoAvaliacao = tipoAvaliacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(LocalDate dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }
}
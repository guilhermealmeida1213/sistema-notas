package com.guilherme.sistemanotas.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LancamentoAviDTO {

    private Integer idMatricula;
    private BigDecimal valor;
    private LocalDate dataAvaliacao;

    public LancamentoAviDTO() {
    }

    public LancamentoAviDTO(
            Integer idMatricula,
            BigDecimal valor,
            LocalDate dataAvaliacao) {

        this.idMatricula = idMatricula;
        this.valor = valor;
        this.dataAvaliacao = dataAvaliacao;
    }

    public Integer getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
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
package com.guilherme.sistemanotas.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AtualizarNotaDTO {

    private BigDecimal valor;
    private LocalDate dataAvaliacao;

    public AtualizarNotaDTO() {
    }

    public AtualizarNotaDTO(
            BigDecimal valor,
            LocalDate dataAvaliacao) {

        this.valor = valor;
        this.dataAvaliacao = dataAvaliacao;
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
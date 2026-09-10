package com.guilherme.sistemanotas.dto;

import java.time.LocalDate;

public class AtualizarFaltaDTO {

    private LocalDate dataFalta;
    private Integer quantidade;

    public AtualizarFaltaDTO() {
    }

    public AtualizarFaltaDTO(
            LocalDate dataFalta,
            Integer quantidade) {

        this.dataFalta = dataFalta;
        this.quantidade = quantidade;
    }

    public LocalDate getDataFalta() {
        return dataFalta;
    }

    public void setDataFalta(LocalDate dataFalta) {
        this.dataFalta = dataFalta;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
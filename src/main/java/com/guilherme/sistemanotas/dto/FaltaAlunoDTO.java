package com.guilherme.sistemanotas.dto;

import java.time.LocalDate;

public class FaltaAlunoDTO {

    private Integer idFalta;
    private String disciplina;
    private LocalDate dataFalta;
    private Integer quantidade;

    public FaltaAlunoDTO() {
    }

    public FaltaAlunoDTO(
            Integer idFalta,
            String disciplina,
            LocalDate dataFalta,
            Integer quantidade) {

        this.idFalta = idFalta;
        this.disciplina = disciplina;
        this.dataFalta = dataFalta;
        this.quantidade = quantidade;
    }

    public Integer getIdFalta() {
        return idFalta;
    }

    public void setIdFalta(Integer idFalta) {
        this.idFalta = idFalta;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
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
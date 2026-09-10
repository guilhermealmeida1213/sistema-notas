package com.guilherme.sistemanotas.dto;

import java.time.LocalDate;

public class AtualizarMatriculaDTO {

    private LocalDate dataMatricula;
    private String status;
    private Integer semestreAluno;

    public AtualizarMatriculaDTO() {
    }

    public LocalDate getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDate dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSemestreAluno() {
        return semestreAluno;
    }

    public void setSemestreAluno(Integer semestreAluno) {
        this.semestreAluno = semestreAluno;
    }
}
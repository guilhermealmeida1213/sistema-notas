package com.guilherme.sistemanotas.dto;

import java.time.LocalDate;

public class MatriculaAlunoDTO {

    private Integer idMatricula;
    private String turma;
    private Integer semestre;
    private Integer semestreAluno;
    private Integer ano;
    private LocalDate dataMatricula;
    private String status;

    public MatriculaAlunoDTO() {
    }

    public MatriculaAlunoDTO(
            Integer idMatricula,
            String turma,
            Integer semestre,
            Integer semestreAluno,
            Integer ano,
            LocalDate dataMatricula,
            String status) {

        this.idMatricula = idMatricula;
        this.turma = turma;
        this.semestre = semestre;
        this.semestreAluno = semestreAluno;
        this.ano = ano;
        this.dataMatricula = dataMatricula;
        this.status = status;
    }

    public Integer getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public Integer getSemestreAluno() {
        return semestreAluno;
    }

    public void setSemestreAluno(Integer semestreAluno) {
        this.semestreAluno = semestreAluno;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
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
}
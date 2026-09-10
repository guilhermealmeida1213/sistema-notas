package com.guilherme.sistemanotas.dto;

import java.time.LocalDate;

public class FaltaAdminDTO {

    private Integer idFalta;
    private Integer idMatricula;
    private String aluno;
    private String matriculaAluno;
    private String turma;
    private String disciplina;
    private String professor;
    private LocalDate dataFalta;
    private Integer quantidade;

    public FaltaAdminDTO(
            Integer idFalta,
            Integer idMatricula,
            String aluno,
            String matriculaAluno,
            String turma,
            String disciplina,
            String professor,
            LocalDate dataFalta,
            Integer quantidade) {

        this.idFalta = idFalta;
        this.idMatricula = idMatricula;
        this.aluno = aluno;
        this.matriculaAluno = matriculaAluno;
        this.turma = turma;
        this.disciplina = disciplina;
        this.professor = professor;
        this.dataFalta = dataFalta;
        this.quantidade = quantidade;
    }

    public Integer getIdFalta() {
        return idFalta;
    }

    public void setIdFalta(Integer idFalta) {
        this.idFalta = idFalta;
    }

    public Integer getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public String getMatriculaAluno() {
        return matriculaAluno;
    }

    public void setMatriculaAluno(String matriculaAluno) {
        this.matriculaAluno = matriculaAluno;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
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
package com.guilherme.sistemanotas.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class NotaAdminDTO {

    private Integer idNota;
    private Integer idMatricula;
    private String aluno;
    private String matriculaAluno;
    private String turma;
    private String disciplina;
    private String professor;
    private String tipoAvaliacao;
    private BigDecimal valor;
    private LocalDate dataAvaliacao;

    public NotaAdminDTO(
            Integer idNota,
            Integer idMatricula,
            String aluno,
            String matriculaAluno,
            String turma,
            String disciplina,
            String professor,
            String tipoAvaliacao,
            BigDecimal valor,
            LocalDate dataAvaliacao) {

        this.idNota = idNota;
        this.idMatricula = idMatricula;
        this.aluno = aluno;
        this.matriculaAluno = matriculaAluno;
        this.turma = turma;
        this.disciplina = disciplina;
        this.professor = professor;
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
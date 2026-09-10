package com.guilherme.sistemanotas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "matricula")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matricula")
    private Integer idMatricula;

    @ManyToOne
    @JoinColumn(
            name = "aluno_id_aluno",
            nullable = false
    )
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(
            name = "turma_id_turma",
            nullable = false
    )
    private Turma turma;

    @Column(name = "data_matricula", nullable = false)
    private LocalDate dataMatricula;

    @Column(nullable = false)
    private String status;

    @NotNull(message = "Semestre do aluno é obrigatório")
    @Min(value = 1, message = "Semestre do aluno deve ser entre 1 e 4")
    @Max(value = 4, message = "Semestre do aluno deve ser entre 1 e 4")
    @Column(name = "semestre_aluno", nullable = false)
    private Integer semestreAluno;

    public Matricula() {
    }

    public Integer getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
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
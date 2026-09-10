package com.guilherme.sistemanotas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "turma_has_disciplina")
public class TurmaDisciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_turma_disciplina")
    private Integer idTurmaDisciplina;

    @ManyToOne
    @JoinColumn(
            name = "turma_id_turma",
            nullable = false
    )
    private Turma turma;

    @ManyToOne
    @JoinColumn(
            name = "disciplina_id_disciplina",
            nullable = false
    )
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(
            name = "professor_id_professor",
            nullable = false
    )
    private Professor professor;

    public TurmaDisciplina() {
    }

    public Integer getIdTurmaDisciplina() {
        return idTurmaDisciplina;
    }

    public void setIdTurmaDisciplina(Integer idTurmaDisciplina) {
        this.idTurmaDisciplina = idTurmaDisciplina;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
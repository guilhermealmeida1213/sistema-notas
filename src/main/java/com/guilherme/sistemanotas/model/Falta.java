package com.guilherme.sistemanotas.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "falta")
public class Falta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_falta")
    private Integer idFalta;

    @Column(name = "data_falta", nullable = false)
    private LocalDate dataFalta;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(
            name = "matricula_id_matricula",
            nullable = false
    )
    private Matricula matricula;

    @ManyToOne
    @JoinColumn(
            name = "turma_has_disciplina_id_turma_disciplina",
            nullable = false
    )
    private TurmaDisciplina turmaDisciplina;

    public Falta() {
    }

    public Integer getIdFalta() {
        return idFalta;
    }

    public void setIdFalta(Integer idFalta) {
        this.idFalta = idFalta;
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

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public TurmaDisciplina getTurmaDisciplina() {
        return turmaDisciplina;
    }

    public void setTurmaDisciplina(TurmaDisciplina turmaDisciplina) {
        this.turmaDisciplina = turmaDisciplina;
    }
}
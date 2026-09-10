package com.guilherme.sistemanotas.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "nota")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nota")
    private Integer idNota;

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

    @Enumerated(EnumType.STRING)
    @Column(
            name = "tipo_avaliacao",
            nullable = false,
            length = 10
    )
    private TipoAvaliacao tipoAvaliacao;

    @Column(
            name = "valor",
            nullable = false,
            precision = 4,
            scale = 2
    )
    private BigDecimal valor;

    @Column(
            name = "data_avaliacao",
            nullable = false
    )
    private LocalDate dataAvaliacao;

    public Nota() {
    }

    public Integer getIdNota() {
        return idNota;
    }

    public void setIdNota(Integer idNota) {
        this.idNota = idNota;
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

    public TipoAvaliacao getTipoAvaliacao() {
        return tipoAvaliacao;
    }

    public void setTipoAvaliacao(TipoAvaliacao tipoAvaliacao) {
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
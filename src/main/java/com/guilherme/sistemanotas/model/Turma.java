package com.guilherme.sistemanotas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "turma")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_turma")
    private Integer idTurma;

    @NotBlank(message = "Nome da turma é obrigatório")
    @Size(max = 50, message = "Nome deve ter no máximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String nome;

    @NotNull(message = "Semestre é obrigatório")
    @Min(value = 1, message = "Semestre deve ser 1 ou 2")
    @Max(value = 2, message = "Semestre deve ser 1 ou 2")
    @Column(nullable = false)
    private Integer semestre;

    @NotNull(message = "Ano é obrigatório")
    @Min(value = 2000, message = "Ano inválido")
    @Column(nullable = false)
    private Integer ano;

    public Turma() {
    }

    public Integer getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Integer idTurma) {
        this.idTurma = idTurma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }
}
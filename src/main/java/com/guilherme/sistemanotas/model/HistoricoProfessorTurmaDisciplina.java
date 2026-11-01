package com.guilherme.sistemanotas.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "historico_professor_turma_disciplina")
public class HistoricoProfessorTurmaDisciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historico_professor")
    private Integer idHistoricoProfessor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "turma_has_disciplina_id_turma_disciplina", nullable = false)
    private TurmaDisciplina turmaDisciplina;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "professor_id_professor", nullable = false)
    private Professor professor;

    // Pode ser nula somente para vínculos antigos cuja data inicial não era registrada.
    @Column(name = "data_inicio")
    private LocalDateTime dataInicio;

    // Nula significa que esta é a atribuição atual.
    @Column(name = "data_fim")
    private LocalDateTime dataFim;

    @Column(name = "registrado_em", nullable = false)
    private LocalDateTime registradoEm;

    @Column(name = "registrado_por_email", length = 100)
    private String registradoPorEmail;

    @Column(name = "motivo", length = 500)
    private String motivo;

    public HistoricoProfessorTurmaDisciplina() {
    }

    public Integer getIdHistoricoProfessor() {
        return idHistoricoProfessor;
    }

    public void setIdHistoricoProfessor(Integer idHistoricoProfessor) {
        this.idHistoricoProfessor = idHistoricoProfessor;
    }

    public TurmaDisciplina getTurmaDisciplina() {
        return turmaDisciplina;
    }

    public void setTurmaDisciplina(TurmaDisciplina turmaDisciplina) {
        this.turmaDisciplina = turmaDisciplina;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public LocalDateTime getRegistradoEm() {
        return registradoEm;
    }

    public void setRegistradoEm(LocalDateTime registradoEm) {
        this.registradoEm = registradoEm;
    }

    public String getRegistradoPorEmail() {
        return registradoPorEmail;
    }

    public void setRegistradoPorEmail(String registradoPorEmail) {
        this.registradoPorEmail = registradoPorEmail;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}

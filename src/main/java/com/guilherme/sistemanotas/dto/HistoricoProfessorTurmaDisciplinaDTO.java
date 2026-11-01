package com.guilherme.sistemanotas.dto;

import java.time.LocalDateTime;

public class HistoricoProfessorTurmaDisciplinaDTO {

    private Integer idHistoricoProfessor;
    private Integer idProfessor;
    private String professor;
    private String registroProfessor;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private LocalDateTime registradoEm;
    private String registradoPor;
    private String motivo;
    private boolean inicioNaoRegistrado;

    public HistoricoProfessorTurmaDisciplinaDTO(
            Integer idHistoricoProfessor,
            Integer idProfessor,
            String professor,
            String registroProfessor,
            LocalDateTime dataInicio,
            LocalDateTime dataFim,
            LocalDateTime registradoEm,
            String registradoPor,
            String motivo,
            boolean inicioNaoRegistrado) {
        this.idHistoricoProfessor = idHistoricoProfessor;
        this.idProfessor = idProfessor;
        this.professor = professor;
        this.registroProfessor = registroProfessor;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.registradoEm = registradoEm;
        this.registradoPor = registradoPor;
        this.motivo = motivo;
        this.inicioNaoRegistrado = inicioNaoRegistrado;
    }

    public Integer getIdHistoricoProfessor() { return idHistoricoProfessor; }
    public Integer getIdProfessor() { return idProfessor; }
    public String getProfessor() { return professor; }
    public String getRegistroProfessor() { return registroProfessor; }
    public LocalDateTime getDataInicio() { return dataInicio; }
    public LocalDateTime getDataFim() { return dataFim; }
    public LocalDateTime getRegistradoEm() { return registradoEm; }
    public String getRegistradoPor() { return registradoPor; }
    public String getMotivo() { return motivo; }
    public boolean isInicioNaoRegistrado() { return inicioNaoRegistrado; }
}

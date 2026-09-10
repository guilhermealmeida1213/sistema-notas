package com.guilherme.sistemanotas.service;

import com.guilherme.sistemanotas.model.Auditoria;
import com.guilherme.sistemanotas.repository.AuditoriaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditoriaService {

    private final AuditoriaRepository auditoriaRepository;

    public AuditoriaService(
            AuditoriaRepository auditoriaRepository) {

        this.auditoriaRepository = auditoriaRepository;
    }

    // =========================
    // REGISTRAR AÇÃO
    // =========================

    public Auditoria registrar(
            String usuario,
            String acao,
            String descricao) {

        Auditoria auditoria =
                new Auditoria(
                        usuario,
                        acao,
                        descricao,
                        LocalDateTime.now()
                );

        return auditoriaRepository.save(auditoria);
    }

    // =========================
    // LISTAR AUDITORIAS
    // =========================

    public List<Auditoria> listar() {

        return auditoriaRepository
                .findAllByOrderByDataHoraDesc();
    }
}
package com.guilherme.sistemanotas.repository;

import com.guilherme.sistemanotas.model.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditoriaRepository
        extends JpaRepository<Auditoria, Integer> {

    List<Auditoria> findAllByOrderByDataHoraDesc();
}
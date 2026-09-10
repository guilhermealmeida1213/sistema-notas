package com.guilherme.sistemanotas.repository;

import com.guilherme.sistemanotas.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TurmaRepository
        extends JpaRepository<Turma, Integer> {

    Optional<Turma> findByNome(String nome);

    List<Turma> findAllByOrderByNomeAsc();
}
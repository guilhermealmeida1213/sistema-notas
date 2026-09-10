package com.guilherme.sistemanotas.repository;

import com.guilherme.sistemanotas.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DisciplinaRepository
        extends JpaRepository<Disciplina, Integer> {

    Optional<Disciplina> findByNome(String nome);

    List<Disciplina> findAllByOrderByNomeAsc();
}
package com.guilherme.sistemanotas.repository;

import com.guilherme.sistemanotas.model.TurmaDisciplina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TurmaDisciplinaRepository
        extends JpaRepository<TurmaDisciplina, Integer> {

    boolean existsByTurma_IdTurmaAndDisciplina_IdDisciplina(
            Integer idTurma,
            Integer idDisciplina
    );

    Optional<TurmaDisciplina> findByTurma_IdTurmaAndDisciplina_IdDisciplina(
            Integer idTurma,
            Integer idDisciplina
    );

    List<TurmaDisciplina> findByTurma_IdTurma(
            Integer idTurma
    );

    List<TurmaDisciplina> findByProfessor_IdProfessor(
            Integer idProfessor
    );

    boolean existsByProfessor_IdProfessor(
            Integer idProfessor
    );

    boolean existsByTurma_IdTurma(
            Integer idTurma
    );

    boolean existsByDisciplina_IdDisciplina(
            Integer idDisciplina
    );

    boolean existsByTurma_IdTurmaAndProfessor_IdProfessor(
            Integer idTurma,
            Integer idProfessor
    );
}
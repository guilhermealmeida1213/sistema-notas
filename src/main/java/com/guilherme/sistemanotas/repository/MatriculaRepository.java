package com.guilherme.sistemanotas.repository;

import com.guilherme.sistemanotas.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import com.guilherme.sistemanotas.model.Professor;
import com.guilherme.sistemanotas.repository.ProfessorRepository;
import com.guilherme.sistemanotas.repository.TurmaDisciplinaRepository;

import java.util.List;

public interface MatriculaRepository
        extends JpaRepository<Matricula, Integer> {

    boolean existsByAluno_IdAlunoAndTurma_IdTurma(
            Integer idAluno,
            Integer idTurma
    );

    List<Matricula> findByAluno_IdAluno(
            Integer idAluno
    );

    // Verifica se uma turma possui alunos matriculados
    boolean existsByTurma_IdTurma(
            Integer idTurma
    );

    List<Matricula> findByTurma_IdTurma(
            Integer idTurma
    );

}
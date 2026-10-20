package com.guilherme.sistemanotas.repository;

import com.guilherme.sistemanotas.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {

    boolean existsByAluno_IdAlunoAndTurma_IdTurma(Integer idAluno, Integer idTurma);

    List<Matricula> findByAluno_IdAluno(Integer idAluno);

    boolean existsByTurma_IdTurma(Integer idTurma);

    List<Matricula> findByTurma_IdTurma(Integer idTurma);

    List<Matricula> findByTurma_IdTurmaAndStatusIgnoreCase(Integer idTurma, String status);
}

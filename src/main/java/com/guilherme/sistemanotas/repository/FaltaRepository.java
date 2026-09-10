package com.guilherme.sistemanotas.repository;

import com.guilherme.sistemanotas.model.Falta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FaltaRepository
        extends JpaRepository<Falta, Integer> {

    // Faltas de uma matrícula específica
    List<Falta> findByMatricula_IdMatricula(
            Integer idMatricula
    );

    // Faltas das turmas/disciplinas de um professor
    List<Falta> findByTurmaDisciplina_Professor_IdProfessor(
            Integer idProfessor
    );

    // Faltas de um aluno em uma disciplina específica
    List<Falta> findByMatricula_IdMatriculaAndTurmaDisciplina_IdTurmaDisciplina(
            Integer idMatricula,
            Integer idTurmaDisciplina
    );
}
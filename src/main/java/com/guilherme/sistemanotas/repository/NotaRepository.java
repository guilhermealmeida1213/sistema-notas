package com.guilherme.sistemanotas.repository;

import com.guilherme.sistemanotas.model.Nota;
import com.guilherme.sistemanotas.model.TipoAvaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaRepository
        extends JpaRepository<Nota, Integer> {

    boolean existsByMatricula_IdMatriculaAndTurmaDisciplina_IdTurmaDisciplinaAndTipoAvaliacao(
            Integer idMatricula,
            Integer idTurmaDisciplina,
            TipoAvaliacao tipoAvaliacao
    );

    List<Nota> findByMatricula_IdMatricula(
            Integer idMatricula
    );

    List<Nota> findByMatricula_IdMatriculaAndTurmaDisciplina_IdTurmaDisciplina(
            Integer idMatricula,
            Integer idTurmaDisciplina
    );

    List<Nota> findByTurmaDisciplina_Professor_IdProfessor(
            Integer idProfessor
    );

    List<Nota> findByMatricula_IdMatriculaAndTipoAvaliacao(
            Integer idMatricula,
            TipoAvaliacao tipoAvaliacao
    );
}
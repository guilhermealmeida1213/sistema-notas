package com.guilherme.sistemanotas.repository;

import com.guilherme.sistemanotas.model.HistoricoProfessorTurmaDisciplina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HistoricoProfessorTurmaDisciplinaRepository
        extends JpaRepository<HistoricoProfessorTurmaDisciplina, Integer> {

    List<HistoricoProfessorTurmaDisciplina>
    findByTurmaDisciplina_IdTurmaDisciplinaOrderByRegistradoEmDesc(
            Integer idTurmaDisciplina
    );

    Optional<HistoricoProfessorTurmaDisciplina>
    findFirstByTurmaDisciplina_IdTurmaDisciplinaAndDataFimIsNullOrderByRegistradoEmDesc(
            Integer idTurmaDisciplina
    );

    boolean existsByTurmaDisciplina_IdTurmaDisciplinaAndDataFimIsNotNull(Integer idTurmaDisciplina);

    void deleteByTurmaDisciplina_IdTurmaDisciplina(Integer idTurmaDisciplina);

    boolean existsByProfessor_IdProfessor(Integer idProfessor);
}

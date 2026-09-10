package com.guilherme.sistemanotas.repository;

import com.guilherme.sistemanotas.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository
        extends JpaRepository<Professor, Integer> {

    Optional<Professor> findByUsuario_Email(
            String email
    );

    Optional<Professor> findByRegistro(
            String registro
    );

    boolean existsByRegistro(
            String registro
    );

    boolean existsByUsuario_IdUsuario(
            Integer idUsuario
    );

    boolean existsByRegistroAndIdProfessorNot(
            String registro,
            Integer idProfessor
    );

    List<Professor> findAllByOrderByUsuario_NomeAsc();
}
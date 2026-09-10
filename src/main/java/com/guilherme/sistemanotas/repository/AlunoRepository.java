package com.guilherme.sistemanotas.repository;

import com.guilherme.sistemanotas.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository
        extends JpaRepository<Aluno, Integer> {

    Optional<Aluno> findByUsuario_Email(String email);

    Optional<Aluno> findByMatricula(String matricula);

    List<Aluno> findAllByOrderByUsuario_NomeAsc();
}
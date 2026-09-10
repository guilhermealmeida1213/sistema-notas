package com.guilherme.sistemanotas.repository;

import com.guilherme.sistemanotas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdUsuarioNot(String email, Integer idUsuario);

    Optional<Usuario> findByEmail(String email);

    List<Usuario> findAllByOrderByNomeAsc();
}
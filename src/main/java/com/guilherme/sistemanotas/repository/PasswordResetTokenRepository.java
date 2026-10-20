package com.guilherme.sistemanotas.repository;

import com.guilherme.sistemanotas.model.PasswordResetToken;
import com.guilherme.sistemanotas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByTokenHash(String tokenHash);
    Optional<PasswordResetToken> findByUsuario(Usuario usuario);
}

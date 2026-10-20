package com.guilherme.sistemanotas.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "password_reset_token")
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_password_reset_token")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Usuario usuario;

    @Column(name = "token_hash", nullable = false, unique = true, length = 64)
    private String tokenHash;

    @Column(name = "expira_em", nullable = false)
    private LocalDateTime expiraEm;

    @Column(name = "usado_em")
    private LocalDateTime usadoEm;

    public PasswordResetToken() {}

    public PasswordResetToken(Usuario usuario, String tokenHash, LocalDateTime expiraEm) {
        this.usuario = usuario;
        this.tokenHash = tokenHash;
        this.expiraEm = expiraEm;
    }

    public Long getId() { return id; }
    public Usuario getUsuario() { return usuario; }
    public String getTokenHash() { return tokenHash; }
    public LocalDateTime getExpiraEm() { return expiraEm; }
    public LocalDateTime getUsadoEm() { return usadoEm; }
    public void setTokenHash(String tokenHash) { this.tokenHash = tokenHash; }
    public void setExpiraEm(LocalDateTime expiraEm) { this.expiraEm = expiraEm; }
    public void setUsadoEm(LocalDateTime usadoEm) { this.usadoEm = usadoEm; }
}

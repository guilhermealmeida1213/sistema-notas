package com.guilherme.sistemanotas.service;

import com.guilherme.sistemanotas.model.PasswordResetToken;
import com.guilherme.sistemanotas.model.Usuario;
import com.guilherme.sistemanotas.repository.PasswordResetTokenRepository;
import com.guilherme.sistemanotas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HexFormat;
import java.util.Optional;

@Service
public class PasswordResetService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final AuditoriaService auditoriaService;
    private final String baseUrl;
    private final SecureRandom secureRandom = new SecureRandom();

    public PasswordResetService(
            UsuarioRepository usuarioRepository,
            PasswordResetTokenRepository tokenRepository,
            PasswordEncoder passwordEncoder,
            EmailService emailService,
            AuditoriaService auditoriaService,
            @Value("${app.base-url}") String baseUrl) {
        this.usuarioRepository = usuarioRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.auditoriaService = auditoriaService;
        this.baseUrl = baseUrl;
    }

    @Transactional
    public void solicitar(String email) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email.trim().toLowerCase());
        if (usuarioOptional.isEmpty() || !Boolean.TRUE.equals(usuarioOptional.get().getAtivo())) return;

        Usuario usuario = usuarioOptional.get();
        byte[] bytes = new byte[32];
        secureRandom.nextBytes(bytes);
        String token = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
        String tokenHash = hash(token);
        LocalDateTime expiraEm = LocalDateTime.now().plusMinutes(30);

        PasswordResetToken registro = tokenRepository.findByUsuario(usuario)
                .orElseGet(() -> new PasswordResetToken(usuario, tokenHash, expiraEm));
        registro.setTokenHash(tokenHash);
        registro.setExpiraEm(expiraEm);
        registro.setUsadoEm(null);
        tokenRepository.save(registro);

        String link = baseUrl.replaceAll("/$", "") + "/redefinir-senha.html?token=" + token;
        emailService.enviarRecuperacaoSenha(usuario.getEmail(), usuario.getNome(), link);
        auditoriaService.registrar(usuario.getEmail(), "SOLICITACAO_REDEFINICAO_SENHA", "Solicitação de redefinição de senha enviada por e-mail");
    }

    @Transactional
    public void redefinir(String token, String novaSenha) {
        PasswordResetToken registro = tokenRepository.findByTokenHash(hash(token))
                .orElseThrow(() -> new IllegalArgumentException("Link de redefinição inválido ou expirado."));

        if (registro.getUsadoEm() != null || registro.getExpiraEm().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Link de redefinição inválido ou expirado.");
        }

        Usuario usuario = registro.getUsuario();
        if (!Boolean.TRUE.equals(usuario.getAtivo())) throw new IllegalArgumentException("Link de redefinição inválido ou expirado.");

        usuario.setSenha(passwordEncoder.encode(novaSenha));
        usuarioRepository.save(usuario);
        registro.setUsadoEm(LocalDateTime.now());
        tokenRepository.save(registro);
        auditoriaService.registrar(usuario.getEmail(), "REDEFINICAO_SENHA", "Senha redefinida com sucesso");
    }

    private String hash(String token) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return HexFormat.of().formatHex(digest.digest(token.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 indisponível", e);
        }
    }
}

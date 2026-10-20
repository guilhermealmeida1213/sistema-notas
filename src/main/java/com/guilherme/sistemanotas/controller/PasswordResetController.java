package com.guilherme.sistemanotas.controller;

import com.guilherme.sistemanotas.dto.RedefinirSenhaDTO;
import com.guilherme.sistemanotas.dto.SolicitarRedefinicaoSenhaDTO;
import com.guilherme.sistemanotas.service.PasswordResetService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/senha")
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    public PasswordResetController(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    @PostMapping("/esqueci")
    public ResponseEntity<Map<String, String>> solicitar(@Valid @RequestBody SolicitarRedefinicaoSenhaDTO dto) {
        passwordResetService.solicitar(dto.email());
        return ResponseEntity.ok(Map.of("mensagem", "Se o e-mail estiver cadastrado e ativo, você receberá um link para redefinir a senha."));
    }

    @PostMapping("/redefinir")
    public ResponseEntity<Map<String, String>> redefinir(@Valid @RequestBody RedefinirSenhaDTO dto) {
        passwordResetService.redefinir(dto.token(), dto.novaSenha());
        return ResponseEntity.ok(Map.of("mensagem", "Senha redefinida com sucesso."));
    }
}

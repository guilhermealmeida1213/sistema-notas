package com.guilherme.sistemanotas.controller;

import com.guilherme.sistemanotas.dto.AtualizarUsuarioDTO;
import com.guilherme.sistemanotas.dto.LoginRequest;
import com.guilherme.sistemanotas.dto.LoginResponse;
import com.guilherme.sistemanotas.model.Usuario;
import com.guilherme.sistemanotas.service.JwtService;
import com.guilherme.sistemanotas.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final JwtService jwtService;

    public UsuarioController(
            UsuarioService usuarioService,
            JwtService jwtService) {

        this.usuarioService = usuarioService;
        this.jwtService = jwtService;
    }

    @PostMapping
    public Usuario criar(
            @Valid
            @RequestBody Usuario usuario,
            Authentication authentication) {

        return usuarioService.salvar(
                usuario,
                authentication.getName()
        );
    }

    @GetMapping
    public List<Usuario> listar() {

        return usuarioService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(
            @PathVariable Integer id) {

        Optional<Usuario> usuario =
                usuarioService.buscarPorId(
                        id
                );

        if (usuario.isPresent()) {

            return ResponseEntity.ok(
                    usuario.get()
            );
        }

        return ResponseEntity
                .notFound()
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(
            @PathVariable Integer id,
            @Valid
            @RequestBody AtualizarUsuarioDTO dados,
            Authentication authentication) {

        Optional<Usuario> usuarioAtualizado =
                usuarioService.atualizar(
                        id,
                        dados,
                        authentication.getName()
                );

        if (usuarioAtualizado.isPresent()) {

            return ResponseEntity.ok(
                    usuarioAtualizado.get()
            );
        }

        return ResponseEntity
                .notFound()
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Integer id,
            Authentication authentication) {

        if (usuarioService.excluir(
                id,
                authentication.getName()
        )) {

            return ResponseEntity
                    .noContent()
                    .build();
        }

        return ResponseEntity
                .notFound()
                .build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid
            @RequestBody LoginRequest loginRequest) {

        Usuario usuario =
                usuarioService
                        .autenticar(
                                loginRequest.getEmail(),
                                loginRequest.getSenha()
                        )
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.UNAUTHORIZED,
                                        "E-mail ou senha inválidos"
                                )
                        );

        String token =
                jwtService.gerarToken(
                        usuario
                );

        LoginResponse resposta =
                new LoginResponse(
                        token,
                        usuario.getIdUsuario(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getPerfil()
                );

        return ResponseEntity.ok(
                resposta
        );
    }
}
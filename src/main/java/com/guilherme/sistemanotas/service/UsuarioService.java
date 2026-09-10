package com.guilherme.sistemanotas.service;

import com.guilherme.sistemanotas.dto.AtualizarUsuarioDTO;
import com.guilherme.sistemanotas.model.Usuario;
import com.guilherme.sistemanotas.repository.AlunoRepository;
import com.guilherme.sistemanotas.repository.ProfessorRepository;
import com.guilherme.sistemanotas.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuditoriaService auditoriaService;
    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            AuditoriaService auditoriaService,
            AlunoRepository alunoRepository,
            ProfessorRepository professorRepository) {

        this.usuarioRepository =
                usuarioRepository;

        this.passwordEncoder =
                passwordEncoder;

        this.auditoriaService =
                auditoriaService;

        this.alunoRepository =
                alunoRepository;

        this.professorRepository =
                professorRepository;
    }

    // =========================
    // CADASTRAR USUÁRIO
    // =========================

    @Transactional
    public Usuario salvar(
            Usuario usuario,
            String emailAdministrador) {

        if (usuarioRepository.existsByEmail(
                usuario.getEmail()
        )) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "E-mail já cadastrado"
            );
        }

        usuario.setSenha(
                passwordEncoder.encode(
                        usuario.getSenha()
                )
        );

        Usuario usuarioSalvo =
                usuarioRepository.save(
                        usuario
                );

        auditoriaService.registrar(
                emailAdministrador,
                "CRIACAO_USUARIO",
                "Usuário "
                        + usuarioSalvo.getEmail()
                        + " criado com perfil "
                        + usuarioSalvo.getPerfil()
        );

        return usuarioSalvo;
    }

    // =========================
    // LISTAR USUÁRIOS
    // =========================

    public List<Usuario> listar() {

        return usuarioRepository
                .findAllByOrderByNomeAsc();
    }

    // =========================
    // BUSCAR POR ID
    // =========================

    public Optional<Usuario> buscarPorId(
            Integer id) {

        return usuarioRepository
                .findById(id);
    }

    // =========================
    // ATUALIZAR USUÁRIO
    // =========================

    @Transactional
    public Optional<Usuario> atualizar(
            Integer id,
            AtualizarUsuarioDTO dados,
            String emailAdministrador) {

        Optional<Usuario> usuarioExistente =
                usuarioRepository.findById(
                        id
                );

        if (usuarioExistente.isEmpty()) {

            return Optional.empty();
        }

        Usuario usuario =
                usuarioExistente.get();

        if (dados.getPerfil() == null) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Perfil é obrigatório"
            );
        }

        // =========================
        // BLOQUEAR ALTERAÇÃO DO
        // PRÓPRIO PERFIL
        // =========================

        boolean proprioUsuario =
                usuario.getEmail()
                        .equalsIgnoreCase(
                                emailAdministrador
                        );

        boolean perfilFoiAlterado =
                usuario.getPerfil() !=
                        dados.getPerfil();

        if (proprioUsuario &&
                perfilFoiAlterado) {

            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Você não pode alterar o perfil do próprio usuário"
            );
        }

        // =========================
        // E-MAIL DUPLICADO
        // =========================

        if (usuarioRepository
                .existsByEmailAndIdUsuarioNot(
                        dados.getEmail(),
                        id
                )) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "E-mail já cadastrado"
            );
        }

        // =========================
        // BLOQUEAR ALTERAÇÃO DE
        // PERFIL COM VÍNCULO
        // =========================

        if (perfilFoiAlterado) {

            boolean possuiAlunoVinculado =
                    alunoRepository
                            .findByUsuario_Email(
                                    usuario.getEmail()
                            )
                            .isPresent();

            boolean possuiProfessorVinculado =
                    professorRepository
                            .findByUsuario_Email(
                                    usuario.getEmail()
                            )
                            .isPresent();

            if (possuiAlunoVinculado ||
                    possuiProfessorVinculado) {

                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Não é possível alterar o perfil de um usuário que já possui vínculo acadêmico"
                );
            }
        }

        String nomeAnterior =
                usuario.getNome();

        String emailAnterior =
                usuario.getEmail();

        String perfilAnterior =
                usuario.getPerfil().name();

        // =========================
        // DADOS BÁSICOS
        // =========================

        usuario.setNome(
                dados.getNome()
        );

        usuario.setEmail(
                dados.getEmail()
        );

        usuario.setPerfil(
                dados.getPerfil()
        );

        // =========================
        // SENHA OPCIONAL
        // =========================

        boolean senhaAlterada =
                dados.getSenha() != null &&
                        !dados.getSenha().isBlank();

        if (senhaAlterada) {

            usuario.setSenha(
                    passwordEncoder.encode(
                            dados.getSenha()
                    )
            );
        }

        Usuario usuarioAtualizado =
                usuarioRepository.save(
                        usuario
                );

        String descricao =
                "Usuário ID "
                        + id
                        + " alterado. "
                        + "Nome: "
                        + nomeAnterior
                        + " -> "
                        + usuarioAtualizado.getNome()
                        + "; E-mail: "
                        + emailAnterior
                        + " -> "
                        + usuarioAtualizado.getEmail()
                        + "; Perfil: "
                        + perfilAnterior
                        + " -> "
                        + usuarioAtualizado.getPerfil();

        if (senhaAlterada) {

            descricao +=
                    "; Senha redefinida";
        }

        auditoriaService.registrar(
                emailAdministrador,
                "ALTERACAO_USUARIO",
                descricao
        );

        return Optional.of(
                usuarioAtualizado
        );
    }

    // =========================
    // EXCLUIR USUÁRIO
    // =========================

    @Transactional
    public boolean excluir(
            Integer id,
            String emailAdministrador) {

        Usuario usuario =
                usuarioRepository
                        .findById(id)
                        .orElse(null);

        if (usuario == null) {

            return false;
        }

        // =========================
        // BLOQUEAR AUTOEXCLUSÃO
        // =========================

        if (usuario.getEmail()
                .equalsIgnoreCase(
                        emailAdministrador
                )) {

            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Você não pode excluir o próprio usuário"
            );
        }

        boolean possuiAlunoVinculado =
                alunoRepository
                        .findByUsuario_Email(
                                usuario.getEmail()
                        )
                        .isPresent();

        boolean possuiProfessorVinculado =
                professorRepository
                        .findByUsuario_Email(
                                usuario.getEmail()
                        )
                        .isPresent();

        if (possuiAlunoVinculado ||
                possuiProfessorVinculado) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Não é possível excluir um usuário que possui vínculo acadêmico"
            );
        }

        String emailUsuarioExcluido =
                usuario.getEmail();

        String nomeUsuarioExcluido =
                usuario.getNome();

        String perfilUsuarioExcluido =
                usuario.getPerfil().name();

        usuarioRepository.delete(
                usuario
        );

        auditoriaService.registrar(
                emailAdministrador,
                "EXCLUSAO_USUARIO",
                "Usuário "
                        + nomeUsuarioExcluido
                        + " ("
                        + emailUsuarioExcluido
                        + "), perfil "
                        + perfilUsuarioExcluido
                        + ", foi excluído"
        );

        return true;
    }

    // =========================
    // AUTENTICAR
    // =========================

    public Optional<Usuario> autenticar(
            String email,
            String senha) {

        Optional<Usuario> usuarioEncontrado =
                usuarioRepository
                        .findByEmail(
                                email
                        );

        if (usuarioEncontrado.isEmpty()) {

            return Optional.empty();
        }

        Usuario usuario =
                usuarioEncontrado.get();

        if (passwordEncoder.matches(
                senha,
                usuario.getSenha()
        )) {

            return Optional.of(
                    usuario
            );
        }

        return Optional.empty();
    }
}
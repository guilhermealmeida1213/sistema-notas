package com.guilherme.sistemanotas.service;

import com.guilherme.sistemanotas.dto.AtualizarProfessorDTO;
import com.guilherme.sistemanotas.model.PerfilUsuario;
import com.guilherme.sistemanotas.model.Professor;
import com.guilherme.sistemanotas.model.Usuario;
import com.guilherme.sistemanotas.repository.ProfessorRepository;
import com.guilherme.sistemanotas.repository.TurmaDisciplinaRepository;
import com.guilherme.sistemanotas.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final UsuarioRepository usuarioRepository;
    private final TurmaDisciplinaRepository turmaDisciplinaRepository;
    private final AuditoriaService auditoriaService;

    public ProfessorService(
            ProfessorRepository professorRepository,
            UsuarioRepository usuarioRepository,
            TurmaDisciplinaRepository turmaDisciplinaRepository,
            AuditoriaService auditoriaService) {

        this.professorRepository =
                professorRepository;

        this.usuarioRepository =
                usuarioRepository;

        this.turmaDisciplinaRepository =
                turmaDisciplinaRepository;

        this.auditoriaService =
                auditoriaService;
    }

    // =========================
    // CADASTRAR PROFESSOR
    // =========================

    @Transactional
    public Professor salvar(
            Professor professor,
            String emailAdministrador) {

        if (professor.getRegistro() == null ||
                professor.getRegistro().isBlank()) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Registro do professor é obrigatório"
            );
        }

        String registro =
                professor
                        .getRegistro()
                        .trim();

        professor.setRegistro(
                registro
        );

        if (professor.getUsuario() == null ||
                professor.getUsuario().getIdUsuario() == null) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Usuário é obrigatório"
            );
        }

        Integer idUsuario =
                professor
                        .getUsuario()
                        .getIdUsuario();

        Usuario usuario =
                usuarioRepository
                        .findById(idUsuario)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Usuário não encontrado"
                                )
                        );

        if (usuario.getPerfil() !=
                PerfilUsuario.PROFESSOR) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O usuário informado não possui perfil PROFESSOR"
            );
        }

        if (professorRepository
                .existsByRegistro(
                        registro
                )) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Registro de professor já cadastrado"
            );
        }

        if (professorRepository
                .existsByUsuario_IdUsuario(
                        idUsuario
                )) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Este usuário já possui cadastro de professor"
            );
        }

        professor.setUsuario(
                usuario
        );

        Professor professorSalvo =
                professorRepository.save(
                        professor
                );

        auditoriaService.registrar(
                emailAdministrador,
                "CRIACAO_PROFESSOR",
                "Professor "
                        + usuario.getNome()
                        + " ("
                        + usuario.getEmail()
                        + ") cadastrado com registro "
                        + professorSalvo.getRegistro()
        );

        return professorSalvo;
    }

    // =========================
    // LISTAR PROFESSORES
    // =========================

    public List<Professor> listar() {

        return professorRepository
                .findAllByOrderByUsuario_NomeAsc();
    }

    // =========================
    // BUSCAR POR ID
    // =========================

    public Optional<Professor> buscarPorId(
            Integer id) {

        return professorRepository
                .findById(id);
    }

    // =========================
    // ATUALIZAR PROFESSOR
    // =========================

    @Transactional
    public Professor atualizar(
            Integer id,
            AtualizarProfessorDTO dados,
            String emailAdministrador) {

        Professor professor =
                professorRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Professor não encontrado"
                                )
                        );

        String novoRegistro =
                dados
                        .getRegistro()
                        .trim();

        if (professorRepository
                .existsByRegistroAndIdProfessorNot(
                        novoRegistro,
                        id
                )) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Registro de professor já cadastrado"
            );
        }

        String registroAnterior =
                professor.getRegistro();

        professor.setRegistro(
                novoRegistro
        );

        Professor professorAtualizado =
                professorRepository.save(
                        professor
                );

        auditoriaService.registrar(
                emailAdministrador,
                "ALTERACAO_PROFESSOR",
                "Professor "
                        + professorAtualizado
                        .getUsuario()
                        .getNome()
                        + " teve o registro alterado de "
                        + registroAnterior
                        + " para "
                        + professorAtualizado.getRegistro()
        );

        return professorAtualizado;
    }

    // =========================
    // EXCLUIR PROFESSOR
    // =========================

    @Transactional
    public void excluir(
            Integer id,
            String emailAdministrador) {

        Professor professor =
                professorRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Professor não encontrado"
                                )
                        );

        boolean possuiVinculos =
                turmaDisciplinaRepository
                        .existsByProfessor_IdProfessor(
                                id
                        );

        if (possuiVinculos) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Professor possui disciplinas vinculadas e não pode ser excluído"
            );
        }

        String nomeProfessor =
                professor
                        .getUsuario()
                        .getNome();

        String emailProfessor =
                professor
                        .getUsuario()
                        .getEmail();

        String registroProfessor =
                professor.getRegistro();

        professorRepository.delete(
                professor
        );

        auditoriaService.registrar(
                emailAdministrador,
                "EXCLUSAO_PROFESSOR",
                "Professor "
                        + nomeProfessor
                        + " ("
                        + emailProfessor
                        + "), registro "
                        + registroProfessor
                        + ", foi excluído"
        );
    }
}
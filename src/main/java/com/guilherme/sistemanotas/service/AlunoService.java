package com.guilherme.sistemanotas.service;

import com.guilherme.sistemanotas.model.Aluno;
import com.guilherme.sistemanotas.model.PerfilUsuario;
import com.guilherme.sistemanotas.model.Usuario;
import com.guilherme.sistemanotas.repository.AlunoRepository;
import com.guilherme.sistemanotas.repository.MatriculaRepository;
import com.guilherme.sistemanotas.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final UsuarioRepository usuarioRepository;
    private final MatriculaRepository matriculaRepository;

    public AlunoService(
            AlunoRepository alunoRepository,
            UsuarioRepository usuarioRepository,
            MatriculaRepository matriculaRepository) {

        this.alunoRepository = alunoRepository;
        this.usuarioRepository = usuarioRepository;
        this.matriculaRepository = matriculaRepository;
    }

    @Transactional
    public Aluno salvar(Aluno aluno) {

        if (aluno == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Dados do aluno são obrigatórios"
            );
        }

        if (aluno.getMatricula() == null ||
                aluno.getMatricula().isBlank()) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Matrícula é obrigatória"
            );
        }

        String matricula =
                aluno.getMatricula().trim();

        aluno.setMatricula(matricula);

        if (aluno.getUsuario() == null ||
                aluno.getUsuario().getIdUsuario() == null) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Usuário é obrigatório"
            );
        }

        Integer idUsuario =
                aluno.getUsuario().getIdUsuario();

        Usuario usuario =
                usuarioRepository
                        .findById(idUsuario)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Usuário não encontrado"
                                )
                        );

        if (usuario.getPerfil() != PerfilUsuario.ALUNO) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O usuário informado não possui perfil ALUNO"
            );
        }

        if (alunoRepository
                .findByMatricula(matricula)
                .isPresent()) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Matrícula de aluno já cadastrada"
            );
        }

        if (alunoRepository
                .findByUsuario_Email(usuario.getEmail())
                .isPresent()) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Este usuário já possui cadastro de aluno"
            );
        }

        aluno.setUsuario(usuario);

        return alunoRepository.save(aluno);
    }

    public List<Aluno> listar() {
        return alunoRepository
                .findAllByOrderByUsuario_NomeAsc();
    }

    public Optional<Aluno> buscarPorId(Integer id) {
        return alunoRepository.findById(id);
    }

    @Transactional
    public void excluir(Integer id) {

        Aluno aluno =
                alunoRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Aluno não encontrado"
                                )
                        );

        if (!matriculaRepository
                .findByAluno_IdAluno(id)
                .isEmpty()) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Não é possível excluir o aluno porque ele possui matrícula vinculada"
            );
        }

        alunoRepository.delete(aluno);
    }
}
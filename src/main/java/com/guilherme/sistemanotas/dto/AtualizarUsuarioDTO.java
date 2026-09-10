package com.guilherme.sistemanotas.dto;

import com.guilherme.sistemanotas.model.PerfilUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AtualizarUsuarioDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(
            max = 100,
            message = "Nome deve ter no máximo 100 caracteres"
    )
    private String nome;

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    @Size(
            max = 100,
            message = "E-mail deve ter no máximo 100 caracteres"
    )
    private String email;

    @Size(
            min = 6,
            max = 255,
            message = "Senha deve ter no mínimo 6 caracteres"
    )
    private String senha;

    @NotNull(message = "Perfil é obrigatório")
    private PerfilUsuario perfil;

    public AtualizarUsuarioDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(
            String nome) {

        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(
            String email) {

        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(
            String senha) {

        this.senha = senha;
    }

    public PerfilUsuario getPerfil() {
        return perfil;
    }

    public void setPerfil(
            PerfilUsuario perfil) {

        this.perfil = perfil;
    }
}
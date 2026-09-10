package com.guilherme.sistemanotas.dto;

import com.guilherme.sistemanotas.model.PerfilUsuario;

public class LoginResponse {

    private String token;
    private Integer idUsuario;
    private String nome;
    private String email;
    private PerfilUsuario perfil;

    public LoginResponse(
            String token,
            Integer idUsuario,
            String nome,
            String email,
            PerfilUsuario perfil) {

        this.token = token;
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.perfil = perfil;
    }

    public String getToken() {
        return token;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public PerfilUsuario getPerfil() {
        return perfil;
    }
}
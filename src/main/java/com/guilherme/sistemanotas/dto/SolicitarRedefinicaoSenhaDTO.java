package com.guilherme.sistemanotas.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SolicitarRedefinicaoSenhaDTO(
        @NotBlank @Email String email
) {}

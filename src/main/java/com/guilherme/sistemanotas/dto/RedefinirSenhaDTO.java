package com.guilherme.sistemanotas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RedefinirSenhaDTO(
        @NotBlank String token,
        @NotBlank @Size(min = 8, max = 72) String novaSenha
) {}

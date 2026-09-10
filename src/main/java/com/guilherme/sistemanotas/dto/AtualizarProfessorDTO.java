package com.guilherme.sistemanotas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AtualizarProfessorDTO {

    @NotBlank(message = "Registro é obrigatório")
    @Size(
            max = 20,
            message = "Registro deve ter no máximo 20 caracteres"
    )
    private String registro;

    public AtualizarProfessorDTO() {
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }
}
package com.guilherme.sistemanotas.controller;

import com.guilherme.sistemanotas.dto.AssistentePerguntaDTO;
import com.guilherme.sistemanotas.dto.AssistenteRespostaDTO;
import com.guilherme.sistemanotas.service.AssistenteService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assistente")
public class AssistenteController {

    private final AssistenteService assistenteService;

    public AssistenteController(
            AssistenteService assistenteService) {

        this.assistenteService = assistenteService;
    }

    @PostMapping("/perguntar")
    public AssistenteRespostaDTO perguntar(
            @RequestBody AssistentePerguntaDTO dados,
            Authentication authentication) {

        String email =
                authentication.getName();

        return assistenteService.responder(
                email,
                dados.getPergunta()
        );
    }
}
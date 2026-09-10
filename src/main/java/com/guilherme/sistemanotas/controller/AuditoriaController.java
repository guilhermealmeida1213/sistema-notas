package com.guilherme.sistemanotas.controller;

import com.guilherme.sistemanotas.model.Auditoria;
import com.guilherme.sistemanotas.service.AuditoriaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auditorias")
public class AuditoriaController {

    private final AuditoriaService auditoriaService;

    public AuditoriaController(
            AuditoriaService auditoriaService) {

        this.auditoriaService = auditoriaService;
    }

    // =========================
    // LISTAR AUDITORIAS
    // ADMIN
    // =========================

    @GetMapping
    public List<Auditoria> listar() {

        return auditoriaService.listar();
    }
}
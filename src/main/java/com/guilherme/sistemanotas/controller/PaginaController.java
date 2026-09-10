package com.guilherme.sistemanotas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaController {

    @GetMapping("/login")
    public String login() {
        return "redirect:/login.html";
    }
}
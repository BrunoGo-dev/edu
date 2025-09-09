package com.TpIntegrado.edu.web.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/registro")
    public String registro() {
        return "Registro básico exitoso";
    }

    @PostMapping("/login")
    public String login() {
        return "Login básico exitoso";
    }

}

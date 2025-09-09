package com.TpIntegrado.edu.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String home() {
        return "API Plataforma Académica funcionando correctamente!";
    }

    @GetMapping("/test")
    public String test() {
        return "Endpoint de prueba - OK";
    }
}

package com.ejemplo.prestamos.controller;

import com.ejemplo.prestamos.model.Herramienta;
import com.ejemplo.prestamos.service.HerramientaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/herramientas")
public class HerramientaController {

    private final HerramientaService service;

    public HerramientaController(HerramientaService service) {
        this.service = service;
    }

    @PostMapping
    public Herramienta crear(@Valid @RequestBody Herramienta herramienta) {
        return service.guardar(herramienta);
    }

    @GetMapping
    public List<Herramienta> listar() {
        return service.listar();
    }
}
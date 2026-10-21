package com.ejemplo.prestamos.controller;

import com.ejemplo.prestamos.dto.PrestamoRequest;
import com.ejemplo.prestamos.model.Prestamo;
import com.ejemplo.prestamos.service.PrestamoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService service;

    public PrestamoController(PrestamoService service) {
        this.service = service;
    }

    @PostMapping
    public Prestamo crear(@Valid @RequestBody PrestamoRequest request) {
        return service.registrar(request);
    }

    @GetMapping
    public List<Prestamo> listar() {
        return service.listar();
    }

    @GetMapping("/herramienta/{id}")
    public List<Prestamo> porHerramienta(@PathVariable Long id) {
        return service.porHerramienta(id);
    }
}
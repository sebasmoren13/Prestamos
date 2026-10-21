package com.ejemplo.prestamos.service;

import com.ejemplo.prestamos.model.Herramienta;
import com.ejemplo.prestamos.repository.HerramientaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class HerramientaService {

    private final HerramientaRepository repo;

    public HerramientaService(HerramientaRepository repo) {
        this.repo = repo;
    }

    public Herramienta guardar(Herramienta h) {
        if (h.getCantidad() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La cantidad no puede ser negativa");
        }
        h.setId(null);
        return repo.save(h);
    }

    public List<Herramienta> listar() {
        return repo.findAll();
    }
}
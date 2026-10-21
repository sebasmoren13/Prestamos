package com.ejemplo.prestamos.service;

import com.ejemplo.prestamos.dto.PrestamoRequest;
import com.ejemplo.prestamos.model.Herramienta;
import com.ejemplo.prestamos.model.Prestamo;
import com.ejemplo.prestamos.repository.HerramientaRepository;
import com.ejemplo.prestamos.repository.PrestamoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepo;
    private final HerramientaRepository herramientaRepo;

    public PrestamoService(PrestamoRepository prestamoRepo, HerramientaRepository herramientaRepo) {
        this.prestamoRepo = prestamoRepo;
        this.herramientaRepo = herramientaRepo;
    }

    @Transactional
    public Prestamo registrar(PrestamoRequest req) {
        Herramienta h = herramientaRepo.findById(req.herramientaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La herramienta no existe"));

        if (req.cantidad() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La cantidad debe ser mayor a cero");
        }
        if (req.cantidad() > h.getCantidad()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Unidades insuficientes. Disponibles: " + h.getCantidad());
        }

        h.setCantidad(h.getCantidad() - req.cantidad());
        herramientaRepo.save(h);

        Prestamo p = new Prestamo();
        p.setResponsable(req.responsable());
        p.setFechaPrestamo(req.fechaPrestamo());
        p.setCantidad(req.cantidad());
        p.setHerramienta(h);
        return prestamoRepo.save(p);
    }

    public List<Prestamo> listar() {
        return prestamoRepo.findAll();
    }

    public List<Prestamo> porHerramienta(Long id) {
        return prestamoRepo.findByHerramientaId(id);
    }
}
package com.ejemplo.prestamos.repository;

import com.ejemplo.prestamos.model.Herramienta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HerramientaRepository extends JpaRepository<Herramienta, Long> {
}
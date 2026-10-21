package com.ejemplo.prestamos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PrestamoRequest(
        @NotBlank String responsable,
        @NotNull LocalDate fechaPrestamo,
        @Min(1) int cantidad,
        @NotNull Long herramientaId
) {}
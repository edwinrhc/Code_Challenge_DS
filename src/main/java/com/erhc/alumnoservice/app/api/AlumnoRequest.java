package com.erhc.alumnoservice.app.api;

import com.erhc.alumnoservice.app.domain.AlumnoEstado;
import jakarta.validation.constraints.*;

public record AlumnoRequest(
        @NotNull(message = "El id es obligatorio")
        @Positive(message = "El id debe ser positivo")
        Long id,

        @NotBlank(message = "El nombre es obligatorio")
        @Size(max=100, message = "El nombre no debe superar 100 caracteres")
        String nombre,

        @NotBlank(message = "El apellido es obligatorio")
        @Size(max=100, message = "El apellido no debe superar 100 caracteres")
        String apellido,

        @NotNull(message = "El estado es obligatorio")
        AlumnoEstado estado,

        @NotNull(message = "La edad es obligatoria")
        @Min(value = 1, message = "La edad debe ser mayor a 0")
        @Max(value = 120, message = "La edad debe ser menor a 120")
        Integer edad
) {}

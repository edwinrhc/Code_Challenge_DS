package com.erhc.alumnoservice.app.api;

import com.erhc.alumnoservice.app.domain.AlumnoEstado;

public record AlumnoResponse(
        Long id,
        String nombre,
        String apellido,
        AlumnoEstado estado,
        Integer edad
) {}

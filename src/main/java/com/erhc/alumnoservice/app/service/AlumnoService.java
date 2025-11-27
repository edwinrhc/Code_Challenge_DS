package com.erhc.alumnoservice.app.service;

import com.erhc.alumnoservice.app.api.AlumnoRequest;
import com.erhc.alumnoservice.app.api.AlumnoResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlumnoService {

    Mono<Void> crearAlumno(AlumnoRequest request);
    Flux<AlumnoResponse> obtenerAlumnosActivos();


}

package com.erhc.alumnoservice.app.repository;

import com.erhc.alumnoservice.app.domain.Alumno;
import com.erhc.alumnoservice.app.domain.AlumnoEstado;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlumnoRepository extends R2dbcRepository<Alumno, Long> {

    Mono<Boolean> existsById(Long id);

    Flux<Alumno> findByEstado(AlumnoEstado estado);
}

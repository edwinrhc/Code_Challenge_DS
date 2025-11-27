package com.erhc.alumnoservice.app.api;

import com.erhc.alumnoservice.app.service.AlumnoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/alumnos")
@RequiredArgsConstructor
public class AlumnoController {

    private final AlumnoService alumnoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> crearAlumno(@Valid @RequestBody AlumnoRequest request ){
        return alumnoService.crearAlumno(request);
    }

    @GetMapping("/activos")
    public Flux<AlumnoResponse> listarActivos(){
        return alumnoService.obtenerAlumnosActivos();
    }
}

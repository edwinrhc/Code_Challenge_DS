package com.erhc.alumnoservice.app.service;

import com.erhc.alumnoservice.app.api.AlumnoRequest;
import com.erhc.alumnoservice.app.api.AlumnoResponse;
import com.erhc.alumnoservice.app.domain.Alumno;
import com.erhc.alumnoservice.app.domain.AlumnoEstado;
import com.erhc.alumnoservice.app.repository.AlumnoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;

    @Override
    public Mono<Void> crearAlumno(AlumnoRequest request) {
        return alumnoRepository.existsById(request.id())
                .flatMap(exists -> {
                    if(exists){
                        return Mono.error(new DuplicateIdException(request.id()));
                    }

                    Alumno alumno  = Alumno.builder()
                            .id(request.id())
                            .nombre(request.nombre())
                            .apellido(request.apellido())
                            .estado(request.estado())
                            .edad(request.edad())
                            .build();

                    return alumnoRepository.save(alumno).then();
                });
    }

    @Override
    public Flux<AlumnoResponse> obtenerAlumnosActivos() {
        return alumnoRepository.findByEstado(AlumnoEstado.ACTIVO)
                .map( a -> new AlumnoResponse(
                        a.getId(),
                        a.getNombre(),
                        a.getApellido(),
                        a.getEstado(),
                        a.getEdad()
                ));
    }
}

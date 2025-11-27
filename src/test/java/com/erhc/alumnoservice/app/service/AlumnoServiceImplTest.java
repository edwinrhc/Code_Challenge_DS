package com.erhc.alumnoservice.app.service;

import com.erhc.alumnoservice.app.api.AlumnoRequest;
import com.erhc.alumnoservice.app.domain.Alumno;
import com.erhc.alumnoservice.app.domain.AlumnoEstado;
import com.erhc.alumnoservice.app.repository.AlumnoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AlumnoServiceImplTest {


    @Mock
    AlumnoRepository alumnoRepository;

    @InjectMocks
    AlumnoServiceImpl alumnoService;

    @Test
    void crearAlumno_ok() {
        AlumnoRequest request = new AlumnoRequest(
                1L,
                "Edwin",
                "Huamanttupa",
                AlumnoEstado.ACTIVO,
                30);
        when(alumnoRepository.existsById(1L))
                .thenReturn(Mono.just(false));
        when(alumnoRepository.save(any(Alumno.class)))
                .thenReturn(Mono.just(Alumno.builder().id(1L).build()));

        StepVerifier.create(alumnoService.crearAlumno(request))
                .verifyComplete();

    }

    @Test
    void crearAlumno_idDuplicado_error() {
        AlumnoRequest request = new AlumnoRequest
                (
                        1L,
                        "Edwin",
                        "Huamanttupa",
                        AlumnoEstado.ACTIVO,
                        30
                );

        when(alumnoRepository.existsById(1L)).thenReturn(Mono.just(true));

        StepVerifier.create(alumnoService.crearAlumno(request))
                .expectError(DuplicateIdException.class)
                .verify();
    }

}

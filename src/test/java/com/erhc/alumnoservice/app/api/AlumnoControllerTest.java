package com.erhc.alumnoservice.app.api;

import com.erhc.alumnoservice.app.domain.AlumnoEstado;
import com.erhc.alumnoservice.app.service.AlumnoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(controllers = AlumnoController.class)
public class AlumnoControllerTest {

    @Autowired
    private  WebTestClient webTestClient;

    @MockitoBean
    private AlumnoService alumnoService;

    @Test
    void crearAlumno_creado(){
        AlumnoRequest request = new AlumnoRequest(1L,"Edwin","HC", AlumnoEstado.ACTIVO, 30);

        Mockito.when(alumnoService.crearAlumno(request))
                        .thenReturn(Mono.empty());

        webTestClient.post()
                .uri("/alumnos")
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    void listarActivos_ok(){
        AlumnoResponse r1 = new AlumnoResponse(1L,"A","HC", AlumnoEstado.ACTIVO, 20);

        Mockito.when(alumnoService.obtenerAlumnosActivos())
                .thenReturn(Flux.just(r1));

        webTestClient.get()
                .uri("/alumnos/activos")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(AlumnoResponse.class)
                .hasSize(1);

    }


}

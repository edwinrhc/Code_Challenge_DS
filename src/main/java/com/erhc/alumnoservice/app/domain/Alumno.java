package com.erhc.alumnoservice.app.domain;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Table("alumno")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alumno implements Persistable<Long> {

    @Id
    private Long id;

    private String nombre;
    private String apellido;
    private AlumnoEstado estado;
    private Integer edad;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public boolean isNew() {
        return true;
    }


}

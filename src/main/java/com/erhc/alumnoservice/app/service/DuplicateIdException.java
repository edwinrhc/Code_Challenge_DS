package com.erhc.alumnoservice.app.service;

public class DuplicateIdException extends RuntimeException {
    public DuplicateIdException(Long id) {
        super("No se pudo grabar el alumno. El id " + id + " ya existe.");
    }
}

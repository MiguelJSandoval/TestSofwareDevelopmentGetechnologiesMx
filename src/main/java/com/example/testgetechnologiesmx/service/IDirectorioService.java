package com.example.testgetechnologiesmx.service;

import com.example.testgetechnologiesmx.model.Persona;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IDirectorioService {
    ResponseEntity<Object> findPersonaByIdentificacion(String identificacion);

    ResponseEntity<Object> findPersonas();

    ResponseEntity<Object> deletePersonaByIdentificacion(String identificacion);

    ResponseEntity<Object> storePersona(Persona persona);
}

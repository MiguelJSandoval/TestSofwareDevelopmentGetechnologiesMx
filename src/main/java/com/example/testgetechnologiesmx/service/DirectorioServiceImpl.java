package com.example.testgetechnologiesmx.service;

import com.example.testgetechnologiesmx.model.Persona;
import com.example.testgetechnologiesmx.repository.IPersonaRepository;
import com.example.testgetechnologiesmx.util.Validador;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class DirectorioServiceImpl implements IDirectorioService {
    private IPersonaRepository personaRepository;

    @Autowired
    public void setPersonaRepository(IPersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public ResponseEntity<Object> findPersonaByIdentificacion(String identificacion) {
        if (Validador.notNullAndNotEmpty(identificacion)) {
            return new ResponseEntity<>(personaRepository.findByIdentificacion(identificacion), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> findPersonas() {
        try {
            return new ResponseEntity<>(personaRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> deletePersonaByIdentificacion(String identificacion) {
        if (Validador.notNullAndNotEmpty(identificacion))
            try {
                if (personaRepository.deleteByIdentificacion(identificacion) == 1)
                    return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> storePersona(Persona persona) {
        if (Validador.notNullAndNotEmpty(persona.getNombre())
                && Validador.notNullAndNotEmpty(persona.getApellidoPaterno())
                && Validador.notNullAndNotEmpty(persona.getIdentificacion())) {
            try {
                personaRepository.save(persona);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (DataIntegrityViolationException e) {
                String constraintName = null;
                if ((e.getCause() != null) && (e.getCause() instanceof ConstraintViolationException))
                    constraintName = ((ConstraintViolationException) e.getCause()).getConstraintName();
                if (constraintName != null && constraintName.toLowerCase(Locale.ROOT).contains("uq_identificacion"))
                    return new ResponseEntity<>("Identificación duplicada", HttpStatus.BAD_REQUEST);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

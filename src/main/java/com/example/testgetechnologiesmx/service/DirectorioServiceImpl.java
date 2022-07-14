package com.example.testgetechnologiesmx.service;

import com.example.testgetechnologiesmx.model.Persona;
import com.example.testgetechnologiesmx.repository.IPersonaRepository;
import com.example.testgetechnologiesmx.util.Validador;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class DirectorioServiceImpl implements IDirectorioService {
    private IPersonaRepository personaRepository;

    Logger logger = LoggerFactory.getLogger(DirectorioServiceImpl.class);

    @Autowired
    public void setPersonaRepository(IPersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public ResponseEntity<Object> findPersonaByIdentificacion(String identificacion) {
        if (Validador.notNullAndNotEmpty(identificacion)) {
            try {
                logger.info("Buscando persona con identificación = " + identificacion);
                return new ResponseEntity<>(personaRepository.findByIdentificacion(identificacion), HttpStatus.OK);
            } catch (Exception e) {
                logger.error("Ocurrió un error al buscar la persona con identificación = " + identificacion
                        + "\nRazón: " + e.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        logger.warn("No se pueden buscar personas con identificación nula o vacía.");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> findPersonas() {
        try {
            logger.info("Buscando personas...");
            return new ResponseEntity<>(personaRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Ocurrió un error al buscar a las personas." +
                    "\nRazón: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> deletePersonaByIdentificacion(String identificacion) {
        if (Validador.notNullAndNotEmpty(identificacion)) {
            try {
                logger.info("Eliminando persona con identificación = " + identificacion);
                if (personaRepository.deleteByIdentificacion(identificacion) == 1)
                    return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                logger.error("Ocurrió un error al eliminar a la persona con identificación = " + identificacion +
                        "\nRazón: " + e.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        logger.warn("No se puede eliminar una persona con identificación nula o vacía.");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> storePersona(Persona persona) {
        if (Validador.notNullAndNotEmpty(persona.getNombre())
                && Validador.notNullAndNotEmpty(persona.getApellidoPaterno())
                && Validador.notNullAndNotEmpty(persona.getIdentificacion())) {
            logger.info("Guardando persona: " + persona);
            try {
                personaRepository.save(persona);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (DataIntegrityViolationException e) {
                String constraintName = null;
                if ((e.getCause() != null) && (e.getCause() instanceof ConstraintViolationException))
                    constraintName = ((ConstraintViolationException) e.getCause()).getConstraintName();
                if (constraintName != null && constraintName.toLowerCase(Locale.ROOT).contains("uq_identificacion")) {
                    logger.error("No se puede guardar a la persona = " + persona
                            + " ya que su identificación ya existe para otra persona dentro de la Base de Datos.");
                    return new ResponseEntity<>("Identificación duplicada", HttpStatus.BAD_REQUEST);
                }
                logger.error("Ocurrió un error al guardar a la persona = " + persona
                        + "\nRazón: " + e.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            } catch (Exception e) {
                logger.error("Ocurrió un error al guardar a la persona = " + persona
                        + "\nRazón: " + e.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        logger.warn("Los datos de la persona(" + persona + ") están incompletos o no cumplen con el formato dado.");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

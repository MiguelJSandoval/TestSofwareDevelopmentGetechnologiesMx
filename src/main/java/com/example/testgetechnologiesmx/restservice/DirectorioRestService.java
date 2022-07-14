package com.example.testgetechnologiesmx.restservice;

import com.example.testgetechnologiesmx.model.Persona;
import com.example.testgetechnologiesmx.service.IDirectorioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/personas")
public class DirectorioRestService {
    private IDirectorioService directorioService;

    Logger logger = LoggerFactory.getLogger(DirectorioRestService.class);

    @Autowired
    public void setDirectorioService(IDirectorioService directorioService) {
        this.directorioService = directorioService;
    }

    @GetMapping("/identificacion/{identificacion}")
    public ResponseEntity<Object> findPersonaByIdentificacion(@PathVariable("identificacion") String identificacion) {
        logger.info("GET: /api/personas/identificacion/{identificacion}");
        logger.info("identificacion = " + identificacion);
        return directorioService.findPersonaByIdentificacion(identificacion);
    }

    @GetMapping
    public ResponseEntity<Object> findPersonas() {
        logger.info("GET: /api/personas");
        return directorioService.findPersonas();
    }

    @DeleteMapping("/identificacion")
    public ResponseEntity<Object> deletePersonaByIdentificacion(@RequestBody String identificacion) {
        logger.info("DELETE: /api/personas/identificacion");
        logger.info("identificacion = " + identificacion);
        return directorioService.deletePersonaByIdentificacion(identificacion);
    }

    @PostMapping
    public ResponseEntity<Object> storePersona(@RequestBody Persona persona) {
        logger.info("POST: /api/personas");
        logger.info("persona = " + persona);
        return directorioService.storePersona(persona);
    }
}

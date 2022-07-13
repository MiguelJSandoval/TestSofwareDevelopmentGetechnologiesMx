package com.example.testgetechnologiesmx.restservice;

import com.example.testgetechnologiesmx.model.Persona;
import com.example.testgetechnologiesmx.service.IDirectorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/personas")
public class DirectorioRestService {
    private IDirectorioService directorioService;

    @Autowired
    public void setDirectorioService(IDirectorioService directorioService) {
        this.directorioService = directorioService;
    }

    @GetMapping("/identificacion/{identificacion}")
    public ResponseEntity<Object> findPersonaByIdentificacion(@PathVariable("identificacion") String identificacion) {
        return directorioService.findPersonaByIdentificacion(identificacion);
    }

    @GetMapping
    public ResponseEntity<Object> findPersonas() {
        return directorioService.findPersonas();
    }

    @DeleteMapping("/identificacion")
    public ResponseEntity<Object> deletePersonaByIdentificacion(@RequestBody String identificacion) {
        return directorioService.deletePersonaByIdentificacion(identificacion);
    }

    @PostMapping
    public ResponseEntity<Object> storePersona(@RequestBody Persona persona) {
        return directorioService.storePersona(persona);
    }
}

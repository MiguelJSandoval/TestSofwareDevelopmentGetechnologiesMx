package com.example.testgetechnologiesmx.restservice;

import com.example.testgetechnologiesmx.model.Factura;
import com.example.testgetechnologiesmx.service.IVentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/facturas")
public class FacturaRestService {
    private IVentasService ventasService;

    @Autowired
    public void setVentasService(IVentasService ventasService) {
        this.ventasService = ventasService;
    }

    @PostMapping
    public ResponseEntity<Object> storeFactura(@RequestBody Factura factura) {
        return ventasService.storeFactura(factura);
    }

    @GetMapping("/personas/{id}")
    public ResponseEntity<Object> findFacturasByPersona(@PathVariable("id") Long personaId) {
        return ventasService.findFacturasByPersona(personaId);
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        return ventasService.getAll();
    }
}

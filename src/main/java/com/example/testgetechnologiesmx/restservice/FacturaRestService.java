package com.example.testgetechnologiesmx.restservice;

import com.example.testgetechnologiesmx.model.Factura;
import com.example.testgetechnologiesmx.service.IVentasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/facturas")
public class FacturaRestService {
    private IVentasService ventasService;

    Logger logger = LoggerFactory.getLogger(FacturaRestService.class);

    @Autowired
    public void setVentasService(IVentasService ventasService) {
        this.ventasService = ventasService;
    }

    @PostMapping
    public ResponseEntity<Object> storeFactura(@RequestBody Factura factura) {
        logger.info("POST: /api/facturas");
        logger.info("factura = " + factura);
        return ventasService.storeFactura(factura);
    }

    @GetMapping("/personas/{id}")
    public ResponseEntity<Object> findFacturasByPersona(@PathVariable("id") Long personaId) {
        logger.info("GET: /api/facturas/personas/{id}");
        logger.info("id = " + personaId);
        return ventasService.findFacturasByPersona(personaId);
    }
}

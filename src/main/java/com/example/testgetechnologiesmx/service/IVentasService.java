package com.example.testgetechnologiesmx.service;

import com.example.testgetechnologiesmx.model.Factura;
import org.springframework.http.ResponseEntity;

public interface IVentasService {
    ResponseEntity<Object> findFacturasByPersona(Long personaId);

    ResponseEntity<Object> storeFactura(Factura factura);

    ResponseEntity<Object> getAll();
}

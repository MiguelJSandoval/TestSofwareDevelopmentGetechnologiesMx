package com.example.testgetechnologiesmx.service;


import com.example.testgetechnologiesmx.model.Factura;
import com.example.testgetechnologiesmx.model.Persona;
import com.example.testgetechnologiesmx.repository.IFacturaRespository;
import com.example.testgetechnologiesmx.repository.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class VentasServiceImpl implements IVentasService {
    private IFacturaRespository facturaRespository;

    private IPersonaRepository personaRepository;

    @Autowired
    public void setFacturaRespository(IFacturaRespository facturaRespository) {
        this.facturaRespository = facturaRespository;
    }

    @Autowired
    public void setPersonaRepository(IPersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public ResponseEntity<Object> findFacturasByPersona(Long personaId) {
        if (personaId != null && personaId > 0) {
            try {
                List<Factura> facturas = facturaRespository.getAllByPersonaId(personaId);
                return new ResponseEntity<>(facturas, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> storeFactura(Factura factura) {
        if (factura.getPersonaId() != null) {
            Persona persona = personaRepository.findById(factura.getPersonaId()).orElse(null);
            if (persona != null && factura.getMonto().compareTo(BigDecimal.ZERO) > 0 && factura.getFecha() != null) {
                try {
                    factura.setPersonaByFacturaId(persona);
                    facturaRespository.save(factura);
                    return new ResponseEntity<>(HttpStatus.OK);
                } catch (Exception e) {
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> getAll() {
        try {
            return new ResponseEntity<>(facturaRespository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

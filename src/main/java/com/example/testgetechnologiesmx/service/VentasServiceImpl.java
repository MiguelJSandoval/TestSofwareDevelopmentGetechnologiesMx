package com.example.testgetechnologiesmx.service;


import com.example.testgetechnologiesmx.model.Factura;
import com.example.testgetechnologiesmx.model.Persona;
import com.example.testgetechnologiesmx.repository.IFacturaRespository;
import com.example.testgetechnologiesmx.repository.IPersonaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(VentasServiceImpl.class);

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
                logger.info("Buscando facturas para la persona con id = " + personaId);
                List<Factura> facturas = facturaRespository.getAllByPersonaId(personaId);
                return new ResponseEntity<>(facturas, HttpStatus.OK);
            } catch (Exception e) {
                logger.error("Ocurrió un error al buscar las facturas para la persona con id = " + personaId
                        + "\nRazón: " + e.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        logger.warn("No se pueden obtener las facturas para una persona con id nulo o vacío.");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> storeFactura(Factura factura) {
        if (factura.getPersonaId() != null) {
            logger.info("Guardando factura = " + factura);
            logger.info("Buscando persona con id = " + factura.getPersonaId());
            Persona persona = personaRepository.findById(factura.getPersonaId()).orElse(null);
            if (persona != null && factura.getMonto().compareTo(BigDecimal.ZERO) > 0 && factura.getFecha() != null) {
                try {
                    logger.info("Guardando factura = " + factura);
                    factura.setPersonaByFacturaId(persona);
                    facturaRespository.save(factura);
                    return new ResponseEntity<>(HttpStatus.OK);
                } catch (Exception e) {
                    logger.error("Ocurrió un error al guardar la factura = " + factura
                            + "\nRazón: " + e.getMessage());
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else if (persona == null) {
                logger.warn("No se encontó a la persona con id = " + factura.getPersonaId());
            } else {
                logger.warn("La factura = " + factura + " contiene datos érroneso, verifique el formato de fecha y el monto."
                + "\nEl formato de la fecha debe ser yyyy-mm-dd y el monto debe ser estrictamente mayor a cero y con un máximo de "
                + "dos décimales.");
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.warn("No se puede guardar la factura = " + factura
                + " con id de persona nulo o vacío.");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

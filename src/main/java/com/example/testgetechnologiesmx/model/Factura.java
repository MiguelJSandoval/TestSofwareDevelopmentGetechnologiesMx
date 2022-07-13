package com.example.testgetechnologiesmx.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "factura")
@JsonIgnoreProperties("personaByFacturaId")
public class Factura {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "factura_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "factura_id")
    private Long id;

    @Column(name = "persona_id", nullable = false, insertable = false, updatable = false)
    private Long personaId;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "monto", nullable = false, scale = 2)
    private BigDecimal monto;

    @ManyToOne
    @JoinColumn(name = "persona_id", referencedColumnName = "id", nullable = false)
    private Persona personaByFacturaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
    }

    public Persona getPersonaByFacturaId() {
        return personaByFacturaId;
    }

    public void setPersonaByFacturaId(Persona personaByFacturaId) {
        this.personaByFacturaId = personaByFacturaId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Factura(id = " +
                id +
                ", personaId = " +
                personaId +
                ", fecha = " +
                fecha +
                ", monto = " +
                monto +
                ")";
    }
}

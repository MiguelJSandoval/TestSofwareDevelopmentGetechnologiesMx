package com.example.testgetechnologiesmx.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Collection;

@Entity
@JsonIgnoreProperties("facturasByPersonaId")
@Table(name = "persona", uniqueConstraints = {@UniqueConstraint(name = "uq_identificacion", columnNames = "identificacion")})
public class Persona {
    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "persona_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "persona_id")
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido_paterno", nullable = false)
    private String apellidoPaterno;

    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    /**
     * Identificación debe ser único para que no exista la posibilidad de eliminar a dos o más
     * {@link Persona} con un mismo valor para "identificación"
     */
    @Column(name = "identificacion", nullable = false)
    private String identificacion;

    @OneToMany(mappedBy = "personaByFacturaId", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Collection<Factura> facturasByPersonaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Collection<Factura> getFacturasByPersonaId() {
        return facturasByPersonaId;
    }

    public void setFacturasByPersonaId(Collection<Factura> facturasByPersonaId) {
        this.facturasByPersonaId = facturasByPersonaId;
    }

    @Override
    public String toString() {
        return "Persona(id = " +
                id +
                ", nombre = " +
                nombre +
                ", apellidoPaterno = " +
                apellidoPaterno +
                ", apellidoMterno = " +
                apellidoMaterno +
                ", identificacion = " +
                identificacion +
                ")";
    }
}

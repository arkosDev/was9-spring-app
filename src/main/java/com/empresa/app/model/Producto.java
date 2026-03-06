package com.empresa.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Entidad JPA de ejemplo.
 * WAS 9 provee el EntityManager via JNDI (IBM OpenJPA).
 */
@Entity
@Table(name = "PRODUCTOS", schema = "APP")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_producto")
    @SequenceGenerator(name = "seq_producto", sequenceName = "SEQ_PRODUCTO", allocationSize = 1)
    @Column(name = "ID_PRODUCTO")
    private Long id;

    @NotBlank
    @Column(name = "NOMBRE", nullable = false, length = 200)
    private String nombre;

    @Column(name = "DESCRIPCION", length = 500)
    private String descripcion;

    @NotNull
    @Column(name = "PRECIO", nullable = false)
    private Double precio;

    @Column(name = "ACTIVO", nullable = false)
    private Boolean activo = Boolean.TRUE;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_CREACION", nullable = false, updatable = false)
    private Date fechaCreacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_MODIFICACION")
    private Date fechaModificacion;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        fechaModificacion = new Date();
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }

    public Date getFechaCreacion() { return fechaCreacion; }
    public Date getFechaModificacion() { return fechaModificacion; }
}

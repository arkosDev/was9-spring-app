package com.empresa.api.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "PRODUCTOS")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_prod")
    @SequenceGenerator(name = "seq_prod", sequenceName = "SEQ_PRODUCTOS", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Column(name = "NOMBRE", nullable = false, length = 200)
    private String nombre;

    @Column(name = "DESCRIPCION", length = 500)
    private String descripcion;

    @NotNull
    @Min(0)
    @Column(name = "PRECIO", nullable = false)
    private Double precio;

    @NotNull
    @Min(0)
    @Column(name = "STOCK", nullable = false)
    private Integer stock;

    @Column(name = "CATEGORIA", length = 100)
    private String categoria;

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
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
    public Date getFechaCreacion() { return fechaCreacion; }
    public Date getFechaModificacion() { return fechaModificacion; }
}

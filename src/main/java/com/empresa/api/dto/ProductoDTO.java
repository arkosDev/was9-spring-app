package com.empresa.api.dto;

import java.util.Date;

public class ProductoDTO {
    private Long    id;
    private String  nombre;
    private String  descripcion;
    private Double  precio;
    private Integer stock;
    private String  categoria;
    private Boolean activo;
    private Date    fechaCreacion;

    public ProductoDTO() {}

    public Long    getId()          { return id; }
    public void    setId(Long id)   { this.id = id; }
    public String  getNombre()      { return nombre; }
    public void    setNombre(String n)  { this.nombre = n; }
    public String  getDescripcion() { return descripcion; }
    public void    setDescripcion(String d) { this.descripcion = d; }
    public Double  getPrecio()      { return precio; }
    public void    setPrecio(Double p)  { this.precio = p; }
    public Integer getStock()       { return stock; }
    public void    setStock(Integer s)  { this.stock = s; }
    public String  getCategoria()   { return categoria; }
    public void    setCategoria(String c) { this.categoria = c; }
    public Boolean getActivo()      { return activo; }
    public void    setActivo(Boolean a) { this.activo = a; }
    public Date    getFechaCreacion()   { return fechaCreacion; }
    public void    setFechaCreacion(Date f) { this.fechaCreacion = f; }
}

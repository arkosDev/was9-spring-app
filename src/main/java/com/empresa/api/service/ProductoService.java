package com.empresa.api.service;

import com.empresa.api.dto.ProductoDTO;
import com.empresa.api.exception.RecursoNoEncontradoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Servicio de Productos.
 * Opera únicamente sobre DTOs — sin acceso a base de datos.
 * La persistencia real se implementará en la capa que el equipo defina.
 */
@Service
public class ProductoService {

    private static final Logger log = LoggerFactory.getLogger(ProductoService.class);

    // Almacén en memoria — reemplazar por la capa de persistencia correspondiente
    private final List<ProductoDTO> almacen = new CopyOnWriteArrayList<>();
    private final AtomicLong secuencia = new AtomicLong(1);

    public ProductoService() {
        // Datos de ejemplo para desarrollo
        almacen.add(build(1L, "Laptop Pro 15",   "Laptop de alto rendimiento", 1299.99, 10, "Electrónica"));
        almacen.add(build(2L, "Teclado Mecánico", "Teclado con switches Cherry MX", 89.99,  25, "Periféricos"));
        almacen.add(build(3L, "Monitor 4K 27\"",  "Monitor UHD con HDR",         349.99, 8,  "Electrónica"));
        secuencia.set(4);
    }

    public List<ProductoDTO> listarActivos() {
        return almacen.stream()
            .filter(p -> Boolean.TRUE.equals(p.getActivo()))
            .collect(Collectors.toList());
    }

    public List<ProductoDTO> buscar(String termino) {
        String t = termino.toLowerCase();
        return almacen.stream()
            .filter(p -> Boolean.TRUE.equals(p.getActivo()))
            .filter(p -> p.getNombre().toLowerCase().contains(t))
            .collect(Collectors.toList());
    }

    public ProductoDTO obtenerPorId(Long id) {
        return almacen.stream()
            .filter(p -> id.equals(p.getId()))
            .findFirst()
            .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado: " + id));
    }

    public ProductoDTO crear(ProductoDTO dto) {
        dto.setId(secuencia.getAndIncrement());
        dto.setActivo(true);
        dto.setFechaCreacion(new Date());
        almacen.add(dto);
        log.info("Producto creado: {}", dto.getNombre());
        return dto;
    }

    public ProductoDTO actualizar(Long id, ProductoDTO datos) {
        ProductoDTO existente = obtenerPorId(id);
        existente.setNombre(datos.getNombre());
        existente.setDescripcion(datos.getDescripcion());
        existente.setPrecio(datos.getPrecio());
        existente.setStock(datos.getStock());
        existente.setCategoria(datos.getCategoria());
        log.info("Producto actualizado: {}", id);
        return existente;
    }

    public void eliminar(Long id) {
        ProductoDTO p = obtenerPorId(id);
        p.setActivo(false);
        log.info("Producto eliminado (lógico): {}", id);
    }

    private ProductoDTO build(Long id, String nombre, String desc,
                               Double precio, Integer stock, String categoria) {
        ProductoDTO p = new ProductoDTO();
        p.setId(id);           p.setNombre(nombre);
        p.setDescripcion(desc); p.setPrecio(precio);
        p.setStock(stock);     p.setCategoria(categoria);
        p.setActivo(true);     p.setFechaCreacion(new Date());
        return p;
    }
}

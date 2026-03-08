package com.empresa.api.service;

import com.empresa.api.dto.ProductoDTO;
import com.empresa.api.exception.RecursoNoEncontradoException;
import com.empresa.api.model.Producto;
import com.empresa.api.repository.ProductoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductoService {

    private static final Logger log = LoggerFactory.getLogger(ProductoService.class);

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional(readOnly = true)
    public List<ProductoDTO> listarActivos() {
        return productoRepository.findByActivoTrue().stream()
            .map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProductoDTO> buscar(String termino) {
        return productoRepository.buscarPorNombre(termino).stream()
            .map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductoDTO obtenerPorId(Long id) {
        Producto p = productoRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado: " + id));
        return toDTO(p);
    }

    public ProductoDTO crear(ProductoDTO dto) {
        Producto p = toEntity(dto);
        productoRepository.save(p);
        log.info("Producto creado: {}", p.getNombre());
        return toDTO(p);
    }

    public ProductoDTO actualizar(Long id, ProductoDTO dto) {
        Producto p = productoRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado: " + id));
        p.setNombre(dto.getNombre());
        p.setDescripcion(dto.getDescripcion());
        p.setPrecio(dto.getPrecio());
        p.setStock(dto.getStock());
        p.setCategoria(dto.getCategoria());
        productoRepository.save(p);
        return toDTO(p);
    }

    public void eliminar(Long id) {
        Producto p = productoRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado: " + id));
        p.setActivo(false);
        productoRepository.save(p);
        log.info("Producto {} desactivado", id);
    }

    private ProductoDTO toDTO(Producto p) {
        return new ProductoDTO(p.getId(), p.getNombre(), p.getDescripcion(),
            p.getPrecio(), p.getStock(), p.getCategoria(), p.getActivo(), p.getFechaCreacion());
    }

    private Producto toEntity(ProductoDTO dto) {
        Producto p = new Producto();
        p.setNombre(dto.getNombre());
        p.setDescripcion(dto.getDescripcion());
        p.setPrecio(dto.getPrecio());
        p.setStock(dto.getStock() != null ? dto.getStock() : 0);
        p.setCategoria(dto.getCategoria());
        return p;
    }
}

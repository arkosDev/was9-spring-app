package com.empresa.app.service;

import com.empresa.app.exception.RecursoNoEncontradoException;
import com.empresa.app.model.Producto;
import com.empresa.app.repository.ProductoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoServiceImpl implements ProductoService {

    private static final Logger log = LoggerFactory.getLogger(ProductoServiceImpl.class);

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarActivos() {
        log.info("Listando productos activos");
        return productoRepository.findByActivoTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> buscarPorId(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto guardar(Producto producto) {
        log.info("Guardando producto: {}", producto.getNombre());
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizar(Long id, Producto datos) {
        Producto existente = productoRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado: " + id));
        existente.setNombre(datos.getNombre());
        existente.setDescripcion(datos.getDescripcion());
        existente.setPrecio(datos.getPrecio());
        return productoRepository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado: " + id));
        producto.setActivo(false);
        productoRepository.save(producto);
        log.info("Producto {} desactivado (borrado logico)", id);
    }
}

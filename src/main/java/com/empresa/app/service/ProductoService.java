package com.empresa.app.service;

import com.empresa.app.model.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listarActivos();
    Optional<Producto> buscarPorId(Long id);
    Producto guardar(Producto producto);
    Producto actualizar(Long id, Producto producto);
    void eliminar(Long id);
}

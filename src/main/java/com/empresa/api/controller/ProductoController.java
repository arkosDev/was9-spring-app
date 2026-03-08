package com.empresa.api.controller;

import com.empresa.api.dto.ApiResponse;
import com.empresa.api.dto.ProductoDTO;
import com.empresa.api.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST API para Productos.
 * Base URL en WAS 9: /api-backend/api/productos
 */
@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // GET /api/productos
    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductoDTO>>> listar() {
        return ResponseEntity.ok(ApiResponse.ok(productoService.listarActivos()));
    }

    // GET /api/productos/buscar?q=termino
    @GetMapping("/buscar")
    public ResponseEntity<ApiResponse<List<ProductoDTO>>> buscar(@RequestParam("q") String termino) {
        return ResponseEntity.ok(ApiResponse.ok(productoService.buscar(termino)));
    }

    // GET /api/productos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductoDTO>> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(productoService.obtenerPorId(id)));
    }

    // POST /api/productos  (requiere auth)
    @PostMapping
    public ResponseEntity<ApiResponse<ProductoDTO>> crear(@RequestBody ProductoDTO dto) {
        ProductoDTO creado = productoService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok("Producto creado", creado));
    }

    // PUT /api/productos/{id}  (requiere auth)
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductoDTO>> actualizar(
            @PathVariable Long id, @RequestBody ProductoDTO dto) {
        return ResponseEntity.ok(ApiResponse.ok("Producto actualizado", productoService.actualizar(id, dto)));
    }

    // DELETE /api/productos/{id}  (requiere auth)
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Producto eliminado", null));
    }
}

package com.empresa.api.struts.action;

import com.empresa.api.dto.ProductoDTO;
import com.empresa.api.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Action Struts 2 para el CRUD de Productos via vistas JSP.
 *
 * Reutiliza ProductoService (Spring) exactamente igual que los
 * @RestController de Spring MVC — sin duplicar logica de negocio.
 *
 * URLs expuestas (namespace /views/producto):
 *   GET  /views/producto/listar.action
 *   GET  /views/producto/nuevo.action
 *   POST /views/producto/guardar.action
 *   GET  /views/producto/editar.action?id=X
 *   GET  /views/producto/eliminar.action?id=X
 */
@Component
public class ProductoAction extends BaseAction {

    // --- Inyectado por Spring via struts2-spring-plugin ---
    @Autowired
    private ProductoService productoService;

    // --- Modelo del formulario ---
    private ProductoDTO producto = new ProductoDTO();
    private Long id;

    // --- Datos para la vista ---
    private List<ProductoDTO> productos;
    private String mensajeExito;

    // =====================================================
    // LISTAR
    // =====================================================
    public String listar() {
        try {
            productos = productoService.listarActivos();
            return SUCCESS;
        } catch (Exception e) {
            log.error("Error listando productos", e);
            addActionError("Error al cargar productos");
            return ERROR;
        }
    }

    // =====================================================
    // NUEVO (muestra formulario vacio)
    // =====================================================
    public String nuevo() {
        producto = new ProductoDTO();
        return SUCCESS;
    }

    // =====================================================
    // GUARDAR (crea o actualiza)
    // =====================================================
    public String guardar() {
        try {
            if (producto.getId() == null) {
                productoService.crear(producto);
                mensajeExito = "Producto creado correctamente";
            } else {
                productoService.actualizar(producto.getId(), producto);
                mensajeExito = "Producto actualizado correctamente";
            }
            getSession().put("mensajeExito", mensajeExito);
            return SUCCESS;
        } catch (Exception e) {
            log.error("Error guardando producto", e);
            addActionError("Error al guardar el producto: " + e.getMessage());
            return INPUT;
        }
    }

    // =====================================================
    // EDITAR (carga datos en formulario)
    // =====================================================
    public String editar() {
        try {
            producto = productoService.obtenerPorId(id);
            return SUCCESS;
        } catch (Exception e) {
            log.warn("Producto {} no encontrado para editar", id);
            addActionError("Producto no encontrado");
            return NOT_FOUND;
        }
    }

    // =====================================================
    // ELIMINAR (borrado logico)
    // =====================================================
    public String eliminar() {
        try {
            productoService.eliminar(id);
            getSession().put("mensajeExito", "Producto eliminado");
            return SUCCESS;
        } catch (Exception e) {
            log.error("Error eliminando producto {}", id, e);
            addActionError("Error al eliminar el producto");
            return ERROR;
        }
    }

    // =====================================================
    // Validacion Struts 2 para guardar()
    // =====================================================
    public void validateGuardar() {
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            addFieldError("producto.nombre", "El nombre es requerido");
        }
        if (producto.getPrecio() == null || producto.getPrecio() < 0) {
            addFieldError("producto.precio", "El precio debe ser mayor o igual a 0");
        }
        if (producto.getStock() == null || producto.getStock() < 0) {
            addFieldError("producto.stock", "El stock debe ser mayor o igual a 0");
        }
    }

    // =====================================================
    // Getters / Setters (requeridos por Struts para binding)
    // =====================================================
    public List<ProductoDTO> getProductos()      { return productos; }
    public ProductoDTO       getProducto()        { return producto; }
    public void              setProducto(ProductoDTO p) { this.producto = p; }
    public Long              getId()              { return id; }
    public void              setId(Long id)       { this.id = id; }
    public String            getMensajeExito()    { return mensajeExito; }
}

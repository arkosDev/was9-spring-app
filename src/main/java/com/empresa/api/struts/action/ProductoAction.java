package com.empresa.api.struts.action;

import com.empresa.api.dto.ProductoDTO;
import com.empresa.api.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Action Struts 2 para el CRUD de Productos via vistas JSP.
 * Reutiliza ProductoService (Spring) sin duplicar logica de negocio.
 *
 * URLs (namespace /views/producto):
 *   /views/producto/listar.action
 *   /views/producto/nuevo.action
 *   /views/producto/guardar.action   (POST)
 *   /views/producto/editar.action?id=X
 *   /views/producto/eliminar.action?id=X
 */
@Component
public class ProductoAction extends BaseAction {

    @Autowired
    private ProductoService productoService;

    private ProductoDTO     producto = new ProductoDTO();
    private Long            id;
    private List<ProductoDTO> productos;

    // ---- LISTAR ----
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

    // ---- NUEVO (formulario vacío) ----
    public String nuevo() {
        producto = new ProductoDTO();
        return SUCCESS;
    }

    // ---- GUARDAR (crear o actualizar) ----
    public String guardar() {
        try {
            if (producto.getId() == null) {
                productoService.crear(producto);
            } else {
                productoService.actualizar(producto.getId(), producto);
            }
            getSession().put("flash", "Producto guardado correctamente");
            return SUCCESS;
        } catch (Exception e) {
            log.error("Error guardando producto", e);
            addActionError("Error al guardar: " + e.getMessage());
            return INPUT;
        }
    }

    // ---- EDITAR (carga datos) ----
    public String editar() {
        try {
            producto = productoService.obtenerPorId(id);
            return SUCCESS;
        } catch (Exception e) {
            log.warn("Producto {} no encontrado", id);
            return NOT_FOUND;
        }
    }

    // ---- ELIMINAR ----
    public String eliminar() {
        try {
            productoService.eliminar(id);
            getSession().put("flash", "Producto eliminado");
            return SUCCESS;
        } catch (Exception e) {
            log.error("Error eliminando producto {}", id, e);
            addActionError("Error al eliminar");
            return ERROR;
        }
    }

    // ---- Validacion Struts 2 para guardar() ----
    public void validateGuardar() {
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty())
            addFieldError("producto.nombre", "El nombre es requerido");
        if (producto.getPrecio() == null || producto.getPrecio() < 0)
            addFieldError("producto.precio", "El precio debe ser >= 0");
        if (producto.getStock() == null || producto.getStock() < 0)
            addFieldError("producto.stock", "El stock debe ser >= 0");
    }

    // ---- Getters / Setters ----
    public List<ProductoDTO> getProductos()         { return productos; }
    public ProductoDTO       getProducto()           { return producto; }
    public void              setProducto(ProductoDTO p) { this.producto = p; }
    public Long              getId()                 { return id; }
    public void              setId(Long id)          { this.id = id; }
}

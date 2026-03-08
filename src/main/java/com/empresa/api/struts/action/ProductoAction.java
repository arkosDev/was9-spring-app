package com.empresa.api.struts.action;

import com.empresa.api.dto.ProductoDTO;
import com.empresa.api.service.ProductoService;
import com.empresa.api.struts.form.ProductoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Action Struts 2 para el CRUD de Productos.
 *
 * Sigue el patrón ActionForm de Struts 1 usando ProductoForm como form bean:
 *   - ProductoForm agrupa los campos del formulario HTML
 *   - Struts 2 los popula automáticamente via OGNL (name="form.campo")
 *   - El Action convierte el form a DTO antes de llamar al servicio
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

    // --- Servicio inyectado por Spring ---
    @Autowired
    private ProductoService productoService;

    // --- Form bean (equivalente a ActionForm de Struts 1) ---
    // Struts 2 popula sus campos via OGNL desde los parámetros HTTP
    private ProductoForm form = new ProductoForm();

    // --- Datos para las vistas ---
    private List<ProductoDTO> productos;
    private Long id;

    // =========================================================
    // LISTAR
    // =========================================================
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

    // =========================================================
    // NUEVO — muestra el formulario vacío
    // =========================================================
    public String nuevo() {
        form.reset();
        return SUCCESS;
    }

    // =========================================================
    // GUARDAR — crea o actualiza usando datos del form bean
    // =========================================================
    public String guardar() {
        try {
            ProductoDTO dto = formToDTO();
            if (dto.getId() == null) {
                productoService.crear(dto);
            } else {
                productoService.actualizar(dto.getId(), dto);
            }
            getSession().put("flash", "Producto guardado correctamente");
            return SUCCESS;
        } catch (Exception e) {
            log.error("Error guardando producto", e);
            addActionError("Error al guardar: " + e.getMessage());
            return INPUT;
        }
    }

    // =========================================================
    // EDITAR — carga datos en el form bean para pre-poblar el HTML
    // =========================================================
    public String editar() {
        try {
            ProductoDTO dto = productoService.obtenerPorId(id);
            dtoToForm(dto);
            return SUCCESS;
        } catch (Exception e) {
            log.warn("Producto {} no encontrado", id);
            return NOT_FOUND;
        }
    }

    // =========================================================
    // ELIMINAR
    // =========================================================
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

    // =========================================================
    // Validacion Struts 2 — se ejecuta antes de guardar()
    // Equivale a ActionForm.validate() de Struts 1
    // =========================================================
    public void validateGuardar() {
        if (form.getNombre() == null || form.getNombre().trim().isEmpty())
            addFieldError("form.nombre", "El nombre es requerido");

        if (form.getPrecioAsDouble() == null)
            addFieldError("form.precio", "El precio debe ser un número >= 0");
        else if (form.getPrecioAsDouble() < 0)
            addFieldError("form.precio", "El precio no puede ser negativo");

        if (form.getStockAsInteger() == null)
            addFieldError("form.stock", "El stock debe ser un número entero >= 0");
        else if (form.getStockAsInteger() < 0)
            addFieldError("form.stock", "El stock no puede ser negativo");
    }

    // =========================================================
    // Helpers de conversión form <-> DTO
    // =========================================================
    private ProductoDTO formToDTO() {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(form.getId());
        dto.setNombre(form.getNombre());
        dto.setDescripcion(form.getDescripcion());
        dto.setPrecio(form.getPrecioAsDouble());
        dto.setStock(form.getStockAsInteger());
        dto.setCategoria(form.getCategoria());
        return dto;
    }

    private void dtoToForm(ProductoDTO dto) {
        form.setId(dto.getId());
        form.setNombre(dto.getNombre());
        form.setDescripcion(dto.getDescripcion());
        form.setPrecio(dto.getPrecio() != null ? String.valueOf(dto.getPrecio()) : "");
        form.setStock(dto.getStock() != null ? String.valueOf(dto.getStock()) : "");
        form.setCategoria(dto.getCategoria());
    }

    // =========================================================
    // Getters / Setters
    // =========================================================
    public ProductoForm       getForm()               { return form; }
    public void               setForm(ProductoForm f) { this.form = f; }
    public List<ProductoDTO>  getProductos()          { return productos; }
    public Long               getId()                 { return id; }
    public void               setId(Long id)          { this.id = id; }
}

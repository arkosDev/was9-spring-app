package com.empresa.api.struts.form;

/**
 * Form bean para el CRUD de Productos.
 *
 * Equivale al ActionForm de Struts 1: agrupa todos los campos
 * del formulario HTML y los expone con getters/setters para
 * que Struts 2 los popule automáticamente via OGNL.
 *
 * En la JSP se referencia como:
 *   <s:textfield name="form.nombre"/>
 *   <s:textfield name="form.precio"/>
 *   etc.
 */
public class ProductoForm extends BaseActionForm {

    private static final long serialVersionUID = 1L;

    // Campos del formulario — Struts 2 los bindea desde los parámetros HTTP
    private Long    id;
    private String  nombre;
    private String  descripcion;
    private String  precio;      // String para recibir el valor raw del campo HTML
    private String  stock;       // String para recibir el valor raw del campo HTML
    private String  categoria;

    @Override
    public void reset() {
        id          = null;
        nombre      = null;
        descripcion = null;
        precio      = null;
        stock       = null;
        categoria   = null;
    }

    /**
     * Convierte el precio String a Double.
     * Devuelve null si el campo está vacío o no es numérico.
     */
    public Double getPrecioAsDouble() {
        if (precio == null || precio.trim().isEmpty()) return null;
        try { return Double.parseDouble(precio.trim().replace(",", ".")); }
        catch (NumberFormatException e) { return null; }
    }

    /**
     * Convierte el stock String a Integer.
     * Devuelve null si el campo está vacío o no es numérico.
     */
    public Integer getStockAsInteger() {
        if (stock == null || stock.trim().isEmpty()) return null;
        try { return Integer.parseInt(stock.trim()); }
        catch (NumberFormatException e) { return null; }
    }

    @Override
    public boolean isValid() {
        return nombre != null && !nombre.trim().isEmpty()
            && getPrecioAsDouble() != null
            && getStockAsInteger() != null;
    }

    // Getters / Setters
    public Long   getId()          { return id; }
    public void   setId(Long id)   { this.id = id; }
    public String getNombre()      { return nombre; }
    public void   setNombre(String n)   { this.nombre = n; }
    public String getDescripcion() { return descripcion; }
    public void   setDescripcion(String d) { this.descripcion = d; }
    public String getPrecio()      { return precio; }
    public void   setPrecio(String p)   { this.precio = p; }
    public String getStock()       { return stock; }
    public void   setStock(String s)    { this.stock = s; }
    public String getCategoria()   { return categoria; }
    public void   setCategoria(String c) { this.categoria = c; }
}

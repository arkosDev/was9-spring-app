package com.empresa.api.struts.form;

import java.io.Serializable;

/**
 * Clase base que simula el comportamiento de ActionForm de Struts 1.
 *
 * En Struts 2 no existe ActionForm — el binding de parámetros HTTP
 * se hace directamente sobre propiedades del Action via OGNL.
 * Este patrón agrupa esas propiedades en un POJO separado para:
 *   - mantener el Action limpio (solo lógica de control)
 *   - reutilizar el form en varios Actions
 *   - centralizar la validación del formulario
 *
 * Uso en JSP (equivalente a Struts 1):
 *   <s:textfield name="form.nombre"/>   →  ProductoAction.getForm().getNombre()
 *
 * Uso en el Action:
 *   private ProductoForm form = new ProductoForm();
 *   public ProductoForm getForm() { return form; }
 *   public void setForm(ProductoForm f) { this.form = f; }
 */
public abstract class BaseActionForm implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Limpia los campos del formulario.
     * Equivalente a ActionForm.reset() de Struts 1.
     * Sobreescribir en cada subclase si se necesita.
     */
    public void reset() {
        // implementar en subclases
    }

    /**
     * Validación básica del formulario.
     * Devuelve true si el form es válido.
     * La validación detallada se hace en validateXxx() del Action.
     */
    public boolean isValid() {
        return true;
    }
}

package com.empresa.api.struts.action;

import com.opensymphony.xwork2.ActionSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase base para todos los Actions de Struts 2.
 * Spring inyecta los servicios gracias al struts2-spring-plugin.
 */
public abstract class BaseAction extends ActionSupport {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    // Resultado extra para "no encontrado"
    protected static final String NOT_FOUND = "notFound";

    /**
     * Verifica si el usuario tiene sesion web activa.
     * Usar en acciones que requieran login por pantalla (no API JWT).
     */
    protected boolean isSessionActiva() {
        return getSession().containsKey("usuarioWeb");
    }

    protected String getUsuarioSesion() {
        return (String) getSession().get("usuarioWeb");
    }
}

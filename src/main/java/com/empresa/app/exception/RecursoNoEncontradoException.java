package com.empresa.app.exception;

/**
 * Excepcion para recursos no encontrados (HTTP 404).
 */
public class RecursoNoEncontradoException extends RuntimeException {
    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}

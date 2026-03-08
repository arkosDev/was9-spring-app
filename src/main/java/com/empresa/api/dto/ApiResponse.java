package com.empresa.api.dto;

public class ApiResponse<T> {
    private boolean exito;
    private String mensaje;
    private T datos;

    public ApiResponse(boolean exito, String mensaje, T datos) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.datos = datos;
    }

    public static <T> ApiResponse<T> ok(T datos) {
        return new ApiResponse<T>(true, "OK", datos);
    }

    public static <T> ApiResponse<T> ok(String mensaje, T datos) {
        return new ApiResponse<T>(true, mensaje, datos);
    }

    public static <T> ApiResponse<T> error(String mensaje) {
        return new ApiResponse<T>(false, mensaje, null);
    }

    public boolean isExito() { return exito; }
    public String getMensaje() { return mensaje; }
    public T getDatos() { return datos; }
}

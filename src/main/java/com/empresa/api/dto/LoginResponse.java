package com.empresa.api.dto;

public class LoginResponse {
    private String token;
    private String tipo = "Bearer";
    private String username;
    private String rol;

    public LoginResponse(String token, String username, String rol) {
        this.token = token;
        this.username = username;
        this.rol = rol;
    }

    public String getToken() { return token; }
    public String getTipo() { return tipo; }
    public String getUsername() { return username; }
    public String getRol() { return rol; }
}

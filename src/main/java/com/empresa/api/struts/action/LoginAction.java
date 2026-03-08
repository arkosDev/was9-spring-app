package com.empresa.api.struts.action;

import com.empresa.api.model.Usuario;
import com.empresa.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Action de Struts 2 para login de la interfaz web (vistas JSP).
 * Mantiene sesion HTTP independiente del token JWT que usa Angular.
 *
 * Spring inyecta los beans gracias a struts2-spring-plugin
 * (struts.objectFactory = spring en struts.xml).
 */
@Component
public class LoginAction extends BaseAction {

    // Campos del formulario (Struts los bindea automaticamente)
    private String username;
    private String password;
    private String mensajeError;

    // Inyectado por Spring
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /** GET - muestra el formulario */
    @Override
    public String execute() {
        return INPUT;
    }

    /** POST - procesa el login */
    public String login() {
        try {
            Usuario usuario = usuarioRepository.findByUsername(username)
                .orElse(null);

            if (usuario == null || !passwordEncoder.matches(password, usuario.getPassword())) {
                mensajeError = "Usuario o contraseña incorrectos";
                addActionError(mensajeError);
                return ERROR;
            }

            // Guardar en sesion HTTP
            getSession().put("usuarioWeb", usuario.getUsername());
            getSession().put("rolWeb",     usuario.getRol());
            log.info("Login web exitoso: {}", username);
            return SUCCESS;

        } catch (Exception e) {
            log.error("Error en login web", e);
            mensajeError = "Error interno. Intente mas tarde.";
            addActionError(mensajeError);
            return ERROR;
        }
    }

    // Getters/Setters para binding de Struts
    public String getUsername()       { return username; }
    public void   setUsername(String u) { this.username = u; }
    public String getPassword()       { return password; }
    public void   setPassword(String p) { this.password = p; }
    public String getMensajeError()   { return mensajeError; }
}

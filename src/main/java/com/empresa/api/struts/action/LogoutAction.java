package com.empresa.api.struts.action;

import org.springframework.stereotype.Component;

@Component
public class LogoutAction extends BaseAction {

    @Override
    public String execute() {
        getSession().remove("usuarioWeb");
        getSession().remove("rolWeb");
        getSession().clear();
        log.info("Logout web ejecutado");
        return SUCCESS;
    }
}

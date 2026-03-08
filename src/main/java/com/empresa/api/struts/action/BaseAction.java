package com.empresa.api.struts.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public abstract class BaseAction extends ActionSupport implements SessionAware {

    protected final Logger log = LoggerFactory.getLogger(getClass());
    protected static final String NOT_FOUND = "notFound";

    private Map<String, Object> session;

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    protected Map<String, Object> getSession() {
        return session;
    }
}

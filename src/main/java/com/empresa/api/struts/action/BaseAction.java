package com.empresa.api.struts.action;

import com.opensymphony.xwork2.ActionSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseAction extends ActionSupport {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    protected static final String NOT_FOUND = "notFound";
}

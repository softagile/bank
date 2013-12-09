package com.softagile.bank.service;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

/**
 * 
 * @author bkalali
 *
 */
public interface ContextAware {

    @Context
    void setHttpHeaders(HttpHeaders httpHeaders);
}

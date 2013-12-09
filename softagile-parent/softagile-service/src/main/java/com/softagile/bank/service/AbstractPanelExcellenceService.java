package com.softagile.bank.service;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;

import com.softagile.bank.exception.MessageHeaderException;

/**
 * 
 * @author bkalali
 * 
 */
public abstract class AbstractPanelExcellenceService {

    private HttpHeaders httpHeaders;

    protected void initHttpHeaders(HttpHeaders httpHeaders) {
        if (null == httpHeaders) {
            throw new MessageHeaderException("Service has problem to set its httpHeaders context");
        }
        this.httpHeaders = httpHeaders;
    }

    protected boolean shouldValidate() {
        if (null != httpHeaders) {
            MultivaluedMap<String, String> headersMap = httpHeaders.getRequestHeaders();
            String validation = headersMap.getFirst("validation");
            return null != validation && headersMap.getFirst("validation").equals("yes");
        } else {
            return false;
        }
    }

    protected void processHeader() {
        try {
            if (null != httpHeaders) {
                //MessageHeaderMdcHelper.addHttpHeadersToMDC(httpHeaders);
            }
        } catch (IllegalArgumentException ex) {
            throw new MessageHeaderException(ex.getMessage());
        }
    }
}

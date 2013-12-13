package com.softagile.bank.rule.engine;

/**
 * 
 * @author bkalali
 *
 */
public class PanelExcellenceValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PanelExcellenceValidationException(String errors){
        super(errors);
    }
}

package com.softagile.bank.audit;

/**
 * The Interface EntityWithAuditableColumns...
 * 
 * @author bkalali
 */
public interface EntityWithAuditableColumns {
    
    public AuditableColumns getAuditableColumns();
    
    public void setAuditableColumns(AuditableColumns auditableColumns);
}

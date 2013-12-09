package com.softagile.bank.audit;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;


/**
 * The Interface AuditableColumnsEntityListener...
 * 
 * @author bkalali
 */
public class AuditableColumnsEntityListener {
    
    @PrePersist
    void onCreate(Object entity) {
        if(entity instanceof EntityWithAuditableColumns) {
            EntityWithAuditableColumns eact = (EntityWithAuditableColumns)entity;
            if(eact.getAuditableColumns() == null) {
                eact.setAuditableColumns(new AuditableColumns());
            }
            eact.getAuditableColumns().setDateCreated(new Timestamp((new Date()).getTime()));
        }
    }
    
    @PreUpdate
    void onUpdate(Object entity) {
        if(entity instanceof EntityWithAuditableColumns) {
            EntityWithAuditableColumns eact = (EntityWithAuditableColumns)entity;
            if(eact.getAuditableColumns() == null) {
                eact.setAuditableColumns(new AuditableColumns());
            }
            eact.getAuditableColumns().setDateModified(new Timestamp((new Date()).getTime()));
        }
    }
}

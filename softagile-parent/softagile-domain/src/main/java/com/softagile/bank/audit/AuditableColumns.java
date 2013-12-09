package com.softagile.bank.audit;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The AuditableColumns...
 * 
 * @author bkalali
 */
@Embeddable
public class AuditableColumns {
    
    @Column(name="UPDATE_TIME", insertable=false, updatable=true)
    private Timestamp dateModified;  
    
    @Column(name="CREATION_TIME", insertable=true, updatable=false)
    private Timestamp dateCreated;


    public Timestamp getDateModified() {
        return dateModified;
    }

    public void setDateModified(Timestamp dateModified) {
        this.dateModified = dateModified;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }
}

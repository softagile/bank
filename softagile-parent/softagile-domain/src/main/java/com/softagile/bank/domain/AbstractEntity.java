package com.softagile.bank.domain;

import javax.persistence.Embedded;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

import com.softagile.bank.audit.AuditableColumns;
import com.softagile.bank.audit.AuditableColumnsEntityListener;
import com.softagile.bank.audit.EntityWithAuditableColumns;

@MappedSuperclass
@EntityListeners(AuditableColumnsEntityListener.class)
public class AbstractEntity implements EntityWithAuditableColumns {

    @Id
    @GeneratedValue(generator="peSeq") 
    @SequenceGenerator(name="peSeq",sequenceName="PE_SEQ", allocationSize=5) 
    private Long id;

    @Embedded
    protected AuditableColumns auditableColumns;

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this.id == null || obj == null || !(this.getClass().equals(obj.getClass()))) {
            return false;
        }
        AbstractEntity that = (AbstractEntity) obj;
        return this.id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public AuditableColumns getAuditableColumns() {
        return auditableColumns;
    }

    @Override
    public void setAuditableColumns(AuditableColumns auditableColumns) {
        this.auditableColumns = auditableColumns;
    }
}

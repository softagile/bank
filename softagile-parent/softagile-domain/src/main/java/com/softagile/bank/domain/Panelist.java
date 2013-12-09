package com.softagile.bank.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "Panelist")
public class Panelist extends AbstractEntity{

    private String name;
    
    @OneToMany
    private Set<Panel> panels;
    
    public Panelist() {
      
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Panel> getPanels() {
        return panels;
    }

    public void setPanels(Set<Panel> panels) {
        this.panels = panels;
    }

}

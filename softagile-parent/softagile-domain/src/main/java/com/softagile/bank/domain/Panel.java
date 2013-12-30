package com.softagile.bank.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Panel")

public class Panel extends AbstractEntity {

    @ManyToOne(optional = true)
    private Company company;
    @ManyToOne(optional = true)
    private Currency currency;
    @ManyToOne(optional = true)
    private Country country;

    public Panel() {

    }

    public void update(Country country) {
        this.country = country;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}

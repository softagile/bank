package com.softagile.bank.domain;

import javax.persistence.Entity;



@Entity

public class Currency extends AbstractEntity {
	
	private String currencyCode;
	private String currencyDescription;
	private String isoCurrencySymbol;
	
	protected Currency(){
	    
	}
	
    public String getCurrencyCode() {
        return currencyCode;
    }
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
    public String getCurrencyDescription() {
        return currencyDescription;
    }
    public void setCurrencyDescription(String currencyDescription) {
        this.currencyDescription = currencyDescription;
    }

    public String getIsoCurrencySymbol() {
        return isoCurrencySymbol;
    }

    public void setIsoCurrencySymbol(String isoCurrencySymbol) {
        this.isoCurrencySymbol = isoCurrencySymbol;
    }

}

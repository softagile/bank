package com.softagile.bank.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Redemption")
public class Redemption extends AbstractEntity{

    private int maximumRedemptionPerDay;
    private int panelistBalanceThreshold;
    
    public Redemption() {
      
    }

    public int getMaximumRedemptionPerDay() {
        return maximumRedemptionPerDay;
    }

    public void setMaximumRedemptionPerDay(int maximumRedemptionPerDay) {
        this.maximumRedemptionPerDay = maximumRedemptionPerDay;
    }

    public int getPanelistBalanceThreshold() {
        return panelistBalanceThreshold;
    }

    public void setPanelistBalanceThreshold(int panelistBalanceThreshold) {
        this.panelistBalanceThreshold = panelistBalanceThreshold;
    }

}

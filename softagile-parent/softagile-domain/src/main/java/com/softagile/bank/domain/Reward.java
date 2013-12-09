package com.softagile.bank.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Reward")
public class Reward extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    private RewardType rewardType;

    @OneToOne
    private Country country;

    @ManyToOne
    @JoinColumn(name="partner_id")
    private Partner partner;

    public Reward() {

    }

    public RewardType getRewardType() {
        return rewardType;
    }

    public void setRewardType(RewardType rewardType) {
        this.rewardType = rewardType;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    // public Partner getPartner() {
    // return partner;
    // }

    // public void setPartner(Partner partner) {
    // this.partner = partner;
    // }

}

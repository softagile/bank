package com.softagile.bank.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Account")
public class Account extends AbstractEntity {

	@Enumerated(EnumType.STRING)
	private AccountType accountType;

	@Temporal(value = TemporalType.DATE)
	private Date openingDate;

	@ManyToMany(mappedBy = "accounts", cascade = CascadeType.ALL)
	private Set<Customer> customers;

	@Column(name = "amount")
	private BigDecimal amount;

	public Account(AccountType accountType) {
		this.accountType = accountType;
	}

	public Account() {
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public Date getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}

	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	public BigDecimal getAmount() {
		if(null==amount){
			amount= new BigDecimal(0);
		}
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void addAmount(BigDecimal savingAmount) {
		amount = getAmount().add(savingAmount);
	}

}

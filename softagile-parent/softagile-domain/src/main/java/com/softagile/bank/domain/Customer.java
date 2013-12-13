package com.softagile.bank.domain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customer extends AbstractEntity {

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "Customer_Account", joinColumns = { @JoinColumn(name = "CUST_ID", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "ACC_ID", referencedColumnName = "ID") })
	private Set<Account> accounts;

	private String name;

	private String familyName;

	private int age;

	public Customer() {

	}

	public Set<Account> getAccounts() {
		if (accounts == null) {
			accounts = new HashSet<Account>();
		}
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public BigDecimal getTotalAmount() {
		BigDecimal totalAsset = new BigDecimal(0);
		for (Account account : accounts) {
			totalAsset = totalAsset.add(account.getAmount());
		}
		return totalAsset;
	}

}

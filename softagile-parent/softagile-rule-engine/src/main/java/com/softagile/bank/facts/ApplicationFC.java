package com.softagile.bank.facts;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ApplicationFC {

	private boolean valid = true;
	private List<String> vaidationMessages = new ArrayList<String>();
	private int age;
	private BigDecimal totalMoneyAsset;

	public List<String> getVaidationMessages() {
		return vaidationMessages;
	}

	public int getAge() {
		return age;
	}

	public BigDecimal getTotalMoneyAsset() {
		if (null == totalMoneyAsset) {
			totalMoneyAsset = new BigDecimal(0);
		}
		return totalMoneyAsset;
	}

	public void setVaidationMessages(List<String> vaidationMessages) {
		this.vaidationMessages = vaidationMessages;
	}

	public void addValidationMessage(String vaidationMessage) {
		vaidationMessages.add(vaidationMessage);
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setTotalMoneyAsset(BigDecimal totalMoneyAsset) {
		this.totalMoneyAsset = totalMoneyAsset;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public void totalMoneyAsset(BigDecimal cachMoney) {
		totalMoneyAsset = getTotalMoneyAsset().add(cachMoney);
	}

}

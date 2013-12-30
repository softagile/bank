package com.softagile.bank.facts;

import java.math.BigDecimal;

/**
 * 
 * @author ITJ6921
 * 
 */
public class ApplicationFC {

	private boolean valid = true;

	private long customerId;
	private int age;
	private BigDecimal totalMoneyAsset;
	private ApplicantValidationStatus validationStatus = ApplicantValidationStatus.VALID;

	public int getAge() {
		return age;
	}

	public BigDecimal getTotalMoneyAsset() {
		if (null == totalMoneyAsset) {
			totalMoneyAsset = new BigDecimal(0);
		}
		return totalMoneyAsset;
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

	public String getApplicantValidationStatus() {
		return validationStatus.toString();
	}

	public void setApplicantValidationStatus(
			ApplicantValidationStatus applicantValidationStatus) {
		validationStatus = applicantValidationStatus;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setcustomerId(long customerId) {
		this.customerId = customerId;
	}

}

package com.softagile.bank.requestreply;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "applicantReply", namespace = "http://www.softagile.com/schema/bank/reply", propOrder = {
		"isValidApplicantForLoan", "validationMessage" })
public class ApplicantReply extends ReplyStatus {

	@XmlElement(namespace = "http://www.softagile.com/schema/bank/reply", required = true)
	private boolean isValidApplicantForLoan;
	@XmlElement(namespace = "http://www.softagile.com/schema/bank/reply")
	private List<String> validationMessage;
	
	@XmlElement(namespace = "http://www.softagile.com/schema/bank/reply")
	private long customerId;

	public boolean isValidApplicantForLoan() {
		return isValidApplicantForLoan;
	}

	public void setValidApplicantForLoan(boolean isValidApplicantForLoan) {
		this.isValidApplicantForLoan = isValidApplicantForLoan;
	}

	public List<String> getValidationMessage() {
		return validationMessage;
	}

	public void setValidationMessage(List<String> validationMessage) {
		this.validationMessage = validationMessage;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

}

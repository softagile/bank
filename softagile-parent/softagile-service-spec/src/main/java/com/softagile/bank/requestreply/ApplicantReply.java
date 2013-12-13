package com.softagile.bank.requestreply;

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
	private String validationMessage;
	
	@XmlElement(namespace = "http://www.softagile.com/schema/bank/reply")
	private long customerId;
	
	@XmlElement(namespace = "http://www.softagile.com/schema/bank/reply")
	private String status;

	public boolean isValidApplicantForLoan() {
		return isValidApplicantForLoan;
	}

	public void setValidApplicantForLoan(boolean isValidApplicantForLoan) {
		this.isValidApplicantForLoan = isValidApplicantForLoan;
	}

	public String getValidationMessage() {
		return validationMessage;
	}

	public void setValidationMessage(String validationMessage) {
		this.validationMessage = validationMessage;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

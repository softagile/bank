package com.softagile.bank.replyfactories;

import com.softagile.bank.facts.ApplicationFC;
import com.softagile.bank.requestreply.ApplicantReply;

public class ApplicantReplyFactory {
	
    public static ApplicantReply createApplicantReply(ApplicationFC applicationFC,long customerId) {
		ApplicantReply applicantReply =  new ApplicantReply();
		applicantReply.setValidApplicantForLoan(applicationFC.isValid());
		applicantReply.setValidationMessage(applicationFC.getApplicantValidationStatus());
		applicantReply.setCustomerId(customerId);
		applicantReply.setStatus(applicationFC.getApplicantValidationStatus().toString());
		return applicantReply;
    }
    
}

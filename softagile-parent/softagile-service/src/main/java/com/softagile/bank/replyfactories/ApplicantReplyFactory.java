package com.softagile.bank.replyfactories;

import com.softagile.bank.facts.ApplicationFC;
import com.softagile.bank.requestreply.ApplicantReply;
/**
 * 
 * @author ITJ6921
 *
 */
public class ApplicantReplyFactory {
	
    public static ApplicantReply createApplicantReply(ApplicationFC applicationFC) {
		ApplicantReply applicantReply =  new ApplicantReply();
		applicantReply.setValidApplicantForLoan(applicationFC.isValid());
		applicantReply.setValidationMessage(applicationFC.getApplicantValidationStatus());
		applicantReply.setCustomerId(applicationFC.getCustomerId());
		applicantReply.setStatus(applicationFC.getApplicantValidationStatus().toString());
		return applicantReply;
    }
    
}

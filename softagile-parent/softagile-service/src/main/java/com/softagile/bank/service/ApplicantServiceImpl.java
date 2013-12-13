package com.softagile.bank.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softagile.bank.api.service.ApplicantService;
import com.softagile.bank.exception.ExceptionHandler;
import com.softagile.bank.facts.ApplicationFC;
import com.softagile.bank.requestreply.ApplicantReply;
import com.softagile.bank.rule.engine.ApplicantValidator;

/**
 * 
 * @author bkalali
 *
 */
@Service("ApplicantService")
@Path("/Applicant")
public class ApplicantServiceImpl implements ApplicantService {

	@Autowired
	private ApplicantValidator applicantValidator;
	
	@Override
	@GET
	@Path("/loan/{customerId}")
	@Produces(MediaType.APPLICATION_JSON)
	@ExceptionHandler
	@Transactional(readOnly = true)
	public ApplicantReply canApplyForLoan(
			@PathParam("customerId") long customerId) {
		ApplicationFC applicationFC = applicantValidator.canApplyForLoan(customerId);
		ApplicantReply applicantReply =  new ApplicantReply();
		applicantReply.setValidApplicantForLoan(applicationFC.isValid());
		applicantReply.setValidationMessage(applicationFC.getVaidationMessages());
		applicantReply.setCustomerId(customerId);
		return applicantReply;
	}

}


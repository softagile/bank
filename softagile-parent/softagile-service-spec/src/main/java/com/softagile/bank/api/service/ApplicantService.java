package com.softagile.bank.api.service;

import com.softagile.bank.requestreply.ApplicantReply;

public interface ApplicantService {

	ApplicantReply canApplyForLoan(long customerId);
}

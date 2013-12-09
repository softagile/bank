package com.softagile.bank.rule.engine;

import com.softagile.bank.facts.ApplicationFC;

public interface ApplicantValidator {
	
	ApplicationFC canApplyForLoan(long customerId);

}

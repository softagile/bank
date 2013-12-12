package com.softagile.bank.rule.engine;

import java.util.Set;

import org.drools.runtime.StatelessKnowledgeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.softagile.bank.domain.Account;
import com.softagile.bank.domain.Customer;
import com.softagile.bank.facts.ApplicationFC;
import com.softagile.bank.repository.CustomerRepository;

@Service("ApplicantValidator")
public class ApplicantValidatorImpl implements ApplicantValidator {

	private CustomerRepository customerRepository;
	
	@Autowired
	private StatelessKnowledgeSession kSession;

	@Autowired
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public ApplicationFC canApplyForLoan(long customerId) {
		Customer customer = customerRepository.findOne(customerId);
		Set<Account> accounts = customer.getAccounts();
		ApplicationFC applicationFC = new ApplicationFC();
		for (Account account : accounts) {
			applicationFC.totalMoneyAsset(account.getAmount());
		}
		applicationFC.setAge(customer.getAge());
		kSession.execute(applicationFC);
		return applicationFC;
	}

}

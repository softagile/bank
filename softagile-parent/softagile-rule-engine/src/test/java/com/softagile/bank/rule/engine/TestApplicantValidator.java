package com.softagile.bank.rule.engine;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.softagile.bank.domain.Account;
import com.softagile.bank.domain.AccountType;
import com.softagile.bank.domain.Customer;
import com.softagile.bank.facts.ApplicationFC;
import com.softagile.bank.repository.CustomerRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:drools-context-test.xml" })
public class TestApplicantValidator {

	@Autowired
	private ApplicantValidatorImpl applicantValidator;

	private CustomerRepository customerRepository;

	@Before
	public void init() {
		customerRepository = createMock(CustomerRepository.class);
		applicantValidator.setCustomerRepository(customerRepository);
	}

	@After
	public void cleanup() {
		reset(customerRepository);
	}

	@Test
	public void testCustomerIsValidForLoan() {
        expect(customerRepository.findOne(1L)).andReturn(getCustomer(22,new BigDecimal(2345),new BigDecimal(567777))).anyTimes();
        replay(customerRepository);
        ApplicationFC applicationFC = applicantValidator.canApplyForLoan(1L);
		assertThat(true, is(applicationFC.isValid()));
	}
	
	@Test
	public void testCustomerIsNotValidForLoan() {
        expect(customerRepository.findOne(1L)).andReturn(getCustomer(16,new BigDecimal(2345),new BigDecimal(567777))).anyTimes();
        replay(customerRepository);
        ApplicationFC applicationFC = applicantValidator.canApplyForLoan(1L);
		assertThat(false, is(applicationFC.isValid()));
		assertThat("customer age is=16 and needs to be more than 18 and total monetory is=570122 and need to be  more than $10000.00",is(applicationFC.getVaidationMessages().get(0)));
	}

	private Customer getCustomer(int age, BigDecimal checkingAmount,
			BigDecimal savingAmount) {
		Customer customer = new Customer();
		customer.setAge(age);
		Account account1 = new Account(AccountType.SAVING);
		account1.addAmount(savingAmount);
		account1.setOpeningDate(new Date());
		customer.getAccounts().add(account1);

		Account account2 = new Account(AccountType.CHECKING);
		customer.getAccounts().add(account2);
		account2.addAmount(checkingAmount);
		customer.getAccounts().add(account2);
		return customer;
	}
}

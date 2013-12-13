package com.softagile.bank.service;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import com.softagile.bank.api.service.ApplicantService;
import com.softagile.bank.domain.Account;
import com.softagile.bank.domain.Customer;
import com.softagile.bank.repository.CustomerRepository;
import com.softagile.bank.requestreply.ApplicantReply;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * 
 * @author ITJ6921
 *
 */
public class ApplicantServiceFeature {

	@Autowired
	private ApplicantService applicantService;

	@Autowired
	private CustomerRepository customerRepository;

	private Customer eligibleCustomer;

	private Customer nonEligibleCustomer;

	@Before
	public void intFeature() throws SQLException {
		assertThat(applicantService, is(notNullValue()));
	}

	@Given("^first customer with id (.*) exists$")
	public void aCustomerWithIDExists(long id) {
		eligibleCustomer = new Customer();
		eligibleCustomer.setId(id);
		eligibleCustomer = customerRepository.save(eligibleCustomer);
		assertThat(eligibleCustomer, is(notNullValue()));
	}

	@When("^age is (\\d+)$")
	public void age_is(int age) throws Throwable {
		eligibleCustomer.setAge(age);
		customerRepository.save(eligibleCustomer);
	}

	@And("^total monetary asset is (\\d+).(\\d+)$")
	public void total_monetary_asset_is_(int arg1, int arg2) {
		BigDecimal givenAmountFromFeatureFile = getGivenAmount(arg1, arg2);
		Account account = new Account();
		account.setAmount(givenAmountFromFeatureFile);
		eligibleCustomer.getAccounts().add(account);
		customerRepository.save(eligibleCustomer);
	}

	@Then("^david can apply for loan$")
	public void customer_can_apply_for_loan() {
		ApplicantReply applicantReply = applicantService
				.canApplyForLoan(eligibleCustomer.getId());
		assertThat(applicantReply.isValidApplicantForLoan(), is(true));
		assertThat(applicantReply.getStatus(), is("VALID"));
		
	}

	@Given("^second customer with id (\\d+) exists$")
	public void a_non_valid_customer_with_id_exists(long id) throws Throwable {
		nonEligibleCustomer = new Customer();
		nonEligibleCustomer.setId(id);
		nonEligibleCustomer = customerRepository.save(nonEligibleCustomer);
		assertThat(nonEligibleCustomer, is(notNullValue()));
	}

	@When("^second valid age is (\\d+)$")
	public void second_valid_age_is(int age) throws Throwable {
		nonEligibleCustomer.setAge(age);
		customerRepository.save(nonEligibleCustomer);
	}

	@When("^second monetary asset is (\\d+).(\\d+)$")
	public void second_monetary_asset_is_(int arg1, int arg2)
			throws Throwable {
		BigDecimal givenAmountFromFeatureFile = getGivenAmount(arg1, arg2);
		Account account = new Account();
		account.setAmount(givenAmountFromFeatureFile);
		nonEligibleCustomer.getAccounts().add(account);
		customerRepository.save(nonEligibleCustomer);
	}

	@Then("^customer john cannnot apply for loan$")
	public void customer_cannnot_apply_for_loan() throws Throwable {
		ApplicantReply applicantReply = applicantService
				.canApplyForLoan(nonEligibleCustomer.getId());
		assertThat(applicantReply.isValidApplicantForLoan(), is(false));
		assertThat(applicantReply.getStatus(), is("MONEY_NOT_VALID"));
	}

	@Given("^third customer with id (\\d+) exists$")
	public void third_customer_with_id_exists(long id) throws Throwable {
		nonEligibleCustomer = new Customer();
		nonEligibleCustomer.setId(id);
		nonEligibleCustomer = customerRepository.save(nonEligibleCustomer);
		assertThat(nonEligibleCustomer, is(notNullValue()));
	}

	@When("^thrird valid age is (\\d+)$")
	public void thrird_valid_age_is(int age) throws Throwable {
		nonEligibleCustomer.setAge(age);
		customerRepository.save(nonEligibleCustomer);
	}

	@When("^thrird monetary asset is (\\d+).(\\d+)$")
	public void total_monetary_asset_fo_is_(int arg1, int arg2) throws Throwable {
		BigDecimal givenAmountFromFeatureFile = getGivenAmount(arg1, arg2);
		Account account = new Account();
		account.setAmount(givenAmountFromFeatureFile);
		nonEligibleCustomer.getAccounts().add(account);
		customerRepository.save(nonEligibleCustomer);
	}

	@Then("^customer bob cannnot apply for loan$")
	public void customer_bob_cannnot_apply_for_loan() throws Throwable {
		ApplicantReply applicantReply = applicantService
				.canApplyForLoan(nonEligibleCustomer.getId());
		assertThat(applicantReply.isValidApplicantForLoan(), is(false));
		assertThat(applicantReply.getStatus(), is("AGE_NOT_VALID"));
	}
	
	@Given("^forth customer with id (\\d+) exists$")
	public void forth_customer_with_id_exists(long id) throws Throwable {
		nonEligibleCustomer = new Customer();
		nonEligibleCustomer.setId(id);
		nonEligibleCustomer = customerRepository.save(nonEligibleCustomer);
		assertThat(nonEligibleCustomer, is(notNullValue()));
	}

	@When("^forth valid age is (\\d+)$")
	public void forth_valid_age_is(int age) throws Throwable {
		nonEligibleCustomer.setAge(age);
		customerRepository.save(nonEligibleCustomer);
	}

	@When("^forth monetary asset is (\\d+).(\\d+)$")
	public void forth_monetary_asset_is_(int arg1, int arg2) throws Throwable {
		BigDecimal givenAmountFromFeatureFile = getGivenAmount(arg1, arg2);
		Account account = new Account();
		account.setAmount(givenAmountFromFeatureFile);
		nonEligibleCustomer.getAccounts().add(account);
		customerRepository.save(nonEligibleCustomer);
	}

	@Then("^customer joe cannnot apply for loan$")
	public void customer_joe_cannnot_apply_for_loan() throws Throwable {
		ApplicantReply applicantReply = applicantService
				.canApplyForLoan(nonEligibleCustomer.getId());
		assertThat(applicantReply.isValidApplicantForLoan(), is(false));
		assertThat(applicantReply.getStatus(), is("AGE_AND_MONEY_NOT_VALID"));
	}
	private BigDecimal getGivenAmount(int arg1, int arg2) {
		StringBuffer sb = new StringBuffer();
		sb.append(arg1);
		sb.append(".");
		sb.append(arg2);
		BigDecimal testData = new BigDecimal(sb.toString());
		return testData;
	}

}

package com.softagile.bank.service;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import com.softagile.bank.api.service.ApplicantService;
import com.softagile.bank.domain.Customer;
import com.softagile.bank.repository.CustomerRepository;
import com.softagile.bank.requestreply.ApplicantReply;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ApplicantServiceFeature {

	@Autowired
	private ApplicantService applicantService;

	@Autowired
	private CustomerRepository customerRepository;

	private Customer validCustomer;

	private Customer nonValidCustomer;

	@Before
	public void intFeature() throws SQLException {
		assertThat(applicantService, is(notNullValue()));
	}

	@Given("^a customer with id (.*) exists$")
	public void aCustomerWithIDExists(long id) {
		validCustomer = customerRepository.findOne(id);
		assertThat(validCustomer, is(notNullValue()));
	}

	@When("^age is (\\d+)$")
	public void age_is(int age) throws Throwable {
		assertThat(validCustomer.getAge(), is(age));
	}

	@And("^total monetary asset is (\\d+).(\\d+)$")
	public void total_monetary_asset_is_(int arg1, int arg2) {
		BigDecimal givenAmountFromFeatureFile = getGivenAmount(arg1, arg2);
		BigDecimal totalAsset = validCustomer.getTotalAmount();
		assertThat(totalAsset, lessThan(givenAmountFromFeatureFile));
	}

	@Then("^customer can apply for loan$")
	public void customer_can_apply_for_loan() {
		ApplicantReply applicantReply = applicantService
				.canApplyForLoan(validCustomer.getId());
		assertThat(applicantReply, is(notNullValue()));
	}

	@Given("^a non valid customer with id (\\d+) exists$")
	public void a_non_valid_customer_with_id_exists(long id) throws Throwable {
		nonValidCustomer = customerRepository.findOne(id);
		assertThat(nonValidCustomer, is(notNullValue()));
	}

	@When("^non valid age is (\\d+)$")
	public void non_valid_age_is(int age) throws Throwable {
		assertThat(nonValidCustomer.getAge(), is(age));
	}

	@When("^non total monetary asset is (\\d+).(\\d+)$")
	public void non_total_monetary_asset_is_(int arg1, int arg2)
			throws Throwable {
		BigDecimal totalAsset = nonValidCustomer.getTotalAmount();
		assertThat(totalAsset, lessThan(new BigDecimal(1000)));
	}

	private BigDecimal getGivenAmount(int arg1, int arg2) {
		StringBuffer sb= new StringBuffer();
		sb.append(arg1);
		sb.append(".");
		sb.append(arg2);
		BigDecimal testData = new BigDecimal(sb.toString());
		return testData;
	}

	@Then("^customer cannnot apply for loan$")
	public void customer_cannnot_apply_for_loan() throws Throwable {
		ApplicantReply applicantReply = applicantService
				.canApplyForLoan(nonValidCustomer.getId());
		assertThat(applicantReply, is(notNullValue()));
		assertThat(
				applicantReply.getValidationMessage().get(0),
				is("customer age is=16 and needs to be more than 18 and total monetory is=100.45 and need to be  more than $10000.00"));
	}
}

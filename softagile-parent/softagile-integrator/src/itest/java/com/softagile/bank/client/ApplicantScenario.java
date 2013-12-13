package com.softagile.bank.client;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.softagile.bank.requestreply.ApplicantReply;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;


public class ApplicantScenario {

	private ApplicantReply applicantReply;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Given("^customer1 with id (\\d+) exists$")
	public void customer1_with_id_exists(long countryId) throws Throwable {
		ApplicantReply applicantReply = getApplicantReply(countryId);
		assertThat(applicantReply, is(notNullValue()));
	}

	@Then("^one can apply for loan$")
	public void one_can_apply_for_loan() throws Throwable {
		assertThat(applicantReply.isValidApplicantForLoan(), is(true));
	}

	@Given("^customer2 with id (\\d+) exists$")
	public void customer2_with_id_exists(long countryId) throws Throwable {
		ApplicantReply applicantReply = getApplicantReply(countryId);
		assertThat(applicantReply, is(notNullValue()));
	}

	@Then("^one cannnot apply for loan$")
	public void one_cannnot_apply_for_loan() throws Throwable {
		assertThat(applicantReply.isValidApplicantForLoan(), is(false));
	}

	private ApplicantReply getApplicantReply(long customerId) throws JsonParseException, JsonMappingException
			{
		WebClient client = WebClient.create(EmbeddedTomcatTestSetup.REST_URI)
				.headers(HttpHeaders.getHttpHeaders());
		Response response = client.path("/Applicant/loan/").path(customerId)
				.accept(MediaType.APPLICATION_JSON).get();
		InputStream inputStream = (InputStream) response.getEntity();
		try {
			applicantReply = objectMapper.readValue(inputStream,
					ApplicantReply.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return applicantReply;
	}

}

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

import com.softagile.bank.requestreply.CountryReply;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CountryScenario {

    private CountryReply countryReply;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Given("^A country code for (.*) exists$")
    public void acountryCodeExist(String countryCode) {
        countryReply = getCountryReply(countryCode);
        assertThat(countryReply, is(notNullValue()));
    }

    @Then("^The (.*) is ISO country code$")
    public void theIsoCountryCode(String isoCountryCode) {
        assertThat(countryReply.getIsoCountryCode(), is(isoCountryCode));
    }

    public CountryReply getCountryReply(String countryCode) {
        WebClient client = WebClient.create(EmbeddedTomcatTestSetup.REST_URI).headers(HttpHeaders.getHttpHeaders());
        Response response = client.path("/PanelExcellence/Country/").path(countryCode)
            .accept(MediaType.APPLICATION_JSON).get();
        InputStream inputStream = (InputStream) response.getEntity();
        CountryReply countryReply = null;
        try {
            countryReply = objectMapper.readValue(inputStream, CountryReply.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return countryReply;
    }

}

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

import com.softagile.bank.requestreply.PanelReply;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class PanelScenario {

    private PanelReply panelReply;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Given("^A panel country code (.*) exists$")
    public void aPanelCountryCode(String countryCode) {
        panelReply = getPanelReplyByQuotaCellId(countryCode);
        assertThat(panelReply, is(notNullValue()));
    }

    @Then("^The panel list size is (\\d+)$")
    public void thePanelListSizeIs(int currentValue) {
        assertThat(panelReply.getPanelIds().size(), is(currentValue));
    }

    public PanelReply getPanelReplyByQuotaCellId(String countryCode) {
        WebClient client = WebClient.create(EmbeddedTomcatTestSetup.REST_URI).headers(HttpHeaders.getHttpHeaders());
        Response response = client.path("/PanelExcellence/Panel/").path(countryCode).accept(MediaType.APPLICATION_JSON)
            .get();
        InputStream inputStream = (InputStream) response.getEntity();
        PanelReply panelReply = null;
        try {
            panelReply = objectMapper.readValue(inputStream, PanelReply.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return panelReply;
    }
}

package com.softagile.bank.service;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.softagile.bank.api.service.PanelExcellenceService;
import com.softagile.bank.domain.Panel;
import com.softagile.bank.requestreply.PanelReply;

import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class PanelistsForCountryFeature {

    @Autowired
    private PanelExcellenceService panelExcellenceService;
    private PanelReply panelReply;

    @Autowired
    private DataSource dataSource;

    @Before
    public void intFeature() throws SQLException {
        assertThat(panelExcellenceService, is(notNullValue()));
    }

    @Given("^A country code (.*) exists$")
    public void acountryCode(String countryCode) {
        assertThat(countryCode, is("USA"));
        panelReply = panelExcellenceService.getPanelReplyByCountryCode(countryCode);
        assertThat(panelReply, is(notNullValue()));
    }

    @Then("^The panel list has size of (\\d+)$")
    public void thePanelListHasSizeOf(int initialValue) {
        assertThat(panelReply.getPanelIds().size(), is(2));
    }

    @Then("^The following panels exist:$")
    public void theFollowingPanelsExist(DataTable expectedPanels) throws Throwable {
        List<Panel> panels = expectedPanels.asList(Panel.class);
        assertThat(panels, is(notNullValue()));
        List<Long> actualIds = new ArrayList<Long>();
        List<Long> panelIds = panelReply.getPanelIds();
        actualIds.add(panelIds.get(0));
        actualIds.add(panelIds.get(1));
        List<Long> excepctedIds = new ArrayList<Long>();
        excepctedIds.add(panels.get(0).getId());
        excepctedIds.add(panels.get(1).getId());
        assertThat(actualIds, is(excepctedIds));
    }

}
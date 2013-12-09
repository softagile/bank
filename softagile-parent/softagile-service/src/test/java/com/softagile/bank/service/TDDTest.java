package com.softagile.bank.service;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.cxf.jaxrs.impl.MetadataMap;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.softagile.bank.domain.Country;
import com.softagile.bank.domain.Panel;
import com.softagile.bank.repository.CountryRepository;
import com.softagile.bank.repository.PanelRepository;
import com.softagile.bank.requestreply.CountryReply;
import com.softagile.bank.requestreply.PanelReply;
import com.softagile.bank.rule.engine.PanelExcellenceValidationEngine;
import com.softagile.bank.rule.engine.PanelExcellenceValidationException;
import com.softagile.bank.service.PanelExcellenceServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/app-context-test.xml" })
public class TDDTest {

    // The goal in here is to unit test panelExcellenceService W/O going to database or
    // calling external system

    private PanelRepository panelRepository;

    private CountryRepository countryRepository;

    private PanelExcellenceValidationEngine validationEngine;

    private HttpHeaders httpHeaders;

    @Autowired
    private PanelExcellenceServiceImpl panelExcellenceService;

    @Before
    public void setup() {
        panelRepository = createMock(PanelRepository.class);
        panelExcellenceService.setPanelRepository(panelRepository);

        countryRepository = createMock(CountryRepository.class);
        panelExcellenceService.setCountryRepository(countryRepository);

        validationEngine = createMock(PanelExcellenceValidationEngine.class);
        panelExcellenceService.setPanelExcellenceValidationEngine(validationEngine);

        httpHeaders = createMock(HttpHeaders.class);
        panelExcellenceService.setHttpHeaders(httpHeaders);

    }

    @After
    public void cleanup() {
        reset(panelRepository);
        reset(countryRepository);
        reset(validationEngine);
        reset(httpHeaders);
    }

    @Test
    public void testPanelExcellenceServiceIsNotNull() {
        assertThat(panelExcellenceService, is(notNullValue()));
    }

    @Test
    public void testGetPanalistIds() throws JsonParseException, JsonMappingException, IOException {
        assertThat(panelExcellenceService, is(notNullValue()));
        List<Panel> panelists = new ArrayList<Panel>();
        Panel pe1 = new Panel();
        pe1.setId(1L);
        Panel pe2 = new Panel();
        pe2.setId(2L);
        panelists.add(pe1);
        panelists.add(pe2);

        MultivaluedMap<String, String> headers = getHttpHeaders();
        headers.add("validation", "no");
        expect(httpHeaders.getRequestHeaders()).andReturn(getHttpHeaders()).anyTimes();
        expect(panelRepository.findByCountryCountryCode("USA")).andReturn(panelists);
        replay(panelRepository, httpHeaders);
        validationEngine.validateCountryCode("USA");
        PanelReply panelReply = panelExcellenceService.getPanelReplyByCountryCode("USA");
        assertThat(panelReply, is(notNullValue()));
        List<Long> panalistIds = panelReply.getPanelIds();
        assertThat(panalistIds, hasSize(2));
        assertThat(panalistIds, containsInAnyOrder(1L, 2L));
        verify(panelRepository, httpHeaders);
    }

    @Test
    public void testCountryRepository() throws JsonParseException, JsonMappingException, IOException {
        assertThat(panelExcellenceService, is(notNullValue()));
        List<Panel> panelists = new ArrayList<Panel>();
        Panel pe1 = new Panel();
        pe1.setId(10223l);
        Panel pe2 = new Panel();
        pe2.setId(144223l);
        panelists.add(pe1);
        panelists.add(pe2);
        Country country = new Country();
        country.setIsoCountryCode("US");
        expect(countryRepository.findCountryByCountryCode("USA")).andReturn(country);
        MultivaluedMap<String, String> headers = getHttpHeaders();
        headers.add("validation", "no");
        expect(httpHeaders.getRequestHeaders()).andReturn(getHttpHeaders()).anyTimes();
        replay(countryRepository, httpHeaders);
        CountryReply countryReply = panelExcellenceService.getCountryReplyByCounytryCode("USA");
        assertThat(countryReply, is(notNullValue()));
        assertThat(countryReply.getIsoCountryCode(), is("US"));
        verify(countryRepository, httpHeaders);
    }

    @Test
    public void testFindCountryByCountryCodeWhenPanelExcellenceDataAccessExceptionThrown() {
        expect(countryRepository.findCountryByCountryCode("ghjghj")).andThrow(
            new DataRetrievalFailureException("NoN-Rtraible-Exception"));
        MultivaluedMap<String, String> headers = getHttpHeaders();
        headers.add("validation", "no");
        expect(httpHeaders.getRequestHeaders()).andReturn(getHttpHeaders()).anyTimes();
        replay(countryRepository, httpHeaders);
        CountryReply countryReply = panelExcellenceService.getCountryReplyByCounytryCode("ghjghj");
        assertThat(countryReply, is(notNullValue()));
        verify(countryRepository, httpHeaders);
    }

    @Test
    public void testByCountryCountryCodeWhenPanelExcellenceDataAccessExceptionThrown() {
        expect(panelRepository.findByCountryCountryCode("ghjghj")).andThrow(
            new PessimisticLockingFailureException("Retriable Exception")).anyTimes();
        MultivaluedMap<String, String> headers = getHttpHeaders();
        headers.add("validation", "no");
        expect(httpHeaders.getRequestHeaders()).andReturn(getHttpHeaders()).anyTimes();
        replay(panelRepository, httpHeaders);
        PanelReply panelReply = panelExcellenceService.getPanelReplyByCountryCode("ghjghj");
        assertThat(panelReply, is(notNullValue()));
        verify(panelRepository, httpHeaders);
    }

    @Test
    public void testWhenCountryCodeIsNotValid() {
        validationEngine.validateCountryCode("ghjghj");
        expectLastCall().andThrow(new PanelExcellenceValidationException("country name ghjghj is not valid"));
        expect(httpHeaders.getRequestHeaders()).andReturn(getHttpHeaders()).anyTimes();
        replay(validationEngine, httpHeaders);
        PanelReply panelReply = panelExcellenceService.getPanelReplyByCountryCode("ghjghj");
        assertThat(panelReply, is(notNullValue()));
        assertThat(panelReply.getDescription(), is("country name ghjghj is not valid"));
        verify(validationEngine, httpHeaders);
    }

    @Test
    public void testWhenCountryCodeIsNull() {
        validationEngine.validateCountryCode(null);
        expectLastCall().andThrow(new PanelExcellenceValidationException("country name cannot be null or empty"));
        expect(httpHeaders.getRequestHeaders()).andReturn(getHttpHeaders()).anyTimes();
        replay(validationEngine, httpHeaders);
        PanelReply panelReply = panelExcellenceService.getPanelReplyByCountryCode(null);
        assertThat(panelReply, is(notNullValue()));
        assertThat(panelReply.getDescription(), is("country name cannot be null or empty"));
        verify(validationEngine, httpHeaders);
    }

    public MultivaluedMap<String, String> getHttpHeaders() {
        MultivaluedMap<String, String> headers = new MetadataMap<String, String>();
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        DateTime dt = new DateTime();
        DateTimeFormatter fmt = ISODateTimeFormat.dateTime();
        String originationTimestamp = fmt.print(dt);
        headers.add("SERVICE_ENV_FIELD", "DEV");
        headers.add("SERVICE_FUNC_AREA_FIELD", "PE");
        headers.add("TRACKING_ID_FIELD", randomUUIDString);
        headers.add("ORIG_TIMESTAMP_FIELD", originationTimestamp);
        headers.add("SRC_PROGRAM_FIELD", "HIPPO_CMS");
        headers.add("HEADER_VERSION_FIELD", "1.1.0");
        headers.add("validation", "yes");
        return headers;
    }
}

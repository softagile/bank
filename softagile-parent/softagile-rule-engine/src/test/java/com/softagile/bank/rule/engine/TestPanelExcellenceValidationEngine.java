package com.softagile.bank.rule.engine;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.softagile.bank.domain.Country;
import com.softagile.bank.repository.CountryRepository;
import com.softagile.bank.rule.engine.PanelExcellenceValidationEngineImpl;
import com.softagile.bank.rule.engine.PanelExcellenceValidationException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:drools-context-test.xml" })
public class TestPanelExcellenceValidationEngine {

    @Autowired
    private PanelExcellenceValidationEngineImpl panelExcellenceRulesEngine;

    @Autowired
    private CountryRepository countryRepository;
    
    @Before
    public void populateCountryCodes() {
        countryRepository = createMock(CountryRepository.class);
        panelExcellenceRulesEngine.setCountryRepository(countryRepository);
    }

    @After
    public void cleanup() {
        reset(countryRepository);
    }

    @Test
    @Transactional
    public void testCountryCodeIsValid() {
        expect(countryRepository.findAll()).andReturn(getValidCountries()).anyTimes();
        replay(countryRepository);
        try {
            panelExcellenceRulesEngine.validateCountryCode("USA");
            panelExcellenceRulesEngine.validateCountryCode("CAD");
            panelExcellenceRulesEngine.validateCountryCode("GRC");
        } catch (PanelExcellenceValidationException ex) {
            fail();
        }
    }

    @Test
    public void testCountryCodeIsNotValid() { 
        expect(countryRepository.findAll()).andReturn(getValidCountries()).anyTimes();
        replay(countryRepository);
        try {
            panelExcellenceRulesEngine.validateCountryCode("SAS");
            fail();
        } catch (PanelExcellenceValidationException ex) {
            assertThat("country name SAS is not valid", is(ex.getMessage()));
        }
    }

    @Test
    public void testCountryCodeIsNull() {
        expect(countryRepository.findAll()).andReturn(getValidCountries()).anyTimes();
        replay(countryRepository);
        try {
            panelExcellenceRulesEngine.validateCountryCode(null);
            fail();
        } catch (PanelExcellenceValidationException ex) {
            assertThat("country name cannot be null or empty", is(ex.getMessage()));
        }
    }

    
    private List<Country> getValidCountries(){
        Country usa = new Country();
        usa.setCountryCode("USA");
        usa.setDescription("USA country");

        Country cad = new Country();
        cad.setCountryCode("CAD");
        cad.setDescription("CAD country");

        Country grc = new Country();
        grc.setCountryCode("GRC");
        grc.setDescription("GRC country");
        
        List<Country> countries= new ArrayList<Country>();
        countries.add(usa);
        countries.add(cad);
        countries.add(grc);
        return countries;
    }
}

package com.softagile.bank.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softagile.bank.api.service.PanelExcellenceService;
import com.softagile.bank.domain.Country;
import com.softagile.bank.domain.Panel;
import com.softagile.bank.exception.ExceptionHandler;
import com.softagile.bank.replyfactories.CountryReplyFactory;
import com.softagile.bank.replyfactories.PanelReplyFactory;
import com.softagile.bank.repository.CountryRepository;
import com.softagile.bank.repository.PanelRepository;
import com.softagile.bank.requestreply.CountryReply;
import com.softagile.bank.requestreply.PanelReply;
import com.softagile.bank.rule.engine.PanelExcellenceValidationEngine;

@Service("PanelExcellenceService")
@Path("/PanelExcellence")
public class PanelExcellenceServiceImpl extends AbstractPanelExcellenceService implements PanelExcellenceService,
    ContextAware {

    private PanelRepository panelRepository;

    private CountryRepository countryRepository;

    private PanelExcellenceValidationEngine validationEngine;

    @Override
    @GET
    @Path("/Panel/{countryCode}")
    @Produces(MediaType.APPLICATION_JSON)
    @ExceptionHandler
    @Transactional(readOnly = true)
    public PanelReply getPanelReplyByCountryCode(@PathParam("countryCode") String countryCode) {
        processHeader();
        if (shouldValidate()) {
            validationEngine.validateCountryCode(countryCode);
        }
        List<Panel> panelists = panelRepository.findByCountryCountryCode(countryCode);
        return PanelReplyFactory.createPanelReply(panelists);
    }

    @Override
    @GET
    @Path("/Country/{countryCode}")
    @Produces(MediaType.APPLICATION_JSON)
    @ExceptionHandler
    @Transactional(readOnly = true)
    public CountryReply getCountryReplyByCounytryCode(@PathParam("countryCode") String countryCode) {
        processHeader();
        if (shouldValidate()) {
            validationEngine.validateCountryCode(countryCode);
        }
        Country country = countryRepository.findCountryByCountryCode(countryCode);
        return CountryReplyFactory.createCountryReply(country);
    }

    @Override
    @POST
    @Path("/Countrycreate/{countryCode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @ExceptionHandler
    public CountryReply createCountry(@PathParam("countryCode") String countryCode) {
        processHeader();
        if (shouldValidate()) {
            validationEngine.validateCountryCode(countryCode);
        }
        Country country = new Country();
        country.setCountryCode(countryCode);
        country.setDescription(countryCode);
        country.setIsoCountryCode(countryCode);
        country = countryRepository.saveAndFlush(country);
        return CountryReplyFactory.createCountryReply(country);
    }

    @Autowired
    public void setPanelRepository(PanelRepository panelRepository) {
        this.panelRepository = panelRepository;
    }

    @Autowired
    public void setCountryRepository(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Autowired
    public void setPanelExcellenceValidationEngine(PanelExcellenceValidationEngine validationEngine) {
        this.validationEngine = validationEngine;
    }

    @Override
    public void setHttpHeaders(HttpHeaders httpHeaders) {
        initHttpHeaders(httpHeaders);
    }

}

package com.softagile.bank.rule.engine;

import java.util.ArrayList;
import java.util.List;

import org.drools.runtime.StatelessKnowledgeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softagile.bank.domain.Country;
import com.softagile.bank.facts.CountryFC;
import com.softagile.bank.repository.CountryRepository;

/**
 * 
 * @author bkalali
 * 
 */
@Service("PanelExcellenceValidationEngine")
public class PanelExcellenceValidationEngineImpl implements PanelExcellenceValidationEngine {

    @Autowired
    private StatelessKnowledgeSession kSession;

    private CountryRepository countryRepository;

    @Autowired
    public void setCountryRepository(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public void validateCountryCode(String countryCode) {
        kSession.setGlobal("countryCodes", loadAllCountryCodes());
        CountryFC countryFC = new CountryFC();
        countryFC.setName(countryCode);
        kSession.execute(countryFC);
        List<String> errors = countryFC.getErrorMessages();
        StringBuffer errorDescription = new StringBuffer();
        if (!countryFC.isValid()) {
            for (String error : errors) {
                errorDescription.append(error);
            }
            throw new PanelExcellenceValidationException(errorDescription.toString());
        }
    }

    public List<String> loadAllCountryCodes() {
        List<String> countryCodes = new ArrayList<String>();
        Iterable<Country> countries = countryRepository.findAll();
        for (Country country : countries) {
            countryCodes.add(country.getCountryCode());
        }
        return countryCodes;
    }
}

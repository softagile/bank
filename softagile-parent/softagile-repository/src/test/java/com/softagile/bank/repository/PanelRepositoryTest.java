package com.softagile.bank.repository;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.softagile.bank.audit.AuditableColumns;
import com.softagile.bank.db.embedded.ApplicationEmbeddedConfig;
import com.softagile.bank.domain.Company;
import com.softagile.bank.domain.Country;
import com.softagile.bank.domain.Panel;

@ContextConfiguration(classes = ApplicationEmbeddedConfig.class)
public class PanelRepositoryTest extends HSQLDatabasePopulator {

    // TODO typically these two repository will be in a facade
    // service and both of them run within the same transaction
    @Autowired
    private PanelRepository panelRepository;

    @Autowired
    private CountryRepository countryRepository;
    
    @Autowired
    private CompanyRepository companyRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testGetPanels() {
        Iterable<Panel> panels = panelRepository.findAll();
        assertThat(panels, is(notNullValue()));
    }

    @Test(expected = ConstraintViolationException.class)
    public void testCreateCountry() {
        Country country = new Country();
        country = countryRepository.save(country);
    }

    @Test
    public void testFindPanelsByCountryId() {
        Country country = countryRepository.findOne(1L);
        List<Panel> panel = panelRepository.findAllByCountry(country);
        assertThat(panel, is(notNullValue()));
        assertThat(panel, is(hasSize(2)));
    }

    @Test
    public void testFindPanelsByCountry() {
        Country country = countryRepository.findCountryByCountryCode("USA");
        List<Panel> panel = panelRepository.findAllByCountry(country);
       
        Company company = companyRepository.findCompanyByCompanyName("IBM");
        panel= panelRepository.findByCompany(company);
        
        assertThat(panel, is(notNullValue()));
        assertThat(panel, is(hasSize(2)));
    }
    
    @Test
    public void testFindPanelsByCompanyName() {
        
        Company company = companyRepository.findCompanyByCompanyName("IBM");
        List<Panel> panels = panelRepository.findByCompany(company);
        
        assertThat(panels, is(notNullValue()));
        assertThat(panels, is(hasSize(2)));
    }

    @Test
    public void testFindPanelsByCountryCode() {
        List<Panel> panel = panelRepository.findByCountryCountryCode("USA");
        assertThat(panel, is(notNullValue()));
        assertThat(panel, is(hasSize(2)));
    }

    @Test
    @Transactional
    public void testAuditableColumns() throws InterruptedException {
        Panel panel = new Panel();
        panelRepository.save(panel);
        Country country = new Country();
        country.setDescription("Country code of USA");
        country.setCountryCode("US");
        country = countryRepository.save(country);
        assertThat(country.getAuditableColumns().getDateCreated(), is(notNullValue()));
        panel.setCountry(country);
        entityManager.merge(panel);
        Thread.sleep(1000);
        entityManager.flush();
        AuditableColumns auditableColumns = panel.getAuditableColumns();
        Timestamp createdDate = auditableColumns.getDateCreated();
        Timestamp modifiedDate = auditableColumns.getDateModified();
        assertThat(modifiedDate.getNanos(), greaterThan(createdDate.getNanos()));
    }

    @Test
    public void testFindPanelsByIsoCountryCode() {
        Country country = countryRepository.findCountryByIsoCountryCode("US");
        List<Panel> panel = panelRepository.findAllByCountry(country);
        assertThat(panel, is(notNullValue()));
        assertThat(panel, is(hasSize(2)));
    }

    @Test
    public void testFindByCountryCodeOrIsoCode() {
        Country country = countryRepository.findByCountryCodeOrIsoCode("USA", "wrongIsoCode");
        assertThat(country, is(notNullValue()));
        int updateResult = countryRepository.updateIsoCountryCode("US", "USA");
        assertThat(updateResult, is(1));
        country = countryRepository.findByCountryCodeOrIsoCode("wrongIsoCode", "US");
        assertThat(country, is(notNullValue()));
    }
}

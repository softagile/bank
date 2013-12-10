package com.softagile.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softagile.bank.domain.Company;
import com.softagile.bank.domain.Country;
import com.softagile.bank.domain.Panel;
/**
 * 
 * @author BKalali
 *
 */
public interface PanelRepository extends JpaRepository<Panel, Long>{   

    List<Panel> findAllByCountry(Country country);
    
    List<Panel> findByCountryCountryCode(String countrycode);
    
    List<Panel> findByCompany(Company company);

    
}

package com.softagile.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.softagile.bank.domain.Country;

import java.lang.String;
import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {

    Country findCountryByCountryCode(String countryCode);

    @Transactional(timeout = 10)
    Country findCountryByIsoCountryCode(String isoCountryCode);

    @Query("select c from Country c where c.countryCode = :countryCode or c.isoCountryCode = :isoCountryCode")
    Country findByCountryCodeOrIsoCode(@Param("countryCode") String countryCode,
        @Param("isoCountryCode") String isoCountryCode);
    
    @Modifying
    @Query("update Country c set c.isoCountryCode = ?1 where c.countryCode = ?2")
    int updateIsoCountryCode(String isoCountryCode, String countryCode);
    
    List<Country> findByIsoCountryCode(String isocountrycode);
    
    
}
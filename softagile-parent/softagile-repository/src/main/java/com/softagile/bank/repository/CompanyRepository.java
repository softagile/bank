package com.softagile.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softagile.bank.domain.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

    Company findCompanyByCompanyName(String companyName);

}

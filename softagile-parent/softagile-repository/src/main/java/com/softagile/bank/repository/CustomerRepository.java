package com.softagile.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softagile.bank.domain.Customer;
import java.util.Set;
import java.util.List;
import java.lang.String;
/**
 * 
 * @author BKalali
 *
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByName(String name);
	
	List<Customer> findByAccounts(Set accounts);
	
	List<Customer> findByAge(int age);
	
	List<Customer> findByFamilyName(String familyname);
	
}

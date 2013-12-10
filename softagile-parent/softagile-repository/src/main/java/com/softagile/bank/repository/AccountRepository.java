package com.softagile.bank.repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softagile.bank.domain.Account;
import com.softagile.bank.domain.AccountType;
/**
 * 
 * @author BKalali
 *
 */
public interface AccountRepository extends JpaRepository<Account, Long>{
	
	List<Account> findByCustomers(Set customers);
	List<Account> findByAccountType(AccountType accounttype);
	List<Account> findByOpeningDate(Date openingdate);

}

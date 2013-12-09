package com.softagile.bank.repository;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.softagile.bank.db.embedded.ApplicationEmbeddedConfig;
import com.softagile.bank.domain.Account;
import com.softagile.bank.domain.AccountType;
import com.softagile.bank.domain.Customer;

@ContextConfiguration(classes = ApplicationEmbeddedConfig.class)
public class CustomerRepositoryTest extends HSQLDatabasePopulator {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testSaveCustomer() {
    	Customer customer = new Customer();
    	Account account1 = new Account(AccountType.SAVING);
    	account1.setOpeningDate(new Date());
    	customer.getAccounts().add(account1);
    	
    	Account account2 = new Account(AccountType.CHECKING);
    	customer.getAccounts().add(account2);
    	customer = customerRepository.save(customer);
    	 assertThat(customer, is(notNullValue()));
    }
    
    @Test
    public void testLoadCustomerByName() {
    	Customer bahman = customerRepository.findByName("Bahman");
        assertThat(bahman, is(notNullValue()));
    }
    
    @Test
    public void testLoadCustomerById() {
    	Customer bahman = customerRepository.findOne(1L);
        assertThat(bahman.getAccounts().size(), is(2));
    }
    
    @Test
    public void testFindCustmerAccounts() {
    	Customer bahman = customerRepository.findOne(1L);
    	Set<Customer> customers = new HashSet<Customer>();
    	customers.add(bahman);
    	List<Account> accounts = accountRepository.findByCustomers(customers);
        assertThat(accounts.size(), is(2));
    }
}

package com.softagile.bank.db.embedded;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//ON Bamboo the parent project is set to run: -P integration-tests clean install verify
//TODO the integrator needs to be refactored to use real database and real server, 
//then create a bamboo plan to automate cucumber test cases  
@Configuration
@EnableJpaRepositories(basePackages = "com.softagile.bank")
@EnableTransactionManagement
@Profile(value = "test")
public class EmbeddedDatabaseConfig {

	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		return builder.setType(EmbeddedDatabaseType.HSQL).build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.HSQL);
		vendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.softagile.bank");
		factory.setPersistenceUnitName("pu1");
		factory.getJpaPropertyMap().put(
				"hibernate.cache.use_second_level_cache", Boolean.FALSE);
		factory.setDataSource(dataSource());
		return factory;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory().getObject());
		txManager.setPersistenceUnitName("pu1");
		return txManager;
	}

	@Bean
	public PersistenceAnnotationBeanPostProcessor getPersistenceAnnotationBeanPostProcessor() {
		PersistenceAnnotationBeanPostProcessor processor = new PersistenceAnnotationBeanPostProcessor();
		Map<String, String> units = new HashMap<String, String>();
		units.put("pu1", "persis1");
		units.put("pu2", "persis2");
		processor.setPersistenceUnits(units);
		processor.setDefaultPersistenceUnitName("pu1");
		return processor;
	}

}

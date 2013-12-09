package com.softagile.bank.client;


import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.softagile.bank.db.embedded.EmbeddedDatabaseConfig;

import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@Transactional
@ContextConfiguration(classes = EmbeddedDatabaseConfig.class)
@Cucumber.Options(features = "classpath:integratedfeatures", format = { "pretty",
		"html:target/cucumber-html-report",
		"json-pretty:target/cucumber-report.json" })
public class BaseFeatureIT {

}

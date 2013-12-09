package com.softagile.bank.service;


import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@Cucumber.Options(features = "classpath:features", format = { "pretty",
		"html:target/cucumber-html-report",
		"json-pretty:target/cucumber-report.json" })
public class BDDFeatureIT {

}

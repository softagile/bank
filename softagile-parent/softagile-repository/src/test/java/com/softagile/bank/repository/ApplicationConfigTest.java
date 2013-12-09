package com.softagile.bank.repository;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.softagile.bank.db.embedded.ApplicationEmbeddedConfig;
import com.softagile.bank.repository.PanelRepository;


public class ApplicationConfigTest {

    @Test
	public void bootstrapAppFromJavaConfigForTest() {
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationEmbeddedConfig.class);
		assertThat(context, is(notNullValue()));
		assertThat(context.getBean(PanelRepository.class), is(notNullValue()));
	}

    
	public void bootstrapAppFromXmlUsedForProduction() {
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/spring-data-application-context.xml");
		assertThat(context, is(notNullValue()));
		PanelRepository panelRepository = context.getBean(PanelRepository.class);
		assertThat(panelRepository, is(notNullValue()));
	}
}

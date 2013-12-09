
package com.softagile.bank.db.embedded;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@Configuration
@EnableJpaRepositories(basePackages="com.softagile.bank")
@Import(EmbeddedDatabaseConfig.class)
@EnableTransactionManagement
public class ApplicationEmbeddedConfig {

}

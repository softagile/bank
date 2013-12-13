package com.softagile.bank.service;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class EmbeddedDataBaseSetup {

    public static int COUNTER = 0;
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    protected void initTomcat() throws SQLException, ServletException {
        if (0 == COUNTER) {
            populateHSQLDatabase();
            COUNTER++;
        }
    }

    @Before
    public void setUpBeforeClass() throws Exception {

    }

    @After
    public void tearDownAfterClass() throws Exception {
       // tomcat.stop();
      //  tomcat.destroy();
    }

    public void populateHSQLDatabase() throws SQLException {
        File rootOfApp = new File("");
        String absolutePath = rootOfApp.getAbsolutePath();
        String absolutePathOfRepositoryProject = absolutePath.replace("softagile-service",
            "softagile-repository");
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new FileSystemResource(absolutePathOfRepositoryProject
            + "/src/test/resources/paneldata.sql"));

        Connection connection = null;

        try {
            connection = DataSourceUtils.getConnection(dataSource);
            populator.populate(connection);
        } finally {
            if (connection != null) {
                DataSourceUtils.releaseConnection(connection, dataSource);
            }
        }
    }
}

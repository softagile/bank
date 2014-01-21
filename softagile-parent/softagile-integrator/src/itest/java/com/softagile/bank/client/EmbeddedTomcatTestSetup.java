package com.softagile.bank.client;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class EmbeddedTomcatTestSetup {

    private static final String host = "http://localhost:";
    private static Tomcat tomcat;
    public static String REST_URI;

    public static int COUNTER = 0;
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    protected void initTomcat() throws SQLException, ServletException, LifecycleException {
        if (0 == COUNTER) {
            populateDatabase();
            COUNTER++;
            tomcat = new Tomcat();
            tomcat.setPort(0);
            File workingDirectory = new File("target/tomcatWorkingDirectory");
            tomcat.setBaseDir(workingDirectory.getAbsolutePath());
            tomcat.getHost().setAppBase(workingDirectory.getAbsolutePath());
            tomcat.getHost().setAutoDeploy(true);
            tomcat.getHost().setDeployOnStartup(true);
            File webappFile = new File("target/");
            String absolutePathOfCurrentProject = webappFile.getAbsolutePath();
            String absolutePathOfService = absolutePathOfCurrentProject.replace("softagile-integrator",
                "softagile-service") + File.separator + "softagile.war";
            tomcat.addWebapp("/softagile", absolutePathOfService);
            tomcat.start();
            REST_URI = host + tomcat.getConnector().getLocalPort() + "/softagile";
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

    public void populateDatabase() throws SQLException {
        File rootOfApp = new File("");
        String absolutePath = rootOfApp.getAbsolutePath();
        String absolutePathOfRepositoryProject = absolutePath.replace("softagile-integrator",
            "softagile-repository");
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new FileSystemResource(absolutePathOfRepositoryProject
            + "/src/test/resources/testdata.sql"));

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

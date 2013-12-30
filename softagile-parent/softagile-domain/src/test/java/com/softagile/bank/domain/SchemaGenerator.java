package com.softagile.bank.domain;

import java.io.IOException;

import org.hibernate.cfg.Configuration;
import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * The SchemaGenerator for creating DDL schema.
 * 
 * @author bkalali
 */
@SuppressWarnings("deprecation")
public class SchemaGenerator {

    private static Logger logger = (Logger) LoggerFactory.getLogger(SchemaGenerator.class);

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            exportSchema("core", "./target/bank-schema.sql", true, true);
        } else {
            exportSchema(args[0], args[1], Boolean.parseBoolean(args[2]), Boolean.parseBoolean(args[3]));
        }
    }

    public static void exportSchema(String persistenceUnitName, String destination, boolean create, boolean format) {
        logger.info("Started DDL Schema import");
        Ejb3Configuration jpaConfiguration = new Ejb3Configuration().configure(persistenceUnitName, null);
        jpaConfiguration.buildMappings();
        Configuration hibernateConfiguration = jpaConfiguration.getHibernateConfiguration();
       // AuditConfiguration.getFor(hibernateConfiguration);
       // EnversSchemaGenerator esg = new EnversSchemaGenerator(hibernateConfiguration);
       // SchemaExport schemaExport = esg.export();
       // schemaExport.setOutputFile(destination);
       // schemaExport.setFormat(format);
       // schemaExport.setDelimiter(";");
       // schemaExport.drop(true, false);
      //  schemaExport.create(true, false);
        logger.info("DDL Schema exported to {}", destination);
    }
}
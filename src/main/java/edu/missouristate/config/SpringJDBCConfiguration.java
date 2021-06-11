package edu.missouristate.config;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class SpringJDBCConfiguration {

    @Bean(name = "embedded", destroyMethod = "shutdown")
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.DERBY).addScript("address.sql")
				.build();
        return db;
    }

    public String dataSourceName() {
        return "";
    }

    /**
     * Method returns String[] of packages for spring to scan for Entity Classes. 
     * Defined package strings should only be for entity packages that use this 
     * datasource
     * 
     * @return String[]
     */
    protected String[] packagesToScan() {
        List<String> packages = Arrays.asList("edu.missouristate");
        return packages.toArray(new String[packages.size()]);
    }

}
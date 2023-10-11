package com.demo.masterslavesample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@Import({PrimaryDatasourceConfig.class, SlaveDatasourceConfiguration.class})
public class JDBCTemplateConfig {
    @Autowired
    @Qualifier("masterDataSource")
    private DataSource masterDataSource;

    @Autowired
    @Qualifier("slaveDataSource")
    private DataSource slaveDataSource;

    @Bean
    public JdbcTemplate masterJdbcTemplate() {
        return new JdbcTemplate(masterDataSource);
    }

    @Bean
    public JdbcTemplate slaveJdbcTemplate() {
        return new JdbcTemplate(slaveDataSource);
    }

}

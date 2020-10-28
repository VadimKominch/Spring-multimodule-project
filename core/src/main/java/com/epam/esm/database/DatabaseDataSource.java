package com.epam.esm.database;

import com.epam.esm.service.GiftService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseDataSource {
    @Bean
    public DataSource getSource() {
        DriverManagerDataSource builder = new DriverManagerDataSource();
        builder.setDriverClassName("com.mysql.cj.jdbc.Driver");
        builder.setUrl("jdbc:mysql://localhost:3306/module2_base");
        builder.setUsername("root");
        builder.setPassword("root");
        return builder;
    }
}
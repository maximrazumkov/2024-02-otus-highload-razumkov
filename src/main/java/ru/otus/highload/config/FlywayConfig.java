package ru.otus.highload.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {

//    @Bean(initMethod = "migrate")
//    public Flyway flyway(@Qualifier("masterDataSource") DataSource masterDataSource) {
//        return Flyway.configure()
//                .dataSource(masterDataSource)
//                .load();
//    }

//    @Bean(initMethod = "migrate")
//    public Flyway flyway(DataSource dataSource) {
//        return Flyway.configure()
//                .dataSource(dataSource)
//                .load();
//    }
}

package ru.otus.highload.config;

import org.flywaydb.core.Flyway;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

//@Configuration
public class DatabaseConfig {

//    @Bean(name = "dataSource")
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource dataSource() {
//        return new DriverManagerDataSource();
//    }
//
//    @Bean
//    public DataSource lazyDataSource(DataSource dataSource) {
//        return new LazyConnectionDataSourceProxy(dataSource);
//    }
//
//    @Bean
//    public JdbcTemplate jdbcTemplate(DataSource lazyDataSource) {
//        return new JdbcTemplate(lazyDataSource);
//    }
//
////    @Bean
////    public DataSourceTransactionManager transactionManager(DataSource lazyDataSource) {
////        return new DataSourceTransactionManager(lazyDataSource);
////    }
//
//    @Bean(initMethod = "migrate")
//    public Flyway flyway(DataSource dataSource) {
//        return Flyway.configure()
//                .dataSource(dataSource)
//                .load();
//    }
}

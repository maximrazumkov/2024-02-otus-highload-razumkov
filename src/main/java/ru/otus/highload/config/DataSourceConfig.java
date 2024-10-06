package ru.otus.highload.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return new DriverManagerDataSource();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave1")
    public DataSource slave1DataSource() {
        return new DriverManagerDataSource();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave2")
    public DataSource slave2DataSource() {
        return new DriverManagerDataSource();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(@Qualifier("lazyConnectionDataSourceProxy") DataSource lazyConnectionDataSourceProxy) {
        return new JdbcTemplate(lazyConnectionDataSourceProxy);
    }

    @Bean
    @Primary
    @DependsOn({"masterDataSource", "slave1DataSource", "slave2DataSource", "routingDataSource"})
    public DataSource lazyConnectionDataSourceProxy(@Qualifier("routingDataSource") DataSource routingDataSource) {
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }

    @Bean
    public DataSource routingDataSource(
            @Qualifier("masterDataSource") DataSource masterDataSource,
            @Qualifier("slave1DataSource") DataSource slave1DataSource,
            @Qualifier("slave2DataSource") DataSource slave2DataSource
    ) {
        ReplicationRoutingDataSource routingDataSource = new ReplicationRoutingDataSource();

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("masterDataSource", masterDataSource);
        dataSourceMap.put("slave1DataSource", slave1DataSource);
        dataSourceMap.put("slave2DataSource", slave2DataSource);
        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(masterDataSource);

        return routingDataSource;
    }
}
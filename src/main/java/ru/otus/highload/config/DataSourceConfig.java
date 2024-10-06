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
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class DataSourceConfig {

    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return new DriverManagerDataSource();
    }

    @Bean(name = "slave1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave1")
    public DataSource slave1DataSource() {
        return new DriverManagerDataSource();
    }

    @Bean(name = "slave2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave2")
    public DataSource slave2DataSource() {
        return new DriverManagerDataSource();
    }

//    @Bean(name = "masterJdbcTemplate")
//    public JdbcTemplate masterJdbcTemplate(@Qualifier("masterDataSource") DataSource masterDataSource) {
//        return new JdbcTemplate(masterDataSource);
//    }
//
//    @Bean(name = "slave1JdbcTemplate")
//    public JdbcTemplate slave1JdbcTemplate(@Qualifier("slave1DataSource") DataSource slave1DataSource) {
//        return new JdbcTemplate(slave1DataSource);
//    }
//
//    @Bean(name = "slave2JdbcTemplate")
//    public JdbcTemplate slave2JdbcTemplate(@Qualifier("slave2DataSource") DataSource slave2DataSource) {
//        return new JdbcTemplate(slave2DataSource);
//    }

//    @Bean
//    public Map<String, JdbcTemplate> jdbcTemplateMap(
//            @Qualifier("masterJdbcTemplate") JdbcTemplate masterJdbcTemplate,
//            @Qualifier("slave1JdbcTemplate") JdbcTemplate slave1JdbcTemplate,
//            @Qualifier("slave2JdbcTemplate") JdbcTemplate slave2JdbcTemplate
//    ) {
//        ConcurrentHashMap<String, JdbcTemplate> jdbcTemplateMap = new ConcurrentHashMap<>();
//        jdbcTemplateMap.put("masterJdbcTemplate", masterJdbcTemplate);
//        jdbcTemplateMap.put("slave1JdbcTemplate", slave1JdbcTemplate);
//        jdbcTemplateMap.put("slave2JdbcTemplate", slave2JdbcTemplate);
//        return jdbcTemplateMap;
//    }
//
    @Bean
    public JdbcTemplate jdbcTemplate(@Qualifier("lazyConnectionDataSourceProxy") DataSource lazyConnectionDataSourceProxy) {
        return new JdbcTemplate(lazyConnectionDataSourceProxy);
    }

    @Primary
    @Bean(name = "lazyConnectionDataSourceProxy")
    // @DependsOn required!! thanks to Michel Decima
    @DependsOn({"masterDataSource", "slave1DataSource", "slave2DataSource", "routingDataSource"})
    public DataSource lazyConnectionDataSourceProxy(@Qualifier("routingDataSource") DataSource routingDataSource) {
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }

    @Bean(name = "routingDataSource")
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
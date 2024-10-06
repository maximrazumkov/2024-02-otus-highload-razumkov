package ru.otus.highload.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

//@Configuration
public class RoutingDataSourceConfig {

//    private final AtomicInteger counter = new AtomicInteger(0); // Для round-robin
//
//    @Bean
//    public DataSource routingDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
//                                        @Qualifier("slave1DataSource") DataSource slave1DataSource,
//                                        @Qualifier("slave2DataSource") DataSource slave2DataSource) {
//
//        AbstractRoutingDataSource routingDataSource = new AbstractRoutingDataSource() {
//            @Override
//            protected Object determineCurrentLookupKey() {
//                return RoutingContext.getCurrentDataSourceType(); // Вернёт "master" или "slaveX"
//            }
//        };
//
//        Map<Object, Object> dataSourceMap = new HashMap<>();
//        dataSourceMap.put("master", masterDataSource);
//        dataSourceMap.put("slave1", slave1DataSource);
//        dataSourceMap.put("slave2", slave2DataSource);
//
//        routingDataSource.setTargetDataSources(dataSourceMap);
//        routingDataSource.setDefaultTargetDataSource(masterDataSource);
//
//        return routingDataSource;
//    }
//
//    @Bean
//    public JdbcTemplate jdbcTemplate(DataSource routingDataSource) {
//        return new JdbcTemplate(routingDataSource);
//    }
}

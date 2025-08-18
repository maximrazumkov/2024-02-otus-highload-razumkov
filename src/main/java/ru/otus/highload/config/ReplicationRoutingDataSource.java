package ru.otus.highload.config;

//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//import org.springframework.transaction.support.TransactionSynchronizationManager;
//
//import java.util.concurrent.ThreadLocalRandom;
//
//public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {
//
//    private static final String[] SLAVE_NODES = {"masterJdbcTemplate", "slave1JdbcTemplate", "slave2JdbcTemplate"};
//
//    @Override
//    protected Object determineCurrentLookupKey() {
//        boolean currentTransactionReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
//        if (currentTransactionReadOnly) {
//            int index = ThreadLocalRandom.current().nextInt(1, 100) % 3;
//            return SLAVE_NODES[index];
//        }
//        return "masterJdbcTemplate";
//    }
//}

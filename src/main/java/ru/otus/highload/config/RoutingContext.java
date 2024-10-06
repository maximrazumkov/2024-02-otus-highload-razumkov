package ru.otus.highload.config;

import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class RoutingContext {
    private static final ThreadLocal<String> currentDataSource = new ThreadLocal<>();

    private static final String[] SLAVE_NODES = {"slave1", "slave2", "master"};
    private static final AtomicInteger counter = new AtomicInteger(0);

    public static void setDataSourceTypeForRead() {
        // Применяем round-robin стратегию
        int index = ThreadLocalRandom.current().nextInt(2, 100) % 3;
        HashSet<Object> objects = new HashSet<>();
        currentDataSource.set(SLAVE_NODES[index]);
    }

    public static void setDataSourceTypeForWrite() {
        currentDataSource.set("master");
    }

    public static String getCurrentDataSourceType() {
        return currentDataSource.get();
    }

    public static void clear() {
        currentDataSource.remove();
    }
}
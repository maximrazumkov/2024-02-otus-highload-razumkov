package ru.otus.highload.component;

//@Aspect
//@Component
public class DataSourceAspect {

//    @Before("execution(* ru.otus.highload.repository..*.find*(..)) || execution(* ru.otus.highload.repository..*.get*(..))")
//    public void setReadOnlyDataSource() {
//
//        RoutingContext.setDataSourceTypeForRead(); // Направляем запросы на один из slave или master
//    }
//
//    @Before("execution(* ru.otus.highload.repository..*.save*(..)) || execution(* ru.otus.highload.repository..*.update*(..)) || execution(* ru.otus.highload.repository..*.delete*(..))")
//    public void setWriteDataSource() {
//        RoutingContext.setDataSourceTypeForWrite(); // Направляем запросы на master
//    }
//
//    @After("execution(* ru.otus.highload.repository..*(..))")
//    public void clearDataSource() {
//        RoutingContext.clear();
//    }
}

package com.tsstu.front.core.datasource;

public class DBContextHolder {
      
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();  
      
    public static void switchDataSource(DataSource dataSource) {
    	contextHolder.set(dataSource.value().name());
    }
    
    public static void switchDataSource(Source source) {
    	contextHolder.set(source.name());
    }
      
    public static String getDBType() {  
        return contextHolder.get();  
    }  
      
    public static void clearDBType() {  
        contextHolder.remove();  
    }
    
}
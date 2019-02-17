package com.ysu.config.mybatis.datasource;


public class DynamicDataSourceHolder {
    public static final ThreadLocal<String> holder = new ThreadLocal<String>();

    public static void setDataSource(String name) {
        holder.set(name);
    }

    public static String getDataSouce() {
        return holder.get();
    }

    public static void clearDataSouce() {
        holder.remove();
    }

    public static void setMaster(String dbName) {
        DynamicDataSourceHolder.setDataSource(dbName);
    }

    public static void setSlave(String dbName) {
        DynamicDataSourceHolder.setDataSource(dbName);
    }
}
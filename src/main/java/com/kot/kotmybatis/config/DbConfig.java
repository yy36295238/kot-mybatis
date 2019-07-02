package com.kot.kotmybatis.config;

public class DbConfig {

    public org.apache.ibatis.session.Configuration configuration() {
        final org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        return configuration;
    }

}

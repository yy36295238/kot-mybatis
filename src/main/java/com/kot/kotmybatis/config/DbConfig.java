package com.kot.kotmybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@MapperScan("com.kot.kotmybatis.mapper")
public class DbConfig {

    @Bean
    public DataSource dataSource() throws SQLException {
        final DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername("kakrot");
        druidDataSource.setPassword("Kakrot!1");
        druidDataSource.setUrl("jdbc:mysql://www.yyself.com/kakrot?useUnicode=true&characterEncoding=utf-8");
        druidDataSource.init();
        return druidDataSource;
    }
}

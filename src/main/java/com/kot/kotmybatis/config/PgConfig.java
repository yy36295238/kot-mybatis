package com.kot.kotmybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.kot.kotmybatis.biz.pg.biz.mapper", sqlSessionFactoryRef = "pgSqlSessionFactory")
public class PgConfig extends BaseDbConfig {

    @Bean(name = "pgDataSource")
    @ConfigurationProperties(prefix = "pg.spring.datasource")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "pgSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("pgDataSource") DataSource dataSource) throws Exception {
        return super.baseSqlSessionFactory(dataSource);
    }

    @Bean(name = "pgTransactionManager")
    public PlatformTransactionManager pgTransactionManager(@Qualifier("pgDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}

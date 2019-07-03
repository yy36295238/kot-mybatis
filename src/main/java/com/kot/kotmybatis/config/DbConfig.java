package com.kot.kotmybatis.config;

import com.github.pagehelper.PageInterceptor;
import kot.bootstarter.kotmybatis.plugin.KeyPropertiesPlugin;
import kot.bootstarter.kotmybatis.plugin.MapResultToCamelPlugin;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;

import javax.sql.DataSource;

public class DbConfig {

    public org.apache.ibatis.session.Configuration configuration() {
        final org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        return configuration;
    }

    public SqlSessionFactory baseSqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfiguration(configuration());
        Interceptor[] interceptors = {new MapResultToCamelPlugin(), new KeyPropertiesPlugin(), new PageInterceptor()};
        bean.setPlugins(interceptors);
        return bean.getObject();
    }


}

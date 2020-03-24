package com.cheryev.crm.auth.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.lang.annotation.Annotation;
import java.util.Properties;

@Configuration(value = "authDataSourceConfig")
public class AuthDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.auth")
    public DataSource setDataSource() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @Bean(name = "authTransactionManager")
    public DataSourceTransactionManager setTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "authSqlSessionFactory")
    public SqlSessionFactory setSqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/cheryev/crm/auth/mapper/*.xml"));
        PageInterceptor pi = new PageInterceptor();
        Properties p = new Properties();
        p.setProperty("helperDialect", String.valueOf("mysql"));
        pi.setProperties(p);
        Interceptor[] plugins = new Interceptor[1];
        plugins[0] = pi;
        bean.setPlugins(plugins);
        return bean.getObject();
    }

    @Bean(name = "authSqlSessionTemplate")
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("authSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "authMapperScannerConfigurer")
    public MapperScannerConfigurer mapperScannerConfigurer() throws ClassNotFoundException {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("authSqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.cheryev.crm.auth");
        mapperScannerConfigurer.setAnnotationClass(Class.forName("org.apache.ibatis.annotations.Mapper").asSubclass(Annotation.class));
        return mapperScannerConfigurer;
    }


}
